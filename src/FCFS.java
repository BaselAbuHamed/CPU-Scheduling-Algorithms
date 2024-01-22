import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FCFS implements SchedulingAlgorithm {

    @Override
    public void execute(ArrayList<Process> processes) {
        int currentTime = 0;
        int completedProcesses = 0;

        Queue<Process> queue = new LinkedList<>(processes);
        List<Process> completedProcessesList = new ArrayList<>();

//        System.out.println("\nFCFS Scheduling:");
//        printHeader();

        while (!queue.isEmpty() || completedProcesses < processes.size()) {// While there are still processes to be executed

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();
                currentProcess.setFinishTime(Math.max(currentTime, currentProcess.getArrivalTime()) + currentProcess.getBurstTime());
                currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());

//                printProcessInfo(currentProcess);

                currentTime = currentProcess.getFinishTime();
                completedProcesses++;
                completedProcessesList.add(currentProcess);
            } else {
                currentTime++;
            }
        }
        processes.clear();
        processes.addAll(completedProcessesList);
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