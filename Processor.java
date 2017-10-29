import javax.swing.*;

public class Processor implements Runnable {
    private JLabel label;
    Algorithms algorithms;
    public Processor(JLabel label,JTextArea textArea,JButton jButton,JPanel panel)
    {
        this.label=label;
        jButton.setText(" ");
        label.setText("DEAD");
        switch (Main.MethodType){
            case 0:
                algorithms=new FIFO(textArea,jButton,panel);
                break;
            case 1:
                algorithms=new RR(textArea,jButton,panel);
                break;
            case 2:
                algorithms=new SJF(textArea,jButton,panel);
                break;
            case 3:
                algorithms=new PS(textArea,jButton,panel);
                break;
        }
    }
    void insert(int TimeTaken,int Priority,int ID)
    {
        Process node=new Process(TimeTaken,Priority,ID);
        algorithms.insert(node);
    }
    @Override
    public void run() {
        while (algorithms.getRoot()!=null){
            algorithms.update();
            try {
                Process process=algorithms.getRoot();
                if(process!=null)
                label.setText(String.valueOf(process.Id)+" "+String.valueOf(process.TimeRemaining));
            }catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        label.setText("DEAD");
    }
    public void clear(){
        label.setText("DEAD");
    }
}
