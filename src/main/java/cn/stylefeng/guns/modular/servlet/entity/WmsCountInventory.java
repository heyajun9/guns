package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 盘点上传表
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-01
 */
@TableName("inf_pd_sc_bill")
public class WmsCountInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("danj_no")
  private String  danj_no          ;
    @TableField("hanghao")
  private Long  hanghao           ;
    @TableField("wlzx_code")
  private String  wlzx_code         ;
    @TableField("huoz_id")
  private String  huoz_id           ;
    @TableField("riqi_char")
  private String  riqi_char         ;
    @TableField("caoz_staff")
  private String  caoz_staff        ;
    @TableField("shangp_id")
  private String  shangp_id         ;
    @TableField("yingk_num")
  private Double  yingk_num         ;
    @TableField("lot")
  private String  lot               ;
    @TableField("kuc_state")
  private String  kuc_state         ;
    @TableField("youx_char")
  private String  youx_char         ;
    @TableField("shengc_char")
  private String  shengc_char       ;
    @TableField("yuanyin")
  private String  yuanyin           ;
    @TableField("zt")
  private String  zt                ;
    @TableField("error_msg")
  private String  error_msg         ;
    @TableField("pd_type")
  private String  pd_type           ;
    @TableField("to_lot")
  private String  to_lot            ;
    @TableField("to_youx_date")
  private Date  to_youx_date      ;
    @TableField("to_shengchan_date")
  private Date to_shengchan_date ;
    @TableField("kub")
  private String  kub               ;
    @TableField("pd_num")
  private Double  pd_num            ;
    @TableField("kc_num")
  private Double  kc_num            ;

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

    public String getRiqi_char() {
        return riqi_char;
    }

    public void setRiqi_char(String riqi_char) {
        this.riqi_char = riqi_char;
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

    public Double getYingk_num() {
        return yingk_num;
    }

    public void setYingk_num(Double yingk_num) {
        this.yingk_num = yingk_num;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getKuc_state() {
        return kuc_state;
    }

    public void setKuc_state(String kuc_state) {
        this.kuc_state = kuc_state;
    }

    public String getYoux_char() {
        return youx_char;
    }

    public void setYoux_char(String youx_char) {
        this.youx_char = youx_char;
    }

    public String getShengc_char() {
        return shengc_char;
    }

    public void setShengc_char(String shengc_char) {
        this.shengc_char = shengc_char;
    }

    public String getYuanyin() {
        return yuanyin;
    }

    public void setYuanyin(String yuanyin) {
        this.yuanyin = yuanyin;
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

    public String getPd_type() {
        return pd_type;
    }

    public void setPd_type(String pd_type) {
        this.pd_type = pd_type;
    }

    public String getTo_lot() {
        return to_lot;
    }

    public void setTo_lot(String to_lot) {
        this.to_lot = to_lot;
    }

    public Date getTo_youx_date() {
        return to_youx_date;
    }

    public void setTo_youx_date(Date to_youx_date) {
        this.to_youx_date = to_youx_date;
    }

    public Date getTo_shengchan_date() {
        return to_shengchan_date;
    }

    public void setTo_shengchan_date(Date to_shengchan_date) {
        this.to_shengchan_date = to_shengchan_date;
    }

    public String getKub() {
        return kub;
    }

    public void setKub(String kub) {
        this.kub = kub;
    }

    public Double getPd_num() {
        return pd_num;
    }

    public void setPd_num(Double pd_num) {
        this.pd_num = pd_num;
    }

    public Double getKc_num() {
        return kc_num;
    }

    public void setKc_num(Double kc_num) {
        this.kc_num = kc_num;
    }
}

