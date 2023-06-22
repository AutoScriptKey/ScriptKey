package org.scriptkey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

/**
 * Helper class for command line arguments. Splits
 * up a standard String of arguments into file paths
 * and startup options.
 */
@SuppressWarnings("unused")
public class Arguments {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * The prefix put before arguments which donate
     * a boolean flag.
     * E.g. -useFlags
     */
    private static final String FLAG_PREFIX = "-";

    /**
     * The prefix put before arguments which specify
     * a key and a value.
     * E.g. --key=value
     */
    private static final String OPTION_PREFIX = "--";

    /**
     * Prefix for arguments which specify the name of a
     * file, for specific types of files.
     */
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
                    LOG.error("Invalid argument: " + arg);
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
                    LOG.error("Invalid file argument: " + arg, e);
                }
            }
        }
    }

    public File[] getFiles() {
        return this.files.toArray(new File[0]);
    }

    public int getFileCount() {
        return this.files.size();
    }

    public boolean hasFiles() {
        return getFileCount() > 0;
    }

    public String[] getFlags() {
        return this.flags.toArray(new String[0]);
    }

    public boolean hasFlag(String flag) {
        return this.flags.contains(flag);
    }

    public int getFlagCount() {
        return this.flags.size();
    }

    public boolean hasFlags() {
        return getFileCount() > 0;
    }

    @SuppressWarnings("unchecked")
    public Map.Entry<String, Object>[] getOptions () {
        return options.entrySet().toArray(new Map.Entry[0]);
    }

    public Object getOption(String name) {
        if(options.containsKey(name)) {
            return options.get(name);
        }

        return null;
    }

    public int getOptionCount() {
        return this.options.size();
    }

    public boolean hasOptions() {
        return getOptionCount() > 0;
    }

    public boolean hasOption(String name) {
        return this.options.containsKey(name);
    }

    @SuppressWarnings("unchecked")
    public Map.Entry<String, File>[] getNamedFiles () {
        return namedFiles.entrySet().toArray(new Map.Entry[0]);
    }

    public File getNamedFile(String name) {
        if(namedFiles.containsKey(name)) {
            return namedFiles.get(name);
        }

        return null;
    }

    public int getNamedFileCount() {
        return this.namedFiles.size();
    }

    public boolean hasNamedFiles() {
        return getNamedFileCount() > 0;
    }

    public boolean hasNamedFile(String name) {
        return this.namedFiles.containsKey(name);
    }

    @Override
    public String toString() {
        return "Arguments: " + Arrays.toString(arguments);
    }
}
