package io.github.cchenxi.hmily.demo.common.d2r.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 美元兑换人民币入参
 * Date: 2020-12-19
 *
 * @author chenxi
 */
@Data
public class D2rTransRequest implements Serializable {
    private static final long serialVersionUID = 8584330081426666360L;
    /**
     * 用户
     */
    private String userId;

    /**
     * 美元金额
     */
    private BigDecimal dollarAmount;

    /**
     * 汇率
     */
    private BigDecimal rate;
}
