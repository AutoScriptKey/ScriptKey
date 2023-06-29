package org.scriptkey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scriptkey.common.ScriptKeyInfo;

import java.util.Arrays;
import java.util.Objects;

/**
 * Main class for the AutoScriptKey launcher, and the main
 * entry point for code execution. Where setup and execution
 * of the main application is handled according to user input.
 */
public class Main {

    private static final Logger LOG = LogManager.getLogger("LAUNCHER");

    private static final String APP_VERSION = ScriptKeyInfo.PROPERTIES.VERSION.getAsString();

    private Main(Arguments arguments) {
        this.arguments = Objects.requireNonNull(arguments);
    }

    private final Arguments arguments;

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
                "Application Version: " + APP_VERSION,
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
     */
    public static void main(String[] args) {
        System.out.println("Running AutoScriptKey Launcher...");
        Main main = new Main(new Arguments(args));
        main.printRuntimeInfo();
    }
}