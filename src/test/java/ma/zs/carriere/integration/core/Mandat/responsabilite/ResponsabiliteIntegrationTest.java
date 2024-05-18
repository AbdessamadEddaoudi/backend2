package ma.zs.carriere.integration.core.Mandat.responsabilite;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ResponsabiliteIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ResponsabiliteHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
