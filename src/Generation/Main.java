package Generation;

import java.awt.Color;
import java.util.List;



import java.util.ArrayList;
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

   public Main( String applicationTitle, String chartTitle ) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Temps" ,
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
      final XYSeries fileAttente = new XYSeries ("File d'attente");
      
      
      
      // Creation loi et tirage ARRIVEE
      Loi arrivee = new LoiExponentielle(1);
      this.repetition = (int) (180*((LoiExponentielle) arrivee).getLambda()/0.69314718);
	  arrivee.tirage(this.repetition);
	  List<Double> listArrivee = arrivee.getListTireeCumulee();
	  
	  // Creation loi et tirage TRAITEMENT
	  Loi machineACafe = new LoiExponentielle(1);
	  machineACafe.tirage(this.repetition);
      List<Double> listTraitement = machineACafe.getListTiree();
	  
	  double time = 0.0;
	  
      //Creation d'une liste de sortie des utilisateurs
      List<Double> sortie = new ArrayList<Double>();
      time = 0.0;
      
      for(int i = 0; i < listTraitement.size(); i++) {
    	  if( time < 120) {
    		      		  
    		  if ( listArrivee.get(i) + listTraitement.get(i) < time ) {
    			  time = time + listTraitement.get(i);
    			  sortie.add(time); 
    		  
    		  }   else {
    			  time = listArrivee.get(i) + listTraitement.get(i);
    			  sortie.add(time);
    		  }

        	  System.out.println("time sortie " + i + " : " + time);  
    	  }
    	  else { 
    		  i = listTraitement.size();
    	  }
      }
	  
      // Mise en place du graph
	  int file = 0;
	  serie.add( 0.0, 0.0 );
	  fileAttente.add( 0.0 , 0.0 );
      
	  int cptA = 0; //Compteur index Arrivee
	  int cptS = 0; //Compteur index Sortie
	  
      do {  // Entrees et sorties du système
    	  
    	  if(listArrivee.get(cptA)<sortie.get(cptS)) {
    		// Entree dans le système
    		  file++;
    		  time = listArrivee.get(cptA);
    		  fileAttente.add( time - 0.01 , file - 1.0 );
        	  fileAttente.add( time , file );
        	// Ajout d'une arrivee
    	      serie.add( time - 0.01, 0.0 );
    	      serie.add( time + 0.0, 1.0 );
    	      serie.add( time + 0.01, 0.0 );
    	    
    	      cptA++;
    	      
    	  } else {
    		// Sortie du système
    		  file--;
    		  time = sortie.get(cptS);
    		  fileAttente.add( time - 0.01 , file + 1.0 );
        	  fileAttente.add( time , file );
        	  
        	  cptS++;
    	  }
      
      } while ( time < 120 );
      
      // // Calcul du temps moyen dans le système
      double moyenneTAS = 0.0;
      for ( int i = 0; i < cptS; i++) {
    	  moyenneTAS += sortie.get(i) - listArrivee.get(i);
      } 
      moyenneTAS /= cptS;
      System.out.println("Temps moyen dans le système : " + moyenneTAS + " minutes.");  
      
      // Calcul de la moyenne de temps entre evenement
      double moyenneIntervalle = listArrivee.get(0);
      for ( int i = 1; i < cptA; i++) {
    	  moyenneIntervalle += listArrivee.get(i)-listArrivee.get(i-1);
      } 
      moyenneIntervalle /= cptA;
      System.out.println("Temps moyen entre évènements : " + moyenneIntervalle + " minutes.");
      
      
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
