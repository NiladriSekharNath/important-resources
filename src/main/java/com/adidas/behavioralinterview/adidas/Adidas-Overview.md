## Introduction ## 

## Tell me about yourself ## 

**"Hi, my name is Niladri, and I am a Software Engineer with over five years of experience in backend development, 
specializing in Java, Spring Boot, and microservices. I am currently part of the Digital Personalization team at Adidas,
where I focus on building scalable backend services that power features like 'You May Also Like' (YMAL) 
and 'Others Also Bought' (OAB) on Adidas.com and the mobile app. While the Data Science team generates ranked
recommendations using machine learning models—training them on user interactions, searches, and clicks—our team 
ensures these recommendations are efficiently processed, optimized, and served via the Personalization API.
We handle ingestion from S3, and store the recommendations in a regionally distributed 
DynamoDB setup for fast retrieval across different countries.

Previously, I was part of the Digital Asset Distribution team, working on optimizing how product images and videos are
displayed on product pages. One of my key contributions was developing a token-based access control feature, 
allowing early access to certain product images before their official launch. This was enabled through 
Cloudinary, a CDN tool, to dynamically control image visibility based on predefined tokens.

Overall, I enjoy designing and optimizing backend services that enhance user experiences and improve system performance. 
I’m excited to discuss how my expertise can contribute to your team!"**

---

## Amazon Leadership Principles ## 


# Tell me about a time you worked backwards from a customer problem. How did you solve it? #
# Tell me about the most complex problem that required a lost of in-depth anyalysis #

Covers: Customer Obsession, Dive Deep


SITUATION:

While working at Adidas on the Digital Personalization team, we noticed a recurring issue: our personalization APIs 
had slow response times, especially for users in different geographic regions. 
This delay negatively impacted the user experience for our e-commerce customers, especially during peak shopping periods.



TASK:

My responsibility was to investigate the bottlenecks and optimize the API’s performance,
ensuring that customers across all supported regions experienced fast and consistent personalization.



ACTION:

I started by profiling the API and identified that synchronous data fetching and configuration loading during runtime were causing delays.

To address this, I:

Implemented asynchronous processing using Java’s @Async annotation and Java virtual threads to parallelize non-blocking tasks.

Introduced token caching and periodic refresh mechanisms to pre-load region-specific configurations before app startup, 
avoiding on-demand calls.

Collaborated with cross-regional teams to validate configuration correctness and ensure that all regional needs were met.



RESULT:

These changes led to a 30% improvement in response times for the Personalization API. 
More importantly, it improved customer satisfaction and supported a smoother shopping experience across regions,
contributing to €1.14M in additional annualized net sales in the EU market alone.

---

 ## Tell me about a time when you helped others remove barriers/roadblocks toward meeting team goals. ##
 ## Tell me about a time when you pushed back against a decision that negatively impacted your team ## 

Covers : Deliver Results, Think Big

SITUATION:

At Adidas, multiple regional teams had to launch country-specific personalization and recommendation configurations. 
Each launch required manually creating configurations across environments—a time-consuming process prone to human error, 
often taking 4-5 days and slowing down our collective release goals.



TASK:

As part of the Digital Personalization team, I took ownership of removing this barrier for all regional teams. 
The goal was to automate and standardize the configuration deployment process so that teams could launch faster and with less friction.



ACTION:

I built a Java tool that performed asynchronous REST service calls to create and deploy configurations across environments. This tool:

Accepted region/country-specific inputs.

Triggered API calls asynchronously, eliminating serial processing delays.

Validated success and provided real-time feedback for error handling.



I collaborated with all regional teams to identify common pain points and ensured the tool could handle diverse use cases.
I also conducted a series of walkthroughs and documentation sessions so teams could adopt it independently.



RESULT:

The tool reduced configuration creation time from 4-5 days to under 30 seconds, effectively removing a huge bottleneck. 
This allowed teams to launch regional features faster, hitting critical business timelines during sales and events. 
It also freed up engineering bandwidth that would have otherwise gone into repetitive manual work.

---

## Tell me about a time when you took on something outside of your area of responsibility. ##

Covers: Ownership


SITUATION:

While working on the Digital Asset Distribution team at Adidas, our backend services often relied on AVRO schemas for 
communication with other microservices. However, developers had to manually create Java model classes every time these
schemas changed—something that was error-prone and outside the scope of most backend engineers’ usual workflow. 
This task typically fell under build/release engineering or platform teams.



TASK:

Though it wasn’t part of my direct responsibilities, I decided to automate this schema-to-model process to save time 
and reduce manual errors for the whole team. The goal was to integrate it seamlessly into our existing Java build process.



ACTION:

I created a custom Gradle script that:

Automatically parsed AVRO schemas during the build phase.

Generated the corresponding Java model classes dynamically.

Plugged into the existing Maven/Gradle pipeline so developers didn’t have to run extra commands.



I tested the integration across multiple microservices and validated compatibility with our CI/CD pipelines. 
I also documented the setup and shared it with other teams using similar architecture.



