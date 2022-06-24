package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface StudentBO {
    boolean saveStudent(StudentDTO dto) throws Exception;

    boolean updateStudent(StudentDTO dto) throws Exception;

    boolean deleteStudent(String id) throws Exception;

    String generateStudentId()throws Exception;

    List<StudentDTO> getAllStudents() throws IOException;
}
