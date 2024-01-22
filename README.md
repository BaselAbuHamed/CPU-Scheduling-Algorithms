# CPU Scheduling Simulation

## Overview

This project is a simulation of various CPU scheduling algorithms, comparing their performance in terms of average turnaround time (ATT) and average waiting time (AWT). The implemented algorithms include First-Come-First-Serve (FCFS), Shortest Remaining Time First (SRTF), Round Robin (RR), and Multilevel Feedback Queues (MLFQ).

## Project Features

1. **Implemented Algorithms:**
   - **FCFS (First-Come-First-Serve):** Processes are executed in the order they arrive.
   - **SRTF (Shortest Remaining Time First):** Preemptive execution based on the remaining burst time.
   - **RR (Round Robin):** Each process is assigned a fixed time slice (quantum) for execution.
   - **MLFQ (Multilevel Feedback Queues):** Processes move between different queues based on their behavior.

2. **Process Generation:**
   - 8 processes are created in the ready queue.
   - Each process has a random CPU burst time between 5 and 100 time units.

3. **Order of Arrival:**
   - Processes are assigned an order of arrival (arrival time).

4. **Algorithm-specific Configurations:**
   - RR uses a fixed time slice (quantum) of 20 units.
   - MLFQ employs three queues with different time slices.

5. **Performance Metrics:**
   - Average Turnaround Time (ATT): The total time taken for a process to complete.
   - Average Waiting Time (AWT): The total time a process spends waiting in the ready queue.

6. **Multiple Iterations:**
   - The simulation is repeated for 100, 1000, 10000, and 100000 iterations.

## Project Execution

1. **Simulation Loop:**
   - The project runs a simulation loop for each algorithm and iteration count.
   - For each iteration, the same set of processes is used across all algorithms.

2. **Results Summary:**
   - A table is generated summarizing ATT and AWT for each algorithm and iteration count.

## Results

### FCFS Scheduling:

| No. of Iterations | 100    | 1000   | 10000  | 100000 |
|-------------------|--------|--------|--------|--------|
| ATT               | *      | *      | *      | *      |
| AWT               | *      | *      | *      | *      |

### SRTF Scheduling:

| No. of Iterations | 100    | 1000   | 10000  | 100000 |
|-------------------|--------|--------|--------|--------|
| ATT               | *      | *      | *      | *      |
| AWT               | *      | *      | *      | *      |

### RR Scheduling:

| No. of Iterations | 100    | 1000   | 10000  | 100000 |
|-------------------|--------|--------|--------|--------|
| ATT               | *      | *      | *      | *      |
| AWT               | *      | *      | *      | *      |

### MLFQ Scheduling:

| No. of Iterations | 100    | 1000   | 10000  | 100000 |
|-------------------|--------|--------|--------|--------|
| ATT               | *      | *      | *      | *      |
| AWT               | *      | *      | *      | *      |

(*) Actual numerical results will be filled during the execution of the project.

## Execution

To run the simulation, follow these steps:

### Clone and Compile

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/BaselAbuHamed/CPU-Scheduling-Algorithms-.git
   cd CPU-Scheduling-Algorithms
   ```

2. Compile and run the application:

    ```bash
    cd CPU-Scheduling-Algorithms
    javac CPUSchedulingSimulation.java
    java CPUSchedulingSimulation
    ```


Happy Simulating! 🚀
