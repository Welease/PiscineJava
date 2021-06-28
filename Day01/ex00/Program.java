public class Program {
    public static void main(String[] args) {
        User mike = new User("Mike", 1100);
        User john = new User("John", 2300);
        User alice = new User("Alice", 3500);
        User kate = new User("Kate", 800);

        mike.printContent();
        john.printContent();
        alice.printContent();
        kate.printContent();

        Transaction tr1 = new Transaction(mike, john, TransferCategory.OUTCOME, -300);
        Transaction tr2 = new Transaction(alice, kate, TransferCategory.INCOME, 200);
        Transaction tr3 = new Transaction(kate, alice, TransferCategory.OUTCOME, -100);
        Transaction tr4 = new Transaction(john, mike, TransferCategory.INCOME, 300);
        Transaction tr5 = new Transaction(alice, john, TransferCategory.OUTCOME, -400);

        System.out.println();

        tr1.printContent();
        tr2.printContent();
        tr3.printContent();
        tr4.printContent();
        tr5.printContent();

    }
}
