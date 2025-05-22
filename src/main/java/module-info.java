module org.blackjack {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.blackjack to javafx.fxml;
    exports org.blackjack;
}
