package com.lsj.settlement.sys.service;

import com.lsj.settlement.sys.util.GeneralPagingResult;
import com.lsj.settlement.sys.data.StSettlementTicket;
import com.lsj.zuul.settlement.sys.util.GeneralPagingResult;

import java.util.List;

public interface StService {

    GeneralPagingResult<List<StSettlementTicket>> getStSettlementTicket(String orderId, String referTicketId, Integer page, Integer size);

}
