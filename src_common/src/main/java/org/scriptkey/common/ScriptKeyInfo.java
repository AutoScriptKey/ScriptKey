package org.scriptkey.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Common utility/helper class for obtaining basic properties or other information
 * on the Application as a whole, such as version number or display name, and made
 * available to and shared between all projects/subprojects which have "src_common"
 * as a dependency.
 */
public final class ScriptKeyInfo {

    /**
     * Static reference to semi-singleton ScriptKeyInfo class object.
     */
    public static final ScriptKeyInfo INFO = new ScriptKeyInfo();

    private final Properties appProperties;

    private ScriptKeyInfo() {
        String fileName = "app.cfg";
        this.appProperties = new Properties();

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(fileName);
            appProperties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Error attempting to read internal config file: " + fileName);
        }
    }

    public Object getProp(String property) {
        return appProperties.getProperty(property);
    }

    // ################### Static function references ###################

    /**
     * Obtains the value of a property defined in the internal file "app.cfg",
     * from its property key.
     *
     * @see PROPERTIES#get()
     * @see ScriptKeyInfo#getProp(String)  
     * @param property the full name of the property. E.g. "property.key"
     * @return the value of the property, or {@code null} if the property
     * was not found.
     */
    public static Object getProperty(String property) {
        return INFO.getProp(property);
    }

    // ################### Internal Classes ###################

    /**
     * List of (most) common properties defined in "app.cfg", as an enum list,
     * with utility functions to quickly obtain the value of the property.
     * <p>
     * Usage:
     * <code>
     *     String version = ScriptKeyInfo.PROPERTIES.VERSION.getAsString();
     * </code>
     */
    public enum PROPERTIES {

        /**
         * The display name of the application. Defined in "app.cfg", and meant primarily for display purposes.
         */
        DISPLAY_NAME("autoscriptkey.name"),

        /**
         * The current version number of <b>this release</b> of the application.
         * Defined in "app.cfg".
         */
        VERSION("autoscriptkey.version");

        private final String property;

        PROPERTIES(String property) {
            this.property = Objects.requireNonNull(property);
        }

        /**
         * Obtains the value (as an Object) of this property,
         * defined in "app.cfg".
         *
         * @see #getAsString()
         * @return the value of the property as an Object, or {@code null}
         * if the property is nonexistent (for some reason).
         */
        public Object get() {
            return getProperty(this.property);
        }

        /**
         * Obtains the value of this property, defined in "app.cfg", and cast to a {@link String}.
         *
         * @see #get()
         * @return the value of the property as a String, or {@code null}
         * if the property is nonexistent (for some reason).
         */
        public String getAsString() {
            return String.valueOf(get());
        }
    }
}
