// Will Tobey
// wtobey1@jhu.edu

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Sparse graph implementation of the graph interface.
 * Uses arrayLists as underlying data structure.
 *
 * @param <V> Type for the vertices.
 * @param <E> Type for edges.
*/

public class SparseGraph<V, E> implements Graph<V, E> {


    // Inner Classes //

    /**
     *
     * SparseGraph representation of a edge.
     *
     * @param <T> The type for the edge.
     *
    */
    private final class SparseEdge<T> implements Edge<T> {

        int tag;
        Object label;

        SparseVertex<V> fromVertex;
        SparseVertex<V> toVertex;

        private T data;

        /**
         * SparseEdge initializer.
         *
         * @param t The value for the edge.
         * @param from The vertex from which the edge is coming.
         * @param to The vertex to which the edge is going.
        */
        SparseEdge(T t, SparseVertex<V> from, SparseVertex<V> to) {

            this.data = t;
            this.fromVertex = from;
            this.toVertex = to;

            this.tag = SparseGraph.this.tag;

        }

        @Override
        public T get() {

            return this.data;

        }

        @Override
        public void put(T t) {

            this.data = t;

        }

        /**
         * Gives a string reprentation for a SparseEdge.
         *
         * @return The string represntation of the object.
        */
        public String toString() {

            StringBuilder s = new StringBuilder();

            s.append(this.fromVertex.toString());
            s.append(" -> ");
            s.append(this.toVertex.toString());
            s.append(" [label=\"");
            s.append(this.data);
            s.append("\"]");

            return s.toString();

        }

    }

    /**
     *
     * SparseGraph representation of a vertex.
     *
     * @param <T> The type for the vertex.
     *
    */
    private final class SparseVertex<T> implements Vertex<T> {

        Object label;
        int tag;

        ArrayList<SparseEdge<E>> incomingEdges = new ArrayList<SparseEdge<E>>();
        ArrayList<SparseEdge<E>> outgoingEdges = new ArrayList<SparseEdge<E>>();

        private T data;

        /**
         * Initializes a SparseVertex.
         *
         * @param t The value for the vertex.
        */
        SparseVertex(T t) {
            this.data = t;
            this.tag = SparseGraph.this.tag;
        }

        @Override
        public T get() {

            return this.data;

        }

        @Override
        public void put(T t) {

            this.data = t;

        }

        /**
         * Gives a string reprentation for a SparseVertex.
         *
         * @return The string represntation of the object.
        */
        public String toString() {

            return "\"" + this.data + "\"";

        }

    }


    // Class Variables //

    private static int indexedTag = 1;


    // Instance Variables //

    ArrayList<SparseEdge<E>> edges = new ArrayList<SparseEdge<E>>();
    ArrayList<SparseVertex<V>> vertices = new ArrayList<SparseVertex<V>>();

    private int tag;


    // Instance Methods //

    SparseGraph() {

        this.tag = SparseGraph.indexedTag;

        SparseGraph.indexedTag++;

    }

    /**
     * Converts an Edge position to an unwrapped SparseEdge.
     *
     * @param edge The edge to convert.
     * @return The SparseEdge converted from the original edge.
     * @throws PositionException Throws an exception if the edge is
     *     not a SparseEdge or if it is not part of the graph.
    */
    private SparseEdge<E> convert(Edge<E> edge) throws PositionException {

        if (!(edge instanceof SparseEdge)) {
            throw new PositionException();
        }

        SparseEdge<E> converted = (SparseEdge<E>) edge;

        if (converted.tag != this.tag) {
            throw new PositionException();
        }

        return converted;

    }

    /**
     * Converts a Vertex position to an unwrapped SparseVertex.
     *
     * @param vertex The vertex to convert.
     * @return The SparseVertex converted from the original vertex.
     * @throws PositionException Throws an exception if the vertex is
     *     not a SparseVertex or if it is not part of the graph.
    */
    private SparseVertex<V> convert(Vertex<V> vertex) throws PositionException {

        if (!(vertex instanceof SparseVertex)) {
            throw new PositionException();
        }

        SparseVertex<V> converted = (SparseVertex<V>) vertex;

        if (converted.tag != this.tag) {
            throw new PositionException();
        }

        return converted;

    }

    /**
     * Converts the SparseGraph to a string.
     *
     * @return The string representation for the object.
    */
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append("digraph {\n");

        for (SparseVertex<V> vertex : this.vertices) {

            s.append("  ");
            s.append(vertex.toString());
            s.append(";\n");

        }

        for (SparseEdge<E> edge : this.edges) {

            s.append("  ");
            s.append(edge.toString());
            s.append(";\n");

        }

        s.append("}");

