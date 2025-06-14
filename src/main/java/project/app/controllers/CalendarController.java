package project.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import project.app.dao.OrderDAO;
import project.app.model.Order;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarController {

    private static final String[] MONTH_NAMES_PL = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};
    private static final String[] WEEK_DAYS_PL = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
    private final OrderDAO orderDAO = new OrderDAO();
    @FXML
    private Label monthLabel;
    @FXML
    private GridPane calendarGrid;
    private YearMonth currentYearMonth = YearMonth.now();
    private List<Order> allOrders;

    @FXML
    public void initialize() {
        loadOrders();
        drawCalendar(currentYearMonth);
    }

    @FXML
    private void handlePreviousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        loadOrders();
        drawCalendar(currentYearMonth);
    }

    @FXML
    private void handleNextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        loadOrders();
        drawCalendar(currentYearMonth);
    }

    private void loadOrders() {
        try {
            allOrders = orderDAO.getAllOrdersCalendar();
        } catch (Exception e) {
            allOrders = List.of();
        }
    }

    private void drawCalendar(YearMonth yearMonth) {
        calendarGrid.getChildren().clear();

        for (int i = 0; i < WEEK_DAYS_PL.length; i++) {
            Label dayLabel = new Label(WEEK_DAYS_PL[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-alignment: center;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(dayLabel, Priority.ALWAYS);
            calendarGrid.add(dayLabel, i, 0);
        }

        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int startDayOfWeek = (firstDayOfMonth.getDayOfWeek().getValue() + 6) % 7;
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate today = LocalDate.now();

        int dayNumber = 1;

        for (int row = 1; row <= 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (row == 1 && col < startDayOfWeek) continue;
                if (dayNumber > daysInMonth) break;

                LocalDate currentDate = yearMonth.atDay(dayNumber);

                VBox dayCell = new VBox(2);
                dayCell.setStyle("-fx-border-color: lightgray; -fx-padding: 5; -fx-cursor: hand;");

                Label dayNumberLabel = new Label(String.valueOf(dayNumber));
                dayNumberLabel.setStyle("-fx-font-size: 12px;");
                dayNumberLabel.setTextAlignment(TextAlignment.LEFT);

                List<Order> ordersForDay = allOrders.stream().filter(o -> currentDate.equals(o.getDeadline())).filter(o -> !"Zakończone".equalsIgnoreCase(o.getStatus()))  // <-- tutaj filtr na status
                        .collect(Collectors.toList());


                if (!ordersForDay.isEmpty()) {
                    dayCell.setStyle("-fx-border-color: lightgray; -fx-padding: 5; -fx-background-color: #ffcccc; -fx-cursor: hand;");
                } else if (currentDate.equals(today)) {
                    dayCell.setStyle("-fx-border-color: lightgray; -fx-padding: 5; -fx-background-color: lightblue; -fx-cursor: hand;");
                    dayNumberLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
                }

                dayCell.getChildren().add(dayNumberLabel);

                int maxVisible = 1;
                int count = 0;
                for (Order order : ordersForDay) {
                    if (count >= maxVisible) break;
                    Label orderLabel = new Label("- " + order.getDescription());
                    orderLabel.setStyle("-fx-font-size: 9px; -fx-text-fill: #333;");
                    dayCell.getChildren().add(orderLabel);
                    count++;
                }

                if (ordersForDay.size() > maxVisible) {
                    int more = ordersForDay.size() - maxVisible;
                    Label moreLabel = new Label("+" + more + " więcej");
                    moreLabel.setStyle("-fx-font-size: 9px; -fx-text-fill: #666; -fx-font-style: italic;");
                    dayCell.getChildren().add(moreLabel);
                }

                dayCell.setOnMouseClicked(_ -> showOrdersForDay(currentDate, ordersForDay));

                calendarGrid.add(dayCell, col, row);
                dayNumber++;
            }
        }

        monthLabel.setText(MONTH_NAMES_PL[yearMonth.getMonthValue() - 1] + " " + yearMonth.getYear());
    }

    private void showOrdersForDay(LocalDate date, List<Order> orders) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zlecenia na dzień");
        alert.setHeaderText("Zlecenia na " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        StringBuilder content = new StringBuilder();

        if (orders.isEmpty()) {
            content.append("Brak zleceń na ten dzień.");
        } else {
            content.append("Liczba zleceń: ").append(orders.size()).append("\n\n");

            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                content.append((i + 1)).append(". ").append("ID: ").append(order.getOrderId()).append("\n").append("   Opis: ").append(order.getDescription()).append("\n");

                if (i < orders.size() - 1) {
                    content.append("\n");
                }
            }
        }

        alert.setContentText(content.toString());

        alert.getDialogPane().setPrefSize(400, 300);
        alert.setResizable(true);

        alert.showAndWait();
    }
}