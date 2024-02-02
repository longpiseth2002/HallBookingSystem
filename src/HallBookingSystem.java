import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HallBookingSystem {

    static final String HORIZONTAL_BORDER = "─";

    // Color
    String reset = "\u001B[0m";
    String green = "\u001B[32m";
    String blue = "\u001B[34m";
    String cyan = "\u001B[36m";
    String black = "\u001B[30m";
    String blueBackGround = "\u001B[44m";
    String[][] morning;
    String[][] afternoon;
    String[][] evening;
    static String[] history = null;
    static int n = 0;
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formatDateTime = now.format(format);

    public void interfaceHallBooking() {

        System.out.println(blueBackGround + black + "\n\n" + " ".repeat(30)
                + " ██████╗███████╗████████╗ █████╗ ██████╗     ██╗  ██╗ █████╗ ██╗     ██╗    \n" + " ".repeat(30) +
                "██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗    ██║  ██║██╔══██╗██║     ██║    \n" + " ".repeat(30) +
                "██║     ███████╗   ██║   ███████║██║  ██║    ███████║███████║██║     ██║    \n" + " ".repeat(30) +
                "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██╔══██║██╔══██║██║     ██║    \n" + " ".repeat(30) +
                "╚██████╗███████║   ██║   ██║  ██║██████╔╝    ██║  ██║██║  ██║███████╗███████╗\n" + " ".repeat(30) +
                "╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝");
        System.out.println(" ".repeat(10) +
                "██████╗  ██████╗  ██████╗ ██╗  ██╗██╗███╗   ██╗ ██████╗     ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗\n" + " ".repeat(10) +
                "██╔══██╗██╔═══██╗██╔═══██╗██║ ██╔╝██║████╗  ██║██╔════╝     ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║\n" + " ".repeat(10) +
                "██████╔╝██║   ██║██║   ██║█████╔╝ ██║██╔██╗ ██║██║  ███╗    ███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║\n" + " ".repeat(10) +
                "██╔══██╗██║   ██║██║   ██║██╔═██╗ ██║██║╚██╗██║██║   ██║    ╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║\n" + " ".repeat(10) +
                "██████╔╝╚██████╔╝╚██████╔╝██║  ██╗██║██║ ╚████║╚██████╔╝    ███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║\n" + " ".repeat(10) +
                "╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝     ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝\n" + " ".repeat(10));

        System.out.println(reset);
    }


    private void setHallBooking(String message, String m, Scanner scanner, String regex) {

        while (true) {
            System.out.print(message);
            String userInput = scanner.nextLine();


            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(userInput).matches()) {
                System.out.print(m);
                String use = scanner.nextLine();
                if (pattern.matcher(use).matches()) {
                    int n = Integer.parseInt(userInput);
                    int e = Integer.parseInt(use);
                    morning = new String[n][e];
                    afternoon = new String[n][e];
                    evening = new String[n][e];
                    history = new String[n * e];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < e; j++) {
                            char l = (char) ('A' + i);
                            morning[i][j] = "|" + l + "-" + (j + 1) + "::AV|";
                            afternoon[i][j] = "|" + l + "-" + (j + 1) + "::AV|";
                            evening[i][j] = "|" + l + "-" + (j + 1) + "::AV|";
                        }
                    }

                    break;
                } else {
                    System.out.println("  >> ERROR !! INVALID FORMAT !\n");
                }
            } else {
                System.out.println("  >> ERROR !! INVALID FORMAT !\n");
            }
        }

    }

    public void bookSeat(String hallTitle, String[][] hall, String hallName, Scanner scanner) {
        String dF = "";
        System.out.println(green + hallTitle);
        displayHallA();
        System.out.println(HORIZONTAL_BORDER.repeat(160) + blue + "# INSTRUCTION \n# SINGLE : C-1 \n# MULTIPLE ( SEPARATE BY COMMA ) : C-1 , C-2");
        System.out.print(">> PLEASE SELECT AVAILABLE SEAT : ");
        String row = scanner.nextLine().toUpperCase().replaceAll("\\s", "");
        String[] booking = row.split(",");
        System.out.print(">> PLEASE ENTER ID : ");
        String id = scanner.nextLine();
        Pattern p = Pattern.compile("[0-9]+");

        if (p.matcher(id).matches()) {
            int f = 1;
            System.out.print(">> ARE YOU SURE TO BOOKING ? (Y/N) : " + reset);
            String makeSure = scanner.nextLine().toLowerCase();
            Pattern pattern1 = Pattern.compile("[A-Za-z]+");

            if (pattern1.matcher(makeSure).matches()) {
                for (String eachBooking : booking) {
                    Pattern pattern = Pattern.compile("^[A-Za-z]-(?!0)\\d+$");

                    if (pattern.matcher(eachBooking).matches()) {
                        String[] parts = eachBooking.split("-");
                        if (parts.length == 2) {
                            int r = parts[0].charAt(0) - 'A';
                            int c = Integer.parseInt(parts[1]) - 1;

                            // Check if the seat is already booked
                            if (!hall[r][c].equals("|" + parts[0] + "-" + parts[1] + "::BO|")) {
//                                String his = hallName + "_" + id + "_" + formatDateTime;
                                String dataFormat = """
                                %s                 %s               %s
                                """;
                                dF += String.format(dataFormat, hallName, id, formatDateTime);

                                hall[r][c] = "|" + parts[0] + "-" + parts[1] + "::BO|";
                                System.out.println(blue + " SUCCESSFULLY !!!");
                                System.out.println(HORIZONTAL_BORDER.repeat(160) + reset);
                            } else {
                                System.out.println("  >> ERROR !! Seat already booked.\n");
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println("  >> ERROR !! INVALID FORMAT !\n");
                    }
                }
                String format = """
                    # SEATS : [ %s ]
                    # HALL                # ID                 # CREATED AT
                    """;
                String getFormat = String.format(format, row);
                String combinedString =getFormat+ dF;
                history[n++] = combinedString;
            } else {
                System.out.println("  >> ERROR !! INVALID FORMAT !\n");
            }
        } else {
            System.out.println("  >> ERROR !! INVALID FORMAT !\n");
        }

    }


    public void bookingA(Scanner scanner) {
        bookSeat("# HALL A ", morning, "HALL A", scanner);
    }

    public void bookingB(Scanner scanner) {
        bookSeat("# HALL B ", afternoon, "HALL B", scanner);
    }

    public void bookingC(Scanner scanner) {
        bookSeat("# HALL C ", evening, "HALL C", scanner);
    }


    public void booking(Scanner scanner) {
        System.out.println("\n\n" + blue + HORIZONTAL_BORDER.repeat(160));
        System.out.println("# START BOOKING PROCESS ");
        System.out.println(HORIZONTAL_BORDER.repeat(160));
        System.out.println("# SHOWTIME INFORMATION ");
        showTime();
        System.out.println(HORIZONTAL_BORDER.repeat(160));
        String op;
        boolean check = false;
        do {
            System.out.print(">> PLEASE SELECT SHOWTIME ( A | B | C) : " + reset);
            op = scanner.nextLine().toLowerCase();
            System.out.println(green + "\n\n" + HORIZONTAL_BORDER.repeat(160));
            switch (op) {
                case "a" -> {
                    bookingA(scanner);
                    check = true;
                }
                case "b", "c" -> {
                    check = true;
                    if (op.equals("b")) {
                        bookingB(scanner);
                    } else {
                        bookingC(scanner);
                    }
                }
            }
        } while (!check);


    }

    public void displayHallA() {
        for (String[] strings : morning) {
            for (int j = 0; j < morning[0].length; j++) {
                System.out.print(strings[j] + "\t");
            }
            System.out.println();
        }
    }

    public void displayHallB() {
        for (String[] strings : afternoon) {
            for (int j = 0; j < afternoon[0].length; j++) {
                System.out.print(strings[j] + "\t");
            }
            System.out.println();
        }
    }

    public void displayHallC() {
        for (String[] strings : evening) {
            for (int j = 0; j < evening[0].length; j++) {
                System.out.print(strings[j] + "\t");
            }
            System.out.println();
        }
    }

    public void hallBooking() {
        System.out.println(blue + "\n" + HORIZONTAL_BORDER.repeat(200));
        System.out.println("# HALL INFORMATION ");
        System.out.println(HORIZONTAL_BORDER.repeat(200));


        System.out.println(green + "# HALL - MORNING ");
        displayHallA();

        System.out.println(HORIZONTAL_BORDER.repeat(160));
        System.out.println(cyan + "\n# HALL - AFTERNOON ");
        displayHallB();


        System.out.println(HORIZONTAL_BORDER.repeat(160));
        System.out.println(green + "\n# HALL - NIGHT ");
        displayHallC();
        System.out.println(HORIZONTAL_BORDER.repeat(160) + reset);
    }

    public void showTime() {
        System.out.println(blue + HORIZONTAL_BORDER.repeat(160));
        System.out.print("""
                # A) MORNING | 10:00AM - 12:30PM |
                # B) AFTERNOON | 03:00PM - 05:30PM |
                # C) NIGHT | 07:00PM - 09:30PM |
                """);
        System.out.println(HORIZONTAL_BORDER.repeat(160));
    }

    public void rebootShowtime() {

        for (int i = 0; i < morning.length; i++) {
            for (int j = 0; j < morning[0].length; j++) {
                char l = (char) ('A' + i);
                morning[i][j] = "|" + l + "-" + (j + 1) + "::AV|";
                afternoon[i][j] = "|" + l + "-" + (j + 1) + "::AV|";
                evening[i][j] = "|" + l + "-" + (j + 1) + "::AV|";
            }
        }
        for (int i = 0; i < history.length; i++) {
            history[i] = null;
        }
        System.out.println(blue + "\n\n" + HORIZONTAL_BORDER.repeat(160));
        System.out.println(green + "# HALL A ");
        displayHallA();
        System.out.println(cyan + "\n" + HORIZONTAL_BORDER.repeat(160));
        System.out.println("# HALL B ");
        displayHallB();
        System.out.println(blue + "\n\n" + HORIZONTAL_BORDER.repeat(160));
        System.out.println(green + "# HALL C ");
        displayHallC();
        System.out.println(HORIZONTAL_BORDER.repeat(160) + reset);

    }


    public void history() {
        System.out.println(blue + "\n".repeat(2) + HORIZONTAL_BORDER.repeat(160));
        System.out.println("# BOOKING HISTORY ");
        System.out.println(HORIZONTAL_BORDER.repeat(160));
        boolean hasHistory = false;

        int i = 1;

        for (String entry : history) {
            if (entry == null) {
                continue;
            }
            System.out.println("# NO : " + i++);
            System.out.println(entry);
            hasHistory = true;
        }

        if (!hasHistory) {
            System.out.println("THERE IS NOT HISTORY ... ");
        }

        System.out.println(HORIZONTAL_BORDER.repeat(160) + reset);
    }



    public static void main(String[] args) {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String magenta = "\u001B[35m";
        String cyan = "\u001B[36m";
        String white = "\u001B[37m";
        String black = "\u001B[30m";


        Scanner scanner = new Scanner(System.in);
        HallBookingSystem hallBookingSystem = new HallBookingSystem();
        // HallBooking System
        hallBookingSystem.interfaceHallBooking();


        String option;
        System.out.println(cyan + HORIZONTAL_BORDER.repeat(160));
        hallBookingSystem.setHallBooking(" >>>> CONFIG TOTAL ROW IN HALL : ", " >>>>  CONFIG TOTAL SEATS PER ROW IN HALL : ", scanner, "[0-9]+");
        System.out.println(HORIZONTAL_BORDER.repeat(160) + reset);
        do {

            System.out.print(magenta + "\n" + """
                    [ [    APPLICATION MENU     ] ]
                       < A >  BOOKING
                       < B >  HALL
                       < C >  SHOWTIME
                       < D >  REBOOT SHOWTIME
                       < E >  HISTORY
                       < F >  EXIT
                    """);
            System.out.print(" >> PLEASE SELECT MENU : ");
            option = scanner.nextLine().toLowerCase();
            switch (option) {
                case "a" -> {
                    hallBookingSystem.booking(scanner);

                }
                case "b" -> {
                    hallBookingSystem.hallBooking();
                }
                case "c" -> {
                    System.out.println("\n\n" + blue + HORIZONTAL_BORDER.repeat(160));
                    System.out.println("# DAILY SHOWTIME OF CSTAD HALL");
                    hallBookingSystem.showTime();

                }
                case "d" -> hallBookingSystem.rebootShowtime();
                case "e" -> hallBookingSystem.history();
                case "f" -> {
                    System.exit(0);
                }
                default -> {

                }
            }

        } while (option != "f");
    }
}
