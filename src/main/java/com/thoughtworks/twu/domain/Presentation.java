package com.thoughtworks.twu.domain;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Presentation {
    private String title;
    private String description;
    private String owner;
    private int id;

    public Presentation(){}

    public Presentation(String title, String description, String owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }
}
