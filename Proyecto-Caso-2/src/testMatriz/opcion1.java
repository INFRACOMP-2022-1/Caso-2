package testMatriz;

import java.util.ArrayList;
import java.util. concurrent. ThreadLocalRandom;


public class opcion1



{
	
	
	private  int INT_MAX=10;
	private  int numeroFilas;
	
	private int numeroColumnas;
	

	

	private int pagina_tamano;


	private int[][] mat1 ;
	
	private int[][] mat2 ;
	
	private int[][] mat3 ;
	
	private int paginas_divisiones;
	
	private int num_paginas;
	
	private int num_referencias;





	

	
	



	
	
	public opcion1(int numeroFilas , int numeroColumnas,int tipoRecorrido,int pagina_tamano,int entero) 
	{
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


		
		
		
		
		
		// se imprimer cada uno de los valores para rectificar los//
		System.out.print(paginas_divisiones);
		System.out.println();
		System.out.print(num_paginas);
		System.out.println();

		System.out.print(num_referencias);

		System.out.println();
		System.out.println();


				
		//primer djito lo obtengo de hacer los calculos, el segundo lo obtengo dividiendo/
		//estamos definiedo la memoria para sacar las referencias y guardar las matrices/
		//TODO: METER LAS PAGINAS/


		int[][] feo=new int[num_paginas][paginas_divisiones];
		

		
				
		
		
		
		
for(int i= 0; i < lista.size(); i++){
			
			
			lista.get(i);
			
			
			
			
			
		}ArrayList<Integer> lista
		
		

		
		
		
		
		
		
		


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
		
		
		
		
	if (tipoRecorrido==1) {
			
			recorrido1();
		}
		else if  (tipoRecorrido==2){
			
			recorrido2();

		}















		
		
		//se selecciona el tipo de recorrido /
		
		//SE IMPRIMEN LOS VALORES PERO SE NECESEITA CORREGIR BIEN  /


		if (tipoRecorrido==1) {
			
			recorrido1();
		}
		else if  (tipoRecorrido==2){
			
			recorrido2();

		}
		

		
		
		
	
		
		

		
		 
		
			
	}


	public void creacionPagina()
	{

	}
	
	
	
	public void recorrido1() {

        for(int i= 0; i < numeroFilas; i++){
    
            for(int j= 0; j < numeroColumnas; j++){
            	
        		//recorridos por aparte -- lo que deberia salir segun HAROLD, porqie con la suma de matrices me salen paginas que nisiquiera estan creadas /

        		System.out.println( mat1[i][j]);
        		System.out.println( mat2[i][j]);
        		System.out.println( mat3[i][j]);
        		System.out.println();
        		//asi aparce en el taller//
        		System.out.println( mat3[i][j]=mat1[i][j] +mat2[i][j]);
        		System.out.println();

            }
        }
    }
    
    
    public void recorrido2() {
    
        for(int j= 0; j< numeroFilas; j++){
    
            for(int i= 0; i < numeroFilas; i++){
            	
            	//recorridos por aparte -- lo que deberia salir segun HAROLD/

        		System.out.println( mat1[i][j]);
        		System.out.println( mat2[i][j]);
        		System.out.println( mat3[i][j]);
        		System.out.println();
        		//asi aparce en el taller//
        		System.out.println( mat3[i][j]=mat1[i][j] +mat2[i][j]);
        		System.out.println();
            }
        }
    }

















	

	
	
	
	
public  int[][] crearMatriz() {


	int[][] matriz = new int[numeroFilas][numeroColumnas];
	

	for (int i = 0; i < numeroFilas; i++) {

		for(int j= 0; j< numeroColumnas; j++) {
			matriz[i][j]=ThreadLocalRandom.current().nextInt(0,INT_MAX);
			}
		}

		return matriz;
		
	}











	public static void main(String[] args){
	
		opcion1 jeje=new opcion1(4,4,1,8,2);
		
		
		
	
		
		
		
	
		
		
	}
	









}
