/******************************************************************
 *  COURSE:             CSC231 Computer Science and Programming II
 *	Lab:			    Number 4
 *	FILE:				DrawCanvas.java
 *	TARGET:				Java 6.0 and 7.0
 *****************************************************************/

// Import Core Java packages
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class DrawCanvas extends Canvas implements MouseListener,
                                                  MouseMotionListener {

    // Constants for shapes
    public static final int CIRCLE = 1;
    public static final int ROUNDED_RECTANGLE = 2;
    public static final int RECTANGLE_3D = 3;
    public static final int LINE = 4;
    public static final int SQUARE = 5;
    public static final int OVAL = 6;
    public static final int CLEAR = 7;
    
    //list of shapes
    ArrayList<Shape>Shapes = new ArrayList<>();
    static DefaultListModel<String>shapeList = new DefaultListModel<>();

    // Coordinates of points to draw
    private int x1, y1, x2, y2;

    // shape to draw
    private int shape = CIRCLE;
    /**
     * Method to set the shape
     */
    public void setShape(int shape) {
        this.shape = shape;
    }

    // filled color
    private Color filledColor = null;
    /**
     * Method to set filled color
     */
    public void setFilledColor(Color color) {
        filledColor = color;
    }

    /**
     * Constructor
     */
	public DrawCanvas() {
	    addMouseListener(this);
	    addMouseMotionListener(this);
	} // end of constructor

    /**
     * painting the component
     */
    public void paint(Graphics g) {

        // the drawing area
        int x, y, width, height;

        // determine the upper-left corner of bounding rectangle
        x = Math.min(x1, x2);
        y = Math.min(y1, y2);
        
        for(int i = 0; i < Shapes.size(); i++) {
    		Shape shapes = Shapes.get(i);
    

        // determine the width and height of bounding rectangle
        width = Math.abs(x1 - x2);
        height = Math.abs(y1 - y2);
        
            
        if(shapes.color != null)
            g.setColor(shapes.color);
        switch (shapes.shape) {
            case ROUNDED_RECTANGLE :
                if(shapes.color == null)
                    g.drawRoundRect(x, y, width, height, width/4, height/4);
                else
                    g.fillRoundRect(x, y, width, height, width/4, height/4);
                break;
            case CIRCLE :
                int diameter = Math.max(width, height);
                if(shapes.color == null)
                    g.drawOval(x, y, diameter, diameter);
                else
                    g.fillOval(x, y, diameter, diameter);
                break;
            case RECTANGLE_3D :
                if(shapes.color == null)
                    g.draw3DRect(x, y, width, height, true);
                else
                    g.fill3DRect(x, y, width, height, true);
                break;
            case LINE :
                if(shapes.color == null)
                    g.drawLine(x1, y1, x2, y2);
                break;
            case SQUARE :
                if(shapes.color == null)
                    g.draw3DRect(x, y, width, width, true);
                else
                    g.fill3DRect(x, y, width, width, true);
                break;
            case OVAL :
                if(shapes.color == null)
                    g.drawOval(x, y, width, height);
                else
                    g.fillOval(x, y, width, height);
                break;
        }
      }
    }

    /**
     * Implementing MouseListener
     */
    public void mousePressed(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();
        Shape shapes = new Shape();
        shapes.color = filledColor;
        shapes.shape = shape;
        shapes.x1 = x1;
        shapes.y1 = y1;
        Shapes.add(shapes);
    }

    public void mouseReleased(MouseEvent event) {
        x2 = event.getX();
        y2 = event.getY();
        Shapes.get(Shapes.size()-1).x2 = x2;
        Shapes.get(Shapes.size()-1).y2 = y2;
        repaint();
        shapeList.addElement(Shapes.get(Shapes.size()-1).coordinates());
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    /**
     * Implementing MouseMotionListener
     */
    public void mouseDragged(MouseEvent event) {
        x2 = event.getX();
        y2 = event.getY();
        Shapes.get(Shapes.size()-1).x2 = x2;
        Shapes.get(Shapes.size()-1).y2 = y2;
        repaint();
    }

    public void mouseMoved(MouseEvent e) {}
    
    
    // class for shape
    class Shape{
	    	int x1,x2,y1,y2, shape;
	    	Color color;
	    	
	    	String coordinates() {
	    		int x = Math.min(x1, x2);
	    		int y = Math.min(y1, y2);
	    		return  shapeName(shape) +"   "+ "X: " + x  + " "+ "Y: " + y;
	    	}
	    	
	    	String shapeName(int n){
	    		String shapes = " ";
	    		switch(n) {
	    		case 1:
	        		shapes = "Circle";
	        		break;
	        	case 2:
	        		shapes = "Rounded Rectangle";
	        		break;
	        	case 3:
	        		shapes = "3D Rectangle";
	        		break;
	        	case 4:
	        		shapes = "Line";
	        		break;
	        	case 5:
	        		shapes = "Square";
	        		break;
	        	case 6:
	        		shapes = "Oval";
	        		break;
	        	}
	        	return shapes;
	    	}
    	}
}
