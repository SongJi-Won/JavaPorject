import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JSplitPaneTest extends JFrame {

    private MyTextEditorPane myTextEditorPane;
    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;

   /* private MyFocusListener myFocusListener;*/

    public JSplitPaneTest()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



       myTextEditorPane = new MyTextEditorPane();
//       myTextEditorPane.getApplyBtn().addActionListener();

       myMindMapPane = new MyMindMapPane();

       myAttributePane = new MyAttributePane();





       //20180610 작업분
        /*myFocusListener = new MyFocusListener();*/

        Node newNode1 = new Node(0, "Node1", 100, 200, 300, 100, Color.pink, 0);
        Node newNode2 = new Node(0, "Node2", 400, 300, 300, 100, Color.blue, 0);
        Node newNode3 = new Node(0, "Node3", 200, 50, 300, 100, Color.yellow, 0);
        Node newNode4 = new Node(0, "Node4", 700, 100, 300, 100, Color.gray, 0);

      /*  newNode1.addFocusListener(myFocusListener);
        newNode2.addFocusListener(myFocusListener);
        newNode3.addFocusListener(myFocusListener);
        newNode4.addFocusListener(myFocusListener);*/

        myMindMapPane.addNode(newNode1);
        myMindMapPane.addNode(newNode2);
        myMindMapPane.addNode(newNode3);
        myMindMapPane.addNode(newNode4);









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


    


}
