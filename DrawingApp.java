import java.awt.*;
import java.awt.event.*;

public class DrawingApp extends Frame implements ActionListener, MouseListener {

    Button red, blue, circle, rect;
    String shape = "Circle";
    Color color = Color.RED;

    int x = 100, y = 100;

    DrawingApp() {

        // Frame settings
        setTitle("Drawing Application");
        setSize(500, 500);
        setLayout(new FlowLayout());

        // Buttons
        red = new Button("Red");
        blue = new Button("Blue");
        circle = new Button("Circle");
        rect = new Button("Rectangle");

        // Add buttons
        add(red);
        add(blue);
        add(circle);
        add(rect);

        // Action events
        red.addActionListener(this);
        blue.addActionListener(this);
        circle.addActionListener(this);
        rect.addActionListener(this);

        // Mouse event
        addMouseListener(this);

        setVisible(true);
    }

    // Button actions
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == red) {
            color = Color.RED;
        }

        else if (e.getSource() == blue) {
            color = Color.BLUE;
        }

        else if (e.getSource() == circle) {
            shape = "Circle";
        }

        else if (e.getSource() == rect) {
            shape = "Rectangle";
        }

        repaint();
    }

    // Drawing shapes
    public void paint(Graphics g) {

        g.setColor(color);

        // Font
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Simple Drawing App", 130, 80);

        if (shape.equals("Circle")) {
            g.fillOval(x, y, 100, 100);
        }

        else if (shape.equals("Rectangle")) {
            g.fillRect(x, y, 120, 80);
        }
    }

    // Mouse click
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        repaint();
    }

    // Unused mouse methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new DrawingApp();
    }
}