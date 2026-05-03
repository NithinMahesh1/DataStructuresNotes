# 13 — Decision Trees

> **Source:** `Hwk-9-1DecisionTrees.txt` (Allen Tan, INFO 397F), `Hwk_9-1DecisionTrees.html`.

## What they do

A decision tree recursively splits the feature space into regions, each associated with a predicted outcome.
- **Classification tree:** predicts a class label.
- **Regression tree:** predicts a numeric value.

You can read a tree as a sequence of **if-then rules** from the root to a leaf:
> "If `LoyalCH < 0.5036` AND `LoyalCH < 0.0356` → predict MM"

## Algorithm sketch

1. Start with all data at the root.
2. Find the predictor + split point that best separates the data (e.g., minimum classification error, lowest deviance, lowest Gini, highest information gain).
3. Split the data into two child nodes.
4. Recurse on each child.
5. Stop when nodes are pure, too small, or some stopping criterion is met.

Result: a binary tree where:
- **Internal nodes** are split criteria like `LoyalCH < 0.5036`.
- **Leaves (terminal nodes)** are the predictions.

## Fitting in R (`tree` package)

```r
library(tree)

# Classification tree (label is a factor)
fit <- tree(Purchase ~ ., data=train.data)
summary(fit)
# → number of terminal nodes, predictors used, training misclassification rate, residual mean deviance

# Plot it
plot(fit)
text(fit, pretty=0)

# Print text representation
fit

# Predict
preds <- predict(fit, newdata=test.data, type="class")
```

> **Note (verified 2026-05-03):** `tree` (Ripley, v1.0-45, Aug 2025) is still maintained but follows the older Clark & Pregibon (1992) formulation. **`rpart`** (v4.1.27, Mar 2026) is the de facto standard for new work — it implements Breiman's CART with cost-complexity pruning out of the box (`printcp()` / `plotcp()`), surrogate splits for missing data, and pairs with **`rpart.plot`** for far better visualizations. Use `rpart` for new analyses; keep `tree` only if you specifically want the Clark/Pregibon formulation.

## Evaluation

```r
table(preds, true.labels)                                 # confusion matrix
1 - sum(diag(theTable)) / sum(theTable)                   # misclassification rate
```

For regression trees, evaluate with **MSE**:
```r
preds <- predict(fit, newdata=test.data)
mean((preds - true.values)^2)
```

## Pruning — to avoid overfitting

Unpruned trees are typically too large and overfit. Pruning removes branches to improve generalization, even if it slightly increases training error.

```r
# Find a good tree size by cross-validation
cv.fit <- cv.tree(fit, FUN=prune.misclass)
plot(cv.fit$size, cv.fit$dev, type="b")     # pick size at the elbow / min

# Prune to that best size
pruned <- prune.misclass(fit, best=4)       # for classification
pruned <- prune.tree(fit, best=5)           # for regression
plot(pruned); text(pruned, pretty=0)
```

A pruned tree often has slightly worse training error but **better test performance**. From the activity:
- Unpruned classification tree: 14% misclassification
- Pruned (size 4) tree: 19% misclassification
- *But* the pruned tree uses many fewer predictors and is more interpretable — a fair trade-off.

## Reading the tree

Each terminal node has:
- A **prediction** (e.g., "CH").
- A **count** of training observations that fell into it.
- **Class proportions** — e.g., "8 of 17 are CH (47%)" — at a glance, you see how confident the rule is.

## Strengths

- **Interpretable** — you can literally trace the decision logic.
- Handles numeric and categorical predictors with no preprocessing.
- No need to scale features.
- Captures nonlinear relationships and interactions naturally.

## Weaknesses

- **Unstable** — small data changes can produce very different trees.
- **Tend to overfit** without pruning.
- Single trees often have only modest predictive accuracy compared to ensembles (see `14-ensemble-methods.md`).
- Greedy splitting can miss globally better splits.
