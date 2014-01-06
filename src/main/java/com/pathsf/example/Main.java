package com.pathsf.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

	
	public static void main(String[] args){
		System.out.println(find("kellek"));
//		int[] array = {1,5,28,233,12,79,23,-19};
//		printArray(array);
//		sort(array);
//		printArray(array);
//		
//		List<Pair> pairs = diff(array);
//		for (int i = 0; i < pairs.size(); i++) {
//			System.out.println(pairs.get(i).a + " - " + pairs.get(i).b);
//		}
		
//		List<Integer> list = divide(15,2);
//		for(int i = list.size() -1; i >= 0; i--){
//			System.out.print(list.get(i));
//		}
//		
//		 System.out.println(Integer.toString((65535 * 52429) >>> (16+3))); 
	}
	
	static String encode(String str){
		
		char[] c = str.toCharArray();
		
		return "";
	}
	
	static List<Pair> diff(int[] array){
		
		int maxdiff = Integer.MAX_VALUE;
		List<Pair> pairs = new ArrayList<Pair>();
		
		for (int i = 0; i < array.length - 1; i++) {
			if (Math.abs((array[i] - array[i+1])) < maxdiff){
				pairs.add(new Pair(array[i], array[i+1]));
				maxdiff = array[i] - array[i+1];
			}
		}
		
		return pairs;
	}
	
	static void sort(int[] array){
		
		//1,5,2,233,12,7,3
		int max = Integer.MIN_VALUE;
		int temp = 0;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++){
				if (array[j] > array[i]){
					temp = array[j];
					array[j] = array[i];
					array[i] = temp;
					//printArray(array);
				}
			}
		}
	}
	
	static void printArray(int[] array){
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println("\n");
	}
	
	static boolean find(String str){
		
		//benneb
		//beneb
		char[] ch = str.toCharArray();
		int x = str.length();
		int length = x/2;
		
		for (int i = 0; i < length; i++) {
			System.out.println(ch[i] + " - " + ch[x-1-i]);
			if (ch[i] != ch[x-1-i]){
				return false;
			}
		}
		return true;
	}
	
	static List<Integer> divide(int a, int b){
		
		List<Integer> list = new ArrayList<Integer>();
		
		StringBuffer sb = new StringBuffer();
		

		while (a != 0){
			int r = Math.abs(a % b);
			a = (a-r)/b;
			//list.add(new Integer(r));
			sb.insert(0, r);
		}
		
		System.out.println(sb);
		
		return list;
	}
}

class Pair{
	
	Pair(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	int a = 0;
	int b = 0;
}
