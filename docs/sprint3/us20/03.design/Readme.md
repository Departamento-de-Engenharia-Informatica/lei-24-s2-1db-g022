# US20 - Register a Green Space

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...   | Answer                       | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:-----------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterGreenSpaceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | RegisterGreenSpaceController | Pure Fabrication: Controller                                                                                  |
| 			  		        | 	... instantiating a new Green Space?         | GreenSpaceRepository         | Pure Fabrication: they form a collection of objects that do not “belong” to any domain object/class.          |
| 			  		        | 							                                       | GreenSpaceRepository         | IE: knows/has its own Green Space                                                                             |
| 			  		        | 							                                       | GreenSpace                   | IE: knows its own data (e.g. name,address,area)                                                               |
| 			  		        | 							                                       | Address                      | IE: knows its own data (e.g. streetName,postCode)                                                             |
| Step 2         |                                               |                              |                                                                                                               |
| Step 3         |                                               |                              |                                                                                                               |
| Step 4         |                                               |                              |                                                                                                               |
| Step 5  		     | 	...saving the inputted data?                 | GreenSpace                   | IE: object created in step 1 has its own data.                                                                |
| Step 6         |                                               |                              |                                                                                                               |
| Step 7  		     | 	...  Address data (local validation)?        | Address                      | IE: owns its data.                                                                                            | 
|                | 	...  greenSpace data (local validation)?     | GreenSpace                   | IE: owns its data.                                                                                            |
| 			  		        | 	... validating all data (global validation)? | GreenSpaceRepository         | IE: knows all its GreenSpace.                                                                                 | 
| 			  		        | 	... saving the created Green Space?          | GreenSpaceRepository         | IE: owns all its GreenSpace.                                                                                  | 
| Step 8  		     | 	... informing operation success?             | RegisterGreenSpaceUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* GreenSpace

Other software classes (i.e. Pure Fabrication) identified:

* RegisterGreenSpaceUI
* RegisterGreenSpaceController
* RegisterGreenSpaceRepository

## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us01-sequence-diagram.svg)

### Split Diagrams

n/a

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us01-class-diagram.svg)