package modelo.tablero;

import modelo.Consumibles.Consumible;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.personaje.Personaje;

public class Casillero {

	
	private Personaje personaje;
	private Posicion posicion;
	private Consumible consumible;
	
	public Casillero(Posicion unaPosicion) {
		posicion = unaPosicion;
		personaje = null;
	}
	
	public boolean estaVacio() {
		return ( personaje == null );
	}

	public void colocar(Personaje unPersonaje) {
		if (personaje != null) throw new CasilleroOcupadoException();
		if (consumible != null) {
			unPersonaje.consumir(consumible);
			consumible = null;
		}
		personaje = unPersonaje;
	}

	public void vaciar() {
		personaje = null;
	}

	public int distanciaHasta(Casillero otroCasillero) {
		return posicion.distanciaHasta(otroCasillero.posicion);
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void colocar(Consumible unConsumible) {
		if ((personaje != null)||(consumible != null)) throw new CasilleroOcupadoException();
		consumible = unConsumible;
	}

	public Posicion getPosicion(){
		return posicion;
	}


    public Consumible getConsumible() {
        return consumible;
    }
}
