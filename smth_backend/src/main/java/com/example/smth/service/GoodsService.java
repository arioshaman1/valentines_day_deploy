package com.example.smth.service;


import com.example.smth.entities.GoodsEntity;
import com.example.smth.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }
    public GoodsEntity addGoods(GoodsEntity goodsEntity) {
        return goodsRepository.save(goodsEntity);
    }
    public List<GoodsEntity> getAllGoods() {
        return goodsRepository.findAll();
    }
    public GoodsEntity getGoodsById(Long id) {
        return goodsRepository.findById(id).orElse(null);
    }
    public GoodsEntity updateGoods(GoodsEntity goodsEntity) {
        return goodsRepository.save(goodsEntity);
    }
    public void deleteGoods(Long id) {
        goodsRepository.deleteById(id);
    }


}
