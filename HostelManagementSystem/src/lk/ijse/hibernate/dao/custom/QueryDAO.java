package lk.ijse.hibernate.dao.custom;


import java.util.List;

public interface QueryDAO {
    List<Object []> getAllPendingKeyMoneyReservationsUsingReservationStatus(String status) throws Exception;
}
