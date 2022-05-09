package dev.group2.proj2.entities;


import lombok.Data;

import javax.persistence.*;

@Data
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
