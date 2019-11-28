package cn.stylefeng.guns.config;

import cn.stylefeng.guns.modular.services.WmsItemWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


/**
 * @author Admin
 * @create 2019-09-23 9:18
 * @desc web服务配置
 **/
@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    WmsItemWebService wmsItemWebService;

    /** JAX-WS **/
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, wmsItemWebService);
        endpoint.publish("/wmsItemService");
        return endpoint;
    }

}
