package mlhubprojecttest.StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import mlhubprojecttest.Pages.RegisterPage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import static mlhubprojecttest.Pages.RegisterPage.*;
import static mlhubprojecttest.Utilities.BrowserUtils.waitFor;
import static mlhubprojecttest.Utilities.Driver.driver;

public class RegisterStepDef {

    RegisterPage registerPage = new RegisterPage();

    @Then("User clicks the Sign Up button")
    public void userClicksTheSignUpButton() {
        registerPage.signUpButton.click();
    }

    @Then("User gets new email address")
    public void userGetsNewEmailAddress() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        getMailAddress();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
    }

    @Then("User enters e-mail address and clicks the register button")
    public void userEntersEMailAddressAndClicksTheRegisterButton() {
        registerPage.inputEmail.sendKeys(mail);
        registerPage.registerButton.click();
    }

    @Then("The user verifies that the link sent to the email box comes from {string} and clicks it. Creates the username and password")
    public void theUserVerifiesThatTheLinkSentToTheEmailBoxComesFromAndClicksItCreatesTheUsernameAndPassword(String seviceName) {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        waitFor(3);
        driver.navigate().refresh();
        driver.switchTo().frame(registerPage.iframe);
        Assert.assertTrue(registerPage.serviceName.getText().contains(seviceName));
        registerPage.yopContinueRegistrationButton.click();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[2]);
        waitFor(1);
        registerPage.inputUsername.sendKeys(username);
        waitFor(1);
        registerPage.inputPassword.sendKeys(password);
        waitFor(1);
        registerPage.inputConfirmPassword.sendKeys(password);
        registerPage.signUpButton1.click();
    }

    @Then("User logs in to Aimped")
    public void userLogsInToAimped() {
        registerPage.inputUsername.sendKeys(username);
        registerPage.inputPassword.sendKeys(password);
        registerPage.signInButton.click();
        waitFor(3);
    }

    @Then("User verifies registration")
    public void userVerifiesRegistration() {
        String usernameStr = registerPage.usernameText.getText();
        System.out.println("username = " + username);
        Assert.assertEquals(usernameStr, username);

        // Excel
        File excelFile = new File("/Users/ibrahimvesek/Desktop/username.xlsx");
        Workbook workbook;
        Sheet sheet;

        try {
            if (excelFile.exists()) {
                FileInputStream fileInputStream = new FileInputStream(excelFile);
                workbook = new XSSFWorkbook(fileInputStream);
                sheet = workbook.getSheetAt(0);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("username");
            }

            // Boş olan ilk hücreyi bulma
            int rowNum = 0;
            while (sheet.getRow(rowNum) != null && sheet.getRow(rowNum).getCell(0) != null && !sheet.getRow(rowNum).getCell(0).getStringCellValue().isEmpty()) {
                rowNum++;
            }

            // Boş olan ilk hücreye veri yazma
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.createCell(0);
            cell.setCellValue(usernameStr);

            // Excel dosyasını kaydetme
            try (FileOutputStream fileOut = new FileOutputStream("/Users/ibrahimvesek/Desktop/username.xlsx")) {
                workbook.write(fileOut);
            }

            // Kaynakları kapatma
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Then("The user enters an e-mail address and verifies error message")
    public void theUserEntersAnEMailAddressAndVerifiesErrorMessage(DataTable table) {

        List<List<String>> data = table.asLists();
        for (List<String> eachRow : data) {
            String email = eachRow.get(0);
            String emailErrorMessage = eachRow.get(1);
            if (email == null) {
                registerPage.registerButton.click();
                Assert.assertEquals(registerPage.emailErrorText.getText(), emailErrorMessage);
            } else {
                registerPage.inputEmail.clear();
                registerPage.inputEmail.sendKeys(email);
                registerPage.registerButton.click();
                Assert.assertEquals(registerPage.emailErrorText.getText(), emailErrorMessage);
            }
        }
    }

    @Then("The user verifies that the link sent to the email box comes from {string} and clicks it.")
    public void theUserVerifiesThatTheLinkSentToTheEmailBoxComesFromAndClicksIt(String seviceName) {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        waitFor(3);
        driver.navigate().refresh();
        driver.switchTo().frame(registerPage.iframe);
        Assert.assertTrue(registerPage.serviceName.getText().contains(seviceName));
        registerPage.yopContinueRegistrationButton.click();
    }

    @Then("Creates the username")
    public void createsTheUsername(DataTable table) {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[2]);
        waitFor(1);

        List<List<String>> data = table.asLists();
        for (List<String> eachRow : data) {
            String username = eachRow.get(0);
            String usernameErrorMessage = eachRow.get(1);

            if (username == null) {
                registerPage.signUpButton1.click();
                Assert.assertEquals(registerPage.usernameErrorText.getText(), usernameErrorMessage);
            } else {
                registerPage.inputUsername.clear();
                registerPage.inputUsername.sendKeys(username);
                registerPage.inputPassword.clear();
                registerPage.inputPassword.sendKeys(password);
                registerPage.inputConfirmPassword.clear();
                registerPage.inputConfirmPassword.sendKeys(password);
                registerPage.signUpButton1.click();
                Assert.assertEquals(registerPage.usernameErrorText.getText(), usernameErrorMessage);
            }
        }
    }

    @Then("Creates the password")
    public void createsThePassword(DataTable table) {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[2]);
        waitFor(1);

        List<List<String>> data = table.asLists();
        for (List<String> eachRow : data) {
            String password = eachRow.get(0);
            String passwordErrorMessage = eachRow.get(1);
            if (password == null) {
                registerPage.signUpButton1.click();
                Assert.assertEquals(registerPage.passwordErrorText.getText(), passwordErrorMessage);
            } else {
                registerPage.inputUsername.clear();
                registerPage.inputUsername.sendKeys(username);
                registerPage.inputPassword.clear();
                registerPage.inputPassword.sendKeys(password);
                registerPage.inputConfirmPassword.clear();
                registerPage.inputConfirmPassword.sendKeys(password);
                registerPage.signUpButton1.click();
                Assert.assertEquals(registerPage.passwordErrorText.getText(), passwordErrorMessage);
            }
        }
    }

    @Then("Creates the confirm password")
    public void createsTheConfirmPassword(DataTable table) {

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[2]);
        waitFor(1);

        List<List<String>> data = table.asLists();
        for (List<String> eachRow : data) {
            String confirmPassword = eachRow.get(0);
            String confirmPasswordErrorMessage = eachRow.get(1);
            if (confirmPassword == null) {
                registerPage.signUpButton1.click();
                Assert.assertEquals(registerPage.confirmPasswordErrorText.getText(), confirmPasswordErrorMessage);
            } else {
                registerPage.inputUsername.clear();
                registerPage.inputUsername.sendKeys(username);
                registerPage.inputPassword.clear();
                registerPage.inputPassword.sendKeys(password);
                registerPage.inputConfirmPassword.clear();
                registerPage.inputConfirmPassword.sendKeys(confirmPassword);
                registerPage.signUpButton1.click();
                Assert.assertEquals(registerPage.confirmPasswordErrorText.getText(), confirmPasswordErrorMessage);
            }
        }
    }
}


