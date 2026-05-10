# 07 — PCA and Bootstrap

> **Source:** `final exam/final mod 13 + 14.md` (ESE-5410) for PCA; `module 7, cross-validation/mod7 slides/` for bootstrap.
> **See also:** [04-scaling-and-standardization.md](04-scaling-and-standardization.md) (PCA requires standardization), [data-science/17-cross-validation.md](../data-science/17-cross-validation.md) (the related resampling idea).

Two resampling / dimension techniques that often live in the data-prep stage:

- **PCA** — compress `p` correlated features into `M ≪ p` uncorrelated ones, used as preprocessing.
- **Bootstrap** — resample with replacement to estimate the variance of any statistic.

---

## PCA — Principal Component Analysis

PCA finds **new axes** (linear combinations of the original features) along which the data varies most. The first axis (PC1) captures the most variance, PC2 the next-most while being orthogonal to PC1, and so on.

Used as data prep when:
- The original features are highly correlated (multicollinearity, see [05-multicollinearity.md](05-multicollinearity.md)).
- You have many features and want to compress to a smaller, uncorrelated set before modeling.
- You want to visualize a high-dimensional dataset in 2D / 3D.

It's **unsupervised** — no target involved. PCA doesn't know what you're trying to predict; it just chases variance.

### The pipeline

1. **Standardize** every column to mean 0, std 1.
2. Compute the `p × p` sample covariance matrix `Σ̂_X` from the centered data.
3. Eigen-decompose: find eigenvectors `φ₁, φ₂, …, φ_p` and eigenvalues `λ₁ ≥ λ₂ ≥ … ≥ λ_p ≥ 0`.
4. Pick the top `M` eigenvectors as the new axes.
5. Project each observation `x` onto them: `z = [φ₁ … φ_M]ᵀ · x`. Each observation now has `M` coordinates instead of `p`.

The eigenvalue `λ_m` is the variance captured by component `m`.

### Why standardize first

Variance scales with the units of measurement. A column in dollars varies more than the same quantity in millions of dollars. **PCA points PC1 at whichever feature has the largest variance** — so without standardization, the answer just tells you which column has the loudest units.

Standardize when:
- Features are in different units (dollars vs. percentages vs. counts).
- Features have different numerical ranges.
- You're unsure whether scales are comparable.

If features are all in the same units and meaningful as-is, you can skip standardization. But it's the safe default.

### Choosing M — proportion of variance explained

`PVE_m = λ_m / Σⱼ λⱼ` — the share of total variance captured by component `m`.

Cumulative PVE (running total as you add components) is monotonically non-decreasing and reaches 1 at `M = p`.

Two heuristics for picking `M`:
- **PVE threshold** — pick the smallest `M` such that cumulative PVE ≥ 90% (or 80%, 95% — by use case).
- **Scree plot** — bar chart of `λ₁, λ₂, …, λ_p`; look for the elbow where the marginal gain falls off. (Subjective, same caveat as the elbow method in clustering.)

### Worked dimension example

`N = 1000` observations, `p = 50` features, keep `M = 4` components:

| Object | Shape |
|---|---|
| `xᵢ` (one observation) | `50 × 1` |
| `Σ̂_X` (covariance matrix) | `50 × 50` |
| `[φ₁ … φ₄]` (top eigenvectors as columns) | `50 × 4` |
| `[φ₁ … φ₄]ᵀ` | `4 × 50` |
| `zᵢ = [φ₁ … φ₄]ᵀ · xᵢ` | `(4 × 50) · (50 × 1) = 4 × 1` |

Each observation goes from 50 numbers down to 4 — a 12.5× reduction.

### In Python

```python
from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import Pipeline

pipe = Pipeline([
    ("scale", StandardScaler()),
    ("pca",   PCA(n_components=4)),   # or n_components=0.9 for "keep enough for 90% PVE"
])
Z_train = pipe.fit_transform(X_train)
Z_test  = pipe.transform(X_test)

pca = pipe.named_steps["pca"]
print(pca.explained_variance_ratio_)
print(pca.explained_variance_ratio_.cumsum())
```

