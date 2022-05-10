package Offer.day02;

import Offer.Utils.Node;
import jdk.nashorn.internal.ir.ReturnNode;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/19  19:49
 */
public class Solution03 {
    public Node copyRandomList(Node head) {
        if (head == null) return head;

        /*Map<Node, Node> map = new HashMap<>();

        Node node = head;
        while (node != null){
            Node clone = new Node(node.val);
            map.put(node, clone);
            node = node.next;
        }

        node = head;

        while (node != null){
            map.get(node).random = map.get(node.random);
            map.get(node).next = map.get(node.next);
            node = node.next;
        }
        return map.get(head);*/
        for (Node node = head; node != null; node = node.next.next) {
            Node clone = new Node(node.val);
            clone.next = node.next;
            node.next = clone;
        }
        for (Node node = head; node != null; node = node.next.next) {
            if (node.random != null){
                node.next.random = node.random.next;
            }
        }

        Node newNode = head.next;
        Node temp;
        for (Node node = head; node != null && node.next != null;) {
            temp = node.next;
            node.next = temp.next;
            node = temp;
        }
        return newNode;
    }
}
