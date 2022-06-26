package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.PendingKeyMoneyBO;
import lk.ijse.hibernate.dao.custom.QueryDAO;
import lk.ijse.hibernate.dao.custom.RegistrationDAO;
import lk.ijse.hibernate.dao.custom.impl.QueryDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.hibernate.dto.CustomDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PendingKeyMoneyBOImpl implements PendingKeyMoneyBO {
    final private RegistrationDAO registrationDAO = new RegistrationDAOImpl();
    final private  QueryDAO queryDAO = new QueryDAOImpl();
    @Override
    public ArrayList<CustomDTO> getAllPendingKeyMoneyReservationsUsingReservationStatus() throws Exception {
        List<Object[]> objects = queryDAO.getAllPendingKeyMoneyReservationsUsingReservationStatus();
        ArrayList<CustomDTO> arrayList = new ArrayList<>();

        for (Object[] o:objects
             ) {
            arrayList.add(new CustomDTO((String) o[0],(String) o[1],(String) o[2],(String) o[3],(String) o[4],(double) o[5],(String) o[6],(LocalDate) o[7]));
        }

        return arrayList;


    }

    @Override
    public boolean updateReservationUsingId(String id, String status) throws Exception {
        return registrationDAO.updateUsingId(id,status);
    }
}
