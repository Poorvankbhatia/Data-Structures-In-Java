package utility.EdgeClasses;

/**
 * Created by poorvank on 14/05/16.
 */
public class Edge implements Comparable<Edge> {

    private final int source;
    private final int destination;
    private final double weight;

    public Edge(int source,int destination,double weight) {
        if(source < 0) {
            throw new IllegalArgumentException("Source cannot be negative");
        }
        if(destination < 0) {
            throw new IllegalArgumentException("Destination cannot be negtive");
        }
        this.source = source;
        this.destination = destination;
        this.weight = weight;

    }

    public double getWeight() {
        return weight;
    }

    public int either(){
        return source;
    }

    public int other(int v) {
        if(v==source) {
            return destination;
        }
        else if(v==destination) {
            return source;
        }
        else {
            throw new IllegalArgumentException("Illegal Endpoint");
        }
    }

    @Override
    public int compareTo(Edge that) {
        if(this.weight<that.weight) {
            return -1;
        }
        else if(that.weight < this.weight) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public String toString() {
        return String.format("%d -- > %d (%.5f)",source,destination,weight);
    }

    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.098);
        System.out.println(e);
    }

}
