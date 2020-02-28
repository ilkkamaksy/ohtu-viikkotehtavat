package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        // WebDriver driver = new HtmlUnitDriver();

        // Käyttäjän kirjautuminen
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        driver.get("http://localhost:4567");
        
        sleep(2);

        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vääräsalasana");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);

        // uuden käyttäjätunnuksen luominen
        driver.get("http://localhost:4567");
        
        sleep(2);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("jarppa"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("jarpansalaisuus1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("jarpansalaisuus1");
        element = driver.findElement(By.name("signup"));
        element.click();

        sleep(3);

        // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        driver.get("http://localhost:4567/ohtu");

        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(3);

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
