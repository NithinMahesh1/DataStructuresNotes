# 12 — Naive Bayes Classifier

> **Source:** `Notes/NaiveBayesClassifier (1).pdf`, `Activity-Naive Bayes.Rmd`, `Hwk 6-1NaiveBayes.Rmd`.
> **Prereq:** `02-probability-and-bayes.md`.
> **Spam dataset (Ling-Spam):** Original csmining.org link is dead; current sources are the AUEB tarball at http://www2.aueb.gr/users/ion/data/lingspam_public.tar.gz (Androutsopoulos, original author) or the Kaggle mirror https://www.kaggle.com/datasets/mandygu/lingspam-dataset.

## What it does

A **supervised classification** algorithm based on Bayes' rule with a strong (and intentionally **naive**) assumption: that all the predictor features are **conditionally independent given the class label**.

Despite the unrealistic assumption, it works surprisingly well for many problems — especially **text classification (spam detection)**.

## The math (spam example)

Bayes' rule for "is this email spam, given its words?":

`P(spam | W) = [ P(W | spam) · P(spam) ] / P(W)`
`P(¬spam | W) = [ P(W | ¬spam) · P(¬spam) ] / P(W)`

Where `W = (w₁, w₂, ..., wₙ)` is a vector representing the words present in the email.

The denominator `P(W)` is the same in both equations, so when comparing classes for the same email, we can drop it:

`P(spam | W) ∝ P(W | spam) · P(spam)`
`P(¬spam | W) ∝ P(W | ¬spam) · P(¬spam)`

We pick whichever side is larger.

## The naive assumption

Computing `P(W | spam)` for an arbitrary combination of words is intractable. But if we *assume* the words are independent given the class:

`P(W | spam) = ∏ⱼ θⱼₛ^xⱼ · (1 − θⱼₛ)^(1−xⱼ)`

Where:
- `θⱼₛ = P(wⱼ | spam)` is the (estimated) probability that word j appears in a spam email.
- `xⱼ ∈ {0, 1}` indicates whether word j is in the email.
- This is a product of n Bernoulli trials.

Each `θ` is estimated from training data — basically just counting frequencies.

## Why "naive"?

Words in a real document are obviously **not** independent ("New" and "York" co-occur way more than chance). But the assumption is a useful simplification, and the resulting classifier works well anyway because we're picking the class with the highest score, not estimating probabilities precisely.

## Laplace smoothing

**Problem:** if a word appears in the test set but never in the training set for a class, its `θ = 0`, which makes the entire product 0. One missing word can override every other word.

**Fix:** **Laplace (additive) smoothing** — add a "pseudocount" k to the numerator (and proportional adjustment to the denominator), pretending you saw each word k extra times.

- `k = 0` → no smoothing (the `e1071::naiveBayes` package default)
- `k = 1` → "add-one" smoothing (textbook convention)
- `k > 1` → stronger smoothing — useful when features are very sparse (long-tail text vocabulary), but flattens class-conditional probabilities

```r
naiveBayes(Party ~ ., data=train.data, laplace=1)
```

> **Note (verified 2026-05-03):** The course activity uses `laplace=3`, which is **not a statistical convention** — it's a course-specific pseudocount choice. Standard practice is `laplace=1`, or tune the value via cross-validation. The `e1071` default is `laplace=0` (no smoothing).

## R example

> `e1071::naiveBayes` (verified 2026-05-03 — v1.7-17, Dec 2025) is still actively maintained with a stable API. For richer distribution choices (Bernoulli / Poisson / Multinomial / Gaussian / kernel), better sparse-matrix support, and explicit missing-data handling, the `naivebayes` package (Majka, v1.0.0) is the modern alternative.

```r
library(e1071)

# Fit
model <- naiveBayes(Party ~ ., data=train.data)
# (or with smoothing: naiveBayes(Party ~ ., data=train.data, laplace=1))

# Posterior probabilities for the first 20 test rows
predict(model, test.data[1:20, -1], type="raw")

# Predicted labels
pred.labels <- predict(model, test.data[, -1])

# Confusion matrix and misclassification rate
conf.matrix <- table(pred.labels, test.data[, 1])
misc.rate   <- sum(pred.labels != test.data[, 1]) / length(pred.labels)
```

`type="raw"` returns posterior probabilities; default returns the predicted class.

## Handling missing data

In the congressional-votes example, abstentions (NAs) are removed by default. But this loses information — abstaining itself is a behavior. The activity treats `NA` as a third level "ab" and re-runs the model, which improved accuracy. Lesson: **don't drop NAs reflexively** — sometimes "missing" is a meaningful signal.

## Strengths and weaknesses

**Strengths**
- Very fast to train and predict — just count frequencies.
- Works well with high-dimensional sparse data (text classification).
- Robust to irrelevant features.
- Performs surprisingly well even when independence assumption is violated.

**Weaknesses**
- Independence assumption rarely holds.
- Probabilities are usually poorly calibrated (good for ranking, bad as raw probabilities).
- Continuous features need a distributional assumption (often Gaussian) — `e1071::naiveBayes` handles this.
- Zero-frequency problem (mitigated by Laplace smoothing).
