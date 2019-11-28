package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.entity.WmsInventory;
import cn.stylefeng.guns.modular.servlet.entity.WmsInventory;
import cn.stylefeng.guns.modular.servlet.mapper.WmsInventoryMapper;
import cn.stylefeng.guns.modular.servlet.mapper.WmsInventoryMapper;
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
public class WmsInventoryService extends ServiceImpl<WmsInventoryMapper, WmsInventory> {

    @Resource
    private WmsInventoryMapper wmsInventoryMapper;

    /**
     * 插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsInventory(WmsInventory wmsInventory) {

     this.save(wmsInventory);
    }

    /**
     * 批量插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsInventoryList(Collection<WmsInventory> wmsInventoryCollection,int batchSize) throws Exception {

        this.saveBatch(wmsInventoryCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsInventory(WmsInventory wmsInventory) {

        //如果物料为空
        if (wmsInventory == null || ToolUtil.isOneEmpty(wmsInventory.getShangp_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(wmsInventory);
    }
    /**
     * 批量更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsInventoryList(Collection<WmsInventory> wmsInventorys,int batchSize) {

        this.updateBatchById(wmsInventorys,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsInventory> wmsInventories,int batchSize) {

        this.saveOrUpdateBatch(wmsInventories,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsInventory(Long wmsInventoryId) {

        //删除菜单
        this.wmsInventoryMapper.deleteById(wmsInventoryId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsInventory(String itemCode,String organization) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsInventoryMapper.selectInventory(page, itemCode,organization);
    }



}
