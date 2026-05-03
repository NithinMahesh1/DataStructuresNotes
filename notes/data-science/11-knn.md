# 11 — K-Nearest Neighbors (KNN)

> **Source:** `Notes KNN.pdf`, `Activity-KNN-2.Rmd`, `Hwk4-1KNNwdbc.html`.

## What it does

A **supervised classification** algorithm. Given features and labels for training data, KNN classifies a new point by finding the **k closest** training points and assigning the most common label among them.

It can also be used for regression (average the k nearest neighbors' values) but is most often used for classification.

## Algorithm

1. Pick a **k** (number of neighbors) and a **distance metric** (Euclidean, Manhattan, etc.).
2. For a new point:
   a. Compute its distance to every training point.
   b. Find the k smallest distances.
   c. Assign the **majority label** among those k neighbors.

That's it — there's no separate "fit" step that estimates parameters. KNN is **lazy / instance-based learning**.

## When to use it

- Classification or recommendation problems where similarity is meaningful.
- Movie recommendations: classify movies into genres by descriptor similarity, recommend by neighbor similarity.
- User-based recommenders: find users with similar preferences and recommend what they liked.

## Distance metrics

- **Numeric data:** Euclidean (most common), Manhattan, etc. (See `16-distance-metrics.md`.)
- **Categorical data:** Hamming distance (count of positions where strings differ).
  - e.g., `hamming("freddy", "teddie") = 5`.
- **Mixed data:** Gower's coefficient.

⚠️ **Always scale numeric predictors** before KNN. Otherwise a feature with large units (income in dollars) dominates the distance calculation vs. a feature with small units (age in years).

## Choosing k

- **Small k (e.g., k=1):** very flexible, low bias, high variance — sensitive to noise / outliers (overfit).
- **Large k:** smoother decision boundary, higher bias, lower variance.
- Sweet spot is data-dependent; common practice is to try a range of k values and pick by cross-validation.

If the training set is dominated by one class, weight neighbors by inverse distance so closer neighbors count more.

## Tie-breaking

When two labels tie (e.g., k=2 with one of each class):
- Random pick, or
- Use distance weighting, or
- Choose odd k for binary problems to avoid ties.

## R example

> `class::knn` (verified 2026-05-03 — v7.3-23, Jan 2025) still ships with R and the API is unchanged. For cross-validation, hyperparameter tuning, or pipeline integration, prefer `tidymodels` + `kknn` or `caret::train(method="knn")` — same algorithm, much better workflow ergonomics.

```r
library(class)
set.seed(123456)

# Create train/test sets
train.rows <- sample(1:nrow(df), 0.75 * nrow(df), replace=FALSE)
test.rows  <- setdiff(1:nrow(df), train.rows)

train.data <- df[train.rows, c("Age", "Income")]
test.data  <- df[test.rows,  c("Age", "Income")]
class.labels <- df$CreditLabel[train.rows]
true.labels  <- df$CreditLabel[test.rows]

# Run KNN
pred.labels <- knn(train.data, test.data, class.labels, k=3)

# Evaluate
conf.mat <- table(pred.labels, true.labels)   # confusion matrix
misc.rate <- sum(pred.labels != true.labels) / length(true.labels)
```

## Sweeping over k

Common loop to find a good k:

```r
for (k in 1:10) {
  pred <- knn(train.data, test.data, class.labels, k)
  err  <- sum(pred != true.labels) / length(true.labels)
  cat(k, err, "\n")
}
```

For more rigor use cross-validation (`17-cross-validation.md`) instead of a single split.

## Evaluation

- **Confusion matrix** (`table(pred, true)`): rows = predicted, cols = true (or vice versa). Diagonal = correct.
- **Misclassification rate** = `1 − accuracy = (incorrect labels) / (total labels)`.
- **Accuracy** = correct / total.

## Strengths and weaknesses

**Strengths**
- Conceptually simple, no model to fit.
- Naturally handles multiclass.
- Works well when decision boundary is irregular.

**Weaknesses**
- Slow at prediction time on large training sets (must compute distance to every point).
- Suffers from the **curse of dimensionality** — distances become less meaningful in high dimensions.
- Sensitive to feature scale (must scale).
- Sensitive to irrelevant features (they distort the distance).
