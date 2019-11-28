package cn.stylefeng.guns.modular.interfaces.service;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.mapper.TableMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class TableService extends ServiceImpl<TableMapper, TableOption> {

    @Resource
    private TableMapper tableMapper;

    /**
     * 添加接口配置
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addTable(TableOption table) {
//        //判断是否已经存在同编码或同名称字典
//        QueryWrapper<TableOption> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.eq("inter_id", table.getInterId());
//        List<TableOption> list = this.list(objectQueryWrapper);
//        if (list != null && list.size() > 0) {
//            throw new ServiceException(BizExceptionEnum.TABLE_EXISTED);
//        }

        if (ToolUtil.isOneEmpty(table, table.getInterId())) {
            throw new RequestEmptyException();
        }

//        table.setOptionId(null);

        this.save(table);
    }
    private TableOption getEntity(TableOption param) {
        TableOption entity = new TableOption();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }
    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateTable(TableOption table) {

        //如果菜单为空
        if (table == null || ToolUtil.isOneEmpty(table.getOptionId(),table.getUpColumnName())) {
            throw new RequestEmptyException();
        }

        this.updateById(table);
    }

    /**
     * 删除接口配置
     *
     * @author stylefeng
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delTable(Long TableId) {

        //删除菜单
        this.tableMapper.deleteById(TableId);

        //删除关联的表结构关系
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectTable(String condition,String downTable) {

        Page page = LayuiPageFactory.defaultPage();

        return this.baseMapper.selectTable(page, condition,downTable);
    }

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<TableOption> selectTableOption(Long interId) {


        return this.baseMapper.selectTableOption(interId);
    }

    /**
     * 根据表名查询
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectByTable(String tableName) {

        Page page = LayuiPageFactory.defaultPage();

        return this.baseMapper.selectByTable(page,tableName);
    }
    /**
     * 根据表名查询
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<TableOption> selectOptionByTable(String tableName) {


        return this.baseMapper.selectTableOptionByTable(tableName);
    }


    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    public LayuiPageInfo findPageBySpec(TableOption param) {
        QueryWrapper<TableOption> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("inter_id", param.getInterId());

        if (ToolUtil.isNotEmpty(param.getDownColumnName())) {
            objectQueryWrapper.and(i -> i.eq("name", param.getDownColumnName()));
        }

        List<TableOption> list = this.list(objectQueryWrapper);

        //创建根节点
        TableOption tableOption=new TableOption();
        list.add(tableOption);

        LayuiPageInfo result = new LayuiPageInfo();
        result.setData(list);

        return result;
    }


}
