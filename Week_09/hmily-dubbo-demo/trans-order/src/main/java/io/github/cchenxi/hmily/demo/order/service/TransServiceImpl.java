package io.github.cchenxi.hmily.demo.order.service;

import org.apache.dubbo.config.annotation.DubboReference;

import io.github.cchenxi.hmily.demo.common.d2r.api.D2rTransService;
import io.github.cchenxi.hmily.demo.common.d2r.model.D2rTransRequest;
import io.github.cchenxi.hmily.demo.common.r2d.api.R2dTransService;
import io.github.cchenxi.hmily.demo.common.r2d.model.R2dTransRequest;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 交易
 * Date: 2020-12-20
 *
 * @author chenxi
 */
@Service
@Slf4j
public class TransServiceImpl implements TransService{
    @DubboReference
    private R2dTransService r2dTransService;

    @DubboReference
    private D2rTransService d2rTransService;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void doTrans(BigDecimal dollarAmount) {
        d2rTransService.trans(buildD2rTransRequest());
        r2dTransService.trans(buildR2dTransRequest());
    }

    public void confirm(BigDecimal dollarAmount) {
        log.info("confirm");
    }

    public void cancel(BigDecimal dollarAmount) {
        log.info("cancel");
    }

    private D2rTransRequest buildD2rTransRequest() {
        D2rTransRequest transRequest = new D2rTransRequest();
        transRequest.setDollarAmount(BigDecimal.ONE);
        transRequest.setUserId("1");
        transRequest.setRate(BigDecimal.valueOf(7L));
        return transRequest;
    }

    private R2dTransRequest buildR2dTransRequest() {
        R2dTransRequest transRequest = new R2dTransRequest();
        transRequest.setRmbAmount(BigDecimal.valueOf(7L));
        transRequest.setUserId("2");
        transRequest.setRate(BigDecimal.valueOf(7L));
        return transRequest;
    }
}
