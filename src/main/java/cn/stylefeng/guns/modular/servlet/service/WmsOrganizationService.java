package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.WmsOrganization;
import cn.stylefeng.guns.modular.servlet.mapper.WmsOrganizationMapper;
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
public class WmsOrganizationService extends ServiceImpl<WmsOrganizationMapper, WmsOrganization> {

    @Resource
    private WmsOrganizationMapper wmsOrganizationMapper;

    /**
     * 插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addWmsItem(WmsOrganization organization) {

     this.save(organization);
    }

    /**
     * 批量插入
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addOrganizationList(Collection<WmsOrganization> organizationCollection,int batchSize) throws Exception {

        this.saveBatch(organizationCollection,batchSize);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsOrganization(WmsOrganization organization) {

        //如果物料为空
        if (organization == null || ToolUtil.isOneEmpty(organization.getDanw_id(),organization.getDanw_name())) {
            throw new RequestEmptyException();
        }

        this.updateById(organization);
    }
    /**
     * 批量更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWmsOrganizationList(Collection<WmsOrganization> wmsOrganizations,int batchSize) {

        this.updateBatchById(wmsOrganizations,batchSize);
    }

    /**
     * 批量插入或更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(Collection<WmsOrganization> wmsOrganizations,int batchSize) {

        this.saveOrUpdateBatch(wmsOrganizations,batchSize);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delWmsOrganization(Long organizationId) {

        //删除菜单
        this.wmsOrganizationMapper.deleteById(organizationId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectWmsOrganization(String organizationCode,String organizationName) {

        Page page = LayuiPageFactory.defaultPage();

        return this.baseMapper.selectOrganization(page, organizationName,organizationCode);
    }



}
