public class Program {
    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();
        Integer i;
        for (i = 0; i <= 20; ++i) {
            list.addUser(new User("user".concat(i.toString()), (i + 1) * 100));
        }

        System.out.print("Count of users: ");
        System.out.println(list.retrieveNumOfUsers());

        list.retrieveUserByIndex(3).printContent();

        try {
            list.retrieveUserById(3).printContent();
        }
        catch (UserNotFoundException e) {
            System.err.print(e.toString());
        }

        list.retrieveUserByIndex(0).printContent();

        try {
            list.retrieveUserById(0).printContent();
        }
        catch (UserNotFoundException e) {
            System.err.print(e.toString());
        }

        list.retrieveUserByIndex(10).printContent();

        try {
            list.retrieveUserById(10).printContent();
        }
        catch (UserNotFoundException e) {
            System.err.print(e.toString());
        }

        list.retrieveUserByIndex(7).printContent();

        try {
            list.retrieveUserById(22).printContent();
        }
        catch (UserNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}
