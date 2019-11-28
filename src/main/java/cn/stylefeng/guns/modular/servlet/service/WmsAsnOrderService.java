package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.WmsAsnOrder;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.mapper.WmsAsnOrderMapper;
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
public class WmsAsnOrderService extends ServiceImpl<WmsAsnOrderMapper, WmsAsnOrder> {

    @Resource
    private WmsAsnOrderMapper wmsAsnOrderMapper;

    /**
     * 插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsAsnOrder(WmsAsnOrder wmsAsnOrder) {

     this.save(wmsAsnOrder);
    }

    /**
     * 批量插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsAsnOrderList(Collection<WmsAsnOrder> wmsAsnOrderCollection,int batchSize) throws Exception {

        this.saveBatch(wmsAsnOrderCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsAsnOrder(WmsAsnOrder wmsAsnOrder) {

        //如果物料为空
        if (wmsAsnOrder == null || ToolUtil.isOneEmpty(wmsAsnOrder.getShangp_id(),wmsAsnOrder.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(wmsAsnOrder);
    }
    /**
     * 批量更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsAsnOrderList(Collection<WmsAsnOrder> wmsAsnOrders,int batchSize) {

        this.updateBatchById(wmsAsnOrders,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsAsnOrder> wmsAsnOrders,int batchSize) {

        this.saveOrUpdateBatch(wmsAsnOrders,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsAsnOrder(Long wmsAsnOrderId) {

        //删除菜单
        this.wmsAsnOrderMapper.deleteById(wmsAsnOrderId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsAsnOrder(String orderCode,String itemCode) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsAsnOrderMapper.selectWmsAsnOrder(page, orderCode,itemCode);
    }



}
