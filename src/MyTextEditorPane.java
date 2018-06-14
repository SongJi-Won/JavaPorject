import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.gson.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.GsonBuilder;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.lang.reflect.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;
import java.util.stream.StreamSupport;


public class MyTextEditorPane extends JPanel {

    private JPanel jPanel;
    private  JLabel title;
    private   JTextArea textArea;
    private   JButton applyBtn;


    private JSplitPaneTest jSplitPaneTest;
    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;

    private    ApplyButtonListener applyButtonListener;

    private    int id;

    private   int centerX;
    private   int centerY;

//    public MyTextEditorPane(JSplitPaneTest jSplitPaneTest, MyMindMapPane myMindMapPane, MyAttributePane myAttributePane, int centerX, int centerY) {
    private double baseTheta;

    public MyTextEditorPane(JSplitPaneTest jSplitPaneTest, MyMindMapPane myMindMapPane, MyAttributePane myAttributePane/*, int centerX, int centerY*/) {
        title = new JLabel("Text Label Pane");
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setFont(new Font("돋움", Font.BOLD, 20));

        textArea = new JTextArea(20, 14);
        textArea.setTabSize(2);

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

        jPanel = this;

    }


    public JButton getApplyBtn() {
        return this.applyBtn;
    }


    class ApplyButtonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            String input = textArea.getText();

