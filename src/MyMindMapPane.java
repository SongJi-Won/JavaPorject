import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class MyMindMapPane extends JPanel {

    private JLabel title;
    private JPanel board;

    private Graphics g;
    private MyComponentAdapter myComponentAdapter;

    private MyContainerAdapter myContainerAdapter;

    private Point[] points;



    public MyMindMapPane()
    {
        this.title = new JLabel("Mind Map Pane");
//        title.setSize(200, 40);

        this.board = new JPanel();
//        board.setSize(400, 400);
        this.g = getGraphics();
        this.myComponentAdapter = new MyComponentAdapter(this, g);
        this.myContainerAdapter = new MyContainerAdapter(this, g);

        this.addContainerListener(this.myContainerAdapter);


        //this.setLayout(null); //송지원 여기 나중에 노드 들어야 하는 부분이라 일단 레이아웃 없앴다.
        this.setLayout(new BorderLayout());



        this.add(title, BorderLayout.NORTH);

        this.add(board, BorderLayout.CENTER);


    }

    public void addNode(Node newNode){

        Node parrentNode = newNode.getParentNode();

        if (parrentNode != null) {

            this.repaint();
        }
        newNode.addComponentListener(this.myComponentAdapter);

        this.add(newNode);

        repaint();
    }







    public Point[] getShortestPoint(Node node1, Node node2)
    {
        Double distance;
        Double tempdis;
        Point [] returnPoints = new Point[2];

        Point p11 = new Point(node1.getNodeX(), node1.getNodeY()+node1.getNodeH()/2);
        Point p12 = new Point(node1.getNodeX()+node1.getNodeW()/2, node1.getNodeY());
        Point p13 = new Point(node1.getNodeX()+node1.getNodeW(), node1.getNodeY()+node1.getNodeH()/2);
        Point p14 = new Point(node1.getNodeX()+node1.getNodeW()/2, node1.getNodeY()+node1.getNodeH());

        Point[] p1 = new Point[4];
        p1[0] = p11;
        p1[1] = p12;
        p1[2] = p13;
        p1[3] = p14;


        Point p21 = new Point(node2.getNodeX(), node2.getNodeY()+node2.getNodeH()/2);
        Point p22 = new Point(node2.getNodeX()+node2.getNodeW()/2, node2.getNodeY());
        Point p23 = new Point(node2.getNodeX()+node2.getNodeW(), node2.getNodeY()+node2.getNodeH()/2);
        Point p24 = new Point(node2.getNodeX()+node2.getNodeW()/2, node2.getNodeY()+node2.getNodeH());

        Point[] p2 = new Point[4];
        p2[0] = p21;
        p2[1] = p22;
        p2[2] = p23;
        p2[3] = p24;

        distance = getDistance(p1[0], p2[0]);
        returnPoints[0] = p1[0];
        returnPoints[1] = p2[0];

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {

                tempdis = getDistance(p1[i], p2[j]);

                if (distance > tempdis){

                    distance = tempdis;
                    returnPoints[0] = p1[i];
                    returnPoints[1] = p2[j];
                }

            }
        }

        return returnPoints;
    }


    public Double getDistance(Point p1, Point p2) {

        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;

        Double distance = Math.sqrt(dx*dx*1.0 + dy*dy*1.0);

        return distance;
    }








    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        Component[] components = this.getComponents();
        int numOfComponents = this.getComponentCount();

        for (int i=0; i < numOfComponents; i++) {
            Node parentNode;
            Node curNode;

            if (components[i] instanceof Node) {

                curNode = (Node)components[i];

                if (curNode.getParentNode() != null) {

                    parentNode = curNode.getParentNode();

                    points = getShortestPoint(curNode, parentNode);

                    int x1 = points[0].x;
                    int y1 = points[0].y;
                    int x2 = points[1].x;
                    int y2 = points[1].y;
                    int d = 10;
                    int h = 10;



                    int dx = x2 - x1, dy = y2 - y1;
                    double D = Math.sqrt(dx * dx + dy * dy);
                    double xm = D - d, xn = xm, ym = h, yn = -h, x;
                    double sin = dy / D, cos = dx / D;

                    x = xm * cos - ym * sin + x1;
                    ym = xm * sin + ym * cos + y1;
                    xm = x;

                    x = xn * cos - yn * sin + x1;
                    yn = xn * sin + yn * cos + y1;
                    xn = x;

                    int[] xpoints = {x2, (int) xm, (int) xn};
                    int[] ypoints = {y2, (int) ym, (int) yn};

                    g2.drawLine(x1, y1, x2, y2);
                    g2.fillPolygon(xpoints, ypoints, 3);
                }
            }
        }
    }









    class MyComponentAdapter extends ComponentAdapter {

        MyMindMapPane myMindMapPane;
        Graphics g;

        Node curNode;
        Node parrentNode;

        Point[] shortestPoints;

        public MyComponentAdapter(MyMindMapPane myMindMapPane, Graphics g) {
            super();
            this.myMindMapPane = myMindMapPane;
            this.g = g;
        }


        @Override
        public void componentResized(ComponentEvent e) {

//            paintComponent(g);
////            System.out.println("송지원 : MyMindMapPane MyComponentAdapter componentResized() 호출");
////            if (e.getSource() instanceof Node) {
////
////                curNode = (Node) e.getSource();
////                parrentNode = curNode.getParentNode();
////
////                if (parrentNode != null) {
////
////                    shortestPoints = getShortestPoint(curNode, parrentNode);
////                    arrowLineExample2 = new ArrowLineExample2(shortestPoints[0], shortestPoints[1]);
////
////                    myMindMapPane.add(arrowLineExample2);
////                    myMindMapPane.repaint();
////                }
////            }

            repaint();

        }


        @Override
        public void componentMoved(ComponentEvent e) {

//            paintComponent(g);
////            System.out.println("송지원 : MyMindMapPane MyComponentAdapter componentMoved() 호출");
////            if (e.getSource() instanceof Node) {
////
////                curNode = (Node) e.getSource();
////                parrentNode = curNode.getParentNode();
////
////                if (parrentNode != null) {
////
////                    shortestPoints = getShortestPoint(curNode, parrentNode);
////                    arrowLineExample2 = new ArrowLineExample2(shortestPoints[0], shortestPoints[1]);
////
////                    myMindMapPane.add(arrowLineExample2);
////                    myMindMapPane.repaint();
////                }
////            }

            repaint();
        }
    }














    class MyContainerAdapter extends ContainerAdapter {

        MyMindMapPane myMindMapPane;
        Graphics g;

        Node curNode;
        Node parrentNode;

        Point[] shortestPoints;

        public MyContainerAdapter(MyMindMapPane myMindMapPane, Graphics g) {
            this.myMindMapPane = myMindMapPane;
            this.g = g;
        }


        @Override
        public void componentAdded(ContainerEvent e) {

//            paintComponent(g);
////            System.out.println("송지원 : MyMindMapPane MyContainerAdapter componentAdded() 호출");
////            if (e.getSource() instanceof Node) {
////
////                curNode = (Node) e.getSource();
////                parrentNode = curNode.getParentNode();
////
////                if (parrentNode != null) {
////
////                    shortestPoints = getShortestPoint(curNode, parrentNode);
////                    arrowLineExample2 = new ArrowLineExample2(shortestPoints[0], shortestPoints[1]);
////
////                    myMindMapPane.add(arrowLineExample2);
////                    myMindMapPane.repaint();
////                }
////            }

            repaint();
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            super.componentRemoved(e);
        }
    }

}
