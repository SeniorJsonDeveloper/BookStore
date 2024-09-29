package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class Epub {

    @JsonProperty("isAvailable")
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    boolean isAvailable;
}
