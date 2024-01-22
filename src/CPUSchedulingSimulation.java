import java.util.ArrayList;
import java.util.Comparator;

public class CPUSchedulingSimulation {

    public static void main(String[] args) {

        int[] iterations = {1, 100, 1000, 10000, 100000};
        System.out.println("Simulation Results:");
        System.out.println("-------------------");

        double[][] fcfsTime = new double[2][iterations.length];
        double[][] srtfTime = new double[2][iterations.length];
        double[][] rrTime = new double[2][iterations.length];
        double[][] mlfqTime = new double[2][iterations.length];

        for (int i = 0; i < iterations.length; i++) {// Run each algorithm for each iteration

            runSimulation("FCFS", iterations[i], fcfsTime, i);
            runSimulation("SRTF", iterations[i], srtfTime, i);
            runSimulation("RR", iterations[i], rrTime, i);
            runSimulation("MLFQ", iterations[i], mlfqTime, i);
        }

        print2DArray(fcfsTime, "FCFS", iterations);
        print2DArray(srtfTime, "SRTF", iterations);
        print2DArray(rrTime, "RR", iterations);
        print2DArray(mlfqTime, "MLFQ", iterations);
    }
    private static void runSimulation(String algorithm, int iterations, double[][] time ,int iterationNumber) {

        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;

        for (int i = 0; i < iterations; i++) {// Run each algorithm for each iteration

            ArrayList<Process> processes = generateRandomProcesses(8);

            switch (algorithm) {
                case "FCFS":
                    FCFS fcfs = new FCFS();
                    ArrayList<Process> processesFCFS = copyProcessList(processes);
                    fcfs.execute(new ArrayList<>(processesFCFS));

                    totalTurnaroundTime += calculateTotalTurnaroundTime(processesFCFS);
                    totalWaitingTime += calculateTotalWaitingTime(processesFCFS);
                    break;
                case "SRTF":
                    SRTF srtf = new SRTF();
                    ArrayList<Process> processesSRTF = copyProcessList(processes);
                    srtf.execute(new ArrayList<>(processesSRTF));

                    totalTurnaroundTime += calculateTotalTurnaroundTime(processesSRTF);
                    totalWaitingTime += calculateTotalWaitingTime(processesSRTF);
                    break;
                case "RR":
                    RR rr = new RR();
                    ArrayList<Process> processesRR = copyProcessList(processes);
                    rr.execute(new ArrayList<>(processesRR));

                    totalTurnaroundTime += calculateTotalTurnaroundTime(processesRR);
                    totalWaitingTime += calculateTotalWaitingTime(processesRR);
                    break;
                case "MLFQ":
                    MLFQ mlfq = new MLFQ();
                    ArrayList<Process> processesMLFQ = copyProcessList(processes);
                    mlfq.execute(new ArrayList<>(processesMLFQ));

                    totalTurnaroundTime += calculateTotalTurnaroundTime(processesMLFQ);
                    totalWaitingTime += calculateTotalWaitingTime(processesMLFQ);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid algorithm: " + algorithm);
            }
        }

        double averageTurnaroundTime = totalTurnaroundTime / (iterations * 8);
        double averageWaitingTime = totalWaitingTime / (iterations * 8);

        // Store averages in the time array
        time[0][iterationNumber] = averageTurnaroundTime;
        time[1][iterationNumber] = averageWaitingTime;
    }

    private static double calculateTotalTurnaroundTime(ArrayList<Process> processes) {

        int totalTurnaroundTime = 0;
        for (Process process : processes) {
            totalTurnaroundTime += process.getTurnaroundTime();
        }
        return totalTurnaroundTime;
    }

    private static double calculateTotalWaitingTime(ArrayList<Process> processes) {
        int totalWaitingTime = 0;
        for (Process process : processes) {
            totalWaitingTime += process.getWaitingTime();
        }
        return totalWaitingTime;
    }

    private static ArrayList<Process> generateRandomProcesses(int numberOfProcesses) {
        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < numberOfProcesses; i++) {

            int arrivalTime = (int) (Math.random() * 80); // Random arrival time between 0 and 40
            int burstTime = (int) (Math.random() * 96) + 5; // Random burst time between 5 and 100
            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }

        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        return processes;
    }
    private static ArrayList<Process> copyProcessList(ArrayList<Process> originalList) {
        ArrayList<Process> copiedList = new ArrayList<>();

        for (Process originalProcess : originalList) {
            copiedList.add(new Process(originalProcess.getProcessId(), originalProcess.getArrivalTime(),
                    originalProcess.getBurstTime()));
        }

        return copiedList;
    }

    private static void print2DArray(double[][] array, String algorithm, int[] iterations) {

        System.out.println("\n" + algorithm + " Scheduling:");

        // Print top border
        System.out.print("+-------------------+");
        for (int i = 0; i < array[0].length; i++) {
            System.out.print("---------------------+");
        }
        System.out.println();

        // Print column headers
        System.out.printf("| %-18s |", "Iterations");
        for (int iteration : iterations) {
            System.out.printf(" %-20d|", iteration);
        }
        System.out.println();

        // Print middle border
        System.out.print("+-------------------+");
        for (int i = 0; i < array[0].length; i++) {
            System.out.print("---------------------+");
        }
        System.out.println();

        // Print data rows
        String[] rowLabels = {"ATT", "AWT"};
        for (int i = 0; i < array.length; i++) {
            System.out.printf("| %-18s |", rowLabels[i]);
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf(" %-20.2f|", array[i][j]);
            }
            System.out.println();
        }

        // Print bottom border
        System.out.print("+-------------------+");
        for (int i = 0; i < array[0].length; i++) {
            System.out.print("---------------------+");
        }
        System.out.println();
    }
}