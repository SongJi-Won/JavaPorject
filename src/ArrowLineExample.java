import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ArrowLineExample extends JFrame {


    public ArrowLineExample() {

        Arrow arrow = new Arrow(100, 100, 200, 300, 100, 100);

        add(arrow);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 900);
        setVisible(true);

        }

    public static void main(String [] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                ArrowLineExample arrowLineExample = new ArrowLineExample();
                arrowLineExample.setVisible(true);
            }
        });

    }












//
//    public static Shape createArrowShape(Point fromPt, Point toPt) {
//        Polygon arrowPolygon = new Polygon();
//        arrowPolygon.addPoint(-6,1);
//        arrowPolygon.addPoint(3,1);
//        arrowPolygon.addPoint(3,3);
//        arrowPolygon.addPoint(6,0);
//        arrowPolygon.addPoint(3,-3);
//        arrowPolygon.addPoint(3,-1);
//        arrowPolygon.addPoint(-6,-1);
//
//
//        Point midPoint = midpoint(fromPt, toPt);
//
//        double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);
//
//        AffineTransform transform = new AffineTransform();
//        transform.translate(midPoint.x, midPoint.y);
//        double ptDistance = fromPt.distance(toPt);
//        double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
//        transform.scale(scale, scale);
//        transform.rotate(rotate);
//
//        return transform.createTransformedShape(arrowPolygon);
//    }
//
//    private static Point midpoint(Point p1, Point p2) {
//        return new Point((int)((p1.x + p2.x)/2.0),
//                (int)((p1.y + p2.y)/2.0));
//    }





//    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
//        int dx = x2 - x1, dy = y2 - y1;
//        double D = Math.sqrt(dx*dx + dy*dy);
//        double xm = D - d, xn = xm, ym = h, yn = -h, x;
//        double sin = dy / D, cos = dx / D;
//
//        x = xm*cos - ym*sin + x1;
//        ym = xm*sin + ym*cos + y1;
//        xm = x;
//
//        x = xn*cos - yn*sin + x1;
//        yn = xn*sin + yn*cos + y1;
//        xn = x;
//
//        int[] xpoints = {x2, (int) xm, (int) xn};
//        int[] ypoints = {y2, (int) ym, (int) yn};
//
//        g.drawLine(x1, y1, x2, y2);
//        g.fillPolygon(xpoints, ypoints, 3);
//    }
}


class Arrow extends JPanel {

    int x1;
    int y1;
    int x2;
    int y2;
    int d;
    int h;

    public Arrow(int x1, int y1, int x2, int y2, int d, int h) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.d = d;
        this.h = h;
    }
    @Override
    protected void printComponent(Graphics g) {
        super.printComponent(g);

//        Graphics2D g2 = (Graphics2D)g;

        g.setColor(Color.black);

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
}
