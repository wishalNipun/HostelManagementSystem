package lk.ijse.hibernate.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private String userId;
    private String name;
    private String telNo;
    private String email;
    private String userName;
    private String password;
}
