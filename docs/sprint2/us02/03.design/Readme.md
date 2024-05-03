# US02 - Register a job.

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...   | Answer                | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterJobUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | RegisterJobController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Job?                 | JobRepository         | Pure Fabrication: they form a collection of objects that do not “belong” to any domain object/class           |
| 			  		        | 							                                       | JobRepository         | IE: knows/has its own Job.                                                                                    |
| 			  		        | 							                                       | Job                   | IE: knows its own data (e.g. job)                                                                             |
| Step 2  		     | 	...saving the inputted data?                 | Job                   | IE: object created in step 1 has its own data.                                                                |
| Step 3  		     | 	... validating all data (local validation)?  | Job                   | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | JobRepository         | IE: knows all the jobs.                                                                                       | 
| 			  		        | 	... saving the created Job?                  | JobRepository         | IE: owns all the jobs.                                                                                        | 
| Step 4  		     | 	... informing operation success?             | RegisterJobUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Job

Other software classes (i.e. Pure Fabrication) identified:

* RegisterJobUI
* RegisterJobController
* JobRepository

## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us02-sequence-diagram.svg)

### Split Diagrams

n/a

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us02-class-diagram.svg)