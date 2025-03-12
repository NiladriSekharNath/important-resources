# System Design: What is Availability?

## 1. Understanding Availability

**Availability** refers to the proportion of time a system is operational and accessible when required. It is typically expressed as a percentage, representing the system's uptime over a specific period.

The formula for availability is:

**Availability = Uptime / (Uptime + Downtime)**

- **Uptime:** The period during which a system is functional and accessible.
- **Downtime:** The period when the system is unavailable due to failures, maintenance, or other issues.

---

## Availability Tiers

Availability is often expressed in "nines," where each additional nine signifies an order of magnitude improvement in uptime.

| Availability | Downtime per year |
|--------------|-------------------|
| 99%          | ~3.65 days        |
| 99.9%        | ~8.76 hours       |
| 99.99%       | ~52.6 minutes     |
| 99.999%      | ~5.26 minutes     |

**Example:** 99.99% availability provides 10 times better uptime than 99.9%.

---

## Strategies for Improving Availability

1. **Redundancy**
    - Backup components take over if primary components fail.
    - **Techniques:**
        - **Server Redundancy:** Multiple servers to handle requests.
        - **Database Redundancy:** Replica databases as failover.
        - **Geographic Redundancy:** Resources spread across regions to prevent localized failures.

2. **Load Balancing**
    - Distributes incoming traffic to avoid overloading servers.
    - **Techniques:**
        - **Hardware Load Balancers:** Physical devices to manage traffic.
        - **Software Load Balancers:** Tools like HAProxy, Nginx, AWS ELB.

3. **Failover Mechanisms**
    - Automatic switch to backup systems during failures.
    - **Techniques:**
        - **Active-Passive Failover:** Standby components activate upon failure.
        - **Active-Active Failover:** All components run concurrently and share the load.

4. **Data Replication**
    - Copies data to multiple locations to prevent data loss.
    - **Techniques:**
        - **Synchronous Replication:** Real-time data copying.
        - **Asynchronous Replication:** Delayed copying, trading consistency for performance.

5. **Monitoring and Alerts**
    - Continuously checks system health and triggers alerts.
    - **Techniques:**
        - **Heartbeat Signals:** Regular signals to check component status.
        - **Health Checks:** Automated scripts verifying component health.
        - **Alerting Systems:** Tools like PagerDuty or OpsGenie for notifications.

---

## Best Practices for High Availability

- **Design for Failure:** Assume any component can fail and design accordingly.
- **Implement Health Checks:** Detect and address issues before they escalate.
- **Use Multiple Availability Zones:** Spread across data centers to prevent localized outages.
- **Practice Chaos Engineering:** Introduce controlled failures to test system resilience.
- **Implement Circuit Breakers:** Prevent cascading failures by isolating faulty services.
- **Use Caching Wisely:** Reduce load on backend systems with caching.
- **Plan for Capacity:** Ensure the system can handle sudden spikes in load.

---

Availability is a core pillar of system design, ensuring users can reliably access services. By combining techniques like redundancy, load balancing, failover, and monitoring, you can build resilient systems that maintain uptime even in the face of failures.

## Reference Notes

- [What is Availability](https://blog.algomaster.io/p/system-design-what-is-availability)

