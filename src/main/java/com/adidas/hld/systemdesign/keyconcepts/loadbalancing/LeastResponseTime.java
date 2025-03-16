package com.adidas.hld.systemdesign.keyconcepts.loadbalancing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 Monitors the response time of each server

 Assigns incoming requests to the server with the fastest response time.

 When to Use:
 When you have servers with varying response times and want to route requests to the fastest server.

 Benefits:
 Minimizes overall latency by selecting the server with the fastest response time.

 Can adapt dynamically to changes in server response times.

 Helps improve the user experience by providing quick responses.

 Drawbacks:
 Requires accurate measurement of server response times, which can be challenging in distributed systems.

 May not consider other factors such as server load or connection count.
 */
public class LeastResponseTime {
  private List<String> servers;
  private List<Double> responseTimes;

  public LeastResponseTime(List<String> servers){
    this.servers = servers;
    this.responseTimes = new ArrayList<>(servers.size());

    for(int i = 0 ; i < servers.size() ; i++){
      responseTimes.add(0.0);
    }
  }

  public String getNextServer(){
    /**
     * as the name suggests we are getting the least response Time
     * finding the server with the least response times and returning that
     *
     */
    double minResponseTime = responseTimes.get(0);
    int minIndex = 0 ;
    for(int i = 1; i < responseTimes.size() ; i++){
      if(responseTimes.get(i) < minResponseTime){
        minResponseTime = responseTimes.get(i);
        minIndex = i  ;
      }
    }

    return servers.get(minIndex) ;
  }

  public void updateResponseTime(String server, double responseTime){
    int index = servers.indexOf(server);
    responseTimes.set(index, responseTime);
  }

  public static double simulateResponseTime(String server){
    //Simulating response time with random delay

    Random random  = new Random();
    double delay = 0.1 + (1.0 - 0.1) * random.nextDouble();
    try{
      Thread.sleep((long) delay * 1000);
    }catch(InterruptedException e){
      e.printStackTrace();
    }

    return delay ;
  }

  public static void main(String[] args) {
    List<String> servers = List.of("Server1", "Server2", "Server3");
    LeastResponseTime leastResponseTimeLB = new LeastResponseTime(servers);

    for (int i = 0; i < 6; i++) {
      String server = leastResponseTimeLB.getNextServer();
      System.out.println("Request " + (i + 1) + " -> " + server);
      double responseTime = simulateResponseTime(server);
      leastResponseTimeLB.updateResponseTime(server, responseTime);
      System.out.println("Response Time: " + String.format("%.2f", responseTime) + "s");
    }
  }
}
