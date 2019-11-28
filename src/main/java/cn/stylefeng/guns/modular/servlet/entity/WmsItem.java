package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 物料表
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-01
 */
@TableName("inf_jc_spzl")
public class WmsItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "lius_no", type = IdType.ID_WORKER)
    private Long   lius_no;
     @TableField("shangp_id")
    private String shangp_id;
     @TableField("shangp_no")
    private String shangp_no;
     @TableField("wlzx_code")
    private String wlzx_code;
     @TableField("huoz_id")
    private String huoz_id;
     @TableField("chinese_name")
    private String chinese_name;
     @TableField("zhuj_code")
    private String zhuj_code;
     @TableField("yaop_guig")
    private String yaop_guig;
     @TableField("maker")
    private String maker;
     @TableField("chandi")
    private String chandi;
     @TableField("baoz_num")
    private long baoz_num;
     @TableField("baoz_danw")
    private String baoz_danw;
     @TableField("chaif_lid")
    private String chaif_lid;
     @TableField("kaipdw_min")
    private long kaipdw_min;
     @TableField("yaop_category")
    private String yaop_category;
     @TableField("cunc_condition")
    private String cunc_condition;
     @TableField("beactive")
    private String beactive;
     @TableField("zhongbz")
    private long zhongbz;
     @TableField("jixing")
    private String jixing;
     @TableField("tongy_name")
    private String tongy_name;
     @TableField("lot_flg")
    private String lot_flg;//是否管理批号
     @TableField("zengp_flg")
    private String zengp_flg;
     @TableField("jiang_flg")
    private String jiang_flg;
     @TableField("fangc_flg")
    private String fangc_flg;
     @TableField("tgyp_flg")
    private String tgyp_flg;
     @TableField("jink_flg")
    private String jink_flg;
     @TableField("yis_flg")
    private String yis_flg;
     @TableField("gzyp_flg")
    private String gzyp_flg;
     @TableField("yxp_flg")
    private String yxp_flg;
     @TableField("caigou_staff")
    private String caigou_staff ;
     @TableField("ypyh_type")
    private String  ypyh_type   ;
     @TableField("geiy_way")
    private String  geiy_way    ;
     @TableField("yaop_xingz")
    private String  yaop_xingz  ;
     @TableField("shiyz")
    private String  shiyz       ;
     @TableField("gengx_time")
    private String  gengx_time  ;
     @TableField("yp_flg")
    private String  yp_flg      ;
     @TableField("zt")
    private String  zt          ;
     @TableField("piz_no")
    private String  piz_no      ;
     @TableField("merge_flg")
    private String  merge_flg   ;
     @TableField("english_name")
    private String  english_name;
     @TableField("sf_zdkz")
    private String  sf_zdkz     ;
     @TableField("sf_tdyp")
    private String  sf_tdyp     ;
     @TableField("shouy_flg")
    private String  shouy_flg   ;
     @TableField("sf_zy")
    private String  sf_zy       ;
     @TableField("medicinespecicalcontrol")
    private String medicinespecicalcontrol;
     @TableField("barcode")
    private String barcode ;
     @TableField("weight")
    private String weight;
     @TableField("height")
    private String height;
     @TableField("length")
    private String length;
     @TableField("width")
    private String width;
     @TableField("youxq_flg")
    private String youxq_flg;
     @TableField("forbid_days")
    private String forbid_days;
     @TableField("commodity")
    private String commodity;
     @TableField("demurrage_day")
    private String demurrage_day;
     @TableField("foreboed_days")
    private String foreboed_days;
     @TableField("ban_days")
    private String ban_days;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getLius_no() {
        return lius_no;
    }

    public void setLius_no(Long lius_no) {
        this.lius_no = lius_no;
    }

    public String getShangp_id() {
        return shangp_id;
    }

    public void setShangp_id(String shangp_id) {
        this.shangp_id = shangp_id;
    }

    public String getShangp_no() {
        return shangp_no;
    }

    public void setShangp_no(String shangp_no) {
        this.shangp_no = shangp_no;
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

    public String getChinese_name() {
        return chinese_name;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }

    public String getZhuj_code() {
        return zhuj_code;
    }

    public void setZhuj_code(String zhuj_code) {
        this.zhuj_code = zhuj_code;
    }

    public String getYaop_guig() {
        return yaop_guig;
    }

    public void setYaop_guig(String yaop_guig) {
        this.yaop_guig = yaop_guig;
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

    public long getBaoz_num() {
        return baoz_num;
    }

    public void setBaoz_num(long baoz_num) {
        this.baoz_num = baoz_num;
    }

    public String getBaoz_danw() {
        return baoz_danw;
    }

    public void setBaoz_danw(String baoz_danw) {
        this.baoz_danw = baoz_danw;
    }

    public String getChaif_lid() {
        return chaif_lid;
    }

    public void setChaif_lid(String chaif_lid) {
        this.chaif_lid = chaif_lid;
    }

    public long getKaipdw_min() {
        return kaipdw_min;
    }

    public void setKaipdw_min(long kaipdw_min) {
        this.kaipdw_min = kaipdw_min;
    }

    public String getYaop_category() {
        return yaop_category;
    }

    public void setYaop_category(String yaop_category) {
        this.yaop_category = yaop_category;
    }

    public String getCunc_condition() {
        return cunc_condition;
    }

    public void setCunc_condition(String cunc_condition) {
        this.cunc_condition = cunc_condition;
    }

    public String getBeactive() {
        return beactive;
    }

    public void setBeactive(String beactive) {
        this.beactive = beactive;
    }

    public long getZhongbz() {
        return zhongbz;
    }

    public void setZhongbz(long zhongbz) {
        this.zhongbz = zhongbz;
    }

    public String getJixing() {
        return jixing;
    }

    public void setJixing(String jixing) {
        this.jixing = jixing;
    }

    public String getTongy_name() {
        return tongy_name;
    }

    public void setTongy_name(String tongy_name) {
        this.tongy_name = tongy_name;
    }

    public String getLot_flg() {
        return lot_flg;
    }

    public void setLot_flg(String lot_flg) {
        this.lot_flg = lot_flg;
    }

    public String getZengp_flg() {
        return zengp_flg;
    }

    public void setZengp_flg(String zengp_flg) {
        this.zengp_flg = zengp_flg;
    }

    public String getJiang_flg() {
        return jiang_flg;
    }

    public void setJiang_flg(String jiang_flg) {
        this.jiang_flg = jiang_flg;
    }

    public String getFangc_flg() {
        return fangc_flg;
    }

    public void setFangc_flg(String fangc_flg) {
        this.fangc_flg = fangc_flg;
    }

    public String getTgyp_flg() {
        return tgyp_flg;
    }

    public void setTgyp_flg(String tgyp_flg) {
        this.tgyp_flg = tgyp_flg;
    }

    public String getJink_flg() {
        return jink_flg;
    }

    public void setJink_flg(String jink_flg) {
        this.jink_flg = jink_flg;
    }

    public String getYis_flg() {
        return yis_flg;
    }

    public void setYis_flg(String yis_flg) {
        this.yis_flg = yis_flg;
    }

    public String getGzyp_flg() {
        return gzyp_flg;
    }

    public void setGzyp_flg(String gzyp_flg) {
        this.gzyp_flg = gzyp_flg;
    }

    public String getYxp_flg() {
        return yxp_flg;
    }

    public void setYxp_flg(String yxp_flg) {
        this.yxp_flg = yxp_flg;
    }

    public String getCaigou_staff() {
        return caigou_staff;
    }

    public void setCaigou_staff(String caigou_staff) {
        this.caigou_staff = caigou_staff;
    }

    public String getYpyh_type() {
        return ypyh_type;
    }

    public void setYpyh_type(String ypyh_type) {
        this.ypyh_type = ypyh_type;
    }

    public String getGeiy_way() {
        return geiy_way;
    }

    public void setGeiy_way(String geiy_way) {
        this.geiy_way = geiy_way;
    }

    public String getYaop_xingz() {
        return yaop_xingz;
    }

    public void setYaop_xingz(String yaop_xingz) {
        this.yaop_xingz = yaop_xingz;
    }

    public String getShiyz() {
        return shiyz;
    }

    public void setShiyz(String shiyz) {
        this.shiyz = shiyz;
    }

    public String getGengx_time() {
        return gengx_time;
    }

    public void setGengx_time(String gengx_time) {
        this.gengx_time = gengx_time;
    }

    public String getYp_flg() {
        return yp_flg;
    }

    public void setYp_flg(String yp_flg) {
        this.yp_flg = yp_flg;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getPiz_no() {
        return piz_no;
    }

    public void setPiz_no(String piz_no) {
        this.piz_no = piz_no;
    }

    public String getMerge_flg() {
        return merge_flg;
    }

    public void setMerge_flg(String merge_flg) {
        this.merge_flg = merge_flg;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getSf_zdkz() {
        return sf_zdkz;
    }

    public void setSf_zdkz(String sf_zdkz) {
        this.sf_zdkz = sf_zdkz;
    }

    public String getSf_tdyp() {
        return sf_tdyp;
    }

    public void setSf_tdyp(String sf_tdyp) {
        this.sf_tdyp = sf_tdyp;
    }

    public String getShouy_flg() {
        return shouy_flg;
    }

    public void setShouy_flg(String shouy_flg) {
        this.shouy_flg = shouy_flg;
    }

    public String getSf_zy() {
        return sf_zy;
    }

    public void setSf_zy(String sf_zy) {
        this.sf_zy = sf_zy;
    }

    public String getMedicinespecicalcontrol() {
        return medicinespecicalcontrol;
    }

    public void setMedicinespecicalcontrol(String medicinespecicalcontrol) {
        this.medicinespecicalcontrol = medicinespecicalcontrol;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getYouxq_flg() {
        return youxq_flg;
    }

    public void setYouxq_flg(String youxq_flg) {
        this.youxq_flg = youxq_flg;
    }

    public String getForbid_days() {
        return forbid_days;
    }

    public void setForbid_days(String forbid_days) {
        this.forbid_days = forbid_days;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getDemurrage_day() {
        return demurrage_day;
    }

    public void setDemurrage_day(String demurrage_day) {
        this.demurrage_day = demurrage_day;
    }

    public String getForeboed_days() {
        return foreboed_days;
    }

    public void setForeboed_days(String foreboed_days) {
        this.foreboed_days = foreboed_days;
    }

    public String getBan_days() {
        return ban_days;
    }

    public void setBan_days(String ban_days) {
        this.ban_days = ban_days;
    }
}

