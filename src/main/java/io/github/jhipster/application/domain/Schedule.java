package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Schedule.
 */
@Entity
@Table(name = "schedule")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "events")
    private Long events;

    @Column(name = "activities")
    private Long activities;

    @OneToMany(mappedBy = "schedule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Room> schedules = new HashSet<>();

    @OneToMany(mappedBy = "schedule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "schedule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Activity> activities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvents() {
        return events;
    }

    public Schedule events(Long events) {
        this.events = events;
        return this;
    }

    public void setEvents(Long events) {
        this.events = events;
    }

    public Long getActivities() {
        return activities;
    }

    public Schedule activities(Long activities) {
        this.activities = activities;
        return this;
    }

    public void setActivities(Long activities) {
        this.activities = activities;
    }

    public Set<Room> getSchedules() {
        return schedules;
    }

    public Schedule schedules(Set<Room> rooms) {
        this.schedules = rooms;
        return this;
    }

    public Schedule addSchedule(Room room) {
        this.schedules.add(room);
        room.setSchedule(this);
        return this;
    }

    public Schedule removeSchedule(Room room) {
        this.schedules.remove(room);
        room.setSchedule(null);
        return this;
    }

    public void setSchedules(Set<Room> rooms) {
        this.schedules = rooms;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Schedule events(Set<Event> events) {
        this.events = events;
        return this;
    }

    public Schedule addEvents(Event event) {
        this.events.add(event);
        event.setSchedule(this);
        return this;
    }

    public Schedule removeEvents(Event event) {
        this.events.remove(event);
        event.setSchedule(null);
        return this;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public Schedule activities(Set<Activity> activities) {
        this.activities = activities;
        return this;
    }

    public Schedule addActivities(Activity activity) {
        this.activities.add(activity);
        activity.setSchedule(this);
        return this;
    }

    public Schedule removeActivities(Activity activity) {
        this.activities.remove(activity);
        activity.setSchedule(null);
        return this;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
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
        Schedule schedule = (Schedule) o;
        if (schedule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), schedule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Schedule{" +
            "id=" + getId() +
            ", events=" + getEvents() +
            ", activities=" + getActivities() +
            "}";
    }
}
