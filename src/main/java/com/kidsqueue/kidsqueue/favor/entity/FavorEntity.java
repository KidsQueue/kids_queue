package com.kidsqueue.kidsqueue.favor.entity;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Parent parent;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	@Column(columnDefinition = "tinyInt")
	private Integer isActive;

	private LocalDateTime createdBy;

	private LocalDateTime updatedBy;




}
