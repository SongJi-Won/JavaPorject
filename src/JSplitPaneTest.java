import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.google.gson.Gson;
import javax.swing.event.MenuEvent;
//import javax.swing.event.MenuListener;
//import javax.swing.JToolBar;

public class JSplitPaneTest extends JFrame {

    private transient MyTextEditorPane myTextEditorPane;
    private transient MyMindMapPane myMindMapPane;
    private transient MyAttributePane myAttributePane;

    private transient SplitPaneMouseListener splitPaneMouseListener;


    private MenuListener menuListener;

    private Node clickedNode;

    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem anotherNameSaveMenuItem;
    private JMenuItem closeMenuItem;
    private JMenuItem applyMenuItem;
    private JMenuItem changeMenuItem;


    private ToolBarListener toolBarListener;

    private JToolBar jToolBar;
    private JButton newToolBtn;
    private JButton openToolBtn;
    private JButton saveToolBtn;
    private JButton anotherNameSaveToolBtn;
    private JButton closeToolBtn;
    private JButton applyToolBtn;
    private JButton changeToolBtn;


    private SaveHelper[] saveHelpers;


    public JSplitPaneTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myMindMapPane = new MyMindMapPane();
        myAttributePane = new MyAttributePane(this);

//        myTextEditorPane = new MyTextEditorPane(this, myMindMapPane, myAttributePane, myMindMapPane.getWidth()/2, myMindMapPane.getHeight()/2);
        myTextEditorPane = new MyTextEditorPane(this, myMindMapPane, myAttributePane/*, 350, 350*/);

        this.clickedNode = null;

        splitPaneMouseListener = new SplitPaneMouseListener();


        menuListener = new MenuListener();
        toolBarListener = new ToolBarListener();

        Node forError = new Node(0, "", 0, 0, 0, 0, Color.white, 0, null, this, myAttributePane);

        myMindMapPane.addNode(forError);
        myMindMapPane.remove(forError);

        myMindMapPane.addMouseListener(splitPaneMouseListener);


        JScrollPane jScrollTextArea = new JScrollPane(myTextEditorPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        jScrollTextArea.setSize(new Dimension(300, 800));
//        jScrollTextArea.setMinimumSize(new Dimension(300, 800));

        JScrollPane jScrollMindMap = new JScrollPane(myMindMapPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        jScrollMindMap.setSize(new Dimension(800, 800));
//        jScrollMindMap.setMinimumSize(new Dimension(800, 800));
//        jScrollMindMap.setPreferredSize(new Dimension(800, 800));

        JScrollPane jScrollAttribute = new JScrollPane(myAttributePane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        jScrollAttribute.setSize(new Dimension(300, 800));
//        jScrollAttribute.setMinimumSize(new Dimension(300,800));


        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollMindMap, jScrollAttribute);
        jSplitPane1.setResizeWeight(0.99);
//        jSplitPane1.setDividerLocation(800);
        jSplitPane1.setLeftComponent(jScrollMindMap);
        jSplitPane1.setRightComponent(jScrollAttribute);


        JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollTextArea, jSplitPane1);

//<<<<<<< HEAD
//        jSplitPane2.setResizeWeight(0.3);
//=======
        jSplitPane2.setResizeWeight(0.01);
//        jSplitPane1.setDividerLocation(300);
        jSplitPane2.setLeftComponent(jScrollTextArea);
        jSplitPane2.setRightComponent(jSplitPane1);

        add(jSplitPane2);
        setSize(1400, 1000);
        jMenuBar = new JMenuBar();

        jMenu = new JMenu("Menu Bar");
        jMenuBar.add(jMenu);

        newMenuItem = new JMenuItem("new");
        openMenuItem = new JMenuItem("open");
        saveMenuItem = new JMenuItem("save");
        anotherNameSaveMenuItem = new JMenuItem("save to another name");
        closeMenuItem = new JMenuItem("close");
        applyMenuItem = new JMenuItem("apply");
        changeMenuItem = new JMenuItem("change");

        newMenuItem.addActionListener(menuListener);
        openMenuItem.addActionListener(menuListener);
        saveMenuItem.addActionListener(menuListener);
        anotherNameSaveMenuItem.addActionListener(menuListener);
        closeMenuItem.addActionListener(menuListener);
        applyMenuItem.addActionListener(menuListener);
        changeMenuItem.addActionListener(menuListener);

        jMenu.add(newMenuItem);
        jMenu.add(openMenuItem);
        jMenu.add(saveMenuItem);
        jMenu.add(anotherNameSaveMenuItem);
        jMenu.add(closeMenuItem);
        jMenu.add(applyMenuItem);
        jMenu.add(changeMenuItem);

        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);


        jToolBar = new JToolBar("Tool Bar");

        newToolBtn = new JButton("new");
        openToolBtn = new JButton("open");
        saveToolBtn = new JButton("save");
        anotherNameSaveToolBtn = new JButton("save to another name");
        closeToolBtn = new JButton("close");
        applyToolBtn = new JButton("apply");
        changeToolBtn = new JButton("change");

