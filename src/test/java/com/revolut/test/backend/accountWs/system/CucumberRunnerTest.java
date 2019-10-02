package com.revolut.test.backend.accountWs.system;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features="classpath:features/",
    glue={
            "com.revolut.test.backend.accountWs.system.step",
            "com.revolut.test.backend.accountWs.system.datatables"
    },
    plugin={"pretty", "html:target/cucumber"}
)
public class CucumberRunnerTest {
}
