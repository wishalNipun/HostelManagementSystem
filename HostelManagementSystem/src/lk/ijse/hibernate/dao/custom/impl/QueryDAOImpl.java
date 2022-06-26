package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.QueryDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<Object[]> getAllPendingKeyMoneyReservationsUsingReservationStatus() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT r.res_id,s.student_id,s.name,ro.room_type_id,ro.type,ro.key_money,r.status,r.date FROM Reservation r JOIN Student s ON r.student = s.student_id JOIN Room ro ON r.room =ro.room_type_id WHERE r.status= : y");


        List<Object[]> list = query.setParameter("y", "Paid Later").list();

        transaction.commit();
        session.close();
        return list;
    }
}
