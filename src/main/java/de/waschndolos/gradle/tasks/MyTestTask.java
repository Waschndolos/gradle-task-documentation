package de.waschndolos.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class MyTestTask extends DefaultTask {

    @Input
    Property<String> in = getProject().getObjects().property(String.class);

    @TaskAction
    public void execute() {
        System.out.println("do something");
    }
}
