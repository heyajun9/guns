package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.entity.WmsAsnOrderDelete;
import cn.stylefeng.guns.modular.servlet.mapper.WmsAsnOrderDeleteMapper;
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
public class WmsAsnOrderDeleteService extends ServiceImpl<WmsAsnOrderDeleteMapper, WmsAsnOrderDelete> {

    @Resource
    private WmsAsnOrderDeleteMapper WmsAsnOrderDeleteMapper;

    /**
     * 插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsAsnOrderDelete(WmsAsnOrderDelete WmsAsnOrderDelete) {

     this.save(WmsAsnOrderDelete);
    }

    /**
     * 批量插入
     *
     * @author heyajun
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsAsnOrderDeleteList(Collection<WmsAsnOrderDelete> WmsAsnOrderDeleteCollection,int batchSize) throws Exception {

        this.saveBatch(WmsAsnOrderDeleteCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsAsnOrderDelete(WmsAsnOrderDelete WmsAsnOrderDelete) {

        //如果物料为空
        if (WmsAsnOrderDelete == null || ToolUtil.isOneEmpty(WmsAsnOrderDelete.getDanj_no_y(),WmsAsnOrderDelete.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(WmsAsnOrderDelete);
    }
    /**
     * 批量更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsAsnOrderDeleteList(Collection<WmsAsnOrderDelete> WmsAsnOrderDeletes,int batchSize) {

        this.updateBatchById(WmsAsnOrderDeletes,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author heyajun
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsAsnOrderDelete> WmsAsnOrderDeletes,int batchSize) {

        this.saveOrUpdateBatch(WmsAsnOrderDeletes,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsAsnOrderDelete(Long WmsAsnOrderDeleteId) {

        //删除菜单
        this.WmsAsnOrderDeleteMapper.deleteById(WmsAsnOrderDeleteId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsAsnOrderDelete(String orderCode,String itemCode) {

        Page page = LayuiPageFactory.defaultPage();

        return this.WmsAsnOrderDeleteMapper.selectWmsAsnOrderDelete(page, orderCode,itemCode);
    }



}
