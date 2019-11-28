package cn.stylefeng.guns.modular.servlet.mapper;

import cn.stylefeng.guns.modular.servlet.entity.WmsInterfaceLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
public interface WmsInterfaceLogMapper extends BaseMapper<WmsInterfaceLog> {

    /**
     * 获取操作日志
     */
    List<Map<String, Object>> getInterfaceLogs(@Param("page") Page page, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("logName") String logName, @Param("logType") String logType,@Param("status") String status);

    /**
     * 根据状态查找对象
     */
    List<WmsInterfaceLog> getWmsInterfaceLogList(@Param("type") String type);
}
