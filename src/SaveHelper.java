import java.awt.*;


public class SaveHelper {

    private  int idTest;
    private   String textOfNodeTest;
    private  int nodeXTest;
    private  int nodeYTest;


    private  int nodeWTest;
    private  int nodeHTest;


    private  Color colorTest;

    private  int levelTest;


    private int parentIndex;

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getTextOfNodeTest() {
        return textOfNodeTest;
    }

    public void setTextOfNodeTest(String textOfNodeTest) {
        this.textOfNodeTest = textOfNodeTest;
    }

    public int getNodeXTest() {
        return nodeXTest;
    }

    public void setNodeXTest(int nodeXTest) {
        this.nodeXTest = nodeXTest;
    }

    public int getNodeYTest() {
        return nodeYTest;
    }

    public void setNodeYTest(int nodeYTest) {
        this.nodeYTest = nodeYTest;
    }

    public int getNodeWTest() {
        return nodeWTest;
    }

    public void setNodeWTest(int nodeWTest) {
        this.nodeWTest = nodeWTest;
    }

    public int getNodeHTest() {
        return nodeHTest;
    }

    public void setNodeHTest(int nodeHTest) {
        this.nodeHTest = nodeHTest;
    }

    public Color getColorTest() {
        return colorTest;
    }

    public void setColorTest(Color colorTest) {
        this.colorTest = colorTest;
    }

    public int getLevelTest() {
        return levelTest;
    }

    public void setLevelTest(int levelTest) {
        this.levelTest = levelTest;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public SaveHelper(int id, String text, int x, int y, int w, int h, Color color, int level, int index) {

        this.idTest = id;

        this.textOfNodeTest = text;

        this.nodeXTest = x;
        this.nodeYTest = y;
        this.nodeWTest = w;
        this.nodeHTest = h;

        this.colorTest = color;
        this.levelTest = level;

        this.parentIndex = index;



    }

}