        newToolBtn.addActionListener(toolBarListener);
        openToolBtn.addActionListener(toolBarListener);
        saveToolBtn.addActionListener(toolBarListener);
        anotherNameSaveToolBtn.addActionListener(toolBarListener);
        closeToolBtn.addActionListener(toolBarListener);
        applyToolBtn.addActionListener(toolBarListener);
        changeToolBtn.addActionListener(toolBarListener);

        jToolBar.add(newToolBtn);
        jToolBar.add(openToolBtn);
        jToolBar.add(saveToolBtn);
        jToolBar.add(anotherNameSaveToolBtn);
        jToolBar.add(closeToolBtn);
        jToolBar.add(applyToolBtn);
        jToolBar.add(changeToolBtn);


        this.setLayout(new BorderLayout());

        this.add(jToolBar, "North");
        this.add(jSplitPane2, "Center");
        setSize(1400, 800);
        setVisible(true);

    }

    public static void main(String[] args) {
        new JSplitPaneTest();
    }


    public void reset() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myMindMapPane = new MyMindMapPane();
        myAttributePane = new MyAttributePane(this);
//        myTextEditorPane = new MyTextEditorPane(this, myMindMapPane, myAttributePane, myMindMapPane.getWidth()/2, myMindMapPane.getHeight()/2);
        myTextEditorPane = new MyTextEditorPane(this, myMindMapPane, myAttributePane/*, 350, 350*/);

        this.clickedNode = null;

        splitPaneMouseListener = new SplitPaneMouseListener();

        menuListener = new MenuListener();

        toolBarListener = new ToolBarListener();

        Node forError = new Node(0, "", 0, 0, 0, 0, Color.white, 0, null, this, myAttributePane);

        myMindMapPane.addNode(forError);
        myMindMapPane.remove(forError);

        myMindMapPane.addMouseListener(splitPaneMouseListener);


        JScrollPane jScrollTextArea = new JScrollPane(myTextEditorPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane jScrollMindMap = new JScrollPane(myMindMapPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane jScrollAttribute = new JScrollPane(myAttributePane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollMindMap, jScrollAttribute);
        jSplitPane1.setResizeWeight(0.99);
        jSplitPane1.setLeftComponent(jScrollMindMap);
        jSplitPane1.setRightComponent(jScrollAttribute);


        JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollTextArea, jSplitPane1);
        jSplitPane2.setResizeWeight(0.01);
        jSplitPane2.setLeftComponent(jScrollTextArea);
        jSplitPane2.setRightComponent(jSplitPane1);


        jMenuBar = new JMenuBar();

        jMenu = new JMenu("Menu Bar");
        jMenuBar.add(jMenu);

        newMenuItem = new JMenuItem("new");
        openMenuItem = new JMenuItem("open");
        saveMenuItem = new JMenuItem("save");
        anotherNameSaveMenuItem = new JMenuItem("save to another name");
        closeMenuItem = new JMenuItem("close");
        applyMenuItem = new JMenuItem("apply");
        changeMenuItem = new JMenuItem("change");

        newMenuItem.addActionListener(menuListener);
        openMenuItem.addActionListener(menuListener);
        saveMenuItem.addActionListener(menuListener);
        anotherNameSaveMenuItem.addActionListener(menuListener);
        closeMenuItem.addActionListener(menuListener);
        applyMenuItem.addActionListener(menuListener);
        changeMenuItem.addActionListener(menuListener);

        jMenu.add(newMenuItem);
        jMenu.add(openMenuItem);
        jMenu.add(saveMenuItem);
        jMenu.add(anotherNameSaveMenuItem);
        jMenu.add(closeMenuItem);
        jMenu.add(applyMenuItem);
        jMenu.add(changeMenuItem);

        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);


        jToolBar = new JToolBar("Tool Bar");

        newToolBtn = new JButton("new");
        openToolBtn = new JButton("open");
        saveToolBtn = new JButton("save");
        anotherNameSaveToolBtn = new JButton("save to another name");
        closeToolBtn = new JButton("close");
        applyToolBtn = new JButton("apply");
        changeToolBtn = new JButton("change");

        newToolBtn.addActionListener(toolBarListener);
        openToolBtn.addActionListener(toolBarListener);
        saveToolBtn.addActionListener(toolBarListener);
        anotherNameSaveToolBtn.addActionListener(toolBarListener);
        closeToolBtn.addActionListener(toolBarListener);
        applyToolBtn.addActionListener(toolBarListener);
        changeToolBtn.addActionListener(toolBarListener);

        jToolBar.add(newToolBtn);
        jToolBar.add(openToolBtn);
        jToolBar.add(saveToolBtn);
        jToolBar.add(anotherNameSaveToolBtn);
        jToolBar.add(closeToolBtn);
        jToolBar.add(applyToolBtn);
        jToolBar.add(changeToolBtn);


        this.setLayout(new BorderLayout());

        this.add(jToolBar, "North");
        this.add(jSplitPane2, "Center");
        setSize(1400, 800);
        setVisible(true);

    }


    public MyTextEditorPane getMyTextEditorPane() {
        return myTextEditorPane;
    }

    public MyMindMapPane getMyMindMapPane() {
        return myMindMapPane;
    }

    public MyAttributePane getMyAttributePane() {
        return myAttributePane;
    }

    public Node getClickedNode() {
        return clickedNode;
    }

    public void setClickedNode(Node clickedNode) {

        if (this.clickedNode != null) {

            this.clickedNode.setBackground(this.clickedNode.getColor());
        }
        this.clickedNode = clickedNode;
    }





    class SplitPaneMouseListener extends MouseAdapter {


        @Override
        public void mouseClicked(MouseEvent e) {

            if (clickedNode != null) {

                clickedNode.setBackground(clickedNode.getColor());
            }
            clickedNode = null;
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
    }



    class MindMapPaneContainerListener extends ContainerAdapter {

        public MindMapPaneContainerListener() {
            super();
        }

        @Override
        public void componentAdded(ContainerEvent e) {
            super.componentAdded(e);

//            if (e.getSource()송지원 여기부터
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            super.componentRemoved(e);
        }
    }




    class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == newMenuItem) {

                //새로 만들기 하는 코드
                reset();
            }
            else if (e.getSource() == openMenuItem) {

                //저장했던 파일 열기 하는 코드
            }
            else if (e.getSource() == saveMenuItem) {

                //저장하는 코드
                Component[] components = myMindMapPane.getComponents();
                saveHelpers = new SaveHelper[components.length];

                int id;
                String text;
                int nodeX;
                int nodeY;
                int nodeW;
                int nodeH;
                Point point;
                Dimension dimension;
                Color color;
                int level;
                Node parent;

                Node temp;

                for (int i=0; i<components.length; i++) {

                    temp = (Node)components[i];
                    id = temp.getId();
                    text = temp.getText();
                    nodeX = temp.getNodeX();
                    nodeY = temp.getNodeY();
                    nodeW = temp.getNodeW();
                    nodeH = temp.getNodeH();
                    point = temp.getPoint();
                    dimension = temp.getDimension();
                    color = temp.getColor();
                    level = temp.getLevel();
                    parent = temp.getParentNode();

                    saveHelpers[i] = new SaveHelper(id, text, nodeX, nodeY, nodeW, nodeH, color, level, parent.getId());

                }
            }
            else if (e.getSource() == anotherNameSaveMenuItem) {

                //다른 이름으로 저장하는 코드
            }
            else if (e.getSource() == closeMenuItem) {

                System.exit(0);
            }
            else if (e.getSource() == applyMenuItem) {

                //적용하는 코드
                if (myTextEditorPane != null) {

                    if (myTextEditorPane.getApplyBtn() != null) {

                        myTextEditorPane.getApplyBtn().doClick();
                    }
                }
            }
            else if (e.getSource() == changeMenuItem) {

                //속성 페인 내용을 화면에 적용하는 코드
                if (myAttributePane != null) {

                    if (myAttributePane.getChangeBtn() != null) {

                        myAttributePane.getChangeBtn().doClick();
                    }
                }
            }

        }


    }


    class ToolBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == newToolBtn) {


                //새로 만들기 하는 코드
                reset();
            }
            else if (e.getSource() == openToolBtn) {

                //저장했던 파일 열기 하는 코드
            }
            else if (e.getSource() == saveToolBtn) {

                //저장하는 코드
                Component[] components = myMindMapPane.getComponents();
                saveHelpers = new SaveHelper[components.length];

                int id;
                String text;
                int nodeX;
                int nodeY;
                int nodeW;
                int nodeH;
                Point point;
                Dimension dimension;
                Color color;
                int level;
                Node parent;

                Node temp;

                for (int i=0; i<components.length; i++) {

                    temp = (Node) components[i];
                    id = temp.getId();
                    text = temp.getText();
                    nodeX = temp.getNodeX();
                    nodeY = temp.getNodeY();
                    nodeW = temp.getNodeW();
                    nodeH = temp.getNodeH();
                    point = temp.getPoint();
                    dimension = temp.getDimension();
                    color = temp.getColor();
                    level = temp.getLevel();
                    parent = temp.getParentNode();

                    saveHelpers[i] = new SaveHelper(id, text, nodeX, nodeY, nodeW, nodeH, color, level, parent.getId());
                }
            }
            else if (e.getSource() == anotherNameSaveToolBtn) {

                //다른 이름으로 저장하는 코드
            }
            else if (e.getSource() == closeToolBtn) {

                System.exit(0);
            }
            else if (e.getSource() == applyToolBtn) {

                //적용하는 코드
                if (myTextEditorPane != null) {

                    if (myTextEditorPane.getApplyBtn() != null) {

                        myTextEditorPane.getApplyBtn().doClick();
                    }
                }
            } else if (e.getSource() == changeToolBtn) {

                //속성 페인 내용을 화면에 적용하는 코드
                if (myAttributePane != null) {

                    if (myAttributePane.getChangeBtn() != null) {

                        myAttributePane.getChangeBtn().doClick();
                    }
                }
            }

        }
    }
}
