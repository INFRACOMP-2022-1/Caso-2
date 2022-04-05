package Proyect;

/**
 * This page is responsible for executing the aging algorithm on the frame page table.
 */
public class Thread2 extends Thread {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
    The buffer that contains the frame page table
     */
    public Buffer buffer;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    /**
     * Constructor of thread 2. This is the thread that is responsible for the execution of the aging algorithm on the frame page table
     */
    public Thread2(Buffer buffer) {
        this.buffer = buffer;
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    /**
     * The run method that the thread will execute
     */
    @Override
    public void run(){
        //TODO: Contenido del run. Correr hasta que termine de iterar a traves de toda la lista.
    }
}
