package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {test.VakTest.class
                , test.OefeningBeheerderTest.class,
                test.BobCorrectTest.class,
                test.BobFoutiefTest.class,
                test.BobfilterenTesten.class
        })

public class TestAll {
}



