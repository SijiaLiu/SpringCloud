package com.lsj.settlement.sys.clients;

import com.lsj.settlement.sys.clients.hystrix.SettlementClientImpl;
import com.lsj.settlement.sys.util.GeneralPagingResult;
import com.lsj.settlement.sys.component.FeignLogConfiguration;
import com.lsj.settlement.sys.data.StSettlementTicket;
import com.lsj.zuul.settlement.sys.clients.hystrix.SettlementClientImpl;
import com.lsj.zuul.settlement.sys.util.GeneralPagingResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * class_name: SettlementClient
 * package: com.lsj.settlement.sys.clients
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/31
**/

@FeignClient(value = "settlement-core", fallback = SettlementClientImpl.class, configuration = FeignLogConfiguration.class)
//@FeignClient(value = "settlement-core", url = "http://localhost:8762")
public interface SettlementClient {

    @ApiOperation(value = "高级查询 settlementTicket", notes = "高级查询 settlementTicket")
    @RequestMapping(value = "/liu/si/jia/settlement/ticket", method = RequestMethod.POST)
    public GeneralPagingResult<List<StSettlementTicket>> getSettlementTicket(
            @ApiParam(required = false, name = "orderId", value = "order id") @RequestParam(value = "orderId", required = false) String orderId,
            @ApiParam(required = false, name = "referTicketId", value = "refer ticket id") @RequestParam(value = "referTicketId", required = false) String referTicketId,
            @ApiParam(required = false, name = "page", value = "Ldap Source list page index") @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(required = false, name = "size", value = "Ldap Source list page size") @RequestParam(value = "size", required = false) Integer size);
}
