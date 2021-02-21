package pl.mvwojcik.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true)
    private Integer id;

    private boolean active;

    private Date createdDate;

    private Date updatedDate;

    @PrePersist
    protected void logDataBeforeInsert() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.setActive(true);
    }

    @PreUpdate
    protected void logDataBeforeUpdate() {
        this.updatedDate = new Date();
        this.setActive(true);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseModel baseModel = (BaseModel) o;
        return active == baseModel.active &&
                Objects.equals(id, baseModel.id) &&
                Objects.equals(createdDate, baseModel.createdDate) &&
                Objects.equals(updatedDate, baseModel.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}