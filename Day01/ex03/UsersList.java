class UserNotFoundException extends Exception {
    public String toString() {
        return "Can't find such user";
    }
}

interface UsersList {

    public void addUser(User user);

    public User retrieveUserById(Integer id) throws UserNotFoundException;

    public User retrieveUserByIndex(Integer index);

    public Integer retrieveNumOfUsers();
}
