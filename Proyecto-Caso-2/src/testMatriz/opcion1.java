package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util. concurrent. ThreadLocalRandom;


public class opcion1



{
	
	
	private  int INT_MAX;
	private  int numeroFilas;
	
	private int numeroColumnas;
	
	

	

	private int pagina_tamano;


	private int[][] mat1 ;
	
	private int[][] mat2 ;
	
	private int[][] mat3 ;
	
	
	private int paginas_divisiones;
	
	private int num_paginas;
	
	private int num_referencias;
	
	private HashMap <Integer, ArrayList<Integer>> map ;

	
	private ArrayList<ArrayList<Integer>> lista_mayor;
	




	

	
	



	
	
	public opcion1(int numeroFilas , int numeroColumnas,int tipoRecorrido,int pagina_tamano,int entero) 
	{
		INT_MAX=1;
		this.pagina_tamano=pagina_tamano;
		this.numeroFilas=numeroFilas;
		this.numeroColumnas=numeroColumnas;
		this.mat1=crearMatriz();
		this.mat2=crearMatriz();
		this.mat3=crearMatriz();
		
		
		
		//numero de referencias o divisiones que tiene una pagina /

		paginas_divisiones=pagina_tamano/entero;

	   //paginas totales que voy a tener /

		
		num_paginas=((numeroColumnas*numeroFilas)/paginas_divisiones) *3;
		
		
		//referencias o divisones totales que voy a tener /
		
		
		num_referencias=num_paginas*paginas_divisiones;
		
		



		// USUARIO SELECCIONA LAS PAGINAS /

		
		if (tipoRecorrido==1) {
			
			recorrido1();
		}
		else if  (tipoRecorrido==2){
			
			recorrido2();

		}

	
		
		
		
		
		// SE CREAN LAS PAGINAS sin embargo queda creado el mapa de 12 paginas pero esta quedando vacio :(/

	
		creacionPaginas();
	

	
	
	

		
		
	}
	
	
	
	
	
/// TODO	
/// IBA A EMPEZAR A HACER LA REFERNCIACION PARA TENER LA LISTA DE PAGINAS REFERENCIADAS
/// ESTO ME TIENE QUE DAR COMO RESULTADO UNA LISTA, LISTA QUE ME VA A SERVIR PARA LA OPION 2 
/// TENGO QUE USAR LA LISTA QUE ME RETORNA EL RECORRIDO, Y ACA SE USA PARA SACAR REFERENCIAS
	public void referencias( )
	{	
		for (Integer clave:map.keySet()) {
		    ArrayList<Integer> valor = map.get(clave);
		    
		    System.out.println("Clave: " + clave + ", valor: " + valor.size());
		}
	
	}
	
	
	
	
	
	
	
	
	
	
/// TODO ARREGLAR PORQUE EL HASHMAP ESTA QUEDANDO VACIO NOSE PORQUE ,
//	APESAR DE QUE SE CREAN LAS PAGINAS CORRECTAS/ AL MIRAR Y REVISAR EL MAPA QUEDA SIN VALORES
// UN MAPA QUE HACE ALUCION A LA PAGINA CON SUS RESPECTIVAS CASILLAS
// EJ:  1:[1,2,3,4]------ 1 ES LA PAGINA
	
/// paginas_divisiones DETERMINA EL TAMANO DE UNA PAGINA

	public void  creacionPaginas()
	{	
		
		HashMap <Integer, ArrayList<Integer>> map=new HashMap<>();

		ArrayList<Integer> lista= new ArrayList<Integer>();
		
		int numero=1;
	
		for(int i= 0; i <mat1.length; i++){
		    
            for(int j= 0; j < mat1[0].length; j++){
            	
            	 
            	lista.add(mat1[i][j] );
            	
            	/// SI La lista(pagina) que estoy haciendo es igual a paginas_divisiones se cierra la pagina, es decir se pone en mi memoria

            	if (lista.size()  == paginas_divisiones) {
            		
                	/// arreglar porque si se hace     System.out.println(map.values()); aca va a votar datos repetidos y eso no puede pasar 

            		
            		map.put(numero,lista);
            		lista.clear(); 
            		numero+=1;

                	      	
        	}
       }    
            
		}
		
		lista.clear();       	

		
		for(int i= 0; i <mat1.length; i++){
		    
            for(int j= 0; j < mat1[0].length; j++){
            	
            	lista.add(mat2[i][j] );
            	
            	if (lista.size()  == 4) {
            		
            		
            		map.put(numero,lista);
            		numero+=1;

                	lista.clear();       	
        	}
            	
            }
            
		}
		
		lista.clear();       	

		
		for(int i= 0; i <mat1.length; i++){
		    
            for(int j= 0; j < mat1[0].length; j++){
            	
            	lista.add(mat3[i][j] );
            	
            	if (lista.size()  == 4) {
            		
            		
            		map.put(numero,lista);
            		numero+=1;

                	lista.clear();       	
        	}
            	
            }
            
		}	
		
      	 System.out.println(map.values());
	
	}
	
	
	
	
	/// guardo en una lista las casillas de mi matriz segun el recorrido, para despues saber en que pagina estan referenciadas
	
	
	public ArrayList<Integer> recorrido1() {
		 ArrayList<Integer> lista_mayor= new ArrayList<>();


        for(int i= 0; i < numeroFilas; i++){
    
            for(int j= 0; j < numeroColumnas; j++){
            	    
        	 mat3[i][j]=mat1[i][j] +mat2[i][j];
        	 
        	 lista_mayor.add(mat1[i][j] );
        	 lista_mayor.add(mat2[i][j] );
        	 lista_mayor.add(mat3[i][j] );

        	

            }
        }
		return lista_mayor;
    }
  
	
	/// guardo en una lista las casillas de mi matriz segun el recorrido, para despues saber en que pagina estan referenciadas
    
    public ArrayList<Integer> recorrido2() {
    	
		 ArrayList<Integer> lista_mayor= new ArrayList<>();
    
        for(int j= 0; j< numeroColumnas; j++){
    
            for(int i= 0; i < numeroFilas; i++){

        		 mat3[i][j]=mat1[i][j] +mat2[i][j];
        		 
        		 lista_mayor.add(mat1[i][j]);
            	 lista_mayor.add(mat2[i][j] );
            	 lista_mayor.add(mat3[i][j] );
        	
            }
        }
        
        return lista_mayor;
    }

















	

	
	/// se crea la matriz con valores del 1 al 48 o bueno segun el numero de matrices 	
	
public  int[][] crearMatriz() {


	int[][] matriz = new int[numeroFilas][numeroColumnas];
	

	for (int i = 0; i < numeroFilas; i++) {

		for(int j= 0; j< numeroColumnas; j++) {
			
			matriz[i][j]=INT_MAX;
			
			INT_MAX+=1;
			
			
			}
		}

		return matriz;
		
	}











	public static void main(String[] args){
	
		opcion1 jeje=new opcion1(4,4,1,8,2);	
	}
	









}
