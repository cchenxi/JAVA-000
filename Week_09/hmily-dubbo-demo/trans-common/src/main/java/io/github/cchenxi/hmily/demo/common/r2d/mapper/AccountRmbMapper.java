package io.github.cchenxi.hmily.demo.common.r2d.mapper;

import io.github.cchenxi.hmily.demo.common.r2d.model.R2dTransRequest;
import org.apache.ibatis.annotations.Update;

/**
 * 人民币账户
 * Date: 2020-12-19
 *
 * @author chenxi
 */
public interface AccountRmbMapper {
    @Update("update t_account_rmb set balance = balance - #{rmbAmount}, " +
            "freeze_amount = freeze_amount + #{rmbAmount} " +
            "where user_id = #{userId} and balance > 0")
    int update(R2dTransRequest transRequest);

    @Update("update t_account_rmb set freeze_amount = freeze_amount - #{rmbAmount} " +
            "where user_id = #{userId}")
    int confirm(R2dTransRequest transRequest);

    @Update("update t_account_rmb set balance = balance + #{rmbAmount}, " +
            "freeze_amount = freeze_amount - #{rmbAmount} " +
            "where user_id = #{userId} and freeze_zmount > 0")
    int cancel(R2dTransRequest transRequest);
}
