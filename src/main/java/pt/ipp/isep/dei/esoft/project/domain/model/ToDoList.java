package pt.ipp.isep.dei.esoft.project.domain.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoList {

    private final List<TaskToDoList> taskTdlList;

    public ToDoList() {

        taskTdlList = new ArrayList<>();
    }

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
     * @param taskToDoList The skill to add.
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

    private String generateUniqueReference() {

        String str = "REF";

        boolean condition = true;

        while (condition) {

            String regex = "(\\D*)(\\d+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);

            if (matcher.find()) {

                String prefix = matcher.group(1); // Parte não numérica
                String numStr = matcher.group(2); // Parte numérica

                // Incrementa a parte numérica
                int num = Integer.parseInt(numStr);
                num++;

                // Reconstroi a string com o número incrementado
                str = prefix + num;

            } else {
                // Se não houver número no final, adiciona "1"
                str = str + "1";
            }

            condition = !validateRef(str);
        }

        return str;
    }

    private boolean validateRef(String ref) {

        for (TaskToDoList ttdl : taskTdlList) {

            if (ttdl.hasRef(ref)) {

                return false;
            }
        }

        return true;
    }

    public Optional<TaskToDoList> registerEntryToDoList(GreenSpace greenSpace, Task task, String urgency, int aproxExpectedDuration) {

        String ref = generateUniqueReference();

        Optional<TaskToDoList> optionalTaskToDoList = Optional.empty();

        TaskToDoList taskToDoList = new TaskToDoList(greenSpace, task, urgency, aproxExpectedDuration, ref);

        if (addTaskToDoList(taskToDoList)) {

            optionalTaskToDoList = Optional.of(taskToDoList);
        }

        return optionalTaskToDoList;
    }
}
