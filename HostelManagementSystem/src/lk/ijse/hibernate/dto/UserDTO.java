package lk.ijse.hibernate.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private String userId;
    private String name;
    private String telNo;
    private String email;
    private String userName;
    private String password;
}
