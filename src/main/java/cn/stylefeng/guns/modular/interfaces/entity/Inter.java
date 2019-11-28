package cn.stylefeng.guns.modular.interfaces.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 接口表
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-01
 */
@TableName("inter_opt")
public class Inter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "inter_id", type = IdType.ID_WORKER)
    private Long interId;
    @TableField("interface_type")
    private String interfaceType;
    @TableField("transfer_type")
    private String transferType;
    @TableField("interface_name")
    private String interfaceName;
    @TableField("beactive")
    private String beactive;
    @TableField("up_method")
    private String upMethod;
    @TableField("up_method_detail")
    private String upMethodDetail;
    @TableField("url")
    private String url;
    @TableField("down_method")
    private String downMethod;
    @TableField("down_method_detail")
    private String downMethodDetail;
    @TableField("up_table")
    private String upTable;
    @TableField("data_method")
    private String dataMethod;
    @TableField("up_table_construct")
    private String upTableConstruct;
    @TableField("down_table")
    private String downTable;
    @TableField("down_table_construct")
    private String downTableConstruct;
    @TableField("message_context")
    private String messageContext;
    @TableField("down_produce")
    private String downProduce;
    @TableField("down_produce_params")
    private String downProduceParams;
    @TableField("detail")
    private String detail;
    @TableField("userName")
    private String userName;
    @TableField("password")
    private String password;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getInterId() {
        return interId;
    }

    public void setInterId(Long interId) {
        this.interId = interId;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getBeactive() {
        return beactive;
    }

    public void setBeactive(String beactive) {
        this.beactive = beactive;
    }

    public String getUpMethod() {
        return upMethod;
    }

    public void setUpMethod(String upMethod) {
        this.upMethod = upMethod;
    }

    public String getUpMethodDetail() {
        return upMethodDetail;
    }

    public void setUpMethodDetail(String upMethodDetail) {
        this.upMethodDetail = upMethodDetail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownMethod() {
        return downMethod;
    }

    public void setDownMethod(String downMethod) {
        this.downMethod = downMethod;
    }

    public String getDownMethodDetail() {
        return downMethodDetail;
    }

    public void setDownMethodDetail(String downMethodDetail) {
        this.downMethodDetail = downMethodDetail;
    }

    public String getUpTable() {
        return upTable;
    }

    public void setUpTable(String upTable) {
        this.upTable = upTable;
    }

    public String getUpTableConstruct() {
        return upTableConstruct;
    }

    public void setUpTableConstruct(String upTableConstruct) {
        this.upTableConstruct = upTableConstruct;
    }

    public String getDownTable() {
        return downTable;
    }

    public void setDownTable(String downTable) {
        this.downTable = downTable;
    }

    public String getDownTableConstruct() {
        return downTableConstruct;
    }

    public void setDownTableConstruct(String downTableConstruct) {
        this.downTableConstruct = downTableConstruct;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public String getDataMethod() {
        return dataMethod;
    }

    public void setDataMethod(String dataMethod) {
        this.dataMethod = dataMethod;
    }

    public String getDownProduce() {
        return downProduce;
    }

    public void setDownProduce(String downProduce) {
        this.downProduce = downProduce;
    }

    public String getDownProduceParams() {
        return downProduceParams;
    }

    public void setDownProduceParams(String downProduceParams) {
        this.downProduceParams = downProduceParams;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

}

