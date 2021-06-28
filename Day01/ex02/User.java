public class User {
    private final Integer  id;
    private String          name;
    private Integer         balance;

    User(String name, Integer balance) {
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
}
