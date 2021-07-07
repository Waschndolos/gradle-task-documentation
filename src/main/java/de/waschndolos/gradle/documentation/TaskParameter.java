package de.waschndolos.gradle.documentation;

public class TaskParameter implements Parameter {

    private final String name;
    private final String defaultValue;
    private final Class<?> type;


    public TaskParameter(String name, String defaultValue, Class<?> type) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Class getType() {
        return type;
    }
}
