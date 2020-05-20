import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;

public class ListComparisonMain {
	final static int EXECUTIONS = 1000;

	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String timeStamp = dtf.format(now);
		
		List l = new List(1);
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(1);
		
		System.out.println("Durchläufe: "+EXECUTIONS);
		System.out.println("\tMethode\t\t|\tMyList\t\t|\tLinkedList");
		System.out.println("------------------------+-----------------------+-------------------------");
		
		try {
			FileWriter fw = new FileWriter("petrovic_ListComparison_"+timeStamp+".txt");
		    fw.write("Durchläufe: "+EXECUTIONS+"\n");
		    fw.write("\tMethode\t\t|\tMyList\t\t|\tLinkedList\n");
		    fw.write("------------------------+-----------------------+-------------------------\n");
		    
		    //Length
			long startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.length();
			long time = getTime(startTime);
			System.out.print("\tlength\t\t|\t"+time+"ns\t\t|");
			fw.write("\tlength\t\t|\t"+time+"ns\t\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) ll.size();
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
			
			//addHead
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.addHead(i);
			time = getTime(startTime);
			System.out.print("\taddHead\t\t|\t"+time+"ns\t|");
			fw.write("\taddHead\t\t|\t"+time+"ns\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) ll.addFirst(i);
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
			
			//addTail
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.addTail(i);
			time = getTime(startTime);
			System.out.print("\taddTail\t\t|\t"+time+"ns\t|");
			fw.write("\taddTail\t\t|\t"+time+"ns\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) ll.addLast(i);
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
			
			//add
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.addElem(i,50);
			time = getTime(startTime);
			System.out.print("\tadd\t\t|\t"+time+"ns\t|");
			fw.write("\tadd\t\t|\t"+time+"ns\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) ll.add(50,i);
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
			
			//swap
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.swap(50, 100);
			time = getTime(startTime);
			System.out.print("\tswap\t\t|\t"+time+"ns\t|");
			fw.write("\tswap\t\t|\t"+time+"ns\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) Collections.swap(ll, 50, 100);;
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
			
			//delete
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.deleteElem(50);
			time = getTime(startTime);
			System.out.print("\tdelete\t\t|\t"+time+"ns\t|");
			fw.write("\tdelete\t\t|\t"+time+"ns\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) ll.remove(50);
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
			
			//deleteTail
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.deleteTail();
			time = getTime(startTime);
			System.out.print("\tdeleteTail\t|\t"+time+"ns\t|");
			fw.write("\tdeleteTail\t|\t"+time+"ns\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) ll.removeLast();
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
			
			//deleteHead
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) l.deleteHead();
			time = getTime(startTime);
			System.out.print("\tdeleteHead\t|\t"+time+"ns\t|");
			fw.write("\tdeleteHead\t|\t"+time+"ns\t|");
			
			startTime = getCurrentTime();
			for (int i = 0; i < EXECUTIONS; i++) ll.removeFirst();
			time = getTime(startTime);
			System.out.println("\t"+time+"ns");
			fw.write("\t"+time+"ns\n");
		    
		    fw.close();
		} catch (IOException e) {
		    System.out.println("Fehler beim Erzeugen des Files!");
		    e.printStackTrace();
		}
		
		
		
	}
	public static long getTime(long startTime) {
		return getCurrentTime() - startTime;
	}
	public static long getCurrentTime() {
		return System.nanoTime();
	}

}
