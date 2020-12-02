package io.github.cchenxi.w7.db.fx01;

import io.github.cchenxi.w7.db.fx01.core.DataSourceType;
import io.github.cchenxi.w7.db.fx01.router.DataSourceRouter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSource config
 * Date: 2020-12-01
 *
 * @author chenxi
 */
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave01")
    public DataSource slave01DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave02DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave02")
    public DataSource slave02DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource masterDataSource) {
        return new JdbcTemplate(masterDataSource);
    }

    @Bean
    public JdbcTemplate slave01JdbcTemplate(@Qualifier("slave01DataSource") DataSource slave01DataSource) {
        return new JdbcTemplate(slave01DataSource);
    }

    @Bean
    public JdbcTemplate slave02JdbcTemplate(@Qualifier("slave02DataSource") DataSource slave02DataSource) {
        return new JdbcTemplate(slave02DataSource);
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slave01DataSource") DataSource slave01DataSource,
                                        @Qualifier("slave02DataSource") DataSource slave02DataSource) {
        DataSourceRouter dataSourceRouter = new DataSourceRouter();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceType.NORMAL.getValue(), masterDataSource);
        dataSourceMap.put(DataSourceType.READONLY.getValue(), slave01DataSource);

        dataSourceRouter.setDefaultTargetDataSource(masterDataSource);
        dataSourceRouter.setTargetDataSources(dataSourceMap);
        return dataSourceRouter;
    }

    @Bean
    public JdbcTemplate dynamicJdbcTemplate(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new JdbcTemplate(dynamicDataSource);
    }
}
