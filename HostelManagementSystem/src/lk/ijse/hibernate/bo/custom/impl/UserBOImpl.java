package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.UserBO;
import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.dao.custom.impl.UserDAOImpl;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        return userDAO.save(new User(dto.getUserId(),dto.getName(),dto.getTelNo(),dto.getEmail(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return userDAO.update(new User(dto.getUserId(),dto.getName(),dto.getTelNo(),dto.getEmail(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
         return userDAO.delete(id);

    }

    @Override
    public String generateUserId() throws Exception {

        String id= userDAO.generateId();


        return id;
    }

    @Override
    public List<UserDTO> getAllUsers() throws IOException {

        List<User> all = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:all
             ) {
            userDTOS.add(new UserDTO(user.getUserId(),user.getName(),user.getTelNo(),user.getEmail(),user.getUserName(),user.getPassword()));
        }
        return userDTOS;
    }
}
