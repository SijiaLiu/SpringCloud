package com.lsj.settlement.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * class_name: SettlementBasisItem
 * package: com.lsj.settlement.data.dto
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettlementBasisItem implements Serializable {

    private static final long serialVersionUID = -5196858534340785271L;
    private String id;
    private String itemId;
    private String code;
    private String name;
    private Byte type;
    private String value;
    private String creatorId;
    private String tenantId;
    private Timestamp createDate;
    private Timestamp updateDate;

}
