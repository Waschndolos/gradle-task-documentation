package de.waschndolos.gradle.processor;


import org.joor.CompileOptions;
import org.joor.Reflect;
import org.joor.ReflectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskProcessorTest {

    @Test
    void testProcessing() {


        TaskProcessor sut = new TaskProcessor();

        try {
            Reflect.compile(
                    "de.waschndolos.tasks.MyTestTask",
                    """
                            package de.waschndolos.gradle.tasks;
                            
                            import org.gradle.api.DefaultTask;
                            import org.gradle.api.provider.Property;
                            import org.gradle.api.tasks.Input;
                            import org.gradle.api.tasks.TaskAction;
                            
                            public class MyTestTask extends DefaultTask {
                            
                                @Input
                                Property<String> in = getProject().getObjects().property(String.class);
                            
                                MyTestTask() {
                                }
                            
                                @TaskAction
                                public void execute() {
                                    System.out.println(\"do something\");
                                }
                            }""",
                    new CompileOptions().processors(sut)
            ).create().get();
            Assertions.assertTrue(sut.processed);
        } catch (ReflectException expected) {
            Assertions.fail();
        }
    }


}