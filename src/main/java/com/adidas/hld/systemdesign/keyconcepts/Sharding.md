# Database Sharding — Detailed Revision Notes

## 📘 Introduction

Imagine a social media platform like Instagram with over 1 billion users. Storing all user data on a single server would quickly cause scalability and performance issues. A better approach is to split users into smaller groups and store each group on separate servers.

Example:
- Group 1: Users with IDs 0–999
- Group 2: Users with IDs 1000–1999
- Group 3: Users with IDs 2000–2999

This concept is known as **Database Sharding**.

---

## 🔑 What is Database Sharding?

**Database sharding** is a **horizontal scaling** technique where a large database is split into smaller, independent pieces called **shards**. Each shard is stored on a different server and handles a subset of the total data.

**Used by:**
- **Social Networks** (e.g., Instagram) → billions of user profiles.
- **E-commerce Platforms** (e.g., Amazon) → huge product catalogs.
- **Search Engines** (e.g., Google) → billions of web pages.
- **Gaming Platforms** → millions of players and vast game data.

---

## 🚀 Benefits of Database Sharding

- **Improved Performance** → Queries hit only relevant shards, reducing server load.
- **Scalability** → New shards can be added as data grows.
- **High Availability** → Failure of one shard doesn’t bring down the entire system.
- **Geographical Distribution** → Place shards closer to users to reduce latency.
- **Cost Efficiency** → Scale with commodity hardware instead of expensive vertical scaling.

---

## 🛠️ How Sharding Works

Sharding relies on several components:

- **Sharding Key:** Determines which shard a piece of data belongs to (e.g., `userId`).
- **Data Partitioning:** Splitting data into shards based on the shard key.
- **Shard Mapping:** Mapping shard keys to their respective shard locations.
- **Shard Management:** Oversees shard distribution, data consistency, and integrity.
- **Query Routing:** Directs incoming queries to the appropriate shard.

---

## ⚡ Sharding Strategies

1. **Hash-Based Sharding:**
    - Data is distributed using a hash function.
    - Example: `Hash(user_id) % 2` distributes users across 2 shards.

2. **Range-Based Sharding:**
    - Data is split into ranges.
    - Example: IDs 1–10,000 in Shard 1, IDs 10,001–20,000 in Shard 2.

3. **Geo-Based Sharding:**
    - Data is split based on geographic location.
    - Example: North American users → Shard 1, European users → Shard 2.

4. **Directory-Based Sharding:**
    - Uses a lookup table to map specific keys to shards.

---

## ⚠️ Challenges with Database Sharding

- **Complexity:** Adds operational overhead.
- **Data Consistency:** Harder to maintain across shards.
- **Cross-shard Joins:** Expensive and complex.
- **Data Rebalancing:** Shards may become uneven, requiring redistribution.

---

## ✅ Best Practices for Implementing Sharding

- **Choose the Right Sharding Key:** Ensure even data distribution.
- **Use Consistent Hashing:** Reduces data movement when adding/removing shards.
- **Monitor & Rebalance:** Regularly check shard health and redistribute data if needed.
- **Optimize Cross-Shard Queries:** Use techniques like query federation or denormalization.
- **Plan for Scalability:** Design with future growth in mind.

---

## 🔍 Horizontal Sharding vs Vertical Sharding

| Aspect                     | Horizontal Sharding                                                                 | Vertical Sharding                                                        |
|---------------------------|--------------------------------------------------------------------------------------|--------------------------------------------------------------------------|
| **Definition**            | Splitting data across multiple servers by rows (each shard contains a subset of rows).| Splitting data across multiple servers by columns (each shard contains specific columns). |
| **Scaling Type**          | Horizontal scaling (more servers, more capacity).                                      | Vertical scaling (splitting schema into smaller, manageable parts).      |
| **Example**               | Users with ID 1–1000 → Shard 1, ID 1001–2000 → Shard 2.                               | User profile data in one shard, user activity logs in another shard.     |
| **Performance Impact**    | Improves read and write throughput.                                                   | Optimizes queries that only access specific columns.                    |
| **Complexity**            | More complex to implement and manage (routing, balancing).                            | Simpler but may not solve scalability issues as well as horizontal sharding. |
| **Common Use Cases**      | Social media, e-commerce, large-scale applications.                                    | Applications with distinct feature sets, microservices with separate domains. |

---

## 🏁 Conclusion

Database sharding is a powerful horizontal scaling technique, essential for handling massive data loads. However, it introduces complexity and requires thoughtful planning around access patterns and growth.

Before sharding, carefully evaluate whether the benefits outweigh the challenges — sometimes, simpler solutions might suffice!

## Notes Reference 

- [Database Sharding](https://blog.algomaster.io/p/what-is-database-sharding)


