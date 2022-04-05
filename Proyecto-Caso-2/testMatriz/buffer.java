package Proyect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util. concurrent. ThreadLocalRandom;



public class buffer



{
	
	
	private  int marcos_pagina;

	
	private HashMap <Integer, ArrayList<Integer>> map ;

	
	private ArrayList<ArrayList<Integer>> lista_mayor;
	
	ArrayList<ArrayList<Integer>> TP;
	




	

	
	



	
	
	public buffer(int marcos_pagina) 
	{
		this.marcos_pagina=marcos_pagina;

		creacionPaginas();
		
			
	}
	
	
	
	

	
	
	
	
	
	
	
/// paginas_divisiones DETERMINA EL TAMANO DE UNA PAGINA

	public void  creacionPaginas()
	{	
		for(int i= 0; i < marcos_pagina; i++){

	        	Integer l1= -1;
	        	Integer l2= 0;	        	
	        	ArrayList<Integer> l3= new ArrayList<>();
 	
	        	l3.add(l1);
	        	l3.add(l2);

	        	
	        	TP.add(l3);

	        }
	        

	        
	        

		


	}
	
	
	
	
	/// guardo en una lista las casillas de mi matriz segun el recorrido, para despues saber en que pagina estan referenciadas
	
	
	public synchronized void adicionar_pagina(int valor) throws  InterruptedException {
		
		
		while( comprobacion()) {
			
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
		
		
	
	
	
	
	
		
		
public	void insert_page(int valor) {
	
	
	Boolean mia	=true;	
	
	int num=0;
	
	
	while ( mia == true && ( num < TP.size())) {
		
		if(TP.get(num).get(0)== -1) 
		{
			TP.get(num).set(0, valor);
			mia=false;
	        num+=1;
		}
		num+=1;

	}

	 num=0;
	 mia=true;
	 
	}
	
	
	

	
	
	

		
		
		
		
		
	
	
	public boolean  comprobacion() {
	
		boolean rta = false;
		
		
		 for(int j= 0; j< TP.size(); j++){
			 
				 
			 if (TP.get(j).get(0) == -1) {
				 
				 
				 rta= true;			 
			 }
	 }
	 
	 return rta;	
}
	
	
	
	
	
  
	
    
	
	
	
	
	public void envejecer() {
		
		 for(int j= 0; j< TP.size(); j++){
			 
			 if (TP.get(j).get(0) != -1) {
				 
				 
				int numero= TP.get(j).get(1);
				numero+=1;
				 TP.get(j).set(1, numero);
			 }
			 
		 }
		}
	
	
	
	
	
	
	
	





public void tablaa() {
	
	System.out.println(TP);
}












	

	
	




















}
