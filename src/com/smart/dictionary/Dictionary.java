package com.smart.dictionary;

public interface Dictionary {
	
	/**
	 * 加载字典文件
	 * @param filename
	 */
	public void loadDictionary(String filename);
	
	/**
	 * 将word与字典中的文件进行比较，查看是否存在
	 * @param word
	 * @return
	 */
	public boolean matchWord(String word);
	
	/**
	 * 将word加入到字典文件中
	 * @param word
	 */
	public void insertWord(String word);
	
	/**
	 * 将word从字典文件中删除
	 * @param word
	 */
	public void deleteWord(String word);
	
	/**
	 * 将内存中的字典数据写回字典文件
	 * @param filename
	 */
	public void writeDictionary(String filename);
}
