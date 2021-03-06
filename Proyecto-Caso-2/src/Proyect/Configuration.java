package Proyect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * The configuration class is the one responsible for creating the matrices, saving all the info on the page sizes, integer size, and other important information. It also sets up the tables for option 2 to then be able to run its algorithms.
 * This class fulfills the requirements for option 1.
 * @author Santiago Vela
 * @author Verónica Escobar
 */

public class Configuration {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
    The initial number that the matrices start with
     */
    public int matrixFillNumber = 1;

    public int pageSize;
    public int intSize;
    public int rows;
    public int columns;
    public int runType;

    public String configurationName;

    public boolean loadingExistingConfiguration;//basically tells if its a brand new configuration or if it is a loaded configuration. In practice is to avoid printing out the report results and creating a new report (the report already exists)

    /*
     * The number of pages needed to store all the integers of the matrices
     */
    public int pageNumber;

    /*
     * The number of integers that can fit in a single page
     */
    public int pageCapacity;

    /*
     * The total number of integers stored in the virtual memory. Or the total number of elements in the 3 matrices
     */
    public int totalReferences;

    public int[][] matrix1;
    public int[][] matrix2;
    public int[][] matrix3;

    public ArrayList<Integer> virtualMemory;//this is the base array just the references in order of the tipo de recorrido
    public ArrayList<Page> pageTable;//an array containing all the pages
    public ArrayList<ElementInfo> pageTableSpecial;//an array that contains the elements like the virtual memory but has some extra information so option 2 can be run

    public ArrayList<Integer> virtMemoryPositionsAccesed;//Stores the positions in the array that are accesed in the type of run
    public ArrayList<ElementInfo> referenceTable; //Records the references to the diferent pages depending on the algorithm selected

