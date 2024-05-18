package ma.zs.carriere.integration.core.avancement.echelle;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EchelleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EchelleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
