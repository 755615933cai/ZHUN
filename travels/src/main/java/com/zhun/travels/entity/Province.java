package com.zhun.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhun
 * @time 2020/10/19 - 14:40
 * @function
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Province {
    private String id;
    private String name;
    private String tags; //标签
    private Integer placecounts;  //景点数量
}
