package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Conejo {
	
	 private double alto ;
	 private double ancho;
	 private double x;
	 private double y;
	 private Image conejito;
	 private boolean estaVivo;
	 
	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	public boolean getEstaVivo() {
		return estaVivo;
	}

	public Conejo(double x, double y, double alto,double ancho ) {
		 this.x = x;
		 this.y = y;
		 this.alto = alto;
		 this.ancho = ancho;
		 this.conejito=Herramientas.cargarImagen("conejete.gif");
		 this.estaVivo = true;
	 }
	
	 public void dibujar(Entorno entorno) {
			entorno.dibujarImagen(conejito, x, y, 0,1);
			
	}
	  public void bajar(int a) { //Gravedad del conejo
		   this.y+= a;	  
	}
	  public void saltarArriba(){
		  this.y=y-this.alto;
	}
	  public void saltarIzquierda(){
		  this.x=x-this.ancho;
	}
	  public void saltarDerecha(){
		  this.x=x+this.ancho;
	 }
	  
	  public void muere() { // modifica el estado del conejo si muere
		  estaVivo = false;
	}
	  public void revivir(double x, double y) { //revive al conejo
		  estaVivo = true;
		  this.x=x;
		  this.y=y;  
	  }
	  
	 public Kamehameha lanzar(){ // Crea el kameha
		Kamehameha bolita= new Kamehameha (this.x, this.y);
		return bolita;
	 }
	 
	 public boolean colicionInf() {//colicion del conejo con el borde de abajo
		  if(this.y>600) 
		  {
			  return true;
		  }
		return false;
	 }
		    
	  public boolean colicionConVehiculo(Vehiculo[] a) {
		  for(int c=0;c<a.length;c++) {
		      if(a[c]==null) {
		    	  return false;
		      }
		      if(this.x+this.ancho/2-1>a[c].getX()-a[c].getAncho()/2
		         &&this.x-this.ancho/2+1<a[c].getX()+a[c].getAncho()/2
		      
		         && this.y + this.alto/2 -2> a[c].getY() - a[c].getAlto()/2 
		         &&this.y - this.alto/2 +2< a[c].getY() + a[c].getAlto()/2) {
		        	return true;
		        }
		  }
		     return false;
		}
	  
	  
	  
	  public void colicionConBordes(Entorno entorno) {
		  
		  if(x > entorno.ancho()-25) { //borde derecho
			  x -= 25;
		  }
		  if(x < 25) { // borde izquierdo
			  x +=25;
		  }
		  if(y<25) { // borde de arriba
			  y =25;
		  }
	 }	  
}
