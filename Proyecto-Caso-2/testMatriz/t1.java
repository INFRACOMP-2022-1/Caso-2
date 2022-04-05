package main;

import java.util.ArrayList;


public class t1  extends Thread{
	
   private buffer buffer;
    
   private ArrayList<Integer> wordListSend;

	
	
	
   public t1(buffer buffer, ArrayList<Integer> wordListSend){
		
	   this.buffer=buffer;
	   this.wordListSend=wordListSend;
  
	   }
   
   
   public void emmitMessage(int table) throws InterruptedException {
	   
    	   buffer.adicionar_pagina(table);
    	   
    	   Thread.sleep(2);
   }
   
   
   
   public void run(){
	   
	    for(int j= 0; j< wordListSend.size(); j++){
	    	
	    	

				try {
					emmitMessage(wordListSend.get(j));
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		   
	   }
	   
	   
	   
   }
    
   
   
   
   
   
   
   
   
   
	
	
	

}