RESULT:

The script improved our build efficiency by 15% and reduced manual model creation by 80%. 
It also prevented version mismatches and runtime errors caused by outdated models. 
Even though it wasn’t my direct responsibility, this effort removed a friction point for the whole development team and 
improved confidence in schema-driven development.

---

## Tell me about a time you had to make a quick decision with incomplete information. ## 
## How did you make it and what was the outcome Tell me about a time when you were working on a tough deadline and 
## you did not have the time to consider all options. ##

Covers : Bias For Action 

At Cognizant, I was working on a legacy microservice that handled order snapshot generation for a major retail client.
During a high-traffic sales event, the system experienced frequent timeouts and throughput issues, 
risking failed order snapshots and potential business impact.


TASK:

I was tasked with stabilizing the service within 48 hours—we had no time for deep architectural overhauls or prolonged POCs. 
I had to act fast to ensure high availability while planning for long-term improvements later.


ACTION:

With incomplete documentation and limited observability in the legacy system, I focused on quick wins:

Enabled asynchronous REST calls to decouple blocking downstream dependencies.

Temporarily increased thread pool sizes to handle short-term spike load.

Used application logs and runtime metrics to identify the most expensive call paths.

Set up a circuit breaker for a downstream dependency known to cause retries under load.

Communicated changes proactively with QA and DevOps to avoid conflicts during deployment.


I also documented quick fixes and proposed a long-term architectural improvement (FaaS using GCS + Pub/Sub) after the incident.



RESULT:

The service stabilized within 24 hours, and we achieved a 35% increase in throughput.
It remained operational throughout the sales period without further escalation.

Later, we implemented the event-driven FaaS model, which reduced infrastructure costs by 20% and improved reliability.

---

## Q. Tell me about a time when you had to communicate a change in direction that you anticipated people would have concerns with. 
## What did you do to understand the concerns and mitigate them?

## Q. Describe a time when you were challenged to think differently in order to find a better solution to a problem. 
## When was the last time you intentionally took on something that was outside of your comfort zone?

SITUATION:

At Adidas, I was leading the backend redesign of our article recommendation system. We were relying on a pre-scored DynamoDB-based model, which was fast but rigid—it couldn’t adapt to real-time user behavior or dynamic product inventory. I proposed a shift to a real-time recommendation system powered by Databricks and regionally deployed APIs.

TASK:

I knew this direction would trigger concerns:

Engineers were wary of introducing latency and new dependencies.

Product managers feared delays due to integration complexity.

Ops teams were cautious about multi-region rollout risks and failure modes.

ACTION:

To gain alignment and proactively address concerns:

I ran a performance benchmarking analysis comparing the existing model with the proposed Databricks real-time system—using simulated traffic and cache warmups—to show we could meet performance SLAs.

I conducted one-on-one sessions with each stakeholder group to hear their objections and gather context.

Engineers flagged latency and instability.

PMs feared slipping deadlines.

Ops needed robust fallback mechanisms.

Based on the feedback, I introduced a configurable, JSON-driven architecture that:

Allowed us to switch dynamically between real-time and pre-scored models without code redeployments.

Supported toggling between different data sources (e.g., S3 ) and target DBs, giving full flexibility.

Enabled cron-based triggers for scheduled recommendations and fallback to real-time when conditions met.

I paired this with a region-by-region staged rollout, real-time observability dashboards, and automated alerts.



RESULT:

The rollout was completed successfully with zero downtime. We achieved a significant increase in article conversions, 
contributing to a €1.14M annualized uplift in EU sales.

More importantly, the JSON configuration became a standard interface across services—developers began building their 
features around it, and PMs cited it in roadmaps as a key enabler for market-specific experimentation.

---

 ## Q. Give a specific example where you drove adoption for your vision and explain how you knew it had been adopted by others.


SITUATION:

At Adidas, teams across different countries had to launch regional configurations for personalization APIs. 
The existing process was manual, slow, and error-prone, often taking 4–5 days for setup and verification. 
This was clearly unsustainable as we expanded into more markets.



TASK:

I believed we needed a more scalable, automated solution that could handle region-specific setups while reducing turnaround time.
However, this idea was not initially prioritized because stakeholders assumed manual configuration was just part of the release cycle.

My task was to drive adoption of a new configuration tool I envisioned, even though it wasn’t a formal requirement at the time.

ACTION:

I built a prototype of a Java-based tool that automated environment-specific configuration creation using asynchronous REST calls.

I pitched the vision to teams during a global tech sync, focusing on the business impact: faster launches, fewer human errors, 
and better developer productivity.


To make adoption easier, I:

Documented the tool clearly with use-case examples.

Added region-specific parameterization so teams could customize it without needing Java expertise.

Created a fallback to manual mode in case of errors, reducing perceived risk.

I personally helped with 2 different teams and supported their first few uses to ensure a smooth transition.



RESULT:

Within a month, 100% of the regional teams adopted the tool for configuration launches.
Launch time was reduced from 4–5 days to under 30 seconds, and manual configuration requests dropped to zero.

