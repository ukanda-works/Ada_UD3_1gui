module es.severo.manuelamoros {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.google.gson;
    requires java.compiler;

    opens es.severo.manuelamoros to javafx.fxml;
    opens es.severo.manuelamoros.app.controllers;
    opens es.severo.manuelamoros.persistence.entity;

    exports es.severo.manuelamoros.app.controllers;
    exports es.severo.manuelamoros;
}

