package com.zhun.travels.controller;

import com.zhun.travels.entity.Place;
import com.zhun.travels.entity.Result;
import com.zhun.travels.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhun
 * @time 2020/10/23 - 16:42
 * @function
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("place")
public class PlaceController {

    @Resource
    private PlaceService placeServiceImpl;

    @Value("${upload.dir}")
    private String realPath;

    /**
     * 更新
     *
     * @param place
     * @return
     */
    @PostMapping("update")
    public Result update(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();
        try {
            if (place.getPicpath() == null) {
                //base64编码处理
                place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
                //文件上传
                String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
                String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
                //
                pic.transferTo(new File(realPath, newFileName));
            }
            placeServiceImpl.update(place);
            result.setMsg("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("修改失败!").setState(false);
        }
        return result;
    }

    /**
     * 根据ID查询景点信息
     *
     * @param id
     * @return
     */
    @GetMapping("findOnePlace")
    public Place findOnePlace(String id) {
        return placeServiceImpl.findOne(id);
    }

    /**
     * 删除景点信息
     *
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id) {
        Result result = new Result();
        try {
            placeServiceImpl.delete(id);
            result.setMsg("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("删除失败！").setState(false);
        }
        return result;
    }

    /**
     * 保存景点信息
     *
     * @param pic
     * @param place
     * @return
     * @throws IOException
     */
    @PostMapping("save")
    public Result save(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();
        try {
            //base64编码处理
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            //文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            //
            File file = new File(realPath);
            pic.transferTo(new File(file, newFileName));

            //保存place对象
            placeServiceImpl.save(place);
            result.setMsg("添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("添加失败！").setState(false);
        }
        return result;
    }

    /**
     * 查询景点
     *
     * @param provinceId
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findAllPlace")
    public Map<String, Object> findAllPlace(String provinceId, Integer page, Integer rows) {
        //页面初始化
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;

        Map<String, Object> result = new HashMap<>();
        List<Place> places = placeServiceImpl.findByProId(provinceId, page, rows);
        Integer counts = placeServiceImpl.findByProIdCounts(provinceId);
        //计算总页数
        Integer totalPage = (int) Math.ceil((double) counts / rows);

        result.put("page", page);
        result.put("places", places);
        result.put("totalPage", totalPage);
        result.put("counts", counts);
        return result;
    }
}
