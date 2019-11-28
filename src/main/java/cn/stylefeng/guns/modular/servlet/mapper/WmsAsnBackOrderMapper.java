package cn.stylefeng.guns.modular.servlet.mapper;

import cn.stylefeng.guns.modular.servlet.entity.WmsAsnBackOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Admin
 * @create 2019-08-25 11:06
 * @desc 接口配置
 **/
public interface WmsAsnBackOrderMapper extends BaseMapper<WmsAsnBackOrder> {
    /**
     * 获取所有列表
     * 根据订单号和物料编号查找
     */
    Page<Map<String, Object>> selectWmsAsnBackOrder(@Param("page") Page page, @Param("orderCode") String orderCode, @Param("itemCode") String itemCode,@Param("zt") String zt );

    /**
     * 根据业务类型查找单据编号
     */
    List<String> selectOrderCode(@Param("types") String[] types);

    /**
     * 根据单据编号查找对象
     */
    List<WmsAsnBackOrder> selectWmsAsBackOrderByCode(@Param("orderCode") String orderCode);
    /**
     *根据单号查找List集合
     */
    List<Map<String,Object>> selectWmsAsnBackOrderList(@Param("orderCode") String orderCode);

    /**
     * 根据列名和id获取到对象字段
     */
     Object selectObject(@Param("tableField") String tableField,@Param("orderCode")String orderCode,@Param("rowNum")String rowNum);

}
