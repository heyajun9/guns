package cn.stylefeng.guns.modular.interfaces.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.mapper.InterMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class InterService extends ServiceImpl<InterMapper, Inter> {

    @Resource
    private InterMapper interMapper;

    /**
     * 添加接口配置
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addInter(Inter inter) {

     this.save(inter);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateInter(Inter inter) {

        //如果菜单为空
        if (inter == null || ToolUtil.isOneEmpty(inter.getInterId(),inter.getInterfaceName())) {
            throw new RequestEmptyException();
        }

        this.updateById(inter);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delInter(Long interId) {

        //删除菜单
        this.interMapper.deleteById(interId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectInter(String condition,String interfaceType,String downTable) {

        Page page = LayuiPageFactory.defaultPage();

        return this.baseMapper.selectInter(page, condition,interfaceType,downTable);
    }

    /**
     * 根据类型查找对象
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<Inter> selectByType(String type) {
        return this.baseMapper.selectInterByType(type);
    }



}
