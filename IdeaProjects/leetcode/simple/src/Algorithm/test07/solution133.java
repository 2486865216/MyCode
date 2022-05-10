package Algorithm.test07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/16  19:47
 */
public class solution133 {
    Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return node;

        if (visited.containsKey(node)){
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}
