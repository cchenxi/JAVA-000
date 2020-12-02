package io.github.cchenxi.w7.db.fx01.aop;

import io.github.cchenxi.w7.db.fx01.core.DataSource;
import io.github.cchenxi.w7.db.fx01.core.DataSourceType;
import io.github.cchenxi.w7.db.fx01.core.FxStatic;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Date: 2020-12-02
 *
 * @author chenxi
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class DataSourceRoutingAspect {
    @Value("${datasource.slave.count}")
    private int slaveCount;

    @Around("execution(public * io.github.cchenxi..*.service..*.*(..)) && @annotation(io.github.cchenxi.w7.db.fx01.core.DataSource) && @annotation(dataSource)")
    public Object setDataSource(ProceedingJoinPoint joinPoint, DataSource dataSource) throws Throwable {
        DataSourceType dataSourceType = dataSource.value();
        try {
            if (DataSourceType.SLAVE.getValue().equals(dataSourceType.getValue())) {
                FxStatic.setDataSourceRouting(loadBalance());
            } else {
                FxStatic.setDataSourceRouting(dataSourceType.getValue());
            }
            return joinPoint.proceed();
        } finally {
            FxStatic.clearDataSourceRouting();
        }
    }

    private String loadBalance() {
        int random = new Random().nextInt(slaveCount);
        int index = random % slaveCount;
        return "slave" + (index + 1);
    }
}
