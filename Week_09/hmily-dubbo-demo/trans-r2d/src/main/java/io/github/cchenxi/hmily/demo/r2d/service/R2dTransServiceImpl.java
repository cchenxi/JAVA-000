package io.github.cchenxi.hmily.demo.r2d.service;

import org.apache.dubbo.config.annotation.DubboService;

import io.github.cchenxi.hmily.demo.common.r2d.api.R2dTransService;
import io.github.cchenxi.hmily.demo.common.r2d.mapper.AccountDollarMapper;
import io.github.cchenxi.hmily.demo.common.r2d.mapper.AccountRmbMapper;
import io.github.cchenxi.hmily.demo.common.r2d.model.R2dTransRequest;
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
public class R2dTransServiceImpl implements R2dTransService {
    @Resource
    private AccountRmbMapper accountRmbMapper;

    @Resource
    private AccountDollarMapper accountDollarMapper;

    /**
     * 2. 人民币账户 余额-7 冻结+7
     * 1. 美元账户 余额+0 冻结+1
     */
    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean trans(R2dTransRequest transRequest) {
        boolean updateRmb = accountRmbMapper.update(transRequest) > 0;
        boolean updateDollar = accountDollarMapper.update(transRequest) > 0;
        return updateRmb && updateDollar;
    }

    /**
     * confirm
     * 2. 人民币账户 冻结-7
     * 1. 美元账户 余额+1 冻结-1
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(R2dTransRequest transRequest) {
        boolean confirmRmb = accountRmbMapper.confirm(transRequest) > 0;
        boolean confirmDollar = accountDollarMapper.confirm(transRequest) > 0;
        return confirmRmb && confirmDollar;
    }

    /**
     * cancel
     * 2. 人民币账户 余额+7 冻结-7
     * 1. 美元账户 冻结-1
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(R2dTransRequest transRequest) {
        boolean cancelRmb = accountRmbMapper.cancel(transRequest) > 0;
        boolean cancelDollar = accountDollarMapper.cancel(transRequest) > 0;
        return cancelRmb && cancelDollar;
    }
}
