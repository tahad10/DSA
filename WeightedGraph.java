package de.unistuttgart.dsass2024.ex06.p3;

import java.util.ArrayList;
import java.util.Iterator;

public class WeightedGraph<N, E> implements IWeightedGraph<N, E> {

    private int numEdges;
    private ArrayList<ArrayList<IEdge<E>>> adjacencyList;

    /**
     * Initializes an empty graph without nodes or edges.
     */
    public WeightedGraph() {
        this.numEdges = 0;
        this.adjacencyList = new ArrayList<>();
    }

    @Override
    public int numberOfNodes() {
        return this.adjacencyList.size();
    }

    @Override
    public int numberOfEdges() {
        return this.numEdges;
    }

    @Override
    public Iterator<IEdge<E>> edgeIterator() {
        ArrayList<IEdge<E>> edges = new ArrayList<>(numEdges);
        for (ArrayList<IEdge<E>> outgoingEdges : this.adjacencyList) {
            edges.addAll(outgoingEdges);
        }
        return edges.iterator();
    }

    @Override
    public Iterator<IEdge<E>> outgoingEdges(int src) {
        return this.adjacencyList.get(src).iterator();
    }

    public int addNode() {
        this.adjacencyList.add(new ArrayList<>());
        return this.adjacencyList.size() - 1;
    }

    public void addEdge(int src, int dest, double weight) {
        if (src < 0 || src >= numberOfNodes() || dest < 0 || dest >= numberOfNodes())
            throw new IllegalArgumentException();
        this.adjacencyList.get(src).add(new Edge<>(src, dest, 0));
        this.numEdges++;
    }

    @Override
    public void createFromEdgeList(ArrayList<Integer> list) throws UnsupportedOperationException {
        if (this.numEdges > 0 || this.numberOfNodes() > 0) {
            throw new UnsupportedOperationException("Graph already has nodes or edges");
        }

        int maxNode = 0;
        for (int i = 0; i < list.size(); i += 3) {
            maxNode = Math.max(maxNode, Math.max(list.get(i), list.get(i + 1)));
        }

        for (int i = 0; i <= maxNode; i++) {
            this.addNode();
        }

        for (int i = 0; i < list.size(); i += 3) {
            int src = list.get(i);
            int dest = list.get(i + 1);
            double weight = list.get(i + 2);
            this.addEdge(src, dest, weight);
        }
    }

    @Override
    public ArrayList<Integer> toEdgeList() {
        ArrayList<Integer> edgeList = new ArrayList<>();
        for (int src = 0; src < this.adjacencyList.size(); src++) {
            for (IEdge<E> edge : this.adjacencyList.get(src)) {
                edgeList.add(edge.getSource());
                edgeList.add(edge.getDestination());
                edgeList.add((int) edge.getWeight());
            }
        } return edgeList;
    }

    @Override
    public void createFromNodeList(ArrayList<Integer> list) throws UnsupportedOperationException {
        if (this.numEdges > 0 || this.numberOfNodes() > 0) {
            throw new UnsupportedOperationException("Graph already has nodes or edges");
        }

        for (int i = 0; i < list.size(); i++) {
            this.addNode();
        }

        for (int i = 0; i < list.size(); i += 2) {
           int node = i / 2;
           int neighbor = list.get(i);
           double weight = list.get(i + 1);
           this.addEdge(node, neighbor, weight);
        }
    }

    @Override
    public ArrayList<Integer> toNodeList(){
        ArrayList<Integer> nodeList = new ArrayList<>();
        for (int i = 0; i < this.adjacencyList.size(); i++){
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (IEdge<E> edge : this.adjacencyList.get(i)){
                neighbors.add(edge.getDestination());
                neighbors.add((int) edge.getWeight());
            }
            nodeList.addAll(neighbors);
        } return nodeList;
    }
}