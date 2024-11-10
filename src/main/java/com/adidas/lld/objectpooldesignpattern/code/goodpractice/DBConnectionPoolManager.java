package com.adidas.lld.objectpooldesignpattern.code.goodpractice;

import java.util.ArrayList;
import java.util.List;

public class DBConnectionPoolManager {

  List<DBConnection> freeConnectionPool = new ArrayList<>();
  List<DBConnection> connectionsCurrentlyInUse = new ArrayList<>();
  int INITIAL_POOL_SIZE = 3;
  int MAX_POOL_SIZE = 6 ;

  private static DBConnectionPoolManager dbConnectionPoolManagerInstance = null;

  private DBConnectionPoolManager(){
    for(int i = 0 ; i < INITIAL_POOL_SIZE ; i++){
      freeConnectionPool.add(new DBConnection());
    }
  }

  /***
   *
   * this the important point here we are getting the instance object here using the
   * singleton pattern thus stopping multiple instance creation
   *
   * @return the DBConnectionPoolManager instance object
   */
  public static DBConnectionPoolManager getInstance(){
    if(dbConnectionPoolManagerInstance == null){
      synchronized (DBConnectionPoolManager.class){
        if(dbConnectionPoolManagerInstance == null){
          dbConnectionPoolManagerInstance = new DBConnectionPoolManager();
        }
      }
    }
    return dbConnectionPoolManagerInstance;
  }

  public synchronized DBConnection getDBConnection(){
    if(freeConnectionPool.isEmpty() && connectionsCurrentlyInUse.size() < MAX_POOL_SIZE){
      freeConnectionPool.add(new DBConnection());
      System.out.println("creating new connection and putting into the pull, free pool size: " + freeConnectionPool.size());

    } else if (freeConnectionPool.isEmpty() && connectionsCurrentlyInUse.size() >= MAX_POOL_SIZE){
      System.out.println("Cannot create DBConnection, as max limit reached");
      return null;
    }
    DBConnection dbConnection = freeConnectionPool.remove(freeConnectionPool.size() - 1);
    connectionsCurrentlyInUse.add(dbConnection);
    System.out.println("Adding db connection into use, pool size: " + connectionsCurrentlyInUse.size());
    return dbConnection;
  }

  public synchronized void releaseConnection(DBConnection dbConnection){
    if(dbConnection != null){
      connectionsCurrentlyInUse.remove(dbConnection);
      System.out.println("Removing db Connections from use pool, size: " + connectionsCurrentlyInUse.size());
      freeConnectionPool.add(dbConnection);
      System.out.println("Adding db Connections into free pool, size: " + freeConnectionPool.size());
    }
  }
}
