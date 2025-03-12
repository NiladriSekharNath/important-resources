# Consistent Hashing Explained

## Why Traditional Hashing Doesn’t Work in Distributed Systems

In distributed systems, nodes (servers) can be added or removed dynamically, making efficient request routing a challenge. The traditional approach of using:

```
Hash(key) mod N
```

Where **N** is the number of servers, breaks down because:

- **Adding a server:** Changes the modulus, causing massive remapping of keys.
- **Removing a server:** Also changes the modulus, leading to high rehashing overhead.

This can cause session loss, cache invalidation, and system performance issues.

### Consistent Hashing to the Rescue

Consistent hashing solves this by only reassigning a small subset of keys when nodes change, rather than remapping everything. Popularized by Amazon Dynamo, it's widely used in systems like Cassandra and DynamoDB.

Let’s break it down step by step!

---

## 1. How Consistent Hashing Works

It uses a **circular hash space** (0 to 2³² - 1) with both servers and keys mapped onto a ring using a hash function.

- **Hash ring:** A circular space where hash values wrap around.
- **Mapping servers:** Each server is assigned a position on the ring via its hash.
- **Mapping keys:** Keys are also placed on the ring based on their hash.
- **Routing requests:** A key is assigned to the next clockwise server on the ring.

This way, adding or removing servers only affects nearby keys, minimizing data movement.

### 1.1 Constructing the Hash Ring

1. **Define the hash space:** A fixed range (e.g., 0 to 2³² - 1).
2. **Place servers on the ring:** `Hash(server_id)` determines the position.
3. **Place keys on the ring:** `Hash(key)` assigns the key’s position.
4. **Assigning keys:** Move clockwise to find the next server.

Example: `UserA` → Hash value `200`, assigned to server `S3`.

### 1.2 Adding a Server

- Add server `S5`, and place it on the ring.
- `S5` takes over keys that fall between `S1` and `S5`.
- Only those affected keys move, not all keys.

### 1.3 Removing a Server

- Remove server `S4`.
- Keys assigned to `S4` shift to the next server (`S3`).
- Again, only a small fraction of keys are remapped.

---

## 2. Virtual Nodes (VNodes)

In simple consistent hashing, uneven distribution can happen:

- Fewer servers lead to load imbalances.
- Servers may cluster together, creating hot spots.

**Solution:** Use **virtual nodes** to split servers into multiple virtual positions on the ring.

- Each server gets hashed multiple times (e.g., `S1-1`, `S1-2`, `S1-3`).
- Requests are assigned to the nearest virtual node.
- Load becomes evenly spread, and node failures cause less disruption.

Example:

```
S1-1 → Position 10
S1-2 → Position 70
S1-3 → Position 120
```

If `S1` fails, its keys are spread across many servers instead of just one.

---

## 3. Key Takeaways

- **Scalability:** Easy to add/remove servers with minimal disruption.
- **Fault Tolerance:** Virtual nodes prevent load spikes when servers fail.
- **Efficiency:** Only a small subset of keys need to move during scaling events.

Consistent hashing is a powerful technique that underpins modern distributed systems, ensuring they can grow, shrink, and stay resilient without collapsing under the weight of their own complexity.

## Working Code 

- [ConsistentHashing.java](../code/ConsistentHashing.java)

## Resource Links 

- [Consistent-Hashing](https://blog.algomaster.io/p/consistent-hashing-explained)

