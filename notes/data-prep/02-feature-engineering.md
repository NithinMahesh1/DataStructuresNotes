# 02 — Feature Engineering

> **Source:** `module 4, extended linear regression/Module 4 - Extended Linear Regression.md` (ESE-5410), §"Modeling Nonlinear Effects with Polynomial Regression" and §"Interaction Terms in Linear Regression".
> **See also:** [03-categorical-encoding.md](03-categorical-encoding.md) for turning categorical strings into numeric features.

Feature engineering is **building new columns from existing ones** so the model can express relationships it couldn't with the raw inputs alone. A linear model fit to `x` can only draw a straight line; fit it to `(x, x², x³)` and it can draw a cubic.

This is "data prep" because it happens *before* fitting — same place as scaling and encoding.

## Polynomial features

A linear model with raw `x` is restricted to:

`y = β₀ + β₁·x + ε`

If the true relationship between `x` and `y` is curved, no choice of `β` will fit well. Adding powers of `x` as new columns gives the linear model the freedom to bend:

`y = β₀ + β₁·x + β₂·x² + β₃·x³ + ε`

The model is still **linear in the parameters β** — fittable with ordinary OLS — even though the relationship to `x` is nonlinear.

### How to do it in Python

**statsmodels (formula API):**

```python
import statsmodels.formula.api as smf
fit = smf.ols("y ~ x + np.square(x) + np.power(x, 3)", data=df).fit()
```

**sklearn `PolynomialFeatures`:**

```python
from sklearn.preprocessing import PolynomialFeatures
poly = PolynomialFeatures(degree=3, include_bias=False)
X_poly = poly.fit_transform(df[["x"]])
# columns: [x, x², x³]
```

**numpy `vander`** (Vandermonde matrix):

```python
import numpy as np
poly_features = np.vander(df["x"], N=4, increasing=True)
# columns: [1, x, x², x³]
```

### Picking the degree

Higher degree → more flexible → lower training error but rising test error past a sweet spot (the classic bias-variance U). Pick the degree that minimizes **cross-validated** MSE — see [data-science/17-cross-validation.md](../data-science/17-cross-validation.md).

⚠️ **Polynomial collinearity.** `x` and `x²` are highly correlated, especially when `x` is far from 0. This inflates the variance on each coefficient — see [05-multicollinearity.md](05-multicollinearity.md). Two ways to mitigate: (1) center `x` first (subtract its mean), or (2) use orthogonal polynomial bases.

## Interaction terms

Two predictors **interact** when the effect of one depends on the value of the other. The standard linear model assumes effects are additive and independent:

`y = β₀ + β₁·x₁ + β₂·x₂ + ε`

This forces a fixed slope on `x₁` regardless of `x₂`. If the true effect of `x₁` is amplified or dampened by `x₂`, you need an **interaction term** — the product of the two:

`y = β₀ + β₁·x₁ + β₂·x₂ + β₃·(x₁·x₂) + ε`

Now `β₃ ≠ 0` says "the slope on `x₁` changes by `β₃` per unit increase in `x₂`."

### Examples

- **Quantitative × quantitative:** ad spend on channel A × spend on channel B — one channel may amplify the other's effectiveness.
- **Quantitative × categorical:** continuous `income` × group dummy `is_member` — slopes differ between groups.
- **Categorical × categorical:** two group dummies multiplied — captures group-specific intercept shifts.

### How to do it in Python

**statsmodels formula** — the cleanest:

```python
smf.ols("y ~ x1 * x2", data=df).fit()
# x1 * x2 expands to: x1 + x2 + x1:x2  (main effects + interaction)

smf.ols("y ~ x1 + x2 + x1:x2", data=df).fit()  # explicit, equivalent
```

**Manual creation** for sklearn / numpy:

```python
df["x1_x2"] = df["x1"] * df["x2"]
```

`PolynomialFeatures(degree=2, interaction_only=True, include_bias=False)` generates all pairwise products without the squared terms.

### The hierarchy principle

If you include `x₁:x₂` in the model, **always include both `x₁` and `x₂` as main effects**, even if their individual t-statistics look insignificant.

**Why:**
- Interaction coefficients are uninterpretable without the main effects.
- Without the main effects, the interaction term will absorb their variance, producing misleading coefficients.

statsmodels' `x1 * x2` expands to include the main effects automatically — this is the principle baked into the syntax.

## Log transforms

When a numeric column is **right-skewed** (long tail of large values) or spans multiple orders of magnitude (income, population, prices), a log transform compresses the tail and often stabilizes variance.

```python
df["log_price"] = np.log(df["price"])

# Or in a formula:
smf.ols("y ~ np.log(price)", data=df).fit()
```

Use `np.log1p(x)` (= `log(1 + x)`) when the column can be 0 — `log(0) = −∞`.

### Interpretation changes

- Model on `log(y) ~ x`: a one-unit change in `x` corresponds to a **multiplicative** change in `y`, not additive.
- Model on `y ~ log(x)`: a 1% change in `x` corresponds to roughly `β₁ / 100` change in `y`.

## Ratios and composite features

Sometimes the *ratio* of two columns is more meaningful than either alone:

```python
df["conversion_rate"] = df["purchases"] / df["views"]
df["price_per_unit"]  = df["total_revenue"] / df["units_sold"]
```

This is domain knowledge encoded as a feature. Tree-based models can sometimes learn ratios on their own; linear and distance-based models almost never can.

⚠️ Watch for divide-by-zero. Replace zero denominators with `np.nan` and fill or drop.

## Feature engineering pitfalls

1. **Leakage from the target** — if you build a feature using the target column (or anything derived from it), the model will look perfect on training and fail on real data. Common offender: target-mean encoding for categoricals computed on the full dataset rather than per-fold.
2. **Polynomial blow-up** — `PolynomialFeatures(degree=4)` on 10 features generates 1001 columns. Combinatorial.
3. **Interaction blow-up** — same issue with `interaction_only=True`: pairwise interactions on 50 features = `C(50, 2) = 1225` columns.
4. **Collinearity** — every transformation that's a function of an existing feature is correlated with it. Plan to regularize ([06-feature-selection.md](06-feature-selection.md)) or drop later.
5. **Train/test consistency** — `PolynomialFeatures`, encoders, and scalers should be `.fit()` on training data only and `.transform()` applied to test. A `Pipeline` enforces this.

## Quick reference

| Want | Do |
|---|---|
| Capture nonlinear shape of one predictor | Polynomial: `x + x² + …` |
| Effect of one predictor depends on another | Interaction: `x₁ * x₂` |
| Compress a right-skewed or wide-range feature | `np.log(x)` or `np.log1p(x)` |
| Encode domain knowledge | Ratios, differences, sums |
| Avoid the dummy-variable trap | See [03-categorical-encoding.md](03-categorical-encoding.md) |
