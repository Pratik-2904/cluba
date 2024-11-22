package assignments;

import java.util.*;
import java.math.*;

class Job {
    public int id;
    public int deadline;
    public int profit;
    // constructor
    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class GreedyJobAllocation {
    private static void jobSheduling(ArrayList<Job> job, int n) {
        int size = job.size();
        boolean result[] = new boolean[n];
        ArrayList<Job> schedule = new ArrayList<>();
// job selection
        for (int i = 0; i < size; i++) {
            Job temp = job.get(i);
// Slot allocation
            for (int j = Math.min((n - 1), (temp.deadline - 1)); j >= 0; j--) {
                if (!result[j]) {
                    result[j] = true;
                    schedule.add(temp);
                    break;
                }
            }
        }
// Display of scheduled jobs
        System.out.println("Following is the Scheduling of Jobs:");
        schedule.forEach((j) -> {
            System.out.println("id:" + j.id + " profit:" + j.profit + " deadline: " + j.deadline);
        });
// Display the profit
        int totalProfit = 0;
        for (Job j : schedule) {
            totalProfit += j.profit;
        }
        System.out.println("Total Profit:" + totalProfit);
    }
    public static void main(String[] args) {
        int counter = 5;
        ArrayList<Job> job = new ArrayList<Job>();
        job.add(new Job(1, 2, 100));
        job.add(new Job(2, 1, 19));
        job.add(new Job(3, 2, 27));
        job.add(new Job(4, 1, 25));
        job.add(new Job(5, 3, 15));
// Sorting of the jobd according to the profits
        Collections.sort(job, (a, b) -> b.profit - a.profit);
        job.forEach((i) -> {
            System.out.println("id:" + i.id + " profit:" + i.profit + " deadline:" + i.deadline);
        });
// max deadline
        int maxDeadline = job.get(0).deadline;
        for (int i = 1; i < counter; i++) {
            if (job.get(i).deadline > maxDeadline) {
                maxDeadline = job.get(i).deadline;
            }
        }
        jobSheduling(job, maxDeadline);
    }
}