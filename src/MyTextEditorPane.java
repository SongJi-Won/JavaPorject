import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyTextEditorPane extends JPanel {

    private JLabel title;

    private JTextArea textArea;

    private JButton applyBtn;

    private MyMindMapPane myMindMapPane;
    private MyAttributePane myAttributePane;


    public MyTextEditorPane(MyMindMapPane myMindMapPane, MyAttributePane myAttributePane) {
        title = new JLabel("Text Label Pane");

        textArea = new JTextArea(20, 14);


        applyBtn = new JButton("적용");
        applyBtn.addActionListener(new ApplyActionListener());


        this.myMindMapPane = myMindMapPane;
        this.myAttributePane = myAttributePane;

//        this.applyButtonListener = new ApplyButtonListener();
//        applyBtn.addActionListener(this.applyButtonListener);


        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(applyBtn, BorderLayout.SOUTH);

    }


    private class ApplyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            //System.out.print(textArea.getText());
            String input = textArea.getText();
            parseToTree(input);

        }
    }

    public void parseToTree(String str) {

        String temp[] = new String[40];
        String parent[] = new String[40];
        int levelArr[] = new int[40];
        String tempStr;
        int level;
        int tempNum;

        for (int i = 0; i < 20; i++) {
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

            System.out.println("텍스트 : " + tempStr + " 레벨 : " + level + " 부모 : " + parent[i]);
            // System.out.println(i);
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
