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

    public Option2(Configuration configuration, int pageFrameSize) {
        this.configuration = configuration;
        this.pageFrameSize = pageFrameSize;

        //Create the buffer
        Buffer buffer = new Buffer(pageFrameSize);

        //Create the threads
        Thread1 thread1 = new Thread1(buffer,configuration.getReferenceTable());
        Thread2 thread2 = new Thread2(buffer,thread1.getIsThreadAlive());

        //Start the threads
        thread2.start();//TODO: Terminar de crear la clase thread2
        thread1.start();//TODO: Terminar de crear la clase thread1

        //TODO: Poner una barrera para que thread 2 se muera cuando thread 1 se muera o algo por el estilo , por que thread 2 no para de ejecutar de pro si sola nunca

        //TODO: Output reprote de resultados (fallos de pagina etc.) . Esta info tiene que ser extraida del buffer

        System.out.println("verrrrrooonica");


    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------



}
