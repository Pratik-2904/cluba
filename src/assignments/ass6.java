package assignments;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// Node class represents a club assignment
class Node {
    Node parent; // parent node
    int pathCost; // cost to reach this node
    int cost; // lower bound cost
    int studentID; // student ID
    int clubID; // club ID
    boolean[] assigned; // keeps track of assigned clubs

    public Node(int N) {
        assigned = new boolean[N]; // initialize assigned clubs array
    }
}

class NClubs {
    static final int N = 4; // number of students and clubs

    // Function to create a new search tree node
    static Node newNode(int studentid, int clubid, boolean[] assigned, Node parent) {
        Node node = new Node(N);
        for (int j = 0; j < N; j++)
            node.assigned[j] = assigned[j];
        if (clubid != -1) {
            node.assigned[clubid] = true;
        }
        node.parent = parent;
        node.studentID = studentid;
        node.clubID = clubid;
        return node;
    }

    // Function to calculate the least promising cost of a node
    static int calculateCost(int[][] costMatrix, int x, int y, boolean[] assigned) {
        int cost = 0;
        boolean[] available = new boolean[N];
        Arrays.fill(available, true);
        for (int i = x + 1; i < N; i++) {
            int min = Integer.MAX_VALUE, minIndex = -1;
            for (int j = 0; j < N; j++) {
                if (!assigned[j] && available[j] && costMatrix[i][j] < min) {
                    minIndex = j;
                    min = costMatrix[i][j];
                }
            }
            cost += min;
            available[minIndex] = false;
        }
        return cost;
    }

    // Function to print club assignment
    static void printAssignments(Node min) {
        if (min.parent == null)
            return;
        printAssignments(min.parent);
        System.out.println("Assign student " + (char) (min.studentID + 'A') + " to club " + (min.clubID + 1));
    }

    // Function to solve club Assignment Problem using Branch and Bound
    static int findMinCost(int[][] costMatrix) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        boolean[] assigned = new boolean[N];
        Node root = newNode(-1, -1, assigned, null);
        root.pathCost = root.cost = 0;
        root.studentID = -1;
        pq.add(root);
        while (!pq.isEmpty()) {
            Node min = pq.poll();
            int i = min.studentID + 1;
            if (i == N) {
                printAssignments(min);
                return min.cost;
            }
            for (int j = 0; j < N; j++) {
                if (!min.assigned[j]) {
                    Node child = newNode(i, j, min.assigned, min);
                    child.pathCost = min.pathCost + costMatrix[i][j];
                    child.cost = child.pathCost + calculateCost(costMatrix, i, j, child.assigned);
                    pq.add(child);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] costMatrix = {
                {5, 2, 7, 8},
                {6, 4, 3, 7},
                {5, 8, 10, 8},
                {7, 6, 9, 4}
        };
        System.out.println("\nOptimal Cost is " + findMinCost(costMatrix));
    }
}
