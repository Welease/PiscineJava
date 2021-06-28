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

    public void removeTransaction(Transaction tr) throws TransactionNotFoundException{
        transactions.removeTransaction(tr.getId());
    }

    public Transaction[] getArrayOfTransactions() {
        return transactions.toArray();
    }
}
