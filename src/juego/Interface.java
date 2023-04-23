package juego;


import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Interface {
	private int kamehamehaImpactado;
	private int puntos; //variable de la cant de puntos
	private int cantSaltos;
	private int tiempo;
	private Image puntajes;// interface de puntaje
	private Image cargado;
	private Image win; // imagen de gano
	private Image gameOver;
	private Image tablaPuntajeFinal;
	private boolean estaTablaDePuntaje;
	private int energiaDeKamehameha;
	private boolean seTerminoElJuego;
	private boolean BotonOnOff; // boton que sirve para verificar si el juego termina
	
	public int getTiempo() {
		return tiempo;
	}
	
	public boolean getEstaTablaDePuntaje() {
		return estaTablaDePuntaje;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public boolean getSeTerminoElJuego() {
		  return seTerminoElJuego;
	}
	
	public boolean getBotonOnOff(){ // boton que sirve para verificar si el juego termina
		return this.BotonOnOff;
	}
	
	public void Prender() { // boton que sirve para verificar si el juego termina
		this.BotonOnOff = true;
	}
	public void Apagar() { // boton que sirve para verificar si el juego termina
		this.BotonOnOff = false;
	}	
	public void SeTerminoElJuegoOn() { // boton que sirve para verificar si el juego termina
		 this.seTerminoElJuego = true;
	}
	public void SeTerminoElJuegoOff() { // boton que sirve para verificar si el juego termina
		this.seTerminoElJuego = false;
	}
	
	
	public Interface(int energiaDeKamehameha) {

		this.kamehamehaImpactado = 0;
		this.cantSaltos = 0;
		this.tiempo = 0;
		this.puntos = 0;
		this.puntajes=Herramientas.cargarImagen("puntajes.png");
		this.cargado=Herramientas.cargarImagen("cargado.gif");
		this.energiaDeKamehameha=energiaDeKamehameha;
		this.win = Herramientas.cargarImagen("win.png");
		this.gameOver = Herramientas.cargarImagen("gameOver.png");
		this.tablaPuntajeFinal = Herramientas.cargarImagen("tablaPuntos.png");
		this.estaTablaDePuntaje = false;
	}

	public void mostrarEnPantalla(Entorno entorno) {
		entorno.dibujarImagen(puntajes, 400, 20, 0,1);
		entorno.cambiarFont("Impact", 18, Color.BLACK); //ver Ravie,Consolas,Impact
		entorno.escribirTexto(""+kamehamehaImpactado, entorno.ancho()-270, entorno.alto()-565);
		entorno.escribirTexto(""+cantSaltos, entorno.ancho()-490, entorno.alto()-565);
		entorno.escribirTexto(""+tiempo/50, entorno.ancho()-685, entorno.alto()-565);
		entorno.escribirTexto(""+puntos, entorno.ancho()-80, entorno.alto()-565);	
	}
	
	
	public int kamehameListo() { //permite consultar cuanta energia tiene cargada el kamehame
		return energiaDeKamehameha;
	}
	public void cargado(Entorno entorno) {// dibuja el icono de kamehame listo para tirar
		entorno.dibujarImagen(cargado, 432, 20, 0,1);
	}
	public void confirmarImpacto() {//confirma colision de kameha con auto
		kamehamehaImpactado+=1;
	}
	public void agotarEnergia() {//una vez lanzado el kameja, resetea y comienza a cargar de nuevo
		energiaDeKamehameha=0;
	}
	public void aumentarSaltos() {// aumenta contador de saltos
		cantSaltos+=1;
	}
	public void aumentarEnergia() { // aumenta contador de energia pal kameha
		energiaDeKamehameha+=1;
	}
	public void aumentarTiempo(int tiem) { // aumenta contador tiempo en el juego
		tiempo+=tiem;
	}
	public void aumentarPuntos() { // suma puntos
		puntos=tiempo/50+cantSaltos+kamehamehaImpactado*5;
	}
	public void resetearPuntajes() { // resetear stats
		cantSaltos=0;
		kamehamehaImpactado=0;
		tiempo=0;
		puntos=0;
	}
	
	//------------------------si se Gana -------------------
	public void dibujarGano(Entorno entorno) { //dibuja el cartel de You Win
		entorno.dibujarImagen(win, entorno.ancho()/2, entorno.alto()/2,0,1);
		entorno.cambiarFont("Impact", 30, Color.BLACK);
		entorno.escribirTexto("''Presione ENTER para continuar''",entorno.ancho()/2-200, entorno.alto()/2+102);
		entorno.cambiarFont("Impact", 30, Color.RED);
		entorno.escribirTexto("''Presione ENTER para continuar''",entorno.ancho()/2-202, entorno.alto()/2+100);
	}
	
	//------------------------dibujar Menu del game over -------------------
	public void gameOver(Entorno entorno) { //dibuja el cartel de game over
		entorno.dibujarImagen(gameOver, entorno.ancho()/2, entorno.alto()/2,0);
		entorno.cambiarFont("Impact", 30, Color.BLACK);
		entorno.escribirTexto("''Presione ENTER''",entorno.ancho()/2+50, entorno.alto()/2+280);
		entorno.cambiarFont("Impact", 30, Color.RED);
		entorno.escribirTexto("''Presione ENTER''",entorno.ancho()/2+52, entorno.alto()/2+280);
	}
	
	//------------------------dibujar Menu de la tabla final -------------------
	public void mostrarTablaDePuntaje() { // confirma la llamada a la tabla depuntajes
			estaTablaDePuntaje=true;
	}
	public void ocultarTablaDePuntaje() {
		estaTablaDePuntaje=false;
	}
	public void dibujarTablaDePuntaje(Entorno entorno) {
		entorno.dibujarImagen(tablaPuntajeFinal, entorno.ancho()/2, entorno.alto()/2,0);
		entorno.cambiarFont("Impact", 18, Color.WHITE);
		entorno.escribirTexto(""+tiempo/50, entorno.ancho()/2-300, entorno.alto()/2-140);
		entorno.escribirTexto(""+cantSaltos, entorno.ancho()/2-10, entorno.alto()/2-90);
		entorno.escribirTexto(""+kamehamehaImpactado, entorno.ancho()/2+300, entorno.alto()/2-140);
		entorno.cambiarFont("Impact", 30, Color.WHITE);
		entorno.escribirTexto(""+puntos, entorno.ancho()/2-30, entorno.alto()/2-192);
		entorno.cambiarFont("Impact", 18, Color.BLACK);
		entorno.escribirTexto("''Presione ENTER para volver a jugar''",entorno.ancho()/2-128, entorno.alto()-30);
		entorno.cambiarFont("Impact", 18, Color.WHITE);
		entorno.escribirTexto("''Presione ENTER para volver a jugar''",entorno.ancho()/2-130, entorno.alto()-30);
	}
}
