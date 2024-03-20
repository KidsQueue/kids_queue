package com.kidsqueue.kidsqueue.reservation.entity;


import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EnableJpaAuditing
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
    private Timestamp createdBy;
    private Timestamp updatedBy;
    private Boolean isActive;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createdBy = Timestamp.valueOf(LocalDateTime.now());
        this.updatedBy = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate // DB에 UPDATE 되기 직전에 실행
    public void updateDate() {
        this.updatedBy = Timestamp.valueOf(LocalDateTime.now());
    }


    public void updateReservation(Timestamp time) {
        this.time = time;
    }

    public void disabled() {
        isActive = false;
    }

}
