package com.lsj.settlement;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lsj.settlement.data.dto.SettlementSlip;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * class_name: Main
 * package: com.lsj.settlement
 * describe: TODO
 * @author liusijia
 * @Date 2018/12/28
**/

public class Main {

    public static void main(String[] args) {
        List<String> rList = new ArrayList<>();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(50, 50, 300L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(), namedThreadFactory, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString());
                rList.add(r.toString());
            }
        });
        long s = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(30);
                List<String> list = new ArrayList<>();
                for (int j = 0; j < 20; j++) {
                    list.add(String.valueOf(j));
                }
                List<CompletableFuture<String>> futureList = list
                        .stream()
                        .map(x -> CompletableFuture.supplyAsync(() -> get(), executorService))
                        .collect(Collectors.toList());
                List<String> resultList = futureList
                        .stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());
                System.out.println(resultList.size());
                System.out.println(System.currentTimeMillis() - s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - s);
        System.out.println(rList.size());
        mapTest();
    }

    private static String get(){
        try {
            Thread.sleep(10);
            return "hehe";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "heh";
    }

    private static void mapTest(){
        List<SettlementSlip> settlementSlips = new ArrayList<>();
        SettlementSlip s1 = new SettlementSlip();
        s1.setId("1");
        s1.setCreatorId("1");
        SettlementSlip s4 = new SettlementSlip();
        s4.setId("4");
        s4.setCreatorId("4");
        SettlementSlip s2 = new SettlementSlip();
        s2.setId("4");
        s2.setCreatorId("1");
        SettlementSlip s3 = new SettlementSlip();
        s3.setId("1");
        s3.setCreatorId("2");
        settlementSlips.add(s1);
        settlementSlips.add(s2);
        settlementSlips.add(s3);
        settlementSlips.add(s4);

        Map<String, Map<String, SettlementSlip>> map = new HashMap<>();
        settlementSlips.forEach(x -> {
            if (map.containsKey(x.getId())){
                Map<String, SettlementSlip> map1 = map.get(x.getId());
                map1.put(x.getCreatorId(), x);
            } else {
                Map<String, SettlementSlip> map1 = new HashMap<>();
                map1.put(x.getCreatorId(), x);
                map.put(x.getId(), map1);
            }
        });

        Map<String, Map<String, SettlementSlip>> mapMap = settlementSlips
                .stream()
                .collect(Collectors.groupingBy(SettlementSlip::getId, Collectors.toMap(SettlementSlip::getCreatorId, Function.identity())));
        System.out.println(JSONObject.toJSON(mapMap));


    }
}
