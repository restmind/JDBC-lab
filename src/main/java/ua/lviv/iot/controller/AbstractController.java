package ua.lviv.iot.controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.service.Service;
import ua.lviv.iot.view.Printable;

import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class AbstractController<T> implements Controller<T> {

    private static final String ERROR_MESSAGE = "Oops...something went wrong\n";
    protected static final Scanner INPUT = new Scanner(System.in, "UTF-8");
    private static final Map<String, Printable> METHODS_MENU = new LinkedHashMap<>();

    public abstract Service<T> getService();

    @Override
    public void findAll() {
        List<T> list;

        try {
            list = getService().findAll();
            formatTable(list);
        } catch (SQLException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
    }

    @Override
    public void findById() {
        T foundedEntity;
        System.out.println("Enter id: ");
        Integer id = Integer.parseInt(INPUT.next());
        INPUT.nextLine();
        try {
            foundedEntity = getService().findById(id);
            if (foundedEntity != null) {
                System.out.println("Your search result is:\n");
                formatTable(foundedEntity);
            } else {
                System.out.println("Oops...it couldn't be found!\n");
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
    }

    @Override
    public void create(final T entity) {
        try {
            getService().create(entity);
            System.out.println("Your have just created new item\n");
        } catch (MysqlDataTruncation e) {
            System.out.println(e.getMessage() + "!Please, follow constraints\n");
        } catch (Exception e1) {
            System.out.println(ERROR_MESSAGE + e1.getMessage());
        }
    }

    public abstract void create();

    public abstract void update();

    @Override
    public void update(final Integer id, final T entity) {
        try {
            getService().update(id, entity);
            System.out.println("Your have just created new item\n");
        } catch (MysqlDataTruncation e) {
            System.out.println(e.getMessage() + "!Please, follow constraints\n");
        } catch (Exception e1) {
            System.out.println(ERROR_MESSAGE + e1.getMessage());
        }
    }

    @Override
    public void delete() {
        System.out.println("Enter id: ");
        Integer id = Integer.parseInt(INPUT.next());
        INPUT.nextLine();
        try {
            getService().delete(id);

        } catch (SQLException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
        System.out.println("Deleted successfully!\n");
    }

    public void exit() {
        INPUT.close();
        ConnectionManager.closeConnection();
    }

    public void createMethodsMenu() {
        METHODS_MENU.put("1", this::findAll);
        METHODS_MENU.put("2", this::findById);
        METHODS_MENU.put("3", this::create);
        METHODS_MENU.put("4", this::update);
        METHODS_MENU.put("5", this::delete);
        METHODS_MENU.put("6", this::exit);
    }

    public Printable methodsMenu(final String key) {
        createMethodsMenu();
        return METHODS_MENU.get(key);
    }

    public void formatTable(final List<T> rows) {
        System.out.println(" \n__________________________________________________________________");
        for (T row : rows) {
            String[] splited = row.toString().split(" ");
            for (String substring : splited) {
                System.out.printf("%15s", substring);
            }
            System.out.println();
        }
        System.out.println(" _________________________________________________________________\n");
    }

    public void formatTable(final T row) {
        System.out.println(" __________________________________________________________________");
        String[] splited = row.toString().split(" ");
        for (String substring : splited) {
            System.out.printf("%15s", substring);
        }
        System.out.println();
        System.out.println(" __________________________________________________________________\n");
    }
}

