import javax.swing.*;

class FormShow
{
    Form form;
    void show(){
        form=new Form();
        JFrame jFrame=new JFrame("Scheduler");
        jFrame.setContentPane(form.JPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1000,700);
        form.JPanel.setBorder(BorderFactory.createTitledBorder("OS-"+MethodSelection.strategy[Main.MethodType]));
        jFrame.setVisible(true);
    }
}
