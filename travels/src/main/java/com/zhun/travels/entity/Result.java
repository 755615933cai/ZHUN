package com.zhun.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhun
 * @time 2020/10/16 - 17:37
 * @function  存储后台返回给前台的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//链式
public class Result {
    private Boolean state=true;
    private String msg;
    private String userId;
}
