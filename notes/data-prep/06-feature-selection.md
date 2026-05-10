# 06 — Feature Selection

> **Source:** `final exam/final - mod8 formulas.md` (ESE-5410), §"best subset selection" through §"ridge & lasso regression".
> **See also:** [05-multicollinearity.md](05-multicollinearity.md) (the problem regularization helps with), [07-pca-and-bootstrap.md](07-pca-and-bootstrap.md) (an alternative dimension-reduction approach).

When you have **too many predictors** — more than necessary, redundant, or just noisy — feature selection picks a smaller subset that predicts as well or better. Three reasons to bother:

1. **Reduce variance.** Fewer parameters → less overfitting → lower test error.
2. **Improve interpretability.** A 5-feature model is easier to explain than a 50-feature one.
3. **Cheaper to deploy.** Fewer features to collect, transform, and store at inference time.

Two families of methods: **subset selection** (pick a discrete set of features and fit OLS) and **shrinkage / regularization** (fit all features but pull coefficients toward zero).

## Best subset selection

For each subset size `k` from 0 to `p`:
1. Fit OLS on every possible `k`-feature subset.
2. Keep the one with the lowest training RSS. Call it `M_k`.

Then select **across** sizes `M₀, M₁, …, M_p` by cross-validated test error.

- Total models considered: `2^p`.
- **Guaranteed globally optimal training fit** at each `k`, because every combination is tried.
- **Not guaranteed best test error** — the search is so large it can overfit.
- **Computationally infeasible** past about `p = 25–30`.

⚠️ Use training RSS to pick *within* size `k`; use cross-validated error to pick *across* sizes. Picking across sizes by training RSS would always pick the full model — training RSS only ever decreases as you add features.

## Forward stepwise selection

Greedy alternative to best-subset:

1. Start with `M₀` = null model (just the intercept; predicts `ȳ`).
2. At step `k`, take `M_{k−1}` and try adding each of the `p − (k−1)` remaining predictors. Pick the addition that minimizes RSS. Call the result `M_k`.
3. Repeat until all `p` predictors are in.
4. Select across `M₀, …, M_p` by cross-validation.

- Total models fit: `1 + p + (p−1) + … + 1 = p(p+1)/2 + 1`. Much cheaper than best-subset.
- **Greedy:** locks in each addition and never reconsiders. The best single-feature model might be `{x₁}`, but the best pair could be `{x₂, x₃}` — forward stepwise will never find that pair because it starts by locking in `x₁`.
- Works for `p` up to thousands.

## Backward stepwise selection

Mirror image of forward:

1. Start with the full `p`-predictor model (`M_p`).
2. At each step, remove the predictor whose deletion increases RSS the least.
3. Continue until only the intercept remains.
4. Select across by CV.

- Same total cost as forward (`p(p+1)/2 + 1` fits).
- Requires `n > p` to fit the initial full model — won't run when you have more features than rows.
- Often picks a different subset than forward; trying both is cheap.

## Ridge regression — L2 penalty

Instead of choosing a discrete subset, **shrink** all coefficients toward zero by adding a penalty to the loss:

`minimize  RSS + λ · Σⱼ βⱼ²`

- `λ = 0` → no penalty → standard OLS.
- `λ → ∞` → all coefficients shrink to 0.
- `λ` chosen by **cross-validation**.

Effect on bias-variance: as `λ` increases, **variance decreases and bias increases.** Past the optimal `λ`, bias dominates and test error rises again.

⚠️ Ridge **never sets a coefficient exactly to zero** — every predictor stays in the model with a (possibly tiny) coefficient. So Ridge is *shrinkage*, not feature selection.

When predictors are highly correlated, Ridge tends to **distribute** coefficient weight evenly across them — keeps both effects represented.

```python
import numpy as np
from sklearn.linear_model import Ridge, RidgeCV

# Single λ
ridge = Ridge(alpha=1.0).fit(X_train_std, y_train)

# Choose λ by CV across a grid
ridge_cv = RidgeCV(alphas=np.logspace(-3, 3, 50), cv=10)
ridge_cv.fit(X_train_std, y_train)
print(ridge_cv.alpha_)
```

⚠️ **Always standardize before Ridge.** The penalty is on the magnitude of `β`, which depends on the units of the predictor. See [04-scaling-and-standardization.md](04-scaling-and-standardization.md).

## Lasso — L1 penalty

Replace the squared penalty with absolute value:

`minimize  RSS + λ · Σⱼ |βⱼ|`

A small change in the penalty geometry has a big consequence: **Lasso can drive coefficients exactly to zero.** As `λ` increases, more and more `βⱼ` snap to zero, leaving a **sparse** model.

So Lasso does feature selection and shrinkage in one step.

```python
from sklearn.linear_model import Lasso, LassoCV

lasso_cv = LassoCV(alphas=np.logspace(-3, 1, 50), cv=10)
lasso_cv.fit(X_train_std, y_train)

# Selected features:
selected = X_train.columns[lasso_cv.coef_ != 0]
```

### Ridge vs Lasso — which to use

| | Ridge | Lasso |
|---|---|---|
| Penalty | `Σ βⱼ²` (L2) | `Σ \|βⱼ\|` (L1) |
| Coefficients ever exactly 0? | No | Yes |
| Performs feature selection? | No | Yes |
| Behavior under multicollinearity | Distributes weight across correlated predictors | Picks one arbitrarily, zeros the others |
| Best when | Many small effects, correlated predictors | Truly sparse signal — most features irrelevant |

**Elastic Net** combines the two: penalty `α · L1 + (1−α) · L2`. Often a safer default than pure Lasso when correlated features matter.

## Choosing `λ`

Always by cross-validation, never by training error. Training RSS would always pick `λ = 0` (no penalty) — defeating the point.

The standard pattern: a log-spaced grid of `λ` values (e.g., `np.logspace(-3, 3, 50)`), K-fold CV, pick the `λ` that minimizes CV error. The **1-standard-error rule** — pick the largest `λ` whose CV error is within 1 SE of the minimum — gives a slightly more conservative model with fewer features.

## Quick reference

| Tool | Use when |
|---|---|
| Best subset | `p ≲ 25`, want the globally best `k`-feature subset |
| Forward stepwise | `p` is large; quick first pass |
| Backward stepwise | `n > p`; quick first pass with all features in play |
| Ridge | Many features, correlated, want to keep them all |
| Lasso | Want sparse model — feature selection built in |
| Elastic Net | Sparse model, but correlated features should stick together |
