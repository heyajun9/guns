package cn.stylefeng.guns.modular.interfaces.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

@TableName("interface_option_t")
public class TableOption implements Serializable{

	private static final long serialVersionUID = 1L;

	@TableId(value = "option_id", type = IdType.ID_WORKER)
	private Long optionId                  ;
	@TableField("inter_id")
	private Long interId;
	@TableField("up_column_name")
	private String upColumnName     ;
	@TableField("down_column_name")
	private String downColumnName   ;
	@TableField("type_name")
	private String typeName          ;
	@TableField("column_size")
	private String columnSize        ;
	@TableField("nullable")
	private String nullable           ;
	@TableField("remarks")
	private String remarks            ;
	@TableField("table_name")
	private String tableName         ;
	@TableField("parent_menu")
	private String parentMenu;
	@TableField("is_flag")
	private String isFlag;
	@TableField("default_value")
	private String defaultValue;
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

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public Long getInterId() {
		return interId;
	}

	public void setInterId(Long interId) {
		this.interId = interId;
	}
	public String getUpColumnName() {
		return upColumnName;
	}

	public void setUpColumnName(String upColumnName) {
		this.upColumnName = upColumnName;
	}

	public String getDownColumnName() {
		return downColumnName;
	}

	public void setDownColumnName(String downColumnName) {
		this.downColumnName = downColumnName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return "TableOption{" +
				"optionId=" + optionId +
				", interId=" + interId +
				", upColumnName='" + upColumnName + '\'' +
				", downColumnName='" + downColumnName + '\'' +
				", typeName='" + typeName + '\'' +
				", columnSize='" + columnSize + '\'' +
				", nullable='" + nullable + '\'' +
				", remarks='" + remarks + '\'' +
				", tableName='" + tableName + '\'' +
				", parentMenu='" + parentMenu + '\'' +
				", isFlag='" + isFlag + '\'' +
				", defaultValue='" + defaultValue + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", createUser=" + createUser +
				", updateUser=" + updateUser +
				'}';
	}
}
