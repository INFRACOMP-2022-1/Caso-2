package main;

import java.util. concurrent. ThreadLocalRandom;


public class opcion1



{
	
	
	private  int INT_MAX=10;
	private  int numeroFilas;
	
	private int numeroColumnas;
	
	private int[][] matriz ;
	
	private int tipoRecorrido ;
	

	
	



	
	
	public opcion1(int numeroFilas , int numeroColumnas) 
	{
		this.numeroFilas=numeroFilas;
		this.numeroColumnas=numeroColumnas;
		
		 matriz = new int[numeroFilas][numeroColumnas];
		
		
		 for (int i = 0; i < 3; i++) {
		 crearMatriz();
		 }
		


		
			
	}

	

	
	
	
	
public  void crearMatriz() {
	

	for (int i = 0; i < numeroFilas; i++) {

		for(int j= 0; j< numeroColumnas; j++) {
			matriz[i][j]=ThreadLocalRandom.current().nextInt(0,INT_MAX);
			}
		}
	
	System.out.println("Matriz:");

		imprimirMatriz();
		
	}





private  void imprimirMatriz() {
	for (int i = 0; i < numeroFilas; i++) {
		for (int j = 0; j < numeroColumnas; j++) {
			System.out.print(matriz[i][j]+ "\t");
		}
		System.out.println();

		}

	}


public static void main(String[] args){
	
	opcion1 jeje=new opcion1(6,5);
	
	
	

	
	
	

	
	
}







}
