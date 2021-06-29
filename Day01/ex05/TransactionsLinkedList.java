import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    Integer     count;
    Node        head;


    TransactionsLinkedList() {
        head = new Node(null);
        head.setNext(head);
        head.setPrev(head);
        count = 0;
    }

    public void addTransaction(Transaction tr){
        Node tmp = head.getPrev();
        tmp.setNext(new Node(tr, head, tmp));
        head.setPrev(tmp.getNext());
        count++;
    }

    public void print() {
        Node tmp = head.getNext();
        while (tmp != head) {
            tmp.getTransaction().printContent();
            tmp = tmp.getNext();
        }
    }

    public void removeTransaction(Integer i, String id) throws TransactionNotFoundException {
        Node tmp = head.getNext();
        while (tmp != head) {
            if (tmp.getTransaction().getId().toString().equals(id) && tmp.getTransaction().getRecipient().getId().equals(i)) {
                Node prev = tmp.getPrev();
                prev.setNext(tmp.getNext());
                tmp.getNext().setPrev(prev);
                count--;
                return;
            }
            tmp = tmp.getNext();
        }
        throw new TransactionNotFoundException();
    }

    public void removeTransactions(UUID uuid) {
        Node tmp = head.getNext();
        Node t;
        while (tmp != head) {
            if (tmp.getTransaction().getId().equals(uuid)) {
                t = tmp.getNext();
                Node prev = tmp.getPrev();
                prev.setNext(tmp.getNext());
                tmp.getNext().setPrev(prev);
                count--;
                tmp = t;
                continue;
            }
            tmp = tmp.getNext();
        }
    }

    public Transaction[] toArray() {
        Transaction[] res = new Transaction[count];
        Node tmp = head.getNext();
        for (int i = 0; i < count; ++i) {
            res[i] = tmp.getTransaction();
            tmp = tmp.getNext();
        }
        return res;
    }

    private class Node {
        Transaction data;
        Node next;
        Node prev;

        Node(Transaction tr) {
            data = tr;
        }

        Node(Transaction tr, Node n, Node p) {
            data = tr;
            next = n;
            prev = p;
        }

        Node getNext() { return next; }

        Node getPrev() { return prev; }

        Transaction getTransaction() { return data; }

        void setNext(Node n) { next = n; }

        void setPrev(Node n) { prev = n; }
    }
}
