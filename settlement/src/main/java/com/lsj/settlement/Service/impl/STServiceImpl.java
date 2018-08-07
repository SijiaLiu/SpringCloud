package com.lsj.settlement.Service.impl;

import com.lsj.settlement.Service.STService;
import com.lsj.settlement.component.PrintLog;
import com.lsj.settlement.constant.SettlementConstant;
import com.lsj.settlement.data.SettlementTicketRepository;
import com.lsj.settlement.data.domain.StSettlementBasisItem;
import com.lsj.settlement.data.domain.StSettlementSlip;
import com.lsj.settlement.data.domain.StSettlementTicket;
import com.lsj.settlement.data.util.PageInfo;
import com.lsj.settlement.data.util.SortUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

/**
 * class_name: STServiceImpl
 * package: com.lsj.settlement.Service.impl
 * describe: TODO
 *
 * @author liusijia
 * @Date 2018/7/27
 **/

@Component
public class STServiceImpl implements STService {

    @Autowired
    private SettlementTicketRepository settlementTicketRepository;

    @Override
    @PrintLog(desc = "高级查询 结算单据")
    public Page<StSettlementTicket> getSettlementTicketItem(String orderId, String referTicketId, PageInfo pageInfo) {

        Byte delete = 3;
        Page<StSettlementTicket> stTicketPageResult = settlementTicketRepository.findAll(new Specification<StSettlementTicket>() {
            @Override
            public Predicate toPredicate(Root<StSettlementTicket> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate pre = cb.notEqual(root.<Byte>get("status"), delete);
                if (StringUtils.isNotBlank(orderId)) {
                    pre = cb.and(pre, cb.equal(root.<String>get("orderId"), orderId));
                }
                if (StringUtils.isNotBlank(referTicketId)) {
                    pre = cb.and(pre, cb.equal(root.<String>get("referTicketId"), referTicketId));
                }
                return pre;
            }
        }, new PageRequest(pageInfo.getCurrentPage(), pageInfo.getPageSize(),
                SortUtil.sortSetting(SettlementConstant.settlementDirectionDesc, Arrays.asList(SettlementConstant.settlementDirectionCreateDate))));

        return stTicketPageResult;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @PrintLog(desc = "新增或者更新 结算单据", printResult = true)
    public StSettlementTicket insertOrUpdateSettlementTicketItem(StSettlementTicket stSettlementTicket) {

        List<StSettlementSlip> stSettlementSlips = stSettlementTicket.getStSettlementSlips();
        stSettlementSlips.forEach(x-> {
            x.setStSettlementTicket(stSettlementTicket);
            if (x.getSettlementSlipItem() != null){
                x.getSettlementSlipItem().setStSettlementSlip(x);
            }
        });
        List<StSettlementBasisItem> stSettlementBasisItems = stSettlementTicket.getStSettlementBasisItems();
        stSettlementBasisItems.forEach(x-> x.setStSettlementTicket(stSettlementTicket));
        return settlementTicketRepository.save(stSettlementTicket);
    }

    @Override
    @PrintLog(desc = "删除 结算单据", printResult = true)
    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteSettlementTicketItem(String stId) {
        StSettlementTicket settlementTicket = settlementTicketRepository.findOne(stId);
        if (null != settlementTicket) {
            settlementTicket.setStatus((byte) 3);
            settlementTicketRepository.save(settlementTicket);
            return SettlementConstant.resultCodeSuccess;
        }
        return SettlementConstant.resultCodeFailed;
    }
}
