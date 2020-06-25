import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ListComparisonMain {
	final static int EXECUTIONS = 10000;
	static Map<Integer, Runnable> DCLmethods = new HashMap<>();
	static Map<Integer, Runnable> LLmethods = new HashMap<>();
	static Map<Integer, Runnable> ArrayMethods = new HashMap<>();
	static FileWriter fw;

	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String timeStamp = dtf.format(now);
		
		//set all lists and the array to the same values
		List l = new List(1);
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(1);
		int[] arr = new int[EXECUTIONS * 3 + 1];
		arr[0] = 1;
		
		//populate DCLmethods map
		DCLmethods.put(0, () -> l.length());
		DCLmethods.put(1, () -> l.addHead(50));
		DCLmethods.put(2, () -> l.addTail(50));
		DCLmethods.put(3, () -> l.addElem(50, 50));
		DCLmethods.put(4, () -> l.swap(50, 100));
		DCLmethods.put(5, () -> l.deleteElem(50));
		DCLmethods.put(6, () -> l.deleteTail());
		DCLmethods.put(7, () -> l.deleteHead());
		
		//populate LLmethods map
		LLmethods.put(0, () -> ll.size());
		LLmethods.put(1, () -> ll.addFirst(50));
		LLmethods.put(2, () -> ll.addLast(50));
		LLmethods.put(3, () -> ll.add(50, 50));
		LLmethods.put(4, () -> Collections.swap(ll, 50, 100));
		LLmethods.put(5, () -> ll.remove(50));
		LLmethods.put(6, () -> ll.removeLast());
		LLmethods.put(7, () -> ll.removeFirst());
		
		//populate ArrayMethods map
		ArrayMethods.put(0, () -> LCArrayMethods.getLength(arr));
		ArrayMethods.put(1, () -> LCArrayMethods.addFirst(arr, 50));
		ArrayMethods.put(2, () -> LCArrayMethods.addLast(arr, 50));
		ArrayMethods.put(3, () -> LCArrayMethods.add(arr, 50, 50));
		ArrayMethods.put(4, () -> LCArrayMethods.swap(arr, 50, 100));
		ArrayMethods.put(5, () -> LCArrayMethods.delete(arr, 50));
		ArrayMethods.put(6, () -> LCArrayMethods.deleteLast(arr));
		ArrayMethods.put(7, () -> LCArrayMethods.deleteFirst(arr));
		
		System.out.println("Durchläufe: "+EXECUTIONS);
		System.out.println("\tMethode\t\t|\tMyList\t\t|\tLinkedList\t|\tArray");
		System.out.println("------------------------+-----------------------+-----------------------+--------------------");
		
		try {
			fw = new FileWriter("petrovic_ListComparison_"+timeStamp+".txt");
		    fw.write("Durchläufe: "+EXECUTIONS+"\n");
		    fw.write("\tMethode\t\t|\tMyList\t\t|\tLinkedList\t|\tArray\n");
		    fw.write("------------------------+-----------------------+-----------------------+--------------------\n");
		    
		    compareMethods("length", 0);
		    compareMethods("addHead", 1);
		    compareMethods("addTail", 2);
		    compareMethods("addElem", 3);
		    compareMethods("swap", 4);
		    compareMethods("delete", 5);
		    compareMethods("delTail", 6);
		    compareMethods("delHead", 7);
		    
		    fw.close();
		} catch (IOException e) {
		    System.out.println("Fehler beim Erzeugen des Files!");
		    e.printStackTrace();
		}
	}
	public static void compareMethods(String methodName, int method) throws IOException {
		//DCL
		long time = methodTime(DCLmethods, method);
		System.out.print("\t"+methodName+"\t\t|\t"+time/EXECUTIONS+"ns\t\t|");
		fw.write("\t"+methodName+"\t\t|\t"+time/EXECUTIONS+"ns\t\t|");
		
		//LL
		time = methodTime(LLmethods, method);
		System.out.print("\t"+time/EXECUTIONS+"ns\t\t|");
		fw.write("\t"+time/EXECUTIONS+"ns\t\t|");
		
		//Array
		time = methodTime(ArrayMethods, method);
		System.out.println("\t"+time/EXECUTIONS+"ns");
		fw.write("\t"+time/EXECUTIONS+"ns\n");
	}
	public static long methodTime(Map<Integer, Runnable> map, int method) {
		long startTime = getCurrentTime();
		for (int i = 0; i < EXECUTIONS; i++) {
			map.get(method).run();
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
