package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.RegistrationDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Reservation reservation) throws Exception {
        return false;
    }

    @Override
    public boolean update(Reservation reservation) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Reservation> getAll() throws IOException {
        return null;
    }

    @Override
    public String generateId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List resultList = session.createSQLQuery("SELECT * FROM Reservation ORDER BY res_id DESC LIMIT 1").addEntity(Reservation.class).getResultList();
        transaction.commit();
        session.close();

        return resultList.size()==0?"RS000":((Reservation)resultList.get(0)).getRes_id();
    }

    @Override
    public boolean updateUsingId(String id, String status) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Reservation SET status = :s WHERE res_id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("s",status );
        query.setParameter("id", id);

        int i = query.executeUpdate();

        transaction.commit();
        session.close();
        return i>0;
    }
}
