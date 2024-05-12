# US05 - Generate team.


## 1. Requirements Engineering

### 1.1. User Story Description

As a HRM, I want to generate a team proposal automatically.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> A team has a fixed number of collaborators with different skills.

**From the client clarifications:**

> **Question:**
>1. What information will the customer provide?
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

> **Question:**
> I would like to ask if, in this US, there should be any more information in the team generated beyond the
> collaborators in which it consists?
>
> I would also like to know if a collaborator can be in more than one team at the same time?
>
> **Answer:**
> The information shouldd contain each of team members and the its skills.
>
> no;

> **Question:**
> I would to know which business rules apply for the input data to generate a team proposal.
>
> **Answer:**
> max and min team size, and a a list of skills needed.
> For instance:
> min: 3
> max: 4

> **Question:**
> Are the skills(input) typed or selected
>
>Does the output show the team members with or without their skills?
>
> **Answer:**
> the ux/ui is up to the dev team.

> **Question:**
> Se, hipoteticamente, tivermos definidas as minSize e as maxSize da equipa como 3 e 5 respetivamente e ao verificar
> quantos colaboradores têm as respetivas skills chegamos à conclusao que temos 6 colaboradores com as skills
> necessárias,
> como é suposto sabermos se precisamos de gerar uma team com 3, 4 ou 5 elementos e qual o elemento para deixar de
> fora ?
>
> **Answer:**
> Algumas palavras chave que podem ajudar:
> Critérios (por exemplo, minimizar o número de elementos)
> Ordenação (de acordo com os critérios escolhidos)
> Algoritmos (apresentar propostas e interagir com o gestor de recursos humanos).

> **Question:**
> Cada equipa tem uma agenda para a semana? Ou a agenda é apenas para o parque (to-do list e doing-list and done-list)(
> with dates etc...)? cada parque tem uma
> The VFM assigned vehicles to the tasks? and equipment? Not the manager?
> The green space manager have access to the all program ? HRM only collaborators skills and jobs ! VFM only equipment,
> vehicles, machines!
> Ao gerar uma equipa automaticamente se por exemplo uma das skills for condutor de ligeiro e existir 3 colaboradores
> que cumprem isso qual o sistema vai introduzir mais depressa? Um que também cumpra outra skill pedida, ou escolhe qual?
> De que forma ele escolhe colaboradores mais precisamente?
> O maxSize da equipa pode ser menor que o número de Skills que são escolhidas?
> De que forma faço verificação do maxSize?
> O colaborador pode ter um Status? Tipo Activate e Not Activate? Eles nao se podem encontrar em mais do que duas
> equipas? Se sim, o Status servirá para isso
>
> **Answer:**
> Só existe uma agenda!
> No entanto é razoavel permitir vistas diferentes sobre a agenda em função do parque ou da equipa.
> De momento ainda não preciso atribuir viaturas e equipamentos às tarefas, este ponto será explicitado em breve.
> De momento não existem restrições para a seriação de equipas pelo que pode escolher um qualquer algoritmo que produza
> uma solução (caso exista) que satisfaça os requisitos; no caso de não existir deve reportar essa situação.
> O maxSize é o número máximo de elementos das equipa e pode ser menor que o número de skills pretendido.
> Não percebo a pergunta; não sei o que pretende validar.
> Não sei o que seja um Status, foi referido em alguma passagem do enunciado?
> Um colaborador não pode estar em mais do que uma equipa.
> PS: neste contexto, o cliente é alguem que gere espaços verdes e não um professor de informática!

### 1.3. Acceptance Criteria

* **AC1:** The minimum and maximum team size and the set of skills need to be supplied by the HRM.
* **AC2:** The number of collaborators(Min and Max) must be an integer and positive above zero.
* **AC3:** The number Max size of collaborators must be an integer and positive above or equals a Min size.
* **AC4:** The skills number size must be an integer and positive above a zero.
* **AC5:** All required fields must be filled in.
* **AC6:** An employee cannot be in more than one team.


### 1.4. Found out Dependencies

* There is a dependency on "US01 - Create a skills" as there must be at least one skill to create a proposal.
* There is a dependency on "US03 - Register a Collaborator" there must be at least one Collaborator registered in the
  system.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a team max size;
    * a team min size;
    * a number skills size.
    * a name skills.

**Output Data:**

* List of team proposals
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us05-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

n/a