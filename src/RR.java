import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RR implements SchedulingAlgorithm {
    @Override
    public void execute(ArrayList<Process> processes) {

        int quantum = 2;
        int currentTime = processes.getFirst().getArrivalTime();

        Queue<Process> queue = new LinkedList<>();
        queue.add(processes.getFirst());

//        System.out.println("\nRound Robin Scheduling (Quantum = " + quantum + "):");
//        printHeader();

        int currentProcessIndex = 1;

        do {// While there are still processes to be executed

            Process currentProcess = queue.poll();

            int timeSlice = Math.min(quantum, currentProcess.getRemainingBurstTime());
            currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - timeSlice);
            currentTime += timeSlice;

            // Add processes to queue if they have arrived
            for (int i = currentProcessIndex; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() <= currentTime) {
                    queue.add(processes.get(i));
                    currentProcessIndex++;
                } else {
                    break;
                }
            }

            if (currentProcess.getRemainingBurstTime() == 0) {// If process is completed, update metrics

                currentProcess.setFinishTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());

//                printProcessInfo(currentProcess);
            } else {// If process is not completed, add to queue
                queue.add(currentProcess);
            }
        } while (!queue.isEmpty());
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
