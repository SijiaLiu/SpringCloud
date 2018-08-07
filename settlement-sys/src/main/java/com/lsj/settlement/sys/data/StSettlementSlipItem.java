package com.lsj.settlement.sys.data;


import lombok.Data;

import java.util.Date;

@Data
public class StSettlementSlipItem {
    private String id;
    private String itemId;
    private String code;
    private String name;
    private Byte type;
    private String value;
    private Date createDate;
    private Date updateDate;

}
