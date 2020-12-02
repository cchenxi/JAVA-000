package io.github.cchenxi.w7.db.fx01.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * Date: 2020-12-02
 *
 * @author chenxi
 */
@Data
public class Customer {
    private BigInteger pkId;
    private String username;
    private String password;
    private String nickName;
}
