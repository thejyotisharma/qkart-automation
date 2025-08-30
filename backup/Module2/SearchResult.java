package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;



    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;


    }


    /*
     * Return title of the parentElement denoting the card content section of a search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        WebElement title = parentElement
                .findElement(By.xpath("//p[contains(text(), 'YONEX Smash Badminton Racquet')]"));
        titleOfSearchResult = title.getText();
        System.out.println(titleOfSearchResult);
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizeChart =
                    parentElement.findElement(By.xpath("//button[contains(text(), 'Size chart')]"));
            sizeChart.click();
            System.out.println("sizeChart is clickable");
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of the
             * element is "SIZE CHART". If the text "SIZE CHART" matches for the element, set status
             * = true , else set to false
             */
            List<WebElement> sizeChart = parentElement
                    .findElements(By.xpath("//button[contains(text(), 'Size chart')]"));

            for (WebElement result : sizeChart) {
                System.out.println(result.isDisplayed());
                String text = result.getText();
                if (text.toUpperCase().contains("SIZE CHART")) {
                    status = true;
                }
            }

            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders,
            List<List<String>> expectedTableBody, WebDriver driver) {
        Boolean status = true;
        try {
            Thread.sleep(5000);
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table header in
             * the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body in the
             * same order
             */
            List<WebElement> tableHeaders = driver.findElements(By.className("MuiTableCell-head"));
            for (int i = 0; i < expectedTableHeaders.size(); i++) {
                String f = tableHeaders.get(i).getText();
                if (!f.equals(expectedTableHeaders.get(i))) {
                    System.out.println("Headers Test: FAIL "+f+ " != "+expectedTableHeaders.get(i));
                    return false;
                }
            }
        
            List<WebElement> tableBodyRows = driver.findElements(By.className("css-171yt5d"));      
            int currentRowNumber = 0;
            for (WebElement row : tableBodyRows) {
                List<WebElement> columns = row.findElements(By.className("MuiTableCell-body"));
                List<String> expectedBody = expectedTableBody.get(currentRowNumber);

                for (int i = 0; i < expectedBody.size(); i++) {
                    String f = columns.get(i).getText();
                    if (!f.equals(expectedBody.get(i))) {
                        System.out.println("Body Test: FAIL "+f+ " != "+expectedBody.get(i));
                        return false;
                    }
                }
                currentRowNumber++;   
            }

            // List<WebElement> tableBody = driver.findElements(By.className("MuiTableRow-root
            // css-171yt5d"));
            System.out.println("contents of Header & Body: MATCH");
            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents "+ e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            WebElement dropdown = driver.findElement(By.id("uncontrolled-native"));
            if (dropdown.isDisplayed()) {
                System.out.println("Dropdown is displayed: PASS");
                status = true;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}
