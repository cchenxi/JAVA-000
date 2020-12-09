package io.github.cchenxi.shardingdemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Date: 2020-12-09
 *
 * @author chenxi
 */
@Data
@Accessors(chain = true)
public class Order {
    private BigInteger pkId;
    private String orderNo;
    private String orderStatus;
    private BigInteger buyerId;
    private BigInteger skuId;
    private BigDecimal skuPrice;
    private String orderTime;
    private String payTime;
    private String dispatchTime;
    private String receiveTime;
    private String createTime;
    private String updateTime;
    private int isDeleted;
}
