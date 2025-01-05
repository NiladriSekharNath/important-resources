package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible.
 * Keep the following conditions in mind:
 *
 * A word can only consist of lowercase characters.
 * Only one letter can be changed in each transformation.
 * Each transformed word must exist in the wordList including the targetWord.
 * startWord may or may not be part of the wordList.
 * Return an empty list if there is no such transformation sequence.
 * The first part of this problem can be found here.
 *
 *
 * Example 1:
 *
 * Input:
 * startWord = "der", targetWord = "dfs",
 * wordList = {"des","der","dfr","dgt","dfs"}
 * Output:
 * der dfr dfs
 * der des dfs
 * Explanation:
 * The length of the smallest transformation is 3.
 * And the following are the only two ways to get
 * to targetWord:-
 * "der" -> "des" -> "dfs".
 * "der" -> "dfr" -> "dfs".
 * Example 2:
 *
 * Input:
 * startWord = "gedk", targetWord = "geek",
 * wordList = {"geek", "gefk"}
 * Output:
 * "gedk" -> "geek"
 *
 *
 * so the problem is same as the problem before this Word LadderI however this problem is slightly different this wants
 * us to print all the paths from start to end
 *
 * der dfr dfs
 * der des dfs
 *
 * start word = der, end word = dfs
 *
 * The Approaches taken in this problem are two approaches
 *
 * One Approach the first one is written by me and the next one is working in GeeksForGeeks but the solution does not work
 * in Leetcode in Leetcode we found a better solution
 *
 * Solution 1 We take a Dijkstra like appraoch to find the words
 *
 * in each minHeap iteration we maintain this in the minHeap
 *
 * [distanceOfWord(paths lengths of reaching a particular word), currentWord, ListOfWords(all the paths)]
 *
 * what we do is we keep a wordDistance map that tracks "the word", and "the shortest distance of that word"
 *
 * and keep the startWord distance as 0 and rest "inf"
 *
 * the idea is let's say have a word initially start word "der" -> 0, then we fina a valid word (which is like a node of a graph)
 * dfr -> 1 (we see if the distance from the distance[currentNode] (either from sourceNode or any other "word" or like a graph node)
 * + 1 we see if the distance is less than the previous distance if yes we update the distance of that particular word and
 * and add it to the heap, and also add it to currentList
 *
 * so the list becomes [sourceword, nextWord] or [der, dfr] and we keep repeating the same
 *
 *
 * Now the distance array is kept because let's say we find a path in like this
 *
 * word1 -> word2 -> word3 -> word4     (if we observer here word1 = 0, word2 = 1 distance)
 * word1 -> word3 -> word2 -> word3 -> word4 (here word1 = 0 and we find word3 = 1, word2 = 2 distance)
 *
 * in the next step through path word1 -> word3 -> word2... we find that the distance of this path that reaches word2 to be
 * 2 which is greater than the previously recorded distance of 1, which we don't want in our paths as we want all the
 * shortest paths only so the distance[word2] map helps us with this keeping the lowest distance always
 *
 * Now if we see this line
 *
 * if (wordDistanceCount.containsKey(nextWord) && currentDist + 1 <= wordDistanceCount.get(nextWord)) in stardard
 *
 * Dijkstra we don't use the equal to sign <= dist[targetNode] since we don't want to update if we got another distance
 * that is the same but here since we need all the shortest path we have to do this
 *
 * This could be explained with this example
 *
 * let's say we find this paths
 *
 *
 *    der dfr dfs  distance[der -> 0, dfr -> 1, dfs -> 2] we found a path to the target, with path length total from source = 2 in first level
 *    der des dfs  distance[der -> 0, dfr -> 1, dfs -> 2] again we found a path to the target, with path
 *                                      length total from source = 2 in the next level
 *
 *
 *  but here if we did not keep <= then dfs which has a recorded value of 2 already, even if we found a different path
 *  we won't be adding that path since the path length is not lower so we keep this <= sign
 *
 *  again this could be true for cases like not only for the target
 *
 *  word1 -> word2 -> word3 -> word4
 *  word1 -> word3 -> word2 -> word4
 *
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * Approach2:
 *   Since Approach1 fails in Leetcode we are doing an optimization on this for Leetcode which is a better solution
 *
 *   In this approach we take a word let's say der (at level 0) find all it's possible words in the (next level 2)
 *   "dfr", "des" and for each order word we find the next level word "dfs"
 *
 *   we keep a map to store the levels of the word -> wordLevelCount
 *
 *   der (found at level 1)
 *   dfr des (found at level 2)
 *   dfs (found at level3)
 *
 *   if "dfs" is the target word in that level we break out of the loop
 *
 *   for doing this above we take a queue and "wordLevelCount" map which "stores words at each bfs levels"
 *
 *   Firstly when we take a word and put in the queue we remove the word from the set of words that we are maintaining this is done
 *   and since we keep track of the levels.
 *
 *   for the currentWord in the bfs once we find it's nextWord a possible candidate we get the current level from the
 *   currentword(from the map) and increment this by 1, to get the next level for the nextWord,
 *   we have the nextword we remove that from the set
 *
 *   This removal from the set is done same as Word Ladder 1
 *
 *   1st reason : follow this pattern
 *
 *    word1 -> word2 -> word3 -> word4
 *
 *    word1 -> word3 -> word2 -> word3 -> word4 (to not getting a path of a greater length that is explored previously, let's say
 *          from word3 -> word2 -> word3..., which is again at a higher level we don't need that as we want the shortest path)
 *
 *    2nd reason: this if we see kind of like a visited array where if a path is already visited we don't visit that path again
 *
 *    3rd reason: since we are changing one character at a time from each word, also the letters are from a - z
 *    there is a gurantee that the same next word with a lesser path length will not be found in the next level we can
 *    find a same level only
 *
 *    so basically we find a nextWord mark it's level and remove that word
 *
 *    We break from the bfs loop, when we get the targetWord
 *
 *    Now if we don't find the targetword in the map that means there is no paths to the targetWord we simply return
 *    an empty list
 *
 *    Now if we find the targetWord from targetWord at last level, we do a backtrack to
 *    get all the paths till the source and add those words in the final list
 *
 *    let's say we found words like this
 *
 *    dfs -> level 3
 *
 *    dfr, des -> level2
 *
 *    der -> level1
 *
 *    we start our backtracking from level3 targetWord -> then we try to find again each possible word but this time only
 *    we try to find the words in level2 only (the previouslevel = currentlevel(3 - 1)), then when we get one word
 *    at level2 -> we try to find a particular word again at level1
 *
 *    now if during our recursion step we find a word that is equal to the startword we add that list to our answer list
 *
 *    Now the thought process for the recursive solution from "target to start" to find the paths
 *
 *    is
 *
 *    let's say if we go from all paths
 *
 *          ---> path1
 *   start  ---> path2        now here only path1 -->  target
 *          ---> path3         and          path2 ---> target
 *
 *          .
 *          .
 *          .
 *
 *    Now here we try path1, path2, path3, path4... but only path1 and path2 reaches "target" so here we end up trying all
 *    paths namely path3, path4... only to find later that it does not reach the "target"
 *
 *    so there is no point of extra computation for failed approaches
 *
 *    thereby what we do is mark all the levels and find one shortest path the target and record the level of target that is found
 *
 *    so what we do now is go from (end to start) to find all paths that are possible by level, this way we reduce the
 *    number of paths that we can find
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class WordLadderII {
  public ArrayList<ArrayList<String>> findSequences(String startWord,
                                                    String targetWord,
                                                    String[] wordList) {

    Queue<WordInfo> queue = new LinkedList<>();

    ArrayList<ArrayList<String>> wordPaths = new ArrayList<>();

    Map<String, Integer> wordDistanceCount = new HashMap<>();

    for (String word : wordList) {
      wordDistanceCount.put(word, (int) 1e9);
    }

    wordDistanceCount.put(startWord, 0);


    queue.add(new WordInfo(wordDistanceCount.get(startWord), startWord, Arrays.asList(startWord)));


    while (!queue.isEmpty()) {
      WordInfo wordInfo = queue.poll();

      int currentDist = wordInfo.distance;
      String currentWord = wordInfo.word;
      List<String> currentWordList = wordInfo.wordPaths;

      if (currentWord.equals(targetWord)) {
        wordPaths.add(new ArrayList<>(currentWordList));
      }


      for (int i = 0; i < currentWord.length(); i++) {
        char possibleNextWord[] = currentWord.toCharArray();
        for (char letter = 'a'; letter <= 'z'; letter++) {
          possibleNextWord[i] = letter;

          String nextWord = new String(possibleNextWord);
          if (wordDistanceCount.containsKey(nextWord) && currentDist + 1 <= wordDistanceCount.get(nextWord)) {

            wordDistanceCount.put(nextWord, (currentDist + 1));

            List<String> nextWordList = new ArrayList<>();
            nextWordList.addAll(currentWordList);
            nextWordList.add(nextWord);

            queue.add(new WordInfo(wordDistanceCount.get(nextWord), nextWord, nextWordList));
          }
        }
      }


    }

    return wordPaths;
  }


  private class WordInfo {

    private int distance;
    private String word;
    private List<String> wordPaths;

    private WordInfo(int distance, String word, List<String> wordPaths) {
      this.distance = distance;

      this.word = word;
      this.wordPaths = wordPaths;
    }
  }

  public List<List<String>> findLaddersLeetCodeEfficient(String startWord, String targetWord, List<String> wordList) {


    Queue<String> queue = new LinkedList<>();

    Set<String> wordSet = new HashSet<>(wordList);

    List<List<String>> wordPaths = new ArrayList<>();

    Map<String, Integer> wordLevelCount = new HashMap<>();

    wordLevelCount.put(startWord, 1);

    queue.add(startWord);

    wordSet.remove(startWord);

    int wordSize = startWord.length();

    while (!queue.isEmpty()) {
      String currentWord = queue.poll();

      int currentLevel = wordLevelCount.get(currentWord);

      if (currentWord == targetWord) break;

      for (int currentLetter = 0; currentLetter < wordSize; currentLetter++) {
        char possibleNextWord[] = currentWord.toCharArray();
        for (char letter = 'a'; letter <= 'z'; letter++) {
          possibleNextWord[currentLetter] = letter;

          String nextWord = new String(possibleNextWord);
          if (wordSet.contains(nextWord)) {
            wordLevelCount.put(nextWord, currentLevel + 1);
            wordSet.remove(nextWord);
            queue.add(nextWord);

          }
        }
      }


    }


    if (!wordLevelCount.containsKey(targetWord)) return wordPaths;

    helper(targetWord, wordLevelCount.get(targetWord), startWord, wordLevelCount, new ArrayList<>(Arrays.asList(targetWord)), wordPaths);


    return wordPaths;
  }

  public void helper(String currentWord, int currentLevel, String startWord, Map<String, Integer> wordLevelCount, List<String> currentList, List<List<String>> resultList) {

    if (currentWord.equals(startWord)) {
      List<String> tempList = new ArrayList<>(currentList);
      Collections.reverse(tempList);
      resultList.add(tempList);
      return;
    }

    for (int currentLetter = 0; currentLetter < currentWord.length(); currentLetter++) {

      char possibleNextWord[] = currentWord.toCharArray();
      for (char letter = 'a'; letter <= 'z'; letter++) {
        possibleNextWord[currentLetter] = letter;

        String nextWord = new String(possibleNextWord);
        if (wordLevelCount.containsKey(nextWord) && wordLevelCount.get(nextWord) == currentLevel - 1) {

          currentList.add(nextWord);
          helper(nextWord, currentLevel - 1, startWord, wordLevelCount, currentList, resultList);
          currentList.remove(currentList.size() - 1);
        }
      }

    }


  }


  public static void main(String[] args) {
    new WordLadderII().findLaddersLeetCodeEfficient("der", "dfs", Arrays.asList(new String[]{"des", "der", "dfr", "dgt", "dfs"}));
  }
}
