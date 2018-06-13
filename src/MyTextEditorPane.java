import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.GsonBuilder;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.lang.reflect.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;




public class MyTextEditorPane extends JPanel {

    private  JLabel title;
    private   JTextArea textArea;
    private   JButton applyBtn;

    private   MyMindMapPane myMindMapPane;
    private   MyAttributePane myAttributePane;

    private    ApplyButtonListener applyButtonListener;

    private    int id;

    private   int centerX;
    private   int centerY;

    public MyTextEditorPane(MyMindMapPane myMindMapPane, MyAttributePane myAttributePane, int centerX, int centerY) {
        title = new JLabel("Text Label Pane");

        textArea = new JTextArea(20, 14);
        textArea.setTabSize(2);

        applyBtn = new JButton("적용");

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


           // double d;
            String input = textArea.getText();
            parseToTree(input);

        }
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
        int depth, tempNum, id =0;
        int indexForLevelArray = 0;
        double d = 0;

       input = str.split("\n");
       //System.out.println(input.length);

        for (int i = 0; i < input.length; i++) {
            tempNum = i; // 혹시 몰라서 쓰는 복사용 수
            temp[i] = str.split("\n")[i];

            Node rootNode = new Node(0, temp[0], centerX, centerY, 300, 100, Color.pink, 0, null, myAttributePane); //가운데 루트 노드
            d = Math.sqrt(rootNode.getNodeW()*rootNode.getNodeW()*0.1 + 4*rootNode.getNodeH()*rootNode.getNodeH()*0.1); //루트 노드 기반으로 각 노드간 벌릴 거리 구하는 식


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
            Node newNode = new Node(id++, temp[i], centerX, centerY, 300, 100, background, depth, null, myAttributePane); //여기세 rootNode 부분에 새로 생성하는 노드 newNode의 부모 노드 객체 넣어줘야 해요!
            nodeArr[i] = newNode;  // parent 설정을 위해 배열에 넣어줌

            if (depth > 0) {
                if (levelArr[i - 1] < depth) {   // level1 증가
                    //parent[i] = temp[i - 1];
                    parentNodeArr[i] = nodeArr[i-1];
                } else if (levelArr[i - 1] == depth) {  //동일 레벨
                   // parent[i] = parent[i - 1];
                    parentNodeArr[i] = parentNodeArr[i-1];
                } else if (levelArr[i - 1] > depth) {
                    while (true) {
                        if (levelArr[tempNum - 2] == depth) {
                           // parent[i] = parent[tempNum - 2];
                            parentNodeArr[i] = parentNodeArr[tempNum-2];
                            break;
                        } else {
                            tempNum--;
                        }
                    }
                }
            }
            newNode.setParent(parentNodeArr[i]);
            if(depth ==1){
                levelOneArray[indexForLevelArray] = newNode;
                indexForLevelArray++;
            }


            Node parentNode = newNode.getParentNode(); //그리고 이부분은 부모가 있으면 부모와 위치 기반으로 자식 위치 설정해주는 부분인데 좀 안되요...;;생각만큼
            if (parentNode != null){

                int dx = new Double(d*Math.cos(Math.toRadians(-225+90*id))).intValue();
                int dy = new Double(d*Math.sin(Math.toRadians(-225+90*id))).intValue();

                //System.out.println("!!!!!! dx :"+dx+"   / dy :"+dy);

                newNode.setLocation(parentNode.getNodeX()+dx, parentNode.getNodeY()+dy);
            }


            Node forError = new Node(-1, "", 0,0,0,0,Color.white,-1, null, myAttributePane); //애는 그 마지막 노드 사이즈 개떡같이 나오는 부분 처리 해주는 애에요
           // System.out.println(newNode);


            SaveHelper save = new SaveHelper(id, temp[i], centerX, centerY, 300, 100, background, depth, i);
            saveArr[i] = save;
            //json = gson.toJson(save);
            //json = gson.toJson(newNode);
           //System.out.println(json);

            //myMindMapPane.addNode(parentNode); //여기서는 제가 2개만 명시적으로 때려박아서 두줄만으로 노드 추가해요. 포문 같은걸로 돌리시면 될듯!
            myMindMapPane.addNode(newNode);

            myMindMapPane.addNode(forError);

            System.out.println("텍스트 : " + tempStr + " 레벨 : " + depth + " 부모 : " + parent[i]);

        }


        json = gson.toJson(saveArr);
        System.out.println(json);
        try {

            FileWriter file = new FileWriter("/Users/shinjeongmin/JavaPorject/test.json");
            file.write(json);
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
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
