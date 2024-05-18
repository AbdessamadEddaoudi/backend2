package ma.zs.carriere.integration.core.avancement.avancement;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AvancementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AvancementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
