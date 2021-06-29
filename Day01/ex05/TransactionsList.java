import java.util.UUID;

interface TransactionsList {
    public void addTransaction(Transaction tr);

    public void removeTransaction(Integer i, String id) throws TransactionNotFoundException;

    public Transaction[] toArray();
}

class TransactionNotFoundException extends Exception {
    public String toString() {
        return "Can't find such transaction";
    }
}
