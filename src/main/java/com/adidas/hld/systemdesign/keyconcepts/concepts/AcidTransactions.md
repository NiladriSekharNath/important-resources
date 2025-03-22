# ACID Transactions in Databases - Detailed Notes

## What is a Database Transaction?
A transaction in a database is a sequence of operations (such as insert, update, delete) that the database treats as a single unit of work. It either fully succeeds or fully fails, ensuring the database remains in a consistent state.

### Example: Bank Transfer
- Deduct money from Sender's account.
- Add money to Receiver's account.
- If either step fails, the entire transaction is rolled back to prevent inconsistencies.

## ACID Properties
ACID stands for Atomicity, Consistency, Isolation, and Durability—four essential properties that ensure reliable database transactions.

### 1. Atomicity
Ensures a transaction is all-or-nothing: either all operations succeed, or none are applied.

#### How Atomicity is Implemented:
1. **Write-Ahead Logging (WAL):**
    - Every change is first written to a log before modifying the actual database.
    - If a failure occurs, the database can recover using the log.
2. **Commit/Rollback Protocols:**
    - `BEGIN TRANSACTION` marks the start.
    - `COMMIT` ensures changes are saved permanently.
    - `ROLLBACK` reverts all changes in case of failure.

### 2. Consistency
Ensures that a transaction moves the database from one valid state to another, maintaining data integrity.

#### How Consistency is Maintained:
- **Constraints** (e.g., NOT NULL, UNIQUE, PRIMARY KEY, FOREIGN KEY)
- **Triggers & Stored Procedures** (to enforce business rules)
- **Application-Level Safeguards** (validations before data reaches the database)

### 3. Isolation
Ensures that concurrently running transactions do not interfere with each other, preventing anomalies.

#### Concurrency Anomalies:
1. **Dirty Read:** Transaction A reads uncommitted data from Transaction B, which may be rolled back.
2. **Non-Repeatable Read:** Transaction A reads a row twice, but another transaction modifies it in between.
3. **Phantom Read:** Transaction A retrieves a set of rows, but another transaction inserts/deletes matching rows before A re-executes the query.

#### Isolation Levels:
- **Read Uncommitted:** Allows dirty reads.
- **Read Committed:** Prevents dirty reads but allows non-repeatable reads and phantom reads.
- **Repeatable Read:** Prevents dirty and non-repeatable reads but allows phantom reads.
- **Serializable:** Ensures full isolation by making transactions execute sequentially.

#### How Isolation is Implemented:
1. **Locking (Pessimistic Concurrency Control):**
    - Rows or tables are locked to prevent concurrent modifications.
    - Can cause blocking or deadlocks.
2. **MVCC (Multi-Version Concurrency Control):**
    - Creates multiple versions of data instead of blocking reads.
    - Readers see a snapshot of data at a given point in time.
3. **Snapshot Isolation:**
    - Each transaction sees a consistent snapshot.
    - Prevents non-repeatable reads and dirty reads but may allow phantom reads.

### 4. Durability
Ensures that once a transaction is committed, it is permanently saved—even in case of system failures.

#### How Durability is Implemented:
1. **Write-Ahead Logging (WAL):**
    - Changes are recorded in a log before updating the main database.
    - If the database crashes, the log is used for recovery.
2. **Replication:**
    - **Synchronous Replication:** Changes are copied to multiple nodes before committing.
    - **Asynchronous Replication:** Changes are copied later, leading to a slight risk of data loss.
3. **Backups:**
    - **Full Backups:** Capture the entire database.
    - **Incremental Backups:** Save only the changes since the last backup.
    - **Off-Site Storage:** Ensures safety from disasters.

## Summary
| Property     | Ensures | Implementation Methods |
|-------------|---------|------------------------|
| Atomicity   | All or nothing transactions | WAL, Commit/Rollback |
| Consistency | Valid state transitions | Constraints, Triggers, Application checks |
| Isolation   | Transactions don’t interfere | Locking, MVCC, Snapshot Isolation |
| Durability  | Changes are permanent | WAL, Replication, Backups |

ACID transactions are essential for ensuring data integrity and reliability in databases, especially in applications like banking, e-commerce, and enterprise systems.

## Resources
   - [Acid Transactions in databases](https://blog.algomaster.io/p/what-are-acid-transactions-in-databases)