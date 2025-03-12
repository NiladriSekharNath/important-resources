package com.adidas.hld.systemdesign.keyconcepts.consistenthashing.code;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ConsistentHashing {


  private final int numReplicas; // Number of virtual nodes per server
  private final TreeMap<Long, String> ring; // Hash ring storing virtual nodes
  private final Set<String> servers; // Set of physical servers

  public ConsistentHashing(List<String> servers, int numReplicas) {
    this.numReplicas = numReplicas;
    this.ring = new TreeMap<>();
    this.servers = new HashSet<>();

    // Add each server to the hash ring
    for (String server : servers) {
      addServer(server);
    }
  }

  public void addServer(String server) {
    servers.add(server);
    for (int i = 0; i < numReplicas; i++) {
      long hash = hash(server + "-" + i); // Unique hash for each virtual node
      ring.put(hash, server);
    }
  }

  public void removeServer(String server) {
    if (servers.remove(server)) {
      for (int i = 0; i < numReplicas; i++) {
        long hash = hash(server + "-" + i);
        ring.remove(hash);
      }
    }
  }

  /**
   *
   * @param key
   * @return
   *
   * so the idea of this is we are trying to get a server for any user in the logical rink
   *
   *
   * we have servers like S0, S1, S2, S3, S4, S5
   *
   * and each one has three replication virtual nodes like S0-0, S0-1, S0-2 to avoid un-event data distribution
   *
   * so we do this
   *
   * S0-0 -> S1-0... S0-1...S5-2
   *
   * NOW to perform the ring kind of idea we do this
   *
   * we do ciel in the treemap to get the next node and for KEYS appearing after the last node S5-2 we do the
   *
   * we do ring.firstEntry()
   *
   */

  public String getServer(String key) {
    if (ring.isEmpty()) {
      return null; // No servers available
    }

    long hash = hash(key);
    // Find the closest server in a clockwise direction
    Map.Entry<Long, String> entry = ring.ceilingEntry(hash);
    if (entry == null) {
      // If we exceed the highest node, wrap around to the first node
      entry = ring.firstEntry();
    }
    return entry.getValue();
  }

  /**
   * Generates a 32-bit hash value for a given key using the MD5 hashing algorithm.
   * This method converts the key to an MD5 digest, extracts the first 4 bytes,
   * and constructs a 32-bit long value by combining those bytes with bit manipulation.
   *
   * <p>The MD5 algorithm produces a 128-bit hash, but for simplicity and performance,
   * we only use the first 4 bytes to map keys onto the hash ring.</p>
   *
   * <h2>Hashing Process:</h2>
   * <ol>
   *   <li>Compute the MD5 hash of the input key.</li>
   *   <li>Extract the first 4 bytes of the digest.</li>
   *   <li>Convert each byte to an unsigned value using <code>& 0xFF</code> to avoid sign issues.</li>
   *   <li>Combine the 4 bytes into a single 32-bit long value using bitwise shifts.</li>
   * </ol>
   *
   * <h3>Handling Negative Byte Values:</h3>
   * In Java, bytes are signed (-128 to 127). When a byte is negative, using <code>& 0xFF</code> converts it to an unsigned value.
   * This prevents negative values from corrupting the final hash calculation.
   *
   * <h4>Example of Handling Negative Bytes:</h4>
   * <pre>
   * byte b = -1;                    // Binary: 11111111
   * int unsignedByte = b & 0xFF;   // Result: 255
   * </pre>
   * Without the <code>& 0xFF</code>, the negative byte would distort the final hash value.
   * Using the mask ensures each byte contributes a valid, non-negative number to the hash.
   *
   * <h3>Example Hash Calculation:</h3>
   * <pre>
   * Key: "UserA"
   * MD5 Hash (first 4 bytes): [0xD3, 0x45, 0x1A, 0xFF]
   *
   * Computed Hash Value:
   * (0xD3 & 0xFF) << 24 | (0x45 & 0xFF) << 16 | (0x1A & 0xFF) << 8 | (0xFF & 0xFF)
   * = (211 << 24) | (69 << 16) | (26 << 8) | 255
   * = 3547258623
   * </pre>
   *
   * @param key The input key to hash (e.g., a user ID or request key).
   * @return A 32-bit long hash value representing the position of the key on the hash ring.
   * @throws RuntimeException If the MD5 algorithm is not available.
   */

  private long hash(String key) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(key.getBytes());
      byte[] digest = md.digest();
      return ((long) (digest[0] & 0xFF) << 24) |
          ((long) (digest[1] & 0xFF) << 16) |
          ((long) (digest[2] & 0xFF) << 8) |
          ((long) (digest[3] & 0xFF));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("MD5 algorithm not found", e);
    }
  }

  public static void main(String[] args) {
    List<String> servers = Arrays.asList("S0", "S1", "S2", "S3", "S4", "S5");
    ConsistentHashing ch = new ConsistentHashing(servers, 3);

    // Step 2: Assign requests (keys) to servers
    System.out.println("UserA is assigned to: " + ch.getServer("UserA"));
    System.out.println("UserB is assigned to: " + ch.getServer("UserB"));

    // Step 3: Add a new server dynamically
    ch.addServer("S6");
    System.out.println("UserA is now assigned to: " + ch.getServer("UserA"));

    // Step 4: Remove a server dynamically
    ch.removeServer("S2");
    System.out.println("UserB is now assigned to: " + ch.getServer("UserB"));
  }
}

