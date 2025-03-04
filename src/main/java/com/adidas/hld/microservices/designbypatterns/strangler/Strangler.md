**Strangler Pattern Notes**

**Introduction**
The Strangler Pattern is a software design pattern for modernizing legacy systems incrementally. Inspired by strangler figs, it gradually replaces old components without disrupting system functionality.

**Origin and Definition**

- Introduced by Martin Fowler in "Patterns of Enterprise Application Architecture."
- Helps migrate legacy systems by wrapping and replacing components over time.

**Challenges in Legacy System Modernization**

1. **Risk and Complexity:** Incremental changes reduce risk.
2. **Business Continuity:** New and old components coexist.
3. **Inflexibility:** Introduces modern tech gradually.
4. **Resource Allocation:** Focus resources on critical areas first.

**When to Use the Strangler Pattern**

- Monolithic systems with complex codebases.
- Business-critical applications where downtime is costly.
- Limited resources for a complete rewrite.
- High-risk projects needing careful step-by-step migration.

**Strangler vs Big Bang Approach**

- **Risk Management:** Strangler reduces risk; big bang is all-or-nothing.
- **Time to Market:** Incremental delivery vs long development cycles.
- **User Experience:** Smooth transitions vs potential disruption.
- **Resource Utilization:** Efficient, targeted modernization.

**Real-World Example: E-commerce Migration**

- **Scenario:** Extracting checkout & payment modules from a monolith.
- **API Gateway:** Acts as a proxy, routing requests to old/new services.
- **Incremental Migration:** Develop new microservices, test via sandbox users.

**Rollout Strategies**

- **Tenant-Based Rollout:** Start with users or clients with the lowest usage or least critical workflows. Gradually migrate more tenants as confidence builds.
- **Geographic Rollout:** Migrate region by region, starting with smaller or lower-risk locations. This approach makes issues more manageable and localizes potential disruptions.
- **Traffic Splitting:** Use techniques like canary releases or blue-green deployments to split traffic. Start with a small percentage of users and gradually increase the proportion as the new services stabilize.
- **Feature Flagging:** Roll out features behind toggles to control who gets access. This allows testing in production with minimal risk and easy rollback if issues arise.
- **Progressive Cutover:** Migrate endpoints one by one, redirecting requests through the API Gateway to either the old or new service as each endpoint is verified.

**Key Takeaways**
The Strangler Pattern provides a practical way to evolve legacy systems, balancing modernization speed with system stability. Using API gateways and incremental rollouts, businesses can reduce risks, maintain service continuity, and transition to a future-proof architecture without disrupting existing users.

**Findings and Additional Links**

- [The Strangler Pattern: Kill Legacy Like a Boss](https://medium.com/@josesousa8/the-strangler-pattern-kill-legacy-like-a-boss-db3db41564ed)

