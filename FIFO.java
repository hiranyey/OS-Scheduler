import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FIFO extends Algorithms
{
    private ArrayList<Process> root;
    private JTextArea area;
    private int Throughput;
    private JButton button;
    private JPanel jPanel;
    private ArrayList<Button> list;
    FIFO(JTextArea textArea,JButton jButton,JPanel panel){
        root=new ArrayList<>();
        this.button=jButton;
        this.area=textArea;
        this.jPanel=panel;
        area.insert("",0);
        Throughput=0;
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        list=new ArrayList<>();
    }

    @Override
    public void clear() {
        root.clear();
        Throughput=0;
        area.setText("");
        button.setText("ThroughPut:- 0");
        for(Button b:list){
            jPanel.remove(b);
        }
        list.clear();
    }

    @Override
    public void insert(Process node) {
        area.append("ID:- "+String.valueOf(node.Id)+" Time:- "+String.valueOf(node.TimeRemaining)+" Priority:- "+String.valueOf(node.Priority)+"\n");
        root.add(node);
        Throughput+=node.Time;
        button.setText("ThroughPut:- "+String.valueOf(throughPut()));
    }

    @Override
    public void update()
    {
        if(!root.isEmpty())
        {
            Process process = root.get(0);
            addGantt();
            process.TimeRemaining -= Main.BurstType;
            if (process.TimeRemaining <= 0)
            {
                Throughput-=process.Time;
                root.remove(0);
                area.setText("");
                for (Process node : root)
                {
                    area.append("ID:- " + String.valueOf(node.Id) + " Time:- " + String.valueOf(node.TimeRemaining) + " Priority:- " + String.valueOf(node.Priority) + "\n");
                }
                button.setText("ThroughPut:- "+String.valueOf(throughPut()));
            }
        }
    }

    @Override
    public void addGantt() {
        Button button=new Button(""+root.get(0).Id);
        button.setBackground(root.get(0).color);
        jPanel.add(button);
        list.add(button);
        if(list.size()>10){
            Button button1=list.get(0);
            list.remove(0);
            jPanel.remove(button1);
        }
    }

    @Override
    public Process getRoot() {
        if(!root.isEmpty())
            return root.get(0);
        else return null;
    }


    @Override
    public float throughPut() {
        if(root.size()==0)return 0;
        return (float)Throughput/root.size();
    }

    @Override
    public void mergeProcess(ArrayList<Process> second) {
        root.addAll(second);
    }

    @Override
    public ArrayList<Process> getParent() {
        return root;
    }

    @Override
    public void addNewProcess() {
        area.setText("");
        for (Process node : root) {
            area.append("ID:- " + String.valueOf(node.Id) + " Time:- " + String.valueOf(node.TimeRemaining) + " Priority:- " + String.valueOf(node.Priority) + "\n");
        }
    }
}
