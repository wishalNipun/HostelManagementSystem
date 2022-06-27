package lk.ijse.hibernate.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.bo.custom.RegistrationBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.RegistrationDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import lk.ijse.hibernate.view.tm.CartTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);

    @Override
    public void Register(ObservableList<CartTM> list, String studentId, String lblId) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            ObservableList<CartTM> items = list;
            for (CartTM tm:items
            ) {
                List<Room> roomDetailUsingId = roomDAO.getRoomDetailUsingId(tm.getRoom_type_id());
                Room room = new Room();

                for (Room r:roomDetailUsingId
                ) {
                    room.setRoom_type_id(r.getRoom_type_id());
                    room.setType(r.getType());
                    room.setKey_money(r.getKey_money());
                    room.setQty(r.getQty()-1);
                }

                List<Student> studentDetailUsingId = studentDAO.getStudentDetailsUsingId(studentId);

                Student student = new Student();
                for (Student s:studentDetailUsingId
                ) {
                    student.setStudent_id(s.getStudent_id());
                    student.setName(s.getName());
                    student.setAddress(s.getAddress());
                    student.setContact_no(s.getContact_no());
                    student.setDob(s.getDob());
                    student.setGender(s.getGender());

                }

                Reservation r = new Reservation();
                r.setRes_id(lblId);
                r.setDate(LocalDate.now());
                r.setStatus(tm.getStatus());
                r.setRoom(room);
                r.setStudent(student);

                session.save(r);
                session.update(room);
                transaction.commit();

            }

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }


        session.close();
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
