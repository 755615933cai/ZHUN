package com.zhun.travels.service.impl;

import com.zhun.travels.entity.Place;
import com.zhun.travels.entity.Province;
import com.zhun.travels.mapper.PlaceMapper;
import com.zhun.travels.service.PlaceService;
import com.zhun.travels.service.ProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhun
 * @time 2020/10/23 - 16:35
 * @function
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {


    @Resource
    private PlaceMapper placeMapper;

    @Resource
    private ProvinceService provinceServiceImpl;

    @Override
    public void save(Place place) {

        placeMapper.save(place);
        //查询所在省份的信息
        Province province = provinceServiceImpl.findOne(place.getProvinceid());
        //更新景点个数
        provinceServiceImpl.update(province.setPlacecounts(province.getPlacecounts()+1));
    }
    @Override
    public List<Place> findByProId(String provinceId, Integer page, Integer rows) {
        Integer start=(page-1)*rows;
        return placeMapper.findByProId(provinceId,start,rows);
    }

    @Override
    public void delete(String id) {
        Place place = placeMapper.findOne(id);
        //查询所在省份的信息
        Province province = provinceServiceImpl.findOne(place.getProvinceid());
        //更新景点个数
        provinceServiceImpl.update(province.setPlacecounts(province.getPlacecounts()-1));
        placeMapper.delete(id);
    }

    @Override
    public Integer findByProIdCounts(String provinceId) {
        return placeMapper.findByProIdCounts(provinceId);
    }

    @Override
    public Place findOne(String id) {
        return placeMapper.findOne(id);
    }

    @Override
    public void update(Place place) {
        placeMapper.update(place);
    }
}
