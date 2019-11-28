package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @category 客户基础数据
 * @author heyajun
 * @version 1.0
 * @created 2018-12-4
 */
@SuppressWarnings("serial")
@TableName("inf_jc_dwzl")
public class WmsOrganization implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "lius_no", type = IdType.ID_WORKER)
	private Long  lius_no;
   @TableField("danw_id")
	private String  danw_id;
   @TableField("wlzx_code")
	private String  wlzx_code;
   @TableField("huoz_id")
	private String  huoz_id;
   @TableField("danw_no")
	private String  danw_no;
   @TableField("danw_name")
	private String  danw_name;
   @TableField("zhuj_code")
	private String  zhuj_code;
   @TableField("beactive")
	private String  beactive;
   @TableField("gengx_time")
	private String  gengx_time;
   @TableField("danw_simplename")
	private String  danw_simplename;
   @TableField("yew_staff")
	private String  yew_staff;
   @TableField("address")
	private String  address;
   @TableField("shouh_phone")
	private String  shouh_phone;
   @TableField("shouh_staff")
	private String  shouh_staff;
   @TableField("postcode")
	private String  postcode;
   @TableField("zt")
	private String  zt;
   @TableField("yaojno")
	private String  yaojno;
   @TableField("xukz_no")
	private String  xukz_no         ;
   @TableField("xukz_yxqdate")
	private String   xukz_yxqdate   ;
   @TableField("yingyzz_yxqdate")
	private String   yingyzz_yxqdate;
   @TableField("zzjgdmz_no")
	private String   zzjgdmz_no     ;
   @TableField("zzjgdmz_flg")
	private String   zzjgdmz_flg    ;
   @TableField("zzjgdmz_yxqdate")
	private String   zzjgdmz_yxqdate;
   @TableField("gmp_no")
	private String   gmp_no         ;
   @TableField("gmp_flg")
	private String   gmp_flg        ;
   @TableField("gmp_yxqdate")
	private String   gmp_yxqdate    ;
   @TableField("gsp_no")
	private String   gsp_no         ;
   @TableField("gsp_flg")
	private String   gsp_flg        ;
   @TableField("gsp_yxqdate")
	private String   gsp_yxqdate    ;
   @TableField("wts_yxqdate")
	private String   wts_yxqdate    ;
   @TableField("xy_yxqdate")
	private String   xy_yxqdate     ;
   @TableField("swdjz_flg")
	private String   swdjz_flg      ;
   @TableField("yzym_flg")
	private String   yzym_flg       ;
   @TableField("khhxkz_flg")
	private String   khhxkz_flg     ;
   @TableField("yingyzz_flg")
	private String   yingyzz_flg    ;
   @TableField("yingyzz_no")
	private String   yingyzz_no     ;
   @TableField("city")
	private String   city   ;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getLius_no() {
		return lius_no;
	}

	public void setLius_no(Long lius_no) {
		this.lius_no = lius_no;
	}

	public String getDanw_id() {
		return danw_id;
	}

	public void setDanw_id(String danw_id) {
		this.danw_id = danw_id;
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

	public String getDanw_no() {
		return danw_no;
	}

	public void setDanw_no(String danw_no) {
		this.danw_no = danw_no;
	}

	public String getDanw_name() {
		return danw_name;
	}

	public void setDanw_name(String danw_name) {
		this.danw_name = danw_name;
	}

	public String getZhuj_code() {
		return zhuj_code;
	}

	public void setZhuj_code(String zhuj_code) {
		this.zhuj_code = zhuj_code;
	}

	public String getBeactive() {
		return beactive;
	}

	public void setBeactive(String beactive) {
		this.beactive = beactive;
	}

	public String getGengx_time() {
		return gengx_time;
	}

	public void setGengx_time(String gengx_time) {
		this.gengx_time = gengx_time;
	}

	public String getDanw_simplename() {
		return danw_simplename;
	}

	public void setDanw_simplename(String danw_simplename) {
		this.danw_simplename = danw_simplename;
	}

	public String getYew_staff() {
		return yew_staff;
	}

	public void setYew_staff(String yew_staff) {
		this.yew_staff = yew_staff;
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

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getYaojno() {
		return yaojno;
	}

	public void setYaojno(String yaojno) {
		this.yaojno = yaojno;
	}

	public String getXukz_no() {
		return xukz_no;
	}

	public void setXukz_no(String xukz_no) {
		this.xukz_no = xukz_no;
	}

	public String getXukz_yxqdate() {
		return xukz_yxqdate;
	}

	public void setXukz_yxqdate(String xukz_yxqdate) {
		this.xukz_yxqdate = xukz_yxqdate;
	}

	public String getYingyzz_yxqdate() {
		return yingyzz_yxqdate;
	}

	public void setYingyzz_yxqdate(String yingyzz_yxqdate) {
		this.yingyzz_yxqdate = yingyzz_yxqdate;
	}

	public String getZzjgdmz_no() {
		return zzjgdmz_no;
	}

	public void setZzjgdmz_no(String zzjgdmz_no) {
		this.zzjgdmz_no = zzjgdmz_no;
	}

	public String getZzjgdmz_flg() {
		return zzjgdmz_flg;
	}

	public void setZzjgdmz_flg(String zzjgdmz_flg) {
		this.zzjgdmz_flg = zzjgdmz_flg;
	}

	public String getZzjgdmz_yxqdate() {
		return zzjgdmz_yxqdate;
	}

	public void setZzjgdmz_yxqdate(String zzjgdmz_yxqdate) {
		this.zzjgdmz_yxqdate = zzjgdmz_yxqdate;
	}

	public String getGmp_no() {
		return gmp_no;
	}

	public void setGmp_no(String gmp_no) {
		this.gmp_no = gmp_no;
	}

	public String getGmp_flg() {
		return gmp_flg;
	}

	public void setGmp_flg(String gmp_flg) {
		this.gmp_flg = gmp_flg;
	}

	public String getGmp_yxqdate() {
		return gmp_yxqdate;
	}

	public void setGmp_yxqdate(String gmp_yxqdate) {
		this.gmp_yxqdate = gmp_yxqdate;
	}

	public String getGsp_no() {
		return gsp_no;
	}

	public void setGsp_no(String gsp_no) {
		this.gsp_no = gsp_no;
	}

	public String getGsp_flg() {
		return gsp_flg;
	}

	public void setGsp_flg(String gsp_flg) {
		this.gsp_flg = gsp_flg;
	}

	public String getGsp_yxqdate() {
		return gsp_yxqdate;
	}

	public void setGsp_yxqdate(String gsp_yxqdate) {
		this.gsp_yxqdate = gsp_yxqdate;
	}

	public String getWts_yxqdate() {
		return wts_yxqdate;
	}

	public void setWts_yxqdate(String wts_yxqdate) {
		this.wts_yxqdate = wts_yxqdate;
	}

	public String getXy_yxqdate() {
		return xy_yxqdate;
	}

	public void setXy_yxqdate(String xy_yxqdate) {
		this.xy_yxqdate = xy_yxqdate;
	}

	public String getSwdjz_flg() {
		return swdjz_flg;
	}

	public void setSwdjz_flg(String swdjz_flg) {
		this.swdjz_flg = swdjz_flg;
	}

	public String getYzym_flg() {
		return yzym_flg;
	}

	public void setYzym_flg(String yzym_flg) {
		this.yzym_flg = yzym_flg;
	}

	public String getKhhxkz_flg() {
		return khhxkz_flg;
	}

	public void setKhhxkz_flg(String khhxkz_flg) {
		this.khhxkz_flg = khhxkz_flg;
	}

	public String getYingyzz_flg() {
		return yingyzz_flg;
	}

	public void setYingyzz_flg(String yingyzz_flg) {
		this.yingyzz_flg = yingyzz_flg;
	}

	public String getYingyzz_no() {
		return yingyzz_no;
	}

	public void setYingyzz_no(String yingyzz_no) {
		this.yingyzz_no = yingyzz_no;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}