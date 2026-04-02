import java.io.*;
import java.util.*;

/**
 * =========================================================
 * TASK MANAGER SYSTEM (Console Based)
 * =========================================================
 * Features:
 * - Add Task
 * - View Tasks
 * - Delete Task
 * - Mark Task as Completed
 * - Sort Tasks by Priority
 * - Filter Completed Tasks
 * - Save & Load from File
 * =========================================================
 */
class Task implements Serializable {
    private String title;
    private String description;
    private String priority;
    private boolean isCompleted;
public Task(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }
public void markCompleted() {
        isCompleted = true;
    }
public boolean isCompleted() {
        return isCompleted;
    }
public String getPriority() {
        return priority;
    }
@Override
    public String toString() {
        return "Title: " + title +
               "\nDescription: " + description +
               "\nPriority: " + priority +
               "\nStatus: " + (isCompleted ? "Completed" : "Pending") +
               "\n---------------------------";
    }
}
public class TaskManager {

    private static final String FILE_NAME = "tasks.dat";
    private static List<Task> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
public static void main(String[] args) {
        loadTasks();
 while (true) {
            System.out.println("\n===== TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task Completed");
            System.out.println("5. Sort by Priority");
            System.out.println("6. Show Completed Tasks");
            System.out.println("7. Save & Exit");
int choice = sc.nextInt();
            sc.nextLine();
switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> deleteTask();
                case 4 -> markCompleted();
                case 5 -> sortTasks();
                case 6 -> showCompleted();
                case 7 -> {
saveTasks();
                    System.out.println("Tasks saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
private static void addTask() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
System.out.print("Enter description: ");
        String desc = sc.nextLine();
System.out.print("Enter priority (High/Medium/Low): ");
        String priority = sc.nextLine();

        tasks.add(new Task(title, desc, priority));
        System.out.println("Task added!");
    }
private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        int i = 1;
        for (Task task : tasks) {
            System.out.println("Task #" + i++);
            System.out.println(task);
        }
    }

private static void deleteTask() {
        viewTasks();
        System.out.print("Enter task number to delete: ");
        int index = sc.nextInt();

        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
            System.out.println("Task deleted!");
        } else {
            System.out.println("Invalid index!");
        }
    }
private static void markCompleted() {
        viewTasks();
        System.out.print("Enter task number to mark complete: ");
        int index = sc.nextInt();

        if (index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).markCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid index!");
        }
    }
private static void sortTasks() {
        tasks.sort((t1, t2) -> {
            List<String> order = Arrays.asList("High", "Medium", "Low");
            return order.indexOf(t1.getPriority()) - order.indexOf(t2.getPriority());
        });
        System.out.println("Tasks sorted by priority!");
    }
 private static void showCompleted() {
        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.println(task);
            }
        }
    }

    private static void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private static void loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) ois.readObject();
        } catch (Exception e) {
            tasks = new ArrayList<>();
        }
    }
}
