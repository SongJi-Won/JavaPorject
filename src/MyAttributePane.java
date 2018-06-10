import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyAttributePane extends JPanel {

    private JLabel title;

    private MyAttribute text;
    private MyAttribute x;
    private MyAttribute y;
    private MyAttribute w;
    private MyAttribute h;
    private MyAttribute color;

    private JButton changeBtn;

    public MyAttributePane()
    {
        title = new JLabel("Attribute Pane");

        text = new MyAttribute("TEXT", "None", false);
        x = new MyAttribute("X", "0", true);
        y = new MyAttribute("Y", "0", true);
        w = new MyAttribute("W", "0", true);
        h = new MyAttribute("H", "0", true);
        color = new MyAttribute("COLOR", "0", true);

        changeBtn = new JButton("변경");
        //changeBtn.add 송지원 여기 버튼 누르면 값 바꿔주는 리스너 추가
        changeBtn.addActionListener(new MyActionListener());


        this.setLayout(new GridLayout(8, 1, 0, 2));

        this.add(title);
        this.add(text);
        this.add(x);
        this.add(y);
        this.add(w);
        this.add(h);
        this.add(color);
        this.add(changeBtn);


    }

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String gettext = text.getAttrName().getText();
            int getX = Integer.parseInt(x.getAttrValue().getText());
            int getY = Integer.parseInt(y.getAttrValue().getText());
            int getW = Integer.parseInt(w.getAttrValue().getText());
            int getH = Integer.parseInt(h.getAttrValue().getText());
            int getCOLOR = Integer.parseInt(color.getAttrValue().getText());

            System.out.println("");


        }
    }


    public JLabel getTitle(){
        return title;
    }

    public MyAttribute getTextAttribute() {
        return text;
    }


    public MyAttribute getXAttribute() {
        return x;
    }


    public MyAttribute getYAttribute() {
        return y;
    }

    public MyAttribute getWAttribute() {
        return w;
    }


    public MyAttribute getHAttribute() {
        return h;
    }

    public MyAttribute getColorAttribute() {
        return color;
    }
}
