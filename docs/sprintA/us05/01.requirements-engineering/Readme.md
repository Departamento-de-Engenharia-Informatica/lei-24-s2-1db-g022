# US05 - Generate a team proposal automatically.


## 1. Requirements Engineering

### 1.1. User Story Description

As a HRM, I want to generate a team proposal automatically.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> A team has a fixed number of collaborators with different skills.

**From the client clarifications:**

> **Question:** 1. What information will the customer provide?
> 2. What should the output of the automation be? (should it just store the team proposal or show it to the customer?)
     Will the team proposal be a document about all the instructions of each team member/worker?
>
> **Answer:** The systems provide team proposals and HRM can accept of refuse the proposals. In the future (not in this
> sprint) HRM may decide to edit the team.

> **Question:**
> How does it generate the team if there are not enough employees?
> How does he propose a team, for what purpose? (Is there any predefinition)?
> What are the input data to automatically generate a team?
>
> **Answer:** The system should provide information why it can't generate a team.
> There is no purpose, at least in this sprint.
> the max size of the team (for instance 4)
> and the skill needed: 4 tree pruner and 1 light vehicle driver
> meaning that one team member have 2 skills.

### 1.3. Acceptance Criteria

* **AC1:** The maximum team size and the set of skills need to be supplied by the HRM.
* **AC2:** The number of collaborators must be an integer and positive above zero.

### 1.4. Found out Dependencies

* There is a dependency on "US01 - Create a skills" as there must be at least one skill to create a proposal.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a number size
	
* Selected data:
    * list of skills

**Output Data:**

* List of team proposals
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us05-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

n/a

### 1.7 Other Relevant Remarks

n/a