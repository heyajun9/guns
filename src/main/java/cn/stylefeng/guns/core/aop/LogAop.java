/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
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
package cn.stylefeng.guns.core.aop;

import cn.stylefeng.guns.core.common.annotion.InterfaceLog;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;
import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.Contrast;
import cn.stylefeng.roses.core.util.HttpContext;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 日志记录
 *
 * @author heyajun
 * @date 2016年12月6日 下午8:48:30
 */
@Aspect
@Component
public class LogAop {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(cn.stylefeng.guns.core.common.annotion.BussinessLog)")
    public void cutService() {
    }

    /**
     * 下传日志
     */
    @Pointcut(value = "@annotation(cn.stylefeng.guns.core.common.annotion.InterfaceLog)")
    public void cutService1() {
    }
    /**
     * 上传日志
     */
    @Pointcut(value = "@annotation(cn.stylefeng.guns.core.common.annotion.QuartzLog)")
    public void cutService2() {
    }

//    @Around("cutService2()")
//    public Object recordQuartzLog(ProceedingJoinPoint point) throws Throwable {
//
//        //先执行业务
//        Object result = point.proceed();
//
//        try {
//            handle2(point);
//        } catch (Exception e) {
//            log.error("日志记录出错!", e);
//        }
//
//        return result;
//    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    @AfterReturning(value="cutService1()",returning = "rtv")
    public void recordInterfaceLogReturn(JoinPoint point, Object rtv) throws Throwable {

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();
        System.out.println("methodName:"+methodName);

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            //如果用户为空，默认写入admin用户
            return;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        InterfaceLog annotation = currentMethod.getAnnotation(InterfaceLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改,比对变化
        String msg="";
        Boolean sucess = false;
        if (bussinessName.contains("修改") || bussinessName.contains("编辑")) {
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpContext.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            JSONObject json= (JSONObject) JSONObject.toJSON(rtv);
            System.out.println("返回结果："+rtv.toString());
            sucess= (Boolean) json.get("success");
//            SuccessResponseData successResponseData=ResponseData.success(rtv);
//            sucess=successResponseData.getSuccess();
            msg+="request:"+sb.toString();
            msg+="===================================";
            msg+="response:"+JSONObject.toJSON(rtv);


        }
            if (!sucess) {
                //记录下传日志(失败)
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(user.getId(), bussinessName, className, methodName, msg));
            } else {
                //记录下传日志（成功）
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(user.getId(), bussinessName, className, methodName, msg));
            }
    }

    @AfterThrowing(value="cutService1()",throwing = "throwing")
    public void recordInterfaceLogError(JoinPoint point,Throwable throwing) throws Throwable{
        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        InterfaceLog annotation = currentMethod.getAnnotation(InterfaceLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改,比对变化
        String msg="";
        if (bussinessName.contains("修改") || bussinessName.contains("编辑")) {
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpContext.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            msg+="request:"+sb.toString();
            msg+="===================================";
            msg+="response:"+throwing.getMessage();
        }
            LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(user.getId(), bussinessName, className, methodName, msg));
    }
    private void handle(ProceedingJoinPoint point) throws Exception {

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改,比对变化
        String msg;
        if (bussinessName.contains("修改") || bussinessName.contains("编辑")) {
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpContext.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            Map<String, String> parameters = HttpContext.getRequestParameters();
            AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
            msg = Contrast.parseMutiKey(dictMap, key, parameters);
        }

        LogManager.me().executeLog(LogTaskFactory.bussinessLog(user.getId(), bussinessName, className, methodName, msg));
    }

//    private void handle2(ProceedingJoinPoint point) throws Exception {
//
//        //获取拦截的方法名
//        Signature sig = point.getSignature();
//        MethodSignature msig = null;
//        if (!(sig instanceof MethodSignature)) {
//            throw new IllegalArgumentException("该注解只能用于方法");
//        }
//        msig = (MethodSignature) sig;
//        Object target = point.getTarget();
//        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
//        String methodName = currentMethod.getName();
//
//        //如果当前用户未登录，不做日志
//        ShiroUser user = ShiroKit.getUser();
//        if (null == user) {
//            return;
//        }
//
//        //获取拦截方法的参数
//        String className = point.getTarget().getClass().getName();
//        Object[] params = point.getArgs();
//
//        //获取操作名称
//        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
//        String bussinessName = annotation.value();
//        String key = annotation.key();
//        Class dictClass = annotation.dict();
//
//        StringBuilder sb = new StringBuilder();
//        for (Object param : params) {
//            sb.append(param);
//            sb.append(" & ");
//        }
//
//        //如果涉及到修改,比对变化
//        String msg;
//        if (bussinessName.contains("修改") || bussinessName.contains("编辑")) {
//            Object obj1 = LogObjectHolder.me().get();
//            Map<String, String> obj2 = HttpContext.getRequestParameters();
//            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
//        } else {
//            Map<String, String> parameters = HttpContext.getRequestParameters();
//            AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
//            msg = Contrast.parseMutiKey(dictMap, key, parameters);
//        }
//
//        LogManager.me().executeLog(LogTaskFactory.upInterfaceLog(user.getId(), bussinessName, className, methodName, msg));
//    }

    @AfterThrowing(value="cutService2()",throwing = "throwing")
    public void recordQuartzLogError(JoinPoint point,Throwable throwing) throws Throwable{
        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        InterfaceLog annotation = currentMethod.getAnnotation(InterfaceLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改,比对变化
        String msg="";
        if (bussinessName.contains("修改") || bussinessName.contains("编辑")) {
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpContext.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            msg+="request:"+params.toString();
            msg+="response:"+throwing.getMessage();
        }
        LogManager.me().executeLog(LogTaskFactory.upInterfaceLogError(user.getId(), bussinessName, className, methodName, msg));
    }
}