package tsp;

import java.util.*;

public class UniformCostSearch {
    private Graph graph;
    private List<Integer> bestPath;
    private double bestCost;

    public UniformCostSearch(Graph graph) {  //Constructor pentru a initializa graful si variabilele bestPath si bestCost.
        this.graph = graph;
        this.bestPath = null;
        this.bestCost = Double.MAX_VALUE;
    }

    public void search() { //Metoda de cautare cu costul uniform. Aici se foloseste PriorityQueue pentru a gestiona nodurile, ordonate dupa cost.
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.cost));
        pq.add(new Node(0, new ArrayList<>(), 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll(); // Se scoate nodul cu costul cel mai mic.

            if (node.path.size() == graph.getCities().size()) { // Verificam daca am vizitat toate orasele.
                double totalCost = node.cost + graph.getDistances()[node.current][0]; //Calculam costul total.
                if (totalCost < bestCost) {
                    bestCost = totalCost; //Actualizam bestCost daca am gasit un cost total mai mic.
                    bestPath = node.path; //Se actualizeaza noul bestPath.
                }
                continue;
            }

            for (int i = 0; i < graph.getCities().size(); i++) { //Iteram prin toate orasele.
                if (!node.visited.contains(i)) { //Daca orasul nu a fost vizitat..
                    List<Integer> newPath = new ArrayList<>(node.path);
                    newPath.add(i); //Adaugam orasul in calea curenta.
                    pq.add(new Node(i, newPath, node.cost + graph.getDistances()[node.current][i])); //Adaugam noul nod in coada.
                }
            }
        }
    }

    private static class Node { // Clasa interna Node pentru a gestiona starea fiecarui nod.
        int current; // Orasul curent.
        List<Integer> path; // Calea vizitata pana acum.
        double cost; //Costul total al caii.
        Set<Integer> visited; // Orasele vizitate.

        Node(int current, List<Integer> path, double cost) {
            this.current = current;
            this.path = path;
            this.cost = cost;
            this.visited = new HashSet<>(path); //Initializam setul de orase vizitate.
        }
    }

    public void printResult() { //Metoda pentru afisarea cautarii.
        System.out.println("Uniform Cost Search Result:");
        for (int index : bestPath) {
            System.out.print(graph.getCities().get(index).getName() + " -> ");
        }
        System.out.println("Start");
        System.out.println("Cost: " + bestCost);
    }
}
