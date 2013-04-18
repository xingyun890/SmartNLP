package com.smart.dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SimpleDictionary implements Dictionary {

	private ArrayList<String> dic=new ArrayList<String>(); 
	
	@Override
	public void loadDictionary(String filename) {
		try {
			String word;
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while ((word = in.readLine()) != null) {
				dic.add(word);
			}
			Collections.sort(dic);
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public boolean matchWord(String word) {
		int pos=Collections.binarySearch(dic, word);
		if(pos<0)
			return false;
		else
			return true;
	}

	@Override
	public void insertWord(String word) {
		if(word==null)
			return;
		if(Collections.binarySearch(dic, word)<0){
			dic.add(word);
		}
		Collections.sort(dic);
	}

	@Override
	public void deleteWord(String word) {
		if(word==null)
			return;
		if(Collections.binarySearch(dic, word)>=0){
			dic.remove(word);
		}
	}
	
	@Override
	public void writeDictionary(String filename) {
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < dic.size(); i++) {
				bw.write(dic.get(i));
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
