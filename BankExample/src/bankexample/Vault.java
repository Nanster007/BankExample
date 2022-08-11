package bankexample;

public class Vault {
    private Double cash;

    public Vault() {
        this.cash = 0.0;
    }
    
    public Double getCash() {
        return cash;
    }
    
    public void addCash(Double cash) {
        if(cash > 0) {
            this.cash += cash;
        }
    }
    
    public Boolean removeCash(Double cash) {
        Boolean success = false;
        if(this.cash >= cash) {
            this.cash -= cash;
            success = true;
        }
        return success;
    }
}
