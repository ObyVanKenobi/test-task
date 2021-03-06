package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rete")
public class Rate {

    @Id
    @GeneratedValue
    private int id;

    @Column(name= "assessed_value")
    private BigDecimal value;

    @Column(name = "date")
    private LocalDate date;


    public Rate(BigDecimal value) {

        this.value = value;
        this.date = LocalDate.now();

    }
}
