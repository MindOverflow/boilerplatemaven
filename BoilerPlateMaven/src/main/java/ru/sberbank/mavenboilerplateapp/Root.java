package ru.sberbank.mavenboilerplateapp;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Root {
    private Catalog catalog;

    @JsonProperty("catalog")
    @JsonGetter("catalog")
    public Catalog getCatalog() {
        return catalog;
    }

    @JsonProperty("catalog")
    @JsonSetter("catalog")
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}