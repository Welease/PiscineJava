public class UserIdsGenerator {
    private static int id = 1;
    private static UserIdsGenerator instance;
    private UserIdsGenerator() {}
    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public Integer generateId() {
        return id++;
    }

    public  UserIdsGenerator getInstanse() { return this; }
}
