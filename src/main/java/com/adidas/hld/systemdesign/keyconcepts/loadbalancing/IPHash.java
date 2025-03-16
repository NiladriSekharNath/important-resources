package com.adidas.hld.systemdesign.keyconcepts.loadbalancing;

import java.util.List;

/**
 *
 Calculates a hash value from the client’s IP address and uses it to determine the server to route the request.

 When to Use:
 When you need session persistence, as requests from the same client are always directed to the same server.

 Benefits:
 Simple to implement.

 Useful for applications that require sticky sessions.

 Drawbacks:
 Can lead to uneven load distribution if certain IP addresses generate more traffic than others.

 Lacks flexibility if a server goes down, as the hash mapping may need to be reconfigured.
 */
public class IPHash {
  private List<String> servers;

  public IPHash(List<String> servers) {
    this.servers = servers;
  }

  public String getNextServer(String clientIp) {
    int hash = clientIp.hashCode();
    int serverIndex = Math.abs(hash) % servers.size();
    return servers.get(serverIndex);
  }

  public static void main(String[] args) {
    List<String> servers = List.of("Server1", "Server2", "Server3");
    IPHash ipHash = new IPHash(servers);

    List<String> clientIps = List.of("192.168.0.1", "192.168.0.2", "192.168.0.3");
    for (String ip : clientIps) {
      System.out.println(ip + " is mapped to " + ipHash.getNextServer(ip));
    }
  }
}
