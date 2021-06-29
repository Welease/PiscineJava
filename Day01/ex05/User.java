import java.util.UUID;

public class User {
    private final Integer  id;
    private String          name;
    private Integer         balance;
    private TransactionsLinkedList transactions;

    User(String name, Integer balance) {
        transactions = new TransactionsLinkedList();
        this.balance = balance < 0 ? 0 : balance;
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
    }

    public void setBalance(Integer balance1) {
        balance = balance1;
    }

    public void increaseBalance(Integer dx) {
        balance += dx;
    }

    public void decreaseBalance(Integer dx) {
        balance -= dx;
    }

    public Integer getBalance() { return balance; }

    public String getName() { return name; }

    public Integer getId() { return id; }

    public void printContent() {
        System.out.print("User:");
        System.out.print(name + " ");
        System.out.print(id);
        System.out.println(" " + balance.toString());
    }

    public void addTransaction(Transaction tr) {
        transactions.addTransaction(tr);
    }

    public void removeTransaction(String id) throws TransactionNotFoundException{
        transactions.removeTransaction(this.id, id);
    }

    public Transaction[] getArrayOfTransactions() {
        return transactions.toArray();
    }

    Transaction getAmountOfTransaction(String uuid) {
        Transaction[] tr = transactions.toArray();
        for (int i = 0; i < tr.length; ++i) {
            if (tr[i].getId().toString().equals(uuid))
                return tr[i];
        }
        return null;
    }
}
