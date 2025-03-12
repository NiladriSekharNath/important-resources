**CQRS Design Pattern in Microservices Architectures - Summary & Notes**

**What is CQRS?**
- Stands for **Command and Query Responsibility Segregation**.
- Separates read and update operations for a database to avoid complex joins and inefficiencies.

**The Problem in Monolithic Systems:**
- A single database handles both reads and writes.
- Complex queries (e.g., joining 10+ tables) can lock the database and cause latency.
- CRUD operations may involve heavy validations and business logic, further impacting performance.

**How CQRS Solves This:**
- Split the system into two databases:
    - **Write Database:** Handles updates and CRUD operations.
    - **Read Database:** Optimized for queries, can use different schemas or NoSQL databases.

**Example:**
- **Instagram:** Uses PostgreSQL for user bio updates (writes) and Cassandra for user stories (reads).

**Key Concepts:**
1. **Commands:** Perform write operations (e.g., add item to cart, checkout order).
2. **Queries:** Fetch and return data (always read-only, return DTOs).
3. **Materialized View Pattern:** Pre-compute views to avoid complex joins during reads.

**Syncing Read & Write Databases:**
- Use **Event-Driven Architecture** with a message broker (e.g., Kafka).
- **Publish/Subscribe Pattern:** Write DB publishes events, read DB consumes and updates.
- **Eventual Consistency:** Data sync is asynchronous, so there might be a delay in reflecting updates.

**Scalability & Performance:**
- Scale read and write databases independently.
- Useful for **read-intensive** applications where queries far outnumber writes.

**Designing with CQRS:**
1. Split databases:
    - **SQL Server (Write DB)** for relational operations.
    - **Cassandra (Read DB)** for fast, schema-less queries.
2. Sync with **Kafka** for reliable event streaming.
3. Combine with **Event Sourcing** to store state as a sequence of events.

**When to Use CQRS:**
- When read and write workloads differ significantly.
- To optimize complex queries and heavy business logic.
- When scalability and independent optimization of reads/writes are required.

**Tech Stack Example:**
- Write DB: **SQL Server**
- Read DB: **Cassandra**
- Message Broker: **Kafka**

**Resources**
- [Design Microservices Architecture with CQRS](https://medium.com/design-microservices-architecture-with-patterns/cqrs-design-pattern-in-microservices-architectures-5d41e359768c)
