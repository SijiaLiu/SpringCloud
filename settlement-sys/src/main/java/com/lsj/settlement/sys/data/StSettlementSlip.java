package com.lsj.settlement.sys.data;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StSettlementSlip {
    private String id;
    private String description;
    private Byte balanceType;
    private BigDecimal changeAmount;
    private Integer slipCount;
    private String slipPrice;
    private String icon;
    private Byte type;
    private Byte status;
    private String creatorId;
    private String tenantId;
    private Date createDate;
    private Date updateDate;

    private StSettlementSlipItem stSettlementSlipItem;
    public StSettlementSlipItem getSettlementSlipItem() {
        return stSettlementSlipItem;
    }

    public void setSettlementSlipItem(StSettlementSlipItem stSettlementSlipItem) {
        this.stSettlementSlipItem = stSettlementSlipItem;
    }
}
