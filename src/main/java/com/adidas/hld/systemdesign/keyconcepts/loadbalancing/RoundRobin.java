package com.adidas.hld.systemdesign.keyconcepts.loadbalancing;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Load balancing is the process of distributing incoming network traffic across multiple servers to ensure that no single server is overwhelmed.
 *
 *
 * By evenly spreading the workload, load balancing aims to prevent overload on a single server, enhance performance by reducing response times and improve availability by rerouting traffic in case of server failures.
 *
 * There are several algorithms to achieve load balancing, each with its pros and cons.
 *
 * In this article, we will dive into the most commonly used load balancing algorithms, how they work, when to use them, their benefits/drawbacks and how to implement them in code.
 *
 *
 A request is sent to the first server in the list.

 The next request is sent to the second server, and so on.

 After the last server in the list, the algorithm loops back to the first server.

 When to Use:
 When all servers have similar processing capabilities and are equally capable of handling requests.

 When simplicity and even distribution of load is more critical.

 Benefits:
 Simple to implement and understand.

 Ensures even distribution of traffic.

 Drawbacks:
 Does not consider server load or response time.

 Can lead to inefficiencies if servers have different processing capabilities
 */
public class RoundRobin {
  private List<String> servers;
  private AtomicInteger index;

  public RoundRobin(List<String> servers){
    this.servers = servers;
    this.index = new AtomicInteger(-1);

  }

  public String getNextServer(){
    int currentIndex = index.incrementAndGet() % this.servers.size();
    return servers.get(currentIndex);
  }

  public static void main(String[] args){
    List<String> servers = List.of("Server1", "Server2", "Server3");

    RoundRobin roundRobinLB = new RoundRobin(servers);

    for(int i = 0 ; i < 6; i++){
      System.out.println(roundRobinLB.getNextServer());
    }
  }
}
