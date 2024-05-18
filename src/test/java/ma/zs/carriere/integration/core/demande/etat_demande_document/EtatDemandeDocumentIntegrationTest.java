package ma.zs.carriere.integration.core.demande.etat-demande-document;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EtatDemandeDocumentIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EtatDemandeDocumentHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
