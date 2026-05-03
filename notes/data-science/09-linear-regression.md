# 09 — Linear Regression

> **Source:** `Notes-LinearRegression.pdf`, `Activity-LinearRegression.Rmd.txt`, `Hwk3-1LinearRegression.html`/`Hwk3-2LinearRegression.html`.

## What it does

Models a relationship between one or more **independent variables (covariates / predictors)** and a **continuous dependent variable (response / outcome)**. Summarizes how the average response varies across subpopulations defined by linear functions of the covariates.

## Simple linear regression (one predictor)

`Y = β₀ + β₁·X + ε`

- `Y` — response (real-valued)
- `X` — covariate (real or "categorized")
- `β₀` — intercept (predicted Y when X = 0)
- `β₁` — slope (predicted change in Y per unit change in X)
- `ε` — error term

Coefficients are estimated by **OLS (Ordinary Least Squares)** — minimizing the sum of squared residuals.

## Multiple linear regression

`Y = β₀ + β₁·X₁ + β₂·X₂ + … + βₙ·Xₙ + ε`

Each `βᵢ` is the predicted change in Y per unit change in `Xᵢ` *holding all other predictors constant*.

## Major assumptions

1. The relationship between covariates and response is **linear**.
2. All covariates have the same variance (**homoscedasticity**).
3. The covariates do not interact (unless modeled explicitly).

> These are routinely violated in practice — and we still use linear regression because it remains useful and interpretable.

## Fitting in R

```r
fit <- lm(Y ~ X, data=df)            # simple
fit <- lm(Y ~ X1 + X2, data=df)      # multiple
fit <- lm(Y ~ X1 * X2, data=df)      # with interaction (X1 + X2 + X1:X2)
summary(fit)
```

`summary(fit)` reports: intercept, coefficient(s), standard errors, t statistics, p-values, residual SE, R-squared, F-statistic, model p-value.

## Predicting new values

```r
new <- data.frame(X = 80)
predict(fit, new, interval="predict")     # for new observation (wider)
predict(fit, new, interval="confidence")  # for the mean response
```

The **prediction interval** (for a random new observation) is wider than the **confidence interval** (for the model parameter / mean response).

## Evaluating fit

### Residuals
- **Residual** = observed Y − predicted Y.
- "Residual standard error" in `summary(fit)` is the SD of residuals — average distance each observation falls from the predicted value (smaller = better).
- A **residual plot** (residuals vs. predictor) should show even dispersal above/below 0; patterns indicate nonlinearity or heteroscedasticity.

### R-squared
- Fraction of variance in Y "explained" by the model.
- R² × 100 = % of variance explained.
- **Not an absolute "goodness" measure** — depends on the data and the question.
- **Low R² is not always bad**: human-behavior fields (psychology, etc.) often see R² < 0.5. Significant predictors can still be meaningful even with low R².

> ⚠️ **Modern caveats (verified 2026-05-03):** Plain R² is now treated as **descriptive only**, not a model-selection or goodness-of-fit metric. It **always increases when you add predictors, even useless ones.**
> - Use **adjusted R²** when comparing models with different numbers of predictors.
> - Use **AIC / BIC** or **cross-validated RMSE** for model selection.
> - R² alone says nothing about whether the model is correctly specified or predictive on new data.

### Coefficient significance (t and p)
- `coefficient_estimate / std_error = t-statistic`.
- The p-value of t tests whether the coefficient is reliably different from 0.
- Significance of the coefficient ≠ size of its effect on the outcome.

### Overall model significance (F-test)
- `F = explained variance / unexplained variance`.
- **H₀:** all coefficients = 0 (model has no explanatory power).
- A small F-test p-value rejects H₀ — at least one predictor matters.

### Degrees of freedom
- `df = N − c`, where `N` = data size, `c` = number of estimated coefficients.
- If `c = N`, the model fits perfectly with zero error but generalizes nothing — useless for prediction.

## Interaction terms

When two predictors interact (their effects depend on each other):

```r
fit <- lm(kid_score ~ mom_hs + mom_iq + mom_hs:mom_iq, data=df.child)
# equivalent shorter form:
fit <- lm(kid_score ~ mom_hs * mom_iq, data=df.child)
```

Adding `mom_hs:mom_iq` lets the slope of `mom_iq` differ for moms with vs. without HS.

Without the interaction, the model forces both subgroups to share a slope (only the intercept differs by group) — which mis-fits if the true effect varies.

## Quick gotchas

- Always plot the data first — linear regression on obviously nonlinear data gives misleading coefficients.
- "Two clusters" in a scatter (e.g., the `faithful` dataset) is a hint that linear regression alone may miss structure — look at clustering.
- Categorical predictors in R are auto-encoded as dummy variables when they're factors; otherwise R treats them as numeric (silent bug).
