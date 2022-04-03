package Proyect;

import java.util.ArrayList;

public class Configuration {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    public int pageSize;
    public int intSize;
    public int rows;
    public int columns;
    public int runType;

    public int pageNumber;
    public int pageCapacity;
    public int totalReferences;

    public int[][] matrix1;
    public int[][] matrix2;
    public int[][] matrix3;

    public ArrayList<Integer> virtualMemory;//this is the base array just the references in order of the tipo de recorrido
    public ArrayList<Page> pageArray;//an array containing all the pages

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public Configuration(int pageSize, int intSize, int rows, int columns, int runType){
        //Save the parameters
        pageSize = pageSize;
        intSize = intSize;
        rows = rows;
        columns = columns;
        runType = runType;

        //Aditional info
        pageCapacity = pageSize/intSize;
        pageNumber = ((columns*rows)/pageCapacity)*3 ;
        totalReferences = pageNumber*pageCapacity;

        //Create matrix 1,2,3

        //Create the base array for the page table (just array)

        //Create the page table array (special data structure)

        //Create the page array with reference format (the format that is directy usefull for option 2)
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    public void createMatrices(){
        //Create matrix 1

        //Create matrix 2

        //Create matrix 3
    }

    public void createPageTable(){

    }

    public void createPageTableSpecial(){

    }

}
