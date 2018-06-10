import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JSplitPaneTest extends JFrame {

    private MyTextEditorPane myTextEditorPane;
    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;

   /* private MyFocusListener myFocusListener;*/
    private SplitPaneMouseListener splitPaneMouseListener;

    public JSplitPaneTest()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



       myTextEditorPane = new MyTextEditorPane();
//       myTextEditorPane.getApplyBtn().addActionListener();

       myMindMapPane = new MyMindMapPane();

       myAttributePane = new MyAttributePane();





       //20180610 작업분
        /*myFocusListener = new MyFocusListener();*/
        splitPaneMouseListener = new SplitPaneMouseListener();

        Node newNode1 = new Node(0, "Node1", 100, 200, 300, 100, Color.pink, 0, myAttributePane);
        Node newNode2 = new Node(0, "Node2", 400, 300, 300, 100, Color.blue, 0, myAttributePane);
        Node newNode3 = new Node(0, "Node3", 200, 50, 300, 100, Color.yellow, 0, myAttributePane);
//        Node newNode4 = new Node(0, "Node4", 700, 100, 300, 100, Color.gray, 0);

      /*  newNode1.addFocusListener(myFocusListener);
        newNode2.addFocusListener(myFocusListener);
        newNode3.addFocusListener(myFocusListener);
        newNode4.addFocusListener(myFocusListener);*/

        myMindMapPane.addNode(newNode1);
        myMindMapPane.addNode(newNode2);
        myMindMapPane.addNode(newNode3);
//        myMindMapPane.addNode(newNode4);
        myMindMapPane.repaint();

        myMindMapPane.addMouseListener(splitPaneMouseListener);









        JScrollPane jScrollTextArea = new JScrollPane(myTextEditorPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane jScrollMindMap = new JScrollPane(myMindMapPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane jScrollAttribute = new JScrollPane(myAttributePane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);




      /*  JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollMindMap, jScrollAttribute);
        jSplitPane1.setOneTouchExpandable(true);

        jSplitPane1.setDividerSize(3);
        jSplitPane1.setContinuousLayout(false);
        jSplitPane1.setLeftComponent(jScrollMindMap);
        jSplitPane1.setRightComponent(jScrollAttribute);

        jSplitPane1.setDividerLocation(0.6);


        JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollTextArea, jSplitPane1);
        jSplitPane2.setOneTouchExpandable(true);

        jSplitPane2.setDividerSize(3);
        jSplitPane2.setContinuousLayout(false);

        jSplitPane2.setLeftComponent(jScrollTextArea);
        jSplitPane2.setRightComponent(jSplitPane1);

        jSplitPane2.setResizeWeight(0.8);
        jSplitPane2.setDividerLocation(0.8);*/

        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollMindMap, jScrollAttribute);
        jSplitPane1.setResizeWeight(0.99);
        jSplitPane1.setLeftComponent(jScrollMindMap);
        jSplitPane1.setRightComponent(jScrollAttribute);
       // jSplitPane1.addMouseListener(splitPaneMouseListener);


        JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollTextArea, jSplitPane1);
        jSplitPane2.setResizeWeight(0.01);
        jSplitPane2.setLeftComponent(jScrollTextArea);
        jSplitPane2.setRightComponent(jSplitPane1);

//        jSplitPane2.setResizeWeight(0.8);
//        jSplitPane2.setDividerLocation(0.8);


        add(jSplitPane2);
        setSize(1000, 600);
        setVisible(true);


//        pack();

    }

    public static void main(String []args)
    {
        new JSplitPaneTest();
    }


    /*class MyFocusListener implements FocusListener {

        private MyAttribute text;
        private MyAttribute x;
        private MyAttribute y;
        private MyAttribute w;
        private MyAttribute h;
        private MyAttribute color;

        public MyFocusListener ()
        {
            this.text = myAttributePane.getTextAttribute();
            this.x = myAttributePane.getXAttribute();
            this.y = myAttributePane.getYAttribute();
            this.w = myAttributePane.getWAttribute();
            this.h = myAttributePane.getHAttribute();
            this.color = myAttributePane.getColorAttribute();
        }

        @Override
        public void focusGained(FocusEvent e) {

            System.out.println("송지원 : focusGained() 호출됨");;

            if (e.getSource() instanceof  Node){

                Node focusgaindedNode = (Node)e.getSource();

                this.text.setAttrValue(focusgaindedNode.getText());
                System.out.println("송지원 : focusGained() 호출됨 / getText() :" + focusgaindedNode.getText());;
                this.x.setAttrValue(""+focusgaindedNode.getNodeX());
                System.out.println("송지원 : focusGained() 호출됨 / getNodeX() :" + focusgaindedNode.getNodeX());;
                this.y.setAttrValue(""+focusgaindedNode.getNodeY());
                System.out.println("송지원 : focusGained() 호출됨 / getNodeY() :" + focusgaindedNode.getNodeY());;
                this.w.setAttrValue(""+focusgaindedNode.getNodeW());
                System.out.println("송지원 : focusGained() 호출됨 / getNodeW() :" + focusgaindedNode.getNodeW());;
                this.h.setAttrValue(""+focusgaindedNode.getNodeH());
                System.out.println("송지원 : focusGained() 호출됨 / getNodeH() : " + focusgaindedNode.getNodeH());;
            }

        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }*/


    public MyTextEditorPane getMyTextEditorPane() {
        return myTextEditorPane;
    }

    public MyMindMapPane getMyMindMapPane() {
        return myMindMapPane;
    }

    public MyAttributePane getMyAttributePane() {
        return myAttributePane;
    }






    class SplitPaneMouseListener extends MouseAdapter {


        public SplitPaneMouseListener()
        {
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : SplitPaneMouseListener 생성");
        }

        @Override
        public void mouseClicked(MouseEvent e) {



            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : SplitPaneMouseListener mouseClicked() 호출 "+e.getSource().toString());

            if (e.getSource() instanceof Node) {

                System.out.println("\n\n\n\n\n\n\n\n\n송지원 : SplitPaneMouseListener mouseClicked() Node 클릭");
                Node clickedNode = (Node)e.getSource();

                myAttributePane.setSelectedNode(clickedNode);

                String text = clickedNode.getText();
                int x = clickedNode.getNodeX();
                int y = clickedNode.getNodeY();
                int w = clickedNode.getNodeW();
                int h = clickedNode.getNodeH();
                Color color = clickedNode.getColor();

                myAttributePane.getTextAttribute().setAttrValue(text);
                myAttributePane.getXAttribute().setAttrValue(""+x);
                myAttributePane.getYAttribute().setAttrValue(""+y);
                myAttributePane.getWAttribute().setAttrValue(""+w);
                myAttributePane.getHAttribute().setAttrValue(""+h);
                myAttributePane.getColorAttribute().setAttrValue(""+color.toString());
            }
        }


        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : SplitPaneMouseListener mousePressed() 호출");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : SplitPaneMouseListener mouseReleased() 호출");
        }


        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : SplitPaneMouseListener mouseEntered() 호출");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : SplitPaneMouseListener mouseExited() 호출");
        }
    }




    class MindMapPaneContainerListener extends ContainerAdapter {

        public MindMapPaneContainerListener() {
            super();
        }

        @Override
        public void componentAdded(ContainerEvent e) {
            super.componentAdded(e);

//            if (e.getSource()송지원 여기부터
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            super.componentRemoved(e);
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
            int dy = p2.y - p2.y;

            Double distance = Math.sqrt(dx*dx*0.1 + dy*dy*0.1);

            return distance;
        }
    }
}
