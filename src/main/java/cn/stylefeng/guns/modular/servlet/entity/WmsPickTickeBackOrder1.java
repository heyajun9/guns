package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@SuppressWarnings("serial")
@TableName("inf_ck_sc_bill")
public class WmsPickTickeBackOrder1 implements Serializable {

	@TableId(value = "pickticketback_id", type = IdType.ID_WORKER)
	private Long pickticketback_id;
	@TableField("danj_no")
	private String danj_no             ; 
	@TableField("hanghao")
	private long hanghao             ; 
	@TableField("huoz_id")
	private String huoz_id             ; 
	@TableField("wlzx_code")
	private String wlzx_code           ; 
	@TableField("riqi_date")
	private String riqi_date           ; 
	@TableField("danw_id")
	private String danw_id             ; 
	@TableField("caoz_staff")
	private String caoz_staff          ; 
	@TableField("shangp_id")
	private String shangp_id           ; 
	@TableField("num")
	private long num                 ; 
	@TableField("lot")
	private String lot                 ;
	@TableField("shengchan_date")
	private String shengchan_date      ;
	@TableField("youx_date")
	private String youx_date           ; 
	@TableField("kuc_state")
	private String kuc_state           ; 
	@TableField("yewdj_no")
	private String yewdj_no            ; 
	@TableField("hanghao_yw")
	private long hanghao_yw          ; 
	@TableField("tiaom_num")
	private long tiaom_num           ; 
	@TableField("zt")
	private String zt                  ; 
	@TableField("error_msg")
	private String error_msg           ; 
	@TableField("yew_type")
	private String yew_type            ; 
	@TableField("carrier_id")
	private String carrier_id          ; 
	@TableField("yundan_no")
	private String yundan_no           ; 
	@TableField("fapiao_no")
	private String fapiao_no           ; 
	@TableField("maker")
	private String maker               ; 
	@TableField("chandi")
	private String chandi              ; 
	@TableField("num_erp")
	private long num_erp             ; 
	@TableField("kub")
	private String kub                 ; 
	@TableField("ck_mx")
	private String ck_mx               ;
	@TableField("cunc_condition")
	private String cunc_condition      ; 
	@TableField("from_djbh")
	private String from_djbh           ; 
	@TableField("from_hanghao")
	private String from_hanghao        ; 
	@TableField("danj_riqi")
	private String danj_riqi           ; 
	@TableField("baoz_danw")
	private String baoz_danw           ; 
	@TableField("yid_type")
	private String yid_type            ; 
	@TableField("gongchang")
	private String gongchang           ; 
	@TableField("kc_address")
	private String kc_address          ; 
	@TableField("to_address")
	private String to_address          ; 
	@TableField("from_address")
	private String from_address        ; 
	@TableField("to_lot")
	private String to_lot              ; 
	@TableField("tskc_flg")
	private String tskc_flg            ; 
	@TableField("gongys_no")
	private String gongys_no           ; 
	@TableField("dingd_no")
	private String dingd_no            ;
	@TableField("chengbzx_no")
	private String chengbzx_no         ; 
	@TableField("yldj_no")
	private String yldj_no             ; 
	@TableField("yl_hanghao")
	private String yl_hanghao          ; 
	@TableField("to_djbh")
	private String to_djbh             ; 
	@TableField("to_hanghao")
	private String to_hanghao          ;
	@TableField("produce_date")
	private String produce_date      ;
	@TableField("expire_date")
	private String expire_date      ;

	public Long getPickticketback_id() {
		return pickticketback_id;
	}

	public void setPickticketback_id(Long pickticketback_id) {
		this.pickticketback_id = pickticketback_id;
	}

	public String getDanj_no() {
		return danj_no;
	}

	public void setDanj_no(String danj_no) {
		this.danj_no = danj_no;
	}

	public long getHanghao() {
		return hanghao;
	}

	public void setHanghao(long hanghao) {
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

	public String getCaoz_staff() {
		return caoz_staff;
	}

	public void setCaoz_staff(String caoz_staff) {
		this.caoz_staff = caoz_staff;
	}

	public String getShangp_id() {
		return shangp_id;
	}

	public void setShangp_id(String shangp_id) {
		this.shangp_id = shangp_id;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getShengchan_date() {
		return shengchan_date;
	}

	public void setShengchan_date(String shengchan_date) {
		this.shengchan_date = shengchan_date;
	}

	public String getYoux_date() {
		return youx_date;
	}

	public void setYoux_date(String youx_date) {
		this.youx_date = youx_date;
	}

	public String getKuc_state() {
		return kuc_state;
	}

	public void setKuc_state(String kuc_state) {
		this.kuc_state = kuc_state;
	}

	public String getYewdj_no() {
		return yewdj_no;
	}

	public void setYewdj_no(String yewdj_no) {
		this.yewdj_no = yewdj_no;
	}

	public long getHanghao_yw() {
		return hanghao_yw;
	}

	public void setHanghao_yw(long hanghao_yw) {
		this.hanghao_yw = hanghao_yw;
	}

	public long getTiaom_num() {
		return tiaom_num;
	}

	public void setTiaom_num(long tiaom_num) {
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

	public String getYew_type() {
		return yew_type;
	}

	public void setYew_type(String yew_type) {
		this.yew_type = yew_type;
	}

	public String getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}

	public String getYundan_no() {
		return yundan_no;
	}

	public void setYundan_no(String yundan_no) {
		this.yundan_no = yundan_no;
	}

	public String getFapiao_no() {
		return fapiao_no;
	}

	public void setFapiao_no(String fapiao_no) {
		this.fapiao_no = fapiao_no;
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

	public long getNum_erp() {
		return num_erp;
	}

	public void setNum_erp(long num_erp) {
		this.num_erp = num_erp;
	}

	public String getKub() {
		return kub;
	}

	public void setKub(String kub) {
		this.kub = kub;
	}

	public String getCk_mx() {
		return ck_mx;
	}

	public void setCk_mx(String ck_mx) {
		this.ck_mx = ck_mx;
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
