package ma.zs.carriere.integration.core.demande.demande-document;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DemandeDocumentIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DemandeDocumentHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
