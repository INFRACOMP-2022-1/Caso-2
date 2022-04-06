package Proyect;

import java.util.ArrayList;
import java.util.HashMap;



public class Buffer



{
	
	
	private  int marcos_pagina;

	// memory in where  we insert  reference pages 
	ArrayList<ArrayList<Integer>> TP;
	
	// this will tell me the amount of memory failures that I had
	private int num_fallos;

	
	// this is a record of the   reference pages  that are in my memory 

	private ArrayList<Integer>  contentPage;





    

	
	// to create a a buffer we need the size of that memory, thanks to the "marcos_pagina" we can determinar that size
	public Buffer(int marcos_pagina) 
	{
		this.marcos_pagina=marcos_pagina;

		creacionPaginas();
		
		this.contentPage=new ArrayList<>();
		
		this.num_fallos=0;
		
				
		TP= new ArrayList<>();
		
			
	}
	
	
	
	

	
	
	
	
	
	
	
/// paginas_divisiones DETERMINA EL TAMANO DE UNA PAGINA

	public void  creacionPaginas()
	{	
		for(int i= 0; i < marcos_pagina; i++){
	        	Integer pagina= -1;
	        	Integer edad= 0;	        	
	        	ArrayList<Integer> marco= new ArrayList<>();
	        	marco.add(pagina);
	        	marco.add(edad);
        	
	        	TP.add(marco);

	        }

	}
	
	
	
	
	/// guardo en una lista las casillas de mi matriz segun el recorrido, para despues saber en que pagina estan referenciadas
	
	
	public synchronized  void adicionar_pagina(int valor) throws  InterruptedException {
		
		
		while( comprobacion()== false ) {
			
			try {
                wait();// Makes the incoming message wait (passive) until someone wakes it up
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
			
		}
		
		insert_page(valor);
		
		
		synchronized (this){
            notify();
        }
	
	}


	public synchronized void envejecer() throws InterruptedException{

		while(comprobacion()==false ){
            try {
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
      makeolder();

        synchronized (this){
            notify();
        }
        //notify();//As we don't know if the previous thread is passive or active we have to notify just in case it was passive and its in waiting mode



	}


	public void makeolder() {
		
		for(int j= 0; j< TP.size(); j++){
			
			if (TP.get(j).get(0) != -1) {
				
				
			   int numero= TP.get(j).get(1);
			   numero+=1;
				TP.get(j).set(1, numero);
			}
			
		}
	   }


		
		
	
	
	
	
	
		
		
public	void insert_page(int valor) {
	
	boolean centinela	=true;	
	
	int num=0;

	if (contentPage.size()<marcos_pagina  || contentPage.contains(valor)== true) {
		
	
	if(comprobacion()) {
		
		
	
	while ( centinela == true && ( num < TP.size())) {
		
		if(TP.get(num).get(0)== -1) 
		{
			TP.get(num).set(0, valor);
			centinela=false;
			 contentPage.add(valor);
		}
		
		if(TP.get(num).get(0)== valor) {
			
			TP.get(num).set(1, 0);
			centinela=false;
			
		}

		num+=1;

	}

	num=0;
	centinela=true;
	}
	}
	else {
		
		fallo_pagina(valor);
	
	}

	}



public void fallo_pagina( int llave) {
	
	
	int numero= 0;
	int indicado=0;
	
	for(int j= 0; j< TP.size(); j++){
		

		 if (TP.get(j).get(1)> numero ) {
			 
			 numero= TP.get(j).get(1);
			 indicado= TP.get(j).get(0);
		 }
	 
		 }
	
	for(int tt= 0; tt< contentPage.size(); tt++){
		
		if (contentPage.get(tt)==indicado) {
			
			contentPage.remove(tt);
		}
		
		
	}
	
	
	
	
	for(int jj= 0; jj< TP.size(); jj++){
		
		 if (TP.get(jj).get(0) == indicado  ) {
			 
			 TP.get(jj).set(0, llave);
			 
			 TP.get(jj).set(1, 0);
			 contentPage.add(llave);		 
		 }
	 
	}
		
	 num_fallos+=1;
			
	
}


	

	
	
	

	
	
	

		
		
		
		
		
	
	
	public boolean  comprobacion() {
	
		boolean rta = false;
		
		
		 for(int j= 0; j< TP.size(); j++){
			 
				 
			 if (TP.get(j).get(0) == -1 || (TP.get(j).get(0) != -1)) {
				 				 
				 rta= true;
						 
				 
			 }
		 }
		 
		 return rta;	
	}
	
	
	
	
	
  
	
    public int fallosdepagina()	
{
	return num_fallos;
}
	
	
	
	
	

	
	

	

public void tablaa() {
	
	System.out.println(TP);
}












	

	
	













<<<<<<< Updated upstream
    //TODO: REMPLAZAR ESO POR LOS NUEVOS
    /**
     * Checks if it is possible to add a new page to the page frame table without generating a memory failure (fallo de pagina)
     * comprobación() in original buffer
     * @return boolean. True if it is possible to add a new page, false the contrary.
     */
    public boolean canAddNewPage(){

        for(int i =0;i < pageFrameTable.size(); i++){
            //TODO: V -> No entiendo tanto la logica de este if , no seria siempre true?
            if(getPageInFrameTable(i) == -1|| getPageInFrameTable(i) != -1){
                return true;
            }
        }
        return false;
    }

    public boolean checkPut(){
        return true;
    }

    public boolean checkOlder(){
        return true;
    }


    public void insertPage(int pageNumber){
        boolean centinel = true;
        int num = 0;

        if(contentTable.size()< pageFrames || contentTable.contains(pageNumber) == true){
            if(canAddNewPage()){
                while(centinel == true && num < pageFrameTable.size()){
                    //finds empty space where it can add the page
                    if(getPageInFrameTable(num) == -1){
                        setPageInFrameTable(num,pageNumber);
                        centinel = false;
                        contentTable.add(pageNumber);
                    }

                    //finds an older reference to the current pageNumber, so it updates the time since last reference to 0
                    if(getPageInFrameTable(num) == pageNumber){
                        setPageLastTimeSinceReferenceInFrameTable(num,0);
                        centinel = false;
                    }

                    num += 1;
                }

                //TODO: V -> Tampoco entiendo la nesecidad de esto
                num = 0;
                centinel = true;
            }
            else {
                pageError(pageNumber);
            }
=======

>>>>>>> Stashed changes






}
