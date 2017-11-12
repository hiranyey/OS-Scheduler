# OS_Scheduler
(JAVA SWING)
A simple simulation of 4 Processors working together in multithreaded environment.  
A master Cpu assigns a randomly generated process to each proccesor using two style(Random or Serial).  
Each Process has ID,TotalTimeRequired,Priority.    
Each Processor simulates any **one of the four famous algorithms**  
 * First in First out
 * Round Robin
 * Shortest Job First
 * Priority Scheduling  

  
After assigning the 20 processes(**u can change this number in code**),MasterCpu randomly chooses any processor and merges the process queue of that proccesor to any other proccesor.  

This merge process takes:-  
* O(1) for FIFO(First in First out) and RR(Round Robin)
* O(log n) for SJF(Shortest Job First) and PS(Priority Scheduling)  

Each Processor show its own ThroughPut and Gantt Chart according to the algorithm chosen  
Data Structure used are ArrayList,Leftist Heap,B Tree  
[11/12/2017] Each Processor now has a file linked. After each CPU burst Something is written in the file.  
Linking Between Processor and Files are done by B Trees.

 
