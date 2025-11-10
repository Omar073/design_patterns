// Proxy Pattern – Logging Proxy Example
// Dynamic proxy adding cross-cutting logging functionality

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Service {
    void doWork();

    String say(String name);
}

class RealService implements Service {
    public void doWork() {
        System.out.println("Doing work...");
    }

    public String say(String name) {
        return "Hello, " + name;
    }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;

    LoggingHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[LOG] Calling " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("[LOG] Done " + method.getName());
        return result;
    }
}

public class LoggingProxyDemo {
    public static void main(String[] args) {
        System.out.println("== Logging (Dynamic) Proxy ==");
        Service real = new RealService();
        Service logged = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(),
                new Class<?>[]{Service.class},
                new LoggingHandler(real));
        logged.doWork();
        System.out.println(logged.say("Omar"));
    }
}

