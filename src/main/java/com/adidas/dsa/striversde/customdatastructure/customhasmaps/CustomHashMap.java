package com.adidas.dsa.striversde.customdatastructure.customhasmaps;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @param <K> any key
 * @param <V> any value
 */
@Slf4j
public class CustomHashMap<K,V> {

  /**
   * 1<<4 -> 2^4 = 16
   */
  private static final int INITIAL_CAPACITY = 1<<4;

  private int size ;
  private int capacity  ;

  private float loadFactor ;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;
  private static final int MAXIMUM_CAPACITY = 1<<30;

  private Entry<K,V>[] hashTable = null;

  public CustomHashMap(){
    this(INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public CustomHashMap(int capacity, float loadFactor){
    if(capacity < 0){
      throw new IllegalArgumentException("Capacity must be positive");

    }
    if(loadFactor <= 0 || loadFactor > 1){
      throw new IllegalArgumentException("Load factor must be between 0 and 1");
    }

    this.capacity = getSizeForCapacity(capacity);
    this.loadFactor = loadFactor;

    hashTable = new Entry[capacity];
  }



  public V put(K key, V value){
    int index = hashIndex(key);

    ensureCapacity();

    Entry<K,V> newEntry = new Entry<>(key, value);

    if(hashTable[index] == null){
      hashTable[index] = newEntry;
      this.size++;
      return value;
    }

    Entry<K, V> prevousEntry = null, currentEntry = hashTable[index];


    while(currentEntry != null){
      if(currentEntry.key.equals(key)){
        currentEntry.value = value;
        return value;
      }
      prevousEntry = currentEntry;
      currentEntry = currentEntry.next;
    }

    prevousEntry.next = newEntry ;

    this.size++;

    return value;
  }

  public V get(K key){
    int index = hashIndex(key);

    Entry<K,V> currentEntry = hashTable[index];

    while(currentEntry != null){
      if(currentEntry.key.equals(key)){
        return currentEntry.value;
      }
      currentEntry = currentEntry.next;
    }

    return null;

  }

  public void ensureCapacity() {
    if (size < capacity * loadFactor) {
      return;
    }

    /**
     * Current Capacity limit reached so we are resizing the hashTable
     */
    int newCapacity = 2 * capacity;

    log.info("Current Capacity limit reached so resizing the hashTable");

    if(newCapacity > MAXIMUM_CAPACITY)
      throw new UnsupportedOperationException("Maximum Capacity of HashMap Reached");

    log.info("Previous Size = {}, Current Size = {}", capacity, newCapacity);


    Entry<K, V>[] newHashTable = new Entry[newCapacity];

    this.capacity = newCapacity;


    for (Entry<K, V> eachEntry : hashTable) {
      Entry<K,V> currentEntry = eachEntry;
      while (currentEntry != null) {
        int newIndex = hashIndex(currentEntry.key);
        newHashTable[newIndex] = eachEntry;
        currentEntry = currentEntry.next;
      }
    }

    this.hashTable = newHashTable;
  }


  @Override
  public String toString(){
    StringBuffer resultTableBuffer = new StringBuffer();

    resultTableBuffer.append("[");

    for(Entry<K,V> eachEntry : hashTable){
      Entry<K,V> currentEntry = eachEntry;
      while(currentEntry != null){
        resultTableBuffer.append(String.format("%s -> %s, ", currentEntry.key, currentEntry.value));
        currentEntry = currentEntry.next;
      }
    }

    int lastCommaIndex = resultTableBuffer.lastIndexOf(",");

    resultTableBuffer.setLength(lastCommaIndex);

    resultTableBuffer.append("]");

    return resultTableBuffer.toString();
  }

  private int hashIndex(Object key){
    return key == null ? 0 : Math.abs(key.hashCode()) % capacity;
  }

  /**
   *
   * @param capacity given by user
   * @return get the correct table size so that we can ensure correct table size
   *
   *
   * Let's break this down step by step to understand what is happening when you
   * provide different values like `12`, `19`, or `6` to the `tableSizeFor()` method.
   *
   * ---
   *
   * ### 1. Purpose of `tableSizeFor()`
   * The `tableSizeFor()` method is used to calculate the next power of 2 greater than or
   * equal to the given capacity (`cap`). This is because the internal bucket array of a `HashMap`
   * always has a size that is a power of 2, ensuring efficient key distribution and bitwise masking.
   *
   * ---
   *
   * ### 2. The Role of `Integer.numberOfLeadingZeros()`
   * The `numberOfLeadingZeros()` method counts how many leading `0`s are present in the 32-bit binary
   * representation of an integer. This is useful to determine the position of the most significant `1` bit in the number.
   *
   * The implementation of `numberOfLeadingZeros()` does this efficiently by
   * checking chunks of bits (16 bits, 8 bits, etc.), reducing the number of operations.
   *
   * ---
   *
   * ### 3. Breaking Down `tableSizeFor(cap)`
   * ```java
   * int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
   * return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
   * ```
   *
   * 1. cap - 1`:** Subtracting 1 ensures that if `cap` is already a power of 2,
   * it doesn't return the next power of 2 (e.g., for `16`, `16 - 1 = 15`).
   *
   * 2. Integer.numberOfLeadingZeros(cap - 1)`:** Finds the position of the most significant bit in `cap - 1`.
   *
   * 3. -1 >>> Integer.numberOfLeadingZeros(cap - 1)
   *
   * Right shifts all bits of `-1` (which is all `1`s in binary) by the number of leading `0`s.
   * This gives a mask with all bits set to `1` from the most significant bit of `cap - 1` onward.
   * For example:
   *    - If `cap - 1 = 12`, `cap - 1` in binary is `0000 0000 0000 0000 0000 0000 0000 1011`.
   *    This results in a mask of `0000 0000 0000 0000 0000 0000 0000 1111` (15 in decimal).
   *
   * 4. **`n + 1`:** Adds 1 to ensure the size is the next power of 2.
   *
   * ---
   *
   * ### 4. Example Walkthroughs
   *
   * #### Case 1: `cap = 12`
   * 1. `cap - 1 = 11` (`0000 0000 0000 0000 0000 0000 0000 1011`).
   * 2. `numberOfLeadingZeros(11) = 28` (there are 28 leading `0`s in a 32-bit integer).
   * 3. `n = -1 >>> 28 = 15` (`0000 0000 0000 0000 0000 0000 0000 1111` in binary).
   * 4. `n + 1 = 16`. So, the result is **16**.
   *
   * #### Case 2: `cap = 19`
   * 1. `cap - 1 = 18` (`0000 0000 0000 0000 0000 0000 0001 0010`).
   * 2. `numberOfLeadingZeros(18) = 27` (there are 27 leading `0`s).
   * 3. `n = -1 >>> 27 = 31` (`0000 0000 0000 0000 0000 0000 0001 1111` in binary).
   * 4. `n + 1 = 32`. So, the result is **32**.
   *
   * #### Case 3: `cap = 6`
   * 1. `cap - 1 = 5` (`0000 0000 0000 0000 0000 0000 0000 0101`).
   * 2. `numberOfLeadingZeros(5) = 29` (there are 29 leading `0`s).
   * 3. `n = -1 >>> 29 = 7` (`0000 0000 0000 0000 0000 0000 0000 0111` in binary).
   * 4. `n + 1 = 8`. So, the result is **8**.
   *
   * ---
   *
   * ### Summary of Results
   * - For `cap = 12`, the result is **16**.
   * - For `cap = 19`, the result is **32**.
   * - For `cap = 6`, the result is **8**.
   *
   * ---
   *
   * ### Why This Matters
   * Using powers of 2 for the HashMap's bucket size ensures efficient hashing and minimizes collisions.
   * The `tableSizeFor()` function helps achieve this by determining the smallest power of 2
   * that is greater than or equal to the requested size.
   */

  private int getSizeForCapacity(int capacity){
    int n = -1 >>> getNumberOfLeadingZeroes(capacity - 1);
    return n < 0 ? 1 : (n >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : n + 1);
  }

  /**
   *
   * The method `numberOfLeadingZeros(int i)` is designed to count how many leading zeros are present in the
   * 32-bit binary representation of an integer `i`. Let’s break it down step by step to understand how it works.
   *
   * ---
   *
   * ### 1. **Edge Case Handling**
   * ```java
   * if (i <= 0)
   *     return i == 0 ? 32 : 0;
   * ```
   * - If `i` is **0**, the binary representation is all zeros: `0000 0000 0000 0000 0000 0000 0000 0000`.
   * There are **32 leading zeros**, so the method returns `32`.
   * - If `i` is **negative**, its binary representation in **two's complement** starts with `1`
   * (no leading zeros). So, the method returns `0`.
   *
   * ---
   *
   * ### 2. **Initialize the Leading Zero Count**
   * ```java
   * int n = 31;
   * ```
   * - The variable `n` starts at 31 because the maximum possible number of leading zeros
   * in a 32-bit integer is 31 (for numbers like `1`, whose binary is `0000 0000 0000 0000 0000 0000 0000 0001`).
   *
   * ---
   *
   * ### 3. **Iterative Reduction**
   * The method uses **bit-shifting** and checks to progressively reduce the number
   * of leading zeros by considering chunks of bits (16, 8, 4, 2, 1). Let’s look at each step:
   *
   * #### Step 1: Check if the Top 16 Bits Are Non-Zero
   * ```java
   * if (i >= 1 << 16) { n -= 16; i >>>= 16; }
   * ```
   * - `1 << 16` is `2^16` (i.e., `0x0001_0000` or `0000 0000 0000 0001 0000 0000 0000 0000` in binary).
   * - If `i >= 2^16`, it means there is at least one `1` bit in the **top 16 bits** of `i` (bits 16–31).
   *   - In this case, we reduce `n` by 16 because we know those 16 leading bits are not zeros.
   *   - Then, `i >>>= 16` shifts the top 16 bits of `i` down to the lower 16 bits, effectively discarding the top 16 bits.
   *
   * **Example:**
   * - Let `i = 100000` (binary: `0000 0001 1000 0110 1010 0000`).
   * - `i >= 2^16` is `true`, so:
   *   - `n -= 16 → n = 15`.
   *   - `i >>>= 16 → i = 1526` (binary: `0000 0000 0000 0000 0101 1110 0110`).
   *
   * ---
   *
   * #### Step 2: Check if the Next 8 Bits Are Non-Zero
   *
   * if (i >= 1 << 8) { n -= 8; i >>>= 8; }
   *
   * - `1 << 8` is `2^8` (i.e., `0x100` or `0000 0000 0000 0000 0000 0001 0000 0000` in binary).
   * - If `i >= 2^8`, it means there is at least one `1` bit in the **next 8 bits** (bits 8–15).
   *   - We reduce `n` by 8.
   *   - Then, `i >>>= 8` shifts the top 8 bits of `i` down.
   *
   * **Example (continued):**
   * - Now `i = 1526` (binary: `0000 0000 0000 0000 0101 1110 0110`).
   * - `i >= 2^8` is `true`, so:
   *   - `n -= 8 → n = 7`.
   *   - `i >>>= 8 → i = 5` (binary: `0000 0000 0000 0000 0000 0000 0000 0101`).
   *
   * ---
   *
   * #### Step 3: Check if the Next 4 Bits Are Non-Zero
   * ```java
   * if (i >= 1 << 4) { n -= 4; i >>>= 4; }
   * ```
   * - `1 << 4` is `2^4` (i.e., `0x10` or `0000 0000 0000 0000 0000 0000 0000 1000` in binary).
   * - If `i >= 2^4`, it means there is at least one `1` bit in the **next 4 bits** (bits 4–7).
   *   - We reduce `n` by 4.
   *   - Then, `i >>>= 4`.
   *
   * **Example (continued):**
   * - Now `i = 5` (binary: `0000 0000 0000 0000 0000 0000 0000 0101`).
   * - `i >= 2^4` is `false`, so we skip this step.
   *
   * ---
   *
   * #### Step 4: Check if the Next 2 Bits Are Non-Zero
   * ```java
   * if (i >= 1 << 2) { n -= 2; i >>>= 2; }
   * ```
   * - `1 << 2` is `2^2` (i.e., `0x4` or `0000 0000 0000 0000 0000 0000 0000 0100` in binary).
   * - If `i >= 2^2`, reduce `n` by 2 and shift `i`.
   *
   * **Example (continued):**
   * - `i = 5` (binary: `0000 0000 0000 0000 0000 0000 0000 0101`).
   * - `i >= 2^2` is `true`, so:
   *   - `n -= 2 → n = 5`.
   *   - `i >>>= 2 → i = 1` (binary: `0000 0000 0000 0000 0000 0000 0000 0001`).
   *
   * ---
   *
   * #### Step 5: Adjust for the Last Bit
   * ```java
   * return n - (i >>> 1);
   * ```
   * - At this point, `i` is either `1` or `0`.
   * - `i >>> 1` shifts the last bit of `i` to the right. This evaluates to:
   *   - `0` if `i = 1` (no more bits to shift).
   *   - `0` if `i = 0`.
   * - Subtracting `0` from `n` leaves `n` unchanged.
   *
   * **Example (continued):**
   * - `i = 1`, so:
   *   - `i >>> 1 = 0`.
   *   - `n - (i >>> 1) = 5 - 0 = 5`.
   *
   * The method returns **5**, which is the number of leading zeros in the original number `100000`.
   *
   * ---
   *
   * ### 4. **Summary**
   * The method efficiently calculates the number of leading zeros in a 32-bit integer by:
   * 1. Checking progressively larger chunks of bits (16, 8, 4, 2, 1) to narrow down the range.
   * 2. Reducing the number of operations using bit-shifting and logical comparisons.
   * 3. Returning the count of leading zeros (`n`).
   *
   * ---
   *
   * ### Example Outputs
   * | Input `i` | Binary Representation                    | Leading Zeros | Output |
   * |-----------|------------------------------------------|---------------|--------|
   * | 5         | `0000 0000 0000 0000 0000 0000 0000 0101`| 29            | 29     |
   * | 100000    | `0000 0000 0000 0001 1000 0110 1010 0000`| 15            | 15     |
   * | 0         | `0000 0000 0000 0000 0000 0000 0000 0000`| 32            | 32     |
   * | -1        | `1111 1111 1111 1111 1111 1111 1111 1111`| 0             | 0      |
   *
   *
   * Summary of the Purpose of Right-Shifting:
   * Efficiency: Right-shifting allows the function to process the number in larger chunks of bits (4 bits at a time),
   * speeding up the process compared to checking one bit at a time.
   * Generality: This approach ensures that the function works efficiently for both small and large numbers.
   * Breaking Down the Problem: The right shift progressively reduces the number to check smaller parts
   * (from the most significant bit to the least significant one), ensuring no leading zeros are missed.
   * Even for a small number like 19, the process works in the same way to handle any case, ensuring that the function
   * is both efficient and consistent.
   *
   * I hope that clears up the reasoning behind the right shift in this function!
   * It’s all about making the function faster and more efficient across various types of input.
   *   *
   *
   * For Example: Number 2147483647
   * The number 2147483647 (which is 0x7FFFFFFF in hexadecimal) in binary is:
   *
   *
   * 11111111 11111111 11111111 11111111
   * First Chunk Check:
   *
   * The number is 32 bits, and we start by checking the first chunk of 16 bits (from the left).
   * 2147483647 >= 1 << 16 (true, because 2147483647 is much greater than 65536).
   * We know there are no leading zeros in the first 16 bits, so we subtract 16 from n (initially 31), making n = 15.
   * But we still don’t know if there are leading zeros in the next chunk of bits.
   *
   * Right-Shift:
   *
   * At this point, if we stop here and just subtract 16 from n without shifting, we won’t be able to analyze the next chunk of 16 bits properly.
   * To move to the next 16 bits (the remaining part of the number), we right-shift the number by 16 positions.
   * After right-shifting by 16:
   *
   *
   * 2147483647 >>> 16 = 65535
   * So now, i is 65535 (in binary: 11111111 11111111).
   *
   * Second Chunk Check:
   *
   * We now check the first chunk of 8 bits (from the left).
   * Since 65535 is greater than 1 << 8 = 256, we subtract another 8 from n.
   * This makes n = 7.
   * Right-Shift Again:
   *
   * We right-shift the number again by 8 positions:
   *
   * 65535 >>> 8 = 255
   * Now, i is 255 (in binary: 11111111).
   *
   * Third Chunk Check:
   *
   * Now we check the first chunk of 4 bits (from the left). Since 255 is greater than 1 << 4 = 16, we subtract another 4 from n.
   * This makes n = 3.
   * Right-Shift Again:
   *
   * We right-shift the number again by 4 positions:
   *
   * 255 >>> 4 = 15
   * Now, i is 15 (in binary: 1111).
   *
   * Final Chunk Check:
   *
   * Finally, we check the first chunk of 2 bits (from the left). Since 15 is greater than 1 << 2 = 4, we subtract another 2 from n.
   * This makes n = 1.
   * Right-Shift Again:
   *
   * We right-shift the number by 2 positions:

   * 15 >>> 2 = 3
   * Now, i is 3 (in binary: 11).
   *
   * Final Check:
   *
   * n = 1, i = 1
   *
   * n - (i >>> 1) = 1
   *
   * we get one even though 2147483647 = 11111111 11111111 11111111 11111111
   *
   * is because is represented completely in 32
   *
   * 0 11111111 11111111 11111111 11111111  ----> 32th bit so 1 leading zero
   *
   */

  private int getNumberOfLeadingZeroes(int i){

    if (i <= 0)
      return i == 0 ? 32 : 0;
    int n = 31;

    if (i >= (1 << 16)) {
      n -= 16;
      i = i >>> 16;
    }

    if (i >= (1 << 8)) {
      n -= 8;
      i = i >>> 8;
    }
    if (i >= (1 << 4)) {
      n -= 4;
      i = i >>> 4;
    }
    if (i >= (1 << 2)) {
      n -= 2;
      i = i >>> 2;
    }

    return n - (i >>> 1);
  }

  /**
   *
   * @param <K> any kind of object that can be created here which represents the Key
   * @param <V> any kind of object that can be created here which represents the Value
   *
   * We are setting this class because internally the hashmap what are doing is we for every key we find the hash code for
   * that once we get the hashcode we need to make sure the hashcode is within the bounds of the array so we mod
   * with the length of the hashTable(explained later).
   *
   * The idea for this is we are creating an array of the Entry<K,V> that would store our keys, now obviously
   * for any size we might collision(same hashKey for different keys) so a LinkedList representation what we are doing
   * is if we find the same index in array -> (hashkey % hashTable.length) for a particular key we would use the next method
   * to chain the values if the keys are unique
   *
   * let's say: we are given, key1 -> value, key2 -> value, key3 -> value
   *
   * hashIndex: (not actual could be anything)
   *           key1 = 3, key2 = 1, key3 = 3
   *
   * hashTable[3] = [key1 -> value, next] -> [key3 -> value, null]  ----> sort of linked list representation
   *
   * hashTable[1] = [key2 -> value, next] -> null
   *
   *
   *
   *
   *
   */
  private class Entry<K,V>{
    private K key ;
    private V value ;

    private Entry<K,V> next;

    private Entry(K key, V value){
      this.key = key ;
      this.value = value;
      this.next = null;
    }
  }



  public static void main(String args[]){
    CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();

    customHashMap.put("abc", 1);
    customHashMap.put("def", 2);
    customHashMap.put("efg", 3);

    log.info("Current Hash Map {}", customHashMap);

    customHashMap.put("def", 5);

    log.info("Current Hash Map {}", customHashMap);

    CustomHashMap<String, Integer> customHashMap2 = new CustomHashMap<>();

    /**
     * Testing reSize functionality for the customHashMap
     */
    for(int i = 1 ; i <= 16; i++){
      customHashMap2.put(String.format("key%s", i), i);

    }

    customHashMap2.put(String.format("key%s", 17), 17);
    log.info("CustomHashMap Values : {}", customHashMap2);

    /**
     * direct size of the map we cannot count because could be the hash for two different values return same result
     * we have keep iterating like the toString() method and keep adding
     */

    log.info("CustomHashMap2 size = {}", customHashMap2.size);
  }
}