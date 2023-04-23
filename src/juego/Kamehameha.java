package juego;

import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;

public class Kamehameha {
  
  private double x;
  private double y;
  private double tamano;
  private Image bolaDeFuego;
  
  
  public Kamehameha(double x, double y) {
    this.x = x;
    this.y = y;
    this.bolaDeFuego=Herramientas.cargarImagen("bolaDeFuego.gif");
  }

  public int colicionConCoche(Vehiculo[] a) {
	   for(int c=0;c<a.length;c++) {
		   if(a[c].getX() - a[c].getAncho()/2 < this.x 
			&& this.x < a[c].getX() + a[c].getAncho()/2 
			&& this.y + this.tamano/2 > a[c].getY() - a[c].getAlto()/2 
			&&this.y - this.tamano/2 < a[c].getY() + a[c].getAlto()/2) {
			   return c;
		   }	  
	  }
	   return -1;
  }
  
  public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(bolaDeFuego, x, y, 0,1);	
  }
 
  public void desplazarArriba() {
	  this.y=this.y-3;
	  
  }
}