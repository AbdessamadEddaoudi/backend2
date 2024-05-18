package ma.zs.carriere.integration.core.Mandat.responsabilite-detail;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ResponsabiliteDetailIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ResponsabiliteDetailHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
