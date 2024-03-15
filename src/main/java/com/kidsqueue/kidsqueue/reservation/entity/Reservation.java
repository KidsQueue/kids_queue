package com.kidsqueue.kidsqueue.reservation.entity;


import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
    @CreatedBy
    private Timestamp createBy;
    @LastModifiedDate
    private Timestamp updatedBy;
    private Boolean isActive;


    public void updateReservation(Timestamp time) {
        this.time = time;
    }

    public void disabled() {
        isActive = false;
    }

}
