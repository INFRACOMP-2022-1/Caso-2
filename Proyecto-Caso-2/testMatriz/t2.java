package main;

import java.util.ArrayList;

public class t2 extends Thread {
	
	
	
	private buffer buffer;

	public t2 (buffer buffer)
	{
	 this.buffer=buffer;

	}
	
	public void envejecer() throws InterruptedException {
		
		buffer.envejecer();
 	   Thread.sleep(1);

		
		
	}
	
	
	 public void run(){	
		 
		 
		 try {
			envejecer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
	 }
	

}
