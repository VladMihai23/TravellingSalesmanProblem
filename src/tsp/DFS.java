package tsp;

import java.util.*;

public class DFS {
    private Graph graph;  // Graful care contine orasele si distantele dintre ele
    private List<Integer> bestPath;  // Cea mai buna cale gasita pana acum
    private double bestCost;  // Costul celei mai bune cai gasite pana acum

    // Constructor pentru initializarea grafului si variabilelor bestPath si bestCost
    public DFS(Graph graph) {
        this.graph = graph;
        this.bestPath = null;
        this.bestCost = Double.MAX_VALUE;
    }

    // Metoda pentru a incepe cautarea in profunzime (DFS)
    public void search() {
        if (graph.getCities().isEmpty()) {
            throw new IllegalStateException("The graph hasn't been properly initialized. There are no available cities.");  // Verifica daca graful este gol
        }

        List<Integer> path = new ArrayList<>();  // Lista pentru a stoca calea curenta
        boolean[] visited = new boolean[graph.getCities().size()];  // Array pentru a tine evidenta oraselor vizitate
        dfs(0, path, visited, 0);  // Incepe cautarea din orasul de start (index 0)
    }

    // Metoda recursiva pentru cautarea in profunzime
    private void dfs(int current, List<Integer> path, boolean[] visited, double currentCost) {
        List<City> cities = graph.getCities();
        path.add(current);  // Adauga orasul curent in calea curenta
        visited[current] = true;  // Marcheaza orasul curent ca vizitat

        // Daca am vizitat toate orasele
        if (path.size() == cities.size()) {
            double totalCost = currentCost + graph.getDistances()[current][0];  // Calculeaza costul total
            if (totalCost < bestCost) {  // Daca am gasit un cost mai mic
                bestCost = totalCost;  // Actualizeaza bestCost
                bestPath = new ArrayList<>(path);  // Actualizeaza bestPath
            }
        } else {
            // Itereaza prin toate orasele
            for (int i = 0; i < cities.size(); i++) {
                if (!visited[i]) {  // Daca orasul nu a fost vizitat
                    double newCost = currentCost + graph.getDistances()[current][i];  // Calculeaza noul cost
                    dfs(i, path, visited, newCost);  // Apeleaza recursiv DFS pentru orasul urmator
                }
            }
        }

        visited[current] = false;  // Deselecteaza orasul curent pentru a permite alte rute
        path.remove(path.size() - 1);  // Scoate orasul curent din calea curenta
    }

    // Metoda pentru a afisa rezultatul cautarii
    public void printResult() {
        if (bestPath == null || bestPath.isEmpty()) {
            System.out.println("No path was found.");  // Mesaj in cazul in care nu a fost gasita nicio cale
            return;
        }

        System.out.println("The Depth First Search (DFS) Result:");
        for (int index : bestPath) {
            System.out.print(graph.getCities().get(index).getName() + " -> ");
        }
        System.out.println("Start");
        System.out.println("Cost: " + bestCost);
    }
}
