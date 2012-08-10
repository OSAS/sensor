package com.redhat.osas.sensor.web;

import com.redhat.osas.sensor.data.DataPoint;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class InitializeTestData implements ServletContextListener {
    @Resource(lookup = "java:/jboss/infinispan/dataPoints")
    private CacheContainer container;
    private Cache<String, DataPoint> cache;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        cache=container.getCache("dataPoints");
        // longitude='-78.51060385', latitude='36.07338185', level=154, timestamp=1344543690882}
        DataPoint dp=new DataPoint("9194533780", 36.07338185, -78.51060385,154);
        dp.setTimestamp(System.currentTimeMillis());
        cache.put(dp.getDeviceId(), dp);
        System.out.println("DataPoint stored");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}