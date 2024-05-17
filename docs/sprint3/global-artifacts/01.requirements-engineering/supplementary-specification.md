# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

**Security:**

* All those who wish to use the application must be authenticated.
* The application must be authenticated with a password of seven alphanumeric characters, including three capital
  letters and two digits.

**Workflow**
* Business rules validation must be respected when recording and updating data.

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

## Reliability

n/a

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

n/a

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

**Localisation**
* The application documentation must be in English language.

**Maintainability**
* The class structure must be designed to allow easy maintenance and addition of new features, following the best Object-Oriented (OO) practices.

**Testability**
* The development team must implement unit tests for all methods, except for the methods that implement Input/Output operations.
* The unit tests should be implemented using the JUnit 5 framework.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

**Reporting**
* The JaCoCo plugin should be used to generate the coverage report.

**Persistence**
* The application ought to employ object serialization to guarantee the data persistence across two successive runs.

## Implementation Constraints

**Implementation languages**
* The application must be developed in Java Language.
* All the images/figures produced during the software development process should be recorded in SVG format.

**Standards-compliance**
* The class structure must be designed to allow easy maintenance and addition of new features, following the best Object-Oriented (OO) practices.
* Adopt recognized coding standards (e.g., CamelCase).
* Use Javadoc to generate useful documentation for Java code.

## Interface Constraints

**Interface Requirements**
* The application’s graphical interface is to be developed in JavaFX 11.