package dnevnikRu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class dnevnikTest implements ActionListener, FocusListener {
    private static final DateFormat dateFormat = new SimpleDateFormat("d MMMM EEEE yyyy");
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final JTextField passwordText = new JTextField("Введите пароль");
    private static final JTextField loginText = new JTextField("Введите логин");
    private static final JTextArea raspisanie=new JTextArea();
    private static final JTextField textAboutSchoodule=new JTextField();
    private boolean isDataEntered = false;
    @Test
    public void dnevnikTest(){
        passwordText.setEditable(true);
        loginText.setEditable(true);
        JFrame interfaceGUI = new JFrame();
        interfaceGUI.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JLabel loginLabel = new JLabel("Введите логин");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(loginLabel);
        topPanel.add(loginText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JLabel passwordLabel = new JLabel("Введите пароль");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(passwordLabel);
        bottomPanel.add(passwordText);

        JButton loginButton = new JButton("Войти");
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.WHITE);
        loginButton.setFocusPainted(false);

        interfaceGUI.add(topPanel, BorderLayout.NORTH);
        interfaceGUI.add(bottomPanel, BorderLayout.SOUTH);
        interfaceGUI.add(loginButton, BorderLayout.CENTER);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        loginText.setBorder(border);
        passwordText.setBorder(border);
        loginButton.setBorder(border);

        textAboutSchoodule.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        textAboutSchoodule.setBorder(border);
        textAboutSchoodule.setEditable(false);
        textAboutSchoodule.setBackground(Color.white);

        raspisanie.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        raspisanie.setBorder(border);
        raspisanie.setEditable(false);
        raspisanie.setBackground(Color.white);

        passwordText.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        loginText.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        loginButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));

        loginText.addFocusListener(this);
        passwordText.addFocusListener(this);

        interfaceGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        interfaceGUI.setSize(new Dimension(WIDTH, HEIGHT));
        interfaceGUI.setLocationRelativeTo(null);
        interfaceGUI.setVisible(true);

        while (!isDataEntered) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        loginButton.setEnabled(false);
        loginButton.setText("Загрузка...");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://dnevnik-kchr.ru/");
        boolean checker = driver.findElement(By.id("schools")).isDisplayed();
        if (checker){
            interfaceGUI.dispose();
        }
        WebElement elem = driver.findElement(By.id("schools"));
        new Actions(driver).click(elem).perform();

        // Выбор школы "Гимназия 19" из списка
        driver.findElement(By.cssSelector("option[value='28']")).click();

        // Заполнение полей с именами UN и PW
        driver.findElement(By.name("UN")).sendKeys(textFromL);
        driver.findElement(By.name("PW")).sendKeys(textFromP);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Нажатие на кнопку "Войти"
            WebElement loginButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("button-login-title")));
            loginButtonElement.click();
        } catch (Exception e) {
            throw new RuntimeException("У вас тут ошибочка небольшая всплыла-" + e);
        }
        // Ожидание появления элемента с титлом "Продолжить"
        List<List<WebElement>> daysOfWeek = new ArrayList<>();
        boolean z = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Продолжить']"))).isDisplayed();
        if (z) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Продолжить']")));
            element.click();
            WebElement schedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Открыть расписание ')]")));
            schedule.click();
            List<WebElement> monday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(4)")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(4) > div")));
            List<WebElement> tuesday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(5)")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(5) > div")));
            List<WebElement> Wednesday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(6)")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(6) > div")));
            List<WebElement> Thursday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(7)")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(7) > div")));
            List<WebElement> Friday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(8)")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(8) > div")));
            daysOfWeek.add(monday);
            daysOfWeek.add(tuesday);
            daysOfWeek.add(Wednesday);
            daysOfWeek.add(Thursday);
            daysOfWeek.add(Friday);
            for (List<WebElement> day : daysOfWeek) {
                for (WebElement webElement : day) {
                    String elementText = webElement.getText();
                    raspisanie.append(elementText + "\t");
                }
                raspisanie.append("\n");
            }
        } else {
            WebElement schedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Открыть расписание ')]")));
            schedule.click();
            List<WebElement> monday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(4)")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(4) > div")));
            monday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(4) > div")));
            List<WebElement> tuesday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(5)")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(5) > div")));
            tuesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(5) > div")));
            List<WebElement> Wednesday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(6)")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(6) > div")));
            Wednesday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(6) > div")));
            List<WebElement> Thursday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(7)")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(7) > div")));
            Thursday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(7) > div")));
            List<WebElement> Friday = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(1) > th:nth-child(8)")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(2) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(3) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(4) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(5) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(6) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(7) > td:nth-child(8) > div")));
            Friday.add(driver.findElement(By.cssSelector("#mCSB_1_container > table > tbody > tr:nth-child(8) > td:nth-child(8) > div")));
            daysOfWeek.add(monday);
            daysOfWeek.add(tuesday);
            daysOfWeek.add(Wednesday);
            daysOfWeek.add(Thursday);
            daysOfWeek.add(Friday);
            for (List<WebElement> day : daysOfWeek) {
                for (WebElement webElement : day) {
                    String elementText = webElement.getText();
                    raspisanie.append(elementText + "\t");
                }
                raspisanie.append("\n");
            }

        }
        JFrame second = new JFrame();
        second.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        textAboutSchoodule.setText("РАСПИСАНИЕ НА НЕДЕЛЮ-" + dateFormat.format(new Date()));
        textAboutSchoodule.setPreferredSize(new Dimension(WIDTH, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        second.add(textAboutSchoodule, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        second.add(raspisanie, gbc);

        second.pack();
        second.setLocationRelativeTo(null);
        second.setVisible(true);
        try {
            TimeUnit.HOURS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String textFromL;
    public static String textFromP;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (loginText.getText().equals("Введите логин") || passwordText.getText().equals("Введите пароль") ||
                passwordText.getText().isEmpty() || loginText.getText().isEmpty()) {
            return;
        }
        isDataEntered = true;
        textFromL = loginText.getText();
        textFromP = passwordText.getText();
        loginText.setText("");
        passwordText.setText("");
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == loginText) {
            if (loginText.getText().equals("Введите логин")) {
                loginText.setText("");
                loginText.setForeground(Color.BLACK);
            }
        } else if (e.getSource() == passwordText) {
            if (passwordText.getText().equals("Введите пароль")) {
                passwordText.setText("");
                passwordText.setForeground(Color.BLACK);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == loginText) {
            if (loginText.getText().isEmpty()) {
                loginText.setText("Введите логин");
                loginText.setForeground(new Color(100, 100, 100));
            }
        } else if (e.getSource() == passwordText) {
            if (passwordText.getText().isEmpty()) {
                passwordText.setText("Введите пароль");
                passwordText.setForeground(new Color(100, 100, 100));
            }
        }
    }
}