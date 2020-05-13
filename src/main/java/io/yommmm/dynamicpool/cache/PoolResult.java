package io.yommmm.dynamicpool.cache;

import io.yommmm.dynamicpool.queue.ResizeableCapacityLinkedBlockingQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author 85374
 * @date
 */
public class PoolResult {

    public static final Map<String, ThreadPoolExecutor> executorCache = new ConcurrentHashMap<>();

    public void put(String executorName, ThreadPoolExecutor executor) {
        executorCache.put(executorName, executor);
    }

    public Map<String, Map<String, Object>> getValues() {
        Map<String, Map<String, Object>> result = new HashMap<>();
        executorCache.forEach((s, executor) -> {
            HashMap<String, Object> values = new HashMap<>();

            values.put("ActiveCount", executor.getActiveCount());
            values.put("CompletedTaskCount", executor.getCompletedTaskCount());
            values.put("CorePoolSize", executor.getCorePoolSize());
            values.put("KeepAliveTime", executor.getKeepAliveTime(TimeUnit.SECONDS));
            values.put("LargestPoolSize", executor.getLargestPoolSize());
            values.put("MaximumPoolSize", executor.getMaximumPoolSize());
            values.put("Queue", executor.getQueue().getClass());
            values.put("TaskCount", executor.getTaskCount());

            result.put(s, values);
        });

        return result;
    }

    public void dynamicModifyExecutor(String executorName, int corePoolSize, int maximumPoolSize, int queueCapacity) throws InterruptedException {
        ThreadPoolExecutor executor = executorCache.get(executorName);

        for (int i = 0; i < executor.getMaximumPoolSize(); i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);

        executor.setCorePoolSize(corePoolSize);
        executor.setMaximumPoolSize(maximumPoolSize);
        ResizeableCapacityLinkedBlockingQueue queue = (ResizeableCapacityLinkedBlockingQueue) executor.getQueue();
        queue.setCapacity(queueCapacity);

    }


}
