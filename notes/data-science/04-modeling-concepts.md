# 04 — Modeling Concepts

> **Source:** `Notes-Modeling.pdf` (Gordon Anderson).
> **See also:** `Activity-LinearRegression.Rmd`, `Activity-KNN-2.Rmd`, `Activity-Naive Bayes.Rmd`.

## What is a model?

- A **generalization** that captures the fundamental trend in a set of observations.
- Useful for two reasons:
  1. **Inference / explanation** — describes interesting aspects of the trend.
  2. **Prediction** — produces predictions for new, unseen observations.
- **Parsimony principle:** prefer simpler models over complex ones when performance is similar.

## Data Analysis Vocabulary

- **Tuple:** a single row of related observations.
- **Dependent variable (a.k.a. predicted variable, response, outcome, label):** what you're trying to predict.
- **Independent variables (a.k.a. predictors, features, covariates, explanatory variables):** the inputs.

## Modeling Tasks

| Task | Predicts | Supervised? |
|------|----------|-------------|
| **Regression** | Continuous (numeric) value | Yes |
| **Classification** | Categorical value (label) | Yes |
| **Clustering** | "Natural" groupings — no labels | No |

## The general modeling process

1. Choose a type of model.
2. Fit the model to the data — the algorithm "learns" the parameters.
3. Evaluate the quality of the fit.
4. If unsatisfactory, return to step 2 (or 1).
5. Use the model — for prediction, or to explore variable interactions.

## Machine Learning categories

- **Supervised:** algorithm sees labeled examples `<predictors, true outcome>` and learns to map predictors → outcome.
- **Unsupervised:** algorithm sees only predictors (unlabeled), discovers structure (e.g., clusters).
- **Semi-supervised:** mixed — only some rows labeled.

## Train / Test splits

For supervised ML:
- **Training set:** model learns from this — `<features, true outcome>` pairs.
- **Test set:** held out — model is given features only, generates predictions, predictions are compared to true outcomes.
- This is how you measure how well the model **generalizes** to new data, not just how well it memorized training data.

A common ratio: 75% / 25% or 80% / 20% train/test. See `17-cross-validation.md` for K-fold CV — a more robust evaluation.

## Model formula structure (linear example)

`y_i = α + β·x_i + ε_i`
- α — intercept
- β — slope / coefficient
- ε — error or noise term

The fitting algorithm (e.g., OLS for linear regression) "learns" α and β by minimizing some loss function.

## Bias-Variance Tradeoff

The central tension in modeling:

| | Bias | Variance |
|---|------|----------|
| **High** | Model misses the trend (underfit) | Model memorizes noise (overfit) |
| **Low** | Captures trend well | Generalizes well |

- **High bias / low variance:** simple model; consistent but inaccurate (e.g., linear fit to nonlinear data).
- **Low bias / high variance:** complex model that perfectly fits training points but predicts new data poorly. **Overfitting.**

Goal: balance the two — fit training set well *and* generalize.

> ⚠️ **Modern caveat — "double descent" (verified 2026-05-03):** The classical U-shaped tradeoff curve still applies to the **underparameterized regime** covered in this course. For **high-capacity models** (deep neural nets, very wide ensembles, large kernel machines), test error can *decrease again* past the point where the model exactly **interpolates** the training data — Belkin et al. 2019, *PNAS*. This **doesn't invalidate** the classical tradeoff for the models in this course; it's a footnote for when you extend to deep learning.

## Errors vs. Residuals

- **Residual:** difference between fitted value and observation. Computed during fitting.
- **Error (true error):** difference between *true* (unknown) model and observation.
- "Errors in the fit" ≠ "errors in prediction" — track them separately when evaluating.

## Always ask…

- What biases and assumptions am I making?
- What are the threats to validity in my model and data?
- Could the result be an artifact of how the sample was drawn?
