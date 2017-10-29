import javax.swing.*;

class MethodSelection
{
    static String[] strategy= new String[] {
            "First in First out",
            "Round Robin",
            "Shortest Job First",
            "Priority Scheduling"
    };
    static void select(){

        String name=
                (String) JOptionPane.showInputDialog(null, "Please choose a strategy(Default:-FIFO)", "Strategy",
                        JOptionPane.QUESTION_MESSAGE, null,strategy,"");
        if(name==null)
            Main.MethodType=0;
        String burst=JOptionPane.showInputDialog(null,"Enter Burst time for CPU(default is 3)");
        try{
            Main.BurstType=Integer.parseInt(burst);
        }catch (Exception e){
            Main.BurstType=3;
        }
        String ProcessNO=JOptionPane.showInputDialog(null,"Number of Processes to be generated(Default is 20)");
        try{
            MasterCpu.processNo=Integer.parseInt(ProcessNO);
        }catch (Exception e){
            Main.BurstType=20;
        }
        for(int i=0;i<strategy.length;i++)
        {
            if(strategy[i].equals(name))
            {
                Main.MethodType=i;
                break;
            }
        }
    }
}
