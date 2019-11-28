package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 采购退回出库单
 */
@SuppressWarnings("serial")
@TableName("INF_CK_GTTZD_BILL")
public class WmsPurcharsePickticketOrder implements Serializable {

	@TableField("danj_no")
 	 private String danj_no     ;
	@TableField("hanghao")
	 private Long hanghao     ;
	@TableField("wlzx_code")
	 private String wlzx_code   ;
	@TableField("huoz_id")
	 private String huoz_id     ;
	@TableField("danw_id")
	 private String danw_id     ;
	@TableField("riqi_char")
	 private String riqi_char   ;
	@TableField("caigou_staff")
	 private String caigou_staff;
	@TableField("tuih_staff")
	 private String tuih_staff  ;
	@TableField("tuih_reason")
	 private String tuih_reason ;
	@TableField("shangp_id")
	 private String shangp_id   ;
	@TableField("lot")
	 private String lot         ;
	@TableField("jihua_num")
	 private Long jihua_num   ;
	@TableField("address")
	 private String address     ;
	@TableField("shouh_phone")
	 private String shouh_phone ;
	@TableField("shouh_staff")
	 private String shouh_staff ;
	@TableField("postcode")
	 private String postcode    ;
	@TableField("peis_notes")
	 private String peis_notes  ;
	@TableField("tih_way")
	 private String tih_way     ;
	@TableField("tiaom_num")
	 private Long tiaom_num   ;
	@TableField("price")
	 private Double price       ;
	@TableField("jifen")
	 private Long jifen       ;
	@TableField("amount")
	 private Long amount      ;
	@TableField("tax_amount")
	 private Long tax_amount  ;
	@TableField("jies_price")
	 private Double jies_price  ;
	@TableField("jies_amount")
	 private Double jies_amount ;
	@TableField("notes")
	 private String notes       ;
	@TableField("zt")
	 private String zt          ;
	@TableField("yn_guid")
	 private String yn_guid     ;
	@TableField("kub")
	 private String kub         ;

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

	public String getDanw_id() {
		return danw_id;
	}

	public void setDanw_id(String danw_id) {
		this.danw_id = danw_id;
	}

	public String getRiqi_char() {
		return riqi_char;
	}

	public void setRiqi_char(String riqi_char) {
		this.riqi_char = riqi_char;
	}

	public String getCaigou_staff() {
		return caigou_staff;
	}

	public void setCaigou_staff(String caigou_staff) {
		this.caigou_staff = caigou_staff;
	}

	public String getTuih_staff() {
		return tuih_staff;
	}

	public void setTuih_staff(String tuih_staff) {
		this.tuih_staff = tuih_staff;
	}

	public String getTuih_reason() {
		return tuih_reason;
	}

	public void setTuih_reason(String tuih_reason) {
		this.tuih_reason = tuih_reason;
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

	public Long getJihua_num() {
		return jihua_num;
	}

	public void setJihua_num(Long jihua_num) {
		this.jihua_num = jihua_num;
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

	public String getTih_way() {
		return tih_way;
	}

	public void setTih_way(String tih_way) {
		this.tih_way = tih_way;
	}

	public Long getTiaom_num() {
		return tiaom_num;
	}

	public void setTiaom_num(Long tiaom_num) {
		this.tiaom_num = tiaom_num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getJifen() {
		return jifen;
	}

	public void setJifen(Long jifen) {
		this.jifen = jifen;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(Long tax_amount) {
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getYn_guid() {
		return yn_guid;
	}

	public void setYn_guid(String yn_guid) {
		this.yn_guid = yn_guid;
	}

	public String getKub() {
		return kub;
	}

	public void setKub(String kub) {
		this.kub = kub;
	}
}
