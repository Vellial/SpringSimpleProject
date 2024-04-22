package dao;

import general.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createNewUser(Long id, String name) {
        // create user in DB
        this.jdbcTemplate.update(
                "insert into users (Id, Name) values (?, ?)",
                id, name);
    }

    public void updateUser(Long id, String name) {
        // update in db
        this.jdbcTemplate.update(
                "update users set Name = ? where Id = ?",
                name, id);
    }

    public User getUserById(Long id) {
        // get user from db

        return jdbcTemplate.queryForObject(
                "select name from users where id = ?",
                (resultSet, rowNum) -> {
                    User newUser = new User();
                    newUser.setUsername(resultSet.getString("Name"));
                    newUser.setId(id);
                    return newUser;
                },
                id);

    }

    public List<User> getAllUsers() {
        return this.jdbcTemplate.query(
                "select * from users",
                (resultSet, rowNum) -> {
                    User user = new User();
                    user.setId(resultSet.getLong("Id"));
                    user.setUsername(resultSet.getString("Name"));
                    return user;
                });
    }

    public void deleteUserById(Long id) {
        // delete user from db
        this.jdbcTemplate.update(
                "delete from users where Id = ?",
                id);
    }

}
