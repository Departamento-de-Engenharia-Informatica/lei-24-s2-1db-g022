# US21 - Add new entry to the To-Do-List

## 4. Tests

**Test 1:** Check that it is not possible to create an instance of the TaskToDoList class with null values - AC3.

	@Test(expected = IllegalArgumentException.class)
		public void ensureSkillIsNotNull() {
		TaskToDoList instance = new TaskToDoList(null,null,null,0,null);
	}

## 5. Construction (Implementation)

### Class RegisterEntryToDoListController

```java
    public Optional<TaskToDoList> registerEntryToDoList(String greenSpaceName, String taskDescription, String urgency, int aproxExpectedDuration) {

    Optional<Task> task = Optional.empty();
    Optional<GreenSpace> greenSpace = Optional.empty();
    Optional<TaskToDoList> taskTdl = Optional.empty();

    task = getTaskRepository().getTaskByDescription(taskDescription);
    greenSpace = getGreenSpaceRepository().getGreenSpaceByName(greenSpaceName);

    if (task.isPresent() && greenSpace.isPresent()) {

        taskTdl = getToDoList().registerEntryToDoList(greenSpace.get(), task.get(), urgency, aproxExpectedDuration);
    }

    getToDoList().ver();
    return taskTdl;
}
```

### Class GreenSpaceRepository

```java
    public Optional<GreenSpace> registerGreenSpace(String greenSpaceType, String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
    Optional<GreenSpace> optionalValue = Optional.empty();
    GreenSpace newGreenSpace = null;
    switch (greenSpaceType) {
        case "Garden":
            newGreenSpace = new Garden(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
            break;
        case "Medium Park":
            newGreenSpace = new MediumPark(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
            break;
        case "Large Park":
            newGreenSpace = new LargePark(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
            break;
    }

    if (addGreenGreenSpace(newGreenSpace)) {
        optionalValue = Optional.of(newGreenSpace);
    }

    return optionalValue;
}
```

```java
    private boolean addGreenGreenSpace(GreenSpace greenSpace) {
    boolean success = false;
    if (validateAllData(greenSpace)) {
        success = greenSpaceList.add(greenSpace);
    }

    return success;
}
```

```java
    private boolean addGreenGreenSpace(GreenSpace greenSpace) {
    boolean success = false;
    if (validateAllData(greenSpace)) {
        success = greenSpaceList.add(greenSpace);
    }

    return success;
}
```

### Class CollaboratorRepository

```java
    public Optional<ICollaborator> getCollaboratorByEmail(String email) {
        for (ICollaborator collaborator : collaboratorList) {
            if (collaborator.hasEmail(email)) {
                return Optional.of(collaborator);
            }

        }
        return Optional.empty();
    }
```

### Class TaskRepository

```java
    public List<Task> getAllTaskDescriptions() {

    return List.copyOf(taskList);
}
```

```java
    public Optional<Task> createTask(String taskDescription) {

    Optional<Task> optionalTask = Optional.empty();
    Task task = new Task(taskDescription);

    if (addTask(task)) {

        optionalTask = Optional.of(task);
    }

    return optionalTask;
}
```

```java
    public Optional<Task> getTaskByDescription(String taskDescription) {

    for (Task t : taskList) {

        if (t.hasDescription(taskDescription)) {

            return Optional.of(t);
        }
    }

    return Optional.empty();
}
```

### Class ToDoList

```java
    private boolean addTaskToDoList(TaskToDoList taskToDoList) {

    boolean success = false;

    if (validateTaskToDoList(taskToDoList)) {

        success = taskTdlList.add(taskToDoList.clone());
    }

    return success;
}
```

```java
    public Optional<TaskToDoList> registerEntryToDoList(GreenSpace greenSpace, Task task, String urgency, int aproxExpectedDuration) {

    String ref = generateUniqueReference();

    Optional<TaskToDoList> optionalTaskToDoList = Optional.empty();

    TaskToDoList taskToDoList = new TaskToDoList(greenSpace, task, urgency, aproxExpectedDuration, ref);

    if (addTaskToDoList(taskToDoList)) {

        optionalTaskToDoList = Optional.of(taskToDoList);
    }

    return optionalTaskToDoList;
}
```

```java
    private String generateUniqueReference() {
    referenceCount++;

    return "REF" + referenceCount;
}
```

## 6. Integration and Demo

* A new option on the GSM menu options was added.

* For demo purposes some Green Spaces associated with a GSM are bootstrapped  while system starts.

## 7. Observations

n/a