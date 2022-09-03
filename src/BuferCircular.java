public class BuferCircular implements Bufer
{
   private final int[] bufer = new int[50]; // b˙fer compartido

   private int celdasOcupadas = 0; // número de búferes utilizados
   private int indiceEscritura = 0; // Índice del siguiente elemento a escribir
   private int indiceLectura = 0; // Índice del siguiente elemento a leer
   
   // coloca un valor en el búfer
   public synchronized void establecer( int valor ) throws InterruptedException
   {
      // imprime información del subproceso y del búfer, después espera;
      // mientras no haya ubicaciones vacÌas, coloca el subproceso en estado de espera
      while ( celdasOcupadas == bufer.length ) 
      {
         System.out.printf( "Local lleno. Clientes esperan.\n" );
         wait(); // espera hasta que haya una celda libre en el búfer
      } // fin de while

      bufer[ indiceEscritura ] = valor; // establece nuevo valor del b˙fer

      // actualiza Ìndice de escritura circular
      indiceEscritura = ( indiceEscritura + 1 ) % bufer.length;

      ++celdasOcupadas; // una celda más del búfer est· llena
      mostrarEstado( "Cliente solicita combo: " + valor );
      notifyAll(); // notifica a los subprocesos en espera para que lean del b˙fer
   } // fin del método establecer
    
   // devuelve un valor del búfer
   public synchronized int obtener() throws InterruptedException
   {
      // espera hasta que el búfer tenga datos, después lee el valor;
      // mientras no haya datos para leer, coloca el subproceso en estado de espera
      while ( celdasOcupadas == 0 ) 
      {
         System.out.printf( "Local vacío. Cocineros esperan.\n" );
         wait(); // espera hasta que se llene una celda del b˙fer
      } // fin de while

      int valorLeido = bufer[ indiceLectura ]; // lee un valor del b˙fer

      // actualiza Ìndice de lectura circular
      indiceLectura = ( indiceLectura + 1 ) % bufer.length;

      --celdasOcupadas; // hay una celda ocupada menos en el búfer
      mostrarEstado( "Cocinando pedido: " + valorLeido );
      notifyAll(); // notifica a los subprocesos en espera que pueden escribir en el búfer

      return valorLeido;
   } // fin del método obtener
    
   // muestra la operación actual y estado del b˙fer
   public void mostrarEstado( String operacion )
   {
      // imprime operacion y número de celdas ocupadas del búfer
      System.out.printf( "%s%s%d)\n", operacion, 
         " (celdas ocupadas del bufer: ", celdasOcupadas);
   } // fin del método mostrarEstado
   
   public boolean estaVacio()
   {
	   return celdasOcupadas == 0;
   }
} // fin de la clase BuferCircular
