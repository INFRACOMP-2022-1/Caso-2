package Proyect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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

        //Output report
        option2ResultConsole();

        //Save report
        createReportFile();
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
        System.out.println("");

        System.out.println("Page frames(Marcos de pagina): " + buffer.pageFrames + "\n\n");

        System.out.println("Page errors(Fallos de pagina): " + buffer.pageErrors + "\n\n");

        System.out.println("For more information on the configuration file you can check in option1OutputFiles/" + configuration.configurationName + ".txt" );

    }

    public void createReportFile(){
        try {
            Random rand = new Random(); //instance of random class
            int int_random = rand.nextInt(1000);

            String fileRoute = String.format("Proyecto-Caso-2/option2OutputFiles/%s%s.txt", configuration.configurationName,int_random );
            File resultFile = new File(fileRoute);
            if (resultFile.createNewFile()) {
                System.out.println("You can see the results in the following file in the option2OutputFiles folder: " + resultFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            //GET FILE
            FileWriter fileWriter = new FileWriter(fileRoute);
            fileWriter.write("\n");
            fileWriter.write("REPORT");
            fileWriter.write("\n");

            //WRITE REPORT CONTENT

            //configuration
            fileWriter.write("CONFIGURATION:\n");
            fileWriter.write(String.format("TP=%d\n",configuration.pageSize));
            fileWriter.write(String.format("TE=%d\n",configuration.intSize));
            fileWriter.write(String.format("NF=%d\n",configuration.rows));
            fileWriter.write(String.format("NC=%d\n",configuration.columns));
            fileWriter.write(String.format("TR=%d\n",configuration.runType));
            fileWriter.write(String.format("NP=%d\n",configuration.pageNumber));
            fileWriter.write(String.format("NR=%d\n\n",configuration.totalReferences));

            //option 2 info

            fileWriter.write("MARCOS DE PAGINA:\n");
            fileWriter.write("Page frames(Marcos de pagina): " + buffer.pageFrames + "\n\n");

            fileWriter.write("FALLOS DE PAGINA:\n");
            fileWriter.write("Page errors(Fallos de pagina): " + buffer.pageErrors + "\n\n");

            fileWriter.write("For more information on the configuration file you can check in option1OutputFiles/" + configuration.configurationName + ".txt" );

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
