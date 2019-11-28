package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@SuppressWarnings("serial")
@TableName("inf_rk_cgdd_bill")
public class WmsAsnOrder implements Serializable {

	 @TableId(value = "asn_id", type = IdType.ID_WORKER)
	 private Long asn_id;
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
	 @TableField("danw_id")
	private String danw_id    ;
	 @TableField("lianx_staff")
	private String lianx_staff;
	 @TableField("lianx_phone")
	private String lianx_phone;
	 @TableField("yew_staff")
	private String yew_staff  ;
	 @TableField("yew_type")
	private String yew_type   ;
	 @TableField("ruk_type")
	private String ruk_type   ;
	 @TableField("shangp_id")
	private String shangp_id  ;
	 @TableField("num")
	private Double num        ;
	 @TableField("note")
	private String note       ;
	 @TableField("tiaom_num")
	private Double tiaom_num  ;
	 @TableField("zt")
	private String zt         ;
	 @TableField("yewdj_no_ck")
	private String yewdj_no_ck;
	 @TableField("new_arrival")
	private String new_arrival;
	 @TableField("piz_no")
	private String piz_no     ;
	 @TableField("from_djbh")
	private String from_djbh        ;
	 @TableField("from_hanghao")
	private String  from_hanghao    ;
	 @TableField("danj_riqi")
	private String  danj_riqi       ;
	 @TableField("lot")
	private String  lot             ;
	 @TableField("baoz_danw")
	private String  baoz_danw       ;
	 @TableField("produce_date")
	private String  produce_date       ;
	 @TableField("expire_date")
	private String  expire_date       ;
	 @TableField("yid_type")
	private String yid_type         ;
	 @TableField("gongchang")
	private String gongchang        ;
	 @TableField("kc_address")
	private String kc_address       ;
	 @TableField("to_address")
	private String to_address       ;
	 @TableField("from_address")
	private String from_address     ;
	 @TableField("to_lot")
	private String to_lot           ;
	 @TableField("tskc_flg")
	private String tskc_flg         ;
	 @TableField("gongys_no")
	private String gongys_no        ;
	 @TableField("dingd_no")
	private String dingd_no         ;
	 @TableField("chengbzx_no")
	private String chengbzx_no      ;
	 @TableField("yldj_no")
	private String yldj_no          ;
	 @TableField("yl_hanghao")
	private String yl_hanghao       ;
	 @TableField("to_djbh")
	private String to_djbh          ;
	 @TableField("to_hanghao")
	private String to_hanghao       ;
	 @TableField("zt001")
	private String zt001            ;
	 @TableField("zt002")
	private String zt002            ;
	 @TableField("zt003")
	private String zt003            ;

	public Long getAsn_id() {
		return asn_id;
	}

	public void setAsn_id(Long asn_id) {
		this.asn_id = asn_id;
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

	public String getLianx_staff() {
		return lianx_staff;
	}

	public void setLianx_staff(String lianx_staff) {
		this.lianx_staff = lianx_staff;
	}

	public String getLianx_phone() {
		return lianx_phone;
	}

	public void setLianx_phone(String lianx_phone) {
		this.lianx_phone = lianx_phone;
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

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
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

	public String getYewdj_no_ck() {
		return yewdj_no_ck;
	}

	public void setYewdj_no_ck(String yewdj_no_ck) {
		this.yewdj_no_ck = yewdj_no_ck;
	}

	public String getNew_arrival() {
		return new_arrival;
	}

	public void setNew_arrival(String new_arrival) {
		this.new_arrival = new_arrival;
	}

	public String getPiz_no() {
		return piz_no;
	}

	public void setPiz_no(String piz_no) {
		this.piz_no = piz_no;
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

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getBaoz_danw() {
		return baoz_danw;
	}

	public void setBaoz_danw(String baoz_danw) {
		this.baoz_danw = baoz_danw;
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

	public String getZt001() {
		return zt001;
	}

	public void setZt001(String zt001) {
		this.zt001 = zt001;
	}

	public String getZt002() {
		return zt002;
	}

	public void setZt002(String zt002) {
		this.zt002 = zt002;
	}

	public String getZt003() {
		return zt003;
	}

	public void setZt003(String zt003) {
		this.zt003 = zt003;
	}
}
