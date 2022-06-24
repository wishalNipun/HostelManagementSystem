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

        Query query = session.createQuery("FROM Student WHERE student_id= :id ");
        query.setParameter("id", "S001");

        List<Student> list = query.list();

        for (Student s:list
             ) {
            System.out.println(s.getName()+" - "+s.getAddress());
        }

        transaction.commit();
        session.close();
    }
}
