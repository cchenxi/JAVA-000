package io.github.cchenxi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC原生接口操作
 * Date: 2020-11-19
 *
 * @author chenxi
 */
public class OriginalJdbc {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_training";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    /**
     * 使用JDBC 原生接口 测试简单增删改查
     *
     * 建表语句 Week_05/db-demo/src/main/resources/create_table.sql
     *
     * @throws SQLException
     */
    public void testOperation() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        prepareData(connection);
        executeSelect(connection);
        executeUpdate(connection);
        executeSelect(connection);
        clearData(connection);
        connection.close();
    }

    private void prepareData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String insertSql = "insert into user (`name`, `password`, `phone`, `nick_name`) " +
                "values " +
                "('c1', '123456', '13813913701', 'c11')," +
                "('c2', '123456', '13813913702', 'c12')," +
                "('c3', '123456', '13813913703', 'c13')";
        statement.execute(insertSql);

        statement.close();
    }

    private void executeSelect(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnValue = resultSet.getString(columnName);
                System.out.println(columnName + " : " + columnValue);
            }
            System.out.println("-----------------------------------");
        }

        statement.close();
    }

    private void executeUpdate(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "update user " +
                "set nick_name = 'c22' " +
                "where name = 'c2'";
        statement.executeUpdate(sql);
        statement.close();
    }

    private void clearData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from user where 1 = 1";
        statement.execute(sql);
        statement.close();
    }

    public static void main(String[] args) throws SQLException {
        new OriginalJdbc().testOperation();
    }
}
