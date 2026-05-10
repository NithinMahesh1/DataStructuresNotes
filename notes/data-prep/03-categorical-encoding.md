# 03 — Categorical Encoding

> **Source:** `module 4, extended linear regression/Module 4 - Extended Linear Regression.md` (ESE-5410), §"Categorical Inputs in Linear Regression" and §"Categorical Variables with More Than Two Levels".
> **See also:** [data-science/06-r-formula-notation.md](../data-science/06-r-formula-notation.md) for the R equivalent (factors are auto-encoded by `lm`).

Linear and distance-based models can't consume strings — they need numbers. **Encoding** converts categorical columns (e.g., `gender`, `ethnicity`, `region`) into a numeric representation a model can use.

## Why one-hot / dummy encoding

The naive idea — encode a 3-level category as `{0, 1, 2}` — introduces a fake **ordering and spacing**: the model would assume level 2 is "twice as far" from level 0 as level 1. That's wrong for nominal categories like ethnicity or region.

**Dummy encoding** instead creates a separate 0/1 indicator column for each level, then drops one to avoid redundancy.

## The K → K−1 rule

For a categorical variable with **K levels**, create **K − 1 dummy columns**.

The dropped level is the **baseline** (a.k.a. reference category). Its effect is absorbed into the model's intercept.

### Worked example: ethnicity ∈ {African American, Asian, Caucasian} (K = 3)

Pick **African American** as baseline. Create 2 dummies:

| Person ethnicity | `is_asian` | `is_caucasian` |
|---|---|---|
| African American | 0 | 0 |
| Asian            | 1 | 0 |
| Caucasian        | 0 | 1 |

Plug into a linear model:

`y = β₀ + β₁·is_asian + β₂·is_caucasian + ε`

| Group            | Predicted mean |
|---|---|
| African American | `β₀`          |
| Asian            | `β₀ + β₁`     |
| Caucasian        | `β₀ + β₂`     |

So:
- `β₀` = mean response for the **baseline** group.
- `β₁` = how much higher (or lower) Asian is *relative to* baseline.
- `β₂` = how much higher (or lower) Caucasian is *relative to* baseline.

The choice of baseline doesn't change the fit — it only changes how the coefficients read. Pick whichever group makes the comparisons easiest to interpret (e.g., the largest group, a "control", or the most natural reference for the question you're asking).

### Binary categories — the trivial case

A 2-level variable needs just **one** dummy:

```python
df["is_female"] = (df["gender"] == "F").astype(int)
```

The model coefficient on `is_female` is then "how much higher (or lower) females are than males, on average."

## Why drop one column? — the dummy variable trap

If you keep all K dummies, they sum to 1 in every row → perfectly collinear with the intercept → the system has no unique solution. Software either errors loudly or silently drops one for you. The K−1 rule is the safe default.

## Doing it in Python

### `pd.get_dummies` — pandas

The most common path. Drops the first level automatically with `drop_first=True`:

```python
df_enc = pd.get_dummies(df, columns=["ethnicity"], drop_first=True)
# Adds columns: ethnicity_Asian, ethnicity_Caucasian
# (African American absorbed into the intercept)
```

### `C(...)` — statsmodels formula API

Wrap the column with `C()` to mark it as categorical. statsmodels handles K−1 encoding behind the scenes:

```python
import statsmodels.formula.api as smf
fit = smf.ols("balance ~ C(ethnicity)", data=df).fit()
fit.summary()
# Coefficients shown as C(ethnicity)[T.Asian], C(ethnicity)[T.Caucasian]
# (T = "treatment" coding — the K−1 default)
```

Set the baseline explicitly:

```python
smf.ols(
    "balance ~ C(ethnicity, Treatment(reference='Caucasian'))",
    data=df,
).fit()
```

### `OneHotEncoder` — scikit-learn

The pipeline-friendly option. Use this when the encoder lives inside a `Pipeline` you train on train and apply to test:

```python
from sklearn.preprocessing import OneHotEncoder

enc = OneHotEncoder(drop="first", sparse_output=False, handle_unknown="ignore")
X_cat = enc.fit_transform(df[["ethnicity"]])
```

`drop="first"` matches the K−1 convention. `handle_unknown="ignore"` is critical for production: if an unseen category appears at predict time, the encoder emits all-zeros rather than crashing.

## Ordinal vs nominal

**Nominal** (no inherent order): ethnicity, region, blood type → one-hot / dummy.

**Ordinal** (clear order): `{S, M, L, XL}`, ratings, education levels → consider integer-encoding *if* the spacing is roughly equal:

```python
df["size_ord"] = df["size"].map({"S": 1, "M": 2, "L": 3, "XL": 4})
```

When the spacing isn't equal (`{poor, fair, good, excellent}` — is the gap from "fair" to "good" the same as from "good" to "excellent"?), one-hot is safer.

## High-cardinality features

If a category has hundreds or thousands of levels (zip code, product ID, user ID), one-hot blows up the column count. Common alternatives:

- **Frequency encoding** — replace each level with its training-set frequency.
- **Target / mean encoding** — replace each level with the mean target value for that group. Works well, but risks **leakage** — must be computed only on training folds, never on the row being predicted.
- **Group rare levels** into an "Other" bucket before encoding.
- **Embeddings** — for deep models.

## Pitfalls

1. **Train/test consistency** — fit the encoder on training data only; apply to test. With `pd.get_dummies` it's easy to end up with different columns in train vs test (if a level is missing in one set). `OneHotEncoder` with `handle_unknown="ignore"` is safer.
2. **Numeric-looking categoricals** — `cylinders ∈ {4, 6, 8}` *looks* numeric but is really 3 categories. Fitting it as a continuous predictor implies "8 cylinders has 2× the effect of 4 cylinders," which is usually wrong. Coerce to category and one-hot.
3. **Interactions with categorical variables** — if you also include an `interaction = continuous × dummy` term, the interaction coefficients become "extra slope for the non-baseline group." See [02-feature-engineering.md](02-feature-engineering.md) for the hierarchy principle.
4. **Memory blow-up** for high-cardinality columns — see above.

## Quick reference

| Tool | Where it fits | K−1 by default? |
|---|---|---|
| `pd.get_dummies(..., drop_first=True)` | Quick exploratory work, one-off scripts | Yes (with `drop_first=True`) |
| `smf.ols("y ~ C(col)", ...)` | Statistical analysis, hypothesis testing | Yes |
| `OneHotEncoder(drop="first")` | Production pipelines, scikit-learn workflows | Yes (with `drop="first"`) |
