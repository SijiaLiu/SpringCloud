package com.lsj.settlement.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * class_name: SettlementSlip
 * package: com.lsj.settlement.data.dto
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettlementSlip implements Serializable {

    private static final long serialVersionUID = 4798636411696153671L;
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
    private Timestamp createDate;
    private Timestamp updateDate;

    private SettlementSlipItem slipItem;

}
