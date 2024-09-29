package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ImageLinks {
    @JsonProperty("smallThumbnail")
    public String getSmallThumbnail() {
        return this.smallThumbnail;
    }

    String smallThumbnail;

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return this.thumbnail;
    }

    String thumbnail;
}
