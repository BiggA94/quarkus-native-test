package org.acme.quickstart.amqp;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * A bean producing random prices every 5 seconds.
 * The prices are written to an AMQP queue (prices). The AMQP configuration is specified in the
 * application configuration.
 */
@ApplicationScoped
public class PriceGenerator {

    //    @Inject
    //    @Stream("generated-price")
    Emitter<String> emitter;

    private Random random = new Random();

    public void generateString(String message) {
        emitter.send(message);
    }

    @Outgoing("generated-price")
    public Flowable<String> generate() {
        return Flowable.interval(5, TimeUnit.SECONDS)
                .map(tick -> {
                    System.out.println(tick.toString());
                    return random.nextInt(100) + ":" + tick;
                });
    }

}
