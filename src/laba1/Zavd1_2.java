package laba1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.lang.reflect.*;

public class Zavd1_2 extends Application {
    private TextField inputField;
    private TextArea outputArea;
    @Override
    public void start(Stage primaryStage) {
        inputField = new TextField();
        inputField.setPromptText("Enter class name");
        inputField.setPrefWidth(200);
        Button descriptionButton = new Button("Description");
        descriptionButton.setOnAction(e -> showTypeDescription());
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            inputField.clear();
            outputArea.clear();
        });
        HBox buttonBox = new HBox(10, descriptionButton, clearButton);
        buttonBox.setPadding(new Insets(10));
        outputArea = new TextArea();
        outputArea.setEditable(false);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(inputField);
        borderPane.setCenter(outputArea);
        borderPane.setBottom(buttonBox);
        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setTitle("Type Description");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showTypeDescription() {
        String typeName = inputField.getText().trim();
        if (!typeName.isEmpty()) {
            String description = getTypeDescription(typeName);
            outputArea.setText(description);
        } else {
            outputArea.setText("Please enter a class name.");
        }
    }
    private String getTypeDescription(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return getTypeDescription(clazz);
        } catch (ClassNotFoundException e) {
            return "Class not found: " + typeName;
        }
    }
    private String getTypeDescription(Class<?> clazz) {
        StringBuilder description = new StringBuilder();
        description.append("Package: ").append(clazz.getPackage()).append("\n");
        int modifiers = clazz.getModifiers();
        description.append("Modifiers: ").append(Modifier.toString(modifiers)).append("\n");
        description.append("Class: ").append(clazz.getSimpleName()).append("\n");
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            description.append("Superclass: ").append(superclass.getSimpleName()).append("\n");
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            description.append("Implemented interfaces: ");
            for (Class<?> anInterface : interfaces) {
                description.append(anInterface.getSimpleName()).append(", ");
            }
            description.delete(description.length() - 2, description.length()); // Remove trailing comma and space
            description.append("\n");
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            description.append("Fields:\n");
            for (Field field : fields) {
                description.append("\t").append(Modifier.toString(field.getModifiers()))
                        .append(" ").append(field.getType().getSimpleName())
                        .append(" ").append(field.getName()).append("\n");
            }
        }
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length > 0) {
            description.append("Constructors:\n");
            for (Constructor<?> constructor : constructors) {
                description.append("\t").append(Modifier.toString(constructor.getModifiers()))
                        .append(" ").append(constructor.getName()).append("(");
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    description.append(parameterTypes[i].getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        description.append(", ");
                    }
                }
                description.append(")\n");
            }
        }
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length > 0) {
            description.append("Methods:\n");
            for (Method method : methods) {
                description.append("\t").append(Modifier.toString(method.getModifiers()))
                        .append(" ").append(method.getReturnType().getSimpleName())
                        .append(" ").append(method.getName()).append("(");
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    description.append(parameterTypes[i].getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        description.append(", ");
                    }
                }
                description.append(")\n");
            }
        }
        return description.toString();
    }
    public static void main(String[] args) {
        launch(args);
    }
}