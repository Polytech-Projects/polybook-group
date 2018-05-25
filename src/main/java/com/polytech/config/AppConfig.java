package com.polytech.config;

import com.polytech.persistence.JdbcNoteRepository;
import com.polytech.persistence.LoginRepository;
import com.polytech.persistence.NoteRepository;
import com.polytech.services.FeedService;
import com.polytech.services.LoginService;
import com.polytech.services.PublicationService;
import com.polytech.web.FeedController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NoteRepository storyRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) throws SQLException {
        return new JdbcNoteRepository(dataSource.getConnection(), jdbcTemplate);
    }

    @Bean
    public LoginRepository loginRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) throws SQLException {
        return new JdbcNoteRepository(dataSource.getConnection(), jdbcTemplate);
    }

    @Bean
    public LoginService loginService(LoginRepository loginRepository) {
        return new LoginService(loginRepository);
    }

    @Bean
    public FeedService feedService(NoteRepository storyRepository) {
        return new FeedService(storyRepository);
    }

    @Bean
    public PublicationService publicationService(NoteRepository storyRepository) {
        return new PublicationService(storyRepository);
    }

    @Bean
    public FeedController feedController(PublicationService publicationService, FeedService feedService) {
        return new FeedController(publicationService, feedService);
    }

    @Bean
    public JdbcNoteRepository jdbcStoryRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) throws SQLException {
        return new JdbcNoteRepository(dataSource.getConnection(), jdbcTemplate);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                //.addScripts("schema.sql")
                .build();
    }
}
