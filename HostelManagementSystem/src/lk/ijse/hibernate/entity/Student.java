package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Student {
    @Id
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;

    public Student(String student_id, String name, String address, String contact_no, LocalDate dob, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.dob = dob;
        this.gender = gender;
    }

    private String gender;



    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Reservation> reservationList=new ArrayList<>();

}
