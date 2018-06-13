/*
import javax.swing.*;
import java.awt.*;

public class ArrowLineExample2 extends JPanel {

    int x1;
    int y1;
    int x2;
    int y2;
    int d;
    int h;

    public ArrowLineExample2(int x1, int y1, int x2, int y2, int d, int h) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.d = d;
        this.h = h;

//        paint(getGraphics());
    }


    public ArrowLineExample2(Point p1, Point p2) {
        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
        this.d = 10;
        this.h = 10;

//        paint(getGraphics());

    }


    public void paintComponent(Graphics g) {

        System.out.println("송지원 : ArrowLineExample2 paintComponent() 호출");

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g2.drawLine(x1, y1, x2, y2);
        g2.fillPolygon(xpoints, ypoints, 3);
    }

    public void paint(Graphics g) {

        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

//    public static void main(String [] args) {
//
//        ArrowLineExample2 arrowLineExample2 = new ArrowLineExample2(100,100, 200, 200, 10,10);
//
//        JFrame jFrame = new JFrame();
//        jFrame.add(arrowLineExample2);
//        jFrame.setSize(1000, 1000);
//        jFrame.setVisible(true);
//    }
}
*/
