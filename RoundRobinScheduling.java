import java.util.LinkedList;
import java.util.Queue;

class Process {
    int processId;
    int burstTime;
    int priority;
    Process next;

    Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;
    private int timeQuantum;

    RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    // Add process at the end
    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            tail.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
    }

    // Execute processes using Round Robin Scheduling
    public void executeProcesses() {
        if (head == null) return;
        Queue<Process> queue = new LinkedList<>();
        Process temp = head;
        do {
            queue.add(temp);
            temp = temp.next;
        } while (temp != head);

        int totalTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int processCount = queue.size();

        while (!queue.isEmpty()) {
            Process current = queue.poll();
            int executionTime = Math.min(current.burstTime, timeQuantum);
            totalTime += executionTime;
            current.burstTime -= executionTime;

            System.out.println("Executing Process " + current.processId + " for " + executionTime + " units");
            if (current.burstTime > 0) {
                queue.add(current);
            } else {
                int turnaroundTime = totalTime;
                int waitingTime = turnaroundTime - executionTime;
                totalTurnaroundTime += turnaroundTime;
                totalWaitingTime += waitingTime;
                System.out.println("Process " + current.processId + " completed. Turnaround Time: " + turnaroundTime + ", Waiting Time: " + waitingTime);
            }
        }

        System.out.println("\nAverage Waiting Time: " + (double) totalWaitingTime / processCount);
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / processCount);
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(3);
        scheduler.addProcess(1, 10, 1);
        scheduler.addProcess(2, 5, 2);
        scheduler.addProcess(3, 8, 3);
        
        System.out.println("Executing processes using Round Robin Scheduling:");
        scheduler.executeProcesses();
    }
}
