package tsp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Afiseaza optiunile pentru utilizator
        System.out.println("Choose the input file:");
        System.out.println("1: att532.txt");
        System.out.println("2: kroE100.txt");
        System.out.print("Your choice:");

        // Citeste optiunea utilizatorului
        int choice = scanner.nextInt();
        String filename;

        // Selecteaza fisierul pe baza optiunii utilizatorului
        switch (choice) {
            case 1:
                filename = "E:\\pbcasa\\InteligentaArtificialaTemaCasa\\data\\att532.txt";
                break;
            case 2:
                filename = "E:\\pbcasa\\InteligentaArtificialaTemaCasa\\data\\kroE100.txt";
                break;
            default:
                System.out.println("Invalid option.");
                scanner.close();
                return;
        }

        // Incarca datele din fisierul selectat
        Graph graph = new Graph();
        graph.loadFromFile(filename);

        if (graph.getCities().isEmpty()) {
            System.err.println("The graph hasn't been properly initialized. Check the input file.");
            scanner.close();
            return;
        }

        // Executa algoritmii de cautare
        // Depth-First Search
        DFS dfs = new DFS(graph);
        dfs.search();
        dfs.printResult();

        // Uniform Cost Search
        UniformCostSearch ucs = new UniformCostSearch(graph);
        ucs.search();
        ucs.printResult();

        // A* Search
        Heuristic heuristic = new Heuristic();
        AStarSearch aStar = new AStarSearch(graph, heuristic);
        aStar.search();
        aStar.printResult();

        scanner.close();
    }
}
