package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.entity.WmsSaleAsnOrder;
import cn.stylefeng.guns.modular.servlet.entity.WmsSaleAsnOrder;
import cn.stylefeng.guns.modular.servlet.mapper.WmsSaleAsnOrderMapper;
import cn.stylefeng.guns.modular.servlet.mapper.WmsSaleAsnOrderMapper;
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
 * 菜单表 销售退回入库service
 * </p>
 *
 * @author heyajun
 * @since 2018-12-07
 */
@Service
public class WmsAsnSaleOrderService extends ServiceImpl<WmsSaleAsnOrderMapper, WmsSaleAsnOrder> {

    @Resource
    private WmsSaleAsnOrderMapper wmsSaleAsnOrderMapper;

    /**
     * 插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsSaleAsnOrder(WmsSaleAsnOrder WmsSaleAsnOrder) {

     this.save(WmsSaleAsnOrder);
    }

    /**
     * 批量插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsSaleAsnOrderList(Collection<WmsSaleAsnOrder> WmsSaleAsnOrderCollection,int batchSize) throws Exception {

        this.saveBatch(WmsSaleAsnOrderCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsSaleAsnOrder(WmsSaleAsnOrder WmsSaleAsnOrder) {

        //如果物料为空
        if (WmsSaleAsnOrder == null || ToolUtil.isOneEmpty(WmsSaleAsnOrder.getShangp_id(),WmsSaleAsnOrder.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(WmsSaleAsnOrder);
    }
    /**
     * 批量更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsSaleAsnOrderList(Collection<WmsSaleAsnOrder> WmsSaleAsnOrders,int batchSize) {

        this.updateBatchById(WmsSaleAsnOrders,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsSaleAsnOrder> WmsSaleAsnOrders,int batchSize) {

        this.saveOrUpdateBatch(WmsSaleAsnOrders,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsSaleAsnOrder(Long WmsSaleAsnOrderId) {

        //删除菜单
        this.wmsSaleAsnOrderMapper.deleteById(WmsSaleAsnOrderId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsSaleAsnOrder(String orderCode,String itemCode) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsSaleAsnOrderMapper.selectWmsSaleAsnOrder(page, orderCode,itemCode);
    }



}
