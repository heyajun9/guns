package cn.stylefeng.guns.modular.interfaces.mapper;

import cn.stylefeng.guns.modular.interfaces.entity.Inter;
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
public interface InterMapper extends BaseMapper<Inter> {
    /**
     * 获取所有列表
     */
    Page<Map<String, Object>> selectInter(@Param("page") Page page, @Param("condition") String condition,@Param("interfaceType") String interfaceType,@Param("downTable") String downTable);

    /**
     * 修改状态
     */
    int setStatus(@Param("interId") Long interId, @Param("status") String status);

    /**
     * 通过类型获取对象
     */
    List<Inter> selectInterByType(@Param("type") String type);

    /**
     * 设置对应关系
     */
    int setOptions(@Param("interId") Long interId, @Param("roleIds") String roleIds);

    /**
     * 通过名称获取接口配置
     */
    Inter getByName(@Param("name") String name);
}
