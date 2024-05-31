package cn.hhp.middleware.dynamic.thread.pool.sdk.trigger.listener;

import cn.hhp.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import cn.hhp.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import cn.hhp.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import com.alibaba.fastjson.JSON;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author heihaipeng
 * @description 动态线程池监听
 * @create 2024/5/29 21:44
 */
public class ThreadPoolConfigAdjustListener implements MessageListener<ThreadPoolConfigEntity> {


    private Logger logger = LoggerFactory.getLogger(ThreadPoolConfigAdjustListener.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry registry;

    public ThreadPoolConfigAdjustListener(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.registry = registry;
    }


    @Override
    public void onMessage(CharSequence charSequence, ThreadPoolConfigEntity threadPoolConfigEntity) {
        logger.info("动态线程池，调整线程池配置。线程池名称：{}，核心线程数：{}，最大线程数：{}", threadPoolConfigEntity.getThreadPoolName(), threadPoolConfigEntity.getCorePoolSize(), threadPoolConfigEntity.getMaximumPoolSize());
        dynamicThreadPoolService.updateThreadPoolConfig(threadPoolConfigEntity);

        //更新后上报最新数据
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = dynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadPool(threadPoolConfigEntities);

        ThreadPoolConfigEntity thradPoolConfigEntityCurrent = dynamicThreadPoolService.queryThreadPoolConfigByName(threadPoolConfigEntity.getThreadPoolName());
        registry.reportThreadPoolConfigParameter(thradPoolConfigEntityCurrent);
        logger.info("动态线程池，上报线程池配置：{}", JSON.toJSONString(threadPoolConfigEntity));
    }
}
