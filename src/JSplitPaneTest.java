import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JSplitPaneTest extends JFrame {

    private MyTextEditorPane myTextEditorPane;
    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;

    private SplitPaneMouseListener splitPaneMouseListener;

    private Node clickedNode;


    public JSplitPaneTest()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myMindMapPane = new MyMindMapPane();
        myAttributePane = new MyAttributePane(this);
        myTextEditorPane = new MyTextEditorPane(myMindMapPane, myAttributePane, this.getWidth()/2, this.getHeight()/2);
//        myTextEditorPane = new MyTextEditorPane(myMindMapPane, myAttributePane, 500, 500);

        this.clickedNode = null;

        splitPaneMouseListener = new SplitPaneMouseListener();

        Node forError = new Node(0, "", 0, 0, 0, 0, Color.white, 0, null, myAttributePane);

        myMindMapPane.addNode(forError);
        myMindMapPane.remove(forError);

        myMindMapPane.addMouseListener(splitPaneMouseListener);




        JScrollPane jScrollTextArea = new JScrollPane(myTextEditorPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane jScrollMindMap = new JScrollPane(myMindMapPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane jScrollAttribute = new JScrollPane(myAttributePane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollMindMap, jScrollAttribute);
        jSplitPane1.setResizeWeight(0.99);
        jSplitPane1.setLeftComponent(jScrollMindMap);
        jSplitPane1.setRightComponent(jScrollAttribute);


        JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollTextArea, jSplitPane1);
        jSplitPane2.setResizeWeight(0.01);
        jSplitPane2.setLeftComponent(jScrollTextArea);
        jSplitPane2.setRightComponent(jSplitPane1);


        add(jSplitPane2);
        setSize(1400, 800);
        setVisible(true);

    }

    public static void main(String []args)
    {
        new JSplitPaneTest();
    }




    public MyTextEditorPane getMyTextEditorPane() {
        return myTextEditorPane;
    }

    public MyMindMapPane getMyMindMapPane() {
        return myMindMapPane;
    }

    public MyAttributePane getMyAttributePane() {
        return myAttributePane;
    }

    public Node getClickedNode() {
        return clickedNode;
    }

    public void setClickedNode(Node clickedNode) {
        this.clickedNode = clickedNode;
    }



    class SplitPaneMouseListener extends MouseAdapter {



        @Override
        public void mouseClicked(MouseEvent e) {




            if (e.getSource() instanceof Node) {

                Node clickedNode = (Node)e.getSource();
                setClickedNode(clickedNode);

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
            Node clickedNode = (Node)e.getSource();
            setClickedNode(clickedNode);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }


        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
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




    }
}
