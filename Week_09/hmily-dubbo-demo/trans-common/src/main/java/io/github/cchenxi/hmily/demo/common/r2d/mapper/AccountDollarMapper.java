package io.github.cchenxi.hmily.demo.common.r2d.mapper;

import io.github.cchenxi.hmily.demo.common.r2d.model.R2dTransRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * 美元账户
 * Date: 2020-12-19
 *
 * @author chenxi
 */
public interface AccountDollarMapper {
    @Insert("insert into t_account_dollar (user_id, balance, freeze_amount, create_time, update_time) " +
            "values " +
            "(#{userId}, 0, (#{rmbAmount} / #{rate}), now(), now())")
    int update(R2dTransRequest transRequest);

    @Update("update t_account_dollar set balance = balance + (#{rmbAmount} / #{rate}), " +
            "freeze_amount = freeze_amount - (#{rmbAmount} / #{rate}) " +
            "where user_id = #{userId}")
    int confirm(R2dTransRequest transRequest);

    @Update("update t_account_dollar set freeze_amount = freeze_amount - (#{rmbAmount} / #{rate}) " +
            "where user_id = #{userId}")
    int cancel(R2dTransRequest transRequest);
}
