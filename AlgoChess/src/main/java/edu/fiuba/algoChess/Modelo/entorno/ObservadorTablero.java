package edu.fiuba.algoChess.Modelo.entorno;

import edu.fiuba.algoChess.Modelo.entidades.Pieza;

public class ObservadorTablero {

    static protected Tablero tablero = null;

    public void ObservadorTablero(){
    }

    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }

    public Celda obtenerCelda(Ubicacion ubicacion){
        return this.tablero.getCelda(ubicacion);
    }

    public Pieza obtenerPieza(Ubicacion ubicacion){
        return this.tablero.getCelda(ubicacion).piezaActual;
    }

}