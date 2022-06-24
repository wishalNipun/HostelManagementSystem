package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.User;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {
    List<Student> getStudentDetailsUsingId(String id) throws Exception;
}