I knew my vision had been adopted because:

Teams began suggesting feature enhancements for the tool.

The tool became a standard part of our deployment checklist.

PMs still references this tool in sprint planning as a baseline expectation and infact the story pointing with 
configuration ticket changed drastically 

---

# Tell me about a time when you raised the quality bar by demanding that your team delivers high quality products,
# services and solutions. Tell me about a time when you used feedback about your team to drive a change

SITUATION:

While working on the Digital Asset Distribution platform at Adidas, I noticed that the asset-fetching API had several quality gaps:

It returned large, unpaginated result sets.

The queries were slow due to hard-coded logic.

Customers reported poor UX due to inconsistent product image ordering.



In parallel, feedback from internal QA and external partners highlighted that the API was hard to understand and 
error-prone due to lack of self-documenting structure.

TASK:

I took it upon myself to not only fix the immediate issues but also raise the overall bar for quality, maintainability, 
and observability across our services.

ACTION:

Optimized query logic by adopting JPA Criteria API, making it modular and dynamic—improving runtime flexibility 
and reducing query time by 15–20%.

Introduced pagination into the result APIs, cutting data load times by 20% and improving the client experience,
especially on slower connections.

Developed a custom image-sorting algorithm based on product type and country, 
significantly enhancing the UI experience and raising user engagement by 15%.

Based on feedback about poor API discoverability, I implemented Spring HATEOAS to provide rich, self-explaining 
hypermedia-driven responses—this helped consumers use the APIs with fewer onboarding sessions and reduced integration errors by 20–25%.

I initiated code reviews with stricter standards, encouraging the team to write more modular, testable code. 
I introduced a checklist for PR reviews focusing on readability, logging, and test coverage.



RESULT:

Within two sprints:

API performance and UX improved significantly.

Customer-reported issues dropped by over 25%.

Partner teams praised the new HATEOAS interface and reduced dependency on documentation sessions.

The team’s PR feedback cycle became faster and more structured.

The changes were so effective that they were upstreamed into other internal teams, 
and I was invited to present our best practices in an internal engineering forum.

---

## Tell me about a time when you had to make tradeoffs between quality and cost. How did you weigh the options? 
## What was the result? Would you have done anything differently?



SITUATION:

At Cognizant, I was working on a legacy order processing microservice for Macy’s during a critical retail sales period. 
The system was experiencing frequent performance bottlenecks under high traffic, 
leading to slow order fulfillment and timeout errors.
Ideally, the right solution was to rewrite core parts of the service or move to an event-driven architecture,
but this wasn’t feasible due to tight business timelines and cost constraints.



TASK:

My task was to stabilize the system within 48 hours. 
I had to weigh long-term code quality against the immediate need for resilience 
and throughput—without significantly increasing infrastructure costs or risking downtime during peak hours.


ACTION:

After analyzing logs and performance traces, I decided to:

Introduce asynchronous REST calls and lightweight thread pooling to reduce blocking.

Optimize just-in-time serialization and cache frequent object responses.

Temporarily scale thread pools and retry logic without rewriting business logic.

Avoid introducing new cloud services to keep cost flat, using existing GCS and Pub/Sub infrastructure.



I documented all changes with clear TODOs for follow-up refactoring, and presented a long-term architecture proposal post-sales.


RESULT:

We achieved a 35% increase in throughput within 24 hours, and the system remained stable during the peak event. 
There were no escalations or order failures reported, and customer satisfaction remained intact.



WOULD I DO ANYTHING DIFFERENTLY?

Yes. In hindsight, I would’ve:

Pushed earlier for observability improvements (e.g., better metrics and alerts),
which would’ve helped us catch performance trends proactively.

Scoped a gradual modularization plan so that we could refactor parts of the service incrementally without waiting for a crisis.

---

# Q: Tell me about a time when you helped a remote team member develop their career.



SITUATION:

While working on the Digital Asset Distribution platform at Adidas, I collaborated with a remote backend engineer 
who had recently transitioned from QA automation. 
He was eager to grow but lacked confidence with backend architecture and production ownership.

TASK:

Though I wasn’t his assigned mentor, I took ownership of helping him grow from a task executor into a confident,
contributing backend developer with long-term career ownership.



ACTION:

I started by pairing with him on enhancements like pagination and modular query construction using JPA Criteria API, 
explaining key backend concepts along the way.

I identified a safe but high-impact feature—implementing image sorting logic on the product listing UI based on region 
and product type, and encouraged him to take end-to-end ownership.

I provided support through code reviews, unit testing best practices, and system behavior analysis under load.

To help with career visibility, I coached him to demo the feature during our global sprint review and 
articulate the customer and business impact.



RESULT:

His feature led to a 15% boost in user engagement, and his contribution was highlighted in our quarterly business review.

He was later trusted to lead enhancements on another shared service.

He personally shared that this experience helped him transition successfully into an SDE II role.



This experience reinforced my belief that leadership is not about titles—it’s about raising the bar for others
and helping them succeed in ways they didn’t think possible.






