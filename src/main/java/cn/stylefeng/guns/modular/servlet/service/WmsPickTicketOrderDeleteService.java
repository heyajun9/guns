package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.entity.WmsPickTicketOrderDelete;
import cn.stylefeng.guns.modular.servlet.mapper.WmsPickTicketOrderDeleteMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author heyajun
 * @since 2018-12-07
 */
@Service
public class WmsPickTicketOrderDeleteService extends ServiceImpl<WmsPickTicketOrderDeleteMapper, WmsPickTicketOrderDelete> {

    @Resource
    private WmsPickTicketOrderDeleteMapper WmsPickTicketOrderDeleteMapper;

    /**
     * 插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsPickTicketOrderDelete(WmsPickTicketOrderDelete WmsPickTicketOrderDelete) {

     this.save(WmsPickTicketOrderDelete);
    }

    /**
     * 批量插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsPickTicketOrderDeleteList(Collection<WmsPickTicketOrderDelete> WmsPickTicketOrderDeleteCollection,int batchSize) throws Exception {

        this.saveBatch(WmsPickTicketOrderDeleteCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsPickTicketOrderDelete(WmsPickTicketOrderDelete WmsPickTicketOrderDelete) {

        //如果物料为空
        if (WmsPickTicketOrderDelete == null || ToolUtil.isOneEmpty(WmsPickTicketOrderDelete.getDanj_no_y(),WmsPickTicketOrderDelete.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(WmsPickTicketOrderDelete);
    }
    /**
     * 批量更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsPickTicketOrderDeleteList(Collection<WmsPickTicketOrderDelete> WmsPickTicketOrderDeletes,int batchSize) {

        this.updateBatchById(WmsPickTicketOrderDeletes,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsPickTicketOrderDelete> WmsPickTicketOrderDeletes,int batchSize) {

        this.saveOrUpdateBatch(WmsPickTicketOrderDeletes,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsPickTicketOrderDelete(Long WmsPickTicketOrderDeleteId) {

        //删除菜单
        this.WmsPickTicketOrderDeleteMapper.deleteById(WmsPickTicketOrderDeleteId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsPickTicketOrderDelete(String orderCode,String itemCode) {

        Page page = LayuiPageFactory.defaultPage();

        return this.WmsPickTicketOrderDeleteMapper.selectWmsPickTicketOrderDelete(page, orderCode,itemCode);
    }



}
