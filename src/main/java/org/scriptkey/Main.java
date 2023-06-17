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
        printOutRuntimeInfo();
    }

    private void printOutRuntimeInfo() {
        System.out.println(this.arguments);
    }

    public static void main(String[] args) {
        System.out.println("Running AutoScriptKey...");
        Main main = new Main(new Arguments(args));
    }
}