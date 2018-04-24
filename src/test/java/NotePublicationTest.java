
import com.polytech.config.AppConfig;
import com.polytech.web.FeedController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class NotePublicationTest {


    private FeedController feedController;

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
        feedController = container.getBean(FeedController.class);
    }

    @Ignore
    @Test
    public void should_post_story() {
        //GIVEN
        String note = "Note test";

        //WHEN
        //feedController.post(note);

        //THEN

        //List<Note> postedNotes = feedController.feed();
        //Assert.assertEquals(Arrays.asList(new Note("Note test")), postedNotes);
    }
}
