package io.github.cchenxi.hmily.demo.common.d2r.mapper;

import io.github.cchenxi.hmily.demo.common.d2r.model.D2rTransRequest;
import org.apache.ibatis.annotations.Update;

/**
 * 美元账户
 * Date: 2020-12-19
 *
 * @author chenxi
 */
public interface AccountDollarMapper {
    @Update("update t_account_dollar set balance = balance - #{dollarAmount}, " +
            "freeze_amount = freeze_amount + #{dollarAmount} " +
            "where user_id = #{userId} and balance > 0")
    int update(D2rTransRequest transRequest);

    @Update("update t_account_dollar set freeze_amount = freeze_amount - #{dollarAmount} where user_id = ${userId}")
    int confirm(D2rTransRequest transRequest);

    @Update("update t_account_dollar set balance = balance + #{dollarAmount}, " +
            "freeze_amount = freeze_amount - #{dollarAmount} " +
            "where user_id = #{userId} and freeze_amount > 0")
    int cancel(D2rTransRequest transRequest);
}
