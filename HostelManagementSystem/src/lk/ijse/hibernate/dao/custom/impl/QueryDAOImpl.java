package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.QueryDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<Object[]> getAllPendingKeyMoneyReservationsUsingReservationStatus(String status) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT s.student_id,  ss.res_id,s.name,ss.status,ss.date FROM Student s INNER JOIN Reservation ss ON s.student_id = ss.student.student_id WHERE ss.status= : s");


        query.setParameter("s",status);

        List<Object[]> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
