package org.acme.quickstart.amqp;

import io.smallrye.reactive.messaging.annotations.Stream;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A simple resource retrieving the "in-memory" "my-data-stream" and sending the items as server-sent events.
 */
@Path("/prices")
public class PriceResource {

    @Inject
    @Stream("my-data-stream") Publisher<String> prices;

    @Inject
    PriceGenerator generator;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        generator.generateString("test");
        return "hello";
    }



    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> stream() {
        return prices;
    }
}
