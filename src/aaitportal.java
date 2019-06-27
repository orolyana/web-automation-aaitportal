import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
public class aaitportal {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Gecko\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();

        String baseUrl = "https://portal.aait.edu.et";
        String gradeReportUrl = "https://portal.aait.edu.et/Grade/GradeReport";
        String myUsername = "ATR/0799/09";
        String myPassword = "password";

        driver.get(baseUrl);

        driver.findElement(By.id("UserName")).sendKeys(myUsername);
        driver.findElement(By.id("Password")).sendKeys(myPassword);
        driver.findElement(By.className("btn-success")).click();

        driver.navigate().to(gradeReportUrl);

        List<WebElement> rows = driver.findElements(By.tagName("tr"));

        StringBuilder gradeReport;
        gradeReport = new StringBuilder();

        for (WebElement  row:
                rows) {
            for (WebElement td:
                    row.findElements(By.tagName("td"))) {
                gradeReport.append(td.getText()).append("  ");
            }
            gradeReport.append("\n");
        }

        writeToFile(gradeReport.toString());

        driver.close();
    }

    private static void writeToFile(String content) {
        String fileNameToStoreGradeReport = ".\\src\\Grade.txt";

        try {
            Writer writer = new FileWriter(fileNameToStoreGradeReport);
            writer.write(content);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}