package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    private final RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public boolean saveRoom(RoomDTO dto) throws Exception {
        return roomDAO.save(new Room(dto.getRoom_type_id(),dto.getType(),dto.getKey_money(),dto.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws Exception {
        return roomDAO.update(new Room(dto.getRoom_type_id(),dto.getType(),dto.getKey_money(),dto.getQty()));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }

    @Override
    public List<RoomDTO> getAllRooms() throws IOException {

        List<Room> all = roomDAO.getAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room:all
             ) {
            roomDTOS.add(
                    new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty())
            );
        }
        return roomDTOS;
    }
}
