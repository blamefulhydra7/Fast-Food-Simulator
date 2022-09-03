import java.util.Random;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Consumidor implements Runnable
{
   private final Bufer ubicacionCompartida; // referencia al objeto compartido
   private final long doceMinutos = 1000 * 60 * 12;
   private final int diezSegundos = 1000 * 10;
   private final int doceSegundos = 1000 * 12;
   private final int catorceSegundos = 1000 * 14;

   // constructor
   public Consumidor( Bufer compartido )
   {
       ubicacionCompartida = compartido;
   }

   
   public void run()                             
   {
      int normal = 0;
      int doble = 0;
      int extraGrande = 0;
      
      // Hacer un ciclo que dure 8 minutos
      Calendar hora = new GregorianCalendar();
      long horaInicial = hora.getTimeInMillis();
      long horaActual = hora.getTimeInMillis();
      
      while ( (( horaActual - horaInicial ) <= doceMinutos) || !ubicacionCompartida.estaVacio() )
      {
          try
          {
             int tipoServicio = ubicacionCompartida.obtener();
             if ( tipoServicio == 1 )
             {
                 normal++;
                 Thread.sleep( diezSegundos );
             }
             else if ( tipoServicio == 2 )
             {
                 doble++;
                 Thread.sleep( doceSegundos );
             }
             else
             {
                 extraGrande++;
                 Thread.sleep( catorceSegundos );
             }
              System.out.println("Pedido entregado: " + tipoServicio);
          } // fin de try
          catch ( InterruptedException excepcion ) 
          {
             excepcion.printStackTrace();
          } // fin de catch
    	  
          // Actualizar la hora
          hora = new GregorianCalendar();
          horaActual = hora.getTimeInMillis();
      }

      System.out.println( 
         "Local vacío\nCombos atendidos" );
      
      // Calcular importes
      double impNormal = normal * 50.0;
      double impDoble = doble * 60.0;
      double impExtraGrande = extraGrande * 70.0;
      
      System.out.printf("Combo\t\tCantidad\t Importe\n");
      System.out.printf( "Normal\t\t%8d\t%,8.2f\n" , normal, impNormal);
      System.out.printf( "Doble\t\t%8d\t%,8.2f\n" , doble, impDoble);
      System.out.printf( "Extra grande\t%8d\t%,8.2f\n" , extraGrande, impExtraGrande);
      System.out.printf( "Total\t\t%8d\t%,8.2f\n" , normal + doble + extraGrande, impNormal + impDoble + impExtraGrande);

   } // fin del método run
} 
