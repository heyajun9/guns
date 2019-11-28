package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("kc_spph_sap")
public class WmsInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("huoz_id")
    private String huoz_id;
    @TableField("wlzx_code")
    private String wlzx_code;
     @TableField("shangp_no")
    private String shangp_no;
     @TableField("lot")
    private String lot;
     @TableField("num")
    private Double num;
     @TableField("baoz_danw")
    private String baoz_danw;
     @TableField("create_time")
    private Date create_time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getShangp_no() {
        return shangp_no;
    }

    public void setShangp_no(String shangp_no) {
        this.shangp_no = shangp_no;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getBaoz_danw() {
        return baoz_danw;
    }

    public void setBaoz_danw(String baoz_danw) {
        this.baoz_danw = baoz_danw;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}

