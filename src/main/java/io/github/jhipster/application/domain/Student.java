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
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("centers")
    private Center center;

    @OneToOne
    @JoinColumn(unique = true)
    private StudentProfile id;

    @OneToOne
    @JoinColumn(unique = true)
    private Gallery id;

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImmunizationRecord> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<IllnessRecord> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Payment> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Kudos> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Milestone> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attendance> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Incident> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Timeline> students = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Parent> students = new HashSet<>();

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

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Center getCenter() {
        return center;
    }

    public Student center(Center center) {
        this.center = center;
        return this;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public StudentProfile getId() {
        return id;
    }

    public Student id(StudentProfile studentProfile) {
        this.id = studentProfile;
        return this;
    }

    public void setId(StudentProfile studentProfile) {
        this.id = studentProfile;
    }

    public Gallery getId() {
        return id;
    }

    public Student id(Gallery gallery) {
        this.id = gallery;
        return this;
    }

    public void setId(Gallery gallery) {
        this.id = gallery;
    }

    public Set<ImmunizationRecord> getStudents() {
        return students;
    }

    public Student students(Set<ImmunizationRecord> immunizationRecords) {
        this.students = immunizationRecords;
        return this;
    }

    public Student addStudent(ImmunizationRecord immunizationRecord) {
        this.students.add(immunizationRecord);
        immunizationRecord.setStudent(this);
        return this;
    }

    public Student removeStudent(ImmunizationRecord immunizationRecord) {
        this.students.remove(immunizationRecord);
        immunizationRecord.setStudent(null);
        return this;
    }

    public void setStudents(Set<ImmunizationRecord> immunizationRecords) {
        this.students = immunizationRecords;
    }

    public Set<IllnessRecord> getStudents() {
        return students;
    }

    public Student students(Set<IllnessRecord> illnessRecords) {
        this.students = illnessRecords;
        return this;
    }

    public Student addStudent(IllnessRecord illnessRecord) {
        this.students.add(illnessRecord);
        illnessRecord.setStudent(this);
        return this;
    }

    public Student removeStudent(IllnessRecord illnessRecord) {
        this.students.remove(illnessRecord);
        illnessRecord.setStudent(null);
        return this;
    }

    public void setStudents(Set<IllnessRecord> illnessRecords) {
        this.students = illnessRecords;
    }

    public Set<Payment> getStudents() {
        return students;
    }

    public Student students(Set<Payment> payments) {
        this.students = payments;
        return this;
    }

    public Student addStudent(Payment payment) {
        this.students.add(payment);
        payment.setStudent(this);
        return this;
    }

    public Student removeStudent(Payment payment) {
        this.students.remove(payment);
        payment.setStudent(null);
        return this;
    }

    public void setStudents(Set<Payment> payments) {
        this.students = payments;
    }

    public Set<Kudos> getStudents() {
        return students;
    }

    public Student students(Set<Kudos> kudos) {
        this.students = kudos;
        return this;
    }

    public Student addStudent(Kudos kudos) {
        this.students.add(kudos);
        kudos.setStudent(this);
        return this;
    }

    public Student removeStudent(Kudos kudos) {
        this.students.remove(kudos);
        kudos.setStudent(null);
        return this;
    }

    public void setStudents(Set<Kudos> kudos) {
        this.students = kudos;
    }

    public Set<Milestone> getStudents() {
        return students;
    }

    public Student students(Set<Milestone> milestones) {
        this.students = milestones;
        return this;
    }

    public Student addStudent(Milestone milestone) {
        this.students.add(milestone);
        milestone.setStudent(this);
        return this;
    }

    public Student removeStudent(Milestone milestone) {
        this.students.remove(milestone);
        milestone.setStudent(null);
        return this;
    }

    public void setStudents(Set<Milestone> milestones) {
        this.students = milestones;
    }

    public Set<Attendance> getStudents() {
        return students;
    }

    public Student students(Set<Attendance> attendances) {
        this.students = attendances;
        return this;
    }

    public Student addStudent(Attendance attendance) {
        this.students.add(attendance);
        attendance.setStudent(this);
        return this;
    }

    public Student removeStudent(Attendance attendance) {
        this.students.remove(attendance);
        attendance.setStudent(null);
        return this;
    }

    public void setStudents(Set<Attendance> attendances) {
        this.students = attendances;
    }

    public Set<Incident> getStudents() {
        return students;
    }

    public Student students(Set<Incident> incidents) {
        this.students = incidents;
        return this;
    }

    public Student addStudent(Incident incident) {
        this.students.add(incident);
        incident.setStudent(this);
        return this;
    }

    public Student removeStudent(Incident incident) {
        this.students.remove(incident);
        incident.setStudent(null);
        return this;
    }

    public void setStudents(Set<Incident> incidents) {
        this.students = incidents;
    }

    public Set<Timeline> getStudents() {
        return students;
    }

    public Student students(Set<Timeline> timelines) {
        this.students = timelines;
        return this;
    }

    public Student addStudent(Timeline timeline) {
        this.students.add(timeline);
        timeline.setStudent(this);
        return this;
    }

    public Student removeStudent(Timeline timeline) {
        this.students.remove(timeline);
        timeline.setStudent(null);
        return this;
    }

    public void setStudents(Set<Timeline> timelines) {
        this.students = timelines;
    }

    public Set<Parent> getStudents() {
        return students;
    }

    public Student students(Set<Parent> parents) {
        this.students = parents;
        return this;
    }

    public Student addStudent(Parent parent) {
        this.students.add(parent);
        parent.setStudent(this);
        return this;
    }

    public Student removeStudent(Parent parent) {
        this.students.remove(parent);
        parent.setStudent(null);
        return this;
    }

    public void setStudents(Set<Parent> parents) {
        this.students = parents;
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
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
