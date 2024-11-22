package  assignments;

import java.util.Scanner;

class NetworkShortestPath {
    static int V;

    int findMinDistance(int distances[], Boolean visitedSet[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < V; v++)
            if (!visitedSet[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        return minIndex;
    }

    void printShortestPaths(int distances[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + distances[i]);
    }

    void dijkstraAlgorithm(int graph[][], int source) {
        int distances[] = new int[V];
        Boolean visitedSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            distances[i] = Integer.MAX_VALUE;
            visitedSet[i] = false;
        }
        distances[source] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = findMinDistance(distances, visitedSet);
            visitedSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (!visitedSet[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE
                        && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                }
            }
        }
        printShortestPaths(distances);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        V = sc.nextInt();
        int graph[][] = new int[V][V];
        System.out.println("Enter the adjacency matrix for the graph:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter the source vertex:");
        int source = sc.nextInt();
        NetworkShortestPath sp = new NetworkShortestPath();
        sp.dijkstraAlgorithm(graph, source);
        sc.close();
    }
}
