package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class AccessInfo {
    @JsonProperty("country")
    public String getCountry() {
        return this.country;
    }

    String country;

    @JsonProperty("viewability")
    public String getViewability() {
        return this.viewability;
    }

    String viewability;

    @JsonProperty("embeddable")
    public boolean getEmbeddable() {
        return this.embeddable;
    }

    boolean embeddable;

    @JsonProperty("publicDomain")
    public boolean getPublicDomain() {
        return this.publicDomain;
    }

    boolean publicDomain;

    @JsonProperty("textToSpeechPermission")
    public String getTextToSpeechPermission() {
        return this.textToSpeechPermission;
    }

    String textToSpeechPermission;

    @JsonProperty("epub")
    public Epub getEpub() {
        return this.epub;
    }

    Epub epub;

    @JsonProperty("pdf")
    public Pdf getPdf() {
        return this.pdf;
    }

    Pdf pdf;

    @JsonProperty("webReaderLink")
    public String getWebReaderLink() {
        return this.webReaderLink;
    }

    String webReaderLink;

    @JsonProperty("accessViewStatus")
    public String getAccessViewStatus() {
        return this.accessViewStatus;
    }

    String accessViewStatus;

    @JsonProperty("quoteSharingAllowed")
    public boolean getQuoteSharingAllowed() {
        return this.quoteSharingAllowed;
    }

    boolean quoteSharingAllowed;
}
