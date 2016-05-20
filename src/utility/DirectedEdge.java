package utility;

/**
 * Created by poorvank on 20/05/16.
 */
public class DirectedEdge {

    private int source;
    private int destination;
    private double weight;

    public DirectedEdge(int source, int destination, double weight) {
        if(source<0) {
            throw new IndexOutOfBoundsException("Source vertex cannot be negative");
        }
        if(destination < 0) {
            throw new IndexOutOfBoundsException("Destination vertex cannot be negative");
        }
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int from() {
        return source;
    }

    public int to() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return String.format("%d -> %d (%.5f)" , source,destination,weight);
    }

    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        System.out.println(e.toString());
    }

}
