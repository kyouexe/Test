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
