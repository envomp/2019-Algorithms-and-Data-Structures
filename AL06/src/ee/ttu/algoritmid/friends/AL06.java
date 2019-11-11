package ee.ttu.algoritmid.friends;

import org.w3c.dom.Node;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class AL06 {
    public UndirectedGraph graph = new UndirectedGraph();

    private static class UndirectedGraph {
        private HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        private HashSet<Integer> nodes = new HashSet<>();

        /**
         * Add undirected edge to the graph.
         *
         * @param one   one element of the edge
         * @param other the other element of edge
         */
        public void addEdge(Integer one, Integer other) {
            contain(one, other);
            contain(other, one);
        }

        private void contain(Integer one, Integer other) {
            if (!graph.containsKey(one)) {
                List<Integer> edges = new ArrayList<>();
                edges.add(other);
                graph.put(one, edges);
            } else {
                if (!graph.get(one).contains(other)) {
                    graph.get(one).add(other);
                }
            }
        }

        /**
         * Return the graph.
         *
         * @return the internal graph structure.
         */
        public HashMap<Integer, List<Integer>> getGraph() {
            return graph;
        }

        /**
         * Perform breadth first search.
         *
         * @param start the vertex to start the search from
         * @param goal  the goal vertex to find
         * @return the number of vertices of the path from start to goal including start and goal (e.g.,
         * start = A, goal = C, path = A, B, C => 3) and the path itself as a list of integers.
         * NB! You can return null as path and only return the number of nodes
         * that connect the start and goal vertices for partial credit
         * (some tests only check for number of nodes)
         */
        public SimpleEntry<Integer, List<Integer>> breadthFirstSearch(Integer start, Integer goal) {

            List<Integer> directions = new LinkedList<>();
//            Queue<Integer> q = new ArrayDeque<>();
//            q.add(start);
//            System.out.println(nodes);
//            HashSet<Integer> discovered = new HashSet<>();
//            discovered.add(start);

            HashSet<Integer> vis = new HashSet<>();
            Map<Integer, Integer> prev = new HashMap<>();

            Queue<Integer> q = new LinkedList<>();

            Integer current = start;
            q.add(current);
            vis.add(current);
            while (!q.isEmpty()) {
                current = q.remove();
                if (current.equals(goal)) {
                    break;
                } else {
                    for (Integer node : graph.get(current)) {
                        if (!vis.contains(node)) {
                            q.add(node);
                            vis.add(node);
                            prev.put(node, current);
                        }
                    }
                }
            }
            if (!current.equals(goal)) {
                System.out.println("can't reach destination");
            }
            for (Integer node = goal; node != null; node = prev.get(node)) {
                directions.add(node);
            }

            Collections.reverse(directions);
            return new SimpleEntry<>(directions.size(), directions);

        }
    }

    /**
     * Use buildGraphAndFindLink to build a graph using the UndirectedGraph class and then use its breadthFirstSearch to
     * return the links.
     *
     * @param friends the list of friends as pairs (people are denoted as integers)
     *                (e.g., [[2, 7], [9, 5]] means that 2 is friends with 7 and 9 is friends with 5)
     * @param pair    the pair to be searched
     * @return the number of people that connect the searched pair including the pair itself (e.g., if pair is
     * = [1, 4] and path is [1, 2, 3, 4], the number of people is 4) the list of people that connect
     * the searched pair (e.g., pair = [1, 4] => result = [1, 7, 11, 3, 2, 4])
     */
    public SimpleEntry<Integer, List<Integer>> buildGraphAndFindLink(List<SimpleEntry<Integer, Integer>> friends,
                                                                     SimpleEntry<Integer, Integer> pair) {

        for (SimpleEntry<Integer, Integer> friend : friends) {
            graph.addEdge(friend.getKey(), friend.getValue());
            graph.nodes.add(friend.getKey());
            graph.nodes.add(friend.getValue());
        }

        UndirectedGraph graph = new UndirectedGraph();
        graph.graph = this.graph.graph;
        graph.nodes = this.graph.nodes;

        return graph.breadthFirstSearch(pair.getKey(), pair.getValue());
    }

    public static void main(String[] args) {

        AL06 al06 = new AL06();
        List<SimpleEntry<Integer, Integer>> graph = new ArrayList<>();
        graph.add(new SimpleEntry<>(1, 2));
        graph.add(new SimpleEntry<>(2, 3));
        graph.add(new SimpleEntry<>(3, 4));
        graph.add(new SimpleEntry<>(4, 5));
        graph.add(new SimpleEntry<>(2, 7));
        graph.add(new SimpleEntry<>(5, 7));

        SimpleEntry<Integer, Integer> pair = new SimpleEntry<>(1, 5);
        System.out.println(al06.buildGraphAndFindLink(graph, pair));

    }
}
