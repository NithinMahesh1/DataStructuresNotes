# 07 — Relational Databases

> **Source:** `Notes/RelationalDatabases.pdf` (Gordon Anderson).

## What's "relational"?

- Most real-world data is stored in **tables** (rows × columns).
- The term **relational** describes the *structure* of the data: each table is a **relation**, each row a **tuple** (a set of related observations about one instance).
- Multiple tables are linked by shared keys (e.g., `CustomerID`).

Example tables (from the course): `Customer`, `Invoice`, `Line`, `Product`, `Supplier` — each related via foreign keys.

## Why use a relational database?

- Process **ad-hoc queries** with SQL.
- **SQL** is a widely accepted standard → reduces dev time, improves interoperability.
- Mature, battle-tested technology.
- High **data integrity** and security (constraints, transactions).
- Protection against system failures and concurrent operations.
- Web and mobile software ecosystems all connect to relational DBs.
- Indexing brings high-speed access.
- Efficient storage.
- Security via permissioned views.

## Relational DB Management Systems (RDBMS)

The course used **SQLite** — file-based, lightweight, easy to install. Not "industrial strength" (vs. Postgres, MySQL, SQL Server, Oracle) but solid for learning and many production cases.

## SQL + R

> **Heads-up (verified 2026-05-03):** `RSQLite` (active, v2.4.6, Feb 2026) had a **breaking change in v2.3.7 (2024)** — duplicate column names in `dbGetQuery()` results are no longer auto-suffixed (`col`, `col.1` → both become `col`). Workaround: alias columns explicitly in your SELECT. Also note the legacy `dbGetPreparedQuery` / `dbSendPreparedQuery` are deprecated — use `dbSendQuery` + `dbBind` + `dbFetch` instead.

The R package **`RSQLite`** lets R connect to a SQLite `.db` file:

```
R + RSQLite  →  DB Driver  →  SQLite .db file
```

Workflow:
1. R uses `RSQLite` to create / connect to a `.db`.
2. SQL queries run against the DB.
3. Results return into R as a **data frame** for analysis / plotting.

The course mostly read from existing databases, but RSQLite also supports creating, inserting, and updating.

## Web-scraping → relational data

Common pattern in the course material:
1. Client program calls a website API.
2. Receives serial stream of records (often **JSON**).
3. Stores them as rows in a relational DB via SQL.
4. Later, queries the DB for analysis.

Example: Twitter tweets have many attributes (metadata) that map well into relational rows.

## Key concepts to recall

- **Relation = table.** **Tuple = row.** **Attribute = column.**
- **Primary key** uniquely identifies a row in its table (e.g., `CustomerID INT`).
- **Foreign key** in one table refers to a primary key in another (e.g., `Invoice.CustomerID` → `Customer.CustomerID`).
- **Constraints** enforce relationships.
- **Indexing** speeds up lookup at the cost of write speed and storage.
- **Highly structured data → easier integrity, easier querying.**

> The course doesn't cover SQL syntax in depth. For modern SQL reference, see `~/Files/Learning/Linux/` if relevant or any standard SQL primer.
