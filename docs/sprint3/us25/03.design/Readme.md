# US25 - Cancel an entry in the Agenda

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...   | Answer                  | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterSkillUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | RegisterSkillController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Skill?               | SkillRepository         | Pure Fabrication: they form a collection of objects that do not “belong” to any domain object/class.          |
| 			  		        | 							                                       | SkillRepository         | IE: knows/has its own Skill                                                                                   |
| 			  		        | 							                                       | Skill                   | IE: knows its own data (e.g. name)                                                                            |
| Step 2         |                                               |                         |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                 | Skill                   | IE: object created in step 1 has its own data.                                                                |
| Step 4         |                                               |                         |                                                                                                               |
| Step 5  		     | 	...  all data (local validation)?            | Skill                   | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | SkillRepository         | IE: knows all its skills.                                                                                     | 
| 			  		        | 	... saving the created Skill?                | SkillRepository         | IE: owns all its skills.                                                                                      | 
| Step 6  		     | 	... informing operation success?             | RegisterSkillUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Skill

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterSkillUI
* RegisterSkillController
* SkillRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us01-sequence-diagram.svg)

### Split Diagrams

n/a

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us01-class-diagram.svg)