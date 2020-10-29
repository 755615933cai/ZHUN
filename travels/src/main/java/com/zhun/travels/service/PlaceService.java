package com.zhun.travels.service;

import com.zhun.travels.entity.Place;

import java.util.List;

/**
 * @author zhun
 * @time 2020/10/23 - 16:34
 * @function
 */
public interface PlaceService {
    //根据Id获取地点
    List<Place> findByProId(String provinceId, Integer page,  Integer rows);

    //获取经典数量
    Integer findByProIdCounts(String provinceId);

    void save(Place place);

    void delete(String id);

    Place findOne(String id);

    void update(Place place);
}
