package io.github.cchenxi.w7.db.fx01.aop;

import io.github.cchenxi.w7.db.fx01.core.DataSource;
import io.github.cchenxi.w7.db.fx01.core.DataSourceType;
import io.github.cchenxi.w7.db.fx01.core.FxStatic;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Date: 2020-12-02
 *
 * @author chenxi
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class DataSourceRoutingAspect {
    @Around("execution(public * io.github.cchenxi..*.service..*.*(..)) && @annotation(io.github.cchenxi.w7.db.fx01.core.DataSource) && @annotation(dataSource)")
    public Object setDataSource(ProceedingJoinPoint joinPoint, DataSource dataSource) throws Throwable {
        DataSourceType dataSourceType = dataSource.value();
        try {
            FxStatic.setDataSourceRouting(dataSourceType.getValue());
            return joinPoint.proceed();
        } finally {
            FxStatic.clearDataSourceRouting();
        }
    }
}
