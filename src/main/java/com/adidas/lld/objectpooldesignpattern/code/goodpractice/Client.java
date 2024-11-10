package com.adidas.lld.objectpooldesignpattern.code.goodpractice;



public class Client {
  public static void main(String [] args){
    DBConnectionPoolManager poolManager = DBConnectionPoolManager.getInstance();

    /**
     * the reason this code is not good or not acceptable is that
     * we have here the problem here anyone or any client could do this type of change here
     * new DBConnectionPoolManager() and initialize a new object everytime thus the problem of having a fixed thread-pool with minimum and
     * maximum value is gone.
     *
     * Solution is to wrap it in singleton pattern so that multiple initialization is not possible
     */

    /**
     * maximum six connections can be used
     */
    for(int i = 0 ; i < 5 ; i++){
      DBConnection dbConnection = poolManager.getDBConnection();

    }
    /**
     * if we add the above connections here in dev
     */
    DBConnection dbConnection2 = poolManager.getDBConnection();
    poolManager.releaseConnection(dbConnection2);

  }
}
