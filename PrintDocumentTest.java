package org.kam.rest.messenger.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PrintDocumentTest {

	/**
	 * 
		Note: A document is represented in String format as {<document name>, <priority>} 
		[{docA, 1}, {docD, 5}, {docE, 2}] merged with [{docA, 4}, {docB, 6} {docD, 3}] 
		results in [{docA, 1}, {docA, 4}, {docE, 2}, {docD, 3}, {docD, 5}, {docB, 6}]
	 */
	
	@Test
	public void testMerge() {
		PrintDocument p1 = new PrintDocument("docA", 1);
		PrintDocument p2 = new PrintDocument("docD", 5);
		PrintDocument p3 = new PrintDocument("docE", 2);
		
		PrintDocument p4 = new PrintDocument("docA", 4);
		PrintDocument p5 = new PrintDocument("docB", 6);
		PrintDocument p6 = new PrintDocument("docD", 3);
		
		List<PrintDocument> returnedList = PrintDocument.merge(Arrays.asList(p1,p2,p3), Arrays.asList(p4,p5,p6));
		
		assertEquals(6, returnedList.size());
		assertEquals("docA", returnedList.get(0).getDocumentName());
		assertEquals(1, returnedList.get(0).getPriority());
		
		assertEquals("docA", returnedList.get(1).getDocumentName());
		assertEquals(4, returnedList.get(1).getPriority());
		
		assertEquals("docE", returnedList.get(2).getDocumentName());
		assertEquals(2, returnedList.get(2).getPriority());
		
		assertEquals("docD", returnedList.get(3).getDocumentName());
		assertEquals(3, returnedList.get(3).getPriority());
		
		assertEquals("docD", returnedList.get(4).getDocumentName());
		assertEquals(5, returnedList.get(4).getPriority());
		
		assertEquals("docB", returnedList.get(5).getDocumentName());
		assertEquals(6, returnedList.get(5).getPriority());
		
	}
	

	@Test
	public void testMergeSpeed() {
		List<PrintDocument> d1 = new ArrayList<>();
		for (int i = 0; i<500000; i++)
		{
			d1.add(new PrintDocument("docA", i));
		}
		
		List<PrintDocument> d2 = new ArrayList<>();
		for (int i = 0; i<500000; i++)
		{
			d2.add(new PrintDocument("docB", i));
		}
		
		long startTime = System.nanoTime();
		List<PrintDocument> returnedList = PrintDocument.merge(d1, d2);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		
		assertEquals(500000, d1.size());
		assertEquals(500000, d2.size());
		assertEquals(1000000, returnedList.size());
		assertTrue((duration/1000000000.0) < 2);	
		
	}
	
	@Test
	public void mytest(){
		String fileName = "name.extension";
		System.out.println(fileName.substring(fileName.indexOf('.')+1, fileName.length()));
		
		System.out.println(fileName.substring(fileName.indexOf('.'), fileName.length()));
	}

}
