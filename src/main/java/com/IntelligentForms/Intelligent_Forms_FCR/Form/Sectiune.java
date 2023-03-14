package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sectiune {
    String label;
    String placeholder;
    String mandatory;
    String type;

    public Sectiune(@JsonProperty("label") String label,
                    @JsonProperty("placeholder")String placeholder,
                    @JsonProperty("mandatory")String mandatory,
                    @JsonProperty("type")String type) {
        this.label = label;
        this.placeholder = placeholder;
        this.mandatory = mandatory;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getMandatory() {
        return mandatory;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{\"label\":\"" + label + "\"," +
                "\"placeholder\":\"" + placeholder + "\"," +
                "\"mandatory\":\"" + mandatory + "\"," +
                "\"type\":\"" + type + "\"}";
    }
}
