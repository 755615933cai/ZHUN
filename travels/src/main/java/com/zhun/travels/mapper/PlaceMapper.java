package com.zhun.travels.mapper;

import com.zhun.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhun
 * @time 2020/10/23 - 16:12
 * @function
 */
@Mapper
public interface PlaceMapper extends BaseMapper<Place,String>{
    //根据Id获取地点
    List<Place> findByProId(String provinceId, @Param("start") Integer start, @Param("rows") Integer rows);

    //获取经典数量
    Integer findByProIdCounts(String provinceId);
}
