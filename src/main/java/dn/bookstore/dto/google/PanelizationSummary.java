package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class PanelizationSummary {
    @JsonProperty("containsEpubBubbles")
    public boolean getContainsEpubBubbles() {
        return this.containsEpubBubbles;
    }

    boolean containsEpubBubbles;

    @JsonProperty("containsImageBubbles")
    public boolean getContainsImageBubbles() {
        return this.containsImageBubbles;
    }

    boolean containsImageBubbles;
}
