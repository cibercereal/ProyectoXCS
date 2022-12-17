package es.uvigo.esei.dgss.teama.microstories.service.util.security;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import java.util.function.Supplier;

/**
 * The class to test with an author role.
 */
@Stateless(name = "author-caller")
@RunAs("author")
@PermitAll
public class AuthorRollerCaller implements RoleCaller {

    public <V> V call(Supplier<V> supplier) {
        return supplier.get();
    }

    public void run(Runnable run) {
        run.run();
    }

    public <V> V throwingCall(ThrowingSupplier<V> supplier) throws Throwable {
        return supplier.get();
    }

    public void throwingRun(ThrowingRunnable run) throws Throwable {
        run.run();
    }
}
