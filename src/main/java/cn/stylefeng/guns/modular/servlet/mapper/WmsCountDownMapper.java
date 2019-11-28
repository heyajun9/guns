package cn.stylefeng.guns.modular.servlet.mapper;

import cn.stylefeng.guns.modular.servlet.entity.WmsCountDown;
import cn.stylefeng.guns.modular.servlet.entity.WmsCountInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Admin
 * @create 2019-08-25 11:06
 * @desc 盘点配置
 **/
public interface WmsCountDownMapper extends BaseMapper<WmsCountDown> {
    /**
     * 获取所有列表
     * 根据订单号和物料编号查找
     */
    Page<Map<String, Object>> selectWmsCountDown(@Param("page") Page page, @Param("orderCode") String orderCode, @Param("itemCode") String itemCode);


    /**
     * 根据单据编号查找对象
     */
    List<WmsCountDown> selectWmsCountDownByCode(@Param("orderCode") String orderCode);

    /**
     * 根据列名和id获取到对象字段
     */
     Object selectObject(@Param("tableField") String tableField, @Param("orderCode") String orderCode, @Param("rowNum") String rowNum);

}
