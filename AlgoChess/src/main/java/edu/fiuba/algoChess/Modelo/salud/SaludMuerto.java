package edu.fiuba.algoChess.Modelo.salud;

public class SaludMuerto extends Salud {

	public SaludMuerto(){
		super(0,0);
	}

	@Override
	public Salud curar(double salud) {
		return this;
	}

	@Override
	public Salud herir(double salud) {
		return this;
	}

}
