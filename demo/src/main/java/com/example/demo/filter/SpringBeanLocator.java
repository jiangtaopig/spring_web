package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.util.Properties;

@Component
@Lazy(false)
public class SpringBeanLocator implements ApplicationContextAware {

    protected static Logger logger = LoggerFactory.getLogger(SpringBeanLocator.class);
    private static ApplicationContext fac = null;
    private static Properties properties = new Properties();

    public static <T> T getBean(Class<T> clz) {
        try {
            ApplicationContext ac = getApplicationContext();
            return ac != null ? ac.getBean(clz) : null;
        } catch (BeansException var2) {
            logger.warn(var2.getLocalizedMessage());
            return null;
        }
    }

    public static ApplicationContext getApplicationContext() {
        if (fac == null) {
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            if (wac == null) {
                logger.error("WebApplicationContext is not exist, applicationContext.xml");
            } else {
                fac = wac;
            }
        }
        return fac;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("------->>> setApplicationContext ...... applicationContext = " + applicationContext);
        fac = applicationContext;
        initSpringProperties(applicationContext);
    }

    private static void initSpringProperties(ApplicationContext ac) {
        try {
            String[] postProcessorNames = ac.getBeanNamesForType(BeanFactoryPostProcessor.class, true, true);
            String[] var2 = postProcessorNames;
            int var3 = postProcessorNames.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String ppName = var2[var4];
                BeanFactoryPostProcessor beanProcessor = (BeanFactoryPostProcessor) ac.getBean(ppName, BeanFactoryPostProcessor.class);
                if (beanProcessor instanceof PropertyResourceConfigurer) {
                    PropertyResourceConfigurer propertyResourceConfigurer = (PropertyResourceConfigurer) beanProcessor;
                    Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
                    mergeProperties.setAccessible(true);
                    Properties props = (Properties) mergeProperties.invoke(propertyResourceConfigurer);
                    Method convertProperties = PropertyResourceConfigurer.class.getDeclaredMethod("convertProperties", Properties.class);
                    convertProperties.setAccessible(true);
                    convertProperties.invoke(propertyResourceConfigurer, props);
                    properties.putAll(props);
                }
            }

        } catch (Exception var11) {
            throw new RuntimeException(var11);
        }
    }
}
