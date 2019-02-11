package co.matrixdevelopment.solidskies.logging;

import static co.matrixdevelopment.solidskies.Methods.tell;
import co.matrixdevelopment.solidskies.SolidSkies;
import org.bukkit.Bukkit;

public class LogMessages {

    public static void log(String... messages) {
        for (final String message : messages)
            log(message);
    }

    public static void log(String messages) {
        tell(Bukkit.getConsoleSender(), "[" + SolidSkies.getInstance().getName() + "] " + messages);
    }
}
