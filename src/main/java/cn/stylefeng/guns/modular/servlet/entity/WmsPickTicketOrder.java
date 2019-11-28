package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@SuppressWarnings("serial")
@TableName("inf_ck_kpd_bill")
public class WmsPickTicketOrder implements Serializable {

	@TableId(value = "pickticket", type = IdType.ID_WORKER)
	private Long pickticket;
	@TableField( "danj_no")
	private String danj_no          ;
	@TableField( "hanghao")
	private String hanghao          ;
	@TableField( "wlzx_code")
	private String wlzx_code        ;
	@TableField( "huoz_id")
	private String huoz_id          ;
	@TableField( "danw_id")
	private String danw_id          ;
	@TableField( "riqi_date")
	private String riqi_date        ;
	@TableField( "yew_staff")
	private String yew_staff        ;
	@TableField( "tih_way")
	private String tih_way          ;
	@TableField( "xiaos_type")
	private String xiaos_type       ;
	@TableField( "fah_type")
	private String fah_type="1"      ;
	@TableField( "shangp_id")
	private String shangp_id        ;
	@TableField( "num")
	private Double num              ;
	@TableField( "lot_request")
	private String lot_request      ;
	@TableField( "note")
	private String note             ;
	@TableField( "ckd_type")
	private String ckd_type         ;
	@TableField( "guke_name")
	private String guke_name        ;
	@TableField( "guke_no")
	private String guke_no          ;
	@TableField( "address_no")
	private String address_no       ;
	@TableField( "address")
	private String address          ;
	@TableField( "shouh_phone")
	private String shouh_phone      ;
	@TableField( "shouh_staff")
	private String shouh_staff      ;
	@TableField( "postcode")
	private String postcode         ;
	@TableField( "peis_notes")
	private String peis_notes       ;
	@TableField( "daoh_time")
	private String daoh_time        ;
	@TableField( "jiaj_flg")
	private String jiaj_flg         ;
	@TableField( "tiaom_num")
	private Double tiaom_num        ;
	@TableField( "price")
	private Double price            ;
	@TableField( "jifen")
	private Double jifen            ;
	@TableField( "amount")
	private Double amount           ;
	@TableField( "tax_amount")
	private Double tax_amount       ;
	@TableField( "jies_price")
	private Double jies_price       ;
	@TableField( "jies_amount")
	private Double jies_amount      ;
	@TableField( "zt")
	private String zt               ;
	@TableField( "carrier_name")
	private String carrier_name     ;
	@TableField( "carrier_id")
	private String carrier_id       ;
	@TableField( "danj_no_wsdd")
	private String danj_no_wsdd     ;
	@TableField( "shuip_type")
	private String shuip_type       ;
	@TableField( "platform_username")
	private String platform_username;
	@TableField( "payer_id")
	private String payer_id         ;
	@TableField( "payer_name")
	private String payer_name       ;
	@TableField( "danj_no_sap")
	private String danj_no_sap      ;
	@TableField( "bills_type")
	private String bills_type       ;
	@TableField( "chongh_erp_flg")
	private String chongh_erp_flg   ;
	@TableField( "province")
	private String province         ;
	@TableField( "city")
	private String city             ;
	@TableField( "area")
	private String area             ;
	@TableField( "strategyno")
	private String strategyno	;
	@TableField( "baoz_danw")
	private String baoz_danw	;
	 @TableField( "yid_type")
	private String yid_type         ;
	 @TableField( "gongchang")
	private String gongchang        ;
	 @TableField( "kc_address")
	private String kc_address       ;
	 @TableField( "to_address")
	private String to_address       ;
	 @TableField( "from_address")
	private String from_address     ;
	 @TableField( "to_lot")
	private String to_lot           ;
	 @TableField( "tskc_flg")
	private String tskc_flg         ;
	 @TableField( "gongys_no")
	private String gongys_no        ;
	 @TableField( "dingd_no")
	private String dingd_no         ;
	 @TableField( "chengbzx_no")
	private String chengbzx_no      ;
	 @TableField( "yldj_no")
	private String yldj_no          ;
	 @TableField( "yl_hanghao")
	private String yl_hanghao       ;
	 @TableField( "to_djbh")
	private String to_djbh          ;
	 @TableField( "to_hanghao")
	private String to_hanghao       ;
	 @TableField( "zt001")
	private String zt001            ;
	 @TableField( "zt002")
	private String zt002            ;
	 @TableField( "zt003")
	private String zt003            ;
	 @TableField( "produce_date")
	private String produce_date      ;
	 @TableField( "expire_date")
	private String expire_date      ;

	public Long getPickticket() {
		return pickticket;
	}

	public void setPickticket(Long pickticket) {
		this.pickticket = pickticket;
	}

	public String getDanj_no() {
		return danj_no;
	}

