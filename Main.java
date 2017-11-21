import javax.swing.*;
public class Main
{
    static int MethodType;
    static int BurstType = 3;
    static String append="ADDED";
    static FormShow Form = new FormShow();
    public static void main(String[] args)
    {
        MethodSelection.select();
        Form.show();
        MasterCpu masterCpu=new MasterCpu();
        long startTime = System.currentTimeMillis();
        masterCpu.Schedule();
        for(Thread thread:masterCpu.threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        JOptionPane.showMessageDialog(null,"TOTAL TIME TAKEN IN SECONDS:-"+(int)elapsedTime/1000);
        System.exit(0);
    }
}
