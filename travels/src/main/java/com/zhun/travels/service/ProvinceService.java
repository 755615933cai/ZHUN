package com.zhun.travels.service;

import com.zhun.travels.entity.Province;

import java.util.List;

/**
 * @author zhun
 * @time 2020/10/19 - 15:06
 * @function
 */
public interface ProvinceService {

    //参数1：当前页是第几页（pageNumber）   参数2：每页显示记录数
    List<Province> findByPage(Integer page,Integer rows);

    //查询总条数
    Integer findTotals();

    //添加省份
    void save(Province province);

    //删除省份
    void delete(String id);

    //根据ID查询省份信息
    Province findOne(String id);

    //修改省份
    void update(Province province);
}
