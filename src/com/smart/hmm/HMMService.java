package com.smart.hmm;

public class HMMService {

	/**
	 * 前向算法
	 * 
	 * @param model
	 *            ：已知的HMM模型
	 * @param observation
	 *            ：观察序列，用数字代替
	 * @return 返回最终的观察概率
	 */
	public double forward(HMMModel model, int[] observation) {

		double tmpSum;
		double[][] tmpMatrix = new double[observation.length][model
				.getNumberOfHidden()];
		double probable = 0;

		/**
		 * 初始化：计算t=0时刻所有状态的局部概率
		 */
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			tmpMatrix[0][i] = model.getPi()[i]
					* model.getConfusion()[i][observation[0]];
		}

		/**
		 * 归纳：动态规划计算每个时间点，t=1,2,..时的局部概率
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
		 * 终止：观察序列的概率等于最后时刻的所有局部概率之和
		 */
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			probable += tmpMatrix[observation.length - 1][i];
		}

		return probable;
	}

	/**
	 * viterbi算法
	 * 
	 * @param model
	 *            ：已知的HMM模型
	 * @param observation
	 *            ：观察序列，用数字代替
	 * @return 返回最终的最佳隐藏状态序列
	 */
	public int[] viterbi(HMMModel model, int[] observation) {

		double[][] tmpMatrix = new double[observation.length][model
				.getNumberOfHidden()];
		int[][] indexMatrix = new int[observation.length][model
				.getNumberOfHidden()];
		double val;

		/**
		 * 初始化：计算t=0时刻所有状态的局部概率
		 */
		for (int i = 0; i < model.getNumberOfHidden(); i++) {
			tmpMatrix[0][i] = model.getPi()[i]
					* model.getConfusion()[i][observation[0]];
		}

		/**
		 * 归纳：动态规划计算每个时间点，t=1,2,..时的局部概率
		 */
		for (int i = 1; i < observation.length; i++) {
			for (int j = 0; j < model.getNumberOfHidden(); j++) {

				double maxval = 0;
				int maxindex = 0;
				for (int k = 0; k < model.getNumberOfHidden(); k++) {

					// 注意这里与forward算法的区别，forward是求和sum，viterbi是求最大max
					val = tmpMatrix[i - 1][k] * model.getTransfer()[k][j];
					if (val > maxval) {
						maxval = val;
						maxindex = k; // 保存下标，以便可以回溯
					}
				}

				tmpMatrix[i][j] = maxval
						* model.getConfusion()[j][observation[i]];
				indexMatrix[i][j] = maxindex;

			}
		}

		/**
		 * 终止：观察序列的概率等于最后时刻的所有局部概率之和
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
		 * 回溯：寻找最佳路径下标
		 */
		for (int i = observation.length - 2; i >= 0; i--) {
			path[i] = indexMatrix[i + 1][path[i + 1]]; // it's important 如何回溯找下标
		}

		return path;
	}

}
