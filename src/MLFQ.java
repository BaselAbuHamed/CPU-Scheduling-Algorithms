import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MLFQ implements SchedulingAlgorithm {

    static int temp = 0;

    @Override
    public void execute(ArrayList<Process> processes) {
        multilevelFeedbackQueues(processes);
    }

    private void multilevelFeedbackQueues(ArrayList<Process> processes) {

        Queue<Process> q1 = new LinkedList<>(); // RR with time quantum = 8
        Queue<Process> q2 = new LinkedList<>(); // RR with time quantum = 16
        Queue<Process> q3 = new LinkedList<>(); // FCFS

        int currentTime = processes.getFirst().getArrivalTime();// Start time

//        System.out.println("\nMultilevel Feedback Queues Scheduling:");
//        printHeader();

        while (!(q1.isEmpty() && q2.isEmpty() && q3.isEmpty() && processes.isEmpty())) {// While there are still processes to be executed

            if (temp != 0) {// If a process was executed, increment time
                currentTime += temp;
                temp = 0;
            }
            if (!(processes.isEmpty()) && processes.getFirst().getArrivalTime() <= currentTime) {// Add processes to queues if they have arrived
                addProcessesToQueue(processes, q1, currentTime);
            }

            if (!q1.isEmpty()) {
                executeProcesses(q1, 8, currentTime, q2);
            }
            else if (!q2.isEmpty()) {
                executeProcesses(q2, 16, currentTime, q3);
            }
            else if (!q3.isEmpty()) {
                executeProcesses(q3, Integer.MAX_VALUE, currentTime, null);
            }
            else {
                currentTime++;
            }
        }
    }

    private void addProcessesToQueue(ArrayList<Process> processes, Queue<Process> queue, int currentTime) {
        while (!processes.isEmpty() && processes.getFirst().getArrivalTime() <= currentTime) {
            Process process = processes.removeFirst();
            queue.offer(process);
        }
    }

    private void executeProcesses(Queue<Process> queue, int timeQuantum, int currentTime, Queue<Process> lowerPriorityQueue) {

        if (!queue.isEmpty()) { // Execute processes in the queue

            Process process = queue.poll();
            int timeSliceUsed = Math.min(timeQuantum, process.getRemainingBurstTime());

            currentTime += timeSliceUsed;
            process.setRemainingBurstTime(process.getRemainingBurstTime() - timeSliceUsed);

            temp = timeSliceUsed;
//            printProcessInfo(process);

            if (process.getRemainingBurstTime() == 0) { // Process is completed

                process.setFinishTime(currentTime);
                process.setTurnaroundTime(currentTime - process.getArrivalTime());
                process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());

//                printProcessInfo(process);
            }
            else if (lowerPriorityQueue != null && process.getRemainingBurstTime() > 0) { // Process is not completed
                lowerPriorityQueue.offer(process);
            }
        }
    }

    private void printHeader() {
        System.out.printf("%-12s%-12s%-12s%-12s%-15s%-15s%-15s%-15s \n",
                "Process", "Arrival", "Burst", "Completion", "Turnaround", "Waiting", "Order", "remainingBurstTime");
    }

    private void printProcessInfo(Process process) {
        System.out.printf("%-12d%-12d%-12d%-12d%-15d%-15d%-15d%-15d%n",
                process.getProcessId(), process.getArrivalTime(),
                process.getBurstTime(), process.getFinishTime(),
                process.getTurnaroundTime(), process.getWaitingTime(),
                process.getProcessId(), process.getRemainingBurstTime());
    }
}