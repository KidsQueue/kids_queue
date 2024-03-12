package com.kidsqueue.kidsqueue.reservation.entity;


import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
    private Timestamp time;
    private Timestamp createBy;
    private Timestamp updatedBy;
    private Boolean is_active;

    @PrePersist
    public void createDate() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        createBy = currentTimestamp;
        updatedBy = currentTimestamp;
    }

    @PreUpdate
    public void updateDate() {
        updatedBy = new Timestamp(System.currentTimeMillis());
    }

}
