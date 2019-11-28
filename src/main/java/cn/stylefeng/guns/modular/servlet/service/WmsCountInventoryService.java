package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.WmsCountInventory;
import cn.stylefeng.guns.modular.servlet.mapper.WmsCountInventoryMapper;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
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
public class WmsCountInventoryService extends ServiceImpl<WmsCountInventoryMapper, WmsCountInventory> {

    @Resource
    private WmsCountInventoryMapper wmsCountInventoryMapper;

    /**
     * 插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsCountInventory(WmsCountInventory wmsCountInventory) {

     this.save(wmsCountInventory);
    }

    /**
     * 批量插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsCountInventoryList(Collection<WmsCountInventory> wmsCountInventories,int batchSize) throws Exception {

        this.saveBatch(wmsCountInventories,batchSize);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsCountInventory(WmsCountInventory wmsCountInventory) {

        //如果物料为空
        if (wmsCountInventory == null || ToolUtil.isOneEmpty(wmsCountInventory.getShangp_id(),wmsCountInventory.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(wmsCountInventory);
    }
    /**
     * 批量更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsCountInventoryList(Collection<WmsCountInventory> wmsCountInventories,int batchSize) {

        this.updateBatchById(wmsCountInventories,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsCountInventory> wmsCountInventories,int batchSize) {

        this.saveOrUpdateBatch(wmsCountInventories,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsCountInventory(Long wmsCountInventoryId) {

        //删除菜单
        this.wmsCountInventoryMapper.deleteById(wmsCountInventoryId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsCountInventory(String orderCode,String itemCode) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsCountInventoryMapper.selectWmsCountInventory(page, orderCode,itemCode);
    }
    /**
     * 根据字段名查询
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Object selectObject(String tableField,String orderCode,String rowNum) {


        return this.wmsCountInventoryMapper.selectObject(tableField,orderCode,rowNum);
    }


    /**
     * 根据单据编号查找对象
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<WmsCountInventory> selectWmsCountInventoryByCode(String orderCode) {


        return this.wmsCountInventoryMapper.selectWmsCountInventoryByCode(orderCode);
    }


}
