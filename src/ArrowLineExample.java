import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ArrowLineExample extends JFrame {


    public ArrowLineExample() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(2000, 1000);
        setVisible(true);

        Point p1 = new Point(200, 300);
        Point p2 = new Point(40, 700);
        createArrowShape(p1, p2);



        }

    public static void main(String [] args) {

        new ArrowLineExample();
    }













    public static Shape createArrowShape(Point fromPt, Point toPt) {
        Polygon arrowPolygon = new Polygon();
        arrowPolygon.addPoint(-6,1);
        arrowPolygon.addPoint(3,1);
        arrowPolygon.addPoint(3,3);
        arrowPolygon.addPoint(6,0);
        arrowPolygon.addPoint(3,-3);
        arrowPolygon.addPoint(3,-1);
        arrowPolygon.addPoint(-6,-1);


        Point midPoint = midpoint(fromPt, toPt);

        double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

        AffineTransform transform = new AffineTransform();
        transform.translate(midPoint.x, midPoint.y);
        double ptDistance = fromPt.distance(toPt);
        double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
        transform.scale(scale, scale);
        transform.rotate(rotate);

        return transform.createTransformedShape(arrowPolygon);
    }

    private static Point midpoint(Point p1, Point p2) {
        return new Point((int)((p1.x + p2.x)/2.0),
                (int)((p1.y + p2.y)/2.0));
    }
















    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
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
