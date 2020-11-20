package io.github.cchenxi.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Date: 2020-11-19
 *
 * @author chenxi
 */
@Repository
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(User user) {
        jdbcTemplate.update("insert into user (`name`, `password`, `phone`, `nick_name`) values (?, ?, ?, ?)",
                user.getName(), user.getPassword(), user.getPhone(), user.getNickName());
    }

    public void clear() {
        jdbcTemplate.execute("delete from user where 1 = 1");
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", (resultSet, i) -> {
            User user = new User();
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            user.setName(name);
            user.setPassword(password);

            return user;
        });
    }

    public void update(String name, String password) {
        jdbcTemplate.update("update user set password = ? where name = ?", password, name);
    }
}
