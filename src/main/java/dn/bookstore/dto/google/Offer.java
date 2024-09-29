package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class Offer {
        @JsonProperty("finskyOfferType")
        public int getFinskyOfferType() {
            return this.finskyOfferType;
        }

    int finskyOfferType;

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
    }
