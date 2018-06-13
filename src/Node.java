import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Node extends JLabel {

    private  int id;

    private   String textOfNode;
    private  int nodeX;
    private  int nodeY;
    private   Point point;


    private  int nodeW;
    private  int nodeH;
    private  Dimension dimension;


    private  Color color;

    private  int level;



    private double theta;
//    Node child;


    private transient Node  parent;
    private transient Node child;


    private JSplitPaneTest jSplitPaneTest;

    private   MyAttributePane parentAttributePane;





    public Node(int id, String text, int x, int y, int w, int h, Color color, int level, Node parent, JSplitPaneTest jSplitPaneTest, MyAttributePane myAttributePane) {
        super(text);
        this.setMinimumSize(new Dimension(300, 100));//송지원 이거 적용안되는듯...;;
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(CENTER);

        this.id = id;

        this.textOfNode = text;

        this.nodeX = x;
        this.nodeY = y;
        this.point = new Point(x, y);
        setLocation(this.point);


        this.nodeW = w;
        this.nodeH = h;
        this.dimension = new Dimension(w, h);
        setSize(this.dimension);

        this.color = color;
        this.setBackground(this.color);

        this.level = level;

        this.parent = parent;
        this.theta = Math.PI;
        this.jSplitPaneTest = jSplitPaneTest;

        this.parentAttributePane = myAttributePane;


        setFont(new Font("돋움", Font.BOLD, 20));
        setOpaque(true);


        NodeResizeAdapter r = new NodeResizeAdapter(this);
        this.addMouseMotionListener(r);
        this.addMouseListener(r);
    }



    public int getId() {
        return id;
    }

    public int getNodeX() {
        return this.nodeX;
    }

    public int getNodeY() {
        return this.nodeY;
    }

    public Point getPoint() {
        return this.point;
    }


    public int getNodeW() {
        return this.nodeW;
    }

    public int getNodeH() {
        return this.nodeH;
    }

    public Dimension getDimension() {
        return this.dimension;
    }


    public int getLevel() {
        return this.level;
    }

    public Color getColor() {
        return color;
    }
    public Node getParentNode() {
        return parent;
    }



    public double getTheta() { return theta; }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setNodeX(int nodeX) {
        this.nodeX = nodeX;
    }

    public void setNodeY(int nodeY) {
        this.nodeY = nodeY;
    }

    public void setNodeW(int nodeW) { this.nodeW = nodeW; }

    public void setNodeH(int nodeH) { this.nodeH = nodeH; }

    public void setPoint(Point point) { this.point = point; }

    public void setDimension(Dimension dimension) { this.dimension = dimension; }

    public void setColor(Color color) { this.color = color; }

    public void setTheta(double theta) { this.theta = theta; }

    public void update(Point point, Dimension dimension) {
        this.point = point;
        this.nodeX = point.x;
        this.nodeY = point.y;

        this.dimension = dimension;
        this.nodeW = dimension.width;
        this.nodeH = dimension.height;
    }










    class NodeResizeAdapter extends MouseAdapter {

        Node component;

        //resizing 관련 필드
        boolean pressed_r = false;
        boolean atLine = false;
        final int PROX_DIST = 3;

        //moving 관련 필드
        boolean pressed_m = false;
        boolean atInside = false;
        int pressedX;
        int pressedY;
        int releasedX;
        int releasedY;

        Node pressedComponent;
        int componentX;
        int componentY;
        int componentW;
        int componentH;


        public NodeResizeAdapter(Node n) {
            component = n;
        }


        @Override
        public void mouseClicked(MouseEvent e) {
            String colorcode;
            int red = component.getColor().getRed();
            int green = component.getColor().getGreen();
            int blue = component.getColor().getBlue();

            //이유는 모르겠는데 주석처리 되있음
//            parentAttributePane.setSelectedNode((Node)e.getSource());
//            parentAttributePane.getTextAttribute().setAttrValue(component.textOfNode);
//            parentAttributePane.getXAttribute().setAttrValue(""+component.getNodeX());
//            parentAttributePane.getYAttribute().setAttrValue(""+component.getNodeY());
//            parentAttributePane.getWAttribute().setAttrValue(""+component.getNodeW());
//            parentAttributePane.getHAttribute().setAttrValue(""+component.getNodeH());
//
//            parentAttributePane.setSelectedNode((Node)e.getSource());
//            parentAttributePane.getTextAttribute().setAttrValue(component.text);
//            parentAttributePane.getXAttribute().setAttrValue(""+component.getNodeX());
//            parentAttributePane.getYAttribute().setAttrValue(""+component.getNodeY());
//            parentAttributePane.getWAttribute().setAttrValue(""+component.getNodeW());
//            parentAttributePane.getHAttribute().setAttrValue(""+component.getNodeH());


            colorcode = String.format("%02x%02x%02x", component.getColor().getRed(), component.getColor().getGreen(),component.getColor().getBlue());
            parentAttributePane.getColorAttribute().setAttrValue(colorcode);

            component.setBackground(new Color(255-red, 255-green, 255-blue));

            jSplitPaneTest.setClickedNode(component);

        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (component.getCursor() != Cursor.getDefaultCursor()) {

                pressed_r = true;
            }


            pressed_m = true;
            pressedX = e.getX();
            pressedY = e.getY();

            pressedComponent = (Node) e.getComponent();
            componentX = pressedComponent.getX();
            componentY = pressedComponent.getY();
            componentW = pressedComponent.getWidth();
            componentH = pressedComponent.getHeight();

            Rectangle inside = new Rectangle(componentX + 2, componentY + 2, componentW - 4, componentH - 4);

            atInside = inside.contains(e.getLocationOnScreen());
            atInside = inside.contains(new Point(componentX + e.getX(), componentY + e.getY()));
        }


        @Override
        public void mouseReleased(MouseEvent e) {

            pressed_r = false;

            if (atInside == true) {

                releasedX = e.getX();
                releasedY = e.getY();

                int changedX = releasedX - pressedX;
                int changedY = releasedY - pressedY;

                if (pressed_m == true) {
                    Point newPoint = new Point(componentX + changedX, componentY + changedY);
                    pressedComponent.setLocation(newPoint);
                    pressedComponent.update(newPoint, pressedComponent.getDimension());
                }
            }

            pressed_m = false;
            atInside = false;

            String colorcode;

            parentAttributePane.setSelectedNode((Node)e.getSource());
            parentAttributePane.getTextAttribute().setAttrValue(component.textOfNode);
            parentAttributePane.getXAttribute().setAttrValue(""+component.getNodeX());
            parentAttributePane.getYAttribute().setAttrValue(""+component.getNodeY());
            parentAttributePane.getWAttribute().setAttrValue(""+component.getNodeW());
            parentAttributePane.getHAttribute().setAttrValue(""+component.getNodeH());

            colorcode = String.format("%02x%02x%02x", component.getColor().getRed(), component.getColor().getGreen(),component.getColor().getBlue());
            parentAttributePane.getColorAttribute().setAttrValue(colorcode);
        }


        @Override
        public void mouseDragged(MouseEvent e) {

            if (pressed_r) {

                Point p = getParent().getMousePosition();

                int type = component.getCursor().getType();

                int dx;
                int dy;
                int newWidth;
                int newHeight;

                switch (type) {

                    case Cursor.W_RESIZE_CURSOR:

                        dx = p.x - nodeX;
                        dy = 0;
                        newWidth = nodeW + nodeX - p.x;
                        newHeight = nodeH;
                        component.setLocation(new Point(p.x, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(p.x, nodeY), new Dimension(newWidth, newHeight));
                        break;


                    case Cursor.N_RESIZE_CURSOR:

                        dx = 0;
                        dy = p.y - nodeY;
                        newWidth = nodeW;
                        newHeight = nodeH + nodeY - p.y;
                        component.setLocation(new Point(nodeX, p.y));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, p.y), new Dimension(newWidth, newHeight));
                        break;


                    case Cursor.E_RESIZE_CURSOR:
                        dx = p.x - (nodeX + nodeW);
                        dy = 0;
                        newWidth = p.x - nodeX;
                        newHeight = nodeH;
                        component.setLocation(new Point(nodeX, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, nodeY), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.S_RESIZE_CURSOR:

                        dx = 0;
                        dy = p.y - (nodeY + nodeH);
                        newWidth = nodeW;
                        newHeight = p.y - nodeY;
                        component.setLocation(new Point(nodeX, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, nodeY), new Dimension(newWidth, newHeight));
                        break;


                    case Cursor.NW_RESIZE_CURSOR:

                        dx = p.x - nodeX;
                        dy = p.y - nodeY;
                        newWidth = nodeW - dx;
                        newHeight = nodeH - dy;
                        component.setLocation(new Point(p.x, p.y));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(p.x, p.y), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.NE_RESIZE_CURSOR:

                        dx = p.x - (nodeX + nodeW);
                        dy = p.y - nodeY;
                        newWidth = nodeW + dx;
                        newHeight = nodeH - dy;
                        component.setLocation(new Point(nodeX, p.y));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, p.y), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.SE_RESIZE_CURSOR:

                        dx = p.x - (nodeX + nodeW);
                        dy = p.y - (nodeY + nodeH);
                        newWidth = nodeW + dx;
                        newHeight = nodeH + dy;
                        component.setLocation(new Point(nodeX, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, nodeY), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.SW_RESIZE_CURSOR:
                        dx = p.x - nodeX;
                        dy = p.y - (nodeY + nodeH);
                        newWidth = nodeW - dx;
                        newHeight = nodeH + dy;
                        component.setLocation(new Point(p.x, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(p.x, nodeY), new Dimension(newWidth, newHeight));
                        break;
                }

                component.repaint();


            }


        }


        @Override
        public void mouseMoved(MouseEvent e) {

            Point p = e.getPoint();
            //Point p = e.getLocationOnScreen();
            Point p2 = getParent().getMousePosition();

            if (!isOverNode(p)) {

                if (component.getCursor() != Cursor.getDefaultCursor()) {

                    component.setCursor(Cursor.getDefaultCursor());
                }
                return;
            }


            int outcode = getOutcode(p);

            switch (outcode) {

                case Rectangle.OUT_TOP:
                    if (Math.abs(p2.y - nodeY) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_TOP + Rectangle.OUT_LEFT:
                    if (Math.abs(p2.y - nodeY) < PROX_DIST && Math.abs(p2.x - nodeX) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_LEFT:
                    if (Math.abs(p2.x - nodeX) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_LEFT + Rectangle.OUT_BOTTOM:
                    if (Math.abs(p2.x - nodeX) < PROX_DIST && Math.abs(p2.y - (nodeY + nodeH)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_BOTTOM:

                    if (Math.abs(p2.y - (nodeY + nodeH)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_BOTTOM + Rectangle.OUT_RIGHT:
                    if (Math.abs(p2.x - (nodeX + nodeW)) < PROX_DIST && Math.abs(p2.y - (nodeY + nodeH)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_RIGHT:
                    if (Math.abs(p2.x - (nodeX + nodeW)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_RIGHT + Rectangle.OUT_TOP:
                    if (Math.abs(p2.x - (nodeX + nodeW)) < PROX_DIST && Math.abs(p2.y - nodeY) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                    }
                    break;

                default:
                    component.setCursor(Cursor.getDefaultCursor());

            }


        }


        @Override
        public void mouseEntered(MouseEvent e) {

            component = (Node) e.getSource();

            Node selectedNode = (Node) e.getSource();

            int x = selectedNode.getNodeX();
            int y = selectedNode.getNodeY();
            int w = selectedNode.getNodeW();
            int h = selectedNode.getNodeH();

            Point point = e.getPoint();
            Boolean isInsideOfUpper = false;
            Boolean isInsideOfInner = false;

            Rectangle inner = new Rectangle(x + 2, y + 2, w - 4, h - 4);
            Rectangle outter = new Rectangle(x - 2, y - 2, w + 4, h + 4);

            isInsideOfInner = inner.contains(point);
            isInsideOfUpper = outter.contains(point);

            if (isInsideOfInner == false && isInsideOfUpper == true) {
                atLine = true;
                atInside = false;
            }
            else {
                atLine = false;
            }


            if (isInsideOfInner == true) {
                atInside = true;
                atLine = false;
            }
            else {
                atInside = false;
            }


        }


        private boolean isOverNode(Point p) {

            Node n = component;
            return n.contains(p);
        }


        private int getOutcode(Point p) {

            Point p2 = getParent().getMousePosition();


            Rectangle r = new Rectangle(component.getPoint(), component.getDimension());


            r.grow(-PROX_DIST, -PROX_DIST);

            return r.outcode((double) p2.x, (double) p2.y);
        }
    }


}
