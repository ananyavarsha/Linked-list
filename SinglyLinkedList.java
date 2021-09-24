public class SinglyLinkedList {

    private ListNode head;

    private static class ListNode {
        private int data;
        private ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void display() {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.data + "-->");
            current = current.next;
        }
        System.out.println("null");
    }

    public void displayObj(ListNode listNode) {
        ListNode current = listNode;

        while (current != null) {
            System.out.print(current.data + "-->");
            current = current.next;
        }
        System.out.println("null");
    }

    public int lengthOfLinklyList() {
        int count = 0;
        ListNode current = head;

        while (current != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }

    public void insertAtTheBeginning(int value) {
        ListNode newNode = new ListNode(value);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtTheEnd(int value) {
        ListNode newNode = new ListNode(value);
        if (head == null) {
            head = newNode;
            return;
        }
        ListNode currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }

    public void insertAtTheGivenPosition(int value, int position) {
        ListNode newNode = new ListNode(value);

        if (position == 1) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode currentNode = head;
            for (int i = 1; i < position - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }

    }

    public ListNode deleteAtThebeginnning() {
        if (head == null)
            return null;
        ListNode currentNode = head;
        head = currentNode.next;
        currentNode.next = null;
        return currentNode;
    }

    public ListNode deleteAtTheEnd() {
        if (head == null || head.next == null)
            return head;
        ListNode currentNode = head;
        ListNode previousNode = null;
        while (currentNode.next != null) {
            previousNode = currentNode;// if currentNode traverse to the last node the previousNode traverses the
                                       // second last node
            currentNode = currentNode.next;
        }
        previousNode.next = null;
        return currentNode;

    }

    public ListNode deleteFromGivenPosition(int position) {
        if (position == 1) {
            ListNode deleted = head;
            head = head.next;
            return deleted;
        } else {
            ListNode currentNode = head;
            ListNode nextNode = null;
            for (int i = 1; i < position - 1; i++) {
                currentNode = currentNode.next;
                nextNode = currentNode.next;
            }
            currentNode.next = nextNode.next;
            return nextNode;
        }
    }

    public Boolean searchElement(int element) {
        ListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == element) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public ListNode reverseLinkedList(ListNode head) {
        if (head == null)
            return head;
        ListNode currentNode = head;
        ListNode nextNode = null;
        ListNode previousNode = null;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }
//  first approach
//    public ListNode findMiddleNode() {
//        ListNode currentNode = head;
//        int length = lengthOfLinklyList();
//        System.out.println("length is "+length);
//        for (int i = 0; i < length / 2 ; i++) {
//            currentNode = currentNode.next;
//        }
//        return currentNode;
//    }

    public ListNode findMiddleNode() {
        // we have two pointers, if slowPtr traverses one node at a time while fast
        // pointer traverses two nodes at a time
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }

//    public void findNthNodeFromTheEndOfTheList(int position) {
//        ListNode currentNode = head;
//        int length = lengthOfLinklyList() - position;
//        for (int i = 0; i < length; i++) {
//            currentNode = currentNode.next;
//        }
//        System.out.println("nth node is " + currentNode.data);
//    }

    public void findNthNodeFromTheEndOfTheList(int position) {
        ListNode mainPtr = head;
        ListNode refPtr = head;
        int count = 0;
        while (count < position) {
            // refPtr will traverse the given position times
            refPtr = refPtr.next;
            count++;
        }

        while (refPtr != null) {
            // Now both the pointers will move parallel to each other
            refPtr = refPtr.next;
            mainPtr = mainPtr.next;
        }
        System.out.println("nth node is " + mainPtr.data);
    }

    public void sortList() {
        ListNode currentNode = head;
        ListNode nextNode = null;
        int temp;
        while (currentNode != null) {
            nextNode = currentNode.next;
            while (nextNode != null) {
                if (currentNode.data > nextNode.data) {
                    temp = currentNode.data;
                    currentNode.data = nextNode.data;
                    nextNode.data = temp;
                }
                nextNode = nextNode.next;
            }
            currentNode = currentNode.next;
        }
    }

    public void removeDuplicates() {
        sortList();
        ListNode currentNode = head;
        while (currentNode != null && currentNode.next != null) {
            if (currentNode.data == currentNode.next.data) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }
    }

    public ListNode insertInSortedLinkedList(ListNode newNode) {
        // ListNode newNode = new ListNode(value);
        ListNode currentNode = head;
        ListNode temp = null;
        while (currentNode != null && currentNode.data < newNode.data) {
            temp = currentNode;
            currentNode = currentNode.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    public void removeGivenKey(int key) {
        ListNode currentNode = head;
        ListNode ref = null;
        while (currentNode.next != null) {
            if (currentNode.data == key) {
                ref.next = currentNode.next;
                return;
            }
            ref = currentNode;
            currentNode = currentNode.next;
        }
    }

    public boolean encounterLoop() {
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (fastPtr == slowPtr)
                return true;
        }
        return false;
    }

    // This function can be proved by Floyd's algorithm
    public ListNode startingPointOfLoop() {
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (fastPtr == slowPtr)
                return getStartingNode(slowPtr);
        }
        return head;
    }

    public ListNode getStartingNode(ListNode slowPtr) {
        ListNode temp = head;
        while (temp != slowPtr) {
            temp = temp.next;
            slowPtr = slowPtr.next;
        }
        return temp;
    }

    public void removeLoops() {
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        if (fastPtr == slowPtr) {
            removeLoops(slowPtr);
            return;
        }
    }

    public void removeLoops(ListNode slowPtr) {
        ListNode temp = head;
        while (slowPtr.next != temp.next) {
            temp = temp.next;
            slowPtr = slowPtr.next;
        }
        slowPtr.next = null;
    }

    public static void main(String[] args) {
        SinglyLinkedList ssl = new SinglyLinkedList();
        ssl.head = new ListNode(10);
        ListNode second = new ListNode(3);
        ListNode third = new ListNode(19);
        ListNode fourth = new ListNode(11);
        ListNode fifth = new ListNode(6);

        // Now connect them together to form a chain
        ssl.head.next = second; // 10-->5
        second.next = third; // 10-->5-->3
        third.next = fourth; // 10-->5-->3-->14-->null
        fourth.next = fifth;
        fifth.next = third;

        ssl.display();
        ssl.insertAtTheBeginning(11);
        ssl.display();

        ssl.insertAtTheEnd(20);
        ssl.display();

        ssl.insertAtTheGivenPosition(9, 2);
        ssl.display();

        ListNode deletedNodeAtBeginning = ssl.deleteAtThebeginnning();
        System.out.println("deleted node is " + deletedNodeAtBeginning.data);
        ssl.display();

        ListNode deletedNodeAtEnd = ssl.deleteAtTheEnd();
        System.out.println("deleted node is " + deletedNodeAtEnd.data);
        ssl.display();

        ListNode deletedNodeAtGivenPosition = ssl.deleteFromGivenPosition(1);
        System.out.println("deleted node is " + deletedNodeAtGivenPosition.data);
        ssl.display();

        System.out.println("Items in the list are as follows");
        ssl.display();
        if (ssl.searchElement(14))
            System.out.println("is present");
        else
            System.out.println("not present");

//        ListNode reverse = ssl.reverseLinkedList(ssl.head);
//        ssl.displayObj(reverse);

        ssl.display();
        ListNode middle = ssl.findMiddleNode();
        System.out.println("middle node is " + middle.data);

        ssl.display();
        ssl.findNthNodeFromTheEndOfTheList(5);

        ssl.display();
        ssl.removeDuplicates();
        ssl.display();

        ssl.display();
        ListNode newNode = new ListNode(15);
        ListNode insert = ssl.insertInSortedLinkedList(newNode);
        ssl.displayObj(insert);

        ssl.display();
        ssl.removeGivenKey(15);
        ssl.display();
//
        ssl.display();
        if (ssl.encounterLoop())
            System.out.println("a loop is encountered");
        else
            System.out.println("a loop is not encountered");

        ssl.display();
        ListNode loop = ssl.startingPointOfLoop();
        System.out.println("starting point of loop is " + loop.data);

        ssl.display();
        ssl.removeLoops();
        ssl.display();

    }
}
