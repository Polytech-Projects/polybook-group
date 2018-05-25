
import com.polytech.config.AppConfig;
import com.polytech.persistence.JdbcNoteRepository;
import com.polytech.web.FeedController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class NotePublicationTest {


    private FeedController feedController;
    private JdbcNoteRepository test ;

    @Before
    public void init() {
        try{
            DataSource ds = new EmbeddedDatabaseBuilder().addScripts("schema.sql").build();
            this.test = new JdbcNoteRepository(ds.getConnection(), new JdbcTemplate(ds)) ;
        } catch (Exception e) {System.out.println("ERREUR") ;}
    }


    @Test
    public void should_post_story() {
        System.out.println("LOL") ;

        test.addUser("test", "test");

        BCryptPasswordEncoder test1 = new BCryptPasswordEncoder() ;

        System.out.println(test1.encode("zeros")) ;

        test1 = new BCryptPasswordEncoder() ;

        System.out.println(test1.encode("zeros")) ;
        //GIVEN
        //String note = "Note test";

        //WHEN
        //feedController.post(note);

        //THEN

        //List<Note> postedNotes = feedController.feed();
        //Assert.assertEquals(Arrays.asList(new Note("Note test")), postedNotes);
    }
}
