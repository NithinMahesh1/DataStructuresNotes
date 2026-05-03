# 15 — Clustering (Unsupervised Learning)

> **Source:** `Notes-Clustering.pdf`, `Activity-Clustering.Rmd.txt`, `Hwk5-1KClustering.html`.

## What it does

Discover **groups of similar data points** without using labels. Also called segmenting, stratification.
- **Unsupervised** — no labeled training set; the algorithm discovers structure.
- It's up to you (or a domain expert) to interpret what each cluster *means*.

Compare with KNN — KNN uses labels (supervised). After clustering, you can use KNN with `k=1` to assign new points to the nearest cluster.

## What "similar" means

Almost all clustering methods need a notion of **distance** (or dissimilarity) between data points. See `16-distance-metrics.md`.
- Numeric data → Euclidean is the standard default.
- Categorical data → matching coefficients, Hamming.
- Mixed data → Gower's coefficient.

You should usually **scale** numeric data first (`scale()` in R), so features with larger units don't dominate.

## K-Means

Simple, widely used, the canonical clustering algorithm.

**Algorithm** (given data and k):
1. Choose k cluster centers at random.
2. Assign each data point to the **nearest** cluster center.
3. Move each cluster center to the **centroid (mean)** of its assigned points.
4. Repeat steps 2–3 until no reassignments (or stopping criterion).

```r
set.seed(1234)
fit.km <- kmeans(wine.data.scaled, centers=3, nstart=25)
fit.km$size           # number of points per cluster
fit.km$tot.withinss   # within-cluster sum of squared distances (cohesiveness)
fit.km$betweenss      # between-cluster sum of squares (separation)
fit.km$cluster        # cluster assignment for each point
```

### Important notes on k-means

- **You must choose k upfront.**
- **Sensitive to initial center placement.** The `nstart=25` argument runs k-means 25 times with different random starts and keeps the best result — always set this to ≥10 in practice.
- **Could overfit** — if `k = N` (number of data points), every point is its own cluster — useless.

## Choosing k

Two common techniques:

### 1. Elbow plot (within-groups SS vs k) — quick visual sanity check only

Plot `tot.withinss` for k = 1, 2, 3, … and look for the **bend ("elbow")**. Below the elbow, adding clusters gives diminishing reductions in within-cluster variance.

> ⚠️ **The elbow method is now the explicit consensus weak choice.** Schubert (2023, *SIGKDD Explorations*: "Stop using the elbow criterion for k-means") argues educators should stop teaching it as the primary method. **Stronger alternatives:**
> - **Silhouette score** — measures how well each point fits its cluster vs. others
> - **Gap statistic** (Tibshirani) — compares within-cluster dispersion to a null reference
> - **BIC-based methods** — X-means, G-means, model-based clustering (`mclust`)
> - **`NbClust`** — combines ~30 indices via majority vote (next subsection)
> Use the elbow plot only as a quick visual sanity check.

```r
wssplot <- function(data, nc=15, seed=1234) {
  wss <- (nrow(data)-1) * sum(apply(data, 2, var))
  for (i in 2:nc) {
    set.seed(seed)
    wss[i] <- sum(kmeans(data, centers=i)$withinss)
  }
  plot(1:nc, wss, type="b", xlab="Number of Clusters", ylab="Within groups sum of squares")
}
wssplot(wine.data.scaled)
```

> ⚠️ The elbow method is now considered a weak heuristic. See audit checklist.

### 2. NbClust — many indices, majority vote

Runs ~30 different cluster-quality indices and reports the most common winner.

```r
library(NbClust)
nc <- NbClust(wine.data.scaled, min.nc=2, max.nc=15, method="kmeans")
table(nc$Best.n[1, ])
```

## PAM — Partitioning Around Medoids

Like k-means, but cluster centers are **actual data points (medoids)** rather than geometric centroids. More general — can use any **dissimilarity** measure (not just distance).

- **Centroid:** geometric mean — does not need to be an actual data point.
- **Medoid:** the actual data point in a cluster that minimizes the sum of distances to all other members.

