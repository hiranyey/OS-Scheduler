import java.awt.*;

class Process
{
    int TimeRemaining,Priority, sValue;
    int Id;
    Process left, right;
    int Time;
    Color color;
    Process(int time,int priority,int id)
    {
        this(time,priority,id, null, null);
    }
    private Process(int time,int priority, int id,Process left, Process right)
    {
        this.TimeRemaining = time;
        this.Time=time;
        this.Id=id;
        this.color=new Color((int)(Math.random() * 0x1000000));
        this.Priority = priority;
        this.left = left;
        this.right = right;
        this.sValue = 0;
    }
}
