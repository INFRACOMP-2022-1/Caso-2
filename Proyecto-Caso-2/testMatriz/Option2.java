package Proyect;

import java.util.ArrayList;

public class Option2 {

    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    public Configuration configuration;
    public int pageFrameSize;
    private ArrayList<ArrayList<Integer>> TPAGES ;



    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public Option2(Configuration configuration, int pageFrameSize) {
        this.configuration = configuration;
        this.pageFrameSize = pageFrameSize;
        creacionPaginas(pageFrameSize);



    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------



    public synchronized void  addPage(String message) throws  InterruptedException{

        while( comprobacion()){
            try {
                wait();// Makes the incoming message wait (passive) until someone wakes it up
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }


        addpage(message); //Stores the received message in the buffer content linked list

        synchronized (this){
            notify();
        }
        //notify(); //notifies a random producerConsumer in monitor queue to wake up
    }



    public boolean  comprobacion() {
        boolean rta = false;
        
         for(int j= 0; j< TPAGES.size(); j++){
    
             if (TPAGES.get(j).get(0) == 0) {
                rta= true;
            
             }
         }
         return rta;      
    }


  

   



    public void  creacionPaginas(int num)
	{	
        for(int i= 0; i < num; i++){

	        	Integer l1= 0;
	        	Integer l2= 0;      	
	        	ArrayList<Integer> l3= new ArrayList<>();	
	        	l3.add(l1);
	        	l3.add(l2);   	
	        	TPAGES.add(l3);
	        }
System.out.println(TPAGES);
	

	}


}
