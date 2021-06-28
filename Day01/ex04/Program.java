public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        service.addUser(new User("Mike", 1000));
        service.addUser(new User("Nick", 700));
        service.addUser(new User("Alice", 1500));
        service.addUser(new User("Kate", 2000));

        try {
            System.out.println("Mike before: " + service.retrieveBalance(0));
            System.out.println("Alice before: " + service.retrieveBalance(2));
            service.makeTransaction(0, 2, -200);
            System.out.println("Mike after: " + service.retrieveBalance(0));
            System.out.println("Alice after: " + service.retrieveBalance(2));
            System.out.println("\n\n");
            System.out.println("Alice before: " + service.retrieveBalance(2));
            System.out.println("Kate before: " + service.retrieveBalance(3));
            service.makeTransaction(2, 3, 100);
            System.out.println("Alice after: " + service.retrieveBalance(2));
            System.out.println("Kate after: " + service.retrieveBalance(3));
        } catch (Exception ex) {}
    }
}
