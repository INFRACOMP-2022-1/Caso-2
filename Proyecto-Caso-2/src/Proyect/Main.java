package Proyect;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
    Contains the loaded configurations
     */
    public static ArrayList<Configuration> loadedConfigurations = new ArrayList<>();

    //--------------------------------------------------------------------------
    // Main
    //--------------------------------------------------------------------------

    public static void main(String[] args){
        System.out.println("Welcome to caso 2");

        //Gets user option selection (1 or 2). This also checks if there is a file has been loaded in order to be able to chose option 2.
        int selectedOption = selectOptionPrompt();

        if(selectedOption == 1){
            option1();
        }
        else{
            option2();
        }

    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    /**
     * Option 1
     */
    public static void option1(){
        //Ask for parameters
        //parameter order: page size, int size, rows, columns, run type
        ArrayList<Integer> parameters = selectParameters();

        /*
         * Get name of the configuration
         */
        System.out.println("Enter the name you want for your configuration file: ");
        Scanner scanner = new Scanner(System.in);
        String configurationName = scanner.nextLine().replaceAll("( )+", "").trim();

        //This does all option 1 work
        Configuration newConfig = new Configuration(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3),parameters.get(4),configurationName);

        //Adds configutation to the list of available configurations
        loadedConfigurations.add(newConfig);
    }

    /**
     * Option 2
     */
    public static void option2(){
        Configuration configuration = selectConfiguration();

        System.out.println("");

        int pageFrameSize = selectPageFrameSize();

        Option2 option2 = new Option2(configuration,pageFrameSize);
    }

    /**
     * Lets the user select the desired page frame size (marco de pagina) to run option 2 with.
     * @return integer of the page frame size
     */
    public static int selectPageFrameSize(){
        int pageFrameSize;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the page frame size (marco de paginas): ");
        String pageFrameSizeStr = scanner.nextLine().trim();
        while(!isInteger(pageFrameSizeStr)){
            System.out.println("Option needs to be an integer");
            pageFrameSizeStr = scanner.nextLine().trim();
        }
        pageFrameSize = Integer.parseInt(pageFrameSizeStr);
        return pageFrameSize;
    }

    /**
     * Lets user select a configuration from the list of loaded configurations.
     * @return Configuration the selected configuration file
     */
    public static Configuration selectConfiguration(){
        int option = 1;
        System.out.println("Available configurations: ");
        for(int i = 0 ; i < loadedConfigurations.size();i++){
            Configuration config = loadedConfigurations.get(i);
            System.out.println( String.format("%d. %s", option, config.getConfigurationName()));
            option += 1;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a configuration: ");
        String selectedOptionStr = scanner.nextLine().trim();
        while(!isInteger(selectedOptionStr)){
            System.out.println("Option needs to be an integer");
            selectedOptionStr = scanner.nextLine().trim();
        }
        while(Integer.parseInt(selectedOptionStr)>option||Integer.parseInt(selectedOptionStr)<1){
            System.out.println(String.format("Option needs to be within 1 and %d",option));
        }
        int selectedOption = Integer.parseInt(selectedOptionStr)-1;//-1 because the list starts at 0

        Configuration selectedConfiguration = loadedConfigurations.get(selectedOption);
        return selectedConfiguration;
    }

    /**
     * Gets option 1 parameters to create the matrices and the page table
     * @return Arraylist with the parameters in the followin order.parameter order: page size, int size, rows, columns, run type
     */
    public static ArrayList selectParameters(){
        //TODO: Si tenemos tiempo seria bueno hacer como en caso 1 que tenemos un txt del que cargamos las configs, aunque al menos en este caso las configs se guardan
        //TODO: Toca hacer el check de que el intSize quepa n veces en el page size (no se si eso es un req)
        int pageSize;//Tamaño de una página (TP)
        int intSize;//Tamaño de un número entero (TE)
        int rows;//Número de filas de las matrices (NF)
        int columns;//Número de columnas de las matrices (NC)
        int runType;//Tipo de recorrido (TR, 1 para recorrido1 o 2 para recorrido 2)

        Scanner scanner = new Scanner(System.in);

        /*
        Get page size
         */
        System.out.println("Enter page size: ");
        String pageSizeUnparsed = scanner.nextLine();
        while(!isInteger(pageSizeUnparsed)){
            System.out.println("Page size needs to be an integer. Please try again.");
            pageSizeUnparsed = scanner.nextLine();
        }
        pageSize = Integer.parseInt(pageSizeUnparsed);

        /*
        Get integer size
         */
        System.out.println("Enter integer size: ");
        String intSizeUnparsed = scanner.nextLine();
        while(!isInteger(intSizeUnparsed)){
            System.out.println("Integer size needs to be an integer. Please try again.");
            intSizeUnparsed = scanner.nextLine();
        }
        intSize = Integer.parseInt(intSizeUnparsed);

        /*
        Get rows
         */
        System.out.println("Enter number of rows for the matrices: ");
        String rowsUnparsed = scanner.nextLine();
        while(!isInteger(rowsUnparsed)){
            System.out.println("Row size needs to be an integer. Please try again.");
            rowsUnparsed = scanner.nextLine();
        }
        //TODO : Undo si no hay una restriccion
        /**
        while(pageSize%Integer.parseInt(rowsUnparsed)!=0){
            System.out.println("The entered page size is not divisible by the integer size selected. Please select a valid integer size.");
        }
        **/
        rows = Integer.parseInt(rowsUnparsed);

        /*
        Get columns
         */
        System.out.println("Enter number of columns for the matrices: ");
        String columnsUnparsed = scanner.nextLine();
        while(!isInteger(columnsUnparsed)){
            System.out.println("Columns size needs to be an integer. Please try again.");
            columnsUnparsed = scanner.nextLine();
        }
        columns = Integer.parseInt(columnsUnparsed);

        /*
        Get run type (recorrido 1 o recorrido 2)
         */
        System.out.println("Enter run type (1 or 2): ");
        String runTypeUnparsed = scanner.nextLine();
        while(!isInteger(runTypeUnparsed)){
            System.out.println("Row size needs to be an integer. Please try again.");
            runTypeUnparsed = scanner.nextLine();
        }
        while(Integer.parseInt(runTypeUnparsed)!= 1 ||Integer.parseInt(runTypeUnparsed)!= 2){
            System.out.println("Need to select 1 or 2 for tipo de recorrido.");
            runTypeUnparsed = scanner.nextLine();
        }
        runType = Integer.parseInt(runTypeUnparsed);




        ArrayList<Integer> parameters = new ArrayList<>();
        parameters.add(pageSize);
        parameters.add(intSize);
        parameters.add(rows);
        parameters.add(columns);
        parameters.add(runType);

        return parameters;

    }

    /**
     * Creates user prompt for user to insert the option he wants to run and returns int of chosen option.
     * @return selectedOption integer
     */
    public static int selectOptionPrompt(){
        System.out.println("Please select the option you want to run: ");
        System.out.println("1. Option 1");
        if(loadedConfigurations.isEmpty()){
            System.out.println("2. Option 2");
        }

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().replaceAll("( )+", "").trim();
        int selectedOption = 1;//default is 1

        //if the option isnt either 1 or 2 it isnt valid.
        while(!userInput.equals("1") || !userInput.equals("2") || !userInput.equals("2") && loadedConfigurations.isEmpty()){
            if(userInput.equals("2") && loadedConfigurations.isEmpty()){
                System.out.println("No configurations have been loaded, please create configuration file with option 1.");
            }
            else{
                System.out.println("That is not a valid option. Choose 1 or 2. Please try again.");
            }
            userInput = scanner.nextLine().replaceAll("( )+", "").trim();
        }

        selectedOption = Integer.parseInt(userInput);
        return selectedOption;
    }

    /**
     * Checks if a string represents a number
     * @param stringNumber a string that might represent a number
     * @return boolean, true if the string represents an integer, false if the contrary
     */
    public static boolean isInteger(String stringNumber) {
        if (stringNumber == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(stringNumber.trim());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
