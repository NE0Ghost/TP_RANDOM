package Generation;

import java.awt.Color;
import java.awt.Paint;
import java.util.List;



import java.util.ArrayList;
import java.util.Collections;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Main extends ApplicationFrame {
	
	public int repetition = 20;
	public int nbMachine = 2;

   public Main( String applicationTitle, String chartTitle ) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Temps (min)" ,
         "Evenement" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 860 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesLinesVisible(0, true);
      renderer.setSeriesShapesVisible(0, false);
      renderer.setSeriesPaint( 1 , Color.BLUE);
      renderer.setSeriesShapesVisible(1, false);
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( ) {
      final XYSeries serie = new XYSeries( "Evenement" );
      final XYSeries fileAttente = new XYSeries ("Personne dans le système");
      
      
      
      // Creation loi et tirage ARRIVEE
      Loi arrivee = new LoiExponentielle(2);
      this.repetition = (int) (180*((LoiExponentielle) arrivee).getLambda()/0.69314718);
	  arrivee.tirage(this.repetition);
	  List<Double> listArrivee = arrivee.getListTireeCumulee();
	  
	  // Creation loi et tirage TRAITEMENT
	  Loi machineACafe = new LoiExponentielle(1);
	  machineACafe.tirage(this.repetition);
      List<Double> listTraitement = machineACafe.getListTiree();
	  
      //Creation d'une liste de sortie des utilisateurs
      List<Double> sortie = new ArrayList<Double>();
      double[] time = new double[this.nbMachine];
      for( int i = 0 ; i < this.nbMachine ; i++ ) {
    	  time[i] = 0.0;
      }
      
      int index = 0;
      boolean traite = false;
      
      for(int i = 0; i < listTraitement.size(); i++) {
    	  traite = false;
    	  if( time[0] < 125 ) {
    		  
    		  for(int j = 0 ; j < nbMachine ; j++) {
    			  if ( listArrivee.get(i) + listTraitement.get(i) > time[j] ) {
        			  time[j] = listArrivee.get(i) + listTraitement.get(i);
        			  sortie.add(time[j]);
        			  traite = true;
        			  j = nbMachine;
        		  }
    		  }
    		  
    		  if (!traite) {
    			  index = 0;
    			  for ( int j = 1; j < nbMachine ; j++) {
    				  if(time[index] > time[j]) {
    					  index = j;
    				  }
    			  }
    			  time[index] = time[index] + listTraitement.get(i);
    			  sortie.add(time[index]);
    			  traite = true;
    		  }
    		  
    	  }
    	  else { 
    		  i = listTraitement.size();
    	  }
      }
      for(int i = 0 ; i < sortie.size(); i++) { 
    	  System.out.println(" arrivee " + i + ": " + listArrivee.get(i) );
    	  System.out.println(" sortie " + i + ": " + sortie.get(i) ); 
      }
      
    /*// Calcul du temps moyen dans le système
      double moyenneTAS = 0.0;
      for ( int i = 0; i < this.repetition/3; i++) {
    	  moyenneTAS += sortie.get(i) - listArrivee.get(i);
      } 
      moyenneTAS /= this.repetition/3;
      System.out.println("Temps moyen dans le système : " + moyenneTAS + " minutes.");  
      */
      // Mise en place du graph
      double timer = 0.0;
	  int file = 0;
	  serie.add( 0.0, 0.0 );
	  fileAttente.add( 0.0 , 0.0 );
	  Collections.sort(sortie);
      
	  int cptA = 0; //Compteur index Arrivee
	  int cptS = 0; //Compteur index Sortie
	  
      do {  // Entrees et sorties du système
    	  
    	  if( listArrivee.get(cptA) < sortie.get(cptS) ) {
    		// Entree dans le système
    		  file++;
    		  timer = listArrivee.get(cptA);
    		  if ( file > this.nbMachine ) {
    			  
	    		  fileAttente.add( timer - 0.0001 , file-this.nbMachine - 1.0 );
	        	  fileAttente.add( timer , file - this.nbMachine );
        	  
	    	  } else {
				  fileAttente.add( timer , 0 );
			  }
    		  
        	// Ajout d'une arrivee
    	      serie.add( timer - 0.0001, 0.0 );
    	      serie.add( timer, -1.0 );
    	      serie.add( timer + 0.0001, 0.0 );
    	    
    	      cptA++;
    	      
    	  } else {
    		// Sortie du système
    		  file--;
    		  timer = sortie.get(cptS);
    		  if ( file > this.nbMachine) {
	    		  fileAttente.add( timer - 0.0001 , file - this.nbMachine + 1.0 );
	        	  fileAttente.add( timer , file - this.nbMachine );
    		  } else {
    			  fileAttente.add( timer , 0 );
    		  }
        	  
        	  cptS++;
    	  }
      
      } while ( timer < 120 );
      
      
      /*
      // Calcul de la moyenne de temps entre evenement
      double moyenneIntervalle = listArrivee.get(0);
      for ( int i = 1; i < cptA; i++) {
    	  moyenneIntervalle += listArrivee.get(i)-listArrivee.get(i-1);
      } 
      moyenneIntervalle /= cptA;
      System.out.println("Temps moyen entre évènements : " + moyenneIntervalle + " minutes.");
      */
      
      final XYSeriesCollection dataset = new XYSeriesCollection();          
      dataset.addSeries( serie );
      dataset.addSeries( fileAttente );
      return dataset;
   }

   public static void main( String[ ] args ) {
      Main chart = new Main("Graphe des evenement aleatoires",
         "Evenement aleatoires");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }
}
