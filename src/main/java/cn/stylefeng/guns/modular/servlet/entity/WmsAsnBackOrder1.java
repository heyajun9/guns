package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@TableName("inf_rk_sc_bill")
public class WmsAsnBackOrder1 implements Serializable {

	@TableId(value = "asnback_id", type = IdType.ID_WORKER)
	 private Long asnback_id;
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
	 @TableField("from_djbh")
	private String from_djbh          ;
	 @TableField("from_hanghao")
	private String from_hanghao       ;
	 @TableField("danj_riqi")
	private String danj_riqi          ;
	 @TableField("baoz_danw")
	private String baoz_danw          ;
	 @TableField("yid_type")
	private String yid_type           ;
	 @TableField("gongchang")
	private String gongchang          ;
	 @TableField("kc_address")
	private String kc_address         ;
	 @TableField("to_address")
	private String to_address         ;
	 @TableField("from_address")
	private String from_address       ;
	 @TableField("to_lot")
	private String to_lot             ;
	 @TableField("tskc_flg")
	private String tskc_flg           ;
	 @TableField("gongys_no")
	private String gongys_no          ;
	 @TableField("dingd_no")
	private String dingd_no           ;
	 @TableField("chengbzx_no")
	private String chengbzx_no        ;
	 @TableField("yldj_no")
	private String yldj_no            ;
	 @TableField("yl_hanghao")
	private String yl_hanghao         ;
	 @TableField("to_djbh")
	private String to_djbh            ;
	 @TableField("to_hanghao")
	private String to_hanghao         ;
	 @TableField("cgd_no")
	 private String cgd_no;
	 @TableField("cgd_hanghao")
	 private String cgd_hanghao;
	@TableField("produce_date")
	private String  produce_date       ;
	@TableField("expire_date")
	private String  expire_date       ;

	public Long getAsnback_id() {
		return asnback_id;
	}

	public void setAsnback_id(Long asnback_id) {
		this.asnback_id = asnback_id;
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

	public String getFrom_djbh() {
		return from_djbh;
	}

	public void setFrom_djbh(String from_djbh) {
		this.from_djbh = from_djbh;
	}

	public String getFrom_hanghao() {
		return from_hanghao;
	}

	public void setFrom_hanghao(String from_hanghao) {
		this.from_hanghao = from_hanghao;
	}

	public String getDanj_riqi() {
		return danj_riqi;
	}

	public void setDanj_riqi(String danj_riqi) {
		this.danj_riqi = danj_riqi;
	}

	public String getBaoz_danw() {
		return baoz_danw;
	}

	public void setBaoz_danw(String baoz_danw) {
		this.baoz_danw = baoz_danw;
	}

	public String getYid_type() {
		return yid_type;
	}

	public void setYid_type(String yid_type) {
		this.yid_type = yid_type;
	}

	public String getGongchang() {
		return gongchang;
	}

	public void setGongchang(String gongchang) {
		this.gongchang = gongchang;
	}

	public String getKc_address() {
		return kc_address;
	}

	public void setKc_address(String kc_address) {
		this.kc_address = kc_address;
	}

	public String getTo_address() {
		return to_address;
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

	public String getFrom_address() {
		return from_address;
	}

	public void setFrom_address(String from_address) {
		this.from_address = from_address;
	}

	public String getTo_lot() {
		return to_lot;
	}

	public void setTo_lot(String to_lot) {
		this.to_lot = to_lot;
	}

	public String getTskc_flg() {
		return tskc_flg;
	}

	public void setTskc_flg(String tskc_flg) {
		this.tskc_flg = tskc_flg;
	}

	public String getGongys_no() {
		return gongys_no;
	}

	public void setGongys_no(String gongys_no) {
		this.gongys_no = gongys_no;
	}

	public String getDingd_no() {
		return dingd_no;
	}

	public void setDingd_no(String dingd_no) {
		this.dingd_no = dingd_no;
	}

	public String getChengbzx_no() {
		return chengbzx_no;
	}

	public void setChengbzx_no(String chengbzx_no) {
		this.chengbzx_no = chengbzx_no;
	}

	public String getYldj_no() {
		return yldj_no;
	}

	public void setYldj_no(String yldj_no) {
		this.yldj_no = yldj_no;
	}

	public String getYl_hanghao() {
		return yl_hanghao;
	}

	public void setYl_hanghao(String yl_hanghao) {
		this.yl_hanghao = yl_hanghao;
	}

	public String getTo_djbh() {
		return to_djbh;
	}

	public void setTo_djbh(String to_djbh) {
		this.to_djbh = to_djbh;
	}

	public String getTo_hanghao() {
		return to_hanghao;
	}

	public void setTo_hanghao(String to_hanghao) {
		this.to_hanghao = to_hanghao;
	}

	public String getCgd_no() {
		return cgd_no;
	}

	public void setCgd_no(String cgd_no) {
		this.cgd_no = cgd_no;
	}

	public String getCgd_hanghao() {
		return cgd_hanghao;
	}

	public void setCgd_hanghao(String cgd_hanghao) {
		this.cgd_hanghao = cgd_hanghao;
	}

	public String getProduce_date() {
		return produce_date;
	}

	public void setProduce_date(String produce_date) {
		this.produce_date = produce_date;
	}

	public String getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
}
