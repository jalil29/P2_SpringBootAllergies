package dev.springallergies.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="potlucks")
public class Potlukk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="potlucks")
    private int pid;

    @Column(name="timeofevent")
    private BigInteger time;

    @Column(name="creatorid")
    private int creatorid;

    @Override
    public String toString() {
        return "Potlukk{" +
                "pid=" + pid +
                ", time=" + time +
                ", creatorid=" + creatorid +
                '}';
    }
}
