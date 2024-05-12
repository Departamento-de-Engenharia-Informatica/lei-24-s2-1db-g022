# US04 - As an HRM, I want to assign one or more skills to a collaborator.

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...   | Answer                  | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | AssignSkillUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | AssignSkillController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Skill?               | SkillRepository         | Pure Fabrication: they form a collection of objects that do not “belong” to any domain object/class.          |
| 			  		        | 							                                       | SkillRepository         | IE: knows/has its own Skill                                                                                   |
| 			  		        | 							                                       | Skill                   | IE: knows its own data (e.g. name)                                                                            |
| Step 2         |                                               |                         |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                 | Skill                   | IE: object created in step 1 has its own data.                                                                |
| Step 4         |                                               |                         |                                                                                                               |
| Step 5  		     | 	...  all data (local validation)?            | Skill                   | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | SkillRepository         | IE: knows all its skills.                                                                                     | 
| 			  		        | 	... saving the created Skill?                | SkillRepository         | IE: owns all its skills.                                                                                      | 
| Step 6  		     | 	... informing operation success?             | AssignSkillUI         | IE: is responsible for user interactions.                                                                     | 

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

![Sequence Diagram - Full](svg/us04-sequence-diagram.svg)

### Split Diagrams
3.2.1 Sequence diagram get_assignSkillCollaborator

![Sequence Diagram - get_AssignSkillCollaborator](svg/us04-sequence-diagram-get_AssignSkillCollaborator.svg)
3.2.2 Sequence diagram get_collaboratorList
![Sequence Diagram - get_collaboratorList](svg/us04-sequence-diagram-get_collaboratorList.svg)

3.2.3 Sequence diagram get_skillList
![Sequence Diagram - get_skillList](svg/us04-sequence-diagram-get_skillList.svg)



## 3.3. Class Diagram (CD)

![Class Diagram](svg/us04-class-diagram.svg)