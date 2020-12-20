package io.github.cchenxi.hmily.demo.common.d2r.mapper;

import io.github.cchenxi.hmily.demo.common.d2r.model.D2rTransRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * 人民币账户
 * Date: 2020-12-19
 *
 * @author chenxi
 */
public interface AccountRmbMapper {
    @Insert("insert into t_account_rmb (user_id, balance, freeze_amount, create_time, update_time) " +
            "values " +
            "(#{userId}, 0, #{dollarAmount} * #{rate}, now(), now())")
    int update(D2rTransRequest transRequest);

    @Update("update t_account_rmb set balance = balance + (#{dollarAmount} * #{rate}), " +
            "freeze_amount = freeze_amount - (#{dollarAmount} * #{rate}) " +
            "where user_id = #{userId}")
    int confirm(D2rTransRequest transRequest);

    @Update("update t_account_rmb set freeze_amount = freeze_amount - (#{dollarAmount} * #{rate}) where user_id = #{userId}")
    int cancel(D2rTransRequest transRequest);
}
