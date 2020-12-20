package io.github.cchenxi.hmily.demo.common.d2r.api;

import io.github.cchenxi.hmily.demo.common.d2r.model.D2rTransRequest;
import org.dromara.hmily.annotation.Hmily;

/**
 * 美元兑换人民币业务api
 * Date: 2020-12-19
 *
 * @author chenxi
 */
public interface D2rTransService {
    /**
     * 美元兑换人民币
     *
     * @param transRequest
     * @return
     */
    @Hmily
    boolean trans(D2rTransRequest transRequest);
}
