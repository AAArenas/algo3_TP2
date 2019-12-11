package edu.fiuba.algoChess.Modelo.juego;

import edu.fiuba.algoChess.Modelo.bandos.Bando;
import edu.fiuba.algoChess.Modelo.bandos.BandoJugador1;
import edu.fiuba.algoChess.Modelo.bandos.BandoJugador2;
import edu.fiuba.algoChess.Modelo.entidades.Pieza;
import edu.fiuba.algoChess.Modelo.entidades.Soldado;
import edu.fiuba.algoChess.Modelo.entidades.*;
import edu.fiuba.algoChess.Modelo.entorno.Tablero;
import edu.fiuba.algoChess.Modelo.entorno.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class Juego {

	@Getter
	@Setter
	private Tablero tablero;

	@Getter
	@Setter
	private Jugador jugador1;

	@Getter
	@Setter
	private Jugador jugador2;

	@Getter
	@Setter
	private boolean activoBando1;

	@Getter
	@Setter
	private Bando bandoActivo;

	@Getter
	@Setter
	private Jugador jugadorActivo;

	@Getter
	@Setter
	private ArrayList<Pieza> piezasEnTablero;

	@Getter
	@Setter
	Boolean segundaEtapa = false;

	@Getter
	@Setter
	Boolean finDeJuego = false;

	@Getter
	@Setter
	Boolean enAplicacionDeComportamiento = false;

	@Getter
	@Setter
	Pieza receptor;

	public static final Soldado soldado = new Soldado();

	public Juego() {

		this.jugador1 = new Jugador();
		this.jugador1.setBando(new BandoJugador1());
		this.jugador2 = new Jugador();
		this.jugador2.setBando(new BandoJugador2());
		this.tablero = new Tablero(jugador1.getBando(), jugador2.getBando());
		this.piezasEnTablero = new ArrayList<>();
		this.bandoActivo = new BandoJugador1();
		this.jugadorActivo = this.jugador1;
	}

	public Juego(String nombreJugador1, String nombreJugador2) {
			this.tablero = new Tablero(new BandoJugador1(), new BandoJugador2());
			this.jugador1 = new Jugador(nombreJugador1);
			this.jugador2 = new Jugador(nombreJugador2);
			this.piezasEnTablero = new ArrayList<>();
			this.bandoActivo = new BandoJugador1();
			this.jugadorActivo = this.jugador1;
		}

		public static void main (String[]args){

		}


		public Pieza crearPieza (String nombre, Ubicacion ubicacion){

			Pieza pieza = new PiezaNull();

				if (nombre.contains("Soldado")) {
					pieza = new Soldado(ubicacion, this.bandoActivo, this.tablero);
					//ubicarEnCelda(pieza, ubicacion);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					pasarTurno();
					return pieza;
				}

				if (nombre.contains("Catapulta")) {
					pieza = new Catapulta(ubicacion, this.bandoActivo, this.tablero);
					//ubicarEnCelda(pieza, ubicacion);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					pasarTurno();
					return pieza;
				}

				if (nombre.contains("Jinete")) {
					pieza = new Jinete(ubicacion, this.bandoActivo, this.tablero);
					//ubicarEnCelda(pieza, ubicacion);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					pasarTurno();
					return pieza;
				}

				if (nombre.contains("Curandero")) {
					pieza = new Curandero(ubicacion, this.bandoActivo, this.tablero);
					//ubicarEnCelda(pieza, ubicacion);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					pasarTurno();
					return pieza;
				}


			return pieza;
		}

	public void atacar (Pieza atacante, Pieza atacado){
		atacante.atacar(atacado);
	}

	public void curar (Pieza curador, Pieza curado){
		curador.curar(curado);
	}

	public Pieza obtenerPiezaAPartirDeUbicacion(Ubicacion ubicacionPiezaObjetivo) {
		Pieza retorno = new PiezaNull();
		return this.getPiezasEnTablero().stream().filter(pieza -> ubicacionPiezaObjetivo.equals(pieza.getUbicacion())).findFirst().orElse(retorno);
	}

	public void pasarTurno(){
		Bando retorno = activoBando1? new BandoJugador2(): new BandoJugador1();
		Jugador jugadorActivo = activoBando1? this.jugador2: this.jugador1;
		this.bandoActivo = retorno;
		this.jugadorActivo = jugadorActivo;
		this.activoBando1 = activoBando1?false:true;
	}

	public void comenzarSegundoStage() {
		setSegundaEtapa(true);
	}

	public void terminarJuego() {
		setFinDeJuego(true);
	}
}
