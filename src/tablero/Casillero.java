package tablero;

import personaje.Personaje;

public class Casillero {

	private Personaje personaje;
	private Posicion posicion;
	
	
	public Casillero(Posicion unaPosicion) {
		posicion = unaPosicion;
		personaje = null;
	}

	public boolean estaVacio() {
		return ( personaje == null );
	}

	public void colocar(Personaje unPersonaje) throws CasilleroOcupadoException {
		if (personaje != null) throw new CasilleroOcupadoException();
		personaje = unPersonaje;
		personaje.ubicarEn(this);
	}

	public void quitar() {
		personaje = null;
	}

	public int distanciaHasta(Casillero otroCasillero) {
		return posicion.distanciaHasta(otroCasillero.posicion);
	}

}
