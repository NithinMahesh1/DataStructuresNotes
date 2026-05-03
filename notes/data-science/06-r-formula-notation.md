# 06 — R Formula Notation

> **Source:** `FormulaNotationInR.pdf` (Chicago Booth, used as a reference handout in the course).

R's `lm()`, `glm()`, `naiveBayes()`, `tree()`, `randomForest()`, etc. all share a compact formula syntax for specifying which variables go into the model and how.

## Basic form

```
response ~ predictors
```

The `~` reads as **"is modeled as a function of."**

```r
fit <- lm(Y ~ X)              # simple linear regression
fit <- lm(Y ~ X + Z)          # multiple regression: two predictors
fit <- lm(Y ~ X + Z + W)
```

Mathematically, `Y ~ X + Z` corresponds to `Y_i = β₀ + β₁·X_i + β₂·Z_i + ε_i`.

> ⚠️ The `+` here is **not arithmetic addition** — it just lists predictors to include.

## Operator cheat sheet

| Symbol | Example | Meaning |
|--------|---------|---------|
| `+` | `+ X` | Include this variable |
| `-` | `- X` | Exclude this variable |
| `:` | `X:Z` | Include the **interaction** between X and Z (no main effects) |
| `*` | `X*Z` | Include both variables AND their interaction (`X + Z + X:Z`) |
| `^` | `(X+Z+W)^3` | Include variables and all interactions up to 3-way |
| `I()` | `I(X*Z)` | "as is" — treat the contents arithmetically (multiply, then include as a single predictor) |
| `1` | `Y ~ X - 1` | Remove the intercept (regress through the origin) |
| `\|` | `X \| Z` | Conditioning: include X given Z |
| `.` | `Y ~ .` | Use **all other columns** in the data frame as predictors |

## Equivalent forms

Three equivalent specs of "all main effects + all interactions up to 3-way":
```
Y ~ X + Z + W + X:Z + X:W + Z:W + X:Z:W
Y ~ X * Z * W
Y ~ (X + Z + W)^3
```

Three equivalent specs without the 3-way interaction:
```
Y ~ X + Z + W + X:Z + X:W + Z:W
Y ~ X * Z * W - X:Z:W
Y ~ (X + Z + W)^2
```

## The `.` shorthand

If a data frame `D` has columns `Y, X, Z, W`, then:
- `lm(Y ~ ., data=D)` is equivalent to `lm(Y ~ X + Z + W, data=D)`
- `lm(Y ~ . - W, data=D)` is equivalent to `lm(Y ~ X + Z, data=D)`
- `lm(Y ~ . * W, data=D)` is equivalent to `lm(Y ~ X + Z + W + X:W + Z:W, data=D)`

This lets you experiment with a lot of model specs without rewriting columns each time.

## Why interactions matter

If two predictors have **non-additive** effects (changing X has different effects depending on Z), a model with only `X + Z` will mis-fit. Adding `X:Z` lets the model capture that. Example from the linear regression activity: child test scores ~ mom_hs + mom_iq has parallel slopes; adding `mom_hs:mom_iq` lets the slopes vary by HS status.
