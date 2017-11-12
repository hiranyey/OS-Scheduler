import java.util.Random;

class MasterCpu
{
    private Processor[] processor;
    private Random random;
    Thread[] threads;
    static int processNo;
    static BTree<Integer,String> Files;
    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    MasterCpu()
    {
        processor=new Processor[4];
        Files=new BTree<>();
        random=new Random();
        Form form=Main.Form.form;
        processor[0]=new Processor(form.Process1,form.textArea1,form.button1,form.list1);
        processor[1]=new Processor(form.Process2,form.textArea2,form.button2,form.list2);
        processor[2]=new Processor(form.Process3,form.textArea3,form.button3,form.list3);
        processor[3]=new Processor(form.Process4,form.textArea4,form.button4,form.list4);
        threads=new Thread[4];
        for(int i=0;i<4;i++){
            threads[i]=new Thread(processor[i]);
        }
    }
    public String generateRandomString(){
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number =random.nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    private Thread Redefine(int i)
    {
        return new Thread(processor[i]);
    }
    void Schedule()
    {
        for(int i=0;i<processNo;i++)
        {
            int time=random.nextInt(400);
            int priority=random.nextInt(100);
            processor[i%4].insert(time,priority,i+1);
            Files.put(i+1,generateRandomString());
            if(!threads[i%4].isAlive())
            {
                threads[i%4]=Redefine(i%4);
                threads[i%4].start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int x=random.nextInt(4);
        int y;
        do{
            y=random.nextInt(4);
        }while (x==y);
        if(Main.MethodType<2)
            processor[x].algorithms.mergeProcess(processor[y].algorithms.getParent());
        else
            processor[x].algorithms.mergeProcess(processor[y].algorithms.getRoot());
        processor[y].algorithms.clear();
        processor[y].clear();
        processor[x].algorithms.addNewProcess();
    }
}
