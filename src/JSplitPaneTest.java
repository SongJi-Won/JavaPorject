import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JSplitPaneTest extends JFrame {

    private MyTextEditorPane myTextEditorPane;
    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;

    private SplitPaneMouseListener splitPaneMouseListener;

    private Node clickedNode;


    public JSplitPaneTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myMindMapPane = new MyMindMapPane();
        myAttributePane = new MyAttributePane(this);
        myTextEditorPane = new MyTextEditorPane(this, myMindMapPane, myAttributePane, myMindMapPane.getWidth()/2, myMindMapPane.getHeight()/2);
//        myTextEditorPane = new MyTextEditorPane(myMindMapPane, myAttributePane, 350, 350);

        this.clickedNode = null;

        splitPaneMouseListener = new SplitPaneMouseListener();

        Node forError = new Node(0, "", 0, 0, 0, 0, Color.white, 0, null, this, myAttributePane);

        myMindMapPane.addNode(forError);
        myMindMapPane.remove(forError);

        myMindMapPane.addMouseListener(splitPaneMouseListener);


        JScrollPane jScrollTextArea = new JScrollPane(myTextEditorPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollTextArea.setSize(new Dimension(300, 800));

        JScrollPane jScrollMindMap = new JScrollPane(myMindMapPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollMindMap.setSize(new Dimension(800, 800));

        JScrollPane jScrollAttribute = new JScrollPane(myAttributePane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollAttribute.setSize(new Dimension(300, 800));


        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollMindMap, jScrollAttribute);
        jSplitPane1.setResizeWeight(0.8);
        jSplitPane1.setLeftComponent(jScrollMindMap);
        jSplitPane1.setRightComponent(jScrollAttribute);


        JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollTextArea, jSplitPane1);
        jSplitPane2.setResizeWeight(0.3);
        jSplitPane2.setLeftComponent(jScrollTextArea);
        jSplitPane2.setRightComponent(jSplitPane1);


        add(jSplitPane2);
        setSize(1400, 1000);
        setVisible(true);

    }

    public static void main(String[] args) {
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

        if (this.clickedNode != null) {

            this.clickedNode.setBackground(this.clickedNode.getColor());
        }
        this.clickedNode = clickedNode;
    }


    class SplitPaneMouseListener extends MouseAdapter {


        @Override
        public void mouseClicked(MouseEvent e) {

            clickedNode.setBackground(clickedNode.getColor());
            clickedNode = null;
        }


        @Override
        public void mousePressed(MouseEvent e) {
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
