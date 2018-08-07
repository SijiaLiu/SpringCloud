package com.lsj.settlement.data.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ST_SETTLEMENT_SLIP", schema = "lz_settlement", catalog = "")
@DynamicInsert
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

    // 关系的拥有方
    private StSettlementTicket stSettlementTicket;
    @ManyToOne(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "SETTLEMENT_TICKET_ID", unique = true, nullable = false, updatable = false)
    @JsonIgnore // 在json序列化时将java bean中的一些属性忽略掉
    public StSettlementTicket getStSettlementTicket() {
        return stSettlementTicket;
    }
    public void setStSettlementTicket(StSettlementTicket settlementSlip) {
        this.stSettlementTicket = settlementSlip;
    }


    private StSettlementSlipItem stSettlementSlipItem;
    @OneToOne(mappedBy = "stSettlementSlip", cascade = {CascadeType.ALL})
    public StSettlementSlipItem getSettlementSlipItem() {
        return stSettlementSlipItem;
    }

    public void setSettlementSlipItem(StSettlementSlipItem stSettlementSlipItem) {
        this.stSettlementSlipItem = stSettlementSlipItem;
    }

    public StSettlementSlipItem addStSettlementSlipItem(StSettlementSlipItem stSettlementSlipItem){
        stSettlementSlipItem.setStSettlementSlip(this);
        return stSettlementSlipItem;
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
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "BALANCE_TYPE")
    public Byte getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(Byte balanceType) {
        this.balanceType = balanceType;
    }

    @Basic
    @Column(name = "CHANGE_AMOUNT")
    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    @Basic
    @Column(name = "SLIP_COUNT")
    public Integer getSlipCount() {
        return slipCount;
    }

    public void setSlipCount(Integer slipCount) {
        this.slipCount = slipCount;
    }

    @Basic
    @Column(name = "SLIP_PRICE")
    public String getSlipPrice() {
        return slipPrice;
    }

    public void setSlipPrice(String slipPrice) {
        this.slipPrice = slipPrice;
    }

    @Basic
    @Column(name = "ICON")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
    @Column(name = "STATUS")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CREATOR_ID")
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "TENANT_ID")
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "UPDATE_DATE", insertable = false, updatable = false)
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
        StSettlementSlip that = (StSettlementSlip) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(balanceType, that.balanceType) &&
                Objects.equals(changeAmount, that.changeAmount) &&
                Objects.equals(slipCount, that.slipCount) &&
                Objects.equals(slipPrice, that.slipPrice) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(type, that.type) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creatorId, that.creatorId) &&
                Objects.equals(tenantId, that.tenantId) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description, balanceType, changeAmount, slipCount, slipPrice, icon, type, status, creatorId, tenantId, createDate, updateDate);
    }
}
