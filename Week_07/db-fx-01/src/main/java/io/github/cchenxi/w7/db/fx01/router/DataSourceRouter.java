package io.github.cchenxi.w7.db.fx01.router;

import io.github.cchenxi.w7.db.fx01.core.FxStatic;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Date: 2020-12-02
 *
 * @author chenxi
 */
public class DataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return FxStatic.getDataSourceRouting();
    }
}
