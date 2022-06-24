package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {
    List<User> getUserDetails(String userName, String pwd) throws Exception;
}
