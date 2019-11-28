package cn.stylefeng.guns.modular.interfaces.mapper;

import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
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
public interface TableMapper extends BaseMapper<TableOption> {
    /**
     * 获取所有列表
     */
    Page<Map<String, Object>> selectTable(@Param("page") Page page, @Param("upName") String upName, @Param("downName") String downName);

    /**
     * 通过表名获取对象
     */
    Page<Map<String, Object>> selectByTable(@Param("page") Page page, @Param("tableName") String tableName);

    /**
     * 根据InterId获取tableOption对象
     */
    List<TableOption> selectTableOption(@Param("interId") Long interId);
    /**
     * 根据表名获取tableOption对象
     */
    List<TableOption> selectTableOptionByTable(@Param("tableName") String tableName);

}
