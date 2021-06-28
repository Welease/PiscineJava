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

    private Integer amount;

    Transaction(User sender, User recipient, TransferCategory category, Integer amount) {
        id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
        this.category = category;
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
        System.out.print(sender.getName());
        System.out.print(" -> ");
        System.out.print(recipient.getName());
        System.out.print(category == TransferCategory.OUTCOME ? ", " : ", +");
        System.out.print(amount);
        System.out.print(category == TransferCategory.OUTCOME ? ", OUTCOME, " : ", INCOME, ");
        System.out.println(id.toString());
    }
}
