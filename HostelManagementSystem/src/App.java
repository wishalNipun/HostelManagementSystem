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
        Query query = session.createQuery("SELECT r.res_id,s.student_id,s.name,ro.room_type_id,ro.type,ro.key_money,r.status,r.date FROM Reservation r JOIN Student s ON r.student = s.student_id JOIN Room ro ON r.room =ro.room_type_id WHERE r.status= : y");

        List<Object[]> list = query.setParameter("y", "Paid Later").list();

        for (Object [] o:list
             ) {
            System.out.println(o[0]+" "+o[1]+" "+o[2]+" "+o[3]+" "+o[4]+" "+o[5]+" "+o[6]+" "+o[7]);

        }



        transaction.commit();
        session.close();
    }
}
