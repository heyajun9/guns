package cn.stylefeng.guns.modular.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Admin
 * @create 2019-09-23 9:08
 * @desc 物料webservice服务发布
 **/
@WebService(name="wmsItemService",targetNamespace = "http://services.modular.guns.stylefeng.cn")
public interface WmsItemWebService {
    @WebMethod
    public void insert(@WebParam(name="params") String params);
}
