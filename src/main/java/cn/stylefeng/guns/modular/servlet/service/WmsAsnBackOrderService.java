package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.WmsAsnBackOrder;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.mapper.WmsAsnBackOrderMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
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
public class WmsAsnBackOrderService extends ServiceImpl<WmsAsnBackOrderMapper, WmsAsnBackOrder> {

    @Resource
    private WmsAsnBackOrderMapper wmsAsnBackOrderMapper;

    /**
     * 插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsAsnBackOrder(WmsAsnBackOrder wmsAsnBackOrder) {

     this.save(wmsAsnBackOrder);
    }

    /**
     * 批量插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsAsnBackOrderList(Collection<WmsAsnBackOrder> wmsAsnBackOrderCollection,int batchSize) throws Exception {

        this.saveBatch(wmsAsnBackOrderCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsAsnBackOrder(WmsAsnBackOrder wmsAsnBackOrder) {

        //如果物料为空
        if (wmsAsnBackOrder == null || ToolUtil.isOneEmpty(wmsAsnBackOrder.getShangp_id(),wmsAsnBackOrder.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(wmsAsnBackOrder);
    }
    /**
     * 批量更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsAsnBackOrderList(Collection<WmsAsnBackOrder> wmsAsnBackOrders,int batchSize) {

        this.updateBatchById(wmsAsnBackOrders,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsAsnBackOrder> wmsAsnBackOrders,int batchSize) {

        this.saveOrUpdateBatch(wmsAsnBackOrders,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsAsnBackOrder(Long wmsAsnBackOrderId) {

        //删除菜单
        this.wmsAsnBackOrderMapper.deleteById(wmsAsnBackOrderId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsAsnBackOrder(String orderCode,String itemCode,String zt) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsAsnBackOrderMapper.selectWmsAsnBackOrder(page, orderCode,itemCode,zt);
    }
    /**
     * 根据字段名查询
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Object selectObject(String tableField,String orderCode,String rowNum) {


        return this.wmsAsnBackOrderMapper.selectObject(tableField,orderCode,rowNum);
    }

    /**
     * 根据业务类型查找单据编号
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<String> selectOrderCode(String[] types) {


        return this.wmsAsnBackOrderMapper.selectOrderCode(types);
    }

    /**
     * 根据单据编号查找对象
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<WmsAsnBackOrder> selectWmsAsnBackOrderByCode(String orderCode) {


        return this.wmsAsnBackOrderMapper.selectWmsAsBackOrderByCode(orderCode);
    }
    /**
     * 根据单据编号查找对象
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<Map<String,Object>> selectWmsAsnBackOrderList(String orderCode) {


        return this.wmsAsnBackOrderMapper.selectWmsAsnBackOrderList(orderCode);
    }


}
