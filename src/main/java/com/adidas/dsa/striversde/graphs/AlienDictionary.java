package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Alien Dictionary
 *
 * Given a sorted dictionary of an alien language having some words dict and k starting alphabets of a standard dictionary. Find the order of characters in the alien language. If no valid ordering of letters is possible, then return an empty string.
 *
 * Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will be "true" if the order of string returned by the function is correct else "false" denotes incorrect string returned.
 *
 * Examples:
 *
 * Input: dict[] = ["baa","abcd","abca","cab","cad"], k = 4
 * Output: true
 * Explanation: Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly we can find other orders.
 * Input: dict[] = ["caa","aaa","aab"], k = 3
 * Output: true
 * Explanation: Here order of characters is 'c', 'a', 'b' Note that words are sorted and in the given language "caa" comes before "aaa", therefore 'c' is before 'a' in output.
 * Similarly we can find other orders.
 *
 * Approach is pretty straight forward
 *
 * in a normal dictionary we have letters arranged in this order
 *
 * a,b,c,d,e,f...
 *
 * if we need to decide the sorting order
 *
 * abc, abe
 *
 * we know abc comes before abe since first two characters('ab') are same we just check the last differentiating
 * character
 *
 *
 *
 * Similarly in this order
 *
 * ["baa","abcd","abca","cab","cad"]
 *
 * if we see in this pair "baa", "abcd". "baa" comes first then "abcd"
 *
 * because in the alien dictionary b -> a ( 'b' comes first then 'a')
 *
 * now if found first 2 pair we don't need to check "baa" with other words because we found the difference since we are
 * interested in the order
 *
 * now in the dictionary we take next two -> "abcd", "abca" since "abc" is common then we get d -> a
 *
 *
 * so like this we find the edges in the DAG.
 *
 * b -> a, d -> a ... etc
 *
 * so the approach is take two words at a time and find the first differentiating character and add an edge between
 * them
 *
 *
 * so after we get the graph we perform a topo sort to get one valid order using any algorithm dfs or bfs
 *
 * once we get the topo sort we check if we have size 'k' given as number of characters or nodes once we get this
 * if  size(toposort)  = k  then we got a valid topo sort which is okay otherwise we can't get an order
 *
 * let's say if there is cyclic dependency
 *
 *
 * Also just one point
 *
 * in this example  ["baa","abcd","abca","cab","cad"], k = 4
 *
 * if we had k = 5 instead of 4 then we needed and additional of e
 *
 * which is also valid without any dependency as the 'e' can be called as the single component value of a multi-component
 * graph
 *
 * which is fine so our algorithm for multi component graph works however if size of toposort is less than 'k' then it is not possible
 *
 *
 *
 *
 */
public class AlienDictionary {
  public String findOrder(String[] dict, int k) {


    List<List<Integer>> graph = getGraph(dict, k);

    List<Integer> topoSort = topologicalSort(graph);

    if(topoSort.size() != k)
      return "";

    StringBuilder resultString = new StringBuilder();
    for(int node  : topoSort){
      resultString.append((char)(node + 'a'));
    }

    return resultString.toString();
  }

  private List<List<Integer>> getGraph(String[] dict, int k){
    List<List<Integer>> graph = new ArrayList<>();

    for(int i = 0 ; i < k ; i++){
      graph.add(new ArrayList<>());
    }

    /**
     * we are doing this take two words at left and left + 1 position and check for the first non-duplicate to find the
     *
     * edge
     *
     * example : "abcd", "abca" since "abc" is common then we get d -> a
     *
     * we are doing zero based way of representation
     *
     * a -> some ascii value of a
     * d -> some ascii value of d (can also be written as some ascii value of a + some diff)
     *
     * so we if we do 'a' - 'a' we get 0 index
     * and if we do 'd' - 'a' we get 3 diff as ( a = 0, b = 1 , c = 2, d = 3 ... as 'd' is 3 characters after 'a')
     *
     * we know
     */
    int left = 0;
    while(left < dict.length - 1){
      String firstWord = dict[left], secondWord = dict[left + 1];
      int ptr1 = 0, ptr2 = 0, len = Math.min(firstWord.length(), secondWord.length());
      while(ptr1 < len && ptr2 < len){
        if(firstWord.charAt(ptr1) != secondWord.charAt(ptr2)){
          int prev = firstWord.charAt(ptr1) - 'a', next = secondWord.charAt(ptr2) - 'a';

          graph.get(prev).add(next);
          break;
        }
        ptr1++;
        ptr2++;
      }

      left++;
    }

    return graph;
  }

  private List<Integer> topologicalSort(List<List<Integer>> graph) {
    Stack<Integer> stack = new Stack<>();
    int vertices = graph.size() ;
    boolean[] visited = new boolean[vertices];

    for(int currentVertex = 0 ; currentVertex < vertices; currentVertex++){
      if(!visited[currentVertex])
        dfs(currentVertex, graph, visited, stack);
    }

    List<Integer> topologicalOrder = new ArrayList<>();
    while(!stack.isEmpty())
      topologicalOrder.add(stack.pop());

    return topologicalOrder;
  }

  private void dfs(int currentNode, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack){
    visited[currentNode] = true;

    for(int neighbour : graph.get(currentNode)){
      if(!visited[neighbour])
        dfs(neighbour, graph, visited, stack);
    }

    stack.push(currentNode);
  }
}
