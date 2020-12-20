package io.github.cchenxi.hmily.demo.common.r2d.api;

import io.github.cchenxi.hmily.demo.common.r2d.model.R2dTransRequest;
import org.dromara.hmily.annotation.Hmily;

/**
 * 人民币兑换美元业务api
 * Date: 2020-12-19
 *
 * @author chenxi
 */
public interface R2dTransService {
    /**
     * 人民币兑换美元
     * @param transRequest
     * @return
     */
    @Hmily
    boolean trans(R2dTransRequest transRequest);
}
