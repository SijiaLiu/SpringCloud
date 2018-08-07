package com.lsj.settlement.sys.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class StSettlementTicket {
    private String id;
    private String code;
    private String name;
    private String orderId;
    private String orderNo;
    private String commercialGroupId;
    private String commercialGroupNo;
    private Byte sourceType;
    private Byte status;
    private Integer slipCount;
    private String referTicketId;
    private String tenantId;
    private String creatorId;
    private Date createDate;
    private Date updateDate;

    private List<StSettlementSlip> stSettlementSlips = new ArrayList<>();

    private List<StSettlementBasisItem> stSettlementBasisItems = new ArrayList<>();

}
