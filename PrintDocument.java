import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PrintDocument {
	String documentName;
	long priority;

	public PrintDocument(String documentName, long priority) {
		this.documentName = documentName;
		this.priority = priority;
	}

	public static List<PrintDocument> merge(List<PrintDocument> d1, List<PrintDocument> d2) {
		// solution code here...
		Map<String, List<PrintDocument>> sortedMap = new LinkedHashMap<>();
		List<PrintDocument> newList = new ArrayList<>();
		List<PrintDocument> sortedList = new ArrayList<>();

		newList.addAll(d1);
		newList.addAll(d2);

		Collections.sort(newList, Comparator.comparingLong(PrintDocument::getPriority));

		for (PrintDocument pd : newList) {
			if (sortedMap.containsKey(pd.getDocumentName())) {
				sortedMap.get(pd.getDocumentName()).add(pd);
			} else {
				sortedMap.put(pd.getDocumentName(), new ArrayList<PrintDocument>(Arrays.asList(pd)));
			}
		}

		for (Entry<String, List<PrintDocument>> sm : sortedMap.entrySet()) {
			sortedList.addAll(sm.getValue());
		}

		return sortedList;
	}
	
	//Getters & Setters

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}
}
