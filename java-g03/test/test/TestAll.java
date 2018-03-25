package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {

                test.BobCorrectTest.class,
                test.BobFoutiefTest.class,
                test.VakTest.class,
                test.BobCorrectTest.class,
                test.SessieTest.class,
                test.DoelstellingscodeTest.class,
                test.OefeningTest.class

        })

public class TestAll {
}



