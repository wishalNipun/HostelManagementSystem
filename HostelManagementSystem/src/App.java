import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
/*

        Query query = session.createQuery("FROM Student WHERE student_id= :id ");
        query.setParameter("id", "S001");

        List<Student> list = query.list();

        for (Student s:list
             ) {
            System.out.println(s.getName()+" - "+s.getAddress());
        }
*/
        Query query = session.createQuery("SELECT s.student_id,  ss.res_id,s.name,ss.status,ss.date FROM Student s INNER JOIN Reservation ss ON s.student_id = ss.student.student_id WHERE ss.status= : y");


        query.setParameter("y","Paid Later");

        List<Object[]> list = query.list();
        for (Object[] objects:list
             ) {
            System.out.println(objects[0]+" "+objects[1]+" "+objects[2]+" "+objects[3]+" "+objects[4]);
        }


        transaction.commit();
        session.close();
    }
}
