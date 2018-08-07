package com.lsj.settlement.data.otd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lsj.settlement.data.dto.SettlementBasisItem;
import com.lsj.settlement.data.dto.SettlementSlip;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * class_name: SettlementItemProfile
 * package: com.lsj.settlement.data.otd
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettlementItemProfile implements Serializable {

    private static final long serialVersionUID = 5072570217118910602L;

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

    private List<SettlementSlip> slips;

    private List<SettlementBasisItem> basisItems;
}
