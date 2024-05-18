package ma.zs.carriere.integration.core.demande.template-document;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TemplateDocumentIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TemplateDocumentHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
