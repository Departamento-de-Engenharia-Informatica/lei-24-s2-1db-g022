# US03 - As an HRM, I want to register a collaborator with a job and fundamental characteristics.

## 1. Requirements Engineering

### 1.1. User Story Description

US03 - As an HRM, I want to register a collaborator with a job and fundamental characteristics.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each information about the collaborator should be provided by the HRM.

**From the client clarifications:**

> **Question:** Should the system able the HRM to insert multiple collaborators in one interaction before saving them?
>
> **Answer:** it's not required to do so.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US02 - Register a job" as there must be at least one job to register a collaborator.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a name
    * a birthdate
    * an admission date
    * an address 
    * a mobile phone number
    * an email
    * an id doc type
    * an id doc number

* Selected data:
    * a job name

**Output Data:**

* List of existing jobs
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us03-system-sequence-diagram-us03.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.