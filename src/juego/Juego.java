package juego;


import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Conejo conejo;
	private Interface i; //interface del juego
	private Kamehameha kamehameha;
	private Vehiculo [] transitoDerecho;
	private Vehiculo [] transitoIzquierdo;
	private int cantVehiculosPorCalle =7;//dificultad definir entre 5 y 10
	private FondoSegmentado calle;
	private FondoSegmentado calle2;
	private FondoSegmentado calle3;
	int pause =0;

	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber", 800, 600);
		
		
		// Inicializar lo que haga falta para el juego
		calle=new FondoSegmentado(400,250);
		calle2=new FondoSegmentado(400,550);
		calle3=new FondoSegmentado(400,-50);
		conejo = new Conejo (entorno.ancho()/2,entorno.alto()-225,50,50);
		i= new Interface (1);

	//-----------------------------------------------------------	
	//-----*Creacion de vehiculos*-----------------------------
		
		//Autos q van para la derecha --------------->>
		transitoDerecho = new Vehiculo[cantVehiculosPorCalle];	
		double xDerecho = entorno.ancho() - 750;
		double yDerecho = entorno.alto()/2+275.0; //el 1er auto se crea en el carril 1
		for(int i=0; i < transitoDerecho.length; i++) {
			if(i == 1 || i == 4 || i == 7) {
				transitoDerecho[i]= new Vehiculo(xDerecho, yDerecho);
				xDerecho += 100.0;
				yDerecho =entorno.alto()/2 + 125; // Carril 4 de abajo para arriba   325 en y --->
			}else if(i == 2 || i == 5 || i == 8) {
				transitoDerecho[i]= new Vehiculo(xDerecho, yDerecho);
				xDerecho += 150.0;
				yDerecho = entorno.alto()/2-175.0;  //Carril 8 de abajo para arriba   125 en y --->
			}else if(i == 3 || i == 6 || i == 9){
				transitoDerecho[i]= new Vehiculo(xDerecho,yDerecho);
				xDerecho += 100.0;
				yDerecho = entorno.alto()/2-125.0; // Carril 7 de abajo para arriba   175 en y --->
			}else{
				transitoDerecho[i]= new Vehiculo(xDerecho, yDerecho);
				xDerecho += 150.0;
				yDerecho = entorno.alto()/2+275.0; //Carril 1 de abajo para arriba  475 en y  --->
			}
		}

		//Autos q van para la izquierda  <<----------
	
		transitoIzquierdo = new Vehiculo[cantVehiculosPorCalle];	
		double xIzquierdo = entorno.ancho() - 50;
		double yIzquierdo = entorno.alto()/2+175.0; // el 1er auto se crea en el carril 8
		for(int i=0; i < transitoIzquierdo.length; i++) {
			if(i == 1 || i == 4 || i == 7) {
				transitoIzquierdo[i]= new Vehiculo(xIzquierdo, yIzquierdo);
				xIzquierdo += 100.0;
				yIzquierdo =entorno.alto()/2-25.0; // Carril 5 de abajo para arriba   275 en y <--
			}else if(i == 2 || i == 5 || i == 8) {
				transitoIzquierdo[i]= new Vehiculo(xIzquierdo, yIzquierdo);
				xIzquierdo += 150.0; 
				yIzquierdo = entorno.alto()/2-75.0; //Carril 6 de abajo para arriba   225 en y <--
			}else if(i == 3 || i == 6 || i == 9){
				transitoIzquierdo[i]= new Vehiculo(xIzquierdo, yIzquierdo);
				xIzquierdo += 100.0;
				yIzquierdo = entorno.alto()/2+225.0; // Carril 2 de abajo para arriba  425 en y <--
				
			}else{
				transitoIzquierdo[i]= new Vehiculo(xIzquierdo, yIzquierdo);
				xIzquierdo += 150.0;
				yIzquierdo = entorno.alto()/2+175.0; //Carril 3 de abajo para arriba   375 en y <--		  
			} 
		}
		
	//----------------------------------------------------------------	
		
		// Inicia el juego!
		
		this.entorno.iniciar();
		
		Herramientas.loop("tarzan_boy.wav"); ///cancion de juego

	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick() {
		// Procesamiento de un instante de tiempo
		
		//Dibujar en pantalla y scroll de calle
		int aceleracion=i.getTiempo(); // la aceleracion se va incrementado al pasar del tiempo
		calle.dibujarabc(entorno);
		calle2.dibujarabc(entorno);
		calle3.dibujarabc(entorno);
		
		if(pause==0) {
			if(entorno.sePresiono(entorno.TECLA_SHIFT)||entorno.sePresiono(entorno.TECLA_ARRIBA)||entorno.sePresiono(entorno.TECLA_ABAJO)||entorno.sePresiono(entorno.TECLA_DERECHA)||entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
				pause=1;
			}
		}
		else {
				if(entorno.sePresiono(entorno.TECLA_SHIFT)){
			pause=0;			
		}}
				
		
		int lambda = 4400;//aumentar lambda hace el juego mas facil, en cuanto el desplazamiento de pantalla hacia abajo
		int gamma= 120000000;//Reducir gamma hace el juego mas dificil en cuanto a velocidad de los autos
		calle.bajar(((aceleracion/lambda)+1)*pause);
		calle2.bajar(((aceleracion/lambda)+1)*pause);
		calle3.bajar(((aceleracion/lambda)+1)*pause);
		calle.redibujar();
		calle2.redibujar();
		calle3.redibujar();
		
		
		//---------------------------------------------------------
		
		//----------*Dibujar Propiedades de los Vehiculos*----------------------------
		
		//----------->>>---Autos que se desplazan hacia la derecha-------->>>
		for(int i=0; i < cantVehiculosPorCalle; i++) {
			if (transitoDerecho[i] != null) {
				transitoDerecho[i].dibujarAutosDerecha(entorno);

				if (i == 1 || i == 4 || i == 7) {
					transitoDerecho[i].desplazarDerVelocidad(((aceleracion/gamma)+2)*pause);
				} else if (i == 2 || i == 5 || i == 8) {
					transitoDerecho[i].desplazarDerVelocidad(((aceleracion/gamma)+2)*pause);
				} else if (i == 3 || i == 6 || i == 9) {
					transitoDerecho[i].desplazarDerVelocidad(((aceleracion/gamma)+2)*pause);
				} else {
					transitoDerecho[i].desplazarDerVelocidad(((aceleracion/gamma)+2)*pause);
				}
				// transitoDerecho[i].desplazarDerecha();
				transitoDerecho[i].colicionConBordesDerecho(entorno, transitoDerecho);
				transitoDerecho[i].bajar(((aceleracion/lambda)+1)*pause);
				transitoDerecho[i].colicionConBordeInferior(entorno, transitoDerecho);
			}else {
			    if(i>4) {
			    	transitoDerecho[i]=new Vehiculo(entorno.ancho()+200,transitoDerecho[i-3].getY());//si se rompio el coche crea otro fijandose
			    //la posicion en y del coche anterior del mismo carril
			    }else if(i==1||i==2||i==3){
			    	transitoDerecho[i]=new Vehiculo(entorno.ancho()+200,transitoDerecho[i+3].getY());
			    }else{
			    	transitoDerecho[i]=new Vehiculo(entorno.ancho()+200,transitoDerecho[3].getY()+50);//si la posicion es 0 toma la posicion de y
			    //del transito 3,+50
			    }
			 }
		}
			  //<<<-----Autos que se desplazan hacia la izquierda---<<<--------
		for(int i=0; i < cantVehiculosPorCalle; i++) {
			if(transitoIzquierdo[i]!=null) {
			   transitoIzquierdo[i].dibujarAutosIzquierda(entorno);
			   		   
					if (i == 1 || i == 4 || i == 7) {
						transitoIzquierdo[i].desplazarIzqVelocidad(((aceleracion/gamma)+2)*pause);
					} else if (i == 2 || i == 5 || i == 8) {
						transitoIzquierdo[i].desplazarIzqVelocidad(((aceleracion/gamma)+2)*pause);
					} else if (i == 3 || i == 6 || i == 9) {
						transitoIzquierdo[i].desplazarIzqVelocidad(((aceleracion/gamma)+2)*pause);
					} else {
						transitoIzquierdo[i].desplazarIzqVelocidad(((aceleracion/gamma)+2)*pause);
					}
	   
			   //transitoIzquierdo[i].desplazarIzquierda();
			   transitoIzquierdo[i].colicionConBordesIzquierdo(entorno,transitoIzquierdo);
			   transitoIzquierdo[i].bajar(((aceleracion/lambda)+1)*pause);
			   transitoIzquierdo[i].colicionConBordeInferior(entorno, transitoIzquierdo);
			} else {
				if (i > 4) {
					transitoIzquierdo[i] = new Vehiculo(-200, transitoIzquierdo[i - 3].getY());
				} else if (i == 1 || i == 2 || i == 3) {
					transitoIzquierdo[i] = new Vehiculo(-200, transitoIzquierdo[i + 3].getY());
				} else {
					transitoIzquierdo[i] = new Vehiculo(-200, transitoIzquierdo[3].getY() + 50);
				}
			}
		}
		if(conejo.getEstaVivo() && !i.getSeTerminoElJuego()) {
			//---------*Dibujar Propiedades del conejo*------------------------------
			{
			conejo.dibujar(entorno);
			conejo.colicionConBordes(entorno);
			conejo.bajar(((aceleracion/lambda)+1)*pause);
			
			// Dibujar interface del juego (puntos, tiempo, etc) se debe ejecutar al final para no ser tapado por el resto
			i.mostrarEnPantalla(entorno);
			i.aumentarTiempo(pause);
			i.aumentarPuntos();	
			}
			//movimientos y acciones del conejo
			if(entorno.sePresiono(entorno.TECLA_ARRIBA) && conejo.getY() >=100 && !i.getSeTerminoElJuego()) {//el && verifica casilla libre arriba
				conejo.saltarArriba();
				i.aumentarEnergia();
				i.aumentarSaltos();
			}
			if(entorno.sePresiono(entorno.TECLA_DERECHA) && !i.getSeTerminoElJuego()) { //el conejo se desplaza a la derecha 
				conejo.saltarDerecha();
			}
			if(entorno.sePresiono(entorno.TECLA_IZQUIERDA) && !i.getSeTerminoElJuego()) { //el conejo se desplaza a la izquierda
				conejo.saltarIzquierda();
			}
			//lanzar kameha si se presiona la tecla ESPACIO 
			if(entorno.sePresiono(entorno.TECLA_ESPACIO) && i.kamehameListo() >= 10 && !i.getSeTerminoElJuego()) {
				kamehameha= conejo.lanzar();
				i.agotarEnergia();
			}
			if(i.kamehameListo()>= 10) {
				i.cargado(entorno);
			}
			//si el kameha es creado, se dibuja y se lanza hacia arriba
			if(kamehameha != null) { 
				kamehameha.desplazarArriba();
				kamehameha.dibujar(entorno);
			for(int e=0;e<transitoIzquierdo.length;e++){
				if(kamehameha!=null && transitoIzquierdo[e]!=null && kamehameha.colicionConCoche(transitoIzquierdo) == e) {
					kamehameha=null;
					transitoIzquierdo[e] = null;
					i.confirmarImpacto();//se verifica si el kameha impacta con algun autoIzquierdo y lo destruye 
			}	   
			}  
			for(int e=0;e<transitoDerecho.length;e++){
			  if(kamehameha!=null && transitoDerecho[e]!=null && kamehameha.colicionConCoche(transitoDerecho) == e) {
				  kamehameha=null;
				  transitoDerecho[e] = null;
				  i.confirmarImpacto();    //se verifica si el kameha impacta con algun autoDerecho y lo destruye
			 } 
			
			}
			}
			//--------------Muerte del conejo, si choca con el piso o lo choca un auto------------------------------------------------
			
			if(conejo.colicionConVehiculo(transitoDerecho) ||conejo.colicionInf() ||conejo.colicionConVehiculo(transitoIzquierdo))
			{
				conejo.muere();
			}
			
		}
			
		
		//conejo muerto= juego en pause
		if(!conejo.getEstaVivo()) {
			pause=0;
		}
		//----------------------------------------------------
		
		//mientras el conejo no este vivo...
			//---------------Game Over------------------------------------
			if(!conejo.getEstaVivo() && !i.getEstaTablaDePuntaje() && !i.getSeTerminoElJuego()) { // se fija si el conejo no esta vivo y si la tabla de puntajes no esta activada
				i.gameOver(entorno);
				
			}
			
			//----------------reaparicion del conejo despues del game over
			if(entorno.sePresiono(entorno.TECLA_ENTER)&&i.getEstaTablaDePuntaje()) {//revivir conejo despues de la tabla de puntaje
				i.resetearPuntajes();
				if(calle.getY()<=300 &&calle.getY()>=0 ) { //alinear con vereda 1
					conejo.revivir(calle.getX(),calle.getY()+125);
					i.Apagar();
				}else {
					if(calle2.getY()<=300 &&calle2.getY()>=0 ) { //alinear con vereda 2
						conejo.revivir(calle2.getX(),calle2.getY()+125);;
						i.Apagar();
						}else if(calle3.getY()<=300 &&calle3.getY()>=0 ){ //alinear con vereda 3
							conejo.revivir(calle3.getX(),calle3.getY()+125);
							i.Apagar();
					}
				}
				i.ocultarTablaDePuntaje();
			}

			//------------------Tabla De puntajes final-------------------------
			if(entorno.sePresiono(entorno.TECLA_ENTER) && !conejo.getEstaVivo() && !i.getSeTerminoElJuego()) {//si se presiona enter y el conejo esta muerto tablaDePuntaje == true
				i.mostrarTablaDePuntaje();
				i.SeTerminoElJuegoOff();
			}
			if(i.getEstaTablaDePuntaje()) {
				i.dibujarTablaDePuntaje(entorno);
			}
			if(entorno.sePresiono(entorno.TECLA_ENTER) && i.getSeTerminoElJuego()) {//si se presiona enter y el conejo esta muerto tablaDePuntaje == true
			    i.mostrarTablaDePuntaje();
			    i.Prender();
			    i.SeTerminoElJuegoOff();	  
			}
		
		//------------ You Win si el conejo si se llegan a los 1000 puntos
		
			if(i.getPuntos() >= 50 && !i.getBotonOnOff()) { 
				i.dibujarGano(entorno);
				i.SeTerminoElJuegoOn();
			}
		
	}
		

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
