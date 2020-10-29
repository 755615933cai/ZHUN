package com.zhun.travels.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhun
 * @time 2020/10/19 - 14:47
 * @function 公共mapper接口
 */
public interface BaseMapper<T,K> {//  T:当前操作的类型     K：组件的类型

    void save (T t);

    void update (T t);

    void delete (K k);

    List<T> findAll ();
    //@Param ：可以使用传入不是 实体类 中的 参数
    List<T> findByPage(@Param("start") Integer start,@Param("rows") Integer rows);

    Integer findTotals();

    T findOne(K k);

}
