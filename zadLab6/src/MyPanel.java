import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class MyPanel extends Panel implements MouseListener, MouseMotionListener, KeyListener {
    private LinkedList<Shape> shapes;

    int mousePosX;
    int mousePosY;

    MyPanel() {
        shapes = new LinkedList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    public void addShape(Shape s) {
        shapes.add(s);
    }

    @Override
    public void paint(Graphics arg0) {
        for(Shape s : shapes)
            s.draw(arg0);
    }
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {

        int dx = e.getX() - mousePosX;
        int dy =  e.getY() - mousePosY;

        for(Shape s : shapes) {
            if(s.mouseOver(e.getX(), e.getY())) {

                s.setX(s.getX() + dx);
                s.setY(s.getY() + dy);

                int index = shapes.indexOf(s);
                shapes.remove(index);
                shapes.add(0, s);

                this.repaint();
                break;
            }
        }

        mousePosX = e.getX();
        mousePosY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosX = e.getX(); mousePosY = e.getY();
    }


    public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    public void keyReleased(KeyEvent evt) { }

    public void keyTyped(KeyEvent evt) { }

}
