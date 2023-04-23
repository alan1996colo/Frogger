package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Vehiculo {
	private double alto;
	private double ancho;
	private double x;
	private double y;
    private Image autoDer;
    private Image autoIzq;
    
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
	
	public void desplazarIzqVelocidad(double velocidad) { //aumenta velocidad de los autos que van hacia la izquierda
		this.x-=velocidad;
	}
	
	public void desplazarDerVelocidad(double velocidad) { //aumenta la velocidad de los autos que van hacia la derecha
		this.x+=velocidad;
	}
	
	public Vehiculo(double x, double y) {
		this.y = y;
		this.x = x;
		this.alto = 50;
		this.ancho = 100;
			
		double variableRandom=Math.random();
		//auto al azar mano izquierda
		if(variableRandom<0.3){//carga un coche al azar entre marron, azul, etc
			this.autoIzq= Herramientas.cargarImagen("autoamarilloIzq.gif");
		}
		else if(variableRandom>0.7){
			this.autoIzq=Herramientas.cargarImagen("autocelesteIzq.gif");
		}
		else{
			this.autoIzq=Herramientas.cargarImagen("autoverdeIzq.gif");
		}
		//auto al azar mano derecha
		if(variableRandom<0.3) {
			this.autoDer=Herramientas.cargarImagen("autonaranjaDer.gif");
		}
		else if(variableRandom>0.7){
			this.autoDer=Herramientas.cargarImagen("autocelesteDer.gif");
		}
		else{
			this.autoDer=Herramientas.cargarImagen("autorosaDer.gif");
		}	
		
	}
	//autos que van a la derecha 50x100 pixel
	public void dibujarAutosDerecha(Entorno entorno) {
			entorno.dibujarImagen(autoDer, x, y, 0,1);
	}
//autos que van a la izquierda 50x100 pixel
	public void dibujarAutosIzquierda(Entorno entorno) {
		
		entorno.dibujarImagen(autoIzq, x, y, 0,1);
	}

	public void colicionConBordesDerecho(Entorno entorno,Vehiculo[] a) {
		double min= 0;
		for(int c=0;c<a.length;c++) {
			if(a[c]==null&&c==a.length-1) {
				 break;
			}//se fija que no sea el ultimo en el arreglo para poder pasarle el valor del siguiente lugar
			if(a[c]==null) {
				c=c+1;
			}
			if(a[c].x<min){
				min=a[c].x;
			}
		}
			if (x > entorno.ancho()+50) { // borde derecho
				if(min<50) {
					x=min-150;
				}
				else{
					x=0;
				}
			}	
	}	
	
	public void colicionConBordesIzquierdo(Entorno entorno,Vehiculo[] a) {
		//que devuelva un Boolean y armar otro metodo para que lo reescriba
		double max=entorno.ancho();
		for(int c=0;c<a.length;c++) {
			if(a[c]==null&&c==a.length-1) {
				 break;
			}
			if(a[c]==null) {
				 c=c+1;
			}
			
			if(a[c].x>max){
				max=a[c].x;
			}
			}

		if (x < -50) { 
			if (max>entorno.ancho() -50){// borde derecho
			x =max+150;
		}
			else {
				x=entorno.ancho();
			}
		}	
	}
	
	
	public void colicionConBordeInferior(Entorno entorno,Vehiculo[] a) {

		if (y >= entorno.alto()+25) { //borde inferior
			y =25;
		}
	}

	public void bajar(int a) {// gravedad autos
		this.y+= a;	
	}
}
