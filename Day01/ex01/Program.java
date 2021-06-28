public class Program {
    public static void main(String[] args) {
        UserIdsGenerator uig = UserIdsGenerator.getInstance();
        UserIdsGenerator i = UserIdsGenerator.getInstance();
        System.out.println(uig.generateId());
        System.out.println(i.generateId());


        User mike = new User("Mike", 1100);
        User john = new User("John", 2300);
        User alice = new User("Alice", 3500);
        User kate = new User("Kate", 800);
        mike.printContent();
        john.printContent();
        alice.printContent();
        kate.printContent();
    }
}
