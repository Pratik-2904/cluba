package assignments;

import java.util.Scanner;

public class FloydWarshall {
    final static int INF = 99999;

    public static void floydWarshall(int[][] costMatrix, int numv) {
        int[][] dist = new int[numv][numv];
        for (int i = 0; i < numv; i++) {
            for (int j = 0; j < numv; j++) {
                dist[i][j] = costMatrix[i][j];
            }
        }
        for (int k = 0; k < numv; k++) {
            for (int i = 0; i < numv; i++) {
                for (int j = 0; j < numv; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        printSolution(dist, numv);
    }

    public static void printSolution(int[][] dist, int numv) {
        System.out.println("Minimum cost between all pairs of vertices:");
        for (int i = 0; i < numv; i++) {
            for (int j = 0; j < numv; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int numv = sc.nextInt();
        int[][] costMatrix = new int[numv][numv];
        System.out.println("Enter the cost matrix (use " + INF + " for no direct connection): ");
        for (int i = 0; i < numv; i++) {
            for (int j = 0; j < numv; j++) {
                costMatrix[i][j] = sc.nextInt();
            }
        }
        floydWarshall(costMatrix, numv);
    }
}