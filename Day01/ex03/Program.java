public class Program {
    public static void main(String[] args) {
        User mike = new User("Mike", 1100);
        User john = new User("John", 2300);
        User alice = new User("Alice", 3500);
        User kate = new User("Kate", 800);
        TransactionsLinkedList list = new TransactionsLinkedList();
        Transaction a1 = new Transaction(mike, john, TransferCategory.OUTCOME, -100);
        Transaction a2 = new Transaction(mike, john, TransferCategory.OUTCOME, -200);
        Transaction a3 = new Transaction(alice, john, TransferCategory.OUTCOME, -300);
        Transaction a4 = new Transaction(alice, john, TransferCategory.OUTCOME, -400);
        Transaction a5 = new Transaction(alice, john, TransferCategory.OUTCOME, -500);
        Transaction a6  = new Transaction(mike, john, TransferCategory.OUTCOME, -600);
        Transaction a7 = new Transaction(mike, john, TransferCategory.OUTCOME, -700);
        Transaction a8 = new Transaction(mike, john, TransferCategory.OUTCOME, -800);
        Transaction a9 = new Transaction(mike, john, TransferCategory.OUTCOME, -900);
        list.addTransaction(a1);;
        list.addTransaction(a2);
        list.addTransaction(a3);
        list.addTransaction(a4);
        list.addTransaction(a5);
        list.addTransaction(a6);
        list.addTransaction(a7);
        list.addTransaction(a8);
        list.addTransaction(a9);

        list.print();
        System.out.print("\n\n\n\n");

        try {
            list.removeTransaction(a1.getId());
            list.removeTransaction(a2.getId());
            list.removeTransaction(a3.getId());
            list.removeTransaction(a4.getId());
            list.removeTransaction(a5.getId());
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }

        Transaction[] tr = list.toArray();

        for (int i = 0; i < tr.length; ++i) {
            tr[i].printContent();
        }

        mike.addTransaction(a1);
        alice.addTransaction(a3);
        alice.addTransaction(a4);
        tr = alice.getArrayOfTransactions();

        System.out.print("\n\n\n");
        for (int i = 0; i < tr.length; ++i) {
            tr[i].printContent();
        }
    }
}
