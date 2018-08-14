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
 * A Activity.
 */
@Entity
@Table(name = "activity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("activities")
    private Schedule schedule;

    @OneToOne
    @JoinColumn(unique = true)
    private ActivityType activityId;

    @OneToMany(mappedBy = "activity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Photo> ids = new HashSet<>();

    @OneToMany(mappedBy = "activity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Video> ids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Activity title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Activity description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Activity schedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public ActivityType getActivityId() {
        return activityId;
    }

    public Activity activityId(ActivityType activityType) {
        this.activityId = activityType;
        return this;
    }

    public void setActivityId(ActivityType activityType) {
        this.activityId = activityType;
    }

    public Set<Photo> getIds() {
        return ids;
    }

    public Activity ids(Set<Photo> photos) {
        this.ids = photos;
        return this;
    }

    public Activity addId(Photo photo) {
        this.ids.add(photo);
        photo.setActivity(this);
        return this;
    }

    public Activity removeId(Photo photo) {
        this.ids.remove(photo);
        photo.setActivity(null);
        return this;
    }

    public void setIds(Set<Photo> photos) {
        this.ids = photos;
    }

    public Set<Video> getIds() {
        return ids;
    }

    public Activity ids(Set<Video> videos) {
        this.ids = videos;
        return this;
    }

    public Activity addId(Video video) {
        this.ids.add(video);
        video.setActivity(this);
        return this;
    }

    public Activity removeId(Video video) {
        this.ids.remove(video);
        video.setActivity(null);
        return this;
    }

    public void setIds(Set<Video> videos) {
        this.ids = videos;
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
        Activity activity = (Activity) o;
        if (activity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Activity{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
