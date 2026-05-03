# 05 — R: Basic Stats and Plots

> **Source:** `Activity-BasicStatsAndPlots.Rmd.txt` (Gordon Anderson).

A primer on the R idioms used throughout the rest of the course: factors, summarizing, grouping, basic plots.

## Built-in datasets

R ships with many datasets (e.g., `mtcars`, `faithful`). Many libraries add their own. Useful first commands:
- `dim(x)` — rows × cols
- `head(x)` — first 6 rows
- `summary(x)` — descriptive stats per column
- `str(x)` — structure (types, sample values)

## Numeric vs. categorical (factors)

Numeric columns get min/median/mean/max in `summary()`. Categorical columns need to be **factors** to be summarized as counts:

```r
mtcars$cyl <- factor(mtcars$cyl)   # convert to categorical
summary(mtcars)                    # cyl now shows level counts
```

Common gotcha: `cyl` looks numeric but is really categorical (only 3 levels: 4, 6, 8).

## Frequency tables

`table()` counts occurrences across one or more categorical columns:

```r
counts <- table(mtcars$cyl)        # 1D table
table(df$Gender, df$LECATTEND)     # 2D contingency table
```

## Grouping and summarizing — `tapply`

General form: `tapply(SummaryVariable, GroupVariable, Function)`.

```r
tapply(mtcars$mpg, mtcars$cyl, mean)   # avg mpg per cylinder count
```

## Subsetting

```r
testdata <- subset(mtcars, cyl == 6, select = c(mpg))
mean(testdata$mpg)
```

⚠️ R won't warn you if you misspell a column name (`cyls` vs `cyl`) or use `=` instead of `==` in a logical filter. Bad data silently returns wrong answers.

## Basic plots

```r
barplot(counts, main="Car Distribution", xlab="Cylinders", ylim=c(0,20))
plot(eruptions ~ waiting, data=faithful)         # scatter, formula form
plot(x, main="title", xlab="...", ylab="...")    # add title and axis labels
hist(x, prob=TRUE, col="gray")                   # histogram (prob=density)
lines(density(x), lty=2)                         # overlay density curve
abline(h=0)                                      # horizontal line
abline(coef(fit), col="red", lty=2)              # plot a regression line
```

`pch` controls point shape, `lty` line type, `col` color, `lwd` line width.

## Recurring patterns you'll see throughout

- **Read CSV:** `df <- read.csv("file.csv")`
- **Make a column a factor with labels:**
  ```r
  df$col <- factor(df$col)
  levels(df$col) <- c("low", "med", "high")
  ```
- **Check missing data:** `sum(is.na(df))`
- **Drop rows with NA:** `df <- na.omit(df)`
- **Random sampling for train/test:**
  ```r
  set.seed(123456)
  train.rows <- sample(1:nrow(df), 0.75 * nrow(df), replace=FALSE)
  test.rows  <- setdiff(1:nrow(df), train.rows)
  ```
- **Reproducibility:** always `set.seed()` before any random sampling so results can be re-run.

## Useful reference

The course mentioned **Quick-R** (Robert Kabacoff) for graphical parameters. Quick-R has been migrated to DataCamp's documentation — the bar plots reference is now at [datacamp.com/doc/r/bar](https://www.datacamp.com/doc/r/bar). The original `statmethods.net` URLs 301-redirect there. (Verified 2026-05-03.)
