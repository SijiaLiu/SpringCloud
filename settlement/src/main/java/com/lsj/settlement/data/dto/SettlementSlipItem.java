package com.lsj.settlement.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * class_name: SettlementSlipItem
 * package: com.lsj.settlement.data.dto
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettlementSlipItem implements Serializable {

    private static final long serialVersionUID = -3282248625693182931L;

    private String id;
    private String itemId;
    private String code;
    private String name;
    private Byte type;
    private String value;
    private Timestamp createDate;
    private Timestamp updateDate;
}
