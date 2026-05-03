# 10 — Logistic Regression

> **Source:** `Hwk_7-1LogisticRegression.html` (rendered output, Gordon Anderson).
> **See also:** `Notes-Modeling.pdf` for the modeling-tasks framing.

## When to use it

When the outcome is **categorical (typically binary)** rather than continuous. Examples:
- Email is spam (yes/no)
- Customer churns (yes/no)
- Loan defaults (yes/no)

Linear regression is wrong for binary outcomes — it'll predict values outside [0, 1], and a "linear effect on a 0/1 outcome" doesn't make probabilistic sense.

## What it does

Models the **log-odds** of the outcome being class 1 as a linear function of the predictors:

`logit(p) = log( p / (1 − p) ) = β₀ + β₁·X₁ + … + βₙ·Xₙ`

Equivalently, the predicted **probability** is the **sigmoid (logistic)** of the linear predictor:

`p = 1 / (1 + e^−(β₀ + β₁X₁ + … + βₙXₙ))`

Output is squashed into (0, 1) — a valid probability.

## Coefficient interpretation

- `β_i` = the change in **log-odds** of the positive class per unit change in `X_i` (holding others constant).
- `exp(β_i)` = the **odds ratio** — multiplicative effect on the odds. e.g., `exp(β_i) = 1.5` means a one-unit increase in X_i multiplies the odds by 1.5.

## Fitting in R

Logistic regression is a Generalized Linear Model with a **binomial** family and **logit** link:

```r
fit <- glm(y ~ x1 + x2, data=df, family=binomial(link="logit"))
summary(fit)

# Predict probabilities for new data:
probs <- predict(fit, newdata=test, type="response")
# Convert to class labels at a 0.5 threshold:
labels <- ifelse(probs > 0.5, 1, 0)
```

`summary(fit)` reports coefficients, std errors, z statistics, p-values, AIC, residual deviance.

## Evaluating

- **Confusion matrix:** `table(predicted, true)`
- **Misclassification rate:** `1 − (sum of diagonal) / total`
- **Accuracy / precision / recall / F1** — derived from the confusion matrix
- **ROC curve / AUC** — measures discrimination across all thresholds (not in source; standard modern practice)

## How it differs from linear regression

| | Linear regression | Logistic regression |
|---|---|---|
| Outcome | Continuous (numeric) | Categorical (binary) |
| Output range | (−∞, ∞) | (0, 1) — a probability |
| Link function | Identity (none) | Logit |
| Estimation | OLS | Maximum likelihood |
| R function | `lm()` | `glm(family=binomial)` |

## Multiclass extension

For more than 2 classes, use **multinomial logistic regression** (`nnet::multinom()` in R) or one-vs-rest variants. The course primarily covers the binary case.

## Common gotchas

- Threshold = 0.5 by default for converting probability → label; this is **not always optimal** — choose based on misclassification cost (e.g., false positive vs false negative).
- Logistic regression assumes a **linear** relationship between predictors and *log-odds*. Nonlinearities need transformations or interaction terms.
- Highly correlated predictors → unstable coefficients (multicollinearity), same as in linear regression.
