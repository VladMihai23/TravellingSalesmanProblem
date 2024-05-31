package tsp;

public class Heuristic {
    public double calculate(City current, City start) {
        // Metoda pentru a calcula distanta euristica dintre orasul curent si orasul de start.
        int dx = current.getX() - start.getX(); // Diferenta coordonatelor pe axa X.
        int dy = current.getY() - start.getY(); // Diferenta coordonatelor pe axa Y.
        return Math.sqrt(dx * dx + dy * dy);    // Calculul distantei euclidiene.
    }
}