```r
library(cluster)
library(fpc)

# Distance / dissimilarity matrix
dist.mat <- daisy(wine.data.scaled, metric="euclidean")

# Find best k via PAM
pk <- pamk(dist.mat, krange=2:15, usepam=TRUE, diss=TRUE)
pk$nc

# Fit PAM at chosen k
fit.pam <- pam(dist.mat, k=3)
plot(fit.pam)       # silhouette plot
clusplot(fit.pam)   # 2D PCA visualization
```

> **Speedup tip (verified 2026-05-03):** For large datasets, `pam(..., pamonce = 5)` activates the **FastPAM** algorithm (Schubert & Rousseeuw 2019), ~10× faster than the default with the same result.

### Silhouette plots

Each cluster member is a horizontal line whose **silhouette width** measures how well it fits its cluster (higher = better, can be negative if a member is closer to another cluster).

## Categorical / Mixed Clustering — Gower

Distance metrics like Euclidean don't apply cleanly to categorical features. **Gower's coefficient** computes dissimilarity for mixed continuous + categorical data, then PAM clusters on it.

```r
diss.mat <- daisy(cr.data.subset, metric="gower")
pk <- pamk(diss.mat, krange=2:15, usepam=TRUE, diss=TRUE)
fit.pam <- pam(diss.mat, k=12)
```

## Model-Based Clustering — `mclust`

Treats clustering as fitting **mixtures of probability distributions**. Each point has a probability of belonging to each cluster (soft assignment).

```r
library(mclust)
fit <- Mclust(wine.data.scaled)
summary(fit)                                  # best model + cluster count
plot(fit, data=wine.data.scaled, what="BIC")  # BIC across model types and k
fit$classification                            # hard cluster assignment per point
fit$z                                         # probability matrix per cluster
```

`Mclust` evaluates many model types (parameterizations of cluster shape, volume, orientation) and many values of k. It uses **BIC (Bayesian Information Criterion)** to select the best model — penalizes complexity to avoid overfitting.

## Visualizing high-dim clusters with PCA

When data has more than 2–3 dimensions, plot the first two **principal components** (axes of greatest variance) and color points by cluster.

```r
wine.pc <- princomp(wine.data.scaled, cor=TRUE)
plot(wine.pc$scores[, 1], wine.pc$scores[, 2],
     xlab="PC 1", ylab="PC 2", col=fit$classification)
```

## Evaluating clustering quality

You usually don't have ground truth. But if you do (or have a hypothesis), compare with:

- **Cross-tabulation:** `table(true.labels, fit$cluster)` — see how cluster assignments line up with known labels.
- **Adjusted Rand Index (ARI):** measures similarity between two clusterings on [-1, 1].

```r
library(flexclust)
randIndex(table(true.labels, fit$cluster))   # ARI

# Lighter alternative if ARI is all you need:
mclust::adjustedRandIndex(true.labels, fit$cluster)
```

When you don't have labels:
- Within-cluster sum of squares (cohesiveness)
- Between-cluster sum of squares (separation)
- Silhouette width

## Strengths and weaknesses

**K-means**
- Fast, simple, scales well.
- Assumes spherical clusters of similar size — fails on elongated, non-convex shapes.
- Sensitive to outliers.

**PAM**
- More flexible (any dissimilarity), more robust to outliers (medoid vs centroid).
- Slower than k-means on large data.

**Model-based (mclust)**
- Soft assignments, principled model selection.
- Assumes clusters follow Gaussian-like distributions (per the chosen model type).

## Workflow recap

1. Decide what "similarity" means for your data → choose distance metric.
2. **Scale** numeric variables (`scale()`).
3. Try multiple methods (k-means, PAM, model-based) and multiple k values.
4. Compare cluster quality measures.
5. Visualize (PCA scatter, silhouette, BIC plot).
6. **Have a domain expert sanity-check the clusters' meaning.** Algorithms find structure; humans assign meaning.
