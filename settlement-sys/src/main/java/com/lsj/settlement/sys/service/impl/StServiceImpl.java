package com.lsj.settlement.sys.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lsj.settlement.sys.clients.SettlementClient;
import com.lsj.settlement.sys.component.PrintLog;
import com.lsj.settlement.sys.util.GeneralPagingResult;
import com.lsj.settlement.sys.data.StSettlementTicket;
import com.lsj.settlement.sys.service.StService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * class_name: StServiceImpl
 * package: com.lsj.settlement.sys.service.impl
 * describe: TODO
 * @author liusijia
 * @Date 2018/11/2
**/

@Component
@Slf4j
public class StServiceImpl implements StService {

    @Autowired
    private SettlementClient settlementClient;

    @Override
    public GeneralPagingResult<List<StSettlementTicket>> getStSettlementTicket(String orderId, String referTicketId, Integer page, Integer size) {
        test();
        return settlementClient.getSettlementTicket(orderId, referTicketId, page, size);
    }

    @PrintLog
    public void test(){
        log.info("hhh");
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("liu-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(1, 10,300L,
                TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        List<CompletableFuture<String>> futureList = list
                .stream()
                .map(x -> CompletableFuture.supplyAsync(() -> get(), executorService))
                .collect(Collectors.toList());
    }
    public String get(){
        log.info("1111");
        return "1";
    }
}
