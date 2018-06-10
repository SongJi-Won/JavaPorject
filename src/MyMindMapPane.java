import javax.swing.*;
import java.awt.*;

public class MyMindMapPane extends JPanel {

    private JLabel title;
    private JPanel board;


    public MyMindMapPane()
    {
        title = new JLabel("Mind Map Pane");
//        title.setSize(200, 40);

        board = new JPanel();
//        board.setSize(400, 400);


        //this.setLayout(null); //송지원 여기 나중에 노드 들어야 하는 부분이라 일단 레이아웃 없앴다.
        this.setLayout(new BorderLayout());



        this.add(title, BorderLayout.NORTH);

        this.add(board, BorderLayout.CENTER);

//        setSize(500, 500);

//        setVisible(true);

    }

    public void addNode(Node newNode){

        this.add(newNode);
    }
}
