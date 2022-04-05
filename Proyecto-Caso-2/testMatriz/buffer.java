package testMatriz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util. concurrent. ThreadLocalRandom;



public class buffer



{
	
	
	private  int marcos_pagina;

	
	private HashMap <Integer, ArrayList<Integer>> map ;

	
	private ArrayList<ArrayList<Integer>> lista_mayor;
	
	ArrayList<ArrayList<Integer>> TP;
	
	
	private int num_fallos;
	

	private int  cambio;
	
	private int  cambios;
	
	
	private ArrayList<Integer> lili;

	




	

	
	



	
	
	public buffer(int marcos_pagina) 
	{
		this.marcos_pagina=marcos_pagina;

		creacionPaginas();
		
		this.lili=new ArrayList<>();
		
		this.num_fallos=0;
		
		this.cambio=0;
		this.cambios=0;
		
		
		TP= new ArrayList<>();
		
			
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
	
	Boolean mia	=true;	
	
	int num=0;
	
	
	 cambio=0;
	 
	if (lili.size()<5  || lili.contains(valor)== true) {
		
	
	if(comprobacion()) {
		
		
	
	while ( mia == true && ( num < TP.size())) {
		
		if(TP.get(num).get(0)== -1) 
		{
			TP.get(num).set(0, valor);
			mia=false;
			 cambios+=1;
			 lili.add(valor);
		}
		
		if(TP.get(num).get(0)== valor) {
			
			TP.get(num).set(1, 0);
			mia=false;
			
		}

		num+=1;

	}
	
	
	num=0;
	mia=true;
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
	
	for(int jj= 0; jj< TP.size(); jj++){
		

		 if (TP.get(jj).get(0) == indicado  ) {
			 
			 TP.get(jj).set(0, llave);
			 
			 TP.get(jj).set(1, 0);
			 
			 
			 
			 
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
	
	
	
	
	
  
	
    
	
	
	
	

	
	

	

public void tablaa() {
	
	System.out.println(TP);
}












	

	
	




















}
