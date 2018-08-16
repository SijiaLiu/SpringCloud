package com.lsj.settlement.sys.service.impl;

import com.lsj.settlement.sys.clients.SettlementClient;
import com.lsj.settlement.sys.util.GeneralPagingResult;
import com.lsj.settlement.sys.data.StSettlementTicket;
import com.lsj.settlement.sys.service.StService;
import com.lsj.zuul.settlement.sys.clients.SettlementClient;
import com.lsj.zuul.settlement.sys.util.GeneralPagingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StServiceImpl implements StService {

    @Autowired
    private SettlementClient settlementClient;

    @Override
    public GeneralPagingResult<List<StSettlementTicket>> getStSettlementTicket(String orderId, String referTicketId, Integer page, Integer size) {
        return settlementClient.getSettlementTicket(orderId, referTicketId, page, size);
    }
}
