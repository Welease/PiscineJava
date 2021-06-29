import java.util.UUID;

enum TransferCategory{
    OUTCOME,
    INCOME
}

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private TransferCategory category;

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    private Integer amount;

    Transaction(User recipient, User sender, TransferCategory category, Integer amount) {
        id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
        this.category = category;
    }

    Transaction(User recipient, User sender, TransferCategory category, Integer amount, UUID id) {
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
        this.category = category;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer am) {
        if (category == TransferCategory.OUTCOME && am > 0)
            amount = 0;
        else if (category == TransferCategory.INCOME && am < 0)
            amount = 0;
        else
            amount = am;
    }

    public UUID getId() { return id; }

    public void printContent() {
        System.out.print("To " + sender.getName() + "(id = " + sender.getId() + ") ");
        System.out.println((category == TransferCategory.INCOME ? amount : amount * -1) + " with id = " + id.toString());
    }
}
