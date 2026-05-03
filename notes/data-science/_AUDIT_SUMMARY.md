# Audit Summary — Data Science Notes

> **Companion to** [`_AUDIT_CHECKLIST.md`](_AUDIT_CHECKLIST.md). The checklist has the per-item details, citations, and date stamps. This file is the high-level recap.

**Audit completed:** 2026-05-03
**Scope:** All 29 items in the original audit checklist
**Method:** 6 parallel research agents across 3 sessions (mix of `general-purpose` for web research and `docs-agent` for CRAN package status verification)
**Result:** ✅ All 29 items verified

---

## Findings by bucket

### Bucket 1 — Dated facts and statistics (4 items)

| Item | Result | Action taken |
|---|---|---|
| Wikipedia DB size (5.87 TB / Jan 2010) | ❌ Original figure was suspect | Replaced with current (~24 GB articles dump, >400 TB w/ media) |
| IBM Watson 16 TB RAM (Feb 2011) | ✅ Verified accurate | Kept; added modern context note |
| Facebook 960 B images / 357 PB (Jan 2013) | ❌ Doesn't match Facebook's own disclosures | Replaced with ~220 B / >100 PB (Dec 2012) + modern multi-exabyte context |
| "Data scientist" definitions (2012-2013) | ⚠️ Still valid for the core role | Added callout about post-2019 fragmentation into Data Scientist / ML Engineer / Analytics Engineer / AI Engineer |

### Bucket 2 — R packages (12 items, including R version itself)

| Package | Status | Action taken |
|---|---|---|
| `class::knn` | Maintenance, v7.3-23 (Jan 2025), Ripley | Kept; noted modern alternatives (`kknn` / `tidymodels` / `caret`) for tuning |
| `e1071::naiveBayes` | Active, v1.7-17 (Dec 2025) | Kept; noted `naivebayes` package (Majka) as richer alternative |
| `tree` | Maintenance, v1.0-45 (Aug 2025), Ripley | Strengthened recommendation to use `rpart` + `rpart.plot` for new work |
| `randomForest` | Maintenance, v4.7-1.2 (Sept 2024) | Kept; strengthened `ranger` callout (10-100× faster, multithreaded) |
| `gbm` | ⚠️ **Confirmed maintenance-only** (NEWS file says so), v2.2.3 (Jan 2026) | Strengthened warning; recommended `xgboost` / `lightgbm`; explicitly told reader to **skip `gbm3`** (never reached CRAN) |
| `mclust` | Active, v6.1.2 (Oct 2025), Scrucca | Kept as standard for GMM clustering; no md change |
| `NbClust` | Maintenance, v3.0.1 (May 2022) | Kept; quiet release cadence noted but algorithm is mathematically stable |
| `cluster::pam` / `daisy` | Active, v2.1.8.2 (Feb 2026) | Kept; added `pamonce=5` (FastPAM) speedup tip for large data |
| `fpc::pamk` / `plotcluster` | Active, v2.2-14 (Jan 2026), Hennig | Kept |
| `flexclust::randIndex` | Active, v1.5.0 (Feb 2025); maintainer transitioned to Bettina Grün | Kept; added `mclust::adjustedRandIndex` as lighter alternative |
| `RSQLite` | Active, v2.4.6 (Feb 2026) | ⚠️ **Breaking change in v2.3.7 (2024)** flagged: duplicate column names in `dbGetQuery()` no longer auto-suffixed |
| R 3.6.0 RNG change | Still in force in R 4.x | Documented `RNGversion("3.5.0")` idiom for reproducing pre-3.6.0 results |

### Bucket 3 — Methodology / terminology drift (7 items)

