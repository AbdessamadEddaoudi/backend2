package ma.zs.carriere.integration.core.avancement.echelon;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EchelonIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EchelonHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
