import javax.swing.*;
import java.awt.*;

public class MyTextEditorPane extends JPanel {

    private JLabel title;

    private JTextArea textArea;

    private  JButton applyBtn;

    public MyTextEditorPane()
    {
        title = new JLabel("Text Label Pane");

        textArea = new JTextArea(20, 30);

        applyBtn = new JButton("적용");
        //applyBtn에 여기 버튼 누르면 마인드맵 그려주는 리스너 추가 송지원

        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(applyBtn, BorderLayout.SOUTH);


    }


    public JButton getApplyBtn()
    {
        return this.applyBtn;
    }
}
