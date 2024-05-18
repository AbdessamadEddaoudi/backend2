package ma.zs.carriere.integration.core.Mandat,employe.mandat;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class MandatIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("MandatHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
