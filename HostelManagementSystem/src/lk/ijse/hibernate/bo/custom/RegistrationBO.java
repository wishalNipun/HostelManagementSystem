package lk.ijse.hibernate.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.view.tm.CartTM;

import java.io.IOException;
import java.util.List;

public interface RegistrationBO {
    void Register(ObservableList<CartTM> list, String studentId, String lblId)throws Exception;
    List<StudentDTO> getAllStudents() throws IOException;
    List<RoomDTO> getAllRooms() throws IOException;
    List<RoomDTO> getRoomDetailUsingId(String id) throws Exception;
    List<StudentDTO> getStudentDetailUsingId(String id) throws Exception;
    String generateRegistrationId()throws Exception;

}
