package tsp;

import java.util.*;

public class AStarSearch {
    private Graph graph; //Graful care contine orasele si distantele dintre ele
    private List<Integer> bestPath; //Cea mai buna cale gasita pana acum.
    private double bestCost; //Costum cele mai bune cai de pana acum.
    private Heuristic heuristic; //Functie euristica pentru A*search.

    public AStarSearch(Graph graph, Heuristic heuristic) { // Constructor pentru initalizarea euristicii si variabilelor bestPath si bestCost.
        this.graph = graph;
        this.heuristic = heuristic;
        this.bestPath = null;
        this.bestCost = Double.MAX_VALUE;
    }

    public void search() { //Metoda pentru inceperea cautarii A*search.
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.cost + heuristic.calculate(graph.getCities().get(node.current), graph.getCities().get(0)))); //Folosim priorityQueue pentru a gestiona nodurile, ordonat cost + euristica.
        pq.add(new Node(0, new ArrayList<>(), 0)); //Adaugam nodul initial in coada.

        while (!pq.isEmpty()) {
            Node node = pq.poll(); //Scoatem nodul cu costul cel mai mic.

            if (node.path.size() == graph.getCities().size()) { //Verificam daca am vizitat toate orasele.
                double totalCost = node.cost + graph.getDistances()[node.current][0]; //Calculam costul total.
                if (totalCost < bestCost) { //Daca am gasit un cost mai mic.
                    bestCost = totalCost; //Actualizam bestCost.
                    bestPath = node.path; //Actualizam bestPath.
                }
                continue;
            }

            for (int i = 0; i < graph.getCities().size(); i++) { //Iteram prin toate orasele.
                if (!node.visited.contains(i)) { //Daca orasul nu a fost vizitat.
                    List<Integer> newPath = new ArrayList<>(node.path);
                    newPath.add(i); //Adaugam orasul in calea curenta.
                    pq.add(new Node(i, newPath, node.cost + graph.getDistances()[node.current][i])); //Adaugam noul nod in coada.
                }
            }
        }
    }

    private static class Node { //Clasa interna pentru nod, ca sa gestionam mai bine starea fiecarui nod.
        int current; //Orasul curent.
        List<Integer> path; //Calea vizitata pana acum.
        double cost; //Costul total al caii.
        Set<Integer> visited; //Orasele vizitate.

        Node(int current, List<Integer> path, double cost) {
            this.current = current;
            this.path = path;
            this.cost = cost;
            this.visited = new HashSet<>(path); //Initializam setul de orase vizitate.
        }
    }

    public void printResult() { //Metoda pentru a afisa rezultatul cautarii.
        System.out.println("A* Search Result:");
        for (int index : bestPath) {
            System.out.print(graph.getCities().get(index).getName() + " -> ");
        }
        System.out.println("Start");
        System.out.println("Cost: " + bestCost);
    }
}
