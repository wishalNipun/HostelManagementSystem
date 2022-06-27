package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;

    public Room(String room_type_id, String type, double key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Reservation> reservationList=new ArrayList<>();



}
