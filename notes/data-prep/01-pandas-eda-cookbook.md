# 01 — Pandas EDA Cookbook

> **Source:** `midterm notes/data analysis.md` (ESE-5410), with bits from the module-3 / module-4 Python labs.
> **See also:** [04-scaling-and-standardization.md](04-scaling-and-standardization.md) for what to do with the numeric columns once you've explored them.

A reference for the **first hour** with a new tabular dataset in Python — load it, slice it, describe it, plot it, find what's missing. Everything here is what you do *before* fitting a model.

> Examples below use a generic dataset with columns `units_sold`, `views`, `purchases`, `price`, and `category` (a binary A/B label) — pick column names that fit your data.

## Loading and previewing

```python
import pandas as pd

df = pd.read_csv("data.csv")

df.head()        # first 5 rows
df.tail()        # last 5 rows
df.shape         # (rows, columns)
df.columns       # column names
df.info()        # dtypes + non-null counts
df.describe()    # numeric summary: count, mean, std, min, quartiles, max
```

`df.info()` is the fastest "are there missing values, and do my dtypes look right?" check.

## Selecting

```python
df["col"]                 # one column → Series (1-D)
df[["col1", "col2"]]      # multiple columns → DataFrame (2-D)
df.iloc[:, :10]           # first 10 columns by position
df[df["col"] > 5]         # filter rows by condition
```

⚠️ **Single vs double brackets:** `df["col"]` is a 1-D Series; `df[["col"]]` is a 2-D DataFrame. scikit-learn estimators that expect `X` to be 2-D need the double brackets.

## Summary statistics

```python
df["col"].mean()
df["col"].median()
df["col"].std()
df["col"].quantile(0.75)   # 75th percentile
df["col"].max()
df["col"].min()
df["col"].idxmax()         # row label of the max — "which row has the highest value?"
df["col"].idxmin()
```

`df.describe()` gives `count / mean / std / min / 25% / 50% / 75% / max` for every numeric column at once.

| Statistic | Meaning |
|---|---|
| `count` | Number of non-missing values |
| `mean` | Average |
| `std` | Standard deviation (spread) |
| `min` / `max` | Smallest / largest value |
| `25%` / `50%` / `75%` | Q1 / median / Q3 |

## Missing values

```python
df.isnull().sum()        # missing count per column
df.dropna()              # drop rows with any NaN
df.fillna(0)             # fill with constant
df.fillna(df.mean(numeric_only=True))   # fill with column mean
```

Don't `dropna()` reflexively — see how many rows you'd lose first. If the missingness is correlated with the target (e.g., respondents who skip income questions are systematically lower-income), dropping introduces bias.

## Boolean masks

A boolean Series you can use to filter, count, or build a new indicator column:

```python
mask = df["units_sold"] > df["units_sold"].mean()

mask.sum()              # how many True (counts as the sum of 0/1)
df[mask]                # filtered DataFrame
df["above_avg"] = mask  # store the mask as a new indicator column
```

Equivalent inline:

```python
df[df["units_sold"] > df["units_sold"].mean()]
```

## Creating new columns (basic feature engineering)

```python
df["conversion_rate"] = df["purchases"] / df["views"]

# Conditional column — vectorized (preferred over .apply for speed)
import numpy as np
df["efficiency"] = np.where(df["mpg"] > 25, "high", "low")
```

⚠️ **Division by zero:** `df["purchases"] / df["views"]` produces `inf` or `NaN` when `views == 0`. Replace explicitly: `df["views"].replace(0, np.nan)`.

See [02-feature-engineering.md](02-feature-engineering.md) for polynomial / interaction features.

## groupby and aggregation

```python
df.groupby("category")["price"].mean()
# category
# A     6813
# B    11802

df.groupby("category").agg({
    "price":      "mean",
    "units_sold": ["min", "max"],
})
```

Common aggregations: `.mean()`, `.median()`, `.count()`, `.sum()`, `.describe()`.

## Correlation

```python
df.corr(numeric_only=True)                                  # full correlation matrix
df["x1"].corr(df["x2"])                                     # one pair
df.corr(numeric_only=True)["target"].sort_values(ascending=False)
# which features correlate most strongly with the target
```

Quick interpretation:

| `\|r\|` | Strength |
|---|---|
| 0.0 – 0.3 | Weak |
| 0.3 – 0.7 | Moderate |
| 0.7 – 1.0 | Strong |

⚠️ `r ≈ 0` only rules out a *linear* relationship — a perfect parabola has `r ≈ 0` too. Always look at the scatter.

For predictor-vs-predictor correlation (the multicollinearity problem), see [05-multicollinearity.md](05-multicollinearity.md).

## Visualization — the core four

### Histogram (distribution of one variable)

```python
import matplotlib.pyplot as plt
plt.hist(df["views"], bins=50)
plt.xlabel("Views"); plt.ylabel("Frequency")
```

Bin count matters: too few smooths features away; too many shows noise. Right-skewed (long tail to the right) is common for counts and dollar amounts; consider a log transform — see [02-feature-engineering.md](02-feature-engineering.md).

### Scatter (two continuous variables)

```python
plt.scatter(df["x"], df["y"])
plt.xlabel("x"); plt.ylabel("y")
```

Look for: linear / non-linear trend, clusters, outliers.

### Boxplot (continuous variable, split by category)

```python
import seaborn as sns
sns.boxplot(x="category", y="price", data=df)
```

Components:

```
    •  ← outlier
    ┬  ← max within ~1.5 × IQR of Q3
    ┌─┐
    │ │
    ├─┤ ← median (Q2)
    │ │
    └─┘
    ┴  ← min within ~1.5 × IQR of Q1
```

Use to compare a numeric variable across categories — overlapping boxes → groups aren't clearly different.

### Pairplot (everything-vs-everything)

```python
sns.pairplot(df)
```

Diagonal = histogram of each column; off-diagonal = scatter of every pair. Doesn't scale past ~10 columns — subset first.

## Common pandas pitfalls

1. **Reference vs copy** — `df2 = df` is a *reference*; modifying `df2` modifies `df`. Use `df.copy()` for an independent DataFrame.
2. **Forgetting `axis`** — `df.mean()` defaults to per-column (`axis=0`); pass `axis=1` for per-row.
3. **`==` vs `=` in filters** — write `df[df["col"] == 6]`, not `df[df["col"] = 6]`.
4. **Chained assignment** — `df[df.x > 5]["y"] = 0` doesn't reliably assign; use `.loc`: `df.loc[df["x"] > 5, "y"] = 0`.
5. **Numeric column read as strings** — a stray `"N/A"` or comma can make pandas type a column as `object`. `df.info()` catches it; fix with `pd.to_numeric(..., errors="coerce")`.

## Quick reference — what tool for what question

| Question | Tool |
|---|---|
| Compare a numeric variable across categories | `boxplot` + `groupby(...).mean()` |
| Distribution of one variable | `hist` |
| Relationship between two continuous variables | `scatter` + `.corr()` |
| Which feature is most related to the target | `df.corr()[target].sort_values(...)` |
| Find the row with the min / max of something | `idxmin()` / `idxmax()` |
| Make a True/False flag | boolean comparison |
| Filter rows | boolean indexing |
| Percentile | `quantile()` or `describe()` |
| Are there missing values? | `df.isnull().sum()` |
