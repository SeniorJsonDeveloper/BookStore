package dn.bookstore.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.ArrayList;


@Setter
public class VolumeInfo {
        @JsonProperty("title")
        public String getTitle() {
            return this.title;
        }

    String title;

        @JsonProperty("authors")
        public ArrayList<String> getAuthors() {
            return this.authors;
        }

    ArrayList<String> authors;

        @JsonProperty("publisher")
        public String getPublisher() {
            return this.publisher;
        }

    String publisher;

        @JsonProperty("publishedDate")
        public String getPublishedDate() {
            return this.publishedDate;
        }

    String publishedDate;

        @JsonProperty("description")
        public String getDescription() {
            return this.description;
        }

    String description;

        @JsonProperty("industryIdentifiers")
        public ArrayList<IndustryIdentifier> getIndustryIdentifiers() {
            return this.industryIdentifiers;
        }

    ArrayList<IndustryIdentifier> industryIdentifiers;

        @JsonProperty("readingModes")
        public ReadingModes getReadingModes() {
            return this.readingModes;
        }

    ReadingModes readingModes;

        @JsonProperty("pageCount")
        public int getPageCount() {
            return this.pageCount;
        }

    int pageCount;

        @JsonProperty("printType")
        public String getPrintType() {
            return this.printType;
        }

    String printType;

        @JsonProperty("categories")
        public ArrayList<String> getCategories() {
            return this.categories;
        }

    ArrayList<String> categories;

        @JsonProperty("maturityRating")
        public String getMaturityRating() {
            return this.maturityRating;
        }

    String maturityRating;

        @JsonProperty("allowAnonLogging")
        public boolean getAllowAnonLogging() {
            return this.allowAnonLogging;
        }

    boolean allowAnonLogging;

        @JsonProperty("contentVersion")
        public String getContentVersion() {
            return this.contentVersion;
        }

    String contentVersion;

        @JsonProperty("panelizationSummary")
        public PanelizationSummary getPanelizationSummary() {
            return this.panelizationSummary;
        }

    PanelizationSummary panelizationSummary;

        @JsonProperty("imageLinks")
        public ImageLinks getImageLinks() {
            return this.imageLinks;
        }

    ImageLinks imageLinks;

        @JsonProperty("language")
        public String getLanguage() {
            return this.language;
        }

    String language;

        @JsonProperty("previewLink")
        public String getPreviewLink() {
            return this.previewLink;
        }

    String previewLink;

        @JsonProperty("infoLink")
        public String getInfoLink() {
            return this.infoLink;
        }

    String infoLink;

        @JsonProperty("canonicalVolumeLink")
        public String getCanonicalVolumeLink() {
            return this.canonicalVolumeLink;
        }

    String canonicalVolumeLink;
    }



