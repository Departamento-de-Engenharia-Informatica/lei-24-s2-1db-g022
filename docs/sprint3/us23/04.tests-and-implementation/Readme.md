# US23 - Assign Team to entry in the Agenda

## 4. Tests

**Test 1:** Different email services can send the message. These services must be defined through a configuration file to allow the use of different platforms (e.g. Gmail, DEI’s email service, etc.) - AC2 and AC1.
<br>
### DEI Service

    @Test
    public void sendEmail() {
        DeiEmailService emailService = new DeiEmailService();
        boolean success = emailService.sendEmail("teste@teste.pt", "ola",0);
        assertTrue(success);
    }

    @Test
    public void nullEmailSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail(null, "ola",0);
        assertFalse(success);
    }

    @Test
    public void nullMsgSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail("teste@teste.pt", null,0);
        assertFalse(success);
    }
    @Test
    public void nullParametreSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail(null, null,0);
        assertFalse(success);
    }

### Gmail Service

    @Test
    public void sendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail("teste@teste.pt", "ola",0);
        assertTrue(success);
    }

    @Test
    public void nullEmailSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail(null, "ola",0);
        assertFalse(success);
    }

    @Test
    public void nullMsgSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail("teste@teste.pt", null,0);
        assertFalse(success);
    }
    @Test
    public void nullParametreSendEmail() {
        GmailEmailService emailService = new GmailEmailService();
        boolean success = emailService.sendEmail(null, null,0);
        assertFalse(success);
    }

## 5. Construction (Implementation)

### Class AssignTeamAgendaController

```java
public List<TeamDto> getAllTeams() {
    // Retrieve the list of teams from the repository
    List<Team> teams = getTeamRepository().getTeamList();

    // Map Team objects to DTOs using TeamMapper
    TeamMapper mapper = new TeamMapper();
    return mapper.toDTO(teams);
}


/**
 * Retrieves all task agendas associated with the green spaces managed by the currently logged-in green space manager.
 *
 * @return A list of DTOs representing the task agendas.
 */
public List<TaskAgendaDto> getAllTaskByGSM() {
    List<TaskAgenda> listTaskAgenda = new ArrayList<>();

    // Retrieve the GreenSpaceManager from the session
    GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();

    // Retrieve the collaborator from the repository using the GreenSpaceManager's email
    Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

    if (collaboratorOptional.isPresent()) {
        // Convert the collaborator to GreenSpaceManager
        GreenSpaceManager greenSpaceManager = (GreenSpaceManager) collaboratorOptional.get();

        // Retrieve the list of green spaces managed by the GreenSpaceManager
        List<GreenSpace> greenSpaceList = getListGreenSpace(greenSpaceManager);

        if (!greenSpaceList.isEmpty()) {
            // Retrieve all task agendas associated with the green spaces
            listTaskAgenda = getAllTaskByGreenSpace(greenSpaceList);
        }
    }

    // Map TaskAgenda objects to DTOs using TaskAgendaMapper
    TaskAgendaMapper mapper = new TaskAgendaMapper();
    return mapper.toDTOTaskToDoList(listTaskAgenda);
}

/**
 * Retrieves all tasks associated with the specified list of green spaces.
 *
 * @param greenSpaceList The list of green spaces for which tasks will be retrieved.
 * @return A list of task agendas associated with the provided green spaces.
 */
private List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {

    return getAgenda().getAllTaskByGreenSpace(greenSpaceList);
}

/**
 * Retrieves a list of green spaces from the specified green space manager.
 *
 * @param greenSpaceManager The green space manager from which the list of green spaces will be retrieved.
 * @return A list of green spaces.
 */
private List<GreenSpace> getListGreenSpace(GreenSpaceManager greenSpaceManager) {
    return greenSpaceManager.getListGreenSpaces().getGreenSpaceList();
}

/**
 * Retrieves the GreenSpaceManager associated with the current user session.
 *
 * @return The GreenSpaceManager object associated with the current user session.
 */
private GreenSpaceManager getGSMFromSession() {

    Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();

    return new GreenSpaceManager(email.getEmail());
}

/**
 * Assigns a task referenced by its ID to a team.
 *
 * @param teamId The ID of the team to which the task will be assigned.
 * @param taskReference The reference of the task to be assigned.
 * @return {@code true} if the task was successfully assigned to the team and an email notification was sent; {@code false} otherwise.
 */
public boolean assignTeamTask(int teamId, String taskReference) {
    Optional<Team> teamOptional = getTeamRepository().getTeamById(teamId);

    if (teamOptional.isPresent()) {
        Optional<TaskAgenda> taskAgendaOptional = getAgenda().getTaskByReference(taskReference);

        if (taskAgendaOptional.isPresent()) {
            if (assignTeamTask(teamOptional.get(), taskAgendaOptional.get())) {
                return sendEmailTeam(teamOptional.get(), "You were given this task [" + taskAgendaOptional.get().getTaskToDoList().getTaskRef() + "] - \"" + taskAgendaOptional.get().getTaskToDoList().getGreenSpace().getName() + " " + taskAgendaOptional.get().getTaskToDoList().getTask().getTaskDescription() + "\"");
            }

        }

    }

    return false;
}

/**
 * Sends an email to the specified team.
 *
 * @param team The team to whom the email will be sent.
 * @param msg The message to be sent.
 * @return {@code true} if the email was successfully sent; {@code false} otherwise.
 */
private boolean sendEmailTeam(Team team, String msg) {
    return team.sendEmail(msg);
}

/**
 * Assigns a task agenda to the specified team.
 *
 * @param team The team to which the task agenda will be assigned.
 * @param taskAgenda The task agenda to be assigned.
 * @return {@code true} if the task agenda was successfully assigned to the team; {@code false} otherwise.
 */
private boolean assignTeamTask(Team team, TaskAgenda taskAgenda) {
    return getAgenda().assignTeamTask(team, taskAgenda);
}
```

