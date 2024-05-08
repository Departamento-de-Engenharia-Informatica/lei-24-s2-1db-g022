# US07 - Register vehicle check-up.


## 1. Requirements Engineering

### 1.1. User Story Description

US07 - As a Vehicle and Equipment Fleet Manager, I want to register a vehicle’s check-up.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**
>Vehicle check-up can only be registered by a Human Resources Manager (VFM).

**From the client clarifications:**

> **Question:** Can a vehicle have more than one check-up?
>
> **Answer:** Yes.

> **Question:** Which attributes will you need for the vehicle's check-up?
>
> **Answer:** Plate number, date, kms at checkup.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** KM must be a number greater than zero.
* **AC3:** Date must follow this format: dd-mm-yyyy.

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register vehicle" as there must be at least one vehicle to be able to do a check-up.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * a license plate
  * a date
  * the km at checkup

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us07-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* N/A