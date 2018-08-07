package com.lsj.settlement.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * class_name: SettlementItem
 * package: com.lsj.settlement.data.dto
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Data
@ApiModel(value = "结算单据")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettlementItem implements Serializable {

    private static final long serialVersionUID = -6523786556606065152L;

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
    private Timestamp createDate;
    private Timestamp updateDate;

    private List<SettlementSlip> slips;

    private List<SettlementBasisItem> basisItems;

}
