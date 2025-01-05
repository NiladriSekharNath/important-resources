package com.adidas.dsa.striversde.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Maximum Stone Removal
 *
 * There are n stones at some integer coordinate points on a 2D plane. Each coordinate point may have at most one stone.
 *
 * You need to remove some stones.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the maximum possible number of stones that you can remove.
 *
 * Example 1:
 *
 * Input:
 * n=6
 * [[0 0] ,[ 0 1], [1 0] ,[1 2] ,[2 1] ,[2 2]]
 *
 * Output:
 * 5
 *
 * Example:
 * One way to remove 5 stones are
 * 1--[0,0]
 * 2--[1,0]
 * 3--[0,1]
 * 4--[2,1]
 * 5--[1,2]
 *
 *
 *
 *
 * let's say we are given stones like this first the idea
 *
 * [1 0 0 0 0]
 * [0 1 0 0 0]
 * [0 0 1 1 1]
 * [0 0 1 1 0]
 *
 * Total stones that are given to us is 7,
 *
 * we have to see that we can remove maximum of 4 stones in any possible order we try, if we imagine this problem
 * as kind of like graph problem if we see 1 at 0, 0 and 1 at 1, 1 needs an adjacent stone to be possible to remove
 * which is not, again if we see cluster of stones in the right out of 5 stones we can remove only 4 because after removing
 * the 4 stones there will be 1 stone remaining that we would not be able to remove even if we try any combination
 * because it would require adjacent nodes, so we see any component 'x' we are able to able to remove (x - 1) stones
 *
 * now let's derive in matrix or 2-d plane that we have received now if we see in this arrangement if we have
 *
 * x1, x2, .... xn components
 *
 * we can at max remove x1 - 1, x2 - 1, x3 - 1, ..., xn -1 stones from each
 *
 * so total comes out to (x1 - 1) + (x2 - 1) + (x3 - 1) + ... + (xn -1) stones total comes out
 *
 * now if we write separately
 *
 *    max_stones_removed =  x1 + x2 + x3 + ... + xn  - 1  - 1  - 1 ...(n times)
 *
 *    max_stones_removed =  we can write this as taking out -1 common
 *
 *    max_stones_removed =  x1 + x2 + x3 + ... + xn - ( 1 + 1 + 1 ...  times, takens from all components)
 *
 *             now we know that x1 + x2 + x3 + .. + xn = n (stones)
 *
 *     max_stones_removed = n - (no of components)
 *
 *
 * So our problem boils down to (total number of stones - no of components)
 *
 * Out best Data Structure for this question would be to use DisjoinedSetUnion
 *
 *
 * now in the graph or plane we are given this in the question a bunch of coordinates that has stones, if we represent them
 * in the matrix
 *
 *
 *
 *  * 1--[0,0]
 *  * 2--[1,0]
 *  * 3--[0,1]
 *  * 4--[2,1]
 *  * 5--[1,2]
 *
 *  we will get for this if represent them in the matrix
 *    0  1  2
 * 0 [1, 1, 0]
 * 1 [1, 0, 1]
 * 2 [0, 1, 0]
 *
 * now we need to find out the points but here is the trick we could have counted each and every point in the matrix
 *
 * however this would be a problem for a big value we would not be able to find the solution also map them because this is
 *
 * not provided as a matrix but the coordinate also we need to understand something here for each row or column we don't
 * need to separately treat each row index or row column, but we would rather treat all stones in a particular row or column
 *
 * as a node in DSU
 *
 * what we are doing a column shifting for the columns and rows we can take individually
 *
 * we have rows 0, 1, 2 (which would be our rows, for columns we would be requiring to do this 0, 1, 2 cannot be taken because we
 * already assigned them to the row, for column we see there are maxRows = 2, (index 2 since we are given the index and
 * we are considering 0 based indexing )
 *
 * rows would be marked as 0, 1, 2 -> maxRows = maxValueInRow + 1
 * columns would be marked as maxRows + column: so 0th column would be represented as 2 + 1 + 0(0th column)
 * 1st column would be represented as 2 + 1 + 1(1st column) and 3 + 1 + 1(2nd column)
 *
 * so 0, 1 ,2 in rows would be marked as : 0, 1, 2
 * and columns 0, 1, 2 would be marked : 3, 4, 5
 *
 * now what we are doing here we have entire row and column as nodes in DSU in order to declare that
 * we are marking the size of the rows.
 *
 * 0, 1, 2, 3 , 4 ,5 = 6, size of the parent and rank array in DSU
 *
 *
 * [[0 0] ,[ 0 1], [1 0] ,[1 2] ,[2 1] ,[2 2]]
 *
 *
 * now max from here we are finding the index for this maxRows, maxColumns since we are given the row and column index position
 * to find the max value for the stone only problem for this approach is
 *
 * we get row 2, column 2 from our stones array but inorder to support row index = 2 and row column = 2
 * we would have to have 3 rows and 3 columns (to support indexing from 0, 1, 2 in either row or column)
 *
 * Therefore we need a total of [maxRows + maxColumns + 2] size arrays in the DSU structure to accomodate all nodes and
 * columns
 *
 * lastly after union of all row or columns we would have to find out the total components this can be easily done
 *
 * if we see row0 row1 when we are calculating and joining either of row0 or row1 in the DSU gets to be the parent of the other
 *
 * parent[row0] = row1
 * or parent[row1] = row0
 *
 * for each component
 *
 * now also not all rows and columns have stones so we don't need to count all
 *
 * since we are joining for all components for each component there will be only one parent
 *
 * now in the DSU we know the parent array stores the parent of each node except the ultimate parent (node at top level)
 * that would be parent of itselt
 *
 * let's say parent[node] = node
 *
 * so we take each key and see if the node is itself's parent and find the total components in the graph
 *
 * finally we return the answer (n - totalComponents)
 *
 *
 *
 *
 *
 *
 * so in order to find the "no of components" we would have to know the size of the matrix first since we are not given
 */
public class MaximumStoneRemoval {

  int maxRemove(int[][] stones, int n) {
    int maxRows = Integer.MIN_VALUE/2, maxColumns = Integer.MIN_VALUE/2;

    for (int[] stone : stones) {
      maxRows = Math.max(maxRows, stone[0]);
      maxColumns = Math.max(maxColumns, stone[1]);
    }

    DisjointSetUnion dsu = new DisjointSetUnion(maxRows + maxColumns + 2);

    Set<Integer> stoneNodes = new HashSet<>();

    for (int[] stone : stones) {
      int currentRow = stone[0], currentColumn = stone[1] + maxRows + 1;
      if (!dsu.isConnected(currentRow, currentColumn)) {
        dsu.unionByRank(currentRow, currentColumn);
        stoneNodes.add(currentRow);
        stoneNodes.add(currentColumn);
      }
    }

    /**
     * above we took the set to mark every row or column that has a stone
     *
     * Now if row0 has a stone and as well row1, either of row1 or row0 would be connected where
     * let's say row1's parent = row0
     *
     * that way parent[row1] = row0;
     * parent[row0] = row0; itself
     *
     * so from the map when we count the actual rows, and columns that are ulimate parents
     * we know those are the only one and we count them as one component
     */
    int countOfComponents = 0;

    for (Integer node : stoneNodes) {
      if (dsu.parent[node] == node) countOfComponents++;
    }

    return n - countOfComponents;
  }

  public class DisjointSetUnion {

    /**
     * vertices represent number of nodes in the graph if we have a zero based indexing then we pass n
     * otherwise if 1-based indexing we pass n + 1
     */
    private int vertices;

    private int[] parent;
    private int[] rank;

    /**
     * now in the DisjointSetUnion we don't need both rank and size as both are necessary we can go with either one
     */
    private int[] size;

    public DisjointSetUnion(int vertices) {
      this.vertices = vertices;
      this.parent = new int[vertices];
      this.rank = new int[vertices];

      for (int i = 0; i < vertices; i++) {
        parent[i] = i;
        /**
         * rank by default is initialized to zero always if we don't initialize, default with Java
         *
         * if we however use size we keep the size = 1 for the nodes
         */


      }

    }

    public int findParent(int node) {
      if (parent[node] == node)
        return node;
      return parent[node] = findParent(parent[node]);
    }

    public void unionByRank(int u, int v) {
      int parentU = findParent(u), parentV = findParent(v);

      if (parentU == parentV) return;

      if (rank[parentU] < rank[parentV]) {
        parent[parentU] = parentV;
      } else if (rank[parentV] < rank[parentU]) {
        parent[parentV] = parentU;
      } else {
        parent[parentV] = parentU;
        rank[parentU]++;
      }
    }


    public boolean isConnected(int u, int v) {
      return findParent(u) == findParent(v);
    }

  }

}


