package org.example.ecobike;

import java.util.Scanner;
import org.example.ecobike.util.BikeFactory;
import org.example.ecobike.util.BikeFileWriter;
import org.example.ecobike.util.impl.BikeFactoryImpl;
import org.example.ecobike.util.BikeFileParser;
import org.example.ecobike.util.impl.BikeFileParserImpl;
import org.example.ecobike.service.BikeManageServiceImpl;
import org.example.ecobike.service.BikeManageService;
import org.example.ecobike.util.impl.BikeFileWriterImpl;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //String filePath = getFilePathFromConsole(in);
        String filePath = "src/main/resources/ecobike.txt";
        BikeFactory bikeFactory =  new BikeFactoryImpl();
        BikeFileParser bikeFileParser = new BikeFileParserImpl(bikeFactory);
        BikeFileWriter bikeFileWriter = new BikeFileWriterImpl();
        BikeManageService bikeManageService = new BikeManageServiceImpl(bikeFileParser, filePath, bikeFactory, bikeFileWriter);

        showMenu();
        int choice = getChoiceFromConsole(in);
        while (choice != 7) {
            switch (choice) {
                case 1:
                    bikeManageService.showBikeCatalog();
                    break;
                case 2:
                    System.out.println("Input data for creation new folding bike.\n"
                        + "Use following format:\n"
                            + "\tBrand;\n"
                            + "\tThe size of the wheels (in inch);\n"
                            + "\tThe number of gears;\n"
                            + "\tThe weight of the bike (in grams);\n"
                            + "\tThe availability of lights at front and back (TRUE/FALSE);\n"
                            + "\tA color;\n"
                            + "\tThe price;\n"
                        + "By separator use \";\".\n"
                        + "\tFor Example: Brompton; 20; 6; 9283; true; black; 1199");
                    bikeManageService.addFoldingBike(getParamsFromConsole(in));
                    break;
                case 3:
                    System.out.println("Input data for creation new SpedElec bike.\n"
                        + "Use following format:\n"
                            + "\tBrand;\n"
                            + "\tTThe maximum speed (in km/h);\n"
                            + "\tThe weight of the bike (in grams);\n"
                            + "\tThe availability of lights at front and back (TRUE/FALSE);\n"
                            + "\tThe battery capacity (in mAh);\n"
                            + "\tA color;\n"
                            + "\tThe price;\n"
                        + "By separator use \";\".\n"
                        + "\tFor Example: Peugeot; 45; 5426; true; 8000; blue; 875");
                    bikeManageService.addSpeedElecBike(getParamsFromConsole(in));
                    break;
                case 4:
                    System.out.println("Input data for creation new E-Bike.\n"
                        + "Use following format:\n"
                            + "\tBrand;\n"
                            + "\tThe maximum speed (in km/h);\n"
                            + "\tThe weight of the bike (in grams);\n"
                            + "\tThe availability of lights at front and back (TRUE/FALSE);\n"
                            + "\tThe battery capacity (in mAh);\n"
                            + "\tA color;\n"
                            + "\tThe price;\n"
                        + "By separator use \";\".\n"
                        + "\tFor Example: Gazelle; 49; 16455; true; 16000; red; 1499");
                    bikeManageService.addEBike(getParamsFromConsole(in));
                    break;
                case 5:
                    System.out.println("You can use following search keys(all are optional):\n"
                        + "\tFor all:\n"
                        + "\ttype(e-bike, folding bike and speedelec)\n"
                        + "\tbrand\n"
                        + "\tavailability lights\n"
                        + "\tprice\n"
                        + "\tcolor\n"
                        + "\tweight in grams\n"
                        + "\n\tFor e-bike and speedelec:\n"
                        + "\tmax speed\n"
                        + "\tbattery capacity\n"
                        + "\n\tFor folding bike:\n"
                        + "\tsize of wheels\n"
                        + "\tnumbers of gears\n"
                        + "\nBy separator use \";\".\n"
                        + "\tFor Example: type = e-bike; brand = Dualtron");
                    String params = getParamsFromConsole(in);
                    System.out.println(bikeManageService.findBike(params));
                    break;
                case 6:
                    bikeManageService.writeBikesToFile(filePath);
                    break;
                default:
                    break;
            }
            System.out.println();
            showMenu();
            choice = getChoiceFromConsole(in);
        }
        in.close();
    }

    public static String getFilePathFromConsole(Scanner in) {
        System.out.println("Input path to file:");
        return in.nextLine();
    }

    public static int getChoiceFromConsole(Scanner in) {
        int choice =  in.nextInt();
        in.nextLine();
        return choice;
    }

    public static String getParamsFromConsole(Scanner in) {
        return in.nextLine();
    }

    public static void showMenu() {
        String menu = "Please make your choice:\n"
            + "1 - Show the entire EcoBike catalog\n"
            + "2 – Add a new folding bike\n"
            + "3 – Add a new speedelec\n"
            + "4 – Add a new e-bike\n"
            + "5 – Find the first item of a particular brand\n"
            + "6 – Write to file\n"
            + "7 – Stop the program";
        System.out.println(menu);
    }
}
