package com.projects.covid19tracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LatestData {

    @Data
    public static class RegionData {
        @JsonIgnore
        @JsonProperty("region")
        private String region;

        @JsonIgnore
        @JsonProperty("totalInfected")
        private String totalInfected;

        @JsonIgnore
        @JsonProperty("recovered")
        private String recovered;

        @JsonIgnore
        @JsonProperty("deceased")
        private String deceased;

        @JsonIgnore
        @JsonProperty("totalCases")
        private String totalCases;
    }

    @JsonIgnore
    @JsonProperty("activeCases")
    private String activeCases;

    @JsonIgnore
    @JsonProperty("recovered")
    private String recovered;

    @JsonIgnore
    @JsonProperty("deaths")
    private String deaths;

    @JsonIgnore
    @JsonProperty("totalCases")
    private String totalCases;

    @JsonIgnore
    @JsonProperty("sourceUrl")
    private String sourceUrl;

    @JsonIgnore
    @JsonProperty("lastUpdatedAtApify")
    private String lastUpdatedAtApify;

    @JsonIgnore
    @JsonProperty("readMe")
    private String readMe;

    @JsonIgnore
    @JsonProperty("regionData")
    private List<RegionData> regionData;

}
