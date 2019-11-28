package cn.stylefeng.guns.modular.servlet.mapper;

import cn.stylefeng.guns.modular.servlet.entity.WmsOrganization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Admin
 * @create 2019-08-25 11:06
 * @desc 接口配置
 **/
public interface WmsOrganizationMapper extends BaseMapper<WmsOrganization> {
    /**
     * 获取所有列表
     */
    Page<Map<String, Object>> selectOrganization(@Param("page") Page page, @Param("organizationName") String organizationName, @Param("organizationCode") String organizationCode);

}
