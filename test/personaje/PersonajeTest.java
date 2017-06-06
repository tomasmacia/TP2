package personaje;

import estado.freezer.DefinitivoFreezer;
import estado.gohan.SuperSayajin2Gohan;
import estado.goku.KaioKenGoku;
import estado.goku.SuperSayajinGoku;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeCambiarDeEstadoKiInsuficienteException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;

public class PersonajeTest {

	
	// --------- Tests de movimiento ----------------
	@Test
	public void testGokuSeMueveEnUnCaminoDespejadoNoDevuelveExcepcion() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		
		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);
		
		goku.mover(camino);
	}
	
	@Test(expected = NoPuedeMoverCaminoObstruidoException.class)
	public void testGokuQuiereMoverPeroEstaGohanEnMedioDelCaminoDevuelveNoPuedeMoverCaminoObstruidoException() throws CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		
		Personaje goku = new Goku(casillero1);
		Personaje gohan = new Gohan(casillero2);
		Camino camino = new Camino(casilleros);
		
		goku.mover(camino);
	}
	
	@Test(expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testGokuQuiereMoverEnModoNormalExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		Casillero casillero4 = new Casillero(new Posicion(1,4));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		casilleros.add(casillero4);
		
		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);
		
		goku.mover(camino);
	}
	
	
	
