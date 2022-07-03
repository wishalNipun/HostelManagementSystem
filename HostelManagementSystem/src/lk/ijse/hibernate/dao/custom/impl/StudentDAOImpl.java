package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, id);
        session.delete(student);

        transaction.commit();
        session.close();


        return true;
    }

    @Override
    public String generateId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List resultList = session.createSQLQuery("SELECT * FROM Student ORDER BY student_id DESC LIMIT 1").addEntity(Student.class).getResultList();
        transaction.commit();
        session.close();

        return resultList.size()==0?"S000":((Student)resultList.get(0)).getStudent_id();
    }

    @Override
    public List<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Student");
        List<Student> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<Student> getStudentDetailsUsingId(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Student WHERE student_id= :id ");
        query.setParameter("id", id);

        List<Student> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
