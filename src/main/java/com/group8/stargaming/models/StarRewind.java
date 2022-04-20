package com.group8.stargaming.models;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name = "Star_Details")
@Entity(name="Star_Details")
public class StarRewind {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ID;
    private String Name;
    private Date Date;
    private double GHA;
    private double DEC;
}
