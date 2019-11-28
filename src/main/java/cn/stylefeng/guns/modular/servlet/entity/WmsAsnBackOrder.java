package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@TableName("inf_rk_sc_bill")
public class WmsAsnBackOrder implements Serializable {

	@TableId(value = "id", type = IdType.ID_WORKER)
	 private Long id;
	 @TableField("danj_no")
	private String danj_no            ;
	 @TableField("hanghao")
	private Long hanghao            ;
	 @TableField("huoz_id")
	private String huoz_id            ;
	 @TableField("wlzx_code")
	private String wlzx_code          ;
	 @TableField("riqi_date")
	private Date riqi_date          ;
	 @TableField("danw_id")
	private String danw_id            ;
	 @TableField("caigou_staff")
	private String caigou_staff       ;
	 @TableField("caoz_staff")
	private String caoz_staff         ;
	 @TableField("zhij_staff")
	private String zhij_staff         ;
	 @TableField("shouh_staff")
	private String shouh_staff        ;
	 @TableField("yew_type")
	private String yew_type           ;
	 @TableField("ruk_type")
	private String ruk_type           ;
	 @TableField("hangh_cgd")
	private Long hangh_cgd          ;
	 @TableField("yewdj_no")
	private String yewdj_no           ;
	 @TableField("shangp_id")
	private String shangp_id          ;
	 @TableField("lot")
	private String lot                ;
	 @TableField("shengchan_char")
	private String shengchan_char     ;
	 @TableField("youx_char")
	private String youx_char          ;
	 @TableField("num")
	private Long num                ;
	 @TableField("yans_rlt")
	private String yans_rlt           ;
	 @TableField("tiaom_num")
	private Long tiaom_num          ;
	 @TableField("zt")
	private String zt                 ;
	 @TableField("error_msg")
	private String error_msg          ;
	 @TableField("rkkpd_no")
	private String rkkpd_no           ;
	 @TableField("maker")
	private String maker              ;
	 @TableField("chandi")
	private String chandi             ;
	 @TableField("areacode")
	private String areacode           ;
	 @TableField("cunc_condition")
	private String cunc_condition     ;
	 @TableField("yn_guid")
	private String yn_guid          ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getHuoz_id() {
		return huoz_id;
	}

	public void setHuoz_id(String huoz_id) {
		this.huoz_id = huoz_id;
	}

	public String getWlzx_code() {
		return wlzx_code;
	}

	public void setWlzx_code(String wlzx_code) {
		this.wlzx_code = wlzx_code;
	}

	public Date getRiqi_date() {
		return riqi_date;
	}

	public void setRiqi_date(Date riqi_date) {
		this.riqi_date = riqi_date;
	}

	public String getDanw_id() {
		return danw_id;
	}

	public void setDanw_id(String danw_id) {
		this.danw_id = danw_id;
	}

	public String getCaigou_staff() {
		return caigou_staff;
	}

	public void setCaigou_staff(String caigou_staff) {
		this.caigou_staff = caigou_staff;
	}

	public String getCaoz_staff() {
		return caoz_staff;
	}

	public void setCaoz_staff(String caoz_staff) {
		this.caoz_staff = caoz_staff;
	}

	public String getZhij_staff() {
		return zhij_staff;
	}

	public void setZhij_staff(String zhij_staff) {
		this.zhij_staff = zhij_staff;
	}

	public String getShouh_staff() {
		return shouh_staff;
	}

	public void setShouh_staff(String shouh_staff) {
		this.shouh_staff = shouh_staff;
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

	public Long getHangh_cgd() {
		return hangh_cgd;
	}

	public void setHangh_cgd(Long hangh_cgd) {
		this.hangh_cgd = hangh_cgd;
	}

	public String getYewdj_no() {
		return yewdj_no;
	}

	public void setYewdj_no(String yewdj_no) {
		this.yewdj_no = yewdj_no;
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

	public String getYoux_char() {
		return youx_char;
	}

	public void setYoux_char(String youx_char) {
		this.youx_char = youx_char;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getYans_rlt() {
		return yans_rlt;
	}

	public void setYans_rlt(String yans_rlt) {
		this.yans_rlt = yans_rlt;
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

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String getRkkpd_no() {
		return rkkpd_no;
	}

	public void setRkkpd_no(String rkkpd_no) {
		this.rkkpd_no = rkkpd_no;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getCunc_condition() {
		return cunc_condition;
	}

	public void setCunc_condition(String cunc_condition) {
		this.cunc_condition = cunc_condition;
	}

	public String getYn_guid() {
		return yn_guid;
	}

	public void setYn_guid(String yn_guid) {
		this.yn_guid = yn_guid;
	}
}
