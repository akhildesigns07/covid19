
package com.akhil.project.dto.covidHistory;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loc",
    "confirmedCasesIndian",
    "confirmedCasesForeign",
    "discharged",
    "deaths",
    "totalConfirmed"
})
public class Regional {

    @JsonProperty("loc")
    private String loc;
    @JsonProperty("confirmedCasesIndian")
    private Integer confirmedCasesIndian;
    @JsonProperty("confirmedCasesForeign")
    private Integer confirmedCasesForeign;
    @JsonProperty("discharged")
    private Integer discharged;
    @JsonProperty("deaths")
    private Integer deaths;
    @JsonProperty("totalConfirmed")
    private Integer totalConfirmed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("loc")
    public String getLoc() {
        return loc;
    }

    @JsonProperty("loc")
    public void setLoc(String loc) {
        this.loc = loc;
    }

    @JsonProperty("confirmedCasesIndian")
    public Integer getConfirmedCasesIndian() {
        return confirmedCasesIndian;
    }

    @JsonProperty("confirmedCasesIndian")
    public void setConfirmedCasesIndian(Integer confirmedCasesIndian) {
        this.confirmedCasesIndian = confirmedCasesIndian;
    }

    @JsonProperty("confirmedCasesForeign")
    public Integer getConfirmedCasesForeign() {
        return confirmedCasesForeign;
    }

    @JsonProperty("confirmedCasesForeign")
    public void setConfirmedCasesForeign(Integer confirmedCasesForeign) {
        this.confirmedCasesForeign = confirmedCasesForeign;
    }

    @JsonProperty("discharged")
    public Integer getDischarged() {
        return discharged;
    }

    @JsonProperty("discharged")
    public void setDischarged(Integer discharged) {
        this.discharged = discharged;
    }

    @JsonProperty("deaths")
    public Integer getDeaths() {
        return deaths;
    }

    @JsonProperty("deaths")
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    @JsonProperty("totalConfirmed")
    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    @JsonProperty("totalConfirmed")
    public void setTotalConfirmed(Integer totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
