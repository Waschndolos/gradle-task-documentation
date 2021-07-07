package de.waschndolos.gradle.processor;

import de.waschndolos.gradle.documentation.Parameter;
import de.waschndolos.gradle.documentation.TaskParameter;
import org.gradle.api.tasks.Input;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({
        "org.gradle.api.tasks.Input",
        "org.gradle.api.tasks.InputFile",
        "org.gradle.api.tasks.InputFiles",
        "org.gradle.api.tasks.OutputFile",
        "org.gradle.api.tasks.OutputFiles"
})
public class TaskProcessor extends AbstractProcessor {

    public boolean processed;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        List<Parameter> parameters = new ArrayList<>();

        for (Element annotation : roundEnv.getElementsAnnotatedWith(Input.class)) {

            Element enclosingElement = annotation.getEnclosingElement();
            if (enclosingElement instanceof VariableElement) {
                VariableElement variableElement = (VariableElement) enclosingElement;
                parameters.add(new TaskParameter(variableElement.getSimpleName().toString(), null, variableElement.getClass()));
            }
        }
        processed = true;

        return false;
    }
}
