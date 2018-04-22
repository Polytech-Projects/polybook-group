package com.polytech.config;

import com.polytech.persistence.JdbcNoteRepository;
import com.polytech.persistence.NoteRepository;
import com.polytech.services.FeedService;
import com.polytech.services.PublicationService;
import com.polytech.web.FeedController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public NoteRepository storyRepository(DataSource dataSource) throws SQLException {
        return new JdbcNoteRepository(dataSource.getConnection());
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
    public JdbcNoteRepository jdbcStoryRepository(DataSource dataSource) throws SQLException {
        return new JdbcNoteRepository(dataSource.getConnection());
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                //.addScripts("schema.sql")
                .build();
    }
}
