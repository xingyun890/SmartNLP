package com.smart.segment;

public class DictMatching {

	private static final int MaxTokenLength = 6;

	/**
	 * �������ƥ��
	 * @param sentence
	 * @return
	 */
	public static String[] forwordMaxMatch(String sentence) { 

		String[] dict = { "��", "��", "�й���" };
		String[] token = new String[100];
		int firstIndex = 0;
		int lastIndex = sentence.length() - 1;
		boolean findtoken = false;
		int tokenNumber = 0;

		while (firstIndex <= sentence.length() - 1) { //��ǰ�������

			findtoken = false;
			lastIndex = firstIndex + MaxTokenLength;
			if (lastIndex > sentence.length() - 1) {
				lastIndex = sentence.length() - 1;
			}

			for (int i = lastIndex; i >= firstIndex; i--) {  //ÿ��ѭ���ֳ�һ����
				for (int j = 0; j < dict.length; j++) {  //�����ʵ�
					if (sentence.substring(firstIndex, i + 1).equals(dict[j])) {
						findtoken = true;
						token[tokenNumber++] = sentence.substring(firstIndex,
								i + 1);
						firstIndex = i + 1;
						break;
					}

				}
				if (findtoken == true)
					break;
				if (i == firstIndex) { // û�����ֵ����ҵ��ô��򵥶�����
					token[tokenNumber++] = sentence
							.substring(firstIndex, i + 1);
					firstIndex = i + 1;
				}
			}

		}

		return token;

	}

	/**
	 * �������ƥ��
	 * @param sentence
	 * @return
	 */
	public static String[] BackwardMaxMatch(String sentence){
		String[] dict = { "��", "��", "�й���" };
		String[] token = new String[100];
		int firstIndex = 0;
		int lastIndex = sentence.length() - 1;
		boolean findtoken = false;
		int tokenNumber = 0;

		while (lastIndex >= 0) { //�Ӻ���ǰ����

			findtoken = false;
			firstIndex = lastIndex - MaxTokenLength;
			if (firstIndex <= 0) {
				firstIndex = 0;
			}

			for (int i = firstIndex; i <= lastIndex; i++) {  //ÿ��ѭ���ֳ�һ����
				for (int j = 0; j < dict.length; j++) {  //�����ʵ�
					if (sentence.substring(i, lastIndex + 1).equals(dict[j])) {
						findtoken = true;
						token[tokenNumber++] = sentence.substring(i,
								lastIndex + 1);
						lastIndex = i - 1;
						break;
					}

				}
				if (findtoken == true)
					break;
				if (i == lastIndex) { // û�����ֵ����ҵ��ô��򵥶�����
					token[tokenNumber++] = sentence
							.substring(i, lastIndex + 1);
					lastIndex = i - 1;
				}
			}

		}

		return token;

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String sentence = "�һ����й�����";
		String[] tokens = forwordMaxMatch(sentence);
//		String[] tokens = BackwardMaxMatch(sentence);
		for (String token : tokens) {
			if (token != null)
				System.out.println(token + " ");
		}
	}

}
