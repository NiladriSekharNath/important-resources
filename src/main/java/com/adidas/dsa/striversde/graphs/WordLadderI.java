package com.adidas.dsa.striversde.graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find the length of the shortest transformation sequence from startWord to targetWord.
 * Keep the following conditions in mind:
 *
 * A word can only consist of lowercase characters.
 * Only one letter can be changed in each transformation.
 * Each transformed word must exist in the wordList including the targetWord.
 * startWord may or may not be part of the wordList
 *
 * Note: If no possible way to transform sequence from startWord to targetWord return 0
 *
 *
 * Example 1:
 *
 * Input:
 * wordList = {"des","der","dfr","dgt","dfs"}
 * startWord = "der", targetWord= "dfs",
 * Output:
 * 3
 * Explanation:
 * The length of the smallest transformation
 * sequence from "der" to "dfs" is 3
 * i,e "der" -> "dfr" -> "dfs".
 * Example 2:
 *
 * Input:
 * wordList = {"geek", "gefk"}
 * startWord = "gedk", targetWord= "geek",
 * Output:
 * 2
 * Explanation:
 * gedk -> geek
 * Example 3:
 *
 * Input:
 * wordList = {"poon", "plee", "same", "poie","plea","plie","poin"}
 * startWord = "toon", targetWord= "plea",
 * Output: 7
 * Explanation:
 * toon -> poon -> poin -> poie -> plie -> plee -> plea
 *
 *
 * If we start with the brute force approach taking the first example
 *
 * we are given
 *
 *                der
 *           /    |        \
 *[aer,ber,..][dar, dbr,.] [dea, deb...] level 1
 *                dfr
 * .            /  |  \
 * .
 * .
 *
 * What we are doing is we are taking each character in the start word and finding out the path if a particular word exist in our given list
 * or not then if we find a word in the level-2 and below we are doing the same
 *
 * Now an important point to observer in the above traversal brute force would obviously take a lot of time
 *
 * now if we see we did the same for all levels now if we think of it this way
 *
 * let's say current start = der step = 1 -> dfr step = 2(we found a word that is in our array) -> dfs step = 3 (we found this word in the
 * level 3) now what we did was
 *
 * take a queue added a pair of pair (startword, steps = 1) to kick off the bfs
 *
 * once we found a matching word that exist we took that word, increased the number of steps and added it back to the queue
 *
 * and we keep doing this till we find the target word
 *
 * Now one point to observe is we could be doing all word combinations and adding it to queue but is that necessary
 *
 * consider this example maybe not a valid one but could be used to understand the problem
 *
 * let's say our start word = "Kolkata" and targetword = "Kerala"
 *
 * now we see we find a path
 *
 * [Kolkata, Odisha, Hyderabad, Kerala] this is not a valid array as the path lengths are different but we are using this to understand
 * the process
 *
 * Kolkata -> Odisha -> Hyderabad -> Kerala, and our path length is 4
 *
 * Kolkata -> Bangalore -> Odisha -> Hyderabad -> Kerala and here our path length = 5
 *
 *
 * While traversing in the first level Kolkata, if we found a path to Odisha that leads us to Hyderabad
 * and finally to Kerala with a lesser path length
 *
 * but in the first level again we find a path to Bangalore then we move to Odisha and then the above same path follows
 *
 * It does not make sense to re-visit a particular point and do that again only to get a greater length path
 *
 * so while we are doing operations in the code we let's say when we find a valid word that is exisiting in the array
 *
 * we remove that word so, as to not visit the word again
 *
 * at first step we remove Kolkata at the second step we remove Odisha
 *
 * so when we find Bangalore in another path when we find a word Odisha but that word is not present now so we don't consider that
 *
 * This works and gurantees to find the path in the first encounter of a particular word because the characters
 *
 * a - z are changed in an order and again the letter in the words are changed Kolkata -> like first K, o, l in that order
 *
 * so that works also since for a particular word we change one character at a time and so other changes from other level is not allowed
 *
 * word1 -> word2 -> word3 -> word4
 * word1 -> word3 -> word2 -> word3 -> word4
 *
 * this case we don't desire
 *
 *
 *
 *
 *
 */
public class WordLadderI {
  public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
    Set<String> wordSet = new HashSet<>(Arrays.asList(wordList));
    Queue<WordSteps> queue = new LinkedList<>();

    /**
     * we are using steps = 1 because we are couting the like this
     * word1 -> word2 -> word3
     *
     * steps = 3
     * it's only fair we count our starting step as 1 instead of 0
     */
    queue.add(new WordSteps(startWord, 1));
    wordSet.remove(startWord);

    while (!queue.isEmpty()) {
      WordSteps wordPair = queue.poll();

      String currentWord = wordPair.word;
      int steps = wordPair.steps;

      if (currentWord.equals(targetWord)) return steps;

      Arrays.asList(new int[]{});

      for (int i = 0; i < currentWord.length(); i++) {
        char possibleNextWord[] = currentWord.toCharArray();
        for (char letter = 'a'; letter <= 'z'; letter++) {
          possibleNextWord[i] = letter;

          String nextWord = new String(possibleNextWord);
          if (wordSet.contains(nextWord)) {
            wordSet.remove(nextWord);
            queue.add(new WordSteps(nextWord, steps + 1));
          }
        }
      }


    }

    return 0;

  }

  private class WordSteps {
    private String word;
    private int steps;

    private WordSteps(String word, int steps) {
      this.word = word;
      this.steps = steps;
    }
  }


  public static void main(String args[]) {
    new WordLadderI().wordLadderLength("der", "dfs", new String[]{"des", "der", "dfr", "dgt", "dfs"});
  }
}
