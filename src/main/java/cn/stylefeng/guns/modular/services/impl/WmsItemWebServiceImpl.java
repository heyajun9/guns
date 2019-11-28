package cn.stylefeng.guns.modular.services.impl;

import cn.stylefeng.guns.modular.servlet.service.WmsItemService;
import cn.stylefeng.guns.modular.services.WmsItemWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author Admin
 * @create 2019-09-23 9:13
 * @desc 物料service实现
 **/
@WebService(serviceName = "wmsItemService", // 与接口中指定的name一致
        targetNamespace = "http://services.modular.guns.stylefeng.cn", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "cn.stylefeng.guns.modular.services.WmsItemWebService"// 接口地址
)
@Component
public class WmsItemWebServiceImpl implements WmsItemWebService {
    @Autowired
    private WmsItemService wmsItemService;

    @Override
    public void insert(String params) {
        //处理逻辑
    }
}
