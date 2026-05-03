# 01 — Introduction to Data Science

> **Source:** `Notes-IntroToDS.pdf` (Gordon Anderson, INFO 397F).
> **See also:** `Doing Data Science.pdf` (textbook, not summarized here).

## What is Data Science?

A few of the working definitions used in the course:

- **Dhar & Leek (2013):** Interdisciplinary field about processes/systems to extract knowledge from large volumes of structured or unstructured data. A continuation of statistics, data mining, predictive analytics, and KDD (Knowledge Discovery in Databases).
- **NIST:** "The empirical synthesis of actionable knowledge from raw data through the complete data lifecycle process."
- **Drew Conway's Venn diagram (2013):** Data Science = intersection of *hacking skills*, *math & stats knowledge*, and *substantive (domain) expertise*.
- **Quote (Josh Wills, 2012):** "Person who is better at statistics than any software engineer and better at software engineering than any statistician."

The slogan: **"The key word in 'Data Science' is not Data, it is Science."**

> **How the role has fragmented since 2019** (verified 2026-05-03)
> The 2013 definitions still describe the *Data Scientist* role accurately, but the umbrella has split:
> - **Data Scientist** — insights, modeling, statistics (the original role)
> - **ML Engineer** — productionizing models, scalable training/serving
> - **Analytics Engineer** — warehouse transformation layer, formalized ~2020 with the dbt ecosystem
> - **AI Engineer** — LLM-app builder, RAG/eval/agents on top of foundation models, emerged ~2023
>
> Conway's Venn diagram now arguably maps to four overlapping roles rather than one.

## Data Science vs. Big Data

- Big Data = *ability* to collect massive amounts of data.
- Data science ≠ big data and doesn't require it.
- DS has been done for decades; it became *popular* because of big data.

> **Term has aged (verified 2026-05-03):** "Big Data" peaked ~2014–2017 and has largely been displaced in industry framing by **lakehouse architecture** (Databricks, 2020), **cloud-native data platforms**, **MLOps / DataOps**, and post-2023 **GenAI / agentic data** language. The "3 Vs" (Volume / Velocity / Variety) framing rarely appears as a headline anymore.

## Data Science Process / Lifecycle

```
Raw Data Collected → Data Processed → Clean Dataset → [Exploratory Data Analysis ↔ Models & Algorithms]
   → Communicate / Visualize / Report → Make Decisions → (data product loops back to the world)
```

The course focuses on the EDA → Models/Algorithms → Communicate slice.

A **data product** = any tool or view created with data to support a more informed decision. Three flavors:
- **Descriptive** — what happened
- **Predictive** — what will happen
- **Prescriptive** — what should we do

## Data Units

Bit → Byte → KB (10³) → MB (10⁶) → GB (10⁹) → TB (10¹²) → PB (10¹⁵).

Course-era reference points and where they stand now:

| Course-era figure | Current status (verified 2026-05-03) |
|---|---|
| **Wikipedia DB** (Jan 2010): "5.87 TB SQL dataset" | The original number was likely conflating full-history-with-media vs. articles-only. As of 2024, the English Wikipedia articles dump is **~24 GB compressed**; full history with all Wikimedia media is **>400 TB**. |
| **IBM Watson** (Feb 2011 Jeopardy!): 16 TB RAM | ✅ Accurate. Watson's build: 90 IBM Power 750 servers, 2,880 POWER7 cores, 16 TB RAM, 21.6 TB disk. Knowledge loaded entirely into RAM because disk was too slow. A modern LLM inference node achieves comparable Q&A on much less hardware. |
| **Facebook** (Jan 2013): "960 billion images, ~357 PB" | The original figure doesn't match Facebook's own disclosures. Closer truth (Dec 2012): **~220 billion photos, >100 PB**. By the mid-2020s, Meta operates **multi-exabyte** cold-storage data centers and ingests petabytes per day. |

## Data ≠ Information

- **Data** = recorded observation of a quantifiable value.
- **Information** = produced by analyzing data; supports decisions.
- A column of numbers is data. A labeled, plotted, contextualized chart of those numbers is information.

## Data Structure

Three rough levels of structure:

| Type | Example | Notes |
|------|---------|-------|
| **Unstructured** | Free text, raw audio, images | No imposed schema |
| **Semi-structured** | XML, HTML, JSON | Hierarchical, has metadata/tags |
| **Structured** | Tabular (CSV, SQL tables) | Rows = tuples, columns = typed observations |

Most analysis in the course uses structured/tabular data.

## Data Types (per column)

- **Numeric (continuous)** — real-valued measurements.
- **Categorical (discrete levels)** — even when stored as numbers (e.g., `Sex` coded 0/1 is still categorical with levels you can label "female"/"male"). In R you make these `factor`s.
- Common gotcha: a column like *number of cylinders* looks numeric but is really categorical — treat as factor before summarizing.

## Why "structure" matters

Adding structure / metadata to data is what makes it interpretable. The more structure, the better the **data integrity** controls you can apply.
