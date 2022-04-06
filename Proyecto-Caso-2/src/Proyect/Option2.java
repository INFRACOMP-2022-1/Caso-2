package Proyect;

import java.util.ArrayList;

public class Option2 {

    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    public Configuration configuration;
    public int pageFrameSize;
    private ArrayList<ArrayList<Integer>> paginas ;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public Option2(Configuration configuration, int pageFrameSize) throws InterruptedException {
        this.configuration = configuration;
        this.pageFrameSize = pageFrameSize;

        //Create the buffer
        Buffer buffer = new Buffer(pageFrameSize);

        //Create the threads
        Thread1 thread1 = new Thread1(buffer,configuration.getReferenceTable());
        Thread2 thread2 = new Thread2(buffer,thread1);

        //Start the threads
        thread1.start();//TODO: Terminar de crear la clase thread1
        thread2.start();//TODO: Terminar de crear la clase thread2


        //TODO: Output reprote de resultados (fallos de pagina etc.) . Esta info tiene que ser extraida del buffer
        thread1.join();
        thread2.join();

        System.out.println("Fallos: " + buffer.pageErrors);

    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------



}
