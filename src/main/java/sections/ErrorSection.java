package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ErrorSection {

    private final WebElement errorSection;

    public ErrorSection(WebElement errorElement) {
        errorSection = errorElement;
    }

    public List<String> getErrorMessages() {
        List<WebElement> errorElements = errorSection.findElements(By.cssSelector("ul > li"));

        List<String> errorMessages = new ArrayList<>();

        for (WebElement errorMessage : errorElements) {
            String text = errorMessage.getText();
            errorMessages.add(text);
        }
        return errorMessages;
    }
}
