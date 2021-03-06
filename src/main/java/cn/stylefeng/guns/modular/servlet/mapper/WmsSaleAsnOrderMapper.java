package cn.stylefeng.guns.modular.servlet.mapper;

import cn.stylefeng.guns.modular.servlet.entity.WmsAsnOrder;
import cn.stylefeng.guns.modular.servlet.entity.WmsSaleAsnOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Admin
 * @create 2019-08-25 11:06
 * @desc 接口配置
 **/
public interface WmsSaleAsnOrderMapper extends BaseMapper<WmsSaleAsnOrder> {
    /**
     * 获取所有列表
     * 根据订单号和物料编号查找
     */
    Page<Map<String, Object>> selectWmsSaleAsnOrder(@Param("page") Page page, @Param("orderCode") String orderCode, @Param("itemCode") String itemCode);

}
