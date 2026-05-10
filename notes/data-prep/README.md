# Data Prep Notes — Index

Concept-only summaries focused on the **data preparation** stage of a Python ML workflow — what to do *before* you fit a model. Adapted from **ESE-5410** (a graduate ML / statistical-learning course) source notes.

> **Source:** `/Users/nmahesh/Documents/MyGit/ESE-5410/` — see the citation at the top of each file for the specific module(s) it draws from.

These notes are a Python-flavored complement to the **R-flavored** [data-science/](../data-science/) notes (Info 397F). When the same concept lives in both, the data-prep file links across with a "See also" line rather than restating it.

---

## Topics

### Exploratory data analysis
- [01 — Pandas EDA Cookbook](01-pandas-eda-cookbook.md) — loading, slicing, summary stats, missing values, masks, groupby, correlation, plots, common pitfalls

### Feature transformation
- [02 — Feature Engineering](02-feature-engineering.md) — polynomial terms, interactions, log/ratio transforms, the hierarchy principle
- [03 — Categorical Encoding](03-categorical-encoding.md) — dummy variables, K−1 rule, choosing a baseline, pandas / statsmodels / sklearn tooling
- [04 — Scaling and Standardization](04-scaling-and-standardization.md) — z-score vs min-max, when (and where in the pipeline) to apply

### Diagnostics
- [05 — Multicollinearity](05-multicollinearity.md) — what it is, how to detect it, why coefficients flip-flop with correlated predictors

### Reducing the input space
- [06 — Feature Selection](06-feature-selection.md) — best subset, forward / backward stepwise, Ridge vs Lasso, choosing λ
- [07 — PCA and Bootstrap](07-pca-and-bootstrap.md) — PCA as a preprocessing step, PVE / scree, bootstrap resampling

---

## What's *not* here (and where to find it)

- **Modeling itself** (regression, classification, trees, SVM, clustering) — see [`../data-science/`](../data-science/) and the original ESE-5410 modules.
- **Cross-validation** — already covered in [data-science/17-cross-validation.md](../data-science/17-cross-validation.md). Bootstrap (a related resampling idea) is added here in `07`.
- **Distance metrics** — already covered in [data-science/16-distance-metrics.md](../data-science/16-distance-metrics.md). The scaling subsection there overlaps with `04`; the data-prep version goes deeper into pipeline placement and Python tooling.

## Style conventions

- **Source line** at the top of every file points back to the ESE-5410 markdown / PDF the topic was synthesized from.
- **See also** lines cross-link to the existing data-science notes when the topic is duplicated there from a different angle.
- Code is Python (`pandas` / `numpy` / `scikit-learn` / `statsmodels`). The data-science folder remains R-flavored.
