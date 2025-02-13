package com.example.smth.repositories;

import com.example.smth.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository <GoodsEntity, Long> {

}
