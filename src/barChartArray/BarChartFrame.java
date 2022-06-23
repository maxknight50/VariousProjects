
package barChartArray;
import javax.swing.*;
import java.awt.*;



public class BarChartFrame extends JFrame{
    
    BarChartPanel bcp;
    public BarChartFrame(String title, int[] values, String[] labels, String[] info) {
        
        bcp = new BarChartPanel(values, labels, info, values.length);
        getContentPane().add(bcp, BorderLayout.CENTER);        
	setSize(800,400);
        this.setTitle(title);
	show();  
        
    }
    
}
