# 05 — Multicollinearity

> **Source:** `midterm notes/multicollinearity.md` (ESE-5410), with the linear-algebra background condensed.
> **See also:** [data-science/09-linear-regression.md](../data-science/09-linear-regression.md) for the regression mechanics, [06-feature-selection.md](06-feature-selection.md) for shrinkage methods that mitigate it.

**Multicollinearity** = high pairwise correlation (or near-linear dependence) between two or more predictors. It doesn't break the model — coefficient estimates are still unbiased — but it **inflates their variance**, making them unstable and hard to interpret.

## What's actually happening

The OLS coefficient covariance matrix is:

`Var(β̂) = σ² · (XᵀX)⁻¹`

When two columns of `X` are nearly linearly dependent, `XᵀX` becomes **near-singular** — its inverse has large diagonal entries, and the variances of `β̂ⱼ` blow up.

In plain English: when two predictors carry almost the same information, the model can't tell which one "deserves" the coefficient. Small changes in the data shift the credit between them, so coefficient estimates wiggle from sample to sample.

## Symptoms

You're probably looking at multicollinearity if:

1. **Coefficient signs flip-flop** when you add or remove other predictors.
2. **Coefficients are large with huge standard errors** — t-stat near zero, p-value near 1, despite the predictor "obviously mattering."
3. **Wide confidence intervals** that often span 0.
4. **Each predictor is highly significant alone but jointly insignificant** — a classic giveaway.
5. **Correlation matrix** has off-diagonal entries `|r| > 0.7` between predictors.

### The classic example

| Model | `β̂₁` | p-value (`x₁`) | `β̂₂` | p-value (`x₂`) |
|---|---|---|---|---|
| `y ~ x₁ + x₂` | 0.7 | **0.28** | 2.5 | 0.03 |
| `y ~ x₁`      | 1.9 | **0.0001** | — | — |
| `y ~ x₂`      | — | — | 3.6 | 0.0002 |

`x₁` and `x₂` are correlated at `r ≈ 0.85`. Each is highly significant alone, but together neither looks reliable. **The variables aren't broken — the model can't separate their effects because they carry nearly the same information.**

## How to detect

### Pairwise correlation

Quick first pass:

```python
df.corr(numeric_only=True).abs() > 0.7
```

Inspect the resulting boolean matrix for any predictor pairs above your threshold. `0.7–0.8` is the common rule.

⚠️ Pairwise correlation only catches **two-variable** collinearity. Three or more variables can be jointly nearly dependent (`x₃ ≈ x₁ + x₂`) without any single pair having high correlation.

### Variance Inflation Factor (VIF)

`VIFⱼ = 1 / (1 − Rⱼ²)`, where `Rⱼ²` is from regressing `xⱼ` on every *other* predictor.

| VIF | Reading |
|---|---|
| 1 | Uncorrelated with the other predictors |
| 1 – 5 | Mild correlation, usually fine |
| 5 – 10 | Moderate concern |
| > 10 | Severe; the coefficient on `xⱼ` is suspect |

```python
from statsmodels.stats.outliers_influence import variance_inflation_factor
import statsmodels.api as sm
import pandas as pd

X = sm.add_constant(df[predictor_cols])
vif = pd.DataFrame({
    "feature": X.columns,
    "VIF":     [variance_inflation_factor(X.values, i) for i in range(X.shape[1])],
})
```

VIF catches multi-way collinearity that pairwise correlation misses.

### Condition number

`np.linalg.cond(XᵀX)` — a single number summarizing how close `XᵀX` is to singular. By Belsley's rule of thumb, condition number ≥ 30 indicates collinearity somewhere in the design.

## How to handle

**Drop one predictor.** If two columns are essentially the same information (e.g., `height_cm` and `height_inches`, or two near-duplicate survey items), keeping both adds nothing. Pick the one easier to interpret or with fewer missing values.

**Combine them.** If they jointly measure the same underlying construct, build a composite (sum, average, or PCA component) and use that.

**Regularize.** Ridge regression in particular shrinks correlated predictors toward each other instead of letting their coefficients go wild. Lasso will pick one and zero the other (less ideal if you want both effects represented). See [06-feature-selection.md](06-feature-selection.md).

**Reduce dimensions.** PCA finds uncorrelated linear combinations of the original predictors. Components have zero pairwise correlation by construction. See [07-pca-and-bootstrap.md](07-pca-and-bootstrap.md).

**Increase the sample size.** Multicollinearity is most dangerous in small samples; with enough rows the coefficient variance comes back down.

## What it does *not* affect

- **Predictions.** Multicollinearity inflates the variance of *individual coefficients*, but the model's predictions in the original variable space can still be accurate. If you only care about predicting `y`, multicollinearity is much less of a problem than it is for inference.
- **Bias.** Coefficients are still unbiased on average; it's the variance that's inflated.
- **R² / overall F-test.** These test the model as a whole, not individual coefficients.

## Quick reference

| Goal | Tool |
|---|---|
| Quick screen | `df.corr().abs() > 0.7` |
| Multi-way detection | VIF (`variance_inflation_factor`) |
| Single-number summary | Condition number of `XᵀX` |
| Mitigation: pick one | Drop the redundant predictor |
| Mitigation: keep both | Ridge regression |
| Mitigation: many correlated predictors | PCA preprocessing |
