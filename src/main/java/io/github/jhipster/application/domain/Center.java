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
 * A Center.
 */
@Entity
@Table(name = "center")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Center implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("orgs")
    private Organization organization;

    @OneToMany(mappedBy = "center")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Room> centers = new HashSet<>();

    @OneToMany(mappedBy = "center")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Teacher> centers = new HashSet<>();

    @OneToMany(mappedBy = "center")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Student> centers = new HashSet<>();

    @OneToMany(mappedBy = "center")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Holiday> centers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Center name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Center organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<Room> getCenters() {
        return centers;
    }

    public Center centers(Set<Room> rooms) {
        this.centers = rooms;
        return this;
    }

    public Center addCenter(Room room) {
        this.centers.add(room);
        room.setCenter(this);
        return this;
    }

    public Center removeCenter(Room room) {
        this.centers.remove(room);
        room.setCenter(null);
        return this;
    }

    public void setCenters(Set<Room> rooms) {
        this.centers = rooms;
    }

    public Set<Teacher> getCenters() {
        return centers;
    }

    public Center centers(Set<Teacher> teachers) {
        this.centers = teachers;
        return this;
    }

    public Center addCenter(Teacher teacher) {
        this.centers.add(teacher);
        teacher.setCenter(this);
        return this;
    }

    public Center removeCenter(Teacher teacher) {
        this.centers.remove(teacher);
        teacher.setCenter(null);
        return this;
    }

    public void setCenters(Set<Teacher> teachers) {
        this.centers = teachers;
    }

    public Set<Student> getCenters() {
        return centers;
    }

    public Center centers(Set<Student> students) {
        this.centers = students;
        return this;
    }

    public Center addCenter(Student student) {
        this.centers.add(student);
        student.setCenter(this);
        return this;
    }

    public Center removeCenter(Student student) {
        this.centers.remove(student);
        student.setCenter(null);
        return this;
    }

    public void setCenters(Set<Student> students) {
        this.centers = students;
    }

    public Set<Holiday> getCenters() {
        return centers;
    }

    public Center centers(Set<Holiday> holidays) {
        this.centers = holidays;
        return this;
    }

    public Center addCenter(Holiday holiday) {
        this.centers.add(holiday);
        holiday.setCenter(this);
        return this;
    }

    public Center removeCenter(Holiday holiday) {
        this.centers.remove(holiday);
        holiday.setCenter(null);
        return this;
    }

    public void setCenters(Set<Holiday> holidays) {
        this.centers = holidays;
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
        Center center = (Center) o;
        if (center.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), center.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Center{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
