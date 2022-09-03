import java.util.Random;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Productor implements Runnable
{
   private final static Random generador = new Random();
   private final Bufer ubicacionCompartida; // referencia al objeto compartido
   private final long doceMinutos = 1000 * 60 * 12;

   // constructor
   public Productor( Bufer compartido )
   {
       ubicacionCompartida = compartido;
   } // fin del constructor de Productor

   
   public void run()                             
   {
      int normal = 0;
      int doble = 0;
      int extraGrande = 0;
      
      // Hacer un ciclo que dure 10 minutos
      Calendar hora = new GregorianCalendar();
      long horaInicial = hora.getTimeInMillis();
      long horaActual = hora.getTimeInMillis();
      
      while ( ( horaActual - horaInicial ) <= doceMinutos)
      {
          try
          {
             int tipoServicio = generador.nextInt( 3 ) + 1;

             ubicacionCompartida.establecer( tipoServicio );
             if ( tipoServicio == 1 )
            	 normal ++;
             else if ( tipoServicio == 2 )
            	 doble ++;
             else
            	 extraGrande ++;
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
         "Local cerrado\nCombos solicitados" );
      
      // Calcular importes
      double impNormal = normal * 50.0;
      double impDoble = doble * 60.0;
      double impExtraGrande = extraGrande * 70.0;
      
      System.out.printf("Combo\t\tCantidad\t Importe\n");
      System.out.printf( "Normal\t\t%8d\t%,8.2f\n" , normal, impNormal);
      System.out.printf( "Doble\t\t%8d\t%,8.2f\n" , doble, impDoble);
      System.out.printf( "Extra grande\t%8d\t%,8.2f\n" , extraGrande, impExtraGrande);
      System.out.printf( "Total\t\t%8d\t%,8.2f\n" , normal + doble + extraGrande, impNormal + impDoble + impExtraGrande);

   } // fin del mÈtodo run
} 
