package java.com.kidsqueue.kidsqueue.domain.Parent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블 생성
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String login_id;

    @Column(nullable = false)
    private String password;
    private String nickname;
    private String name;
    private String age;
    private String gender;
    private String phone_number;
    private String resident_registration_number;
    private String profile_image_url;

    private LocalDateTime create_by;
    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.create_by = LocalDateTime.now();
    }
}

