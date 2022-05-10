package Algorithm.test07;

import Algorithm.Util.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/17  19:02
 */
public class solution138 {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node current = head;
        Map<Node, Node> map = new HashMap<>();
        while (current != null){
            Node cloneNode = new Node(current.val);
            map.put(current, cloneNode);
            current = current.next;
        }
        current = head;
        while (current != null){
            map.get(current).random = map.get(current.random);
            map.get(current).next = map.get(current.next);
            current = current.next;
        }

        return map.get(head);
    }
}
