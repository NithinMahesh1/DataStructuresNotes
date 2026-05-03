# 18 — Time Series

> **Source:** `Notes/Notes-TimeSeries (1).pdf` (Gordon Anderson).

## What is a time series?

A sequence of observations recorded **over time, at equally spaced intervals**.

- Data recorded **irregularly** is *not* a time series — it needs to be regularized first (interpolation, aggregation).
- Each series has a **time unit** and **frequency**: e.g., monthly birth counts from 1946–1958 → time unit = year, frequency = 12 readings/year.

In R: `ts(values, start=c(1946,1), frequency=12)`.

## The four components

Every time series can (roughly) be decomposed into:

1. **Trend** — long-term direction (rising, falling, flat).
2. **Trend cycles** — long, irregular swings (multi-year). May not be visible in short series.
3. **Seasonal pattern** — regular, repeating cycle (daily, weekly, yearly).
4. **Noise / random fluctuations** — what's left after removing the above.

R's `decompose()` or `stl()` will split a series into these components.

## Smoothing — extracting the trend

Smoothing methods are **low-pass filters** — they keep the slow-varying trend and filter out high-frequency noise.

### Simple Moving Average (SMA)

Average of `n` consecutive values, computed on a sliding window. All values weighted equally.

- Larger `n` → smoother curve, more lag, more visible trend.
- Smaller `n` → preserves more short-term fluctuation.

R: `forecast::ma(x, order=n)` or `TTR::SMA(x, n)`.

### Exponential smoothing

Like SMA, but the window weights **exponentially decay** — recent observations matter more.

`s_t = α · x_t + (1 − α) · s_{t-1}`, where `0 < α < 1`.

- Smaller `α` = more weight to past values, smoother.
- Larger `α` = follows recent values more closely.

### Holt-Winters

Three smoothing equations (one each for **level**, **trend**, **seasonal**), with smoothing parameters α, β, γ. Used when the series has trend and seasonality.

```r
fit <- HoltWinters(birth.ts)   # auto-fits α, β, γ
plot(fit)                      # observed vs filtered
forecast::forecast(fit, h=24)  # predict next 24 periods
```

## Additive vs. Multiplicative

| | Additive | Multiplicative |
|---|---|---|
| Form | `series = trend + cycle + noise` | `series = trend × cycle × noise` |
| Amplitude | Constant over time | Grows with the level |
| Examples | Births by year | Sales / volatile financial data |

If a series is multiplicative, `log(series)` makes it additive (since `log(a·b·c) = log(a) + log(b) + log(c)`). Then standard additive techniques apply.

## Stationary vs. Non-stationary

- **Stationary:** No change in trend or seasonal variation over time. The series "looks the same" in any window. Mean and variance roughly constant.
- **Non-stationary:** Has changing trend or seasonal component.

Most classical TS modeling (especially ARIMA) **assumes stationarity**. You need to make the series stationary before modeling.

### Differencing — making a series stationary

Compute differences between consecutive observations:

`y'_t = y_t − y_{t-1}`

This removes a level shift over time. Often makes a non-stationary series stationary.

- **Second-order differencing:** `y''_t = y'_t − y'_{t-1}` (when first-order isn't enough)
- **Seasonal differencing:** `y'_t = y_t − y_{t-m}` (where m = seasonal period — 12 for monthly w/ yearly seasonality)

## ARIMA — AutoRegressive Integrated Moving Average

The classic time-series modeling family. Works on **stationary** series; differencing handles the non-stationary case.

`ARIMA(p, d, q)`

- **p** — order of the **A**uto**R**egressive part (how many lagged observations)
- **d** — degree of **I**ntegration (how many differencing steps)
- **q** — order of the **M**oving Average part (how many lagged forecast errors)

```r
library(forecast)
fit <- auto.arima(birth.ts)         # auto-selects p, d, q
forecast(fit, h=24)                  # 24-period forecast
```

`auto.arima()` is the modern default — finds reasonable `(p, d, q)` automatically.

## Lagged variables

A lagged-variable linear model:

`Y_t = α + β₀·X_t + β₁·X_{t-1} + … + β_q·X_{t-q} + ε_t`

The lag operator: `L(X_t) = X_{t-1}`, `L_k(X_t) = X_{t-k}`. So `β_1` describes the effect of the predictor's value one time-step ago.

This generalizes regression for time-dependent data.

## Forecasting

A model fitted to historical data can produce **forecasts** for future periods, with confidence/prediction intervals (typically 80% and 95%).

```r
library(forecast)
fc <- forecast(HoltWinters(x), h=24)
plot(fc)   # forecast with shaded error bands
```

## Modern alternatives (verified 2026-05-03)

ARIMA is still actively used in 2025-2026 — production workhorse for **short-horizon univariate forecasting, low-data regimes, interpretable / regulated environments** (finance, supply chain, econometrics) and as the **standard baseline**. It has been displaced as the *only* tool, not as a tool. Other now-popular options:

- **Prophet** (Meta) — robust to missing data, outliers, complex seasonality / holidays
- **State-space models** (`KFAS`, `bsts`, structural time series) — flexible, handles regime changes
- **Deep learning** — N-BEATS, Temporal Fusion Transformer, DeepAR — useful when you have many related series with non-linear patterns and abundant data
- **Boosting on lag features** — XGBoost / LightGBM with engineered date features
- **Hybrid approaches** — ARIMA + ML residual models are increasingly common in production

## Common gotchas

- **Time-series data violates IID** — random K-fold CV is wrong. Use forward-chaining / rolling-origin CV.
- **Don't shuffle** before train/test split.
- **Forecasts get worse the further out you predict** — error bands widen for a reason.
- **Outliers / structural breaks** (regime changes) crater simple models — investigate first.