            centerX = jPanel.getWidth() / 2;
            centerY = jPanel.getHeight() / 2;
            parseToTree(input);

        }
    }

    public void setNodesTheta(Node[] nodes, int nodeCnt) {

        int i;
        baseTheta = 360 / nodeCnt;

        for (i = 0; i < nodes.length; i++) {

            if (nodes[i] != null) {

                nodes[i].setTheta(baseTheta * i);
            }
        }
    }




    public double getRandomTheta(Node input) {

        double theta = input.getTheta();
        Random random = new Random();
        int bounds = new Double(baseTheta).intValue();

        if (theta == -1.0) {

        } else if (theta == Math.PI) {

            System.out.println("초기화 되지 않은 값 들어옴");
            input.setTheta(input.getParentNode().getTheta());
            theta = input.getTheta();
            theta = theta + random.nextInt(bounds) - bounds;

        } else {

            theta = theta + random.nextInt(bounds) - bounds;
        }

        return theta;
    }

    private void parseToTree(String str) {

        Color background;
        Gson gson = new Gson();
//        GsonBuilder gsonBuliderTest = new GsonBuilder();
//        gsonBuliderTest.addDeserializationExclusionStrategy(new SuperclassExclusionStrategy());
//        gsonBuliderTest.addSerializationExclusionStrategy(new SuperclassExclusionStrategy());
//        Gson gson = gsonBuliderTest.create();
        String json;
        SaveHelper[] saveArr = new SaveHelper[40];
        Node[] parentNodeArr = new Node[40];
        Node[] nodeArr = new Node[40];
        Node[] levelOneArray = new Node[40];
        String[] input;
        String temp[] = new String[40];
        String parent[] = new String[40];
        int levelArr[] = new int[40];
        String tempStr;
        int depth, tempNum, id = 0;
        int indexForLevelArray = 0;
        double d = 0;

        Node rootNode = new Node(0, str.split("\n")[0], centerX, centerY, 90, 50, Color.gray, 0, null, jSplitPaneTest, myAttributePane); //가운데 루트 노드


        input = str.split("\n");

        for (int i = 0; i < input.length; i++) {
            tempNum = i; // 혹시 몰라서 쓰는 복사용 수
            temp[i] = str.split("\n")[i];
            rootNode.setTheta(-1.0);
            d = Math.sqrt(rootNode.getNodeW() * rootNode.getNodeW() * 1.0 + 4 * rootNode.getNodeH() * rootNode.getNodeH() * 1.0); //루트 노드 기반으로 각 노드간 벌릴 거리 구하는 식


            if (i == 0) {
                parent[i] = temp[i]; //본인을 루트로
                parentNodeArr[i] = rootNode;
                nodeArr[i] = rootNode;
                levelArr[i] = 0;
            }


            depth = indentCheck(temp[i]);
            tempStr = temp[i].replaceAll("\\s+", "");  // 공백제거   //바로 temp[i]에 넣으면 에러뜸 이유 불명
            temp[i] = tempStr; // 공백제거후 다시 넣어줌
            levelArr[i] = depth;
            background = colorSelectFromLevel(depth);

            Node newNode = new Node(id++, temp[i], centerX, centerY, 90, 50, background, depth, null, jSplitPaneTest, myAttributePane); //여기세 rootNode 부분에 새로 생성하는 노드 newNode의 부모 노드 객체 넣어줘야 해요!

            nodeArr[i] = newNode;  // parent 설정을 위해 배열에 넣어줌
            if (depth > 0) {

                if (depth == 1) {
                    levelOneArray[indexForLevelArray] = newNode;
                    indexForLevelArray++;
                }

                if (levelArr[i - 1] < depth) {   // level1 증가
                    //parent[i] = temp[i - 1];
                    parentNodeArr[i] = nodeArr[i - 1];
                } else if (levelArr[i - 1] == depth) {  //동일 레벨
                    // parent[i] = parent[i - 1];
                    parentNodeArr[i] = parentNodeArr[i - 1];
                } else if (levelArr[i - 1] > depth) {
                    while (true) {
                        if (levelArr[tempNum - 2] == depth) {
                            // parent[i] = parent[tempNum - 2];
                            parentNodeArr[i] = parentNodeArr[tempNum - 2];
                            break;
                        } else {
                            tempNum--;
                        }
                    }
                }
            }

            newNode.setParent(parentNodeArr[i]);
        }


        setNodesTheta(levelOneArray, indexForLevelArray + 1);


        for (int i = 0; i < nodeArr.length; i++) {
            Node newNode = nodeArr[i];
            if (newNode != null) {
                Node parentNode = newNode.getParentNode();

                if (parentNode != null) {

                    if (parentNode.getTheta() != -1.0) {

                        newNode.setTheta(parentNode.getTheta());
                    }

                    if (parentNode != null) {

                        double theta = getRandomTheta(newNode);

                        int dx = new Double(Math.cos(Math.toRadians(theta * 1.0)) * d).intValue();
                        int dy = new Double(Math.sin(Math.toRadians(theta * 1.0)) * d).intValue();

                        Point newPont = new Point(parentNode.getNodeX() + dx, parentNode.getNodeY() + dy);
                        newNode.setLocation(newPont);
                        newNode.update(newPont, newNode.getDimension());
                    }

                    Node forError = new Node(-1, "", 0, 0, 0, 0, Color.white, -1, rootNode, jSplitPaneTest, myAttributePane); //애는 그 마지막 노드 사이즈 개떡같이 나오는 부분 처리 해주는 애에요
                    forError.setOpaque(false);
                    forError.setVisible(false);
                    myMindMapPane.addNode(newNode);
                    myMindMapPane.addNode(forError);
                }
            }
        }


      System.out.println("end");


    }

    public int indentCheck(String str) {
        int i = 0;
        int depth = 0;
        while (true) {
            if (str.charAt(i) != '\t') {
                break;
            } else {
                i += 1;
                depth += 1;

            }
        }

        return depth;
    }

    private Color colorSelectFromLevel(int depth){
        Color color;

        if(depth ==0){
            color = Color.gray;
        }else if(depth ==1){
            color =  Color.cyan;
        }else if(depth ==2){
            color = Color.green;
        }else if(depth == 3){
            color = Color.magenta;
        }else{
            color = Color.pink;
        }

        return color;
    }

//    public class SuperclassExclusionStrategy implements ExclusionStrategy
//    {
//        public boolean shouldSkipClass(Class<?> arg0)
//        {
//            return false;
//        }
//
//        public boolean shouldSkipField(FieldAttributes fieldAttributes)
//        {
//            String fieldName = fieldAttributes.getName();
//            Class<?> theClass = fieldAttributes.getDeclaringClass();
//
//            return isFieldInSuperclass(theClass, fieldName);
//        }
//
//        private boolean isFieldInSuperclass(Class<?> subclass, String fieldName)
//        {
//            Class<?> superclass = subclass.getSuperclass();
//            Field field;
//
//            while(superclass != null)
//            {
//                field = getField(superclass, fieldName);
//
//                if(field != null)
//                    return true;
//
//                superclass = superclass.getSuperclass();
//            }
//
//            return false;
//        }
//
//        private Field getField(Class<?> theClass, String fieldName)
//        {
//            try
//            {
//                return theClass.getDeclaredField(fieldName);
//            }
//            catch(Exception e)
//            {
//                return null;
//            }
//        }
//    }
}
