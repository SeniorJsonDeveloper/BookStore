package dn.bookstore.dto.google;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ReadingModes {
    @JsonProperty("text")
    public boolean getText() {
        return this.text;
    }

    boolean text;

    @JsonProperty("image")
    public boolean getImage() {
        return this.image;
    }

    boolean image;
}
