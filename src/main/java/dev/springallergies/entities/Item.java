package dev.springallergies.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemid")
    private int itemId;

    @Column(name="status")
    private String status;

    @Column(name="description")
    private String description;

    @Column(name="supplier")
    private String supplier;

    @Column(name="pid")
    private int pid;

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", supplier='" + supplier + '\'' +
                ", pid=" + pid +
                '}';
    }
}
