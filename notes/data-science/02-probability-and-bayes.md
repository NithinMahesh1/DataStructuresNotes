# 02 — Probability and Bayes Rule

> **Source:** `Notes/ProbabilityAndBayesRule (1).pdf` (Gordon Anderson).
> **See also:** `Notes/NaiveBayesClassifier (1).pdf`, `Activity-Naive Bayes.Rmd`.

## Probability basics

- **Random variable (X):** a variable whose value is the result of a random process.
- **Sample space (S):** the set of all possible outcomes.
- **Probability:** `P(X = outcome) = (ways it can occur) / (all possible outcomes)`. Implies a defined sample space.
- **Sum to 1:** Probabilities of all events in a sample space sum to 1.
- **Cumulative probability:** e.g., `P(grade ≥ 50)`.

Standard notation: `P(X = 7)`, `P(color = green)`, `P(spam)`.

## Independent vs. Dependent Events

- **Independent:** Event A's occurrence has no bearing on B. Example: consecutive dice rolls.
- **Dependent:** A's occurrence affects B's probability. Example: drawing without replacement.

| Case | Joint probability |
|------|-------------------|
| Independent | `P(A and B) = P(A) × P(B)` |
| Dependent (A first) | `P(A and B) = P(A) × P(B after A happened)` |

## Sampling

- **With replacement:** observed sample is returned to the pool. Successive draws are independent.
- **Without replacement:** observed sample is removed. Later draws are dependent on earlier ones (denominator shrinks).

## Joint and Conditional Probability

- **Joint probability** (all equivalent notations):
  `P(A and B) = P(A ∩ B) = P(A, B) = P(AB)`
- **Conditional probability:** `P(A | B) = P(A, B) / P(B)`
- Rearranged: `P(A, B) = P(A | B) × P(B)`

Example from notes — joint probability via a contingency table of `student major × home location`. Cell value / grand total = joint probability.

## Conditional Independence (the "naive" assumption)

- If A and B are independent: `P(A | B) = P(A)`.
- If A is independent of B given C: `P(A | B, C) = P(A | C)`.
- For a joint of four variables, the chain rule:
  `P(A, B, C, D) = P(A | B,C,D) · P(B | C,D) · P(C | D) · P(D)`
- Under the **naive** assumption (A, B, C all independent given D):
  `P(A, B, C, D) = P(A | D) · P(B | D) · P(C | D) · P(D)`

This is the assumption that makes the **Naive Bayes classifier** tractable (see `12-naive-bayes.md`).

## Bayes Rule

`P(theory | evidence) = [P(evidence | theory) · P(theory)] / P(evidence)`

Spam-classifier framing:
- **Theory:** an email is spam (or not).
- **Evidence:** observed words (e.g., "viagra").

`P(spam | word="viagra") = [P(word="viagra" | spam) · P(spam)] / P(word="viagra")`

Denominator (law of total probability):
`P(word) = P(word | spam) · P(spam) + P(word | ¬spam) · P(¬spam)`

## Why Bayes matters for classification

- **Prior** `P(theory)` — what we believe before seeing evidence.
- **Likelihood** `P(evidence | theory)` — how well the theory predicts what we saw.
- **Posterior** `P(theory | evidence)` — updated belief after seeing evidence.

When comparing two theories (spam vs not spam) for the *same evidence*, the denominator is identical for both, so it can be dropped — you just compare `P(evidence | theory) · P(theory)` and pick the larger.
