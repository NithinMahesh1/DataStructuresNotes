# 16 — Distance Metrics

> **Source:** `DistanceMetrics.pdf` (Gordon Anderson).
> **Used in:** KNN (`11-knn.md`), Clustering (`15-clustering.md`).

## Why distance matters

The concept of **similarity** is central to ML and AI. Most classification and clustering algorithms need a way to gauge how similar two data points are. **Distance is dissimilarity** — small distance = high similarity.

Examples of similarity questions: *Are two products similar? Is this frozen pizza healthy (vs. a "healthy food" model)? How long will the next task take?* All can be framed as "compare a feature vector to a reference and measure distance."

## Properties of a "true" distance metric

A function `d(a, b)` is a true metric iff it satisfies:

1. **Symmetry:** `d(a,b) = d(b,a)`
2. **Non-negativity / identity:** `d(a,b) ≥ 0`, and `d(a,b) = 0` iff `a = b`
3. **Triangle inequality:** `d(a,b) ≤ d(a,c) + d(c,b)`

If any of these fails, the function is a **dissimilarity measure** (still useful — e.g., Gower satisfies symmetry/non-negativity but not always the triangle inequality).

## Distance Matrix

Given N data points and a metric, the **distance matrix** is the N×N table of all pairwise distances. Symmetric, with zeros on the diagonal. Computed once, reused by clustering / KNN.

```r
d  <- dist(data, method="euclidean")   # returns a "dist" object
dm <- as.matrix(d)                     # convert to N×N matrix
```

## Euclidean (L₂) — the default for numeric data

The straight-line distance between two points in p-dimensional space:

`d(i, h) = √( Σⱼ (aᵢⱼ − aₕⱼ)² )`

In 2D this is the Pythagorean theorem.

## Manhattan / City-Block (L₁)

Sum of absolute differences along each axis — like taxi-cab distance on a grid:

`d(i, h) = Σⱼ |aᵢⱼ − aₕⱼ|`

Less sensitive to outliers than Euclidean.

## When to use Euclidean vs. Manhattan

- **Euclidean:** when the underlying geometry is "real" — physical positions, normalized continuous features.
- **Manhattan:** when features represent independent quantities or when you want robustness to outliers.

In practice, you often try both and see which gives more interpretable / better-performing clusters.

## Hamming Distance — for strings / categorical

The number of positions where two equal-length strings differ.

```
hamming("freddy", "teddie") = 5
```

Used for comparing fixed-length categorical sequences (DNA, error-correcting codes, etc.).

## Mahalanobis Distance — accounts for covariance

A Euclidean-like distance that accounts for the **covariance structure** of the data.

- Inversely weights distance by variance — features with high variance contribute less.
- Takes correlations between features into account.
- Used when clusters have different shapes/sizes or when features are correlated.
- Tied closely to **Principal Component Analysis (PCA)**.

## Categorical data — matching approach

For categorical-only data, dummy-encode levels and compute a metric, OR use a matching coefficient:

**Simple Matching Coefficient (SMC):**
`SMC = mismatches / total variables`
This is in [0, 1] and is a **dissimilarity**. Similarity = 1 − SMC.

Example with 5 categorical variables, 3 mismatches: `SMC = 3/5 = 0.6`.

If you dummy-encode each level into 0/1 indicators and compute squared Euclidean distance, you get an equivalent measure (the count of mismatches), divided by total possible.

The **chi-square distance** is a weighted version of SMC.

## Mixed data — Gower's coefficient

For data with both continuous and categorical variables. Computed in R with `cluster::daisy(data, metric="gower")`.

Steps:
1. **Standardize** each continuous variable.
2. **Scale** categorical variables by √2 (compensates for the 0/1 encoding).
3. Compute a distance metric per variable type and average.

Useful for clustering datasets that mix incomes, ages, gender, ethnicity, etc.

## Scaling: Normalize vs. Standardize

Both are common preprocessing steps so different-unit features contribute equally.

| | Formula | Result | When to use |
|---|---|---|---|
| **Normalize (Min-Max)** | `(x − min) / (max − min)` | Range `[0, 1]` | Bounded data, neural networks, when you want a fixed range |
| **Standardize (Z-score)** | `(x − mean) / sd` | Mean 0, SD 1 | Most ML, when distribution is roughly normal |

- **Normalization diminishes outlier effects** (everything fits in [0,1]).
- **Standardization can produce extreme values** (no bounding).

There are many other scaling techniques (robust scaling using median/IQR, log transforms, etc.).

## Distance metric selection — quick guide

| Data type | Default | Alternatives |
|---|---|---|
| Numeric, continuous | Euclidean | Manhattan, Mahalanobis |
| Numeric with correlations | Mahalanobis | |
| Categorical | Hamming, SMC | Chi-square distance |
| Mixed | Gower | Custom weighted metric |
| High-dim sparse (text) | Cosine similarity | Jaccard |

(Cosine similarity isn't covered in detail by the source notes but is the standard for text/document similarity.)
