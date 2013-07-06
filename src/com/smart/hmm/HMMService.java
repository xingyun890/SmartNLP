package com.smart.hmm;


/**
 * @author chaolou
 *
 */
public class HMMService {

	/**
	 * ǰ���㷨
	 * 
	 * @param model
	 *            ����֪��HMMģ��
	 * @param observation
	 *            ���۲����У������ִ���
	 * @return �������յĹ۲����
	 */
	public double forward(HMMModel model, int[] observation) {

		double tmpSum;
		double[][] tmpMatrix = new double[observation.length][model
				.getNumberOfHidden()];
		double probable = 0;

		/**
		 * ��ʼ��������t=0ʱ������״̬�ľֲ�����
		 */
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			tmpMatrix[0][i] = model.getPi()[i]
					* model.getConfusion()[i][observation[0]];
		}

		/**
		 * ���ɣ���̬�滮����ÿ��ʱ��㣬t=1,2,..ʱ�ľֲ�����
		 */
		for (int i = 1; i < observation.length; i++) {
			for (int j = 0; j < model.getNumberOfHidden(); j++) {
				tmpSum = 0;
				for (int k = 0; k < model.getNumberOfHidden(); k++)
					tmpSum += tmpMatrix[i - 1][k] * model.getTransfer()[k][j];

				tmpMatrix[i][j] = tmpSum
						* model.getConfusion()[j][observation[i]];

			}
		}

		/**
		 * ��ֹ���۲����еĸ��ʵ������ʱ�̵����оֲ�����֮��
		 */
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			probable += tmpMatrix[observation.length - 1][i];
		}

		return probable;
	}

	/**
	 * viterbi�㷨�����������ö�ά�������ͣ�������ֻ����int�͵�,�޷�ʹ��String
	 * 
	 * @param model
	 *            ����֪��HMMģ��
	 * @param observation
	 *            ���۲����У������ִ���
	 * @return �������յ��������״̬����
	 */
	public int[] viterbi(HMMModel model, int[] observation) {

		double[][] tmpMatrix = new double[observation.length][model
				.getNumberOfHidden()];
		int[][] indexMatrix = new int[observation.length][model
				.getNumberOfHidden()];
		double val;

		/**
		 * ��ʼ��������t=0ʱ������״̬�ľֲ�����
		 */
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			tmpMatrix[0][i] = model.getPi()[i]
					* model.getConfusion()[i][observation[0]];
		}

		/**
		 * ���ɣ���̬�滮����ÿ��ʱ��㣬t=1,2,..ʱ�ľֲ�����
		 */
		for (int i = 1; i < observation.length; i++) {
			for (int j = 0; j < model.getNumberOfHidden(); j++) {

				double maxval = 0;
				int maxindex = 0;
				for (int k = 0; k < model.getNumberOfHidden(); k++) {

					// ע��������forward�㷨������forward�����sum��viterbi�������max
					val = tmpMatrix[i - 1][k] * model.getTransfer()[k][j];
					if (val > maxval) {
						maxval = val;
						maxindex = k; // �����±꣬�Ա���Ի���
					}
				}

				tmpMatrix[i][j] = maxval
						* model.getConfusion()[j][observation[i]];
				indexMatrix[i][j] = maxindex;

			}
		}

		/**
		 * ��ֹ���۲����еĸ��ʵ������ʱ�̵����оֲ�����֮��
		 */
		double probable = 0;
		int[] path = new int[observation.length];
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			if (tmpMatrix[observation.length - 1][i] > probable) {
				probable = tmpMatrix[observation.length - 1][i];
				path[observation.length - 1] = i;
			}
		}

		/**
		 * ���ݣ�Ѱ�����·���±�
		 */
		for (int i = observation.length - 2; i >= 0; i--) {
			path[i] = indexMatrix[i + 1][path[i + 1]]; // it's important ��λ������±�
		}

		return path;
	}
	
	
	/**
	 * viterbi�㷨,����������Map����
	 * 
	 * @param model
	 *            ����֪��HMMģ��
	 * @param observation
	 *            ���۲����У������ִ���
	 * @return �������յ��������״̬����
	 */
	public String viterbi(HMMModelWithConfusionMap model, String observation) {

		double[][] tmpMatrix = new double[observation.length()][model
				.getNumberOfHidden()];
		int[][] indexMatrix = new int[observation.length()][model
				.getNumberOfHidden()];
		double val;
		String[] status = {"B","M","E","S"};
		
		/**
		 * ��ʼ��������t=0ʱ������״̬�ľֲ�����
		 */
		for (int i = 0; i < model.getNumberOfHidden(); i++) {

			 if(model.getConfusion().get(status[i]).get(observation.substring(0,1))!=null){
				 tmpMatrix[0][i] = model.getPi()[i] * model.getConfusion().get(status[i]).get(observation.substring(0,1));
			 }else{
				 tmpMatrix[0][i] = 0.0;
			 }
		}

		/**
		 * ���ɣ���̬�滮����ÿ��ʱ��㣬t=1,2,..ʱ�ľֲ�����
		 */
		for (int i = 1; i < observation.length(); i++) {
			for (int j = 0; j < model.getNumberOfHidden(); j++) {

				double maxval = 0;
				int maxindex = 0;
				for (int k = 0; k < model.getNumberOfHidden(); k++) {

					// ע��������forward�㷨������forward�����sum��viterbi�������max
					val = tmpMatrix[i - 1][k] * model.getTransfer()[k][j];
					if (val > maxval) {
						maxval = val;
						maxindex = k; // �����±꣬�Ա���Ի���
					}
				}
				
				if(model.getConfusion().get(status[j]).get(observation.substring(i,i+1))!=null){
					tmpMatrix[i][j] = maxval* model.getConfusion().get(status[j]).get(observation.substring(i,i+1));
				}else{
					tmpMatrix[i][j] = 0.0;
				}
				
				indexMatrix[i][j] = maxindex;

			}
		}

		/**
		 * ��ֹ���۲����еĸ��ʵ������ʱ�̵����оֲ�����֮��
		 */
		double probable = 0;
		int[] path = new int[observation.length()];
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			if (tmpMatrix[observation.length() - 1][i] > probable) {
				probable = tmpMatrix[observation.length() - 1][i];
				path[observation.length() - 1] = i;
			}
		}

		/**
		 * ���ݣ�Ѱ�����·���±�
		 */
		for (int i = observation.length() - 2; i >= 0; i--) {
			path[i] = indexMatrix[i + 1][path[i + 1]]; // it's important ��λ������±�
		}
		
		System.out.print("The Path is : ");
		for(int i=0;i<path.length;i++){
			System.out.print(path[i]+" ");
		}
		System.out.println();
		return showFormatSentence(path,observation);
	}
	
	
	/**
	 * ����Path��������ʾ���ո��sentence
	 * 
	 * @param path
	 * @param observation
	 * @return
	 */
	public String showFormatSentence(int[] path,String observation){
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<observation.length();i++){
			sb.append(observation.charAt(i));
			if(path[i]==2||path[i]==3){ //2 means 'M' and 3 means 'E'
				sb.append(" ");
			}
		}
		return sb.toString();
		
	}
	
}
