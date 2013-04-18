package com.smart.dictionary;

public interface Dictionary {
	
	/**
	 * �����ֵ��ļ�
	 * @param filename
	 */
	public void loadDictionary(String filename);
	
	/**
	 * ��word���ֵ��е��ļ����бȽϣ��鿴�Ƿ����
	 * @param word
	 * @return
	 */
	public boolean matchWord(String word);
	
	/**
	 * ��word���뵽�ֵ��ļ���
	 * @param word
	 */
	public void insertWord(String word);
	
	/**
	 * ��word���ֵ��ļ���ɾ��
	 * @param word
	 */
	public void deleteWord(String word);
	
	/**
	 * ���ڴ��е��ֵ�����д���ֵ��ļ�
	 * @param filename
	 */
	public void writeDictionary(String filename);
}
