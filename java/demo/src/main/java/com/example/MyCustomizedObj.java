package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by zzt on 5/13/17.
 * <p>
 * <h3></h3>
 */
public class MyCustomizedObj {

    @JsonProperty(value = "prop-1")
    private int prop1;
    @JsonProperty(value = "prop-2")
    private String prop2;
    @JsonProperty(value = "prop-3")
    private String prop3;

    public int getProp1() {
        return prop1;
    }

    public void setProp1(int prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }
}
