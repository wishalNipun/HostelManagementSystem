package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.CustomDTO;

import java.util.ArrayList;
import java.util.List;

public interface PendingKeyMoneyBO {
    ArrayList<CustomDTO> getAllPendingKeyMoneyReservationsUsingReservationStatus() throws Exception;
    boolean updateReservationUsingId(String id, String status) throws Exception;

    }
