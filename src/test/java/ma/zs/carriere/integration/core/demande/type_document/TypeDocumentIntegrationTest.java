package ma.zs.carriere.integration.core.demande.type-document;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeDocumentIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeDocumentHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
