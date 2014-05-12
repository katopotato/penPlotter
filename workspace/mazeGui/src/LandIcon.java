import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.*;

/**
   An icon that contains a moveable shape.
*/
public class LandIcon implements Icon
{
   public LandIcon(int height)
   {
      this.height = height;
   }
   
   public int getIconWidth()
   {
      return width;
   }

   public int getIconHeight()
   {
      return height;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
	   Rectangle2D.Double body
       = new Rectangle2D.Double(0, 0, width, width);
	   g2.setColor(Color.GREEN);
	   g2.draw(body);
	   g2.fill(body);
   }

   private static final int width = 50;
   private int height;
}


