package com.adidas.dsa.striversde.graphs;

import lombok.extern.slf4j.Slf4j;

/**
 * DisjointSetUnion is the main Data Structure behind Kruskal's Algorithm, however it's main functionality is to determine
 * whether we can
 *
 * say if an edge is connected to a particular graph or not in constant time TC 0[4alpha] alpha is near about constant
 *
 * let's say we are given these edges
 *
 *
 * [[1,2], [2,3], [4,5], [6,7], [5,6], [3,7]]  -----> edges in a 1 based graph
 *
 * initially let's say we have the nodes like this all diffent components in a multi-component graph
 *
 *       1     2    3    4    5     6     7       ------------------> here the nodes are parent of itself meaning
 *                                                                    parent [1] = 1
 *
 *     now if someone asks us if 1 and 7 belong to the same component graph, -> No (as parent[1] = 1 and parent[7] = 7)
 *
 *  Before we add an edge(union by rank or size) let's understand parent finding and path compression
 *
 *    let's say nodes are like this     1   2        ----->   parent[1] = 1 and parent[2] = 2
 *
 *    now let's say we add assuming rank for both 1, 2 is same we will discuss later let's just add the parent's now
 *
 *    Case 1 :
 *
 *    1
 *     \
 *      2
 *
 *    here if we add 2 to 1 that means parent[1] = 1 still, however parent[2] = 1 , after addition of 2
 *    here, WE INCREASE THE RANK OF node 1 BY 1, rank[1] = 1 (now since we added an element to it), this is discussed later
 *
 *
 *
 *    case 2 : now since ranks are equal we could have done this
 *
 *    2
 *     \
 *      1
 *
 *   here if we add 1 to 2 that means parent[2] = 2 still, however parent[1] = 2
 *   here, WE INCREASE THE RANK OF node 2 BY 1, rank[2] = 1 (now since we added an element to it)
 *
 *   Again let's say we have this now
 *
 *   1
 *    \
 *     2
 *      \
 *       3
 *        \
 *         4
 *          \
 *           5
 *
 * here parent[1] = 1, parent[2] = 1, parent[3] = 2, parent[4] = 3, parent[5] = 4
 *
 * now if we are required to find the ultimate of parent[5] we have to keep going up, -----> we now find
 * let's call it ultimateParent5 = (parent[5] = 1)
 *
 * if we keep doing this, this will be a log(n) operation where u is the node of which parent needs to be found
 *
 * so what we do here is path Compression
 *
 * Path Compression :
 *
 *
 *    here parent[1] = 1, parent[2] = 1, parent[3] = 2, parent[4] = 3, parent[5] = 4
 *
 * we have this above configuration :
 *
 * now let's say we need to find the ultimate parent of 5 == 1
 *
 * we do this using recursion we keep searching for nade let's say parent of node 'u', where u = 5
   *
   *  findParent(5)  parent[5] = findParent[parent[5]], here we receive 1, since we received 1, we set parent[5] = 1 and return 1 and complete
   *           \
   *   findParent(4)  parent[4] = findParent[parent[4]], here we receive 1, since we received 1, we set parent[4] = 1 and return 1
   *             \
   *    findParent(3)  parent[3] = findParent[parent[3]], here we receive 1, since we received 1, we set parent[3] = 1 and return 1
   *              \
   *      findParent(2)  parent[2] = findParent[parent[2]],  here we receive 1, since we received 1, we set parent[2] = 1 and return 1
   *                \
   *       findParent(1)    parent[1] = here we see parent[1] = 1, so we return 1
 *
 *
 *   now after this approach we can write something like this
 *
 *          1
 *      / /  | \
 *     2 3  4  5
 *
 * since everyone's parent is 1
 *
 * now we can get this in constant time if we need to find the parent[5] we return 1 directly
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * Union By Rank/ Union by Size:
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * Union By Rank
 * ---------------------------------------------------------------------------------------------------------------------
 * We need to add and edge
 *
 * now when we add an edge we do this
 *
 *  initially we have the following configuration vertices = 7
 *
 *  idxRepre = [0, 1, 2, 3, 4, 5, 6, 7]
 *  parent[] = [0, 1, 2, 3, 4, 5, 6, 7]         since arrays are generally 0 - based we take a  8 size array initializing
 *  rank[] =   [0, 0 ,0, 0, 0, 0, 0, 0]         parent of all nodes to itself and rank of all nodes to 0
 *
 *
 *  let's say we add an edge : [1, 2], we follow the steps either we union by rank or union by Size
 *
 *  Steps to union by Rank(u, v) :
 *
 *   1: Find the ultimate parents of u, v, if both ultimate parents same we return, since they are already connected
 *     let's call it 'ultimateParent_u' and 'ultimateParent_v'
 *
 *   2: Find the rank of 'ultimateParent_u' and 'ultimateParent_v'
 *
 *   3: If both ranks same then we can connect either one 'ultimateParent_u' -> 'ultimateParent_v'
 *       or 'ultimateParent_v' -> 'ultimateParent_u',
 *
 *  ------------------------------------------------
 *             if we connect 'ultimateParent_u' -> 'ultimateParent_v', represented by :
 *
 *                parent[ultimateParent_u] = ultimateParent_v   -->  ultimateParent of u's is now ulimateParent_v
 *                rank[ultimateParent_v]++;  ---> we increase the rank now of ultimateParentV
 *
 *                Example:
 *
 *                let's say, u = 1, v = 2, parent of u = 1, and parentOfV  = 2, rank[1] = 0, rank[2] = 0
 *
 *                       2    v
 * u -> 1                 \
 * v -> 2,                 1    u
 *
 *               after this we make parentOf u(=1) = parentV[=2], and parentOfV(=2) = 2
 *               rank[1] = 0 and rank[v = 2]++ = 1  (0 to 1 increase)
 *
 *  ----------------------------------------------------------------------------------------
 *        otherwise, if we connect 'ultimateParent_v' -> 'ultimateParent_u', represented by :
 *
 *                  parent[ultimateParent_v] = ultimateParent_u   -->  ultimateParent of v's is now ulimateParent_u
 *                  rank[ultimateParent_u]++;  ---> we increase the rank now of ultimateParentU
 *
 *                  Example:
 *
 *                  let's say, u = 1, v = 2, parent of u(=1) = 1 and parentOfV(=2) = 2 rank[1] = 0, rank[2] = 0
 *
 *                         1    u
 *   u -> 1                 \
 *   v -> 2,                 2    v
 *
 *                 after this we make parentOf v(=2) = parentu[=1], now since we added to parentU we increaese
 *                 parent[u] value
 *                 rank[u = 1]++ = 1 and rank[v = 2] = 0
 *
 *   ---------------------------------------------------------------------------------------------------------------
 *
 *   now let's say "rank[u] less than rank[v]" we add parent[ultimateParentU] -> ultimateParent[v], represented by
 *
 *
 *   parent[ultimateParent_u] = ultimateParent_v but here we don't increase the size
 *
 *   let's say we have u = 1 and v = 2 (we are just pointing the root)
 *
 *   represented by in the graph here
 *
 *   1 u       2 v
 *              \
 *               3
 *
 *  now parent[u = 1] = 1, rank[u = 1] = 0, parent[v = 2] = 2, rank[v = 2] = 1
 *
 *  since rank[u] < rank[v]
 *
 *  we add 1 - > 2, which is represented by
 *
 *       2
 *     /  \
 *   1     3
 *
 *  now parent[u = 1] = 2, rank[u = 1] = 0, parent[v = 2] = 2, rank[v = 2] = 1 (here rank of 2 is still 1 we don't increase)
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *   Union By Rank ----- End
 * ---------------------------------------------------------------------------------------------------------------------
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
 *
 */
