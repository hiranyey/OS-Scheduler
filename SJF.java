import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SJF extends Algorithms
{
    private Process root;
    private int size;
    private  JTextArea area;
    private int Throughput;
    private JButton button;
    private JPanel jPanel;
    private ArrayList<Button> list;
    SJF(JTextArea textArea,JButton jButton,JPanel panel){
        root=null;
        size=0;
        this.area=textArea;
        Throughput=0;
        this.button=jButton;
        this.jPanel=panel;
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        list=new ArrayList<>();
    }

    @Override
    public void clear() {
        root=null;
        size=0;
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
        root = merge(node, root);
        Throughput+=node.Time;
        size++;
        button.setText("ThroughPut:- "+String.valueOf(throughPut()));
    }

    @Override
    public void update() {
    if(root!=null)
    {
        root.TimeRemaining-=Main.BurstType;
        addGantt();
        if(root.TimeRemaining<=0)
        {
            Throughput-=root.Time;
            delete();
            size--;
            button.setText("ThroughPut:- "+String.valueOf(throughPut()));

        }
    }
    }

    @Override
    public void addGantt() {
        Button button=new Button(""+root.Id);
        button.setBackground(root.color);
        jPanel.add(button);
        list.add(button);
        if(list.size()>10){
            Button button1=list.get(0);
            list.remove(0);
            jPanel.remove(button1);
        }
    }
    @Override
    public Process getRoot()
    {
        return root;
    }

    private void delete()
    {
        if (root==null)
            return;
        root = merge(root.left, root.right);
        area.setText("");
        BFS(root);

    }
    private void BFS(Process node)
    {
        if(node!=null)
        {
            area.append("ID:- "+String.valueOf(node.Id)+" Time:- "+String.valueOf(node.TimeRemaining)+" Priority:- "+String.valueOf(node.Priority)+"\n");
            BFS(node.left);
            BFS(node.right);
        }
    }
    private Process merge(Process Left, Process Right)
    {
        if (Left == null)
            return Right;
        if (Right == null)
            return Left;
        if (Left.TimeRemaining > Right.TimeRemaining)
        {
            Process temp = Left;
            Left = Right;
            Right = temp;
        }
        Left.right = merge(Left.right, Right);
        if(Left.left == null)
        {
            Left.left = Left.right;
            Left.right = null;
        }
        else
        {
            if(Left.left.sValue < Left.right.sValue)
            {
                Process temp = Left.left;
                Left.left = Left.right;
                Left.right = temp;
            }
            Left.sValue = Left.right.sValue + 1;
        }
        return Left;
    }

    @Override
    public float throughPut() {
        if(size==0)return 0;
        return (float)Throughput/size;
    }

    @Override
    public void mergeProcess(Process second) {
       root= merge(root,second);
    }

    @Override
    public void addNewProcess() {
        area.setText("");
        BFS(root);
    }
}
