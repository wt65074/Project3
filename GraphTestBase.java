// Will Tobey
// wtobey1@jhu.edu

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class GraphTestBase {

    Graph<Integer, Integer> graph1;
    Graph<Integer, Integer> graph2;

    // Edge class for testing position esxception
    private final class TestEdge<T> implements Edge<T> {
        @Override
        public T get() { return null; }
        public void put(T t) { }
    }

    // Vertex class for testing exception
    private final class TestVertex<T> implements Vertex<T> {
        @Override
        public T get() { return null; }
        public void put(T t) { }
    }

    // Position Exception Testing
    // Although SparseGraph only needs one such test becuase it
    // uses the same convert method in all PositionException throwing
    // functions, not all Graphs will do the same, and as such every
    // method needs to be tested.

    @Test
    public void testInsertEdgePositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);

        // Test position from another graph

        try {

            graph1.insert(g2v1, g2v2, 12);
            assertTrue(false);

        } catch (PositionException e) { }

        try {

            graph1.insert(g2v1, g1v2, 12);
            assertTrue(false);

        } catch (PositionException e) { }

        try {

            graph1.insert(g1v1, g2v2, 12);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1v2);

        try {

            graph1.insert(g1v1, g1v2, 12);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestVertex<Integer> falseVertex = new TestVertex<Integer>();

        try {

            graph1.insert(falseVertex, g1v1, 12);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testRemoveVertexPositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);

        // Test position from another graph

        try {

            graph1.remove(g2v1);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1v2);

        try {

            graph1.remove(g1v2);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestVertex<Integer> falseVertex = new TestVertex<Integer>();

        try {

            graph1.remove(falseVertex);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testRemoveEdgePositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);
        Edge<Integer> g2e12 = graph2.insert(g2v1, g2v2, 12);

        // Test position from another graph

        try {

            graph1.remove(g2e12);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1e12);

        try {

            graph1.remove(g1e12);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestEdge<Integer> falseEdge = new TestEdge<Integer>();

        try {

            graph1.remove(falseEdge);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testOutgoingPositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Vertex<Integer> g1v3 = graph1.insert(3);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);
        Edge<Integer> g2e12 = graph2.insert(g2v1, g2v2, 12);

        // Test position from another graph

        try {

            graph1.outgoing(g2v1);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1v3);

        try {

            graph1.outgoing(g1v3);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestVertex<Integer> falseVertex = new TestVertex<Integer>();

        try {

            graph1.outgoing(falseVertex);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testIncomingPositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Vertex<Integer> g1v3 = graph1.insert(3);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);
        Edge<Integer> g2e12 = graph2.insert(g2v1, g2v2, 12);

        // Test position from another graph

        try {

            graph1.incoming(g2v1);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1v3);

        try {

            graph1.incoming(g1v3);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestVertex<Integer> falseVertex = new TestVertex<Integer>();

        try {

            graph1.incoming(falseVertex);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testFromPositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);
        Edge<Integer> g2e12 = graph2.insert(g2v1, g2v2, 12);

        // Test position from another graph

        try {

            graph1.from(g2e12);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1e12);

        try {

            graph1.from(g1e12);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestEdge<Integer> falseEdge = new TestEdge<Integer>();

        try {

            graph1.from(falseEdge);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testToPositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);
        Edge<Integer> g2e12 = graph2.insert(g2v1, g2v2, 12);

        // Test position from another graph

        try {

            graph1.to(g2e12);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1e12);

        try {

            graph1.to(g1e12);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestEdge<Integer> falseEdge = new TestEdge<Integer>();

        try {

            graph1.to(falseEdge);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testSetLabelVertexPositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);

        // Test position from another graph

        try {

            graph1.label(g2v1, "TEST LABEL");
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1v2);

        try {

            graph1.label(g1v2, "TEST LABEL");
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestVertex<Integer> falseVertex = new TestVertex<Integer>();

        try {

            graph1.label(falseVertex, "TEST LABEL");
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testSetLabelEdgePositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);
        Edge<Integer> g2e12 = graph2.insert(g2v1, g2v2, 12);

        // Test position from another graph

        try {

            graph1.label(g2e12, "TEST LABEL");
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1e12);

        try {

            graph1.label(g1e12, "TEST LABEL");
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestEdge<Integer> falseEdge = new TestEdge<Integer>();

        try {

            graph1.label(falseEdge, "TEST LABEL");
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testGetLabelVertexPositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);

        // Test position from another graph

        try {

            graph1.label(g2v1);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1v2);

        try {

            graph1.label(g1v2);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestVertex<Integer> falseVertex = new TestVertex<Integer>();

        try {

            graph1.label(falseVertex);
            assertTrue(false);

        } catch (PositionException e) { }

    }

    @Test
    public void testGetLabelEdgePositionException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        Vertex<Integer> g2v1 = graph2.insert(1);
        Vertex<Integer> g2v2 = graph2.insert(2);
        Edge<Integer> g2e12 = graph2.insert(g2v1, g2v2, 12);

        // Test position from another graph

        try {

            graph1.label(g2e12);
            assertTrue(false);

        } catch (PositionException e) { }

        // Test removed position

        graph1.remove(g1e12);

        try {

            graph1.label(g1e12);
            assertTrue(false);

        } catch (PositionException e) { }


        // Test position of different type

        TestEdge<Integer> falseEdge = new TestEdge<Integer>();

        try {

            graph1.label(falseEdge);
            assertTrue(false);

        } catch (PositionException e) { }

    }


    // Insertion Exception Testing

    @Test(expected = InsertionException.class)
    public void testInsertEdgeInsertionExsception() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        graph1.insert(g1v1, g1v2, 12);

    }


    // Removal Exception Testing

    @Test
    public void testRemoveVertexRemovalException() {

        Vertex<Integer> g1v1 = graph1.insert(1);
        Vertex<Integer> g1v2 = graph1.insert(2);
        Edge<Integer> g1e12 = graph1.insert(g1v1, g1v2, 12);

        try {

            graph1.remove(g1v1);
            assertTrue(false);

        } catch (RemovalException e) {}

        try {

            graph1.remove(g1v2);
            assertTrue(false);
            
        } catch (RemovalException e) {}

    }


    // Graph Testing

    @Test
    public void testInsertVertex() {

        for (int i = 0; i < 25; i++) {
            graph1.insert(i);
        }

        int count = 0;

        for (Vertex<Integer> vertex : graph1.vertices()) {

            count++;

        }

        assertTrue(count == 25);

    }

    @Test
    public void testInsertEdge() {

        for (int i = 0; i < 60; i += 2) {

            Vertex<Integer> v1 = graph1.insert(i);
            Vertex<Integer> v2 = graph1.insert(i + 1);

            Edge<Integer> e12 = graph1.insert(v1, v2, i * 10 + i + 1);

            boolean foundInOutgoing = false;

            for (Edge<Integer> edge : graph1.outgoing(v1)) {

                if (edge == e12) {
                    foundInOutgoing = true;
                    break;
                }

            }

            assertTrue(foundInOutgoing);

            boolean foundInIncoming = false;

            for (Edge<Integer> edge : graph1.incoming(v2)) {

                if (edge == e12) {
                    foundInIncoming = true;
                    break;
                }

            }

            assertTrue(foundInIncoming);

        }

        int count = 0;

        for (Edge<Integer> edge : graph1.edges()) {

            assertTrue(edge.get() == count * 10 + count + 1);

            count += 2;

        }

    }

    @Test
    public void testRemoveVertex() {

        ArrayList<Vertex<Integer>> vertices = new ArrayList<Vertex<Integer>>();

        for (int i = 0; i < 30; i++) {

            Vertex<Integer> v1 = graph1.insert(i);
            vertices.add(v1);

        }

        for (Vertex<Integer> vertex : vertices) {

            assertTrue(graph1.remove(vertex) == vertex.get());

        }

        // Make sure there are no more verticies in the graph.
        // Since Iterable doesnt have a count() method, this
        // is the best way.
        for (Vertex<Integer> vertex : graph1.vertices()) { 

            assertTrue(false); 

        }

    }

    @Test
    public void testRemoveEdge() {

        ArrayList<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();

        for (int i = 0; i < 60; i += 2) {

            Vertex<Integer> v1 = graph1.insert(i);
            Vertex<Integer> v2 = graph1.insert(i + 1);

            Edge<Integer> edge = graph1.insert(v1, v2, i * 10 + i + 1);

            edges.add(edge);
        }

        for (Edge<Integer> edge : edges) {

            assertTrue(graph1.remove(edge) == edge.get());

        }

        for (Edge<Integer> edge : graph1.edges()) {

            assertTrue(false);

        }

    }

    @Test
    public void testVertices() {

        for (int i = 0; i < 30; i++) {

            Vertex<Integer> v1 = graph1.insert(i);

            int count = 0;

            // Count the vertices being added.
            for (Vertex<Integer> vertex : graph1.vertices()) {
                count++;
            }

            assertTrue(count == i + 1);
 
        }

        // Make sure the iterator isnt modifying the graph
        Iterator<Vertex<Integer>> iter = graph1.vertices().iterator();

        while (iter.hasNext()) {

            iter.next();
            iter.remove();

        }

        boolean hasElement = false;
        for (Vertex<Integer> vertex : graph1.vertices()) {
            hasElement = true;
            break;
        }

        assertTrue(hasElement);

    }

    @Test
    public void testEdges() {

        for (int i = 0; i < 30; i++) {

            Vertex<Integer> v1 = graph1.insert(i);
            Vertex<Integer> v2 = graph1.insert(i + 1);

            Edge<Integer> edge12 = graph1.insert(v1, v2, i * 10 + i + 1);

            int count = 0;

            // Count the Edges being added.
            for (Edge<Integer> edge : graph1.edges()) {
                count++;
            }

            assertTrue(count == i + 1);

        }

        // Make sure the iterator isnt modifying the graph
        Iterator<Vertex<Integer>> iter = graph1.vertices().iterator();

        while (iter.hasNext()) {

            iter.next();
            iter.remove();

        }

        boolean hasElement = false;
        for (Edge<Integer> edge : graph1.edges()) {
            hasElement = true;
            break;
        }

        assertTrue(hasElement);

    }

    @Test
    public void testOutgoing() {

        ArrayList<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();

        Vertex<Integer> v1 = graph1.insert(1);

        for (int i = 0; i < 15; i++) {

            Vertex<Integer> v2 = graph1.insert(i);

            Edge<Integer> edge12 = graph1.insert(v1, v2, i);

            edges.add(edge12);

            int count = 0;

            // Count the number of ourgoing edges
            for (Edge<Integer> edge : graph1.outgoing(v1)) {
                count++;
            }

            assertTrue(count == i + 1);

        }

        // Make sure the iterator isnt modifying the graph
        Iterator<Edge<Integer>> iter = graph1.outgoing(v1).iterator();

        while (iter.hasNext()) {

            iter.next();
            iter.remove();

        }

        boolean hasElement = false;
        for (Edge<Integer> edge : graph1.outgoing(v1)) {
            hasElement = true;
            break;
        }

        assertTrue(hasElement);

        // Make sure removing elements changes the graph

        int count = 1;

        for (Edge<Integer> edge : edges) {

            graph1.remove(edge);

            int outgoingCount = 0;

            // Count the number of ourgoing edges
            for (Edge<Integer> outEdge : graph1.outgoing(v1)) {
                outgoingCount++;
            }

            assertTrue(15 - count == outgoingCount);

            count++;

        }

    }

    @Test
    public void testIncoming() {

        ArrayList<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();

        Vertex<Integer> v1 = graph1.insert(1);

        for (int i = 0; i < 15; i++) {

            Vertex<Integer> v2 = graph1.insert(i);

            Edge<Integer> edge12 = graph1.insert(v2, v1, i);

            edges.add(edge12);

            int count = 0;

            // Count the number of ourgoing edges
            for (Edge<Integer> edge : graph1.incoming(v1)) {
                count++;
            }

            assertTrue(count == i + 1);

        }

        // Make sure the iterator isnt modifying the graph
        Iterator<Edge<Integer>> iter = graph1.incoming(v1).iterator();

        while (iter.hasNext()) {

            iter.next();
            iter.remove();

        }

        boolean hasElement = false;
        for (Edge<Integer> edge : graph1.incoming(v1)) {
            hasElement = true;
            break;
        }

        assertTrue(hasElement);

        // Make sure removing edges modifies the graph

        int count = 1;

        for (Edge<Integer> edge : edges) {

            graph1.remove(edge);

            int incomingCount = 0;

            // Count the number of ourgoing edges
            for (Edge<Integer> outEdge : graph1.incoming(v1)) {
                incomingCount++;
            }

            assertTrue(15 - count == incomingCount);

            count++;

        }

    }

    @Test
    public void testFrom() {

        Vertex<Integer> v1 = graph1.insert(1);
        Vertex<Integer> v2 = graph1.insert(2);

        Edge<Integer> e12 = graph1.insert(v1, v2, 12);

        assertEquals(v1, graph1.from(e12));

    }

    @Test
    public void testTo() {

        Vertex<Integer> v1 = graph1.insert(1);
        Vertex<Integer> v2 = graph1.insert(2);

        Edge<Integer> e12 = graph1.insert(v1, v2, 12);

        assertEquals(v2, graph1.to(e12));

    }

    @Test
    public void testSetLabelVertex() {

        Vertex<Integer> v1 = graph1.insert(1);
        Vertex<Integer> v2 = graph1.insert(2);

        graph1.label(v1, "Test Label");

        assertEquals("Test Label", graph1.label(v1));

        graph1.label(v1, 12);

        assertEquals(12, graph1.label(v1));

        graph1.label(v1, v2);

        assertEquals(v2, graph1.label(v1));

    }

    @Test
    public void testSetLabelEdge() {

        Vertex<Integer> v1 = graph1.insert(1);
        Vertex<Integer> v2 = graph1.insert(2);

        Edge<Integer> e12 = graph1.insert(v1, v2, 12);

        graph1.label(e12, "Test Label");

        assertEquals("Test Label", graph1.label(e12));

        graph1.label(e12, 12);

        assertEquals(12, graph1.label(e12));

        graph1.label(e12, v1);

        assertEquals(v1, graph1.label(e12));

    }

    @Test
    public void testGetLabelVertex() {
        
        Vertex<Integer> v1 = graph1.insert(1);
        Vertex<Integer> v2 = graph1.insert(2);

        graph1.label(v1, "Test Label");

        assertEquals("Test Label", graph1.label(v1));

        graph1.label(v1, 12);

        assertEquals(12, graph1.label(v1));

        graph1.label(v1, v2);

        assertEquals(v2, graph1.label(v1));


    }

    @Test
    public void testGetLabelEdge() {

        Vertex<Integer> v1 = graph1.insert(1);
        Vertex<Integer> v2 = graph1.insert(2);

        Edge<Integer> e12 = graph1.insert(v1, v2, 12);

        graph1.label(e12, "Test Label");

        assertEquals("Test Label", graph1.label(e12));

        graph1.label(e12, 12);

        assertEquals(12, graph1.label(e12));

        graph1.label(e12, v1);

        assertEquals(v1, graph1.label(e12));

    }

    @Test
    public void testClearLabels() {

        for (int i = 0; i < 30; i++) {

            Vertex<Integer> v1 = graph1.insert(i);
            Vertex<Integer> v2 = graph1.insert(i);

            Edge<Integer> e12 = graph1.insert(v1, v2, 12);

            graph1.label(v1, "Test Label");
            graph1.label(v2, "Test Label");
            graph1.label(e12, "Test Label");

        }

        graph1.clearLabels();

        for (Vertex<Integer> vertex : graph1.vertices()) {
            assertEquals(null, graph1.label(vertex));
        }

        for (Edge<Integer> edge : graph1.edges()) {
            assertEquals(null, graph1.label(edge));
        }

    }

    @Test
    public void testToString() {

        Vertex<Integer> v1 = graph1.insert(1);
        Vertex<Integer> v2 = graph1.insert(2);

        Edge<Integer> e12 = graph1.insert(v1, v2, 12);

        String expected = "digraph {\n  \"1\";\n  \"2\";\n  \"1\" -> \"2\" [label=\"12\"];\n}";

        assertEquals(expected, graph1.toString());

        Vertex<Integer> v3 = graph1.insert(3);
        Vertex<Integer> v4 = graph1.insert(4);

        Edge<Integer> e34 = graph1.insert(v3, v4, 34);

        expected = "digraph {\n  \"1\";\n  \"2\";\n  \"3\";\n  \"4\";\n  \"1\" -> \"2\" [label=\"12\"];\n  \"3\" -> \"4\" [label=\"34\"];\n}";

        assertEquals(expected, graph1.toString());



    }

}