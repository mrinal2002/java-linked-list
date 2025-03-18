class TaskNode {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    TaskNode next; // The never-ending cycle of tasks!

    TaskNode(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = this; // Self-loop for now, because loneliness is sad!
    }
}

class TaskScheduler {
    private TaskNode head, current; // Head keeps track, Current runs the show!

    // Add a task at the beginning - Because some tasks demand priority!
    public void addTaskAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newNode = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = current = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newNode.next = head;
            temp.next = newNode;
            head = newNode;
        }
    }

    // Add a task at the end - Because some tasks prefer a grand entry!
    public void addTaskAtEnd(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newNode = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = current = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    // Add a task at a specific position - Because some tasks like a custom seat!
    public void addTaskAtPosition(int position, int taskId, String taskName, int priority, String dueDate) {
        if (position <= 0) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        TaskNode newNode = new TaskNode(taskId, taskName, priority, dueDate);
        TaskNode temp = head;
        for (int i = 0; temp.next != head && i < position - 1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Remove a task by Task ID - Because some tasks must be *terminated*!
    public void removeTaskById(int taskId) {
        if (head == null) return; // No tasks? Must be a dream!
        
        TaskNode temp = head, prev = null;
        while (temp.taskId != taskId) {
            if (temp.next == head) return; // Task not found!
            prev = temp;
            temp = temp.next;
        }
        
        if (temp == head && temp.next == head) {
            head = null; // One task, now none!
            return;
        }
        if (temp == head) {
            prev = head;
            while (prev.next != head) prev = prev.next;
            head = head.next;
            prev.next = head;
        } else {
            prev.next = temp.next;
        }
    }

    // View current task and move to the next - Because time waits for no one!
    public void viewAndMoveToNextTask() {
        if (current == null) return;
        System.out.println("Current Task: " + current.taskName + " (Priority: " + current.priority + ")");
        current = current.next;
    }

    // Display all tasks - Because a well-planned schedule is a happy schedule!
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks scheduled!");
            return;
        }
        TaskNode temp = head;
        do {
            System.out.println(temp.taskId + ": " + temp.taskName + " | Priority: " + temp.priority + " | Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    // Search for a task by Priority - Because high-priority tasks deserve attention!
    public void searchTaskByPriority(int priority) {
        if (head == null) return;
        TaskNode temp = head;
        do {
            if (temp.priority == priority) {
                System.out.println(temp.taskId + ": " + temp.taskName + " | Priority: " + temp.priority);
            }
            temp = temp.next;
        } while (temp != head);
    }
}

public class CircularTaskScheduler {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        
        scheduler.addTaskAtEnd(1, "Finish report", 2, "2024-08-25");
        scheduler.addTaskAtBeginning(2, "Prepare slides", 1, "2024-08-24");
        scheduler.addTaskAtPosition(1, 3, "Team meeting", 3, "2024-08-23");
        
        System.out.println("All Tasks:");
        scheduler.displayAllTasks();
        
        System.out.println("Viewing and moving through tasks:");
        scheduler.viewAndMoveToNextTask();
        scheduler.viewAndMoveToNextTask();
        scheduler.viewAndMoveToNextTask();
        
        System.out.println("Searching for high-priority tasks:");
        scheduler.searchTaskByPriority(1);
        
        System.out.println("Removing task ID 2 (Prepare slides)...");
        scheduler.removeTaskById(2);
        scheduler.displayAllTasks();
    }
}
