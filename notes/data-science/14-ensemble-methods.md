# 14 — Ensemble Methods (Bagging, Random Forest, Boosting)

> **Source:** `Hwk-9-2Ensemble-Methods.txt` (Allen Tan, INFO 397F), `Hwk_9-2Ensemble_Methods.html`.

## The ensemble idea

A single decision tree is unstable and often overfit. **Combine many trees** to get a stronger, more stable model. Three main flavors:

1. **Bagging** (Bootstrap Aggregation)
2. **Random Forest**
3. **Boosting**

All build on `13-decision-trees.md`.

## Bagging (Bootstrap Aggregation)

1. Draw many **bootstrap samples** (random samples *with replacement*) from the training data.
2. Fit a **full / unpruned** tree on each.
3. Aggregate predictions: **majority vote** for classification, **average** for regression.

Bagging reduces **variance** — trees fit to different bootstrap samples make different errors that cancel out when averaged.

```r
library(randomForest)
# Bagging is just a Random Forest with mtry = number of predictors
bag <- randomForest(Sales ~ ., data=train.data, mtry=11, importance=TRUE)
preds <- predict(bag, newdata=test.data)
mean((preds - true.vals)^2)   # MSE
```

> **Modern alternative (verified 2026-05-03):** `randomForest` (v4.7-1.2, Sept 2024) is in maintenance — API unchanged, code above still runs. For production / large data prefer **`ranger`** (v0.18.0, Jan 2026): same idea, multithreaded by default, ~10–100× faster on wide data, and the engine `tidymodels` / `mlr3` use under the hood.

## Random Forest

Bagging, but at **each split**, only a random subset of `mtry` predictors is considered. This **decorrelates** the trees so the ensemble averages over more diverse fits.

- **Classification rule of thumb:** `mtry ≈ sqrt(p)` where p = total predictors.
- **Regression rule of thumb:** `mtry ≈ p/3`.

```r
rf <- randomForest(Sales ~ ., data=train.data, mtry=6, importance=TRUE)
preds <- predict(rf, newdata=test.data)
mean((preds - true.vals)^2)

# Importance scores per predictor:
importance(rf)
```

Random Forests give per-feature **importance** scores — the average decrease in impurity (or accuracy) when the feature is used in splits — which is great for understanding what's driving predictions.

## Boosting (gradient boosting)

Trees are built **sequentially**. Each new tree focuses on the errors (residuals) of the existing ensemble. Final prediction is a weighted sum of all trees.

```r
library(gbm)
boost <- gbm(Sales ~ ., data=train.data,
             distribution="gaussian",   # "bernoulli" for binary classification
             n.trees=5000,
             interaction.depth=4)
preds <- predict(boost, newdata=test.data, n.trees=5000)
mean((preds - true.vals)^2)

summary(boost)   # shows variable influence
```

Key knobs:
- **`n.trees`** — total trees. Too many → overfit; too few → underfit.
- **`interaction.depth`** — depth of each tree. 1 = stumps (additive); higher captures interactions.
- **`shrinkage`** (learning rate) — small values (~0.01) usually generalize better but need more trees.

> ⚠️ **`gbm` is officially in maintenance-only mode** (verified 2026-05-03 — v2.2.3, Jan 2026; the NEWS file states it's "only being maintained for backwards compatibility"). For new work use **`xgboost`** (v3.2.1.1, Mar 2026 — the dominant default in modern R, with `tidymodels`/`caret`/`mlr3` integration, GPU support, built-in regularization) or **`lightgbm`** (v4.6.0, Feb 2025 — strong on wide/sparse data). **Skip `gbm3`** — never reached CRAN, breaking changes from `gbm`, low activity.

## Why ensembles win

From the activity (predicting carseat sales, MSE on test):

| Model | MSE |
|---|---|
| Single tree (unpruned) | 2.71 |
| Single tree (pruned) | 2.60 |
| Bagged | 1.89 |
| Random Forest (mtry=6) | 1.73 |
| Boosted (5000 trees) | 1.52 |

Pattern: **single tree → bagging → random forest → boosting** is roughly increasing accuracy at increasing computational cost.

## When each shines

| Method | Best for | Notes |
|---|---|---|
| Bagging | High-variance base learners (deep trees) | All predictors considered at each split |
| Random Forest | Default workhorse — tabular data | Less tuning, robust |
| Boosting | When you can tune carefully | Often best accuracy, but tuning matters |

## Common gotchas

- **More trees in RF/bagging never hurts** (they just slow things down). **More trees in boosting can overfit** — tune `n.trees` via CV.
- Random Forests don't extrapolate well — predictions are bounded by the training range. Same applies to all tree ensembles for regression.
- **Importance scores can be biased** toward high-cardinality features in random forests — prefer permutation importance for sensitive analyses.
