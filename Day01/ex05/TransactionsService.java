import java.util.UUID;

public class TransactionsService {
    private UsersArrayList users;
    private TransactionsLinkedList trList;

    TransactionsService() {
        trList = new TransactionsLinkedList();
        users = new UsersArrayList();
    }

    public void addUser(User user) {
        System.out.println("Пользователь добавлен с id=" + user.getId().toString());
        users.addUser(user);
    }

    public Integer retrieveBalance(Integer id) throws UserNotFoundException {
        return users.retrieveUserById(id).getBalance();
    }

    public Integer retrieveBalanceByIndex(Integer index) {
        return users.retrieveUserByIndex(index).getBalance();
    }

    public void makeTransaction(Integer id2, Integer id1, Integer amount) throws IllegalTransactionException, UserNotFoundException {
        User sender = users.retrieveUserById(id1);
        User recipient = users.retrieveUserById(id2);
        Transaction tr1;
        Transaction tr2;
        if (amount < 0) {
            tr1 = new Transaction(recipient, sender, TransferCategory.INCOME, amount);
            tr2 = new Transaction(sender, recipient, TransferCategory.OUTCOME, amount, tr1.getId());
            users.retrieveUserById(id1).addTransaction(tr1);
            if (retrieveBalance(id1) + amount < 0) {
                trList.addTransaction(tr2);
                throw new IllegalTransactionException();
            }
            sender.increaseBalance(amount);
            recipient.decreaseBalance(amount);
            users.retrieveUserById(id2).addTransaction(tr2);
        } else {
            tr1 = new Transaction(recipient, sender, TransferCategory.OUTCOME, amount);
            tr2 = new Transaction(sender, recipient, TransferCategory.INCOME, amount, tr1.getId());
            users.retrieveUserById(id1).addTransaction(tr2);
            if (retrieveBalance(id2) - amount < 0) {
                trList.addTransaction(tr1);
                throw new IllegalTransactionException();
            }
            sender.increaseBalance(amount);
            recipient.decreaseBalance(amount);
            users.retrieveUserById(id2).addTransaction(tr1);
        }
        trList.addTransaction(tr1);
        trList.addTransaction(tr2);
    }

    public Transaction[] getTransactionsOfUser(Integer id) throws UserNotFoundException{
        return users.retrieveUserById(id).getArrayOfTransactions();
    }

    public void removeTransactionOfUser(Integer id, String trId) throws UserNotFoundException,
            TransactionNotFoundException{
        System.out.println("Перевод To " + users.retrieveUserById(id).getAmountOfTransaction(trId).getSender().getName() +
                "(id = " + users.retrieveUserById(id).getAmountOfTransaction(trId).getSender().getId() + ") " + users.retrieveUserById(id).getAmountOfTransaction(trId).getAmount() + " удален");
        users.retrieveUserById(id).removeTransaction(trId);
        trList.removeTransaction(id, trId);
    }

    public String getBalanceInfo(Integer id) throws UserNotFoundException {
        return (users.retrieveUserById(id).getName() + " - " + users.retrieveUserById(id).getBalance());
    }

    private boolean findPair(Transaction[] tr, Transaction t) {
        for (int i = 0; i < tr.length; ++i) {
            if (tr[i] != t && tr[i].getId() == t.getId())
                return true;
        }
        return false;
    }

    public Transaction[] getArrayOfUnpairedTr() {
        Transaction[] tr = trList.toArray();

        for (int i = 0; i < tr.length; ++i) {
            if (findPair(tr, tr[i])) {
                trList.removeTransactions(tr[i].getId());
                tr = trList.toArray();
                i = 0;
            }
        }

        return tr;
    }

}

class IllegalTransactionException extends Exception {
    public String toString() {
        return "Can't make a transaction";
    }
}


