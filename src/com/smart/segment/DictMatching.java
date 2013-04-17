package com.smart.segment;

public class DictMatching {

	private static final int MaxTokenLength = 5;

	public static String[] forwordMaxMatch(String sentence) {

		String[] dict = { "��", "��", "�й���" };
		String[] token = new String[10];
		int firstIndex = 0;
		int lastIndex = sentence.length() - 1;
		boolean findtoken = false;
		int tokenNumber = 0;

		while (firstIndex <= sentence.length() - 1) {

			findtoken = false;
			lastIndex = firstIndex + MaxTokenLength;
			if (lastIndex > sentence.length() - 1) {
				lastIndex = sentence.length() - 1;
			}

			for (int i = lastIndex; i >= firstIndex; i--) {
				for (int j = 0; j < dict.length; j++) {
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
	 * @param args
	 */
	public static void main(String[] args) {

		String sentence = "�����й�����";
		String[] tokens = forwordMaxMatch(sentence);
		for (String token : tokens) {
			if (token != null)
				System.out.println(token + " ");
		}
	}

}
