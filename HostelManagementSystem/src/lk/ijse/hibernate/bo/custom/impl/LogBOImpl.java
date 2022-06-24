package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.LogBO;
import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.dao.custom.impl.UserDAOImpl;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.entity.User;

import java.util.ArrayList;
import java.util.List;

public class LogBOImpl implements LogBO {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<UserDTO> getUserDetails(String userName, String pwd) throws Exception {

        List<User> list = userDAO.getUserDetails(userName, pwd);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:list
        ) {
            userDTOS.add(new UserDTO(user.getUserId(),user.getName(),user.getTelNo(),user.getEmail(),user.getUserName(),user.getPassword()));
        }
        return userDTOS;

    }
}
