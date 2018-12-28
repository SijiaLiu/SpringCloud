package com.lsj.settlement.controller;

import com.lsj.settlement.Service.RedisService;
import com.lsj.settlement.Service.STService;
import com.lsj.settlement.constant.SettlementConstant;
import com.lsj.settlement.data.domain.StSettlementTicket;
import com.lsj.settlement.data.util.GeneralContentResult;
import com.lsj.settlement.data.util.GeneralPagingResult;
import com.lsj.settlement.data.util.GeneralResult;
import com.lsj.settlement.data.util.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * class_name: SettlementController
 * package: com.lsj.settlement.controller
 * describe: TODO
 * @author liusijia
 * @Date 2018/11/2
**/

@RestController
@Slf4j
public class SettlementController {

    @Autowired
    private STService stService;
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "高级查询 settlementTicket", notes = "高级查询 settlementTicket")
    @RequestMapping(value = "/liu/si/jia/settlement/ticket", method = RequestMethod.POST)
    public GeneralPagingResult<List<StSettlementTicket>> getSettlementTicket(
            @ApiParam(required = false, name = "orderId", value = "order id") @RequestParam(value = "orderId", required = false) String orderId,
            @ApiParam(required = false, name = "referTicketId", value = "refer ticket id") @RequestParam(value = "referTicketId", required = false) String referTicketId,
            @ApiParam(required = false, name = "page", value = "Ldap Source list page index") @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(required = false, name = "size", value = "Ldap Source list page size") @RequestParam(value = "size", required = false) Integer size){
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(page != null ? page : 0);
        pageInfo.setPageSize(size != null ? size : 10);

        Page<StSettlementTicket> ticketPageResult = stService.getSettlementTicketItem(orderId, referTicketId, pageInfo);
        log.info("result : {}", JSONObject.toJSONString(ticketPageResult));
        GeneralPagingResult<List<StSettlementTicket>> resultList = new GeneralPagingResult<>();
        resultList.setPageInfo(pageInfo);
        if (null != ticketPageResult){
            pageInfo.setTotalPage(ticketPageResult.getTotalPages());
            pageInfo.setTotalRecords(new Long(ticketPageResult.getTotalElements()).intValue());
            resultList.setResultCode(SettlementConstant.resultCodeSuccess);
            resultList.setResultContent(ticketPageResult.getContent());
            resultList.setDetailDescription("success");
        } else {
            resultList.setResultCode(SettlementConstant.resultCodeFailed);
            resultList.setResultContent(new ArrayList<>());
            resultList.setDetailDescription("failed");
        }
        redisService.setStr("settlementTicket", resultList);
        return resultList;
    }

    @ApiOperation(value = "新增或者更新 settlementTicket", notes = "新增或者更新 settlementTicket")
    @RequestMapping(value = "/liu/si/jia/settlement/ticket/item", method = RequestMethod.POST)
    public GeneralContentResult<StSettlementTicket> insertSettlementTicket(
            @RequestBody StSettlementTicket stSettlementTicket){
        GeneralContentResult<StSettlementTicket> result = new GeneralContentResult<>();
        result.setResultCode(SettlementConstant.resultCodeSuccess);
        result.setResultContent(stService.insertOrUpdateSettlementTicketItem(stSettlementTicket));
        result.setDetailDescription("success");
        return result;
    }

    @ApiOperation(value = "删除 settlementTicket", notes = "删除 settlementTicket")
    @RequestMapping(value = "/liu/si/jia/settlement/ticket/item", method = RequestMethod.DELETE)
    public GeneralResult deleteSettlementTicket(@RequestParam(value = "id") String id){
        GeneralResult result = new GeneralResult();
        result.setResultCode(stService.deleteSettlementTicketItem(id));
        result.setDescription("success");
        return result;
    }

}