    //DISCLAIMER: The difference between pageTableSpecial and reference table is that pageTableSpecial contains the info of the page of each reference in the virtualMemory in ORDER, meanwhile referenceTable has the info of the accesed elements acording to the run type
    //Me sobre complique? Si pero creo que funciona

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public Configuration(int pageSize, int intSize, int rows, int columns, int runType, String configurationName, boolean loadingExistingConfiguration){
        //Save the parameters
        this.pageSize = pageSize;
        this.intSize = intSize;
        this.rows = rows;
        this.columns = columns;
        this.runType = runType;

        //Configuration file name
        this.configurationName = configurationName;

        //Additional info
        this.pageCapacity = pageSize/intSize;
        this.pageNumber = ((columns*rows)/pageCapacity)*3 ;
        this.totalReferences = pageNumber*pageCapacity;

        //Create the base array for the page table (just array)
        virtualMemory = new ArrayList<>();

        //Create the page table array (special data structure)
        pageTable = new ArrayList<>();

        //Create the page array with reference format (the format that is directy usefull for building the referene table used in option table)
        pageTableSpecial = new ArrayList<>();

        //Reference table(track) keeps track de que paginas son referenciadas para crear la matriz 3
        referenceTable = new ArrayList<>();

        //initiate matrices
        matrix1 = new int[rows][columns];
        matrix2 = new int[rows][columns];
        matrix3 = new int[rows][columns];

        //Create matrix 1,2,3 con el recorrido indicado. Also
        createMatrices();

        //Create virtual memory has already been done in the matrix processes

        //Create page tables
        createPageTable();

        //Create special page tables
        createPageTableSpecial();

        //Create reference table (used in option 2)
        createReferenceTable();

        //if its creating a brand new configuration this needs to be done, else its already been done before and there is no need to do  it again
        if(loadingExistingConfiguration==false) {
            //Crear reporte
            createReport();

            //Crear console output
            consoleOutput();

            //Crear recovery file para futuras ejecuciones
            createRecoveryFile();
        }
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    /**
     * Creates matrix 1,2,3 and also adds the reference to the elements of the matrix to the virtualMemoryArray
     */
    public void createMatrices(){
        //Create matrix 1
        createBaseMatrices(this.matrix1);

        //Create matrix 2
        createBaseMatrices(this.matrix2);

        //Create matrix 3

        //RECORRIDO 1
        if(runType==1){
            for(int row = 0 ; row < rows;row++){
                for(int column = 0; column < columns;column++){

                    //Sum
                    matrix3[row][column] = matrix1[row][column]+matrix2[row][column];
                    virtualMemory.add(matrix3[row][column]);//Adds to the main virtual memory list the current element that has been created
                    //Position accesed stored
                }
            }
        }

        //RECORRIDO 2
        else{
            for(int column = 0; column < columns;column++){
                for(int row = 0 ; row < rows;row++){
                    matrix3[row][column] = matrix1[row][column]+matrix2[row][column];
                    virtualMemory.add(matrix3[row][column]);//Adds to the main virtual memory list the current element that has been created
                }
            }
        }


    }

    /**
     * Method for the creation of matrix 1 and 2
     * @param matrix the matrix that is being currently used
     */
    private void createBaseMatrices(int[][] matrix) {
        for(int row = 0 ; row < rows;row++){
            for(int column = 0; column < columns;column++){
                matrix[row][column] = getMatrixFillNumber();
                virtualMemory.add(matrix1[row][column]);//Adds to the main virtual memory list the current element that has been created
                matrixFillNumber+=1;
            }
        }
    }

    /**
     * Creates the page table that contains pages with the respective integers inside
     */
    public void createPageTable(){
        int currentPageNumber = 0;
        Page currentPage;
        int curNumElementsInPage = 0;
        int capacity = pageCapacity;//the cap capacity of a page

        //page zero is created
        currentPage = new Page(intSize,pageSize,pageCapacity,currentPageNumber);
        for(int i = 0 ; i < virtualMemory.size();i++){
            try{
                //if the current page capacity isnt meet then add the element to the current page
                if(curNumElementsInPage < capacity){
                    currentPage.addInteger(virtualMemory.get(i));
                    curNumElementsInPage +=1;
                }
                //when the page capacity has been filled add the current page to the table, and create a new empty page and add the current int to it
                else{
                    pageTable.add(currentPage);//page is added to the table
                    currentPageNumber +=1;
                    currentPage = new Page(intSize,pageSize,pageCapacity,currentPageNumber);
                    currentPage.addInteger(virtualMemory.get(i));
                    curNumElementsInPage = 1;
                }

                //This handles the case that the number of elements in virtual memory don't completely fill the page. This makes sure the unfilled last page adds
                if(i == virtualMemory.size()-1){
                    pageTable.add(currentPage);//page is added to the table
                }
            }
            catch (Exception e){
                //This should never get here by any means the ifs above should avoid it
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the special page table that is going to be used in option 2
     */
    public void createPageTableSpecial(){
        //Go through the page table and create the elements
        for(int currentPage = 0; currentPage <pageTable.size();currentPage++){
            ArrayList<Integer> pageContent = pageTable.get(currentPage).getPageContent();
            for(int elementPosition = 0; elementPosition < pageContent.size();elementPosition++){
                int currentElementDisplacement = elementPosition * getIntSize();
                ElementInfo currElementInfo = new ElementInfo(pageContent.get(elementPosition),currentElementDisplacement,currentPage,elementPosition);
                pageTableSpecial.add(currElementInfo);
            }
        }
    }

    /**
     * Creates the page reference table that is used in option 2
     */
    public void createReferenceTable(){
        //Santiago ESTE ES EL METODO DEL HASHMAP ADAPTADO ,y si esta re fumado
        ArrayList<Integer> accesPostitionTable = getIntegerAccessTrack();


        //recorro todos los accesos y me voy a su respectiva posicion en page table especial (los index de ambos estan alineados)
        for(int i = 0; i < accesPostitionTable.size();i++){
            int position= accesPostitionTable.get(i);//int of the position to be accesed
            ElementInfo elementAccesed = pageTableSpecial.get(position);//gets all the info of te accesed element, alongs with page size and other usefull info for option 2
            referenceTable.add(elementAccesed);
        }
    }


    /**
     * Basically gets the positions of the elements accesed depending on the run type (recorrido).
     * This will be used by createPageTableSpecial to get the pages to then be able to do the page reference table
     * @return Arraylist with the position of the elements within the virtual memory acording to the accesses done by the run type
     */
    public ArrayList<Integer> getIntegerAccessTrack(){
        //Go through the page table and create the elements

        ArrayList<Integer> accessPositionTable = new ArrayList<>();

        //go through each element of each matrix
        int currentRow = 0;
        int currentColumn = 0;

        //stores the base counters for each matrix, these values wont change
        int m1CounterBase = 0;
        int m2CounterBase =(rows*columns);
        int m3CounterBase = (rows*columns)*2;

        //for recorrido 2 only
        int cycle = 0;

        //stores base counters, these values will change
        int matrix1Counter = 0;//gets index in table where matrix 1 starts
        int matrix2Counter = (rows*columns);//gets index in table where matrix 2 starts
        int matrix3Counter = (rows*columns)*2;//gets index in table where matrix 3 starts
        for(int i = 0;i<rows*columns;i++){
            if(runType==1){
                //cols first then rows

                //access first matrix
                accessPositionTable.add(matrix1Counter);
                matrix1Counter+=1;

                //access second matrix
                accessPositionTable.add(matrix2Counter);
                matrix2Counter+=1;

                //access third matrix
                accessPositionTable.add(matrix3Counter);
                matrix3Counter+=1;
            }
            if(runType==2){
                //rows first then column
                if(i%rows==0 && i != 0){
                    //adds one row cycle completed
                    cycle += 1;
                    //resets to base all counters
                    matrix1Counter= m1CounterBase+cycle;
                    matrix2Counter = m2CounterBase+cycle;
                    matrix3Counter = m3CounterBase+cycle;
                }

                //access first matrix
                accessPositionTable.add(matrix1Counter);
                matrix1Counter+=columns;

                //access second matrix
                accessPositionTable.add(matrix2Counter);
                matrix2Counter+=columns;

                //access third matrix
                accessPositionTable.add(matrix3Counter);
                matrix3Counter+=columns;

            }
        }
        return accessPositionTable;
    }

    /**
     * Creates the report needed for option 1
     */
    public void createReport(){
        try {
            String fileRoute = String.format("Proyecto-Caso-2/option1OutputFiles/%s.txt", configurationName);
            File resultFile = new File(fileRoute);
            if (resultFile.createNewFile()) {
                System.out.println("You can see the results in the following file in the option1OutputFiles folder: " + resultFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            //GET FILE
            FileWriter fileWriter = new FileWriter(fileRoute);
            fileWriter.write("\n");
            fileWriter.write("REPORT");
            fileWriter.write("\n");

            //WRITE REPORT CONTENT
            fileWriter.write(String.format("TP=%d\n",pageSize));
            fileWriter.write(String.format("TE=%d\n",intSize));
            fileWriter.write(String.format("NF=%d\n",rows));
            fileWriter.write(String.format("NC=%d\n",columns));
            fileWriter.write(String.format("TR=%d\n",runType));
            fileWriter.write(String.format("NP=%d\n",pageNumber));
            fileWriter.write(String.format("NR=%d\n",totalReferences));


            ArrayList<Integer> accessList = listOfIntegers();


            ArrayList<String> listOfArrayPositionsStr = new ArrayList<>();
            for(int i = 0; i< rows; i++){
                for(int j = 0; j < columns; j++){
                    listOfArrayPositionsStr.add((String.format("A:[%d-%d]",i,j)));
                    listOfArrayPositionsStr.add((String.format("B:[%d-%d]",i,j)));
                    listOfArrayPositionsStr.add((String.format("C:[%d-%d]",i,j)));
                }
            }

            ArrayList<String> listOfAccesessStr = new ArrayList<>();
            for(int i = 0; i < (columns*rows)*3;i++){
                int position = accessList.get(i);//the position within pageTableSpecial to access
                ElementInfo currElement = pageTableSpecial.get(position);//gets the element and its info
                int currElementPage = currElement.getPageNumber();
                int currElementDisplacement = currElement.getDisplacement();

                //Matrix A
                if(i%3==0){
                    listOfAccesessStr.add(String.format(",%d,%d",currElementPage,currElementDisplacement));
                }

                //Matrix B
                if(i%3==1){
                    listOfAccesessStr.add(String.format(",%d,%d",currElementPage,currElementDisplacement));
                }

                //Matrix C
                if(i%3 ==2){
                    listOfAccesessStr.add(String.format(",%d,%d",currElementPage,currElementDisplacement));
                }
            }

            for(int i = 0;i < 3*(rows*columns);i++){
                fileWriter.write(listOfArrayPositionsStr.get(i)+listOfAccesessStr.get(i)+"\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Create the console output refelecting all the content of option 1
     */
    public void consoleOutput(){
        System.out.println(String.format("TP=%d",pageSize));
        System.out.println(String.format("TE=%d",intSize));
        System.out.println(String.format("NF=%d",rows));
        System.out.println(String.format("NC=%d",columns));
        System.out.println(String.format("TR=%d",runType));
        System.out.println(String.format("NP=%d",pageNumber));
        System.out.println(String.format("NR=%d",totalReferences));

        int currRow = 0;
        int currColumn = 0;


        ArrayList<Integer> accessList = listOfIntegers();


        ArrayList<String> listOfArrayPositionsStr = new ArrayList<>();
        for(int i = 0; i< rows; i++){
            for(int j = 0; j < columns; j++){
                listOfArrayPositionsStr.add((String.format("A:[%d-%d]",i,j)));
                listOfArrayPositionsStr.add((String.format("B:[%d-%d]",i,j)));
                listOfArrayPositionsStr.add((String.format("C:[%d-%d]",i,j)));
            }
        }

        ArrayList<String> listOfAccesessStr = new ArrayList<>();
        for(int i = 0; i < (columns*rows)*3;i++){
            int position = accessList.get(i);//the position within pageTableSpecial to access
            ElementInfo currElement = pageTableSpecial.get(position);//gets the element and its info
            int currElementPage = currElement.getPageNumber();
            int currElementDisplacement = currElement.getDisplacement();

            //Matrix A
            if(i%3==0){
                listOfAccesessStr.add(String.format(",%d,%d",currElementPage,currElementDisplacement));
            }

            //Matrix B
            if(i%3==1){
                listOfAccesessStr.add(String.format(",%d,%d",currElementPage,currElementDisplacement));
            }

            //Matrix C
            if(i%3 ==2){
                listOfAccesessStr.add(String.format(",%d,%d",currElementPage,currElementDisplacement));
            }
        }

        for(int i = 0;i < 3*(rows*columns);i++){
            System.out.println(listOfArrayPositionsStr.get(i)+listOfAccesessStr.get(i));
        }
    }

    /**
     * Gets the list of integers in from the three matrices
     * @return List with the integers
     */
    public ArrayList<Integer> listOfIntegers(){
        ArrayList<Integer> accessList = new ArrayList<>();
        int matrix1Counter = 0;//gets index in table where matrix 1 starts
        int matrix2Counter = (rows*columns);//gets index in table where matrix 2 starts
        int matrix3Counter = (rows*columns)*2;//gets index in table where matrix 3 starts

        for(int i = 0;i<rows*columns;i++){

            accessList.add(matrix1Counter);
            matrix1Counter += 1;

            accessList.add(matrix2Counter);
            matrix2Counter += 1;

            accessList.add(matrix3Counter);
            matrix3Counter += 1;

        }
        return accessList;
    }

    /**
     * In order to persist the configurations over multiple excecutions the parameters to create the configuration need to be saved, in such a way that the configurations can be re-loaded
     */
    public void createRecoveryFile(){
        //Save all the parameter data in a txt file
        //int pageSize, int intSize, int rows, int columns, int runType, String configurationName
        int PAGE_SIZE_PARAM = getPageSize();
        int INT_SIZE_PARAM = getIntSize();
        int ROWS_PARAM = getRows();
        int COLUMNS_PARAM = getColumns();
        int RUN_TYPE_PARAM = getRunType();
        String CONFIGURATION_NAME_PARAM = getConfigurationName();

        try {
            String fileRoute = String.format("Proyecto-Caso-2/configurations/%s.txt", CONFIGURATION_NAME_PARAM);
            File resultFile = new File(fileRoute);
            if (resultFile.createNewFile()) {
                System.out.println("You can see the results in the following file in the configurations folder: " + resultFile.getName());

                //GET FILE
                FileWriter fileWriter = new FileWriter(fileRoute);

                //WRITE PARAMETERS IN ORDER JUST SEPARATED BY THE LINE JUMP
                fileWriter.write(PAGE_SIZE_PARAM+"\n");
                fileWriter.write(INT_SIZE_PARAM+"\n");
                fileWriter.write(ROWS_PARAM+"\n");
                fileWriter.write(COLUMNS_PARAM+"\n");
                fileWriter.write(RUN_TYPE_PARAM+"\n");
                fileWriter.write(CONFIGURATION_NAME_PARAM+"\n");

                fileWriter.close();
            } else {
                //IF THE CONFIG HAS ALREADY BEEN SAVED THERE IS NO NEED TO SAVE IT AGAIN
                System.out.println("Configuration already exists. Moving on to the next one....");//THIS IS NOT AN ERROR MESSAGE
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMatrixFillNumber() {
        return matrixFillNumber;
    }

    public void setMatrixFillNumber(int matrixFillNumber) {
        this.matrixFillNumber = matrixFillNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIntSize() {
        return intSize;
    }

    public void setIntSize(int intSize) {
        this.intSize = intSize;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRunType() {
        return runType;
    }

    public void setRunType(int runType) {
        this.runType = runType;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCapacity() {
        return pageCapacity;
    }

    public void setPageCapacity(int pageCapacity) {
        this.pageCapacity = pageCapacity;
    }

    public int getTotalReferences() {
        return totalReferences;
    }

    public void setTotalReferences(int totalReferences) {
        this.totalReferences = totalReferences;
    }

    public int[][] getMatrix1() {
        return matrix1;
    }

    public void setMatrix1(int[][] matrix1) {
        this.matrix1 = matrix1;
    }

    public int[][] getMatrix2() {
        return matrix2;
    }

    public void setMatrix2(int[][] matrix2) {
        this.matrix2 = matrix2;
    }

    public int[][] getMatrix3() {
        return matrix3;
    }

    public void setMatrix3(int[][] matrix3) {
        this.matrix3 = matrix3;
    }

    public ArrayList<Integer> getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(ArrayList<Integer> virtualMemory) {
        this.virtualMemory = virtualMemory;
    }

    public ArrayList<Page> getPageTable() {
        return pageTable;
    }

    public void setPageTable(ArrayList<Page> pageTable) {
        this.pageTable = pageTable;
    }

    public ArrayList<ElementInfo> getPageTableSpecial() {
        return pageTableSpecial;
    }

    public void setPageTableSpecial(ArrayList<ElementInfo> pageTableSpecial) {
        this.pageTableSpecial = pageTableSpecial;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    public ArrayList<Integer> getVirtMemoryPositionsAccesed() {
        return virtMemoryPositionsAccesed;
    }

    public void setVirtMemoryPositionsAccesed(ArrayList<Integer> virtMemoryPositionsAccesed) {
        this.virtMemoryPositionsAccesed = virtMemoryPositionsAccesed;
    }

    public ArrayList<ElementInfo> getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(ArrayList<ElementInfo> referenceTable) {
        this.referenceTable = referenceTable;
    }
}
