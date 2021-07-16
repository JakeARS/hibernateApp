import conference.AppConfig;
import conference.Speaker;
import conference.SpeakerRepository;
import conference.Talk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.ls.LSProgressEvent;

import javax.transaction.Transactional;

import java.sql.Array;
import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

import java.util.Set;
import java.util.logging.Logger;

//import org.apache.log4j.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AppTest {

    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());
    //private static final Logger LOG = Logger.getLogger(AppTest.class);

    @Autowired
    private SpeakerRepository speakerRepository;

    @Before
    @Rollback(false)
    @Commit
    public void setUp() {
        //LOG.setLevel(Level.OFF);
        Speaker evgeny = new Speaker("mr Evgeny Krivosheev");

        Speaker jeka = new Speaker("mr Evgeny Borisov");
        Speaker nikolay = new Speaker("mr Nikolay Alimenkov");
        Speaker baruch = new Speaker("mr Baruch Sadogursky");
        jeka.addTalk(new Talk("The Internals of Spring", "12:30"));
        jeka.addTalk(new Talk("The Spring modules", "17:30"));
        nikolay.addTalk(new Talk("CD JEE7", "18:00"));
        baruch.addTalk(new Talk("AST Groovy", "12:00"));
        baruch.addTalk(new Talk("Making Spring Groovy", "09:00"));
        speakerRepository.save(Arrays.asList(jeka, evgeny, nikolay, baruch));
    }

    @Test
    public void testCount() {
        LOG.info("********* Number of speakers test *********");
        LOG.info("Speakers count: " + speakerRepository.count());
        LOG.info("******* ***************************************");
    }

    @Test
    public void testFindByAll() {
        LOG.info("********* All speakers test: *********");
        Iterable<Speaker> allSpeakers = speakerRepository.getAllSpeakers();
        for (Speaker speaker: allSpeakers) {
            LOG.info(speaker.getName());
        }
        LOG.info("******* ***************************************");
    }

    @Test
    public void testFindByName() {
        LOG.info("********* All talks of Borisov *********");
        Speaker speaker = speakerRepository.findByName("mr Evgeny Borisov").get(0);
        Set<Talk> talks = speaker.getTalks();
        for (Talk talk : talks) {
            LOG.info("talk = " + talk);
        }
        LOG.info("******* ***************************************");
    }

    @Test
    public void testGetWhen() {
        LOG.info("********* All talks of Borisov *********");
        Speaker speaker = speakerRepository.findByName("mr Evgeny Borisov").get(0);
        Set<Talk> talks = speaker.getTalks();
        for (Talk talk : talks) {
            LOG.info("talk time = " + talk.getWhen());
        }
        LOG.info("******* ***************************************");
    }

    /*@After
    public void clean() {
        speakerRepository.deleteAll();
    }*/
}
