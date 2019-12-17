package edu.fiuba.algoChess.modelo.entidades;

import edu.fiuba.algoChess.modelo.bandos.Bando;
import edu.fiuba.algoChess.modelo.comportamientos.AtaqueNormal;
import edu.fiuba.algoChess.modelo.entorno.*;
import edu.fiuba.algoChess.modelo.excepciones.FueraDeRangoParaEjecutarComportamientoException;
import edu.fiuba.algoChess.modelo.batallones.Batallon;
import edu.fiuba.algoChess.modelo.rangos.Rango;
import edu.fiuba.algoChess.modelo.rangos.RangoSoldado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
public class Soldado extends Pieza {

	@Getter
	@Setter
	private AtaqueNormal ataqueCercano;

	@Getter
	@Setter
	private int danioCercano;

	public Soldado(Ubicacion ubicacion, Bando bando, Tablero tablero){
		super(2,75,ubicacion,bando);
		this.danioCercano = 10;
		this.ataqueCercano = new AtaqueNormal(10);
		this.rango = new RangoSoldado(this, tablero);
		tablero.ubicarEnCeldaFaseInicial(this, ubicacion);
	}

	public Rango actualizaRango(Tablero tablero) {
		this.getRango().actualizaRangoSoldado(this,tablero);
		return this.getRango();
	}

	@Override
	public ArrayList<Pieza> unirABatallonDeSoldado(ArrayList<Pieza> stackDeUnion) {
		stackDeUnion.add(this);
		return stackDeUnion;
	}

	@Override
	public ArrayList<Pieza> aniadirSoldadoAliadoStack(ArrayList<Pieza> stack, Pieza pieza) {
		return pieza.bando.aniadirSoldadoAliadoAlStack(this, stack, this.bando);
	}

	@Override
	public ArrayList<Pieza> aniadirTodoMenosSoldadoAlStack(ArrayList<Pieza> stack) {
		return stack;
	}

	public void setRango(Rango rango) {
		super.setRango(rango);
	}

	@Override
	public ArrayList<Pieza> getSoldadosContiguos(){
		return this.getRango().getSoldadosEquipo();
	}

	@Override
	public boolean soldadosInmediatosSePuedenUnir() {
		return this.getRango().getSoldadosEquipo().size() == 3;
	}

	public void moverPiezaDeBatallon(Tablero campoDeBatalla, Ubicacion ubicacion) {
		this.setUbicacion(ubicacion);
		campoDeBatalla.ubicarEnCeldaFaseInicial(this,ubicacion);
	}

	@Override
	public void ejecutarComportamientoPorDistancia(DistanciaCercana distancia, Pieza pieza) {
		this.bando.atacar(pieza, this.ataqueCercano, pieza.getBando());
	}

	@Override
	public void ejecutarComportamientoPorDistancia(DistanciaMedia distancia, Pieza pieza) {
		throw new FueraDeRangoParaEjecutarComportamientoException("Pieza fuera de rango");
	}

	@Override
	public void ejecutarComportamientoPorDistancia(DistanciaLejana distancia, Pieza pieza) {
		throw new FueraDeRangoParaEjecutarComportamientoException("Pieza fuera de rango");
	}

	@Override
	public Rango actualizaRangoInmediato(Pieza piezaCentral, Tablero tablero) {
		return null;
	}

	@Override
	public Batallon darDeAltaBatallon() {
		return this.getRango().darDeAltaBatallon();
	}

	@Override
	public ArrayList<Pieza> getSoldadosEquipo() {
		return this.getRango().getSoldadosEquipo();
	}

	public double getDanio() {
		return danioCercano;
	}

}