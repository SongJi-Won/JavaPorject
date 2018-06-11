import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTextEditorPane extends JPanel {

    private JLabel title;

    private JTextArea textArea;

    private  JButton applyBtn;

    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;

    private ApplyButtonListener applyButtonListener;

    public MyTextEditorPane(MyMindMapPane myMindMapPane, MyAttributePane myAttributePane)
    {
        title = new JLabel("Text Label Pane");

        textArea = new JTextArea(20, 30);

        applyBtn = new JButton("적용");

        this.myMindMapPane = myMindMapPane;
        this.myAttributePane = myAttributePane;

        this.applyButtonListener = new ApplyButtonListener();
        applyBtn.addActionListener(this.applyButtonListener);



        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(applyBtn, BorderLayout.SOUTH);


    }


    public JButton getApplyBtn()
    {
        return this.applyBtn;
    }


    class ApplyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //여기서 제이슨 정보듣 받아와서

//            Node newNode1 = new Node(0, "Node1", 100, 500, 300, 100, Color.pink, 0, myAttributePane);
//            Node newNode2 = new Node(0, "Node2", 400, 600, 300, 100, Color.pink, 0, myAttributePane);
//            Node newNode3 = new Node(0, "Node3", 300, 700, 300, 100, Color.pink, 0, myAttributePane);
//
//            myMindMapPane.addNode(newNode1);
//            myMindMapPane.addNode(newNode2);
//            myMindMapPane.addNode(newNode3);


        }
    }
}
