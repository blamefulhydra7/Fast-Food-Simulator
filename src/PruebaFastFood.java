import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PruebaFastFood
{
   public static void main( String[] args )
   {
      // crea nueva reserva con dos subprocesos
      ExecutorService aplicacion = Executors.newCachedThreadPool();

      // crea objeto BuferCircular para almacenar valores int
      BuferCircular ubicacionCompartida = new BuferCircular();

      // muestra el estado inicial del objeto BuferCircular
      ubicacionCompartida.mostrarEstado( "Estado inicial" );

      // ejecuta las tareas Productor y Consumidor
      aplicacion.execute( new Productor( ubicacionCompartida ) );
      aplicacion.execute( new Consumidor( ubicacionCompartida ) );

      aplicacion.shutdown();
   } // fin de main
} // fin de la clase PruebaBuferCircular