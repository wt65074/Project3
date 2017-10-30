import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
    Six Degrees of Angelina Jolie.

    Some of the stuff I used in here is a little ahead of us. For
    example we have not formally covered maps yet. But I am sure
    you can nevertheless find your way around. :-)

    Do NOT USE MAPS in your code for BFS below! Trust us, you can
    make do just with the existing Graph interface and with Java's
    Queue interface (since BFS needs a queue internally).
*/
public final class Angelina {
    // Graph holding movies and actors as vertices, relationships
    // as edges. All are simply strings.
    private static Graph<String, String> graph =
        new SparseGraph<String, String>();

    // Vertices for the actor we're trying to connect to Angelina
    // Jolie and for Angelina herself.
    private static Vertex<String> actor;
    private static Vertex<String> jolie;

    // Shut up checkstyle.
    private Angelina() {}

    // Read input file and turn it into a Graph.
    //
    // There's one line for each movie, with the fields separated
    // by "/". The first field is the movie, the remaining fields
    // are actors.
    //
    // Generates a bipartite graph in which BOTH movies and actors
    // are vertices. A graph in which all vertices are actors and
    // movies are edges would be HORRIBLE instead (why?).
    //
    // This function also sets up the "actor" and "jolie" globals
    // that will be used to direct the breadth-first-search.
    private static void readInput(String filename, String who)
        throws FileNotFoundException, IOException {
        // keep track of all vertices created so far by name
        Map<String, Vertex<String>> vertices = new HashMap<>();

        // how we read the input
        BufferedReader reader = new BufferedReader(new FileReader(
            new File(filename)));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");

            // find or create vertex for the movie
            Vertex<String> m = vertices.get(data[0]);
            if (m == null) {
                m = graph.insert(data[0]);
                vertices.put(data[0], m);
            }

            for (int i = 1; i < data.length; i++) {
                // find or create vertex for the actor
                Vertex<String> a = vertices.get(data[i]);
                if (a == null) {
                    a = graph.insert(data[i]);
                    vertices.put(data[i], a);
                }

                // double-check for special actors
                if (a.get().equals("Jolie, Angelina")) {
                    jolie = a;
                }
                if (a.get().equals(who)) {
                    actor = a;
                }

                // create two edges, from and to the movie
                graph.insert(m, a, "features");
                graph.insert(a, m, "acts in");
            }
        }
    }

    // Perform a breadth-first search (BFS) starting from Angelina Jolie
    // and stopping when (a) the graph is exhausted or (b) we found the
    // actor we're looking for. Then print the path from the actor back
    // to Angelina and exit the program. Since we're using BFS we can be
    // sure that the resulting path is among the shortest ones.
    private static void solveJolie() {

        // put your code here, use System.exit(0) to stop after printing!

        // Clear any labels
        graph.clearLabels();

        // Create the main queue of vertices.
        Queue<Vertex<String>> queue = new LinkedList<Vertex<String>>();

        // Add the starting vertex.
        queue.add(jolie);

        while (true) {

            // Check if the queue is empty.
            if (queue.size() == 0) {
                System.exit(0); 
            }

            // Get the next vertex from teh queue.
            Vertex<String> currentVertex = queue.remove();

            // If we found the actor.
            if (currentVertex == actor) {

                Vertex<String> vertex = currentVertex;

                // Print the vertex.
                System.out.println(vertex.get());

                do {

                    // Get the vertex's parent vertex.
                    vertex = (Vertex<String>) graph.label(vertex);

                    // Print the vertex.
                    System.out.println(vertex.get());

                } while (vertex != jolie);

                System.exit(0);

            }

            else {

                // Loop through all outgoing edges of the vertex
                for (Edge<String> edge : graph.outgoing(currentVertex)) {

                    // Store the vertex the edge points to.
                    Vertex<String> nextVertex = graph.to(edge);

                    // Check if the vertex has been checked yet.
                    // If it has, dont add it to the queue.
                    if (graph.label(nextVertex) != null) {
                        continue;
                    }

                    else {

                        // Label it so that we know the parent vertex,
                        // and so it is not checked again.
                        graph.label(nextVertex, currentVertex);
                    }

                    // Add the vertex to the queue.
                    queue.add(nextVertex);

                }

            }

        }

    }

    /**
        Main method.
        @param args Command line arguments.
        @throws FileNotFoundException If database file cannot be opened.
        @throws IOException If database file cannot be read properly.
    */
    public static void main(String[] args)
        throws FileNotFoundException, IOException {
        // read the input, initialize globals
        readInput(args[0], args[1]);

        // check that we could find both actors, quit if not
        if (actor == null) {
            System.out.printf("Error: Can't find %s in database.\n", args[1]);
            System.exit(1);
        }
        if (jolie == null) {
            System.out.printf("Error: Can't find Angelina in database.\n");
            System.exit(1);
        }

        // play "six degrees of Anglina Jolie" using breadth-first search
        solveJolie();
    }
}
