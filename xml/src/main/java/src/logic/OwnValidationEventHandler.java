package src.logic;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OwnValidationEventHandler implements ValidationEventHandler {

    @Override
    public boolean handleEvent(ValidationEvent event) {
//		System.out.println("\nEVENT");
//		System.out.println("SEVERITY:  " + event.getSeverity());
//		System.out.println("MESSAGE:  " + event.getMessage());
//		System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
//		System.out.println("LOCATOR");
//		System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
//		System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
//		System.out.println("    OFFSET:  " + event.getLocator().getOffset());
//		System.out.println("    OBJECT:  " + event.getLocator().getObject());
//		System.out.println("    NODE:  " + event.getLocator().getNode());
//		System.out.println("    URL:  " + event.getLocator().getURL());

        Alert info = new Alert(AlertType.ERROR);
        info.setTitle("Błąd walidacji");
        info.setHeaderText("Błąd walidacji.");
        info.setContentText(event.getMessage());
        info.showAndWait();
        return true;
    }
}