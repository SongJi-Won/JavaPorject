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


    private Node selectedNode;

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



            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출 ");

            Pattern p = Pattern.compile("[0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F][0-9|a-f|A-F]");
            int red;
            int green;
            int blue;

            int getX = Integer.parseInt(x.getAttrValue().getText());
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  X:"+getX);
            int getY = Integer.parseInt(y.getAttrValue().getText());
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  Y:"+getY);
            int getW = Integer.parseInt(w.getAttrValue().getText());
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  W:"+getW);
            int getH = Integer.parseInt(h.getAttrValue().getText());
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  H:"+getH);
            String getCOLOR = color.getAttrValue().getText().toUpperCase();
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  COLOR:"+getCOLOR);


            Matcher m = p.matcher(getCOLOR);

            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  m:"+m.find());
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  red/green/b;ue:"+m.group());



            red = Integer.parseInt(m.group().substring(0,2), 16);
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  red:"+red);
            green = Integer.parseInt(m.group().substring(2,4), 16);
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  green:"+green);
            blue = Integer.parseInt(m.group().substring(4,6), 16);
            System.out.println("\n\n\n\n\n\n\n\n\n송지원 : MyAttributePane actionPerformed() 호출  blue:"+blue);


//            selectedNode.setNodeX(getX);
//            selectedNode.setNodeY(getY);
//            selectedNode.setNodeW(getW);
//            selectedNode.setNodeH(getH);
            //update() 함수가 대체 가능해서 생략
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



    public void setSelectedNode(Node selectedNode) {
        System.out.println("\n\n\n\n\n\n\n\n\n\t\t\t송지원 : MyAttributePane selectedNode() 호출  :"+selectedNode.getText());
        this.selectedNode = selectedNode;
    }
}
