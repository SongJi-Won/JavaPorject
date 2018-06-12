import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NodeTest extends JFrame {

    Node n1;
    Node n2;
    Node n3;

    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JLabel l6;
    JLabel l7;
    JLabel l8;


    Container c;


    public NodeTest() {


//        n1 = new Node(1, "여름", 100, 100, 300, 200, Color.black, 0);
//        n2 = new Node(2, "날씨", 300, 300, 100, 50, Color.black, 1);
//        n3 = new Node(3, "호우", 500, 500, 100, 50, Color.black, 2);

//        n1.setHorizontalAlignment(SwingConstants.CENTER);
//        n2.setHorizontalAlignment(SwingConstants.CENTER);
//        n3.setHorizontalAlignment(SwingConstants.CENTER);
//
//        n1.setPreferredSize(new Dimension(200, 50));
//        n2.setPreferredSize(new Dimension(200, 50));
//        n3.setPreferredSize(new Dimension(200, 50));
//
//        n1.setFont(new Font("돋움", Font.BOLD, 20));
//        n2.setFont(new Font("돋움", Font.BOLD, 20));
//        n3.setFont(new Font("돋움", Font.BOLD, 20));
//
//        n1.setOpaque(true);
//        n1.setBackground(Color.magenta);
//        n2.setOpaque(true);
//        n2.setBackground(Color.GREEN);
//        n3.setOpaque(true);
//        n3.setBackground(Color.orange);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = getContentPane();
        c.setBackground(Color.CYAN);


        c.setLayout(null);
        //c.setLayout(new FlowLayout());
        setSize(1200, 600);

//        n1.setBounds(101, 20, 200, 30);
//        n2.setBounds(501, 80, 200, 30);
//        n3.setBounds(341, 60, 200, 30);

        add(n1);
        add(n2);
        add(n3);


        l1 = new JLabel("None", Label.LEFT);
        l2 = new JLabel("None", Label.LEFT);
        l3 = new JLabel("None", Label.LEFT);
        l4 = new JLabel("None", Label.LEFT);
        l5 = new JLabel("None", Label.LEFT);
        l6 = new JLabel("None", Label.LEFT);
        l7 = new JLabel("None", Label.LEFT);
        l8 = new JLabel("None", Label.LEFT);

        l1.setBounds(50, 100, 100, 10);
        l2.setBounds(50, 200, 100, 10);
        l3.setBounds(50, 300, 100, 10);
        l4.setBounds(50, 400, 100, 10);
        l5.setBounds(50, 500, 100, 10);
        l6.setBounds(50, 600, 100, 10);
        l7.setBounds(50, 700, 100, 10);
        l8.setBounds(50, 800, 100, 10);


        setListener(n1);
        setListener(n2);
        setListener(n3);


        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);


        setVisible(true);
    }


    public void add(Node node) {

        int x = node.getNodeX();
        int y = node.getNodeY();

        node.setLocation(x, y);
        c.add(node);
        //node.setLocation(x, y);
    }

    public void setListener(JComponent input) {

        System.out.println("송지원: setListener is called\n");
        if (input instanceof Node) {

            System.out.println("송지원: input is instance of Node\n");
            Node node = (Node) input;

            node.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                    Node input = (Node) e.getSource();
                    input.setBounds(100, 200, 500, 600);


//                    String text = node.getText();
//                    int id = node.getId();
//                    int level = node.getLevel();
//                    int x = node.getNodeX();
//                    int y = node.getNodeY();
//                    int w = node.getNodeW();
//                    int h = node.getNodeH();
//                    Color color = node.getColor();
//
//
                    String text = input.getText();
                    int id = input.getId();
                    int level = input.getLevel();
                    int x = input.getNodeX();
                    int y = input.getNodeY();
                    int w = input.getNodeW();
                    int h = input.getNodeH();
                    Color color = input.getColor();


                    System.out.println("송지원: focusGained() 호출됨\n");
                    l1.setText("@!$!");
                    l1.setText(text);
                    l2.setText("" + id);
                    l3.setText("" + level);
                    l4.setText("" + x);
                    l5.setText("" + y);
                    l6.setText("" + w);
                    l7.setText("" + h);
                    l8.setText(color.toString());


                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });

            System.out.println("송지원: before addMouseListener is called\n");


            node.addMouseListener(new MouseListener() {


                @Override
                public void mouseClicked(MouseEvent e) {

                    System.out.println("\n\n원지송 mouseClicked() 호출됨\n\n");

                    Node input = (Node) e.getSource();

                    if (e.getSource() instanceof Node) {
                        String text = input.getText();
                        int id = input.getId();
                        int level = input.getLevel();
                        //int y = node.getNodeY();
                        //int x = node.getNodeX();
                        Dimension dimension = input.getDimension();

                        int y = input.getLocationOnScreen().y;
                        int x = input.getLocationOnScreen().x;

                        int w = input.getNodeW();
                        int h = input.getNodeH();
                        Color color = input.getColor();


                        System.out.println("송지원: mouseClicked() 호출됨\n");
                        l1.setText("@!$!");
                        l1.setText("TEXT :" + text);
                        l2.setText("ID :" + id);
                        l3.setText("LEVEL :" + level);
                        l4.setText("X :" + x);
                        l5.setText("Y :" + y);
                        l6.setText("W :" + w);
                        l7.setText("H :" + h);
                        l8.setText(color.toString());

                    }
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

            });


        }
    }

    public static void main(String[] args) {
        new NodeTest();
    }
}
