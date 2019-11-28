/**
 * Copyright 2018-2020 stylefeng & heyajun (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.servlet.controller;

import cn.stylefeng.guns.core.common.annotion.InterfaceLog;
import cn.stylefeng.guns.core.common.constant.dictmap.ServletDict;
import cn.stylefeng.guns.core.util.MD5Util;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.guns.modular.servlet.entity.InsertMap;
import cn.stylefeng.guns.modular.servlet.entity.WmsInventory;
import cn.stylefeng.guns.modular.servlet.service.InsertMapService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * 库存对账接口
 *
 * @author heyajun
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/inventory")
public class WmsInventoryController extends BaseController {

    private static String PREFIX = "/modular/servlet/inventory/";

    private static final int batchSize=100;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(200));

    @Autowired
    private TableService tableService;

    @Autowired
    private InsertMapService insertMapService;

    @Autowired
    private InterService interService;

    /**
     * 跳转到接口列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "inventory.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/inventory_add")
    public String inventoryAdd() {
        return PREFIX + "inventory_add.html";
    }


    /**
     * 编辑
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @InterfaceLog(value = "编辑库存", key = "inventory", dict = ServletDict.class)
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody String params) {
        JSONObject json=JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        Collection<WmsInventory> inventorys=new ArrayList<>();
        try {
            for(int i=0;i<arry.size();i++){
                JSONObject object=arry.getJSONObject(i);
                WmsInventory inventory=new WmsInventory();
                inventorys.add(inventory);
            }
//            this.inventoryService.updateinventoryList(inventorys,batchSize);
            return ResponseData.success(0,"库存修改成功",inventorys.size()+"条");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error(1,"库存修改异常",e.getMessage());
        }
//        this.inventoryService.updateinventory(inventory);
    }

    /**
     * 新增库存接口(异步多线程)
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @InterfaceLog(value = "添加库存", key = "inventory", dict = ServletDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public  ResponseData  add(@RequestBody String params ) {
        System.out.println(Thread.currentThread().getName()+":start add "+System.currentTimeMillis());
        ResponseData responseData=new ResponseData();
        Callable<ResponseData> callable = new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                System.out.println(Thread.currentThread().getName()+":start callable add "+System.currentTimeMillis());
                JSONObject json = JSONObject.parseObject(params);
                String msgId = json.getString("msgid");//当前时间戳
                String partnerId = json.getString("partnerId");
                String sign = json.getString("sign");
                String operatorType = json.getString("operatorType");
                ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
                JSONArray arry = json.getJSONArray("data");//获取到data数据数组
                Collection<WmsInventory> inventorys = new ArrayList<>();
                //根据加密要求验证sign
                //partnerKey作为私秘钥
                List<Inter> inters = interService.selectByType("U");//U代表库存下传
                Inter inter = inters.get(0);//获取接口配置信息
                String partnerKey = inter.getPassword();//获取秘钥
                String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
                try {
                    if (!MD5Util.stringToMd5(signString).equals(sign)) {
//                System.out.println("sign:"+MD5Util.stringToMd5(signString));
                        List<TableOption> optionList = tableService.selectOptionByTable(inter.getDownTable());
                        for (int i = 0; i < arry.size(); i++) {
                            JSONObject object = arry.getJSONObject(i);//获取到实体json
                            Map<String, Object> objectMap = new HashMap<>();
                            if (optionList != null || optionList.size() > 0) {
                                for (TableOption tableOption : optionList) {
                                    String key = tableOption.getDownColumnName();
                                    objectMap.put(key, object.get(key));
                                }
                            }
                            InsertMap insertMap = new InsertMap();
                            insertMap.setTableName(inter.getDownTable());
                            insertMap.setParams(objectMap);
                            insertMapService.addMap(insertMap);

                        }
//                this.inventoryService.saveBatch(inventorys, batchSize);
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        return ResponseData.success(0, "库存新增成功", arry.size() + "条");
                    } else {
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        return ResponseData.error(1, "签名错误", sign);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                    return ResponseData.error(1, "库存新增异常", e.getMessage());
                }
            }
        };
        FutureTask<ResponseData> futuretask= (FutureTask<ResponseData>) executor.submit(callable);
        try {
            responseData=futuretask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":end all add "+System.currentTimeMillis());
        return responseData;
    }




}
