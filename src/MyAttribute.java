import javax.swing.*;
import java.awt.*;

public class MyAttribute extends JPanel {

    private JLabel attrName;
    private boolean editable;
    private  JTextField attrValue;

    public MyAttribute(String name, String value, Boolean editable)
    {
        this.setLayout(new FlowLayout());

        this.attrName = new JLabel(name+": ");
        this.attrName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        this.attrName.setHorizontalTextPosition(SwingConstants.RIGHT);


        this.attrValue = new JTextField(value , 10);
        this.attrValue.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        this.attrValue.setHorizontalAlignment(JTextField.RIGHT);

        this.editable = editable;
        attrValue.setEditable(this.editable);


        this.add(attrName);
        this.add(attrValue);
    }

    public JLabel getAttrName() {
        return this.attrName;
    }

    public JTextField getAttrValue() {
        return this.attrValue;
    }

    public Boolean getEditable(){
        return this.editable;
    }



    public void setAttrName(String newName){

        this.attrName.setText(newName);
    }

    public void setAttrValue(String newValue){

        this.attrValue.setText(newValue);
    }

    public void setEditable(Boolean newEditable) {

        this.editable = newEditable;
        this.attrValue.setEditable(this.editable);
    }


}
