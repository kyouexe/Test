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
