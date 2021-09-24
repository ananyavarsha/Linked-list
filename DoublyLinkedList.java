
public class DoublyLinkedList {

    private ListNode head;
    private ListNode tail;
    private int length;

    public static class ListNode {
        private int data;
        private ListNode previous;
        private ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public boolean isEmpty() {
        return length == 0; // head==null
    }

    public int length() {
        return length;
    }

    public void displayForward() {
        if (head == null)
            return;
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " <--> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void displayBackward() {
        if (tail == null)
            return;
        ListNode current = tail;
        while (current != null) {
            System.out.print(current.data + " <--> ");
            current = current.previous;
        }
        System.out.println("null");
    }

    public void insertNodeInTheBeggining(int value) {
        ListNode newNode = new ListNode(value);
        if (isEmpty())
            tail = newNode; // here head and tail both will be pointing to null first and after execution
                            // both will be pointing to newNode
        else
            head.previous = newNode;
        newNode.next = head;
        head = newNode;
        length++;
    }

    public void insertNodeAtTheEnd(int value) {
        ListNode newNode = new ListNode(value);
        if (isEmpty())
            head = newNode;
        else {
            tail.next = newNode;
        }
        newNode.previous = tail;
        tail = newNode;
        length++;
    }

    public ListNode deleteAtTheBeggining() {
        ListNode temp = head;
        if (head == tail)
            tail = null;
        else {
            temp.next.previous = null;
        }
        head = head.next;
        temp.next = null;
        return temp;
    }

    public ListNode deleteAtTheEnd() {
        ListNode temp = tail;
        if (head == tail)
            head = null;
        else
            tail.previous.next = null;
        tail = tail.previous;
        temp.previous = null;
        return temp;
    }

    public static void main(String[] args) {
        DoublyLinkedList ddl = new DoublyLinkedList();
        ddl.insertNodeInTheBeggining(3);
        ddl.insertNodeInTheBeggining(4);
        ddl.insertNodeInTheBeggining(5);
        ddl.insertNodeAtTheEnd(6);
        ddl.insertNodeAtTheEnd(7);
        ddl.displayForward();
//        ddl.displayBackward();
        ListNode deleted = ddl.deleteAtTheBeggining();
        System.out.println("deleted node is " + deleted.data);
        ddl.displayForward();

        ListNode delete = ddl.deleteAtTheEnd();
        System.out.println("deleted node is " + delete.data);
        ddl.displayForward();

    }
}
