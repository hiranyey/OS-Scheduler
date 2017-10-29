public class Main
{
    static int MethodType;
    static int BurstType = 3;
    static FormShow Form = new FormShow();
    public static void main(String[] args) {
        MethodSelection.select();
        Form.show();
        MasterCpu masterCpu=new MasterCpu();
        masterCpu.Schedule();
        for(Thread thread:masterCpu.threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
