package mlhubprojecttest.Pages;

import mlhubprojecttest.Utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Driver {

    public BasePage() {

        PageFactory.initElements(driver,this);
    }

}
