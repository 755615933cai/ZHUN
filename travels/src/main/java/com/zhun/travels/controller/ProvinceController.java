package com.zhun.travels.controller;

import com.zhun.travels.entity.Province;
import com.zhun.travels.entity.Result;
import com.zhun.travels.service.ProvinceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhun
 * @time 2020/10/19 - 15:44
 * @function
 *
 *
 * 如下情况使用GET方法：客户端与服务端的交互像是一个提问(如查询操作、搜索操作、读操作)
 * 如下情况使用POST方法：
 *        1.交互是一个命令或订单(order)，比提问包含更多信息
 *        2.交互改变了服务器端的资源并被用户察觉，例如订阅某项服务
 *        3.用户需要对交互产生的结果负责
 */
@RestController
@CrossOrigin
@RequestMapping("province")
public class ProvinceController {

    @Resource
    ProvinceService provinceServiceImpl;

    /**
     * 修改省份信息
     * @param province
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceServiceImpl.update(province);
            result.setMsg("保存省份信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("修改省份信息失败！");
        }
        return result;
    }

    /**
     * 根据ID查询省份信息
     * @param id
     * @return
     */
    @GetMapping("findOne")
    public Province findOne(String id){
        return provinceServiceImpl.findOne(id);
    }

    /**
     * 删除省份信息
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try {
            provinceServiceImpl.delete(id);
            result.setMsg("删除省份信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("删除省份信息失败！").setState(false);
        }
        return result;
    }

    /**
     * 保存省份信息
     * @param province
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceServiceImpl.save(province);
            result.setMsg("保存省份信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("添加省份信息失败！");
        }
        return result;
    }

    /**
     * 查询所有
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findByPage")//什么时候用get，什么时候用post：
    public Map<String,Object> findByPage(Integer page,Integer rows){
        //页面初始化
        page=page==null?1:page;
        rows=rows==null?4:rows;
        Map<String,Object> map=new HashMap<>();
        //分页处理
        List<Province> list = provinceServiceImpl.findByPage(page, rows);
        //计算总页数
        Integer totals = provinceServiceImpl.findTotals();
        Integer totalPage=(int)Math.ceil((double)totals/rows);
        map.put("list",list);
        map.put("totals",totals);
        map.put("totalPage",totalPage);
        map.put("page",page);
        return map;
    }
}
