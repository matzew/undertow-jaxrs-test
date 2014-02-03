package net.wessendorf.undertow;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.FileResourceManager;
import net.wessendorf.undertow.jaxrs.TestApplication;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.io.File;
import java.util.logging.Logger;

import static io.undertow.Handlers.resource;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args ) {

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        TestApplication ta = new TestApplication();

        ut.deploy(ta);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")

        );

        try {
            Thread.currentThread().join();
        }
        catch (InterruptedException e) {
            logger.info("shutting down");
            ut.stop();
        }
    }
}
