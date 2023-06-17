package org.scriptkey;

import java.util.Objects;

/**
 * Main class for the launcher.
 * This is where we figure out
 * what the user wants, and where
 * we set everything up.
 */
public class Main {

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
        System.out.println();
        System.out.println(this.arguments);

        System.out.println("Java: " + System.getProperty("java.vendor.version")
                + " (" + System.getProperty("java.runtime.version") + ")");
        System.out.println("Run Directory: " + System.getProperty("user.dir"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Architecture: " + System.getProperty("os.arch"));
        System.out.println();
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
        System.out.println("Running AutoScriptKey...");
        Main main = new Main(new Arguments(args));
        main.printRuntimeInfo();
    }
}