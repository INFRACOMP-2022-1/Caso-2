package Proyect;

import java.util.ArrayList;

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
        this.pageSize = pageSize;
        this.intSize = intSize;
        this.rows = rows;
        this.columns = columns;
        this.runType = runType;

        //Aditional info
        this.pageCapacity = pageSize/intSize;
        this.pageNumber = ((columns*rows)/pageCapacity)*3 ;
        this.totalReferences = pageNumber*pageCapacity;

        //Create matrix 1,2,3 con el recorrido indicado
        createMatrices();

        //Create the base array for the page table (just array)

        //Create the page table array (special data structure)

        //Create the page array with reference format (the format that is directy usefull for option 2)
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
                    this.matrix3[row][column] = matrix1[row][column]+matrix2[row][column];
                    virtualMemory.add(matrix3[row][column]);//Adds to the main virtual memory list the current element that has been created
                }
            }
        }

        //RECORRIDO 2
        else{
            for(int column = 0; column < columns;column++){
                for(int row = 0 ; row < rows;row++){
                    this.matrix3[row][column] = matrix1[row][column]+matrix2[row][column];
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
                matrix[row][column] = matrixFillNumber;
                virtualMemory.add(matrix1[row][column]);//Adds to the main virtual memory list the current element that has been created
                matrixFillNumber+=1;
            }
        }
    }

    public void createPageTable(){

    }

    public void createPageTableSpecial(){

    }

}
