# 04 — Scaling and Standardization

> **Source:** `final exam/final mod 13 + 14.md` (ESE-5410), §"standardization", with extras on pipeline placement.
> **See also:** [data-science/16-distance-metrics.md](../data-science/16-distance-metrics.md) for the distance-metrics-flavored Min-Max vs Z-score table.

**Scaling** rewrites your numeric columns onto comparable ranges so a feature measured in millions doesn't drown out a feature measured in [0, 1].

This matters because several model families weight features **by magnitude**:

- **Distance-based** (KNN, k-means, hierarchical clustering, RBF-kernel SVM) — bigger ranges contribute more to Euclidean distance.
- **Regularized** (Ridge, Lasso) — penalty `λ·Σβⱼ²` shrinks coefficients toward zero. Coefficients on small-range features are mechanically larger to produce the same effect → get penalized more heavily.
- **Gradient-based** (logistic regression with SGD, neural networks) — feature scales control how the loss surface is shaped; mismatched scales make optimization slow or unstable.
- **PCA** — variance is the entire signal. PCA fixates on whichever feature has the largest variance, which without scaling is whichever has the loudest units. See [07-pca-and-bootstrap.md](07-pca-and-bootstrap.md).

**Tree-based models** (decision tree, random forest, gradient-boosted trees) use only feature *order*, never magnitude — scaling is a no-op for them.

## Standardization (z-score)

`x' = (x − x̄) / σ`

Each column ends up with **mean 0 and std 1**. Negative values mean "below the mean," positive mean "above," and the magnitude is in standard-deviation units.

```python
from sklearn.preprocessing import StandardScaler

scaler = StandardScaler()
X_train_std = scaler.fit_transform(X_train)
X_test_std  = scaler.transform(X_test)   # ← no fit; uses train's μ and σ
```

When to use: most ML pipelines, when the underlying distribution is roughly normal, and whenever you'd want to compare features in standard-deviation units.

### Interpreting standardized values

If a column has `x̄ = 50, σ = 8`:

| Raw `x` | Standardized `x'` | Reading |
|---|---|---|
| 58 | +1.00 | One std above the mean |
| 50 |  0.00 | Exactly average |
| 34 | −2.00 | Two std below the mean — tail of the distribution |

A standardized score of `+1` means "one standard deviation above the mean" regardless of the original units.

## Min-max normalization

`x' = (x − min) / (max − min)`

Every column lands in **[0, 1]**.

```python
from sklearn.preprocessing import MinMaxScaler

scaler = MinMaxScaler()
X_train_norm = scaler.fit_transform(X_train)
X_test_norm  = scaler.transform(X_test)
```

When to use:
- A bounded input range is required (some neural-net activations).
- You need a guaranteed range for visualization or downstream consumption.
- Outliers are not a concern. ⚠️ Min-max is **very** sensitive to outliers — a single huge value compresses everyone else into a thin band near 0.

## Robust scaling (median + IQR)

`x' = (x − median) / IQR`

Replaces mean with median and std with the interquartile range. **Robust to outliers** because it doesn't use min, max, or mean.

```python
from sklearn.preprocessing import RobustScaler

scaler = RobustScaler()
X_train_r = scaler.fit_transform(X_train)
```

When to use: heavy-tailed columns where Min-max is dominated by outliers and Standard scaling is inflated by them.

## Comparison

| | Center | Spread | Output range | Outlier-robust? |
|---|---|---|---|---|
| **Standardize** (z-score) | Mean | Std | Roughly `[−3, 3]` for normal-ish data; unbounded | No |
| **Min-max** | Min | `Max − Min` | Exactly `[0, 1]` | No |
| **Robust** | Median | IQR | Centered at 0; most data near `[−1, 1]`; unbounded | Yes |

## Pipeline placement — fit on train, apply to test

The single most important rule: **fit the scaler on training data only**, then apply it to both train and test.

```python
# WRONG — leaks test-set info (the test mean and std) into preprocessing
scaler = StandardScaler().fit(np.vstack([X_train, X_test]))

# RIGHT
scaler = StandardScaler().fit(X_train)
X_train_std = scaler.transform(X_train)
X_test_std  = scaler.transform(X_test)
```

A real test point should be standardized using the **train**'s `μ` and `σ` — that's what it'll see in production. Using combined statistics is a small but real form of data leakage.

A `Pipeline` enforces this automatically:

```python
from sklearn.pipeline import Pipeline
from sklearn.linear_model import Ridge

pipe = Pipeline([
    ("scale", StandardScaler()),
    ("ridge", Ridge(alpha=1.0)),
])
pipe.fit(X_train, y_train)        # scaler.fit() runs on train only
pipe.predict(X_test)              # scaler.transform() with train stats
```

In cross-validation, the scaler is re-fit on each training fold — the correct behavior.

## When NOT to scale

- **Tree-based models** — order-invariant; scaling has no effect (other than wasting compute).
- **When the absolute scale carries meaning** — if you need coefficients in original units (e.g., a regression where `β` is "dollars per unit"), scaling makes interpretation harder. You can scale internally and back-transform after fitting.
- **One-hot encoded columns** — already 0/1; further scaling distorts the indicator without benefit. Either skip them or use `ColumnTransformer` to scale only continuous columns.

## Pitfalls

1. **Re-fitting on test data** — see above. The most common scaling bug.
2. **Scaling before train/test split** — same issue. Split first, then fit the scaler on train.
3. **Min-max on outlier-heavy data** — one extreme value crushes everyone. Use Robust or remove outliers first.
4. **Standardizing categorical / dummy columns** — unhelpful. Use `ColumnTransformer` to scale numeric columns only.
5. **Forgetting to standardize before PCA / regularization** — PCA's "biggest variance" becomes "biggest scale." Ridge / Lasso penalize bigger coefficients, which favors small-range features.

## Quick reference

| Situation | Use |
|---|---|
| Default for most ML | `StandardScaler` |
| Need bounded `[0, 1]` outputs | `MinMaxScaler` |
| Heavy outliers in features | `RobustScaler` |
| Tree-based models | Don't scale |
| PCA / regularization | Standardize first, always |
