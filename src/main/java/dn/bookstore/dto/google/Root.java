package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.ArrayList;

@Setter
public class Root {
    @JsonProperty("kind")
    public String getKind() {
        return this.kind;
    }

    String kind;

    @JsonProperty("totalItems")
    public double getTotalItems() {
        return this.totalItems;
    }

    double totalItems;

    @JsonProperty("items")
    public ArrayList<Item> getItems() {
        return this.items;
    }

    ArrayList<Item> items;
}
