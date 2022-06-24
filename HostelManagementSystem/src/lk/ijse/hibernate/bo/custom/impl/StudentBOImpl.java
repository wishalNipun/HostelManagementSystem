package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {

        return studentDAO.save(new Student(dto.getStudent_id(),dto.getName(),dto.getAddress(),dto.getContact_no(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(dto.getStudent_id(),dto.getName(),dto.getAddress(),dto.getContact_no(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public String generateStudentId() throws Exception {
        String id = studentDAO.generateId();
        return id;
    }

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
}
