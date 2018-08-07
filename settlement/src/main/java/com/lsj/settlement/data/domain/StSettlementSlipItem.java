package com.lsj.settlement.data.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ST_SETTLEMENT_SLIP_ITEM", schema = "lz_settlement", catalog = "")
@DynamicInsert
public class StSettlementSlipItem {
    private String id;
    private String itemId;
    private String code;
    private String name;
    private Byte type;
    private String value;
    private Date createDate;
    private Date updateDate;


    private StSettlementSlip stSettlementSlip;
    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="SETTLEMENT_SLIP_ID", unique = true, nullable = false, updatable = false)
    @JsonIgnore // 在json序列化时将java bean中的一些属性忽略掉
    public StSettlementSlip getStSettlementSlip() {
        return stSettlementSlip;
    }
    public void setStSettlementSlip(StSettlementSlip stSettlementSlip) {
        this.stSettlementSlip = stSettlementSlip;
    }



    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEM_ID")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TYPE")
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StSettlementSlipItem that = (StSettlementSlipItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(itemId, that.itemId) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(value, that.value) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, itemId, code, name, type, value, createDate, updateDate);
    }
}
