# 17 — Cross-Validation

> **Source:** `Notes/Notes-CrossValidation (1).pdf` (Gordon Anderson).
> **Used in:** `Activity-Naive Bayes.Rmd`, `Hwk 6-1NaiveBayes.Rmd` (10-fold CV examples).

## Why cross-validate?

A single train/test split tells you how the model performed on **one** random partition of the data. That's noisy:
- Got "lucky" with an easy test set? You'll overestimate performance.
- Got "unlucky"? You'll underestimate.

**Cross-validation** runs multiple train/test splits in a structured way and averages the results to give a **stable, less-biased estimate** of how well the model generalizes.

## Train / Test recap

In supervised ML you always split your data:
- **Training set** — model learns the relationship between predictors and outcome.
- **Test set** — held out; used only to check how well the model predicts on **unseen** data.

Single-split rule of thumb: 70–80% train, 20–30% test.

But a single split is just one trial — CV repeats this in a structured way.

## LOOCV — Leave-One-Out Cross-Validation

For each row in the dataset:
1. Hold that one row out as the test set.
2. Train on all remaining rows.
3. Predict that one row, record the error.

Then average the errors across all N rows.

- For N=1,250 rows: 1,250 model fits.
- Maximizes use of the data — every row is in training N-1 times.
- **Computationally expensive** for large N.
- Tends to have low bias but **high variance** in the estimate (since training sets are nearly identical).

## K-Fold Cross-Validation

A compromise between a single split and LOOCV.

1. Pick `K` folds (commonly K=5 or K=10).
2. Partition the data into K roughly equal subsets.
3. For each fold `i` from 1 to K:
   - Test set = fold `i`
   - Train set = all other folds
   - Fit the model on train, predict on test, record performance metric
4. You end up with K performance numbers — analyze their mean, SD, distribution.

```
[ Fold 1 | Fold 2 | Fold 3 | Fold 4 | Fold 5 ]
   Test     Train     Train    Train    Train
   Train    Test      Train    Train    Train
   Train    Train     Test     Train    Train
   ... and so on
```

If `K = 1`, this is essentially LOOCV (one row per fold).

## K-Fold algorithm (course style, in R)

```r
data.set       <- df
data.size      <- nrow(data.set)
data.cols      <- ncol(data.set)
num.folds      <- 10

# Generate fold IDs and attach as a column
data.set$fold  <- floor(runif(data.size) * num.folds) + 1
data.set$fold  <- factor(data.set$fold)

misclassification.rates <- c()

for (i in 1:num.folds) {
  train <- data.set[data.set$fold != i, 1:data.cols]
  test  <- data.set[data.set$fold == i, 1:data.cols]

  model       <- naiveBayes(Outcome ~ ., data=train)
  pred.labels <- predict(model, test[, -1])
  true.labels <- test[, 1]

  rate <- sum(pred.labels != true.labels) / length(pred.labels)
  misclassification.rates <- c(misclassification.rates, rate)
}

summary(misclassification.rates)   # mean, median, etc.
sd(misclassification.rates)        # variability across folds
hist(misclassification.rates)
```

## Choosing K

- **K = 5 or 10** is the standard. Generally good bias-variance balance.
- **Larger K** (closer to LOOCV) — lower bias, higher variance, slower.
- **Smaller K** (e.g., 3) — higher bias, lower variance, faster.

## What CV is for

| Use | How |
|---|---|
| **Estimate generalization performance** | Average across folds |
| **Compare models / hyperparameters** | Pick the model with the best CV mean |
| **Assess stability** | High SD across folds = model is unstable / data is heterogeneous |

## Common gotchas

- **Don't peek at the test set during model selection.** If you tune hyperparameters using CV folds, use a separate **final test set** for unbiased final evaluation. Better: nested CV.
- **Stratify** the folds for classification with imbalanced classes — otherwise some folds may have very few of the minority class.
- **Time-series data needs special CV** — use forward-chaining / time-series split, not random K-fold (otherwise you train on the future to predict the past).
- **Group-aware CV** when rows aren't independent — keep all rows from the same person/group/account in the same fold.
- The course's manual fold-generation (`floor(runif(N)*K)+1`) doesn't guarantee equal-size folds. Modern practice: use `caret::createFolds()` or the built-in CV machinery in `tidymodels`.
