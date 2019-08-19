package org.acme.quickstart.amqp;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

/**
 * A bean consuming data from the "prices" AMQP queue and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class PriceConverter {

    @Incoming("prices")
    @Outgoing("my-data-stream")
    @Broadcast
    public String process(String message) {
        System.out.println(message);
        return "got message: " + message;
    }
}
