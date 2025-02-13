package com.example.smth.entities;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private int price;
}
