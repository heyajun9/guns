package cn.stylefeng.guns.modular.servlet.mapper;

import cn.stylefeng.guns.modular.servlet.entity.InsertMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Admin
 * @create 2019-08-25 11:06
 * @desc 基础实体类的插入
 **/
public interface InsertMapMapper extends BaseMapper<InsertMap> {

    /**
     * 插入实体
     */
    void insertInfo(InsertMap insertMap);
    /**
     * 更新实体
     */
    void updateInfo(InsertMap insertMap);

    List<String> findFieldByTableName(@Param("tableName") String tableName);

}
