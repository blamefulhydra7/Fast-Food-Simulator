// Fig. 23.11: Bufer.java
// La interfaz Bufer especifica los mÈtodos que el Productor y el Consumidor llaman.
public interface Bufer
{
   // coloca valor int value en Bufer
   public void establecer( int valor ) throws InterruptedException; 

   // obtiene valor int de Bufer
   public int obtener() throws InterruptedException; 
   
   public boolean estaVacio();
   
} // fin de la interfaz Bufer


/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigaciÛn y prueba de las teorÌas y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garantÌa de      *
 * ning˙n tipo, expresa o implÌcita, en relaciÛn con estos programas o    *
 * con la documentaciÛn contenida en estos libros. Los autores y el       *
 * editor no ser·n responsables en ning˙n caso por los daÒos consecuentes *
 * en conexiÛn con, o que surjan de, el suministro, desempeÒo o uso de    *
 * estos programas.                                                       *
 *************************************************************************/