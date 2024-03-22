# US02 - As an HRM, I want to register a job.


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to register a job that a collaborator need to have.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> Jobs can only be registered by a Human Resources Manager (HRM).

**From the client clarifications:**

> **Question:** Quais são os dados de entrada para a criação de uma profissão?
>
> **Answer:** o Nome da profissão:
jardineiro
calceteiro
electricista
condutor
...

> **Question:** What are the acceptance criteria?
When are creating a job that already exit, what the system do?

>
> **Answer:**
By definition a set can´t have duplicates. Assuring no duplicates is not a business rule is a tecnichal issue.

> **Question:** Para a US02, gostaria de esclarecer o seguinte:
>- É relevante associar uma área ou setor específico a cada Job? (Por exemplo, "Jardineiro" seria inserido no setor de "Manutenção")
>- Deve-se incluir informações como salário, tipo de contratação (full-time ou part-time), e modalidade de trabalho (presencial, remoto ou híbrido) no Job? Ou essas informações encaixam-se melhor no âmbito do colaborador, ou talvez nem sejam necessárias?
>- Que outras informações acha necessárias associar ao Job?

> **Answer:** Bom dia,
>- não é necessário na medida que não existem US que sugiram que isso possa vir a ser necessário;
>- idem
>- para já nenhumas

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* n/a

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a job name

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - US02](svg/us002-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* n/a