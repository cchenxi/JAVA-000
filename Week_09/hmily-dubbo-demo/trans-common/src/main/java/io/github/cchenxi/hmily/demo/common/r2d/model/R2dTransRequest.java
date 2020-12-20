package io.github.cchenxi.hmily.demo.common.r2d.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 人民币兑换美元入参
 * Date: 2020-12-19
 *
 * @author chenxi
 */
@Data
public class R2dTransRequest implements Serializable {
    private static final long serialVersionUID = 8515933876538217541L;
    /**
     * 用户
     */
    private String userId;

    /**
     * 人民币金额
     */
    private BigDecimal rmbAmount;

    private BigDecimal rate;
}
