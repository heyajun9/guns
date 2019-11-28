package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.entity.WmsItem;
import cn.stylefeng.guns.modular.servlet.mapper.WmsItemMapper;
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
public class WmsItemService extends ServiceImpl<WmsItemMapper, WmsItem> {

    @Resource
    private WmsItemMapper wmsItemMapper;

    /**
     * 插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsItem(WmsItem wmsItem) {

     this.save(wmsItem);
    }

    /**
     * 批量插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsItemList(Collection<WmsItem> wmsItemCollection,int batchSize) throws Exception {

        this.saveBatch(wmsItemCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsItem(WmsItem wmsItem) {

        //如果物料为空
        if (wmsItem == null || ToolUtil.isOneEmpty(wmsItem.getShangp_id(),wmsItem.getShangp_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(wmsItem);
    }
    /**
     * 批量更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsItemList(Collection<WmsItem> wmsItems,int batchSize) {

        this.updateBatchById(wmsItems,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsItem> wmsItems,int batchSize) {

        this.saveOrUpdateBatch(wmsItems,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsItem(Long wmsItemId) {

        //删除菜单
        this.wmsItemMapper.deleteById(wmsItemId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsItem(String itemCode,String itemName) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsItemMapper.selectWmsItem(page, itemName,itemCode);
    }



}
