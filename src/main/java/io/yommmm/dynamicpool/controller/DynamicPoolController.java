package io.yommmm.dynamicpool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author 85374
 * @date
 */
@RestController
@RequestMapping("/dynamicpool/v1/manager")
public class DynamicPoolController {

    @GetMapping("/create")
    public void create() {
        ExecutorService executorService = new ThreadPoolExecutor(1, 3, 1, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

}
