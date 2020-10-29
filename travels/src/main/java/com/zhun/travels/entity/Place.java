package com.zhun.travels.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhun
 * @time 2020/10/23 - 15:56
 * @function
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private String id;
    private String name;
    private String picpath;//图片路径
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date hottime;//热门时间
    private Double hotticket;//旺季票价
    private Double dimticket;//淡季票价
    private String placedes;//描述
    private String provinceid;
}
