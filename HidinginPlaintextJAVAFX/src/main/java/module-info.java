module com.example.hidinginplaintextjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.hidinginplaintextjavafx to javafx.fxml;
    exports com.example.hidinginplaintextjavafx;
}