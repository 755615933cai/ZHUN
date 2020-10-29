package com.zhun.travels.service.impl;

import com.zhun.travels.entity.Province;
import com.zhun.travels.mapper.ProvinceMapper;
import com.zhun.travels.service.ProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhun
 * @time 2020/10/19 - 15:12
 * @function
 */
@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;
    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        return provinceMapper.findByPage(start,rows);
    }

    @Override
    public void save(Province province) {
        province.setPlacecounts(0);//景点个数
        provinceMapper.save(province);
    }

    @Override
    public Integer findTotals() {
        return provinceMapper.findTotals();
    }

    @Override
    public Province findOne(String id) {
        return provinceMapper.findOne(id);
    }

    @Override
    public void delete(String id) {
        provinceMapper.delete(id);
    }

    @Override
    public void update(Province province) {
        provinceMapper.update(province);
    }
}
