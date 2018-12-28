package com.lsj.settlement.sys.service;

import com.lsj.settlement.sys.util.GeneralPagingResult;
import com.lsj.settlement.sys.data.StSettlementTicket;

import java.util.List;
/**
 * class_name: StService
 * package: com.lsj.settlement.sys.service
 * describe: TODO
 * @author liusijia
 * @Date 2018/11/2
**/

public interface StService {

    GeneralPagingResult<List<StSettlementTicket>> getStSettlementTicket(String orderId, String referTicketId, Integer page, Integer size);

}
