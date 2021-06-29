public class UsersArrayList implements UsersList {
    private User[] array;
    private Integer count;
    private Integer capacity;

    UsersArrayList() {
        array = new User[10];
        count = 0;
        capacity = 10;
    }

    public void addUser(User user) {
        if (count.equals(capacity)) {
            capacity *= 2;
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
