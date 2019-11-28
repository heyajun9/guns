package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.InsertMap;
import cn.stylefeng.guns.modular.servlet.mapper.InsertMapMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class InsertMapService extends ServiceImpl<InsertMapMapper, InsertMap> {

    @Resource
    private InsertMapMapper insertMapMapper;

    /**
     * 插入实体类
     */
    @Transactional
    public void addMap(InsertMap insertMap) {

        this.insertMapMapper.insertInfo(insertMap);
    }
    /**
     * 更新实体类
     */
    @Transactional
    public void updateMap(InsertMap insertMap) {

        this.insertMapMapper.updateInfo(insertMap);
    }


}
