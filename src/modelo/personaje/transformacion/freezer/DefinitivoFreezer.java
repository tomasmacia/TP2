package modelo.personaje.transformacion.freezer;

import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;

public class DefinitivoFreezer extends Transformacion{
    protected static final int poderDePelea = 50;
    protected static final int distanciaAtaque = 3;
    protected static final int velocidad = 6;

    public DefinitivoFreezer() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) {
        throw new NoHayProximaTransformacionException();
    }
}