/*		

	@Test 
	public void testGokuEnModoNormalPuedeMover2PosicionesYEnPrimeraTransformacionPuedeMover3Posiciones() throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException{
		Tablero tablero = new Tablero(10,10);
		Personaje goku = new Goku();
		Posicion posicion11= new Posicion(1,1);
		Posicion posicion31= new Posicion(3,1);
		Posicion posicion41= new Posicion(4,1);
		Posicion posicion61= new Posicion(6,1);
		Posicion posicion71= new Posicion(7,1);
		
		tablero.colocar(goku,posicion11);
		
		Casillero casillero41 = tablero.getCasillero(posicion41);
		try{	
			goku.moverA(casillero41);
			Assert.assertTrue(false);
		}catch(NoPuedeMoverAEsaDistanciaException e){
			Assert.assertTrue(true);
		}
		
		Casillero casillero31 = tablero.getCasillero(posicion31);
		goku.moverA(casillero31);
		Assert.assertFalse(tablero.estaVacioEn(posicion31));
		Assert.assertTrue(tablero.estaVacioEn(posicion11));
		
		goku.aumentarKi(20);
		goku.transformar();
		
		Casillero casillero71 = tablero.getCasillero(posicion71);
		try{	
			goku.moverA(casillero71);
			Assert.assertTrue(false);
		}catch(NoPuedeMoverAEsaDistanciaException e){
			Assert.assertTrue(true);
		}
		
		Casillero casillero61 = tablero.getCasillero(posicion61);
		goku.moverA(casillero61);
		Assert.assertFalse(tablero.estaVacioEn(posicion61));
		Assert.assertTrue(tablero.estaVacioEn(posicion31));
		
		
		
	}
	
	
	// --------------- Test de transformacion --------------
	@Test(expected = NoPuedeCambiarDeEstadoKiInsuficienteException.class)
	public void testTransformoAGokuCon0KiDevuelveNopuedeCambiarDeEstadoKiInsuficienteException() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.transformar();
	}

	@Test(expected = EstadoNoTieneProximoException.class)
	public void testTransformoAGokuSuperSayajinDevuelveEstadoNoTieneProximoException() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.estado = new SuperSayajinGoku();
		goku.transformar();
	}

	@Test public void testTransformoAGokuNormalYRevisoQueTengaEstadoKaioKen() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(20);
		goku.transformar();
		Assert.assertTrue(goku.estado.getClass() == KaioKenGoku.class);
	}

	@Test public void testTransformoAGokuCon80DeKiYSuKiTieneQueBajarA60() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(80);
		goku.transformar();
		Assert.assertTrue(goku.ki == 60);
	}

	@Test public void testTransformoAGokuNormal2VecesYVerificoQueSuKiPaseDe100A30() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.ki == 30);
	}

	@Test public void testTransformoAGokuNormal2VecesYVerificoQueTengaEstadoSuperSayajin() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.estado.getClass() == SuperSayajinGoku.class);
	}

	@Test public void testCreoAGokuYTiene500PuntosDeVida(){
		Personaje goku = new Goku();
		Assert.assertTrue(goku.vida == 500);
	}

	@Test public void testGokuNormalAtacaConElAtaqueNormalAOtroGokuYLeDejaLaVidaEn480() throws NoPuedeAtacarAEsaDistanciaException, DimensionDeTableroInvalidoException, CasilleroOcupadoException {
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(2,4);
		Personaje goku1 = new Goku();
		Personaje goku2 = new Goku();

		Tablero tablero = new Tablero(10,10);
		tablero.colocar(goku1,posicion1);
		tablero.colocar(goku2,posicion2);

		goku1.ataqueBasicoA(goku2);
		Assert.assertTrue(goku2.vida == 480);
	}

	@Test public void testGohanNormalAtacaAGokuDaniando20PorcientoMenosDejandoloEn488() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException, DimensionDeTableroInvalidoException {
		//Poder de ataque de gohan: 15 / Poder de ataque de goku: 20 => gohan daña 20% menos(12dmg)
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(2,4);
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();

		Tablero tablero = new Tablero(10,10);
		tablero.colocar(goku,posicion1);
		tablero.colocar(gohan,posicion2);

		gohan.ataqueBasicoA(goku);
		Assert.assertTrue(goku.vida == 488);

	}

	@Test public void testGokuNormalAtacaAGohanNormalDejandoloEn280() throws NoPuedeAtacarAEsaDistanciaException, DimensionDeTableroInvalidoException, CasilleroOcupadoException {
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(2,4);
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();

		Tablero tablero = new Tablero(10,10);
		tablero.colocar(goku,posicion1);
		tablero.colocar(gohan,posicion2);

		goku.ataqueBasicoA(gohan);
		Assert.assertTrue(gohan.vida == 280);
	}

	@Test (expected = NoPuedeAtacarAEsaDistanciaException.class)
	public void testGokuAtacaAGohanEstandoADistancia3EsperoRecibirNoPuedeAtacarAEsaDistanciaException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarAEsaDistanciaException {
		Posicion posicion1 = new Posicion(2,1);
		Posicion posicion2 = new Posicion(2,4);
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();

		Tablero tablero = new Tablero(10,10);
		tablero.colocar(goku,posicion1);
		tablero.colocar(gohan,posicion2);

		goku.ataqueBasicoA(gohan);

	}

	@Test
	public void testCreoUnTablero20x20ColocoAGokuNormalEn44YFreezerNormalEn66YHaceAtaqueBasicoDejandoloEn380() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException {
		Tablero tablero = new Tablero(20,20);
		Personaje goku = new Goku();
		Personaje freezer = new Freezer();
		Posicion posicionGoku = new Posicion(4,4);
		Posicion posicionFreezer = new Posicion(6,6);
		tablero.colocar(goku, posicionGoku);
		tablero.colocar(freezer, posicionFreezer);

		goku.ataqueBasicoA(freezer);
		Assert.assertTrue(freezer.vida == 380);
	}

	@Test
	public void testCreoUnTablero20x20ColocoAGokuNormalEn44YFreezerDefinitivoEn66YHaceAtaqueBasicoDejandoloEn384() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Goku: 20. Poder de pelea de Freezer definitivo: 50. -20%dmg (16)
		Tablero tablero = new Tablero(20,20);
		Personaje goku = new Goku();
		Personaje freezer = new Freezer();
		freezer.estado = new DefinitivoFreezer();
		Posicion posicionGoku = new Posicion(4,4);
		Posicion posicionFreezer = new Posicion(6,6);
		tablero.colocar(goku, posicionGoku);
		tablero.colocar(freezer, posicionFreezer);

		goku.ataqueBasicoA(freezer);
		Assert.assertTrue(freezer.vida == 384);
	}

	@Test
	public void testCreoUnTablero20x20ColocoAGohanSS2En33YFreezerNormalEn66YHaceAtaqueBasicoDejandoloEn300() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Gohan SS2: 100. Poder de pelea de Freezer normal: 50.
		Tablero tablero = new Tablero(20,20);
		Personaje gohan = new Gohan();
		Personaje freezer = new Freezer();
		gohan.estado = new SuperSayajin2Gohan();
		Posicion posicionGohan = new Posicion(3,3);
		Posicion posicionFreezer = new Posicion(6,6);
		tablero.colocar(gohan, posicionGohan);
		tablero.colocar(freezer, posicionFreezer);

		gohan.ataqueBasicoA(freezer);
		Assert.assertTrue(freezer.vida == 300);
	}

	@Test(expected = NoPuedeAtacarAEsaDistanciaException.class)
	public void testCreoUnTablero20x20ColocoAGohanSS2En33YFreezerNormalEn1010YHaceAtaqueBasicoLanzandoExcepcionNoPuedeAtacar() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Gohan SS2: 100. Poder de pelea de Freezer normal: 50.
		Tablero tablero = new Tablero(20,20);
		Personaje gohan = new Gohan();
		Personaje freezer = new Freezer();
		gohan.estado = new SuperSayajin2Gohan();
		Posicion posicionGohan = new Posicion(3,3);
		Posicion posicionFreezer = new Posicion(10,10);
		tablero.colocar(gohan, posicionGohan);
		tablero.colocar(freezer, posicionFreezer);

		gohan.ataqueBasicoA(freezer);
		Assert.assertTrue(freezer.vida == 400);
	}

*/
}
