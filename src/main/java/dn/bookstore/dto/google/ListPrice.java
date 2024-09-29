package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ListPrice {
    @JsonProperty("amount")
    public double getAmount() {
        return this.amount;
    }

    double amount;

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    String currencyCode;

    @JsonProperty("amountInMicros")
    public double getAmountInMicros() {
        return this.amountInMicros;
    }

    double amountInMicros;
}
