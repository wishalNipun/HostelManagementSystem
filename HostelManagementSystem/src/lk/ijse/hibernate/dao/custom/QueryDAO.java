package lk.ijse.hibernate.dao.custom;


import lk.ijse.hibernate.dao.SuperDAO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> getAllPendingKeyMoneyReservationsUsingReservationStatus() throws Exception;
}
