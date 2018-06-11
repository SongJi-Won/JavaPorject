import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Node extends JLabel {

    private int id;

    private String text;
    private int nodeX;
    private int nodeY;
    private Point point;

    private int nodeW;
    private int nodeH;
    private Dimension dimension;


    private Color color;

    private int level;


    Node parent;
    Node child;



    private MyAttributePane parentAttributePane;


    public Node(int id, String text, int x, int y, int w, int h, Color color, int level, Node parent, Node child) {
        super(text);
        this.id = id;

        this.text = text;

        this.nodeX = x;
        this.nodeY = y;
        this.point = new Point(x, y);

        this.nodeW = w;
        this.nodeH = h;
        this.dimension = new Dimension(w, h);

        this.level = level;

        //this.color = 레벨에 따른 다른 색상 넣기
        this.color = Color.cyan;

        this.parent = parent;
        this.child = child;

        /*this.addMouseListener(new MouseListener() {

            private int pressedX;
            private int pressedY;

            private int releasedX;
            private int releasedY;


            @Override
            public void mouseClicked(MouseEvent e) {



            }

            @Override
            public void mousePressed(MouseEvent e) {

                pressedX = e.getX();
                pressedY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                Node clicked = (Node) e.getSource();

                releasedX = e.getX();
                releasedY = e.getY();

                int changedX = releasedX - pressedX;
                int changedY = releasedY - pressedY;

                setLocation(clicked.getNodeX() + changedX, clicked.getNodeY() + changedY);

                clicked.setNodeX(clicked.getNodeX() + changedX);
                clicked.setNodeY(clicked.getNodeY() + changedY);
            }


            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });*/
    }


    public Node(int id, String text, int x, int y, int w, int h, Color color, int level, MyAttributePane myAttributePane) {
        super(text);
        /*this.setMinimumSize(this.getPreferredSize());*/
        this.setMinimumSize(new Dimension(300, 100));//송지원 이거 적용안되는듯...;;
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(CENTER);

        this.id = id;

        this.text = text;

        this.nodeX = x;
        this.nodeY = y;
        this.point = new Point(x, y);
        setLocation(this.point);


        this.nodeW = w;
        this.nodeH = h;
        this.dimension = new Dimension(w, h);
        setSize(this.dimension);

        this.level = level;

        //this.color = 레벨에 따른 다른 색상 넣기
        this.color = Color.cyan;


        this.parentAttributePane = myAttributePane;


        setFont(new Font("돋움", Font.BOLD, 20));
        setOpaque(true);
        setBackground(Color.magenta);


        NodeResizeAdapter r = new NodeResizeAdapter(this);
        this.addMouseMotionListener(r);
        this.addMouseListener(r);

    }






    public Node(int id, String text, int x, int y, int w, int h, Color color, int level) {
        super(text);
        /*this.setMinimumSize(this.getPreferredSize());*/
        this.setMinimumSize(new Dimension(300, 100));//송지원 이거 적용안되는듯...;;
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(CENTER);

        this.id = id;

        this.text = text;

        this.nodeX = x;
        this.nodeY = y;
        this.point = new Point(x, y);
        setLocation(this.point);


        this.nodeW = w;
        this.nodeH = h;
        this.dimension = new Dimension(w, h);
        setSize(this.dimension);

        this.level = level;

        //this.color = 레벨에 따른 다른 색상 넣기
        this.color = Color.cyan;



        setFont(new Font("돋움", Font.BOLD, 20));
        setOpaque(true);
        setBackground(Color.magenta);


        NodeResizeAdapter r = new NodeResizeAdapter(this);
        this.addMouseMotionListener(r);
        this.addMouseListener(r);
        }






























    public void setParent(Node parent) {

        this.parent = parent;
    }

    public void setChild(Node child) {

        this.child = child;
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

            parentAttributePane.setSelectedNode((Node)e.getSource());
            parentAttributePane.getTextAttribute().setAttrValue(component.text);
            parentAttributePane.getXAttribute().setAttrValue(""+component.getNodeX());
            parentAttributePane.getYAttribute().setAttrValue(""+component.getNodeY());
            parentAttributePane.getWAttribute().setAttrValue(""+component.getNodeW());
            parentAttributePane.getHAttribute().setAttrValue(""+component.getNodeH());

            colorcode = String.format("%02x%02x%02x", component.getColor().getRed(), component.getColor().getGreen(),component.getColor().getBlue());
            parentAttributePane.getColorAttribute().setAttrValue(colorcode);

        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (component.getCursor() != Cursor.getDefaultCursor()) {

                pressed_r = true;
//                System.out.println("송지원 : mousePressed() 호출");
            }
//            System.out.println("송지원 : mousePressed() 호출 pressed_r : " + pressed_r);


            pressed_m = true;
            pressedX = e.getX();
            pressedY = e.getY();
            System.out.println("송지원 : mousePressed() 호출 pressedX : " + pressedX + " / pressedY : " + pressedY);

            pressedComponent = (Node) e.getComponent();
            componentX = pressedComponent.getX();
            componentY = pressedComponent.getY();
            componentW = pressedComponent.getWidth();
            componentH = pressedComponent.getHeight();
            System.out.println("송지원 : mousePressed() 호출 componentX : " + componentX + " / componentY : " + componentY + " / componentW : " + componentW + " / componentH : " + componentH);

            Rectangle inside = new Rectangle(componentX + 2, componentY + 2, componentW - 4, componentH - 4);
            System.out.println("송지원 : mousePressed() 호출 Rectangle inside (" + (componentX + 2) + ", " + (componentY + 2) + ", " + (componentW - 4) + ", " + (componentH - 4));
           /* atInside = inside.contains(e.getPoint());
            System.out.println("\n송지원 : mousePressed() 호출 e.getPoint: " + e.getPoint().x +"/ "+ e.getPoint().y + "\n");*/

            atInside = inside.contains(e.getLocationOnScreen());
            atInside = inside.contains(new Point(componentX + e.getX(), componentY + e.getY()));
            System.out.println("\n송지원 : mousePressed() 호출 e.getPoint: " + e.getPoint().x + "/ " + e.getPoint().y + "\n");

            System.out.println("송지원 : mousePressed() 호출 pressed_r : " + pressed_r + " / atInside : " + atInside);

        }


        @Override
        public void mouseReleased(MouseEvent e) {

//            System.out.println("송지원 : mouseReleased() 호출 pressed_r : " + pressed_r);
            pressed_r = false;
//            System.out.println("송지원 : mouseReleased() 호출 pressed_r : " + pressed_r);


            System.out.println("송지원 : mouseReleased() 호출 pressed_m : " + pressed_m + " / atInside : " + atInside);

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
        }


        @Override
        public void mouseDragged(MouseEvent e) {

//            System.out.println("송지원 : mouseDragged() 호출 pressed_r :" + pressed_r);

            if (pressed_r) {
//                System.out.println("송지원 : mouseDragged() 호출2");

//                Point p = e.getPoint();
                Point p = getParent().getMousePosition();

                int type = component.getCursor().getType();

                int dx;
                int dy;
                int newWidth;
                int newHeight;

//                System.out.println("송지원 : mouseDragged() 호출2.5 type :" + type);
                switch (type) {

                    case Cursor.W_RESIZE_CURSOR:
                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.W_RESIZE_CURSOR");
//                        nodeX = p.x;
//                        nodeW = nodeW - dx;
//                        component.setLocation(new Point(nodeX, nodeY));
//                        component.setSize(new Dimension(nodeW, nodeH));
//                        break;
                        dx = p.x - nodeX;
                        dy = 0;
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
                        newWidth = nodeW + nodeX - p.x;
                        newHeight = nodeH;
                        component.setLocation(new Point(p.x, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(p.x, nodeY), new Dimension(newWidth, newHeight));
                        break;


                    case Cursor.N_RESIZE_CURSOR:
                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.N_RESIZE_CURSOR");
//                        nodeY = nodeY + dy;
//                        nodeH = nodeH - dy;
//                        component.setLocation(new Point(nodeX, nodeY));
//                        component.setSize(new Dimension(nodeW, nodeH));
//                        break;
                        dx = 0;
                        dy = p.y - nodeY;
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
                        newWidth = nodeW;
                        newHeight = nodeH + nodeY - p.y;
                        component.setLocation(new Point(nodeX, p.y));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, p.y), new Dimension(newWidth, newHeight));
                        break;


                    case Cursor.E_RESIZE_CURSOR:
                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.E_RESIZE_CURSOR");
//                        nodeW = nodeW + dx;
//                        component.setLocation(new Point(nodeX, nodeY));
//                        component.setSize(new Dimension(nodeW, nodeH));
//                        break;
                        dx = p.x - (nodeX + nodeW);
                        dy = 0;
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
                        newWidth = p.x - nodeX;
                        newHeight = nodeH;
                        component.setLocation(new Point(nodeX, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, nodeY), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.S_RESIZE_CURSOR:
                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.S_RESIZE_CURSOR");
//                        nodeH = nodeH + dy;
//                        component.setLocation(new Point(nodeX, nodeY));
//                        component.setSize(new Dimension(nodeW, nodeH));
//                        break;
                        dx = 0;
                        dy = p.y - (nodeY + nodeH);
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
                        newWidth = nodeW;
                        newHeight = p.y - nodeY;
                        component.setLocation(new Point(nodeX, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, nodeY), new Dimension(newWidth, newHeight));
                        break;


                    case Cursor.NW_RESIZE_CURSOR:
                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.NW_RESIZE_CURSOR");
//                        nodeX = nodeX + dx;
//                        nodeY = nodeY + dy;
//                        nodeW = nodeW - dx;
//                        nodeH = nodeH - dy;
                        dx = p.x - nodeX;
                        dy = p.y - nodeY;
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
                        newWidth = nodeW - dx;
                        newHeight = nodeH - dy;
                        component.setLocation(new Point(p.x, p.y));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(p.x, p.y), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.NE_RESIZE_CURSOR:
                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.NE_RESIZE_CURSOR");
//                        nodeY = nodeY + dy;
//                        nodeW = nodeW + dx;
//                        nodeH = nodeH - dy;
                        dx = p.x - (nodeX + nodeW);
                        dy = p.y - nodeY;
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
                        newWidth = nodeW + dx;
                        newHeight = nodeH - dy;
                        component.setLocation(new Point(nodeX, p.y));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, p.y), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.SE_RESIZE_CURSOR:
                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.SE_RESIZE_CURSOR");
//                        nodeW = nodeW + dx;
//                        nodeH = nodeH + dy;
                        dx = p.x - (nodeX + nodeW);
                        dy = p.y - (nodeY + nodeH);
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
                        newWidth = nodeW + dx;
                        newHeight = nodeH + dy;
                        component.setLocation(new Point(nodeX, nodeY));
                        component.setSize(new Dimension(newWidth, newHeight));
                        component.update(new Point(nodeX, nodeY), new Dimension(newWidth, newHeight));
                        break;

                    case Cursor.SW_RESIZE_CURSOR:

                        System.out.println("송지원 : mouseDragged() 호출3 : Cursor.SW_RESIZE_CURSOR");
//                        nodeX = nodeX + dx;
//                        nodeW = nodeW - dx;
//                        nodeH = nodeH + dy;
                        dx = p.x - nodeX;
                        dy = p.y - (nodeY + nodeH);
                        System.out.println("\t\t송지원 : mouseDragged() 호출2.3 /dx" + dx + "/dy" + dy);
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

//            System.out.println("송지원 : mouseMoved() 호출1");

            Point p = e.getPoint();
            //Point p = e.getLocationOnScreen();
            Point p2 = getParent().getMousePosition();

            if (!isOverNode(p)) {

//                System.out.println("송지원 : mouseMoved() 호출2 : isOverNode is true");
                if (component.getCursor() != Cursor.getDefaultCursor()) {

                    component.setCursor(Cursor.getDefaultCursor());
                }
                return;
            }


            int outcode = getOutcode(p);
//            System.out.println("송지원 : mouseMoved() 호출3 outcode :" + outcode);

            switch (outcode) {

                case Rectangle.OUT_TOP:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_TOP (p2.y-nodeY):" + (p2.y - nodeY));
                    if (Math.abs(p2.y - nodeY) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_TOP + Rectangle.OUT_LEFT:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_TOP + Node.OUT_LEFT (p2.y - nodeY):" + (p2.y - nodeY) + " (p2.x - nodeX):" + (p2.x - nodeX));
                    if (Math.abs(p2.y - nodeY) < PROX_DIST && Math.abs(p2.x - nodeX) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_LEFT:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_LEFT (p2.x - nodeX):" + (p2.x - nodeX));
                    if (Math.abs(p2.x - nodeX) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_LEFT + Rectangle.OUT_BOTTOM:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_LEFT + Node.OUT_BOTTOM (p2.x - nodeX):" + (p2.x - nodeX) + " (p2.y - (nodeY + nodeH)):" + (p2.y - (nodeY + nodeH)));
                    if (Math.abs(p2.x - nodeX) < PROX_DIST && Math.abs(p2.y - (nodeY + nodeH)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_BOTTOM:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_BOTTOM (p.y - (nodeY + nodeH)):" + (p2.y - (nodeY + nodeH)));
                    if (Math.abs(p2.y - (nodeY + nodeH)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_BOTTOM + Rectangle.OUT_RIGHT:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_BOTTOM + Node.OUT_RIGHT (p2.x - (nodeX + nodeW)):" + (p2.x - (nodeX + nodeW)) + " (p2.y - (nodeY + nodeH)):" + (p2.y - (nodeY + nodeH)));
                    if (Math.abs(p2.x - (nodeX + nodeW)) < PROX_DIST && Math.abs(p2.y - (nodeY + nodeH)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_RIGHT:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_RIGHT (p2.x - (nodeX + nodeW)):" + (p2.x - (nodeX + nodeW)));
                    if (Math.abs(p2.x - (nodeX + nodeW)) < PROX_DIST) {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    }
                    break;

                case Rectangle.OUT_RIGHT + Rectangle.OUT_TOP:
                    System.out.println("송지원 : mouseMoved() 호출4 Node.OUT_RIGHT + Node.OUT_TOP (p2.x - (nodeX + nodeW))" + (p2.x - (nodeX + nodeW)) + " (p2.y - nodeY):" + (p2.y - nodeY));
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
//            System.out.println("@@@@@@@@@@@@@@@@@@송지원 : mouseEntered() 호출 " + (x + 2) + " " + (y + 2) + " " + (w - 4) + " " + (h - 4));
            Rectangle outter = new Rectangle(x - 2, y - 2, w + 4, h + 4);
//            System.out.println("@@@@@@@@@@@@@@@@@@송지원 : mouseEntered() 호출 " + (x - 2) + " " + (y - 2) + " " + (w + 4) + " " + (h + 4));

            isInsideOfInner = inner.contains(point);
            isInsideOfUpper = outter.contains(point);
//            System.out.println("@@@@@@@@@@@@@@@@@@송지원 : mouseEntered() 호출 isInsideOfInner :" + isInsideOfInner.toString());
//            System.out.println("@@@@@@@@@@@@@@@@@@송지원 : mouseEntered() 호출 isInsideOfOUttet :" + isInsideOfUpper.toString());

            if (isInsideOfInner == false && isInsideOfUpper == true) {
                atLine = true;
                atInside = false;
//                System.out.println("!!!!!!!!!!!!!!송지원 : mouseEntered() 호출 atline : true");
            } else {
                atLine = false;
//                System.out.println("!!!!!!!!!!!!송지원 : mouseEntered() 호출 atline : false");
            }


            if (isInsideOfInner == true) {
                atInside = true;
                atLine = false;
//                System.out.println("송지원송지원송지원송지원 : mouseEntered() 호출 isInside : true");
            } else {
                atInside = false;
            }


        }


        private boolean isOverNode(Point p) {
//            System.out.println("송지원 : isOverNode() 호출 ");
            Node n = component;
//            System.out.println("송지원 : isOverNode() 호출 n.contains(p) :" + n.contains(p));
            return n.contains(p);
        }


        private int getOutcode(Point p) {

            Point p2 = getParent().getMousePosition();

//            System.out.println("\n\t!#@$!@#$!@#$!@#$송지원 : getOutcode() 호출 p2.x:" + p2.x + " p2.y" + p2.y);

//            System.out.println("\n\t!#@$!@#$!@#$!@#$송지원 : getOutcode() 호출 p.x:" + p.x + " p.y" + p.y);
//            System.out.println("\t!#@$!@#$!@#$!@#$송지원 : getOutcode() 호출 point.x:" + point.x + " point.y" + point.y);
//            System.out.println("\t!#@$!@#$!@#$!@#$송지원 : getOutcode() 호출 dimention.w:" + dimension.width + " / dimetion.h:" + dimension.height);

            Rectangle r = new Rectangle(component.getPoint(), component.getDimension());

//            System.out.println("\n\t%^*%^*%^*%^*%^*%^*%^*송지원 : getOutcode() 호출 r.x:" + r.x + " / r.y:" + r.y + " / r.w:" + r.width + " / r.h:" + r.height);

            r.grow(-PROX_DIST, -PROX_DIST);

//            System.out.println("\t!#@$!@#$!@#$!@#$송지원 : getOutcode() 호출 r.outcode((double)p.x, (double)p.y): " + r.outcode((double) p.x, (double) p.y) + "\n");
            return r.outcode((double) p2.x, (double) p2.y);
        }
    }


}
