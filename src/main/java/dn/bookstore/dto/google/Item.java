package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class Item {
    @JsonProperty("kind")
    public String getKind() {
        return this.kind;
    }

    String kind;

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }

    String id;

    @JsonProperty("etag")
    public String getEtag() {
        return this.etag;
    }

    String etag;

    @JsonProperty("selfLink")
    public String getSelfLink() {
        return this.selfLink;
    }

    String selfLink;

    @JsonProperty("volumeInfo")
    public VolumeInfo getVolumeInfo() {
        return this.volumeInfo;
    }

    VolumeInfo volumeInfo;

    @JsonProperty("saleInfo")
    public SaleInfo getSaleInfo() {
        return this.saleInfo;
    }

    SaleInfo saleInfo;

    @JsonProperty("accessInfo")
    public AccessInfo getAccessInfo() {
        return this.accessInfo;
    }

    AccessInfo accessInfo;

    @JsonProperty("searchInfo")
    public SearchInfo getSearchInfo() {
        return this.searchInfo;
    }

    SearchInfo searchInfo;
}
