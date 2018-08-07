package com.lsj.settlement.sys.clients.hystrix;

import com.lsj.settlement.sys.clients.SettlementClient;
import com.lsj.settlement.sys.data.StSettlementTicket;
import com.lsj.settlement.sys.util.GeneralPagingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * class_name: SettlementClientImpl
 * package: com.lsj.settlement.sys.clients.hystrix
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/31
**/

@Component
@Slf4j
public class SettlementClientImpl implements SettlementClient {

    @Override
    public GeneralPagingResult<List<StSettlementTicket>> getSettlementTicket(String orderId, String referTicketId, Integer page, Integer size) {
        log.error("Settlement load balance error");
        GeneralPagingResult<List<StSettlementTicket>> result = new GeneralPagingResult<>();
        result.setResultCode("101");
        return result;
    }

}
