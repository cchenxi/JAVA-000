package io.github.cchenxi.hmily.demo.d2r.service;

import org.apache.dubbo.config.annotation.DubboService;

import io.github.cchenxi.hmily.demo.common.d2r.api.D2rTransService;
import io.github.cchenxi.hmily.demo.common.d2r.mapper.AccountDollarMapper;
import io.github.cchenxi.hmily.demo.common.d2r.mapper.AccountRmbMapper;
import io.github.cchenxi.hmily.demo.common.d2r.model.D2rTransRequest;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Date: 2020-12-19
 *
 * @author chenxi
 */
@DubboService
@Slf4j
public class D2rTransServiceImpl implements D2rTransService {
    @Resource
    private AccountDollarMapper accountDollarMapper;

    @Resource
    private AccountRmbMapper accountRmbMapper;

    /**
     * 1. 美元账户 余额-1 冻结+1
     * 2. 人民币账户 余额+0 冻结+7
     */
    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean trans(D2rTransRequest transRequest) {
        boolean updateDollar = accountDollarMapper.update(transRequest) > 0;
        boolean updateRmb = accountRmbMapper.update(transRequest) > 0;
        return updateDollar && updateRmb;
    }

    /**
     * confirm
     * 1. 美元账户 冻结-1
     * 2. 人民币账户 余额+7 冻结-7
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(D2rTransRequest transRequest) {
        boolean confirmDollar = accountDollarMapper.confirm(transRequest) > 0;
        boolean confirmRmb = accountRmbMapper.confirm(transRequest) > 0;
        return confirmDollar && confirmRmb;
    }

    /**
     * cancel
     * 1. 美元账户 余额+1 冻结-1
     * 2. 人民币账户 冻结-7
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(D2rTransRequest transRequest) {
        boolean cancelDollar = accountDollarMapper.cancel(transRequest) > 0;
        boolean cancelRmb = accountRmbMapper.cancel(transRequest) > 0;
        return cancelDollar && cancelRmb;
    }
}