### Class Agenda

```java
    /**
 * Retrieves all tasks associated with the specified list of green spaces.
 *
 * @param greenSpaceList The list of green spaces for which tasks will be retrieved.
 * @return A list of task agendas associated with the provided green spaces.
 */
public List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {
    return tasksAgenda.getAllTaskByGreenSpace(greenSpaceList);
}

/**
 * Retrieves a task agenda by its reference.
 *
 * @param taskReference The reference of the task agenda to retrieve.
 * @return An Optional containing the task agenda if found, or empty if not found.
 */
public Optional<TaskAgenda> getTaskByReference(String taskReference) {
    return tasksAgenda.verifyHasTaskReference(taskReference);
}

/**
 * Assigns a task agenda to a team.
 *
 * @param team The team to which the task agenda will be assigned.
 * @param taskAgenda The task agenda to be assigned.
 * @return {@code true} if the task agenda was successfully assigned to the team, {@code false} otherwise.
 */
public boolean assignTeamTask(Team team, TaskAgenda taskAgenda) {
    return tasksAgenda.assignTeamTask(team, taskAgenda);
}
```
### Class TeamRepository

```java
public List<Team> getTeamList() {
    return teamList;
}

public Optional<Team> getTeamById(int id) {

    for (Team t : teamList) {
        if (t.verifyIdTeam(id)) {
            return Optional.of(t);
        }
    }
    return Optional.empty();
}
```

### Class TasksAgenda

```java
public boolean assignTeamTask(Team team, TaskAgenda taskAgenda){
    for (TaskAgenda tA : taskAgendaList){
        if (tA.equalsTask(taskAgenda)){
            tA.updateTeam(team);
            return true;
        }
    }
    return false;
}
```
### Class GmailEmailService

```java
/**
 * Sends an email using Gmail.
 *
 * @param email The recipient's email address.
 * @param msg   The message to be sent.
 * @return true if the email was sent successfully, false otherwise.
 */
@Override
public boolean sendEmail(String email, String msg, int delay) {
    Properties properties = new Properties();

    try {
        if (email == null || email.isEmpty() || msg == null || msg.isEmpty()) {
            return false;
        }
        InputStream in = new FileInputStream("src\\main\\resources\\config.properties");
        properties.load(in);
        in.close();

        System.out.println("\n-------Sending Email [GMAIL]-------");
        Thread.sleep(delay);
        System.out.println("From: " + properties.getProperty("Company.email"));
        System.out.println("Sending email to " + email);
        System.out.println("Message: " + msg);
        System.out.println("-----------------------------------");

    } catch (InterruptedException | IOException e) {
        return false;
    }

    return true;
}
```
### Class GmailEmailService

```java
/**
 * Sends an email using the DEI email service.
 *
 * @param email The email address of the recipient.
 * @param msg   The message content of the email.
 * @return {@code true} if the email was sent successfully; {@code false} otherwise.
 */
@Override
public boolean sendEmail(String email, String msg,int delay) {
    Properties properties = new Properties();


    try {
        if (email == null || email.isEmpty() || msg == null || msg.isEmpty()) {
            return false;
        }
        InputStream in = new FileInputStream("src\\main\\resources\\config.properties");
        properties.load(in);
        in.close();

        System.out.println("\n-------Sending Email [DEI]-------");
        Thread.sleep(delay);
        System.out.println("From: " + properties.getProperty("Company.email"));
        System.out.println("Sending email to " + email);
        System.out.println("Mensage: " + msg);
        System.out.println("---------------------------------");

    } catch (InterruptedException | IOException e) {
        return false;
    }

    return true;
}
```

### Interface GmailEmailService

```java
public interface EmailService {

    /**
     * Sends an email message to the specified email address.
     *
     * @param email The recipient's email address.
     * @param msg   The message content.
     * @return {@code true} if the email was sent successfully; {@code false} otherwise.
     */
    boolean sendEmail(String email, String msg,int delay);
}
```

## 6. Integration and Demo

* A new option on the GSM menu options was added.

## 7. Observations

n/a