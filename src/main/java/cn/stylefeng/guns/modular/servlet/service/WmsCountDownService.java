package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.servlet.entity.WmsCountDown;
import cn.stylefeng.guns.modular.servlet.entity.WmsCountDown;
import cn.stylefeng.guns.modular.servlet.mapper.WmsCountDownMapper;
import cn.stylefeng.guns.modular.servlet.mapper.WmsCountDownMapper;
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
public class WmsCountDownService extends ServiceImpl<WmsCountDownMapper, WmsCountDown> {

    @Resource
    private WmsCountDownMapper wmsCountDownMapper;

    /**
     * 插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsCountDown(WmsCountDown wmsCountDown) {

     this.save(wmsCountDown);
    }

    /**
     * 批量插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsCountDownList(Collection<WmsCountDown> wmsCountDowns,int batchSize) throws Exception {

        this.saveBatch(wmsCountDowns,batchSize);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsCountDown(WmsCountDown wmsCountDown) {

        //如果物料为空
        if (wmsCountDown == null || ToolUtil.isOneEmpty(wmsCountDown.getShangp_id(),wmsCountDown.getDanj_no())) {
            throw new RequestEmptyException();
        }

        this.updateById(wmsCountDown);
    }
    /**
     * 批量更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsCountDownList(Collection<WmsCountDown> wmsCountDowns,int batchSize) {

        this.updateBatchById(wmsCountDowns,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsCountDown> wmsCountDowns,int batchSize) {

        this.saveOrUpdateBatch(wmsCountDowns,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsCountDown(Long wmsCountDownId) {

        //删除菜单
        this.wmsCountDownMapper.deleteById(wmsCountDownId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsCountDown(String orderCode,String itemCode) {

        Page page = LayuiPageFactory.defaultPage();

        return this.wmsCountDownMapper.selectWmsCountDown(page, orderCode,itemCode);
    }
    /**
     * 根据字段名查询
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Object selectObject(String tableField,String orderCode,String rowNum) {


        return this.wmsCountDownMapper.selectObject(tableField,orderCode,rowNum);
    }


    /**
     * 根据单据编号查找对象
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<WmsCountDown> selectWmsCountDownByCode(String orderCode) {


        return this.wmsCountDownMapper.selectWmsCountDownByCode(orderCode);
    }


}