        return s.toString();

    }


    // Graph Interface Implementation //

    @Override
    public Vertex<V> insert(V v) {

        SparseVertex<V> vertex = new SparseVertex<V>(v);

        this.vertices.add(vertex);

        return vertex;

    }

    @Override
    public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
        throws PositionException, InsertionException {

        SparseVertex<V> fromVertex = this.convert(from);
        SparseVertex<V> toVertex = this.convert(to);

        if (fromVertex.outgoingEdges.size() <= toVertex.incomingEdges.size()) {

            for (SparseEdge<E> edge : fromVertex.outgoingEdges) {
                if (edge.toVertex == toVertex) {
                    throw new InsertionException();
                }
            }

        } else {

            for (SparseEdge<E> edge : toVertex.incomingEdges) {
                if (edge.fromVertex == fromVertex) {
                    throw new InsertionException();
                }
            }

        }

        SparseEdge<E> edge = new SparseEdge<E>(e, fromVertex, toVertex);

        fromVertex.outgoingEdges.add(edge);
        toVertex.incomingEdges.add(edge);

        this.edges.add(edge);

        return edge;

    }

    @Override
    public V remove(Vertex<V> v) throws PositionException, RemovalException {

        SparseVertex<V> vertex = this.convert(v);

        // These lines are to fix checkstyle errors
        int incomingEdgeCount = vertex.incomingEdges.size();
        int outgoingEdgeCount = vertex.outgoingEdges.size();

        if (incomingEdgeCount != 0 || outgoingEdgeCount != 0) {
            throw new RemovalException();
        }

        Iterator<SparseVertex<V>> iterator = this.vertices.iterator();

        while (iterator.hasNext()) {

            if (iterator.next() == vertex) {
                iterator.remove();
                break;
            }

        }

        vertex.tag = 0;

        return vertex.get();

    }

    @Override
    public E remove(Edge<E> e) throws PositionException {

        SparseEdge<E> edge = this.convert(e);

        // These lines are to fix checkstyle errors for long lines
        SparseVertex<V> from = edge.fromVertex;
        SparseVertex<V> to = edge.toVertex;

        ArrayList<SparseEdge<E>> outgoingEdges = from.outgoingEdges;
        ArrayList<SparseEdge<E>> incomingEdges = to.incomingEdges;

        Iterator<SparseEdge<E>> outgoingIterator =  outgoingEdges.iterator();
        Iterator<SparseEdge<E>> incomingIterator = incomingEdges.iterator();
        Iterator<SparseEdge<E>> edgesIterator = this.edges.iterator();

        // Remove the edge from the outgoing ArrayList
        while (outgoingIterator.hasNext()) {

            if (outgoingIterator.next().toVertex == to) {
                outgoingIterator.remove();
                break;
            }

        }

        // Remove the edge from the incoming ArrayList
        while (incomingIterator.hasNext()) {

            if (incomingIterator.next().fromVertex == from) {
                incomingIterator.remove();
                break;
            }

        }

        // Remove the edge from the Edges ArrayList
        while (edgesIterator.hasNext()) {

            if (edgesIterator.next() == edge) {
                edgesIterator.remove();
                break;
            }

        }

        // invalidate the edge so it cannot be used in the graph
        edge.tag = 0;

        return edge.get();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {

        return new ArrayList<Vertex<V>>(this.vertices);

    }

    @Override
    public Iterable<Edge<E>> edges() {

        return new ArrayList<Edge<E>>(this.edges);

    }

    @Override
    public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {

        SparseVertex<V> vertex = this.convert(v);

        return new ArrayList<Edge<E>>(vertex.outgoingEdges);

    }

    @Override
    public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {

        SparseVertex<V> vertex = this.convert(v);

        return new ArrayList<Edge<E>>(vertex.incomingEdges);

    }

    @Override
    public Vertex<V> from(Edge<E> e) throws PositionException {

        SparseEdge<E> edge = this.convert(e);

        return edge.fromVertex;

    }

    @Override
    public Vertex<V> to(Edge<E> e) throws PositionException {

        SparseEdge<E> edge = this.convert(e);

        return edge.toVertex;

    }

    @Override
    public void label(Vertex<V> v, Object l) throws PositionException {

        SparseVertex<V> vertex = this.convert(v);

        vertex.label = l;

    }

    @Override
    public void label(Edge<E> e, Object l) throws PositionException {

        SparseEdge<E> edge = this.convert(e);

        edge.label = l;

    }

    @Override
    public Object label(Vertex<V> v) throws PositionException {

        SparseVertex<V> vertex = this.convert(v);

        return vertex.label;

    }

    @Override
    public Object label(Edge<E> e) throws PositionException {

        SparseEdge<E> edge = this.convert(e);

        return edge.label;

    }

    @Override
    public void clearLabels() {

        for (SparseEdge<E> edge : this.edges) {
            edge.label = null;
        }

        for (SparseVertex<V> vertex : this.vertices) {
            vertex.label = null;
        }

    }

}
