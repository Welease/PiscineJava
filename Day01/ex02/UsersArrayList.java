public class UsersArrayList implements UsersList {
    private static final int MAX_COUNT = 10;
    private User[] array;
    private Integer count;
    private Integer capacity;

    UsersArrayList() {
        array = new User[MAX_COUNT];
        count = 0;
        capacity = MAX_COUNT;
    }

    public void addUser(User user) {
        if (count.equals(capacity)) {
            capacity += capacity / 2;
            User[] tmp = new User[capacity];
            for (int i = 0; i < count; ++i) {
                tmp[i] = array[i];
            }
            array = tmp;
        }
        array[count++] = user;
    }

    public User retrieveUserById(Integer id) throws UserNotFoundException{
        for (int i = 0; i < count; ++i) {
            if (id.equals(array[i].getId()))
                return array[i];
        }
        throw new UserNotFoundException();
    }

    public User retrieveUserByIndex(Integer index) {
        return array[index];
    }

    public Integer retrieveNumOfUsers() { return count; }

}
