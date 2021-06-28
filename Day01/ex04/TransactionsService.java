import java.util.UUID;

public class TransactionsService {
    private UsersArrayList users;
    private TransactionsLinkedList trList;

    TransactionsService() {
        trList = new TransactionsLinkedList();
        users = new UsersArrayList();
    }

    public void addUser(User user) {
        users.addUser(user);
    }

    public Integer retrieveBalance(Integer id) throws UserNotFoundException {
        return users.retrieveUserById(id).getBalance();
    }

    public Integer retrieveBalanceByIndex(Integer index) {
        return users.retrieveUserByIndex(index).getBalance();
    }

    public void makeTransaction(Integer id1, Integer id2, Integer amount) throws IllegalTransactionException, UserNotFoundException {
        User sender = users.retrieveUserById(id1);
        User recipient = users.retrieveUserById(id2);
        if (amount < 0) {
            Transaction tr1 = new Transaction(recipient, sender, TransferCategory.INCOME, amount);
            Transaction tr2 = new Transaction(sender, recipient, TransferCategory.OUTCOME, amount, tr1.getId());
            users.retrieveUserById(id1).addTransaction(tr1);
            if (retrieveBalance(id1) + amount < 0) {
                trList.addTransaction(tr2);
                throw new IllegalTransactionException();
            }
            sender.increaseBalance(amount);
            recipient.decreaseBalance(amount);
            users.retrieveUserById(id2).addTransaction(tr2);
        } else {
            Transaction tr1 = new Transaction(recipient, sender, TransferCategory.OUTCOME, amount);
            Transaction tr2 = new Transaction(sender, recipient, TransferCategory.INCOME, amount, tr1.getId());
            users.retrieveUserById(id1).addTransaction(tr2);
            if (retrieveBalance(id2) - amount < 0) {
                trList.addTransaction(tr1);
                throw new IllegalTransactionException();
            }
            sender.increaseBalance(amount);
            recipient.decreaseBalance(amount);
            users.retrieveUserById(id2).addTransaction(tr1);
        }
    }

    public Transaction[] getTransactionsOfUser(Integer id) throws UserNotFoundException{
        return users.retrieveUserById(id).getArrayOfTransactions();
    }

    public void removeTransactionOfUser(UUID trId, Integer id) throws UserNotFoundException,
            TransactionNotFoundException{
        users.retrieveUserById(id).removeTransaction(trId);
    }

    public Transaction[] getArrayOfUnpairedTr() {
        return trList.toArray();
    }

}

class IllegalTransactionException extends Exception {
    public String toString() {
        return "Can't make a transaction";
    }
}