Use `n_components=0.9` to let sklearn pick the smallest `M` that retains 90% of variance.

### Reading a biplot

A biplot overlays:
- **Scores** — each observation plotted at `(z₁, z₂)` in the reduced PC1/PC2 space.
- **Loading arrows** — original feature axes projected into the same space. Arrow direction = which PC the feature contributes to most; arrow length = strength.

If the loading arrow for `feature_k` points right and is long, `feature_k` has a large positive weight on PC1.

⚠️ Two observations close together on the biplot have *similar projected coordinates*, not necessarily similar original feature values — projection loses information.

### When NOT to use PCA

- **Interpretability matters.** PCs are linear combinations of every original feature — `PC1 = 0.3·x₁ + 0.7·x₂ − 0.2·x₃ + …`. Hard to explain to a stakeholder.
- **You're doing supervised learning and the target is the goal.** PCA is unsupervised — it ignores `y`. The "biggest variance" direction may not be the "most predictive" direction. Partial Least Squares (PLS) is the supervised analog if you want target-aware dimension reduction.
- **You need to recover original features.** Once you've projected, original column meanings are gone.

---

## Bootstrap

Bootstrap is a **resampling-with-replacement** technique that estimates how much a statistic (mean, median, regression coefficient, model accuracy — anything) would vary if you redrew your sample.

### The procedure

Given a dataset of `N` observations:

1. Draw `N` rows **with replacement** from the original — some rows appear multiple times, others not at all. Call this a bootstrap sample.
2. Compute your statistic on this bootstrap sample (e.g., the mean, or refit the model and grab a coefficient).
3. Repeat `B` times (commonly `B = 1000`).
4. The distribution of the `B` statistic values estimates the sampling distribution.

From those `B` values you can read off:
- **Standard error** — the standard deviation of the bootstrap statistics.
- **Confidence interval** — the 2.5th and 97.5th percentiles of the bootstrap distribution give an empirical 95% CI.

### Why it works

The empirical distribution of your sample is the best estimate of the population distribution you have. Resampling from it simulates "redrawing the data" without needing to actually collect more.

### Bootstrap vs cross-validation

They look similar (both sample from the data) but answer different questions:

| | Bootstrap | Cross-validation |
|---|---|---|
| **Sampling** | With replacement | Without replacement |
| **Used for** | Estimating uncertainty / SE / CI of a statistic | Estimating out-of-sample prediction error |
| **Output** | A distribution / histogram of values | An average prediction error across folds |

### In Python

```python
import numpy as np

n = len(df)
B = 1000
estimates = np.empty(B)

for b in range(B):
    idx = np.random.randint(0, n, size=n)         # sample N rows w/ replacement
    sample = df.iloc[idx]
    estimates[b] = sample["x"].mean()             # statistic of interest

se   = estimates.std()
ci95 = np.percentile(estimates, [2.5, 97.5])
```

`scipy.stats.bootstrap` (added in SciPy 1.7) wraps this up, including BCa-corrected CIs.

### Pitfalls

1. **Time-series data** — straight resampling destroys temporal structure. Use **block bootstrap** (resample contiguous blocks) instead.
2. **Small `N`** — bootstrap from 10 observations gives mostly the same 10 observations back; the distribution is too narrow.
3. **Pathological statistics** — bootstrap estimates the variance of *smooth* statistics well. Things like the maximum or minimum (not smooth functions of the data) are estimated poorly.

## Quick reference

| Goal | Tool |
|---|---|
| Compress correlated features into uncorrelated ones | PCA |
| Reduce dimensionality before modeling | PCA |
| Visualize high-D data in 2D | PCA biplot |
| Quantify uncertainty in any statistic | Bootstrap |
| 95% CI for a coefficient or metric | Bootstrap percentiles |
| Out-of-sample error | Cross-validation, not bootstrap |
