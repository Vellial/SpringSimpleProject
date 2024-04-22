package configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.UserService;

@Configuration
public class AppConfig {

    @Bean
    public HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.addDataSourceProperty("url", "jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setMaximumPoolSize(4);
        config.addDataSourceProperty("oracle.jdbc.defaultConnectionValidation", "LOCAL");
        return new HikariDataSource(config);
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDao(getDataSource());
    }

    @Bean
    public UserService getUserService() {
        return new UserService(getUserDao());
    }
}
