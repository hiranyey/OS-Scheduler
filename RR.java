import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class RR extends Algorithms
{
    private ArrayList<Process> root;
    private JTextArea area;
    private int Throughput;
    private JButton button;
    private JPanel jPanel;
    private  ArrayList<Button> list;
    RR(JTextArea textArea,JButton jButton,JPanel panel){
        root=new ArrayList<>();
        list=new ArrayList<>();
        this.area=textArea;
        Throughput=0;
        this.button=jButton;
        this.jPanel=panel;
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
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
    public void update() {
        if(!root.isEmpty()) {
            Process process = root.get(0);
            addGantt();
            root.remove(0);
            process.TimeRemaining -= Main.BurstType;

            if (process.TimeRemaining <= 0) {
                Throughput-=process.Time;
                area.setText("");
                for (Process node : root) {
                    area.append("ID:- " + String.valueOf(node.Id) + " Time:- " + String.valueOf(node.TimeRemaining) + " Priority:- " + String.valueOf(node.Priority) + "\n");
                }
                button.setText("ThroughPut:- "+String.valueOf(throughPut()));
            } else {
                root.add(process);
            }
            try{
                FileWriter fstream = new FileWriter(MasterCpu.Files.get(process.Id)+".txt",true);
                BufferedWriter fbw = new BufferedWriter(fstream);
                fbw.write(Main.append);
                fbw.newLine();
                fbw.close();
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
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

