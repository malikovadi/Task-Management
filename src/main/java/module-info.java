module com.example.task_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.task_management to javafx.fxml;
    exports com.example.task_management;
}