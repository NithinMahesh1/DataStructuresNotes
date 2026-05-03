# 08 — Contingency Tables and Chi-Square

> **Source:** `Activity-ContingencyTables.Rmd.txt` (Gordon Anderson).

## What is a contingency table?

A table that shows the joint frequencies of two (or more) categorical variables.
- Rows = levels of one variable (e.g., gender)
- Columns = levels of another (e.g., political party)
- Cells = **observed frequencies** — counts of the combination

Example:
|   | Democrat | Independent | Republican |
|---|---|---|---|
| F | 762 | 327 | 468 |
| M | 484 | 239 | 477 |

Question: does affiliation **depend on** gender, or are they independent?

The word *contingency* means **dependency** — we're testing for one.

## Building a contingency table in R

```r
data.table <- as.table(rbind(c(762,327,468), c(484,239,477)))
dimnames(data.table) <- list(gender = c("F","M"),
                              party  = c("Democrat","Independent","Republican"))

# Or from a data frame:
cont.table <- table(df$GENDER, df$LECATTEND)

# Row proportions:
prop.table(cont.table, 1)   # 1 = rows; 2 = columns
```

## Chi-square test of independence

Tests whether two categorical variables are independent.

For each cell `i, j`:
- Observed count: `N_ij`
- Row total: `N_i`
- Column total: `N_j`
- Grand total: `N`
- **Expected count under independence:** `E_ij = N_i × N_j / N`

Test statistic:
`χ² = Σ (O − E)² / E` summed over all cells.

**Degrees of freedom:** `df = (rows − 1) × (cols − 1)`.

### Hypotheses

- **H₀ (null):** the variables are **independent** — no significant difference between observed and expected.
- **Hₐ (alternative):** there *is* a dependency.

### Decision rule

- Compare χ² to a critical value at chosen confidence (commonly 95%, i.e. p < 0.05).
- Equivalently: if **p-value < 0.05**, **reject H₀** → conclude there is a dependency.
- If p-value ≥ 0.05, fail to reject H₀ — no significant evidence of dependency.

### In R

```r
chisq.test(cont.table)
```

The output gives χ², df, and p-value in one shot.

## Interpretation tips

- A small p-value tells you *whether* there's likely a dependency, not *how strong* it is. Inspect proportions to see the practical effect.
- `prop.table(cont.table, 1)` gives **row proportions** — "what % of females always attended lecture vs. males?"
- A bar plot of `prop.table` makes the dependency visible at a glance.

## Cautions

- The chi-square test assumes **expected counts ≥ ~5** in each cell. With smaller cells use Fisher's exact test (`fisher.test`).
- Don't confuse statistical significance with practical importance — a tiny effect can be "significant" in a huge sample.
- Chi-square checks for *any* dependency, not its direction or magnitude.
