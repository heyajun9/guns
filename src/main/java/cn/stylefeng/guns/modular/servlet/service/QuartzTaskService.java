package cn.stylefeng.guns.modular.servlet.service;

import cn.stylefeng.guns.modular.servlet.entity.QuartzTask;
import cn.stylefeng.guns.modular.servlet.mapper.QuartzTaskMapper;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
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
public class QuartzTaskService extends ServiceImpl<QuartzTaskMapper, QuartzTask> {

    @Resource
    private QuartzTaskMapper quartzTaskMapper;

    /**
     * 添加接口配置
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addQuartzTask(QuartzTask quartzTask) {

     this.save(quartzTask);
    }

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateQuartzTask(QuartzTask quartzTask) {

        //如果菜单为空
        if (quartzTask == null || ToolUtil.isOneEmpty(quartzTask.getClassName(),quartzTask.getJobGroupName())) {
            throw new RequestEmptyException();
        }

        this.updateById(quartzTask);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delQuartzTask(Long quartzTaskId) {

        //删除菜单
        this.quartzTaskMapper.deleteById(quartzTaskId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectQuartzTask(String jobName,String className) {

        Page page = LayuiPageFactory.defaultPage();

        return this.baseMapper.selectTask(page, jobName,className);
    }


    /**
     * 查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<QuartzTask>  selectQuartzTaskAll() {

        return this.baseMapper.selectTaskAll();
    }



}
