package ua.lviv.iot;

import ua.lviv.iot.view.View;

import java.sql.SQLException;
import java.text.ParseException;

public class Application {
    public static void main(final String[] args) throws SQLException, ParseException {
        new View().show();
    }
}
