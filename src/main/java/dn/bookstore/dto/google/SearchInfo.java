package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class SearchInfo {
    @JsonProperty("textSnippet")
    public String getTextSnippet() {
        return this.textSnippet;
    }

    String textSnippet;
}
