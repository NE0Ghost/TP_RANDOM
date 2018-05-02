package Generation;

import java.awt.Color;
import java.util.List;
import java.awt.BasicStroke; 

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
	
	public final int repetition = 20; 

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
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( ) {
      final XYSeries serie = new XYSeries( "Evenement" );
      
      Loi uniforme = new LoiPoisson(20);
	  uniforme.tirage(this.repetition);		
	  List<Double> list = uniforme.getListTiree();
	  
	  double time = 0.0;
	  
	  serie.add( 0.0, 0.0 );
      
      for(int i=0; i<list.size(); i++) {
    	  time+=list.get(i); 
    	  serie.add( time - 0.01, 0.0 );
    	  serie.add( time + 0.0, 1.0 );
    	  serie.add( time + 0.01, 0.0 );
    	  System.out.println("Temps " + list.get(i));
      }
      
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( serie );          
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
