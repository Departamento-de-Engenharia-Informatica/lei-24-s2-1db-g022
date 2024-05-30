# US21 - Add new entry to the To-Do-List

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...       | Answer                          | Justification (with patterns)                                                                                       |
|:---------------|:--------------------------------------------------|:--------------------------------|:--------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                  | RegisterEntryToDoListUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.       |
| 			  		        | 	... coordinating the US?                         | RegisterEntryToDoListController | Pure Fabrication: Controller                                                                                        |
| 			  		        | 	... instantiating a new entry in ToDoList?       | ToDoList                        | Pure Fabrication: they form a collection of objects that do not “belong” to any domain object/class.                |
| 			  		        | ... knowing the user using the system?            | UserSession                     | IE: cf. A&A component documentation.                                                                                |
| 			  		        | 							                                           | ToDoList                        | IE: knows/has its own Task                                                                                          |
| 			  		        | 							                                           | ToDoList                        | IE: knows its own data (e.g. Task)                                                                                  |
| 			  		        | 	... instantiating a new Task?                    | ToDoList                        | Creator: <br/>(Rule 1): in the DM ToDoList has a Task(Entries). <br/>(Rule 2) : ToDoList records instances of Task. |
| 			  		        | 							                                           | Task                            | IE: knows its own data (e.g. urgency)                                                                               |
| Step 2         | ...knowing the list of GreenSpace by GSM to show? | GSM                             | IE:GreenSpace list managed by GSM.                                                                                  |
| Step 3         | ... saving the selected type?                     | GreenSpace                      | IE: object created in step 1 is classified in one type.                                                             |
| Step 4         |                                                   |                                 |                                                                                                                     |
| Step 5  		     | 	...saving the inputted data?                     | Task                            | IE: object created in step 1 has its own data.                                                                      |
| Step 6         |                                                   |                                 |                                                                                                                     |
| Step 7  		     | 	...  Task data (local validation)?               | Task                            | IE: owns its data.                                                                                                  | 
|                | 	...  ToDoList data (local validation)?           | ToDoList                        | IE: owns its data.                                                                                                  |
| 			  		        | 	... validating all data (global validation)?     | ToDoList                        | IE: knows all its Task.                                                                                             | 
| 			  		        | 	... saving the created Task?                     | ToDoList                        | IE: owns all its Task.                                                                                              | 
| Step 8  		     | 	... informing operation success?                 | RegisterEntryToDoListUI         | IE: is responsible for user interactions.                                                                           | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* GreenSpace
* GSM
* Task
* ToDoList

Other software classes (i.e. Pure Fabrication) identified:

* RegisterEntryToDoListUI
* RegisterEntryToDoListController

## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us21-sequence-diagram.svg)

### Split Diagrams

Get Green Space Manager

![Sequence Diagram - Split Manager](svg/us21-partial-sequence-diagram-get-GSM.svg)

Add Entry To Do List

![Sequence Diagram - Split Entry](svg/us21-sequence-diagram-partial-add-to-do-list.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us21-class-diagram.svg)