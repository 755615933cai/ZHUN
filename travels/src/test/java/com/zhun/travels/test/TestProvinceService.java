package com.zhun.travels.test;

import com.zhun.travels.TravelsApplication;
import com.zhun.travels.entity.Province;
import com.zhun.travels.service.ProvinceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhun
 * @time 2020/10/16 - 17:23
 * @function
 */
@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestProvinceService {

    @Resource
    private ProvinceService provinceServiceImpl;

    @Test
    public void testSave(){
        List<Province> list = provinceServiceImpl.findByPage(1, 5);
        list.forEach(province -> {
            System.out.println(province);
        });

    }

}
