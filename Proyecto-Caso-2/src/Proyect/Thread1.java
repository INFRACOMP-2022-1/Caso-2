package Proyect;

import java.util.ArrayList;

/**
 * This is responsible for iterating through the reference table and inserting the latest page reference in the frame page table.
 * It handles all posible scenarios, such as inserting a page in an empty slot in the frame page table, a fail in page insertion (and the swap)
 * This should run until the whole of the page reference table has been finished
 */
public class Thread1 extends Thread{
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
    The buffer that contains the frame page table
     */
    public Buffer buffer;

    /*
    Table of references
     */
    public ArrayList<ElementInfo> referenceTable;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    /**
     * The constructor of thread 1.
     * @param referenceTable the table of references produced by the execution of the run type
     */
    public Thread1(Buffer buffer,ArrayList<ElementInfo> referenceTable){
        this.buffer = buffer;
        this.referenceTable = referenceTable;
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------
    /**
     * The run method that the thread will execute
     */
    @Override
    public void run(){
        //TODO: Contenido del run. Se muere cuando se le terminaron las paginas que revisar
        for(int i = 0 ; i < referenceTable.size(); i++){
            ElementInfo currentElement = referenceTable.get(i);//Gets the element
            int currentPageReference = currentElement.getPageNumber();//gets the page where the current element referenced is located

            //TODO: Llamar al metodo sincronziado en el buffer que intenta acceder la tabla de paginas para actualizar lso valroes. Seguramente el metodo que directamente accede no esta sincronizado pero llama otros elementos dentro de buffer que monitorean el acceso a la tabla. Ver como funciona eso
            //buffer.addPageMethod(currentPageReference);

            //Sends the thread to sleep for to milliseconds after adding a page on the frame page table
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
