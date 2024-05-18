package ma.zs.carriere.integration.core.commun.diplome;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DiplomeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DiplomeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
