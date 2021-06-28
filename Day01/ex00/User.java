public class User {
    private static Integer  newId = 1;
    private Integer         id;
    private String          name;
    private Integer         balance;

    User(String name, Integer balance) {
        this.balance = balance < 0 ? 0 : balance;
        this.id = newId++;
        this.name = name;
    }

    public String getName() { return name; }

    public void printContent() {
        System.out.print("User:");
        System.out.print(name + " ");
        System.out.print(id);
        System.out.println(" " + balance.toString());
    }
}
