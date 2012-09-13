package com.thoughtworks.twu.domain;


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
        this.owner = owner.toLowerCase();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Presentation)) return false;

        Presentation that = (Presentation) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
