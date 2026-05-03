# 03 — Statistical Inference

> **Source:** `Notes-IntroStatInf.pdf` (Gordon Anderson).
> **See also:** `Activity-BasicStatsAndPlots.Rmd` (R worked examples).

## Why statistical inference?

- **To infer:** make a well-informed guess about something you can't measure exhaustively.
- We must infer because the world is **stochastic** (varies) and our methods have **bias**.
- A definite calculation is rarely possible because of variance and measurement bias.

## The inference process

1. Make observations / collect data.
2. Analyze — build models, evaluate them.
3. Draw a conclusion — has the question been answered?
4. Communicate results (graphs, charts).

You will iterate; later steps often send you back.

## Population vs. Sample

- **Population:** the full set you want to draw conclusions about (e.g., all students in the course).
- **Sample:** the subset you actually measure (e.g., 9 of 35 students).
- Samples are nearly always **biased** — they may not represent the population. Repeated random sampling helps.
- Beware scope: "all students this semester" may not generalize to "all students who ever take the course."

## Descriptive statistics

For a sample like heights `(167, 170, 155, 186, 160, 163, 158, 157, 166)`:

| Stat | Meaning |
|------|---------|
| **min / max** | smallest, largest |
| **range** | max − min |
| **mean** | arithmetic average |
| **median** | middle value (Q2) |
| **quartiles (Q1, Q2, Q3)** | values that split sorted data into 25/50/75% |
| **IQR** | Q3 − Q1 (middle 50%) |

R: `summary(x)` gives Min / 1st Qu / Median / Mean / 3rd Qu / Max.

## Distribution shape

- **Symmetric (normal-like):** quartiles spaced evenly around the median.
- **Skewed:** quartile widths differ; median ≠ mean.

## Variance and Standard Deviation

- **Variance (σ²):** measure of how widely data points are dispersed *relative to the mean*.
- **Standard deviation (σ or SD or s):** square root of variance, in the **same units as the data**. Use this when communicating spread.
- R: `sd(x)`.

### Normal distribution rule of thumb (68 / 95 / 99.7)

For a normal distribution:
- ~68% of values fall within **±1 SD** of the mean
- ~95% within **±2 SD**
- ~99.7% within **±3 SD**

Example with the heights: mean ≈ 164.7, SD ≈ 9.43 → 68% expected in [155, 174] cm.

## Visualization basics

- `plot(x)` — basic scatter
- Add labels: `plot(x, main="Title", xlab="...", ylab="...")`
- Histograms (`hist`), bar plots (`barplot`) are next.

## Bias is everywhere

- A small sample may not reflect the population.
- The choice of *what* to measure introduces bias.
- The instrument introduces bias.
- Always state your assumptions and threats to validity.

> "Data is not objective." — recurring theme in the source material.
