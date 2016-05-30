package utility;

/**
 * Created by poorvank on 26/05/16.
 */
public class FlowEdge {

    private final int source;
    private final int destination;
    private final double capacity;
    private double flow;

    public FlowEdge(int source, int destination, double capacity) {
        if(source<0) {
            throw new IndexOutOfBoundsException("source vertex cannot be negative");
        }
        if(destination<0) {
            throw new IndexOutOfBoundsException("destination vertex cannot be negative");
        }
        if(capacity<=0.0) {
            throw new IllegalArgumentException("Edge capacity must not be negative");
        }
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        flow = 0.0;
    }

    public FlowEdge(int source, int destination, double capacity, double flow) {
        if(source<0) {
            throw new IndexOutOfBoundsException("source vertex cannot be negative");
        }
        if(destination<0) {
            throw new IndexOutOfBoundsException("destination vertex cannot be negative");
        }
        if(capacity<=0.0) {
            throw new IllegalArgumentException("Edge capacity must not be negative");
        }
        if(flow<0) {
            throw new IllegalArgumentException("Flow must be non-negative");
        }
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        this.flow = flow;
    }

    public FlowEdge(FlowEdge e) {
        this.flow = e.flow;
        this.capacity = e.capacity;
        this.destination = e.destination;
        this.source = e.source;
    }

    public double getFlow() {
        return flow;
    }

    public double getCapacity() {
        return capacity;
    }

    public int from() {
        return source;
    }

    public int to() {
        return destination;
    }

    public int other(int v) {
        if(source==v) {
            return destination;
        }
        else if(destination==v) {
            return source;
        }
        else {
            throw new IllegalArgumentException("Illegal Endpoint");
        }
    }

    public double residualCapacityTo(int v) {
        if(v==source) { // backward Edge
            return flow;
        }
        else if(v==destination) { // Forward Edge
            return capacity-flow;
        }
        else {
            throw new IllegalArgumentException("Illegal EndPoint");
        }
    }

    public void addResidualFlowTo(int v,double delta) {
        if(v==destination) { //forward Edge
            this.flow += delta;
        }
        else if(v==source) { // backward Edge
            this.flow -= delta;
        }
        else throw new IllegalArgumentException("Illegal endpoint");
        if (Double.isNaN(delta)) throw new IllegalArgumentException("Change in flow = NaN");
        if (!(flow >= 0.0))      throw new IllegalArgumentException("Flow is negative");
        if (!(flow <= capacity)) throw new IllegalArgumentException("Flow exceeds capacity");
    }

    @Override
    public String toString() {
        return source + " -> " + destination + " " +  flow + "/" + capacity;
    }

    public static void main(String[] args) {
        FlowEdge e = new FlowEdge(12, 23, 3.14);
        System.out.println(e);
    }

}
