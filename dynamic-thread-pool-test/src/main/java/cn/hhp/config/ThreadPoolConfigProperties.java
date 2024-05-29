/**
 * @description
 * @author heihaipeng
 * @create 2024/5/24 11:30
 */
package cn.hhp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
//@ConfigurationProperties: 这是主注解，它告诉Spring Boot，此类的属性应从配置文件中加载。
@ConfigurationProperties(prefix = "thread.pool.executor.config", ignoreInvalidFields = true)
public class ThreadPoolConfigProperties {
    /** 核心线程数 */
    private Integer corePoolSize = 20;
    /** 最大线程数 */
    private Integer maxPoolSize = 200;
    /** 最大等待时间 */
    private Long keepAliveTime = 10L;
    /** 最大队列数 */
    private Integer blockQueueSize = 5000;
    /*
     * AbortPolicy：丢弃任务并抛出RejectedExecutionException异常。
     * DiscardPolicy：直接丢弃任务，但是不会抛出异常
     * DiscardOldestPolicy：将最早进入队列的任务删除，之后再尝试加入队列的任务被拒绝
     * CallerRunsPolicy：如果任务添加线程池失败，那么主线程自己执行该任务
     * */
    private String policy = "AbortPolicy";
}
