package com.adidas.hld.systemdesign.keyconcepts.loadbalancing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * How it Works:
 * Monitor the number of active connections on each server.
 *
 * Assigns incoming requests to the server with the least number of active connections.
 *
 * When to use:
 * When you want to distribute the load based on the current number of active connections.
 *
 * When servers have similar processing capabilities but may have different levels of concurrent connections.
 *
 * Benefits:
 * Balances load more dynamically based on current server load.
 *
 * Helps prevent any server from becoming overloaded with a high number of active connections.
 *
 * Drawbacks:
 * May not be optimal if servers have different processing capabilities.
 *
 * Requires tracking active connections for each server.
 */
public class LeastConnections {
  private Map<String, Integer> serverConnections;
  private Random random;

  public LeastConnections(List<String> servers){

    this.serverConnections = new HashMap<>();
    for(String server : servers){
      serverConnections.put(server, 0);
    }
    random = new Random();

  }

  public String getNextServer(){
    // Find the minimum number of connections
    int minConnections = Collections.min(serverConnections.values());

    // Collect servers with the minimum connections

    List<String> leastLoadedServers = new ArrayList<>();

    for(Map.Entry<String, Integer> entry : serverConnections.entrySet()){
      if(entry.getValue() == minConnections){
        leastLoadedServers.add(entry.getKey());
      }
    }

    // Pick a random server from the least loaded ones
    String selectedServer = leastLoadedServers.get(random.nextInt(leastLoadedServers.size()));

    serverConnections.put(selectedServer, serverConnections.get(selectedServer) + 1);

    return selectedServer;
  }

  public void releaseConnection(String server){
    serverConnections.computeIfPresent(server, (k, v) -> v > 0 ? v - 1 : 0);
  }

  public void printServerConnections() {
    System.out.println(serverConnections);
  }

  public static void main(String[] args) {
    List<String> servers = List.of("Server1", "Server2", "Server3");
    LeastConnections loadBalancer = new LeastConnections(servers);

    for (int i = 0; i < 6; i++) {
      String server = loadBalancer.getNextServer();
      System.out.println("Request " + (i + 1) + " -> " + server);
      loadBalancer.releaseConnection(server);
    }

    loadBalancer.printServerConnections();
  }
}
