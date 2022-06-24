package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws Exception;

    boolean updateUser(UserDTO dto) throws Exception;

    boolean deleteUser(String id) throws Exception;

    String generateUserId()throws Exception;

    List<UserDTO> getAllUsers() throws IOException;
}
