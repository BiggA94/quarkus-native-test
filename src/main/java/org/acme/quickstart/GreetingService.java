package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public TestObject getTestObject() {
        return new TestObject("TestString", true, 42);
    }

}
