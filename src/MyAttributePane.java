import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAttributePane extends JPanel {

    private JLabel title;

    private MyAttribute text;
    private MyAttribute x;
    private MyAttribute y;
    private MyAttribute w;
    private MyAttribute h;
    private MyAttribute color;

    private JButton changeBtn;

    private JSplitPaneTest jSplitPaneTest;
    private Node selectedNode;

    public MyAttributePane(JSplitPaneTest jSplitPaneTest)
    {
        this.jSplitPaneTest = jSplitPaneTest;


        title = new JLabel("Attribute Pane");

        text = new MyAttribute("   TEXT", "None", false);
        x = new MyAttribute("      X", "0", true);
        y = new MyAttribute("      Y", "0", true);
        w = new MyAttribute("      W", "0", true);
        h = new MyAttribute("      H", "0", true);
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

            Pattern p = Pattern.compile("[0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F]");
            int red;
            int green;
            int blue;

            int getX = Integer.parseInt(x.getAttrValue().getText());
            int getY = Integer.parseInt(y.getAttrValue().getText());
            int getW = Integer.parseInt(w.getAttrValue().getText());
            int getH = Integer.parseInt(h.getAttrValue().getText());
            String getCOLOR = color.getAttrValue().getText().toUpperCase();
            System.out.println("이상덕 getCOLOR : " + getCOLOR);

            System.out.println("이상덕 getCOLOR.substring(0,2) : [" + getCOLOR.substring(0, 2)+"]");
            System.out.println("이상덕 getCOLOR.substring(0,2) : [" + getCOLOR.substring(2, 4)+"]");
            System.out.println("이상덕 getCOLOR.substring(0,2) : [" + getCOLOR.substring(4, 6)+"]");


            red = Integer.parseInt(getCOLOR.substring(0,2), 16);
            green = Integer.parseInt(getCOLOR.substring(2,4), 16);
            blue = Integer.parseInt(getCOLOR.substring(4,6), 16);


            selectedNode.update(new Point(getX, getY), new Dimension(getW, getH));
            selectedNode.setColor(new Color(red, green, blue));

            selectedNode.setLocation(new Point(getX, getY));
            selectedNode.setSize(new Dimension(getW, getH));
            selectedNode.setBackground(new Color(red, green, blue));



            selectedNode.repaint();
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

    public JButton getChangeBtn() { return changeBtn; }



    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
    }
}
