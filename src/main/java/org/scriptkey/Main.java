package org.scriptkey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Objects;

/**
 * Main class for the launcher.
 * This is where we figure out
 * what the user wants, and where
 * we set everything up.
 */
public class Main {

    private static final Logger LOG = LogManager.getLogger("LAUNCHER");

    private final Arguments arguments;

    private Main(Arguments arguments) {
        this.arguments = Objects.requireNonNull(arguments);
    }

    /**
     * Prints out information about the current
     * runtime and user specific details.
     * Such as Java and OS version, and runtime path.
     */
    private void printRuntimeInfo() {
        String[] runtimeProperties = new String[] {
                "Java: " + System.getProperty("java.vendor.version")
                        + " (" + System.getProperty("java.runtime.version") + ")",
                this.arguments.toString(),
                "Run Directory: " + System.getProperty("user.dir"),
                "OS: " + System.getProperty("os.name"),
                "Architecture: " + System.getProperty("os.arch"),
                "Console: " + System.console()
        };

        StringBuilder runtimeInfo = new StringBuilder();
        Arrays.asList(runtimeProperties).forEach(prop -> runtimeInfo.append("\t").append(prop).append("\n"));

        LOG.info("Runtime Info: \n" + runtimeInfo);
    }

    /**
     * Launcher main method, true entry point
     * for the application.
     * <p>
     * This defines the startup procedure for
     * the launcher.
     *
     * @param args command line arguments.
     * */
    public static void main(String[] args) {
        System.out.println("Running AutoScriptKey Launcher...");
        Main main = new Main(new Arguments(args));
        main.printRuntimeInfo();
    }
}