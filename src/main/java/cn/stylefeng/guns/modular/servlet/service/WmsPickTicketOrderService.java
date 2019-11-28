package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.WmsPickTicketOrder;
import cn.stylefeng.guns.modular.servlet.mapper.WmsPickTicketOrderMapper;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
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
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class WmsPickTicketOrderService extends ServiceImpl<WmsPickTicketOrderMapper, WmsPickTicketOrder> {

    @Resource
    private WmsPickTicketOrderMapper wmsPickTicketOrderMapper;

    /**
     * 插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsPickTicketOrder(WmsPickTicketOrder wmsPickTicketOrder) {

     this.save(wmsPickTicketOrder);
    }

    /**
     * 批量插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsPickTicketOrderList(Collection<WmsPickTicketOrder> wmsPickTicketOrderCollection,int batchSize) throws Exception {

        this.saveBatch(wmsPickTicketOrderCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsPickTicketOrder(WmsPickTicketOrder wmsPickTicketOrder) {

        //如果物料为空
        if (wmsPickTicketOrder == null || ToolUtil.isOneEmpty(wmsPickTicketOrder.getShangp_id(),wmsPickTicketOrder.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(wmsPickTicketOrder);
    }
    /**
     * 批量更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsPickTicketOrderList(Collection<WmsPickTicketOrder> wmsPickTicketOrders,int batchSize) {

        this.updateBatchById(wmsPickTicketOrders,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsPickTicketOrder> wmsPickTicketOrders,int batchSize) {

        this.saveOrUpdateBatch(wmsPickTicketOrders,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsPickTicketOrder(Long wmsPickTicketOrderId) {

        //删除菜单
        this.wmsPickTicketOrderMapper.deleteById(wmsPickTicketOrderId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsPickTicketOrder(String orderCode,String itemCode) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsPickTicketOrderMapper.selectWmsPickTicketOrder(page, orderCode,itemCode);
    }



}
