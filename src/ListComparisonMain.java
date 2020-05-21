import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ListComparisonMain {
	final static int EXECUTIONS = 1000;
	static Map<Integer, Runnable> methods = new HashMap<>();
	static FileWriter fw;

	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String timeStamp = dtf.format(now);
		
		List l = new List(1);
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(1);
		
		//populate methods map
		methods.put(0, () -> l.length());
		methods.put(1, () -> ll.size());
		methods.put(2, () -> l.addHead(50));
		methods.put(3, () -> ll.addFirst(50));
		methods.put(4, () -> l.addTail(50));
		methods.put(5, () -> ll.addLast(50));
		methods.put(6, () -> l.addElem(50, 50));
		methods.put(7, () -> ll.add(50, 50));
		methods.put(8, () -> l.swap(50, 100));
		methods.put(9, () -> Collections.swap(ll, 50, 100));
		methods.put(10, () -> l.deleteElem(50));
		methods.put(11, () -> ll.remove(50));
		methods.put(12, () -> l.deleteTail());
		methods.put(13, () -> ll.removeLast());
		methods.put(14, () -> l.deleteHead());
		methods.put(15, () -> ll.removeFirst());
		
		
		System.out.println("Durchläufe: "+EXECUTIONS);
		System.out.println("\tMethode\t\t|\tMyList\t\t|\tLinkedList");
		System.out.println("------------------------+-----------------------+-------------------------");
		
		try {
			fw = new FileWriter("petrovic_ListComparison_"+timeStamp+".txt");
		    fw.write("Durchläufe: "+EXECUTIONS+"\n");
		    fw.write("\tMethode\t\t|\tMyList\t\t|\tLinkedList\n");
		    fw.write("------------------------+-----------------------+-------------------------\n");
		    
		    compareMethods("length", 0);
		    compareMethods("addHead", 2);
		    compareMethods("addTail", 4);
		    compareMethods("addElem", 6);
		    compareMethods("swap", 8);
		    compareMethods("delete", 10);
		    compareMethods("delTail", 12);
		    compareMethods("delHead", 14);
		    
		    fw.close();
		} catch (IOException e) {
		    System.out.println("Fehler beim Erzeugen des Files!");
		    e.printStackTrace();
		}
	}
	public static void compareMethods(String methodName, int firstMethod) throws IOException {
		//firstMethod
		long time = methodTime(firstMethod);
		System.out.print("\t"+methodName+"\t\t|\t"+time+"ns\t|");
		fw.write("\t"+methodName+"\t\t|\t"+time+"ns\t|");
		
		//secondMethod
		time = methodTime(firstMethod+1);
		System.out.println("\t"+time+"ns");
		fw.write("\t"+time+"ns\n");
	}
	public static long methodTime(int method) {
		long startTime = getCurrentTime();
		for (int i = 0; i < EXECUTIONS; i++) {
			methods.get(method).run();
		}
		return getTime(startTime);
	}
	public static long getTime(long startTime) {
		return getCurrentTime() - startTime;
	}
	public static long getCurrentTime() {
		return System.nanoTime();
	}

}
