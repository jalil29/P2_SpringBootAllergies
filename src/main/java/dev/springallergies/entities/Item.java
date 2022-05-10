package dev.springallergies.entities;




import javax.persistence.*;


@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemid")
    private int itemId;

    @Column(name="status")
    private int status;
}
