package com.cmcc.hsp.netty.core;

import com.cmcc.hsp.netty.annotation.STBRequestMapping;
import com.cmcc.hsp.netty.invote.STBAction;
import com.cmcc.hsp.netty.invote.STBActionMapUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author zhouzhou
 *
 */
@Component
public class ActionBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            STBRequestMapping stbRequestMapping = method.getAnnotation(STBRequestMapping.class);
            if (stbRequestMapping != null) {
                STBAction stbAction = new STBAction();
                stbAction.setMethod(method);
                stbAction.setObject(bean);
                STBActionMapUtil.put(stbRequestMapping.value(), stbAction);
            }
        }
        return bean;
    }

}
