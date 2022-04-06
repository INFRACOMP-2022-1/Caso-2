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

    /*
    Saves a reference to thread 1.
     */
    public Thread thread1;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    /**
     * Constructor of thread 2. This is the thread that is responsible for the execution of the aging algorithm on the frame page table
     */
    public Thread2(Buffer buffer, Thread1 thread1) {
        this.buffer = buffer;
        this.thread1 = thread1;
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    /**
     * The run method that the thread will execute.
     * The thread will continue to age the elements in the pageFrameReference table as long as thread 1 is running.
     */
    @Override
    public void run(){
        //TODO: Contenido del run. Correr hasta que termine de iterar a traves de toda la lista.
        while(thread1.isAlive()){
            agePageFrameTable();
        }
    }

    /**
     * Its the instruction for the thread to age the pageFrameReference table elements, and then to sleep for one millisecond
     * Werther or not thread 1 is running depends if it has cycled through all the page references in the reference table.
     */
    public void agePageFrameTable(){
        buffer.age();

        //Thread goes to sleep 1 millisecond after excecuting the aging algorithm
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Buffer getBuffer() {
        return buffer;
    }
}
