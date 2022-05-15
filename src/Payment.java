import java.util.Objects;

public class Payment {
    private String payer;
    private double amount;

    public String getPayer() {
        return payer;
    }

    public double getAmount() {
        return amount;
    }

    public Payment(String payer, double amount) {
        this.payer = payer;
        this.amount = amount;
    }

}
