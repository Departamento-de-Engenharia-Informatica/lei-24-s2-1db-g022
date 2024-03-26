# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

**Security:**

* All those who wish to use the application must be authenticated.
* The application must be authenticated with a password of seven alphanumeric characters, including three capital
  letters and two digits.


## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

**Help and documentation:**

* The team must use Javadocs to generate useful documentation for Java Code.
* The application and all the documents support the English language.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

* The software should be secure and protect confidential information.

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

* All activities must have a quick response from the system.
* The program ought to use little memory and CPU.

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

The team must adopt:

* Best practices for identifying requirements and for OO software analysis and design,
* Recognized coding conventions and standards (e.g., Camel Case).

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

**Programming Languages:**

* The application must be developed in Java language using the IntelliJ IDE or Netbeans.
* The application graphical interface is to developed in JavaFX 11.
* The unit tests should be implemented using the JUnit framework.

**Mandatory standards/patterns:**

* All the images/figures produced during the software development process should be recorded in SVG format.
* The application should use object serialization to ensure data persistence between two runs of the application.

**Development Tools:**

* The unit should be implemented using the JUnit 5 framework.
* The JaCoCo plugin should be used to generate the coverage report.
