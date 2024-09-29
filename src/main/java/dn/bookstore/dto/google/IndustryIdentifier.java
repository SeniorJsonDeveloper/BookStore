package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class IndustryIdentifier {
    @JsonProperty("type")
    public String getType() {
        return this.type;
    }

    String type;

    @JsonProperty("identifier")
    public String getIdentifier() {
        return this.identifier;
    }

    String identifier;
}
