package tablero;

import org.junit.Assert;
import org.junit.Test;

import personaje.Gohan;
import personaje.Goku;
import personaje.Personaje;

public class CasilleroTest {

	@Test
	public void testAlCrearCasilleroEstaVacio() throws PosicionInvalidaException{
		Posicion posicion = new Posicion(2,3);
		Casillero casillero = new Casillero(posicion);
		Assert.assertTrue(casillero.estaVacio());	
	}

	@Test
	public void testAgregoUnPersonajeEstaVacioDevuelveFalse() throws PosicionInvalidaException, CasilleroOcupadoException{
		Posicion posicion = new Posicion(2,3);
		Casillero casillero = new Casillero(posicion);
		Personaje goku = new Goku();
		
		Assert.assertTrue(casillero.estaVacio());
		casillero.colocar(goku);
		Assert.assertFalse(casillero.estaVacio());
	}
	
	@Test(expected = CasilleroOcupadoException.class)
	public void testQuieroAgregarDosPersonajesAlMismoCasilleroDevuelveCasilleroOcupadoException() throws PosicionInvalidaException, CasilleroOcupadoException{
		Posicion posicion = new Posicion(2,3);
		Casillero casillero = new Casillero(posicion);
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();
		
		Assert.assertTrue(casillero.estaVacio());
		casillero.colocar(goku);
		Assert.assertFalse(casillero.estaVacio());
		casillero.colocar(gohan);
	}
	

}