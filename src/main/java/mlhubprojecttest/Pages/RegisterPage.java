package mlhubprojecttest.Pages;

import mlhubprojecttest.Utilities.Driver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
    public static String mail;
    public static String username;
    public static int usernameLength = 3; //username : yazılan sayının bir fazlası sayıda karakter içerir.
    public static String password = "Test.101";

    @FindBy(id = "login")
    public static WebElement yopInputMail;
    @FindBy(className = "bname")
    public static WebElement yopMail;
    @FindBy(xpath = "//*[@id='mail']/div/h3/a")
    public WebElement yopContinueRegistrationButton;
    @FindBy(xpath = "//*[text()='Sign Up']")
    public WebElement signUpButton;
    @FindBy(id = "email")
    public WebElement inputEmail;
    @FindBy(xpath = "//*[@class='btn btn-primary btn-block my-5']")
    public WebElement registerButton;
    @FindBy(xpath = "//*[@id='username']")
    public WebElement inputUsername;
    @FindBy(xpath = "//*[@id='password']")
    public WebElement inputPassword;
    @FindBy(xpath = "//*[@id='repeatPassword']")
    public WebElement inputConfirmPassword;
    @FindBy(xpath = "//*[@class='btn btn-primary btn-block mt-4']")
    public WebElement signUpButton1;
    @FindBy(name = "ifmail")
    public WebElement iframe;
    @FindBy(xpath = "//*[@class='ellipsis b']")
    public WebElement serviceName;
    @FindBy(xpath = "//*[@class='btn btn-primary btn-block mb-4']")
    public WebElement signInButton;
    @FindBy(xpath = "//*[@class='MuiBox-root css-0']/p")
    public WebElement usernameText;
    @FindBy(id = "email-error")
    public WebElement emailErrorText;
    @FindBy(id = "username-error")
    public WebElement usernameErrorText;
    @FindBy(id = "password-error")
    public WebElement passwordErrorText;
    @FindBy(id = "repeatPassword-error")
    public WebElement confirmPasswordErrorText;


    public static String fakeUsername() {
        return RandomStringUtils.randomAlphanumeric(usernameLength);
    }

    public static void getMailAddress() {
        Driver.driver.get("https://yopmail.com/");
        yopInputMail.sendKeys(fakeUsername() + Keys.ENTER);
        mail = yopMail.getText();
        username = "Test_" + mail.substring(0, usernameLength);
    }

}








