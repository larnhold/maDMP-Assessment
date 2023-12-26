package org.arnhold.evaluation.openaire.model;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "response")
public class OpenAireResponseModel {
    private Long id;
    private String name;
    private String author;

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }
}


