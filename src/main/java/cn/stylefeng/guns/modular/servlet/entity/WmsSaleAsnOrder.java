package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 销售退回的单子
 */
@SuppressWarnings("serial")
@TableName("INF_RK_XTD_BILL")
public class WmsSaleAsnOrder implements Serializable {

	@TableField("danj_no")
	private String danj_no          ;
	@TableField("hanghao")
	private Long hanghao          ;
	@TableField("wlzx_code")
	private String wlzx_code        ;
	@TableField("huoz_id")
	private String huoz_id          ;
	@TableField("riqi_date")
	private String riqi_date        ;
	@TableField("danw_id")
	private String danw_id          ;
	@TableField("yew_staff")
	private String yew_staff        ;
	@TableField("yew_type")
	private String yew_type         ;
	@TableField("ruk_type")
	private String ruk_type         ;
	@TableField("shangp_id")
	private String shangp_id        ;
	@TableField("lot")
	private String lot              ;
	@TableField("shengchan_char")
	private String shengchan_char   ;
	@TableField("youxq_char")
	private String youxq_char       ;
	@TableField("num")
	private Double num              ;
	@TableField("tuih_reason")
	private String tuih_reason      ;
	@TableField("yans_rlt")
	private String yans_rlt         ;
	@TableField("note")
	private String note             ;
	@TableField("tiaom_num")
	private Long tiaom_num        ;
	@TableField("zt")
	private String zt               ;
	@TableField("danj_no_wsdd")
	private String danj_no_wsdd     ;
	@TableField("platform_username")
	private String platform_username;
	@TableField("yn_guid")
	private String yn_guid          ;

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

	public String getDanw_id() {
		return danw_id;
	}

	public void setDanw_id(String danw_id) {
		this.danw_id = danw_id;
	}

	public String getYew_staff() {
		return yew_staff;
	}

	public void setYew_staff(String yew_staff) {
		this.yew_staff = yew_staff;
	}

	public String getYew_type() {
		return yew_type;
	}

	public void setYew_type(String yew_type) {
		this.yew_type = yew_type;
	}

	public String getRuk_type() {
		return ruk_type;
	}

	public void setRuk_type(String ruk_type) {
		this.ruk_type = ruk_type;
	}

	public String getShangp_id() {
		return shangp_id;
	}

	public void setShangp_id(String shangp_id) {
		this.shangp_id = shangp_id;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getShengchan_char() {
		return shengchan_char;
	}

	public void setShengchan_char(String shengchan_char) {
		this.shengchan_char = shengchan_char;
	}

	public String getYouxq_char() {
		return youxq_char;
	}

	public void setYouxq_char(String youxq_char) {
		this.youxq_char = youxq_char;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String getTuih_reason() {
		return tuih_reason;
	}

	public void setTuih_reason(String tuih_reason) {
		this.tuih_reason = tuih_reason;
	}

	public String getYans_rlt() {
		return yans_rlt;
	}

	public void setYans_rlt(String yans_rlt) {
		this.yans_rlt = yans_rlt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getTiaom_num() {
		return tiaom_num;
	}

	public void setTiaom_num(Long tiaom_num) {
		this.tiaom_num = tiaom_num;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getDanj_no_wsdd() {
		return danj_no_wsdd;
	}

	public void setDanj_no_wsdd(String danj_no_wsdd) {
		this.danj_no_wsdd = danj_no_wsdd;
	}

	public String getPlatform_username() {
		return platform_username;
	}

	public void setPlatform_username(String platform_username) {
		this.platform_username = platform_username;
	}

	public String getYn_guid() {
		return yn_guid;
	}

	public void setYn_guid(String yn_guid) {
		this.yn_guid = yn_guid;
	}
}
