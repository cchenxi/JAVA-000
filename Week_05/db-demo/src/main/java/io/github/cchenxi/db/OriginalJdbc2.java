package io.github.cchenxi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC原生接口操作
 * 使用 事务 和 {@link java.sql.PreparedStatement} 改进 {@link OriginalJdbc}
 *
 * Date: 2020-11-19
 *
 * @author chenxi
 */
public class OriginalJdbc2 {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_training";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    /**
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
        String insertSql = "insert into user (`name`, `password`, `phone`, `nick_name`) " +
                "values " +
                "(?, ?, ?, ?)," +
                "(?, ?, ?, ?)," +
                "(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertSql);

        statement.setString(1, "c1");
        statement.setString(2, "123456");
        statement.setString(3, "13813912345");
        statement.setString(4, "c11");

        statement.setString(5, "c2");
        statement.setString(6, "123456");
        statement.setString(7, "13813912345");
        statement.setString(8, "c22");

        statement.setString(9, "c3");
        statement.setString(10, "123456");
        statement.setString(11, "13813912345");
        statement.setString(12, "c33");

        statement.execute();
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
        String sql1 = "update user " +
                "set nick_name = ? " +
                "where name = ?";

        String sql2 = "update user " +
                "set nick_name = ? " +
                "where name = ?";

        try (PreparedStatement statement1 = connection.prepareStatement(sql1);
             PreparedStatement statement2 = connection.prepareStatement(sql2))
        {
            connection.setAutoCommit(false);

            statement1.setString(1, "ccxx1");
            statement1.setString(2, "c1");

            statement2.setString(1, "ccxx2");
            statement2.setString(2, "c2");

            statement1.executeUpdate();
            statement2.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private void clearData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from user where 1 = 1";
        statement.execute(sql);
        statement.close();
    }

    public static void main(String[] args) throws SQLException {
        new OriginalJdbc2().testOperation();
    }
}
