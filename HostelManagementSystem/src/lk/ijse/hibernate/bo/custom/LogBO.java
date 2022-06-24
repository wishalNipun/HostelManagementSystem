package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.entity.User;

import java.util.List;

public interface LogBO extends SuperBO {
    List<UserDTO> getUserDetails(String userName, String pwd) throws Exception;
}
