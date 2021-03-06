package controlador.eventos;

import controlador.CaminoController;
import controlador.PersonajeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.JuegoTerminadoException;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.acciones.NoPuedeAtacarSiendoChocolateException;
import modelo.excepciones.acciones.YaAtacasteEsteTurnoException;
import modelo.excepciones.personaje.NoEsSuTurnoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.partida.Partida;
import modelo.personaje.Personaje;
import modelo.personaje.equipos.Equipo;
import vista.Aplicacion;
import vista.VistaInfo;
import vista.VistaTablero;

public class BotonAtaqueBasicoHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;
    private final PersonajeController personajeController;
    private final CaminoController caminoController;
    private final VistaInfo info;
    private final Aplicacion app;
    private Label consola;

    public BotonAtaqueBasicoHandler(Aplicacion app, VistaTablero vistaTablero, Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info, Label unaConsola) {
        this.app = app;
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.info = info;
        this.consola = unaConsola;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Equipo equipoJugando = partida.turnoActual();
        Personaje atacante = personajeController.obtenerPersonaje();
        Personaje atacado = personajeController.obtenerPersonaje();
        try {
            int vidaPreviaDefensor = atacado.getVida();
            partida.ataqueBasico(atacante, atacado);
            int danioTotal = vidaPreviaDefensor-atacado.getVida();
            consola.setText(atacante.getClass().getSimpleName()+" ataca a "+atacado.getClass().getSimpleName()+" dejandolo en "+atacado.getVida()+" (-"+danioTotal+")");

        } catch (NoPuedeAtacarMismoEquipoException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede atacar a su amigo " + atacado.getClass().getSimpleName() + "!");
        } catch (NoPuedeAtacarAEsaDistanciaException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede atacar a esa distancia.");
        } catch (NoPuedeAtacarSiendoChocolateException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede atacar este turno. Es chocolate.");
        } catch (NoEsSuTurnoException e){
            consola.setText("No es el turno de este personaje.");
        } catch (YaAtacasteEsteTurnoException e) {
            consola.setText("El equipo de " + atacante.getClass().getSimpleName() + " ya ataco este turno!");
        } catch (JuegoTerminadoException e) {
            consola.setText("Ha ganado el equipo de los " + equipoJugando.getNombre() + ". FELICITACIONES!");
            new VentanaJuegoTerminado(app);
        }

        personajeController.limpiar();
        caminoController.limpiar();
        if (equipoJugando.getNombre().equals(partida.turnoActual().getNombre())) {
            info.setAtaques(Integer.toString(partida.ataquesRestantes()));
        } else {
            consola.setText("Comienza el turno de los " + partida.turnoActual().getNombre());
            info.setTurno(partida.turnoActual().getNombre());
            info.setAccionesDefault();
        }
        info.setStatsDefault();
        vistaTablero.actualizarVista();
    }
}
