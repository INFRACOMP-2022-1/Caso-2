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

    /*
    If the current thread is still runing or not
     */
    public boolean threadAlive;

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
        this.threadAlive = true;
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

            try {
                updatePageFrameTable(currentPageReference);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        threadAlive = false;//this is used to signal to thread 2 that thread 1 has finished its execution
    }



    public void updatePageFrameTable(int currentPageReference) throws InterruptedException{
        
        Buffer.adicionar_pagina(currentPageReference);

        //Sends the thread to sleep for to milliseconds after adding a page on the frame page table
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public ArrayList<ElementInfo> getReferenceTable() {
        return referenceTable;
    }

    public boolean getIsThreadAlive() {
        return threadAlive;
    }

}
