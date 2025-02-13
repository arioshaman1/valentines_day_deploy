package com.example.smth.entities;


import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String client;
    @Column
    private String address;
    @Column
    private String date;
}
