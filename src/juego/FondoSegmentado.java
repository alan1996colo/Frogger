package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class FondoSegmentado { // clase para el fondo de pantalla
	private double x;
	private double y;
    private Image segmento1;
    //private Image segmento2;

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public FondoSegmentado(double x, double y) {
		this.y = y;
		this.x = x;
		this.segmento1=Herramientas.cargarImagen("segmento1.gif");
	}

	public void dibujarabc(Entorno entorno) {
		entorno.dibujarImagen(segmento1, x, y, 0,1);
	}

	/*public void dibujar2(Entorno entorno) {
		
		entorno.dibujarImagen(segmento2, x, y, 0,1);
	}*/
	
	public void bajar(int a ) {
		   this.y+= a;	
	}
	public void redibujar() {
		if(this.y>=750) 
		{
			this.y=-150;
		}
	}
}
