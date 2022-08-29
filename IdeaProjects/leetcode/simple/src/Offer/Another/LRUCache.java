package Offer.Another;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/5/7  19:50
 */
public class LRUCache {
    class DLinkNode{
        int key;
        int value;
        DLinkNode pre;
        DLinkNode next;

        public DLinkNode() {
        }

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkNode> cacheMap = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new DLinkNode();
        this.tail = new DLinkNode();
        this.head.next = tail;
        this.tail.pre = head;
    }

    public int get(int key) {
        DLinkNode node = cacheMap.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkNode node = cacheMap.get(key);
        if (node == null){
            DLinkNode newNode = new DLinkNode(key, value);
            cacheMap.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity){
                DLinkNode tail = removeTail();
                cacheMap.remove(tail.key);
                size--;
            }
        }else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkNode newNode) {
        newNode.next = head.next;
        newNode.pre = head;
        head.next.pre = newNode;
        head.next = newNode;
    }

    private void removeNode(DLinkNode node){
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    private DLinkNode removeTail() {
        DLinkNode node = tail.pre;
        removeNode(node);
        return node;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }
}
