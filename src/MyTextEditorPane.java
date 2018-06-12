import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyTextEditorPane extends JPanel {

    private JLabel title;

    private JTextArea textArea;

    private JButton applyBtn;

    private JSplitPaneTest jSplitPaneTest;
    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;

    private ApplyButtonListener applyButtonListener;

    private int id;

    private int centerX;
    private int centerY;

    public MyTextEditorPane(JSplitPaneTest jSplitPaneTest, MyMindMapPane myMindMapPane, MyAttributePane myAttributePane, int centerX, int centerY) {
        title = new JLabel("Text Label Pane");
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setFont(new Font("돋움", Font.BOLD, 20));

        textArea = new JTextArea(20, 14);

        applyBtn = new JButton("적용");

        this.jSplitPaneTest = jSplitPaneTest;
        this.myMindMapPane = myMindMapPane;
        this.myAttributePane = myAttributePane;

        this.applyButtonListener = new ApplyButtonListener();
        applyBtn.addActionListener(this.applyButtonListener);

        this.id = 0;
        this.centerX = centerX;
        this.centerY = centerY;



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

//            Node newNode1 = new Node(0, "Node1", 100, 500, 300, 100, Color.pink, 0, null, myAttributePane);
//            Node newNode2 = new Node(0, "Node2", 400, 600, 300, 100, Color.pink, 0, newNode1, myAttributePane);
//            Node newNode3 = new Node(0, "Node3", 300, 700, 300, 100, Color.pink, 0, newNode2, myAttributePane);
//
//            myMindMapPane.addNode(newNode1);
//            myMindMapPane.addNode(newNode2);
//            myMindMapPane.addNode(newNode3);
//
//            myMindMapPane.setVisible(true);

            double d;




            //적채형 여기서 부터가 새로 노드 넣어주는 부분이에요
            //그 줄 갯수? 받아오셔서 반복문 도시면서 하시면 될듯!
            Node rootNode = new Node(id++, "읽어들여온 정보 ", centerX, centerY, 300, 100, Color.pink, 0, null, jSplitPaneTest,  myAttributePane); //가운데 루트 노드

            d = Math.sqrt(rootNode.getNodeW()*rootNode.getNodeW()*0.1 + 4*rootNode.getNodeH()*rootNode.getNodeH()*0.1); //루트 노드 기반으로 각 노드간 벌릴 거리 구하는 식

            Node newNode = new Node(id++, "Node2", centerX, centerY, 300, 100, Color.pink, 0, rootNode, jSplitPaneTest, myAttributePane); //여기세 rootNode 부분에 새로 생성하는 노드 newNode의 부모 노드 객체 넣어줘야 해요!
            //이 부분이 좀 어려울것같은데 지금 드는 생각으로는 각 노드별로 아이디 값 저장해둔 다음에 아이디값 주면 노드 객체 리턴는 메소드 짜서 그 메소드로 노드 객체 받아서 넣는 방법이 떠오르네요

            Node parentNode = newNode.getParentNode(); //그리고 이부분은 부모가 있으면 부모와 위치 기반으로 자식 위치 설정해주는 부분인데 좀 안되요...;;생각만큼
            if (parentNode != null){

                int dx = new Double(d*Math.cos(Math.toRadians(-225+90*id))).intValue();
                int dy = new Double(d*Math.sin(Math.toRadians(-225+90*id))).intValue();

                System.out.println("!!!!!! dx :"+dx+"   / dy :"+dy);

                newNode.setLocation(parentNode.getNodeX()+dx, parentNode.getNodeY()+dy);
            }



            myMindMapPane.addNode(parentNode); //여기서는 제가 2개만 명시적으로 때려박아서 두줄만으로 노드 추가해요. 포문 같은걸로 돌리시면 될듯!
            myMindMapPane.addNode(newNode);


            //여기가 끝!


        }
    }

    public void parseToTree(String str) {


        String[] input;
        String temp[] = new String[40];
        String parent[] = new String[40];
        int levelArr[] = new int[40];
        String tempStr;
        int level;
        int tempNum;

        input = str.split("\n");
        //System.out.println(input.length);

        for (int i = 0; i < input.length; i++) {
            tempNum = i; // 혹시 몰라서 쓰는 복사용 수

            if (i == 0) {
                parent[i] = temp[i];
                levelArr[i] = 0;
            }

            temp[i] = str.split("\n")[i];
            level = indentCheck(temp[i]);
            tempStr = temp[i].replaceAll("\\s+", "");  // 공백제거   //바로 temp[i]에 넣으면 에러뜸 이유 불명
            temp[i] = tempStr; // 공백제거후 다시 넣어줌
            levelArr[i] = level;
            if (level > 0) {
                if (levelArr[i - 1] < level) {   // level1 증가
                    parent[i] = temp[i - 1];
                } else if (levelArr[i - 1] == level) {  //동일 레벨
                    parent[i] = parent[i - 1];
                } else if (levelArr[i - 1] > level) {
                    while (true) {
                        if (levelArr[tempNum - 2] == level) {
                            parent[i] = parent[tempNum - 2];
                            break;
                        } else {
                            tempNum--;
                        }
                    }
                }


            }
//            Node newNode = new Node(5, temp[i], 100, 200, 300, 100, Color.pink, level, myAttributePane);
//            myMindMapPane.addNode(newNode);
//            System.out.println("텍스트 : " + tempStr + " 레벨 : " + level + " 부모 : " + parent[i]);
        }


        System.out.println("end");


    }

    public int indentCheck(String str) {
        int i = 0;
        int level = 0;
        while (true) {
            if (str.charAt(i) != ' ') {
                break;
            } else {
                i += 4;
                level += 1;
            }
        }

        return level;
    }
}
