package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.RegistrationBO;
import lk.ijse.hibernate.dao.custom.RegistrationDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {
    private final StudentDAO studentDAO = new StudentDAOImpl();
    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final RegistrationDAO registrationDAO = new RegistrationDAOImpl();

    @Override
    public List<StudentDTO> getAllStudents() throws IOException {

        List<Student> all = studentDAO.getAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student s:all
        ) {
            dtos.add(
                    new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact_no(),s.getDob(),s.getGender())
            );

        }
        return dtos;
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

    @Override
    public List<RoomDTO> getRoomDetailUsingId(String id) throws Exception {
        List<Room> list = roomDAO.getRoomDetailUsingId(id);
        List<RoomDTO> dtoList = new ArrayList<>();
        for (Room roomDTO:list
             ) {
            dtoList.add(
                   new RoomDTO(roomDTO.getRoom_type_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty())
            );
        }
        return dtoList;
    }

    @Override
    public List<StudentDTO> getStudentDetailUsingId(String id) throws Exception {
        List<Student> list = studentDAO.getStudentDetailsUsingId(id);
        List<StudentDTO> dtoList = new ArrayList<>();
        for (Student student:list
        ) {
            dtoList.add(
                  new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),student.getDob(),student.getGender())
            );
        }
        return dtoList;
    }

    @Override
    public String generateRegistrationId() throws Exception {
        String id = registrationDAO.generateId();
        return id;
    }
}
