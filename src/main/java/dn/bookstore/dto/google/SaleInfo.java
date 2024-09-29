package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.ArrayList;

@Setter
public class SaleInfo {
    @JsonProperty("country")
    public String getCountry() {
        return this.country;
    }

    String country;

    @JsonProperty("saleability")
    public String getSaleability() {
        return this.saleability;
    }

    String saleability;

    @JsonProperty("isEbook")
    public boolean getIsEbook() {
        return this.isEbook;
    }

    boolean isEbook;

    @JsonProperty("listPrice")
    public ListPrice getListPrice() {
        return this.listPrice;
    }

    ListPrice listPrice;

    @JsonProperty("retailPrice")
    public RetailPrice getRetailPrice() {
        return this.retailPrice;
    }

    RetailPrice retailPrice;

    @JsonProperty("buyLink")
    public String getBuyLink() {
        return this.buyLink;
    }

    String buyLink;

    @JsonProperty("offers")
    public ArrayList<Offer> getOffers() {
        return this.offers;
    }

    ArrayList<Offer> offers;
}
