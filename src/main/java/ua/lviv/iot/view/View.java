package ua.lviv.iot.view;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.controller.implementation.*;


public class View {
    private final Map<String, AbstractController> controllers;
    private static final Scanner INPUT = new Scanner(System.in, "UTF-8");

    public View() {
        controllers = new LinkedHashMap<>();
        generateControllers();
    }


    public final void show() throws SQLException, ParseException {
        Menu showMenu = new Menu();
        String inputs;
        do {

            showMenu.displayMenu();
            do {
                System.out.println("Please enter 2 digits from 1 to 7:");
                inputs = INPUT.nextLine();
            } while (!inputs.matches("[1-7][1-6]"));

            if (!inputs.substring(1).equals("6")) {
                controllers.get(inputs.substring(0, 1)).methodsMenu(inputs.substring(1)).print();
            }
        }
        while (!inputs.substring(1).equals("6"));
    }

    private void generateControllers() {
        controllers.put("1", new ClientController());
        controllers.put("2", new GymController());
        controllers.put("3", new CardTypeController());
        controllers.put("4", new ClientCardController());
        controllers.put("5", new ServiceController());
        controllers.put("6", new OrderController());
        controllers.put("7", new CardServiceController());
    }
}

