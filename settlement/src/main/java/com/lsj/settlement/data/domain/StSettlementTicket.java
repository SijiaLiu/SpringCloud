package com.lsj.settlement.data.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ST_SETTLEMENT_TICKET", schema = "lz_settlement", catalog = "")
@DynamicInsert
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

    // 关系的维护方 mappedBy 的值是关系拥有方的某个对象
    private List<StSettlementSlip> stSettlementSlips = new ArrayList<>();
    @OneToMany(mappedBy = "stSettlementTicket", cascade = { CascadeType.ALL })
    public List<StSettlementSlip> getStSettlementSlips() {
        return stSettlementSlips;
    }

    public void setStSettlementSlips(List<StSettlementSlip> stSettlementSlips) {
        this.stSettlementSlips = stSettlementSlips;
    }

    public StSettlementSlip addStSettlementSlips(StSettlementSlip stSettlementSlip){
        getStSettlementSlips().add(stSettlementSlip);
        stSettlementSlip.setStSettlementTicket(this);
        return stSettlementSlip;
    }

    private List<StSettlementBasisItem> stSettlementBasisItems = new ArrayList<>();
    @OneToMany(mappedBy = "stSettlementTicket", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    public List<StSettlementBasisItem> getStSettlementBasisItems() {
        return stSettlementBasisItems;
    }

    public void setStSettlementBasisItems(List<StSettlementBasisItem> stSettlementBasisItems) {
        this.stSettlementBasisItems = stSettlementBasisItems;
    }

    public StSettlementBasisItem addStSettlementBasisItem(StSettlementBasisItem stSettlementBasisItem){
        getStSettlementBasisItems().add(stSettlementBasisItem);
        stSettlementBasisItem.setStSettlementTicket(this);
        return stSettlementBasisItem;
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
    @Column(name = "ORDER_ID")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "ORDER_NO")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "COMMERCIAL_GROUP_ID")
    public String getCommercialGroupId() {
        return commercialGroupId;
    }

    public void setCommercialGroupId(String commercialGroupId) {
        this.commercialGroupId = commercialGroupId;
    }

    @Basic
    @Column(name = "COMMERCIAL_GROUP_NO")
    public String getCommercialGroupNo() {
        return commercialGroupNo;
    }

    public void setCommercialGroupNo(String commercialGroupNo) {
        this.commercialGroupNo = commercialGroupNo;
    }

    @Basic
    @Column(name = "SOURCE_TYPE")
    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
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
    @Column(name = "SLIP_COUNT")
    public Integer getSlipCount() {
        return slipCount;
    }

    public void setSlipCount(Integer slipCount) {
        this.slipCount = slipCount;
    }

    @Basic
    @Column(name = "REFER_TICKET_ID")
    public String getReferTicketId() {
        return referTicketId;
    }

    public void setReferTicketId(String referTicketId) {
        this.referTicketId = referTicketId;
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
    @Column(name = "CREATOR_ID")
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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
        StSettlementTicket that = (StSettlementTicket) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(commercialGroupId, that.commercialGroupId) &&
                Objects.equals(commercialGroupNo, that.commercialGroupNo) &&
                Objects.equals(sourceType, that.sourceType) &&
                Objects.equals(status, that.status) &&
                Objects.equals(slipCount, that.slipCount) &&
                Objects.equals(referTicketId, that.referTicketId) &&
                Objects.equals(tenantId, that.tenantId) &&
                Objects.equals(creatorId, that.creatorId) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, name, orderId, orderNo, commercialGroupId, commercialGroupNo, sourceType, status, slipCount, referTicketId, tenantId, creatorId, createDate, updateDate);
    }
}
