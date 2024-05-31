package tsp;

import java.util.*;
import java.io.*;

public class Graph {
    private List<City> cities;  // Lista de orase
    private double[][] distances;  // Matricea de distante dintre orase

    // Constructor pentru initializarea listei de orase
    public Graph() {
        cities = new ArrayList<>();
    }

    // Metoda pentru incarcarea datelor dintr-un fisier
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean readCoords = false;  // Flag pentru a verifica daca suntem in sectiunea de coordonate

            while ((line = br.readLine()) != null) {
                if (line.startsWith("NODE_COORD_SECTION")) {
                    readCoords = true;  // Am ajuns la sectiunea de coordonate
                    continue;
                }

                if (readCoords) {
                    if (line.equals("EOF")) {
                        break;  // Am ajuns la sfarsitul fisierului
                    }
                    String[] parts = line.trim().split("\\s+");  // Imparte linia in parti separate de spatii
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0]);
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        cities.add(new City("City" + id, x, y));  // Adauga orasul in lista de orase
                    }
                }
            }

            if (cities.isEmpty()) {
                throw new IllegalArgumentException("There are no available cities in the specified file.");  // Arunca exceptie daca nu s-au gasit orase
            }

            calculateDistances();  // Calculeaza distantele dintre orase
        } catch (IOException e) {
            e.printStackTrace();  // Prinde si afiseaza exceptiile de I/O
        }
    }

    // Metoda pentru calcularea distantelor dintre toate perechile de orase
    private void calculateDistances() {
        int n = cities.size();
        distances = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = calculateDistance(cities.get(i), cities.get(j));  // Calculeaza distanta dintre orasul i si orasul j
            }
        }
    }

    // Metoda pentru calcularea distantei euclidiene dintre doua orase
    private double calculateDistance(City a, City b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);  // Returneaza distanta euclidiana
    }

    // Metoda pentru obtinerea listei de orase
    public List<City> getCities() {
        return cities;
    }

    // Metoda pentru obtinerea matricii de distante
    public double[][] getDistances() {
        return distances;
    }
}
