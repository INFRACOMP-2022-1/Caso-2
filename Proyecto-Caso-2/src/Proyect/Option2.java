package Proyect;

import java.util.ArrayList;

public class Option2 {

    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    public Configuration configuration;
    public int pageFrameSize;
    private ArrayList<ArrayList<Integer>> paginas ;
    public Buffer buffer;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public Option2(Configuration configuration, int pageFrameSize) throws InterruptedException {
        this.configuration = configuration;
        this.pageFrameSize = pageFrameSize;

        //Create the buffer
        Buffer buffer = new Buffer(pageFrameSize);
        this.buffer = buffer;

        //Create the threads
        Thread1 thread1 = new Thread1(buffer,configuration.getReferenceTable());
        Thread2 thread2 = new Thread2(buffer,thread1);

        //Start the threads
        thread1.start();//TODO: Terminar de crear la clase thread1
        thread2.start();//TODO: Terminar de crear la clase thread2


        //Waits for both thread 1 and 2 to finish execution
        thread1.join();
        thread2.join();

        //TODO: Output reprote de resultados (fallos de pagina etc.) . Esta info tiene que ser extraida del buffer
        option2ResultConsole();
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    public void option2ResultConsole(){
        System.out.println("File loaded: " + configuration.configurationName + "\n");

        System.out.println("Configuration: ");
        System.out.println(String.format("TP=%d",configuration.pageSize));
        System.out.println(String.format("TE=%d",configuration.intSize));
        System.out.println(String.format("NF=%d",configuration.rows));
        System.out.println(String.format("NC=%d",configuration.columns));
        System.out.println(String.format("TR=%d",configuration.runType));
        System.out.println(String.format("NP=%d",configuration.pageNumber));
        System.out.println(String.format("NR=%d",configuration.totalReferences));
        System.out.println("");

        System.out.println("Page frames(Marcos de pagina): " + buffer.pageFrames + "\n");

        System.out.println("Page errors(Fallos de pagina): " + buffer.pageErrors + "\n\n");

        System.out.println("For more information on the configuration file you can check in option1OutputFiles/" + configuration.configurationName + ".txt" );

    }


}
