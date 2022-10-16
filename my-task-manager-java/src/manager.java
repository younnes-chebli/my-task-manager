import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class manager {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> tasks =
            new ArrayList<>(Arrays.asList("task1", "task2", "task3"));

    static void displayTasks(ArrayList<String> array) {
        System.out.println("---");
        for(String element : array) {
            System.out.println(element);
        }
    }

    static void continueOrExit(String option) {
        switch (option) {
            case "0":
                exit();
                break;
            default:
                showMain();
                break;
        }
    }

    static boolean isEmpty(ArrayList<String> array) {
        return array.size() == 0;
    }

    static void noTasks() {
        System.out.println("You have no tasks yet! " +
                "Continue [any key] - Exit [0]");
        String option = scan.nextLine();
        continueOrExit(option);
    }

    static void showTasks() {
        if(isEmpty(tasks)) {
            noTasks();
        } else {
            System.out.println("Here are your tasks " +
                    "Continue [any key] - Exit[0]:\n");
            displayTasks(tasks);
            String option = scan.nextLine();
            continueOrExit(option);
        }
    }

    static void askForNewAddTaskChoice (String option) {
        switch (option) {
            case "1":
                addTask();
                break;
            default:
                showMain();
                break;
        }
    }

    static void addTask() {
        displayTasks(tasks);
        System.out.println("Which task do you want to add? " +
                "(Cancel [0])");
        String task = scan.nextLine();
        if(task == "0") {
            showMain();
        } else if (task.isEmpty()) {
            addTask();
        } else {
            tasks.add(" " + task);
            displayTasks(tasks);
            System.out.println("Add another task? " +
                    "(Yes [1] - No [any key])");
            String option = scan.nextLine();
            askForNewAddTaskChoice(option);
        }
    }

    /*static void askForNewDeleteTaskChoice (String option) {
        switch (option) {
            case "1":
                deleteTask();
                break;
            default:
                showMain();
                break;
        }
    }*/

    static boolean outOfArray(ArrayList<String> array, int index) {
        return index < 0 || index > array.size();
    }

    static void deleteTask() {
        if(isEmpty(tasks)) {
            noTasks();
        } else {
            displayTasks(tasks);
            System.out.println("Which task do you want to delete? " +
                    "(give an index begginning from 1 - Cancel [0])");
            int option = scan.nextInt();
            if(option == 0) {
                showMain();
            } else if(outOfArray(tasks, option)) {
                deleteTask();
            } else {
                tasks.remove(option - 1);
                if(isEmpty(tasks)) {
                    showMain();
                } else {
                    /*System.out.println("Delete another task? " +
                            "(yes [1] - no [any key])");
                    String optionString = scan.nextLine();
                    askForNewDeleteTaskChoice(optionString);*/
                    deleteTask();
                }
            }
        }
    }

    /*static void askForNewMarkTaskDoneChoice (String option) {
        switch (option) {
            case "1":
                markTaskDone();
                break;
            default:
                showMain();
                break;
        }
    }*/

    static void markTaskDone() {
        displayTasks(tasks);
        System.out.println("Which task do you want to mark as done? " +
                "(give an index begginning from 1 - Cancel [0])");
        int option = scan.nextInt();
        if(option == 0) {
            showMain();
        } else if(outOfArray(tasks, option)) {
            markTaskDone();
        }else if(tasks.get(option - 1).contains("done")) {
            markTaskDone();
        } else {
            tasks.set(option - 1,
                    tasks.get(option - 1).concat(" (done)"));
            displayTasks(tasks);
            /*System.out.println("Which task do you want to mark as done? " +
                    "(give an index begginning from 1 - Cancel [0])");
            String optionString = scan.nextLine();
            askForNewMarkTaskDoneChoice(optionString);*/
            markTaskDone();
        }
    }

    static void exit() {
        System.out.println("Bye Bye ;-)");
        System.exit(0);
    }

    static void showMain() {
        System.out.println("Welcome to your task manager, Press:\n" +
                "1. to see all your tasks\n" +
                "2. to add a task\n" +
                "3. to delete a task\n" +
                "4. to mark a task as done\n" +
                "5. to Exit the task manager\n");
        String option = scan.nextLine();
        switch (option) {
            case "1":
                showTasks();
                break;
            case "2":
                addTask();
                break;
            case "3":
                deleteTask();
                break;
            case "4":
                markTaskDone();
                break;
            case "5":
                exit();
                break;
            default:
                showMain();
        }
    }

    public static void main(String[] args) {
        showMain();
    }
}