@Slf4j
public class DisjointSetUnion {

  /**
   *
   * vertices represent number of nodes in the graph if we have a zero based indexing then we pass n
   * otherwise if 1-based indexing we pass n + 1
   */
  private int vertices;

  private int[] parent;
  private int[] rank ;

  /**
   * now in the DisjointSetUnion we don't need both rank and size as both are necessary we can go with either one
   */
  private int[] size;
  public DisjointSetUnion(int vertices){
    this.vertices = vertices;
    this.parent = new int[vertices];
    this.rank = new int[vertices];
    this.size = new int[vertices];

    for(int i = 0 ; i < vertices; i++){
      parent[i] = i;
      /**
       * rank by default is initialized to zero always if we don't initialize, default with Java
       *
       * if we however use size we keep the size = 1 for the nodes
       */
      size[i] = 1;

    }

  }

  public int findParent(int node){
    if(parent[node] == node)
      return node;
    return parent[node] = findParent(parent[node]);
  }

  public void unionByRank(int u, int v){
    int parentU = findParent(u), parentV = findParent(v);

    if(parentU == parentV) return ;

    if(rank[parentU] < rank[parentV]){
      parent[parentU] = parentV;
    }
    else if(rank[parentV] < rank[parentU]){
      parent[parentV] = parentU;
    }
    else {
      parent[parentV] = parentU;
      rank[parentU]++;
    }
  }

  /**
   *
   * we don't need both unionByRank or unionBySize
   */

  public void unionBySize(int u, int v){
    int parentU = findParent(u), parentV = findParent(v);

    if(parentU == parentV) return ;

    if(size[parentU] < size[parentV]){
      parent[parentU] = parentV;
      size[parentV] += size[parentU];
    }
    else {
      parent[parentV] = parentU;
      size[parentU] += size[parentV];
    }
  }
  public boolean isConnected(int u , int v){
    return findParent(u) == findParent(v);
  }

  public static void main(String[] args){
    /**
     * testing with a one based indexing graph
     */
    DisjointSetUnion dsu = new DisjointSetUnion(8);

    dsu.unionByRank(1,2);
    dsu.unionByRank(2,3);
    dsu.unionByRank(4,5);
    dsu.unionByRank(6,7);
    dsu.unionByRank(5,6);

    if(dsu.isConnected(3,7))
      log.info("Node: {}, Node: {} is connected", 3, 7);
    else
      log.info("Node: {}, Node: {} is not connected", 3, 7);

    dsu.unionByRank(3,7);

    if(dsu.isConnected(3,7))
      log.info("Node: {}, Node: {} is connected", 3, 7);
    else
      log.info("Node: {}, Node: {} is not connected", 3, 7);

    /**
     * here the union is done by size however we cannot run both union by rank and union by size at the same time
     *
     * as the parent array is same for both we can however do either one or have a different parent array
     */

    /*dsu.unionBySize(1,2);
    dsu.unionBySize(2,3);
    dsu.unionBySize(4,5);
    dsu.unionBySize(6,7);
    dsu.unionBySize(5,6);

    if(dsu.isConnected(3,7))
      log.info("Node: {}, Node: {} is connected, union By Size ", 3, 7);
    else
      log.info("Node: {}, Node: {} is not connected", 3, 7);

    dsu.unionBySize(3,7);

    if(dsu.isConnected(3,7))
      log.info("Node: {}, Node: {} is connected union by Size", 3, 7);
    else
      log.info("Node: {}, Node: {} is not connected", 3, 7);*/
  }

}
