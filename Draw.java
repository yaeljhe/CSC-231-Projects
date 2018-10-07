/******************************************************************
 *  Name:				Yael Hernandez
 *  COURSE:             	CSC231 Computer Science and Programming II
 *	Lab:			    		Number 7
 *	FILE:				Draw.java
 *	TARGET:				Java 6.0 and 7.0
 *****************************************************************/

// Import Core Java packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Draw extends JFrame implements ActionListener, ItemListener {

	// Initial Frame size
	static final int WIDTH = 550;                // frame width
	static final int HEIGHT = 450;               // frame height

    // Color choices
    static final String COLOR_NAMES[] = {"None", "Red", "Blue", "Green"};
    static final Color COLORS[] = {null, Color.red, Color.blue, Color.green};
    

    // Button control
    JButton circle;
    JButton roundRec;
    JButton threeDRec;
    JButton line;
    JButton square;
    JButton oval;
    JButton remove;

    // Color choice box
    JComboBox<String> colorChoice = new JComboBox<String>(COLOR_NAMES);

    // the canvas
    DrawCanvas canvas;
    
    // list 
    static JList<String>List;

    /**
     * Constructor
     */
	public Draw() {
	    super("Java Draw");
        setLayout(new BorderLayout());

        // create panel for controls
        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        add(topPanel, BorderLayout.NORTH);

        // create button control
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(buttonPanel);

        circle = new JButton("Circle");
        buttonPanel.add(circle);
        roundRec = new JButton("Rounded Rectangle");
        buttonPanel.add(roundRec);
        threeDRec = new JButton("3D Rectangle");
        buttonPanel.add(threeDRec);
        
        // create bottom button control
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(bottomPanel);
        line = new JButton("Line");
        bottomPanel.add(line);
        square = new JButton("Square");
        bottomPanel.add(square);
        oval = new JButton("Oval");
        bottomPanel.add(oval);

        // add button listener
        circle.addActionListener(this);
        roundRec.addActionListener(this);
        threeDRec.addActionListener(this);
        line.addActionListener(this);
        square.addActionListener(this);
        oval.addActionListener(this);
        
        JPanel listPanel = new JPanel(new BorderLayout());
        add(listPanel, BorderLayout.CENTER);
        JLabel label2 = new JLabel("Name List", JLabel.LEFT);
        listPanel.add(label2, BorderLayout.NORTH);
        

        // create panel for color choices
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(colorPanel);
        JLabel label = new JLabel("Filled Color:");
        colorPanel.add(label);
        colorChoice = new JComboBox<String>();
        for(int i=0; i<COLOR_NAMES.length; i++) {
            colorChoice.addItem(COLOR_NAMES[i]);
        }
        colorPanel.add(colorChoice);
        colorChoice.addItemListener(this);
        	
        	//panel for list
        JPanel leftPanel = new JPanel(new GridLayout(2,1));
        add(leftPanel, BorderLayout.WEST);
        List = new JList<>(canvas.shapeList);
        leftPanel.add(List);
        
        // create remove button
        JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(removePanel);
        remove = new JButton("Remove");
        removePanel.add(remove);
        remove.addActionListener(this);
        
        // create the canvas
        canvas = new DrawCanvas();
        add(canvas, BorderLayout.CENTER);
               
	} // end of constructor


    /**
     *  Implementing ActionListener
     */
    public void actionPerformed(ActionEvent event) {
    		int selectedItem;
    		int selectedIndex;
    		
        if(event.getSource() == circle) {  // circle button
            canvas.setShape(DrawCanvas.CIRCLE);
        }
        else if(event.getSource() == roundRec) {  // rounded rectangle button
        		canvas.setShape(DrawCanvas.ROUNDED_RECTANGLE);
        }
        else if(event.getSource() == threeDRec) { // 3D rectangle button
        		canvas.setShape(DrawCanvas.RECTANGLE_3D);
        }
        else if(event.getSource() == line) { // line button
        		canvas.setShape(DrawCanvas.LINE);
        }
        else if(event.getSource() == square) { // square button
        		canvas.setShape(DrawCanvas.SQUARE);
        }
        else if(event.getSource() == oval) {  // oval button
        		canvas.setShape(DrawCanvas.OVAL);
        }else if(event.getSource()== remove) {
        		selectedIndex = List.getSelectedIndex();
        		if(selectedIndex >= 0) {     			
        		}
        }
    }

    /**
     * Implementing ItemListener
     */
    public void itemStateChanged(ItemEvent event) {
        Color color = COLORS[colorChoice.getSelectedIndex()];
        canvas.setFilledColor(color);
    }
    
    public void actionPerformed1(ActionEvent arg0) {
    	DefaultListModel<String> shapes = new DefaultListModel<String>();
    		shapes.addElement("Hello");   		
    }
    
    public void removeShape(int shape) {
    		List.remove(shape);
    }

    /**
     * the main method
     */
    public static void main(String[] argv) {
        // Create a frame
        Draw JFrame = new Draw();
        JFrame.setSize(WIDTH, HEIGHT);
        JFrame.setLocation(150, 100);

        // add window closing listener
        JFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        // Show the frame
        JFrame.setVisible(true);
    }
}
