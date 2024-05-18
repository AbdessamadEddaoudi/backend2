package ma.zs.carriere.integration.core.commun.employe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EmployeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EmployeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
