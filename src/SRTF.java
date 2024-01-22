import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SRTF implements SchedulingAlgorithm{
    @Override
    public void execute(ArrayList<Process> processes) {
        int currentTime = 0;
        int completedProcesses = 0;

//        System.out.println("\nSRTF (SJF with Preemption) Scheduling:");
//        printHeader();

        PriorityQueue<Process> minHeap = new PriorityQueue<>(Comparator.comparingInt(Process::getRemainingBurstTime));

        Process currentProcess = null;

        // While there are still processes to be executed
        while ((completedProcesses < processes.size() || !minHeap.isEmpty()) || currentProcess != null) {

            // Add processes to minHeap if they have arrived
            while (!processes.isEmpty() && processes.getFirst().getArrivalTime() <= currentTime) {
                minHeap.offer(processes.removeFirst());
            }

            if (currentProcess == null && !minHeap.isEmpty()) {// If there is no current process, poll from minHeap
                currentProcess = minHeap.poll();

            }
            /* If there is a current process and there is a process
               in the minHeap with a smaller burst time, preempt the current process */
            if (currentProcess != null && !minHeap.isEmpty() &&
                    minHeap.peek().getRemainingBurstTime() < currentProcess.getRemainingBurstTime() ) {

                minHeap.offer(currentProcess);
                currentProcess = minHeap.poll();

            }
            if (currentProcess != null) {// If there is a current process, execute it

                currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - 1);

                // If current process is completed, update metrics
                if (currentProcess.getRemainingBurstTime() == 0) {

                    currentProcess.setFinishTime(currentTime + 1);
                    currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
                    currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());

//                    printProcessInfo(currentProcess);

                    currentProcess.setRemainingBurstTime(0); // Process completed
                    completedProcesses++;
                    currentProcess = null; // Reset currentProcess after completion
                }
            }
            currentTime++; // Increment currentTime
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
