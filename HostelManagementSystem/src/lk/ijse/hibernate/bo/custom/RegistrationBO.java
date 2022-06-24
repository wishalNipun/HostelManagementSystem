package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface RegistrationBO {
    List<StudentDTO> getAllStudents() throws IOException;
    List<RoomDTO> getAllRooms() throws IOException;
    List<RoomDTO> getRoomDetailUsingId(String id) throws Exception;
    List<StudentDTO> getStudentDetailUsingId(String id) throws Exception;
    String generateRegistrationId()throws Exception;

}
