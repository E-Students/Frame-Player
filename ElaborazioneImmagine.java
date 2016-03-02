package elaborazioneimmagine;

import java.lang.Runtime;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ElaborazioneImmagine
{
  
  public static void main(String[] args) 
  {
    char[] letti;
    letti = new char[1024];
    char chiave;
    int nLetti,nScritti;
    int i,fLetti;
    int b;
    
    try
    {
      Process p = Runtime.getRuntime().exec("wget 172.16.3.3/~compito/tigre.bmp");
	
      FileInputStream file = new FileInputStream("/tmp/tigrecifr.bmp");
      FileOutputStream f = new FileOutputStream("/tmp/tigredecifr.bmp");

      if(file==null)
      {
	System.err.println("ERRORE");
      }
      else
      {
	fLetti=0;
	chiave=1; 
	while ((nLetti= file.read()) != -1) 
	{  
	  if(fLetti>=2190)  //2190 e' l'intestazione delle immagini con estensione .bmp
	    nLetti-=chiave%256; //applicazione dell'algoritmo 
	  chiave++; //incremento chiave
	  fLetti++; 
	  if(chiave==0)
	    chiave=1;
	  f.write(nLetti); //scrittura byte nel file 
	}
	file.close();
	f.close();
      }
    }
    catch(IOException e)
    {
	System.err.println("Errore eccezione");
    }
  }
}
