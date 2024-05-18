package ma.zs.carriere.integration.core.commun.entite-admin;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EntiteAdminIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EntiteAdminHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
