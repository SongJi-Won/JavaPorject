import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class MyAttributePane extends JPanel {

    private JLabel title;


    private MyAttribute text;
    private MyAttribute attributeX;
    private MyAttribute attributeY;
    private MyAttribute attributeW;
    private MyAttribute attributeH;
    private MyAttribute attributeColor;

//    private MyAttribute x;
//    private MyAttribute y;
//    private MyAttribute w;
//    private MyAttribute h;
//    private MyAttribute color;

    private JButton changeBtn;

    private Node selectedNode;

    public MyAttributePane(JSplitPaneTest jSplitPaneTest)
    {
        //this.jSplitPaneTest = jSplitPaneTest;


        title = new JLabel("Attribute Pane");

        text = new MyAttribute("   TEXT", "None", false);
        attributeX = new MyAttribute("      X", "0", true);
        attributeY = new MyAttribute("      Y", "0", true);
        attributeW = new MyAttribute("      W", "0", true);
        attributeH = new MyAttribute("      H", "0", true);
        attributeColor = new MyAttribute("COLOR", "0", true);

        changeBtn = new JButton("변경");
        //changeBtn.add 송지원 여기 버튼 누르면 값 바꿔주는 리스너 추가
        changeBtn.addActionListener(new MyActionListener());


        this.setLayout(new GridLayout(8, 1, 0, 2));

        this.add(title);
        this.add(text);
        this.add(attributeX);
        this.add(attributeY);
        this.add(attributeW);
        this.add(attributeH);
        this.add(attributeColor);
        this.add(changeBtn);


    }

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Pattern p = Pattern.compile("[0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F]");
            int red;
            int green;
            int blue;

            int getX = Integer.parseInt(attributeX.getAttrValue().getText());
            int getY = Integer.parseInt(attributeY.getAttrValue().getText());
            int getW = Integer.parseInt(attributeW.getAttrValue().getText());
            int getH = Integer.parseInt(attributeH.getAttrValue().getText());
            String getCOLOR = attributeColor.getAttrValue().getText().toUpperCase();


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
        return attributeX;
    }


    public MyAttribute getYAttribute() {
        return attributeY;
    }

    public MyAttribute getWAttribute() {
        return attributeW;
    }


    public MyAttribute getHAttribute() {
        return attributeH;
    }

    public MyAttribute getColorAttribute() {
        return attributeColor;
    }

    public JButton getChangeBtn() { return changeBtn; }



    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
    }
}
