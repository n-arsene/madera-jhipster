package com.madera.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Frame.
 */
@Entity
@Table(name = "frame")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Frame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "frame")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Assortment> assortments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Frame name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Frame url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Assortment> getAssortments() {
        return assortments;
    }

    public Frame assortments(Set<Assortment> assortments) {
        this.assortments = assortments;
        return this;
    }

    public Frame addAssortment(Assortment assortment) {
        assortments.add(assortment);
        assortment.setFrame(this);
        return this;
    }

    public Frame removeAssortment(Assortment assortment) {
        assortments.remove(assortment);
        assortment.setFrame(null);
        return this;
    }

    public void setAssortments(Set<Assortment> assortments) {
        this.assortments = assortments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        if(frame.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, frame.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Frame{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", url='" + url + "'" +
            '}';
    }
}
