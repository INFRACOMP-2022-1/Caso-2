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
    Saves a reference to the status of execution of thread 1. If true then the thread is running, else its finished its execution.
     */
    public boolean thread1IsRunning;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    /**
     * Constructor of thread 2. This is the thread that is responsible for the execution of the aging algorithm on the frame page table
     */
    public Thread2(Buffer buffer, boolean thread1IsRunning) {
        this.buffer = buffer;
        this.thread1IsRunning = thread1IsRunning;
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
        while(isThread1IsRunning()){
            //TODO: Excecute aging algorithm. Toca antes implementar esos metodos en el buffer
            //buffer.ageReferences();

            //Thread goes to sleep 1 millisecond after excecuting the aging algorithm
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public Buffer getBuffer() {
        return buffer;
    }

    public boolean isThread1IsRunning() {
        return thread1IsRunning;
    }
}
