package com.lsj.settlement.Service;

import com.lsj.settlement.data.domain.StSettlementTicket;
import com.lsj.settlement.data.util.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface STService {

    Page<StSettlementTicket> getSettlementTicketItem(String orderId, String referTicketId, PageInfo pageInfo);

    StSettlementTicket insertOrUpdateSettlementTicketItem(StSettlementTicket stSettlementTicket);

    String deleteSettlementTicketItem(String stId);

}
