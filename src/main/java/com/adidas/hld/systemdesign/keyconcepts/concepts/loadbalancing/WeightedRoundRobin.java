package com.adidas.hld.systemdesign.keyconcepts.concepts.loadbalancing;

import java.util.List;

/**
 *
 Each server is assigned a weight based on their processing power or available resources.

 Servers with higher weights receive a proportionally larger share of incoming requests.

 When to use:
 When servers have different processing capabilities or available resources.

 When you want to distribute the load based on the capacity of each server.

 Benefits:
 Balances load according to server capacity.

 More efficient use of server resources.

 Drawbacks:
 Slightly more complex to implement than simple Round Robin.

 Does not consider current server load or response time.

 */
public class WeightedRoundRobin {
  private List<String> servers;
  private List<Integer> weights;
  private int currentIndex;
  private int currentWeight;

  public WeightedRoundRobin(List<String> servers, List<Integer> weights){
    this.servers = servers;
    this.weights = weights;
    this.currentIndex = -1;
    this.currentWeight = 0;
  }

  /**
   *
   *🎯 Goal:
   * This block makes the algorithm gradually lower the weight across cycles, resetting when a full round is complete.
   * It helps control when servers should be eligible to handle requests, based on their weights.
   *
   * 🛠️ What It Does:
   * When index reaches 0:
   * It means we’ve completed one full loop through the server list.
   * So, after checking all servers once, we reduce the weight (currentWeight--).
   *
   * Reset weight when needed:
   * When the currentWeight becomes 0 or negative, we reset it to the maximum weight (getMaxWeight()).
   *
   * 🟩 Why This Works:
   * This weight decrement ensures that servers with higher weights stay available longer,
   * while servers with lower weights become available less often.
   *
   * Let’s break this down with an example!
   *
   * 🛠️ Example:
   * Servers: Server1, Server2, Server3
   * Weights: 5, 1, 1
   * Max weight: 5
   * 🔸 Initial state:
   * currentWeight = 5
   * currentIndex = -1
   * 🔸 Iteration Flow:
   * Iteration	Index	Current Weight	Selected Server	Explanation
   * 1	0	5	Server1	Server1 is chosen because weight 5 >= 5.
   * 2	1	5	Skip	Server2 weight is 1, less than 5 — skipped.
   * 3	2	5	Skip	Server3 weight is 1, less than 5 — skipped.
   * 4	0	4	Server1	After completing a full loop, weight decreases.
   * 5	1	4	Skip	Server2 weight still less than 4 — skipped.
   * 6	2	4	Skip	Server3 weight still less than 4.
   * 7	0	3	Server1	Server1 chosen again, weight dropped to 3.
   *
   * 🔥 Key Insights:
   * Full loop required to drop weight:
   * The weight only decreases once per full loop.
   * This ensures higher-weight servers are picked multiple times before low-weight servers even become eligible.
   *
   * Reset on depletion:
   * When the weight reaches 0 or less, it resets to the maximum weight (5 in this case).
   * This restart makes sure the distribution keeps repeating!
   *
   * Fair distribution:
   * Servers get requests proportional to their weights:
   *
   * Server1 (5) gets 5/7 requests
   * Server2 (1) and Server3 (1) get 1/7 each
   * 🚀 Summary:
   * This logic:
   *
   * Controls weight decay after every full round.
   * Resets weight after depletion to start a new weighted cycle.
   * Ensures high-weight servers stay available longer than low-weight ones.
   *
   * servers with heigher weights get more requests than servers with lower weight out of the total number of
   * request
   *
   */
  public String getNextAvailableServer(){
    while(true){
      currentIndex = (currentIndex + 1) % servers.size();
      if(currentIndex == 0){
        currentWeight--;
        if(currentWeight <= 0)
          currentWeight = getMaxWeight();
      }

      if(weights.get(currentIndex) >= currentWeight)
        return servers.get(currentIndex);

    }
  }

  private int getMaxWeight(){
    return this.weights.stream().max(Integer::compareTo).orElse(0);
  }
}
