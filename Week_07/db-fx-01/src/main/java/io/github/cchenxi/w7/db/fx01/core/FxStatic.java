package io.github.cchenxi.w7.db.fx01.core;

/**
 * Date: 2020-12-02
 *
 * @author chenxi
 */
public final class FxStatic {
    private static final ThreadLocal<String> DATASOURCE_ROUTING_TL = new ThreadLocal<>();

    public static String getDataSourceRouting() {
        return DATASOURCE_ROUTING_TL.get();
    }

    public static void setDataSourceRouting(String routingKey) {
        DATASOURCE_ROUTING_TL.set(routingKey);
    }

    public static void clearDataSourceRouting() {
        DATASOURCE_ROUTING_TL.remove();
    }
}
