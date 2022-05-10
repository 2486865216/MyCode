package src.danlilnk;

public class linktest {
    Node header;

    int size = 0;
    public int size(){
        return size;
    }
    public void add(Object data){
        if (header==null){
            header = new Node(data,null);
            size++;
        }else{
            Node lastNode = findlist(header);
            lastNode.next = new Node(data,null);
            size++;
        }
    }

    private Node findlist(Node header) {
        if (header.next==null){
            return header;
        }
        return findlist(header.next);
    }
}
