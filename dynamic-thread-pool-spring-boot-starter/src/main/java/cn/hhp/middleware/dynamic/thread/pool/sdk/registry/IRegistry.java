package cn.hhp.middleware.dynamic.thread.pool.sdk.registry;

import cn.hhp.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @author heihaipeng
 * @description 注册中心接口
 * @create 2024/5/24 16:19
 */
public interface IRegistry {
    void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolEntities);

    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);
}
