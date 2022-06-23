package barChartArray;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BarChartPanel extends JPanel{
	// variables for bar drawing
	private Color[] colors =
		{Color.blue,Color.red,Color.green,Color.yellow,Color.cyan,Color.pink,Color.magenta};
	private int[] barValues;
	private String[] barLabels;
	private Rectangle[] bars;
        private String[] info;
	private int nbrBars;

	public BarChartPanel(int[] values, String[] labels, String[] info, int nbr){ 
                setValuesAndLabels(values, labels, nbr);

                
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
                                for (int i=0; i<bars.length; i++){
                                    Rectangle r = bars[i];
                                    if (r.contains(x,y)){
                                        JOptionPane.showMessageDialog(null, info[i], labels[i] + " details", HEIGHT);
                                    }                                    
                                }
			}
		});                
	}
        
        public void setValuesAndLabels(int[] values, String[] labels, int nbr){
            
		barValues = values;
		barLabels = labels;
		nbrBars = nbr;

		for (int i=0;i<nbrBars;i++){
			barValues[i] = values[i]; 
		}

		bars = new Rectangle[barValues.length];
		for (int i=0;i<bars.length;i++)
			bars[i] = new Rectangle();
                repaint();

        }

	public int getNbrBars(){
		return nbrBars;
	}

	public int getMaxBars(){
		return barValues.length;
	}

       	public int getMinBars(){
		return 1;
	}

	public int getBarValue(int idx){
		return barValues[idx];
	}

        public void addBar(int val){
          if (nbrBars<getMaxBars())
            barValues[nbrBars++] = val;
            repaint();
        }

        public void removeBar(){
          if (nbrBars>=getMinBars()){
              barValues[--nbrBars] = 0;
              //nbrBars = nbrBars - 1;
              }
            repaint();
        }

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// get rectangle bounding the graphic region
		Rectangle r = getBounds();

		// r2 will be the region of the bar chart
		Rectangle r2 =
			new Rectangle(r.x+20,r.y+20,r.width-20,r.height-60);

		// set the bar width based on width of
		// chart and number of bars in chart
		int bar_width = (r2.width-r2.x)/nbrBars;

		// find the largest number in the array
		float largest=-1000;
		for (int i=0;i<nbrBars;i++)
			largest=Math.max(largest,barValues[i]);

		// draw bars based on size relative to largest...
		// largest value will be full height, and all
		// others will be relative to the largest

		for (int i=0;i<nbrBars;i++){
			float barValue = barValues[i];
			float ratio = barValue/largest;
			int height = (int) ((r2.height - r2.y) * ratio);
			bars[i].x = r2.x+bar_width*i;
			bars[i].width = bar_width;
			bars[i].y =	r2.height - height;
			bars[i].height=r2.height-bars[i].y;

			g.setColor(colors[i%colors.length]);
                        g.fillRect(bars[i].x,bars[i].y,bars[i].width,bars[i].height);
                        
                        g.setColor(Color.black);
                        g.drawString(barLabels[i], bars[i].x + (bar_width/3), r.height-40);
                        g.drawString(Integer.toString(barValues[i]), bars[i].x + (bar_width/3), bars[i].y+20);
                        
		}

		// draw x-axis and y-axis
		g.setColor(Color.black);
		g.drawLine(r2.x,r2.height,r2.width,r2.height);
		g.drawLine(r2.x,r2.y,r2.x,r2.height);


	}
}
