package dn.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(schema = "bookstore_db",name = "sms_codes")
@Getter
@NoArgsConstructor
@Setter
public class SmsCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String code;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    public SmsCodeEntity(String code, Integer expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusMinutes(expireIn);
    }

    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }


}
