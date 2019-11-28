package cn.stylefeng.guns.modular.servlet.mapper;

import cn.stylefeng.guns.modular.servlet.entity.QuartzTask;
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
public interface QuartzTaskMapper extends BaseMapper<QuartzTask> {
    /**
     * 获取所有列表
     */
    List<QuartzTask> selectTaskAll();

    /**
     * 根据条件查找
     * @param page
     * @param jobName
     * @param className
     * @return
     */
    Page<Map<String, Object>> selectTask(@Param("page") Page page, @Param("jobName") String jobName, @Param("className") String className);

}
