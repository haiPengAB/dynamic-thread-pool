package cn.hhp.middleware.dynamic.thread.pool.sdk.trigger.job;

import cn.hhp.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import cn.hhp.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import cn.hhp.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author heihaipeng
 * @description 线程池数据上报任务
 * @create 2024/5/29 21:45
 */
public class ThreadPoolDataReportJob {
    private final Logger logger = LoggerFactory.getLogger(ThreadPoolDataReportJob.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry registry;

    public ThreadPoolDataReportJob(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.registry = registry;
    }

    @Scheduled(cron = "0/20 * * * *") //Cron表达式：使用类似Unix系统的cron工具的表达式来定义执行计划，这允许非常灵活的任务调度，如每天、每周或每月执行。
    public void execReportThreadPoolList() {
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = dynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadPool(threadPoolConfigEntities);
        logger.info("动态线程池，上报线程池信息：{}", JSON.toJSONString(threadPoolConfigEntities));

        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntities) {
            registry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
            logger.info("动态线程池，上报线程池配置参数：{}", JSON.toJSONString(threadPoolConfigEntity));
        }
    }

}
