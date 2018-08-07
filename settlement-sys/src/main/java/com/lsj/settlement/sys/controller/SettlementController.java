package com.lsj.settlement.sys.controller;

import com.lsj.settlement.sys.data.StSettlementTicket;
import com.lsj.settlement.sys.data.test;
import com.lsj.settlement.sys.service.StService;
import com.lsj.settlement.sys.util.GeneralContentResult;
import com.lsj.settlement.sys.util.GeneralPagingResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * class_name: SettlementController
 * package: com.lsj.settlement.sys.controller
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/31
**/

@RestController
public class SettlementController {

    @Autowired
    private StService stService;

    @ApiOperation(value = "高级查询 settlementTicket", notes = "高级查询 settlementTicket")
    @RequestMapping(value = "/liu/si/jia/settlement-sys/ticket", method = RequestMethod.POST)
    public GeneralPagingResult<List<StSettlementTicket>> getSettlementTicket(
            @ApiParam(required = false, name = "orderId", value = "order id") @RequestParam(value = "orderId", required = false) String orderId,
            @ApiParam(required = false, name = "referTicketId", value = "refer ticket id") @RequestParam(value = "referTicketId", required = false) String referTicketId,
            @ApiParam(required = false, name = "page", value = "Ldap Source list page index") @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(required = false, name = "size", value = "Ldap Source list page size") @RequestParam(value = "size", required = false) Integer size){
        return stService.getStSettlementTicket(orderId, referTicketId, page, size);
    }

    @ApiOperation(value = "高级查询 settlementTicket", notes = "高级查询 settlementTicket")
    @RequestMapping(value = "/liu/si/jia/settlement-sys/ticket/test", method = RequestMethod.GET)
    public GeneralContentResult<StSettlementTicket> getTest(){
        test test = new test();
        GeneralContentResult<StSettlementTicket> result = new GeneralContentResult<>();
        result.setResultCode("100");
        result.setResultContent(test.getStSettlementTicket());
        return result;
    }

}
