package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 出库开票单取消对象
 */

@SuppressWarnings("serial")
@TableName("INF_CK_KPD_DEL_BILL")
public class WmsPickTicketOrderDelete implements Serializable {

	@TableField("danj_no")
	private String danj_no     ;
	@TableField("hanghao")
	private Long hanghao    ;
	@TableField("wlzx_code")
	private String wlzx_code  ;
	@TableField("huoz_id")
	private String huoz_id    ;
	@TableField("riqi_date")
	private String riqi_date  ;
	@TableField("danj_no_y")
	private String danj_no_y    ;
	@TableField("hanghao_y")
	private String hanghao_y;
	@TableField("yew_staff")
	private String yew_staff  ;
	@TableField("num")
	private Double num        ;
	@TableField("yuan_yin")
	private String yuan_yin       ;
	@TableField("note")
	private String note       ;
	@TableField("tiaom_num")
	private Double tiaom_num  ;
	@TableField("zt")
	private String zt         ;

	public String getDanj_no() {
		return danj_no;
	}

	public void setDanj_no(String danj_no) {
		this.danj_no = danj_no;
	}

	public Long getHanghao() {
		return hanghao;
	}

	public void setHanghao(Long hanghao) {
		this.hanghao = hanghao;
	}

	public String getWlzx_code() {
		return wlzx_code;
	}

	public void setWlzx_code(String wlzx_code) {
		this.wlzx_code = wlzx_code;
	}

	public String getHuoz_id() {
		return huoz_id;
	}

	public void setHuoz_id(String huoz_id) {
		this.huoz_id = huoz_id;
	}

	public String getRiqi_date() {
		return riqi_date;
	}

	public void setRiqi_date(String riqi_date) {
		this.riqi_date = riqi_date;
	}

	public String getDanj_no_y() {
		return danj_no_y;
	}

	public void setDanj_no_y(String danj_no_y) {
		this.danj_no_y = danj_no_y;
	}

	public String getHanghao_y() {
		return hanghao_y;
	}

	public void setHanghao_y(String hanghao_y) {
		this.hanghao_y = hanghao_y;
	}

	public String getYew_staff() {
		return yew_staff;
	}

	public void setYew_staff(String yew_staff) {
		this.yew_staff = yew_staff;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String getYuan_yin() {
		return yuan_yin;
	}

	public void setYuan_yin(String yuan_yin) {
		this.yuan_yin = yuan_yin;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getTiaom_num() {
		return tiaom_num;
	}

	public void setTiaom_num(Double tiaom_num) {
		this.tiaom_num = tiaom_num;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
}
