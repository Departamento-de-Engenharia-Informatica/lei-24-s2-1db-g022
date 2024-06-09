package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a list of tasks to be completed.
 */
public class ToDoList {

    private final List<TaskToDoList> taskTdlList;
    private static int referenceCount = 0;

    /**
     * Constructs a new ToDoList object.
     */
    public ToDoList() {

        taskTdlList = new ArrayList<>();
    }

    /**
     * Prints the details of each taskToDoList in the to-do list.
     */
    public void ver() {

        for (TaskToDoList ttdl : this.taskTdlList) {

            System.out.println("------------");
            System.out.println(ttdl.toString());
            System.out.println("------------");
        }
    }

    /**
     * Adds a taskToDoList to the ToDoList.
     *
     * @param taskToDoList The taskToDoList to add.
     * @return True if the taskToDoList is successfully added, false otherwise.
     */
    private boolean addTaskToDoList(TaskToDoList taskToDoList) {

        boolean success = false;

        if (validateTaskToDoList(taskToDoList)) {

            success = taskTdlList.add(taskToDoList.clone());
        }

        return success;
    }

    /**
     * Validates if the taskToDoList is not already present in the ToDoList.
     *
     * @param taskToDoList The taskToDoList to validate.
     * @return True if the taskToDoList is valid (not already present), false otherwise.
     */
    private boolean validateTaskToDoList(TaskToDoList taskToDoList) {

        return !taskTdlList.contains(taskToDoList);
    }

    /**
     * Generates a unique reference for the taskToDoList.
     *
     * @return A unique reference for the taskToDoList.
     */
    private String generateUniqueReference() {
        referenceCount++;

        return "REF" + referenceCount;
    }

    /**
     * Registers an entry in the to-do list for a task related to a green space.
     *
     * @param greenSpace            The green space related to the task.
     * @param task                  The task to be completed.
     * @param urgency               The urgency level of the task.
     * @param aproxExpectedDuration The approximate expected duration of the task.
     * @return An Optional containing the registered taskToDoList if successful, otherwise an empty Optional.
     */
    public Optional<TaskToDoList> registerEntryToDoList(GreenSpace greenSpace, Task task, String urgency, int aproxExpectedDuration) {

        String ref = generateUniqueReference();

        Optional<TaskToDoList> optionalTaskToDoList = Optional.empty();

        TaskToDoList taskToDoList = new TaskToDoList(greenSpace, task, urgency, aproxExpectedDuration, ref);

        if (addTaskToDoList(taskToDoList)) {

            optionalTaskToDoList = Optional.of(taskToDoList);
        }

        return optionalTaskToDoList;
    }

    public List<TaskToDoList> getTaskByGreenSpace(GreenSpace greenSpace) {
        List<TaskToDoList> taskList = new ArrayList<>();
        for (TaskToDoList ttdl : taskTdlList) {
            if (ttdl.getGreenSpace().equals(greenSpace)) {
                taskList.add(ttdl);
            }
        }
        return taskList;
    }

    public Optional<TaskToDoList> getTaskToDoListByReference(String taskToDoListReference) {
        Optional<TaskToDoList> optionalTaskToDoList = Optional.empty();

        for (TaskToDoList ttdl : taskTdlList) {
            if (ttdl.hasRef(taskToDoListReference)) {
                optionalTaskToDoList = Optional.of(ttdl);
            }
        }
        return optionalTaskToDoList;
    }

    public boolean removeOldEntryToDoList(TaskToDoList taskToDoList) {
        return taskTdlList.remove(taskToDoList);
    }
}
