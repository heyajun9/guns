package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.WmsInterfaceLog;
import cn.stylefeng.guns.modular.servlet.mapper.WmsInterfaceLogMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class WmsInterfaceLogService extends ServiceImpl<WmsInterfaceLogMapper, WmsInterfaceLog> {

    /**
     * 获取操作日志列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    public List<Map<String, Object>> getInterfaceLogs(Page page, String beginTime, String endTime, String logName, String s,String succeed) {
        return this.baseMapper.getInterfaceLogs(page, beginTime, endTime,logName, s,succeed);
    }
    /**
     * 根据状态查找对象
     */
    public List<WmsInterfaceLog> getWmsInterfaceLogList(String type){
        return this.baseMapper.getWmsInterfaceLogList(type);
    }

}
