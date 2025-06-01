package project.app.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import project.app.interfaces.UserDataReceiver;
import project.app.utils.ConfirmationHandler;
import project.app.utils.Constants;
import project.app.utils.PageManagerUtils;

public class ManagerPageController implements UserDataReceiver {

    @FXML
    private StackPane mainPane;

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label positionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    public void initialize() {
        dateLabel.setText("Dziś: " + Constants.getCurrentDate());

        FadeTransition ft = new FadeTransition(Duration.seconds(2), mainPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    private void handleCarsButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/carsPage.fxml", contentPane);
    }

    @FXML
    private void handleCustomersButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/customersPage.fxml", contentPane);
    }

    @FXML
    private void handleOrdersButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/ordersPage.fxml", contentPane);
    }

    @FXML
    private void handleCarPartsButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/carPartsPage.fxml", contentPane);
    }

    @FXML
    private void handleCalendarButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/calendarPage.fxml", contentPane);
    }

    @FXML
    private void handleEmployeesButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/employeesPage.fxml", contentPane);
    }

    @FXML
    private void handleInvoicesButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/invoicesPage.fxml", contentPane);
    }

    @FXML
    private void handleAboutButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/aboutPage.fxml", contentPane);
    }

    @FXML
    private void handleServicesButton() {
        PageManagerUtils.loadPageIntoAnchorPane("/project/app/servicesPage.fxml", contentPane);
    }

    @FXML
    private void handleLogout() {
        ConfirmationHandler.show("Potwierdzenie", "Czy na pewno chcesz się wylogować?",
                () -> PageManagerUtils.showPageInSameWindow("/project/app/loginPage.fxml", logoutButton));
    }

    @Override
    public void initUserData(String name, String position) {
        nameLabel.setText("Zalogowany jako: " + name);
        positionLabel.setText("Stanowisko: " + position);
    }
}
