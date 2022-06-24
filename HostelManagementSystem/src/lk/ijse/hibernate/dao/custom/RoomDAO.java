package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.User;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    List<Room> getRoomDetailUsingId(String id) throws Exception;
}
