package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Parent.
 */
@Entity
@Table(name = "parent")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Parent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("students")
    private Student student;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Feature> parents = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Notification> parents = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Task> parents = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Permission> parents = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Conversation> parents = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public Parent student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<Feature> getParents() {
        return parents;
    }

    public Parent parents(Set<Feature> features) {
        this.parents = features;
        return this;
    }

    public Parent addParent(Feature feature) {
        this.parents.add(feature);
        feature.setParent(this);
        return this;
    }

    public Parent removeParent(Feature feature) {
        this.parents.remove(feature);
        feature.setParent(null);
        return this;
    }

    public void setParents(Set<Feature> features) {
        this.parents = features;
    }

    public Set<Notification> getParents() {
        return parents;
    }

    public Parent parents(Set<Notification> notifications) {
        this.parents = notifications;
        return this;
    }

    public Parent addParent(Notification notification) {
        this.parents.add(notification);
        notification.setParent(this);
        return this;
    }

    public Parent removeParent(Notification notification) {
        this.parents.remove(notification);
        notification.setParent(null);
        return this;
    }

    public void setParents(Set<Notification> notifications) {
        this.parents = notifications;
    }

    public Set<Task> getParents() {
        return parents;
    }

    public Parent parents(Set<Task> tasks) {
        this.parents = tasks;
        return this;
    }

    public Parent addParent(Task task) {
        this.parents.add(task);
        task.setParent(this);
        return this;
    }

    public Parent removeParent(Task task) {
        this.parents.remove(task);
        task.setParent(null);
        return this;
    }

    public void setParents(Set<Task> tasks) {
        this.parents = tasks;
    }

    public Set<Permission> getParents() {
        return parents;
    }

    public Parent parents(Set<Permission> permissions) {
        this.parents = permissions;
        return this;
    }

    public Parent addParent(Permission permission) {
        this.parents.add(permission);
        permission.setParent(this);
        return this;
    }

    public Parent removeParent(Permission permission) {
        this.parents.remove(permission);
        permission.setParent(null);
        return this;
    }

    public void setParents(Set<Permission> permissions) {
        this.parents = permissions;
    }

    public Set<Conversation> getParents() {
        return parents;
    }

    public Parent parents(Set<Conversation> conversations) {
        this.parents = conversations;
        return this;
    }

    public Parent addParent(Conversation conversation) {
        this.parents.add(conversation);
        conversation.setParent(this);
        return this;
    }

    public Parent removeParent(Conversation conversation) {
        this.parents.remove(conversation);
        conversation.setParent(null);
        return this;
    }

    public void setParents(Set<Conversation> conversations) {
        this.parents = conversations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Parent parent = (Parent) o;
        if (parent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), parent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Parent{" +
            "id=" + getId() +
            "}";
    }
}
