import javax.swing.*;
import java.awt.*;

public class JSplitPaneTest extends JFrame {

    public JSplitPaneTest()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



       MyTextEditorPane myTextEditorPane = new MyTextEditorPane();
//       myTextEditorPane.getApplyBtn().addActionListener();

       MyMindMapPane myMindMapPane = new MyMindMapPane();

       MyAttributePane myAttributePane = new MyAttributePane();




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


}
