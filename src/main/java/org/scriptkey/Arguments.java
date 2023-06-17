package org.scriptkey;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

/**
 * Helper class for command line arguments. Splits
 * up a standard String of arguments into file paths
 * and startup options.
 */
public class Arguments {

    private static final String FLAG_PREFIX = "-";

    private static final String OPTION_PREFIX = "--";

    private static final String FILETYPE_PREFIX = "/";

    private final String[] arguments;

    private final List<String> flags = new ArrayList<>();

    private final Map<String, Object> options = new HashMap<>();

    private final Map<String, File> namedFiles = new HashMap<>();

    private final List<File> files = new ArrayList<>();

    public Arguments(String[] args) {
        this.arguments = Objects.requireNonNull(args);
        parseArgs();
    }

    private void parseArgs() {
        String namedFile = null;

        for(String arg : arguments) {
            if(arg.startsWith(OPTION_PREFIX)) {
                String[] kv = arg.replace(OPTION_PREFIX, "").split("=");
                if(kv.length == 2) {
                    options.put(kv[0], kv[1]);
                } else {
                    System.err.println("Invalid argument: " + arg);
                }
            } else if(arg.startsWith(FLAG_PREFIX)) {
                flags.add(arg.replace(FLAG_PREFIX, ""));
            } else if(arg.startsWith(FILETYPE_PREFIX)) {
                namedFile = FILETYPE_PREFIX.replace(FILETYPE_PREFIX, "");
            } else {
                try {
                    File file = Path.of(arg).toFile();

                    if(namedFile != null) {
                        namedFiles.put(namedFile, file);
                        namedFile = null;
                    } else {
                        files.add(file);

                    }
                } catch (InvalidPathException e) {
                    System.err.println("Invalid file argument: " + arg);
                    System.err.println("Cause: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Arguments: " + Arrays.toString(arguments);
    }
}
