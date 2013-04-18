package com.smart.segment;

public class DictMatching {

	private static final int MaxTokenLength = 6;

	/**
	 * 最大正向匹配
	 * @param sentence
	 * @return
	 */
	public static String[] forwordMaxMatch(String sentence) { 

		String[] dict = { "我", "是", "中国人" };
		String[] token = new String[100];
		int firstIndex = 0;
		int lastIndex = sentence.length() - 1;
		boolean findtoken = false;
		int tokenNumber = 0;

		while (firstIndex <= sentence.length() - 1) { //从前往后遍历

			findtoken = false;
			lastIndex = firstIndex + MaxTokenLength;
			if (lastIndex > sentence.length() - 1) {
				lastIndex = sentence.length() - 1;
			}

			for (int i = lastIndex; i >= firstIndex; i--) {  //每次循环分出一个词
				for (int j = 0; j < dict.length; j++) {  //遍历词典
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
				if (i == firstIndex) { // 没有在字典里找到该词则单独成字
					token[tokenNumber++] = sentence
							.substring(firstIndex, i + 1);
					firstIndex = i + 1;
				}
			}

		}

		return token;

	}

	/**
	 * 最大逆向匹配
	 * @param sentence
	 * @return
	 */
	public static String[] BackwardMaxMatch(String sentence){
		String[] dict = { "我", "是", "中国人" };
		String[] token = new String[100];
		int firstIndex = 0;
		int lastIndex = sentence.length() - 1;
		boolean findtoken = false;
		int tokenNumber = 0;

		while (lastIndex >= 0) { //从后往前遍历

			findtoken = false;
			firstIndex = lastIndex - MaxTokenLength;
			if (firstIndex <= 0) {
				firstIndex = 0;
			}

			for (int i = firstIndex; i <= lastIndex; i++) {  //每次循环分出一个词
				for (int j = 0; j < dict.length; j++) {  //遍历词典
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
				if (i == lastIndex) { // 没有在字典里找到该词则单独成字
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

		String sentence = "我还是中国人吗";
		String[] tokens = forwordMaxMatch(sentence);
//		String[] tokens = BackwardMaxMatch(sentence);
		for (String token : tokens) {
			if (token != null)
				System.out.println(token + " ");
		}
	}

}
