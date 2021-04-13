package com.xjw.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MyLb implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);


    /**
     * 调用次数
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current+1;
        }while (!atomicInteger.compareAndSet(current,next));
        log.info("第{}次调用",next);
        return next;
    }

    /**
     * 获取哪台服务器处理请求
     * @param serviceInstanceList
     * @return
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {
        int serverCount = serviceInstanceList.size();
        int index = getAndIncrement() % serverCount;
        return serviceInstanceList.get(index);
    }
}
