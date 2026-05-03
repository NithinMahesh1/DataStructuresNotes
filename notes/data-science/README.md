# Data Science Notes — Index

Concept-only summaries adapted from **INFO 397F: Introduction to Data Science** (Gordon Anderson). Each topic links back to the original source document(s) so you can drill in for code, examples, or follow-up questions.

> **Source:** `/home/nmahesh/Documents/MyGit/DataStructuresNotes/DataScience/Info 397F/`
> **Era:** ~2015–2019. Some package APIs and statistics may be dated — see `_AUDIT_CHECKLIST.md`.

---

## Foundations

- [01 — Introduction to Data Science](01-intro-to-data-science.md) — what DS is, data vs. information, structured/semi/unstructured data, types, lifecycle
- [02 — Probability and Bayes Rule](02-probability-and-bayes.md) — sample spaces, independent/dependent events, joint/conditional probability, Bayes
- [03 — Statistical Inference](03-statistical-inference.md) — populations vs samples, descriptive stats, quartiles, variance, SD, normal distribution
- [04 — Modeling Concepts](04-modeling-concepts.md) — what a model is, fit/predict, supervised vs unsupervised, train/test, bias-variance, overfitting

## R Basics

- [05 — R: Basic Stats and Plots](05-r-basic-stats-and-plots.md) — R data types, factors, `summary`, `tapply`, `subset`, `barplot`
- [06 — R Formula Notation](06-r-formula-notation.md) — `y ~ x` syntax, `+`, `:`, `*`, `.`, `I()`, intercept removal
- [07 — Relational Databases](07-relational-databases.md) — tables, relations, SQL, RSQLite, integrity

## Statistical Tests

- [08 — Contingency Tables and Chi-Square](08-contingency-tables-chi-square.md) — observed vs expected frequencies, chi-square test, dependence/independence

## Regression

- [09 — Linear Regression](09-linear-regression.md) — simple/multiple, OLS, coefficients, residuals, R², assumptions, interactions
- [10 — Logistic Regression](10-logistic-regression.md) — binary classification, log-odds/sigmoid, interpreting coefficients

## Classification

- [11 — K-Nearest Neighbors (KNN)](11-knn.md) — algorithm, choosing k, distance metrics, training/test split, confusion matrix
- [12 — Naive Bayes](12-naive-bayes.md) — conditional independence, posterior probabilities, Laplace smoothing, spam example
- [13 — Decision Trees](13-decision-trees.md) — recursive splits, leaf nodes, pruning with cv.tree, classification vs regression trees
- [14 — Ensemble Methods](14-ensemble-methods.md) — bagging, random forest, boosting (gbm), why ensembles outperform single trees

## Clustering (Unsupervised)

- [15 — Clustering](15-clustering.md) — k-means, PAM/medoids, model-based (mclust), choosing k, mixed-data with Gower

## Cross-Cutting Topics

- [16 — Distance Metrics](16-distance-metrics.md) — distance properties, Euclidean, Manhattan/city-block, Mahalanobis, Hamming, Gower
- [17 — Cross-Validation](17-cross-validation.md) — train/test split, LOOCV, K-fold CV
- [18 — Time Series](18-time-series.md) — components (trend/cycle/seasonal/noise), smoothing, stationarity, ARIMA, Holt-Winters

---

## Audit

- **[`_AUDIT_CHECKLIST.md`](_AUDIT_CHECKLIST.md)** — per-item details with sources, dates, and outcomes for all 29 audit items (✅ all checked off as of 2026-05-03).
- **[`_AUDIT_SUMMARY.md`](_AUDIT_SUMMARY.md)** — high-level recap of findings by bucket and list of files modified during the audit.

## Skipped from source

- `Doing Data Science.pdf` — the textbook, intentionally not summarized.
- `Hwk*.html` files — rendered homework outputs, duplicate the `.Rmd` content already covered.

## Running the source `.Rmd` code on modern R (R 4.x)

If you want to re-run the original course `.Rmd` files on a current R install, the most likely friction is **R 4.0.0's `stringsAsFactors = FALSE` default change**. Old code that assumed `data.frame()` / `read.csv()` auto-converts strings to factors will break for `tree`, `randomForest`, `e1071::naiveBayes` (which expect factor response variables).

**Workaround:** explicitly call `as.factor()` on categorical columns, or set `stringsAsFactors = TRUE` in the read call:
```r
df <- read.csv("file.csv", stringsAsFactors = TRUE)
df$Class <- as.factor(df$Class)
```

For reproducing pre-R 3.6.0 sample-output (since the default RNG changed in 2019):
```r
RNGversion("3.5.0"); set.seed(1234)
# ... legacy code ...
RNGversion(getRversion())
```