	public void setDanj_no(String danj_no) {
		this.danj_no = danj_no;
	}

	public String getHanghao() {
		return hanghao;
	}

	public void setHanghao(String hanghao) {
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

	public String getDanw_id() {
		return danw_id;
	}

	public void setDanw_id(String danw_id) {
		this.danw_id = danw_id;
	}

	public String getRiqi_date() {
		return riqi_date;
	}

	public void setRiqi_date(String riqi_date) {
		this.riqi_date = riqi_date;
	}

	public String getYew_staff() {
		return yew_staff;
	}

	public void setYew_staff(String yew_staff) {
		this.yew_staff = yew_staff;
	}

	public String getTih_way() {
		return tih_way;
	}

	public void setTih_way(String tih_way) {
		this.tih_way = tih_way;
	}

	public String getXiaos_type() {
		return xiaos_type;
	}

	public void setXiaos_type(String xiaos_type) {
		this.xiaos_type = xiaos_type;
	}

	public String getFah_type() {
		return fah_type;
	}

	public void setFah_type(String fah_type) {
		this.fah_type = fah_type;
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

	public String getLot_request() {
		return lot_request;
	}

	public void setLot_request(String lot_request) {
		this.lot_request = lot_request;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCkd_type() {
		return ckd_type;
	}

	public void setCkd_type(String ckd_type) {
		this.ckd_type = ckd_type;
	}

	public String getGuke_name() {
		return guke_name;
	}

	public void setGuke_name(String guke_name) {
		this.guke_name = guke_name;
	}

	public String getGuke_no() {
		return guke_no;
	}

	public void setGuke_no(String guke_no) {
		this.guke_no = guke_no;
	}

	public String getAddress_no() {
		return address_no;
	}

	public void setAddress_no(String address_no) {
		this.address_no = address_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShouh_phone() {
		return shouh_phone;
	}

	public void setShouh_phone(String shouh_phone) {
		this.shouh_phone = shouh_phone;
	}

	public String getShouh_staff() {
		return shouh_staff;
	}

	public void setShouh_staff(String shouh_staff) {
		this.shouh_staff = shouh_staff;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPeis_notes() {
		return peis_notes;
	}

	public void setPeis_notes(String peis_notes) {
		this.peis_notes = peis_notes;
	}

	public String getDaoh_time() {
		return daoh_time;
	}

	public void setDaoh_time(String daoh_time) {
		this.daoh_time = daoh_time;
	}

	public String getJiaj_flg() {
		return jiaj_flg;
	}

	public void setJiaj_flg(String jiaj_flg) {
		this.jiaj_flg = jiaj_flg;
	}

	public Double getTiaom_num() {
		return tiaom_num;
	}

	public void setTiaom_num(Double tiaom_num) {
		this.tiaom_num = tiaom_num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getJifen() {
		return jifen;
	}

	public void setJifen(Double jifen) {
		this.jifen = jifen;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(Double tax_amount) {
		this.tax_amount = tax_amount;
	}

	public Double getJies_price() {
		return jies_price;
	}

	public void setJies_price(Double jies_price) {
		this.jies_price = jies_price;
	}

	public Double getJies_amount() {
		return jies_amount;
	}

	public void setJies_amount(Double jies_amount) {
		this.jies_amount = jies_amount;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCarrier_name() {
		return carrier_name;
	}

	public void setCarrier_name(String carrier_name) {
		this.carrier_name = carrier_name;
	}

	public String getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}

	public String getDanj_no_wsdd() {
		return danj_no_wsdd;
	}

	public void setDanj_no_wsdd(String danj_no_wsdd) {
		this.danj_no_wsdd = danj_no_wsdd;
	}

	public String getShuip_type() {
		return shuip_type;
	}

	public void setShuip_type(String shuip_type) {
		this.shuip_type = shuip_type;
	}

	public String getPlatform_username() {
		return platform_username;
	}

	public void setPlatform_username(String platform_username) {
		this.platform_username = platform_username;
	}

	public String getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
	}

	public String getPayer_name() {
		return payer_name;
	}

	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}

	public String getDanj_no_sap() {
		return danj_no_sap;
	}

	public void setDanj_no_sap(String danj_no_sap) {
		this.danj_no_sap = danj_no_sap;
	}

	public String getBills_type() {
		return bills_type;
	}

	public void setBills_type(String bills_type) {
		this.bills_type = bills_type;
	}

	public String getChongh_erp_flg() {
		return chongh_erp_flg;
	}

	public void setChongh_erp_flg(String chongh_erp_flg) {
		this.chongh_erp_flg = chongh_erp_flg;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStrategyno() {
		return strategyno;
	}

	public void setStrategyno(String strategyno) {
		this.strategyno = strategyno;
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
