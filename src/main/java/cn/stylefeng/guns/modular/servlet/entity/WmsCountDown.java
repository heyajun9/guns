package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 盘点下传表
 * </p>
 *
 * @author heyajun
 * @since 2019-04-01
 */
@TableName("inf_pd_xc_bill")
public class WmsCountDown implements Serializable {

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
    @TableField("pd_num")
  private Double  pd_num            ;
    @TableField("kc_num")
  private Double  kc_num            ;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

