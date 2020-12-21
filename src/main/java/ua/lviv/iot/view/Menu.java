package ua.lviv.iot.view;

public final class Menu {

    public void displayMenu() {

        System.out.println(" _______________________________________________________________________");
        System.out.println("|                                                                       |");
        System.out.println("|       Enter any combination of existing entity and action             |");
        System.out.println("|_______________________________________________________________________|");
        System.out.println("|                                  |                                    |");
        System.out.println("|        entity number:            |           Action number:           |");
        System.out.println("|__________________________________|____________________________________|");
        System.out.println("|   |                              |   |                                |");
        System.out.println("| 1 | client                       | 1 | GET ALL                        |");
        System.out.println("| 2 | gym                          | 2 | GET ONE                        |");
        System.out.println("| 3 | card_type                    | 3 | CREATE                         |");
        System.out.println("| 4 | client_card                  | 4 | UPDATE                         |");
        System.out.println("| 5 | service                      | 5 | DELETE                         |");
        System.out.println("| 6 | order                        | 6 | QUIT                           |");
        System.out.println("| 7 | card_service                 |   |                                |");
        System.out.println("|___|______________________________|___|________________________________|");
    }
}