| Item | Result | Action taken |
|---|---|---|
| Naive Bayes `laplace=3` | ⚠️ Non-standard — `e1071` default is 0, textbook convention is 1 | Clarified in `12-naive-bayes.md` |
| K-means elbow method | Schubert (2023) explicitly says stop teaching as primary | Strengthened warning in `15-clustering.md`; pointed to silhouette / gap statistic / BIC / NbClust |
| "Big Data" terminology | Term peaked ~2014-2017, displaced by lakehouse / cloud-native / GenAI framing | Added margin note in `01-intro-to-data-science.md` |
| R-squared interpretation | Plain R² now treated as descriptive only, not for model selection | Strengthened caveat in `09-linear-regression.md`; added adjusted R² / AIC / BIC / out-of-sample RMSE |
| Bias-variance / double descent | Belkin et al. 2019 widely accepted | Added footnote in `04-modeling-concepts.md`; clarified classical U-curve still applies in this course's regime |
| "Data mining" framing | Largely absorbed into ML / data science | Noted; no md change (course quotes original 2013 definitions verbatim) |
| ARIMA still relevant? | ✅ Still actively used in 2025-2026 for short-horizon, low-data, interpretable, regulated, baseline use | Updated `18-time-series.md` framing — displaced as the *only* tool, not as a tool; mentioned hybrid ARIMA + ML residual approach |

### Bucket 4 — Sources / URLs / dataset references (3 items)

| Item | Result | Action taken |
|---|---|---|
| Ling-Spam dataset URL | ❌ csmining.org dead (now redirects to Federation University Australia infra w/ broken cert) | Replaced with AUEB tarball (Androutsopoulos, original author) + Kaggle mirror |
| statmethods.net (Quick-R) | ⚠️ Site silently moved | Updated link to `datacamp.com/doc/r/...` (Quick-R was acquired/migrated to DataCamp) |
| ISLR | ⚠️ Now in 2nd edition | Noted ISLR2 (R, 2021) + ISLP (Python, 2023); datasets unchanged; free PDFs at statlearning.com |

### Bucket 5 — Final sanity checks (3 items)

| Item | Result | Action taken |
|---|---|---|
| Formula sanity-check (chi-square, Bayes, Euclidean, OLS, ARIMA) | ✅ All still standard and unchanged | None needed |
| Core algorithm sanity-check (KNN, Naive Bayes, k-means, decision trees, RF, gradient boosting, linear/logistic regression) | ✅ All still taught the same way | None needed; XGBoost/LightGBM noted as modern boosting implementations |
| R 4.x compatibility | ⚠️ Most code still runs, but R 4.0.0's `stringsAsFactors = FALSE` default is the main friction | Added compatibility section to `README.md` with workaround + RNG idiom |

---

## Files modified across the audit

- `_AUDIT_CHECKLIST.md` — all 29 items checked off with verification dates, sources, and outcomes; completion footer added
- `README.md` — added "Running the source `.Rmd` code on modern R" section with `stringsAsFactors` and `RNGversion` guidance
- `01-intro-to-data-science.md` — corrected Wikipedia/Facebook stats (table format), added DS role fragmentation callout, added "Big Data" term-aging note
- `04-modeling-concepts.md` — added double descent footnote
- `05-r-basic-stats-and-plots.md` — replaced statmethods.net link with DataCamp
- `07-relational-databases.md` — added RSQLite v2.3.7 breaking change heads-up
- `09-linear-regression.md` — strengthened R² caveat (adjusted R² / AIC / BIC / cross-validated RMSE)
- `11-knn.md` — added modern alternatives note (`kknn`, `tidymodels`, `caret`)
- `12-naive-bayes.md` — clarified `laplace=3` is non-standard; replaced dead Ling-Spam URL with AUEB + Kaggle
- `13-decision-trees.md` — strengthened recommendation to use `rpart` + `rpart.plot`
- `14-ensemble-methods.md` — added `ranger` callout to bagging code; strengthened `gbm` maintenance-mode warning + `xgboost`/`lightgbm` recommendation + skip-`gbm3` note
- `15-clustering.md` — strengthened elbow method warning (Schubert 2023); added FastPAM (`pamonce=5`) tip; added lighter ARI alternative
- `18-time-series.md` — reframed ARIMA section as still-relevant baseline; mentioned hybrid ARIMA + ML approach

**No changes needed** to: `02`, `03`, `06`, `08`, `10`, `16`, `17` — content was already accurate or unaffected by audit findings.

---

## Recommendation

The notes are now reasonably current as of **May 2026**. Periodic re-audit recommended every **~12 months** as R packages drift and new methodology emerges. The R packages section ages fastest; the foundational concept files (`02–04`, `08–10`, `16`) age slowest.
