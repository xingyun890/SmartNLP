package com.smart.hmm;

public class HMMTest {
	
	
	/**
	 * 前向算法
	 * @param model：已知的HMM模型
	 * @param observation：观察序列，用数字代替
	 * @return 返回最终的观察概率
	 */
	public double forward(HMMModel model,int[] observation){
		
		double tmpSum;
		double[][] tmpMatrix = new double[model.getNumberOfHidden()][observation.length];
		double probable=0;
		
		/**
		 * 初始化：计算t=0时刻所有状态的局部概率
		 */
		for(int i=0;i<model.getNumberOfHidden();i++){
			tmpMatrix[0][i] = model.getPi()[i]*model.getConfusion()[i][observation[0]];
		}
		
		/**
		 * 归纳：动态规划计算每个时间点，t=1,2,..时的局部概率
		 */
		for(int i=1;i<observation.length;i++){
			for(int j=0;j<model.getNumberOfHidden();j++){
				tmpSum = 0;
				for(int k=0;k<model.getNumberOfHidden();k++)
					tmpSum+=tmpMatrix[i-1][k]*model.getTransfer()[k][j];
								
				tmpMatrix[i][j]=tmpSum*model.getConfusion()[j][observation[i]];
					
			}
		}
		
		/**
		 * 终止：观察序列的概率等于最后时刻的所有局部概率之和
		 */
		for(int i=0;i<model.getNumberOfHidden();i++){
			probable+=tmpMatrix[observation.length-1][i];
		}
		
		return probable;
	}
	
	
	
	/**
	 * Just For Test
	 * @param args
	 */
	public static void main(String[] args) {
		double[] pi={0.63,0.17,0.20};
		int numberOfObservation=4;
		int numberOfHidden=3;
		double[][] transfer={{0.500,0.375,0.125},{0.250,0.125,0.625},{0.250,0.375,0.375}};
		double[][] confusion={{0.60,0.20,0.15,0.05},{0.25,0.25,0.25,0.25},{0.05,0.10,0.35,0.50}};
		
		HMMModel model = new HMMModel(pi, numberOfObservation, numberOfHidden, transfer, confusion);
		HMMTest test=new HMMTest();
		int[] observation={0,2,3};
		System.out.println("the result:"+test.forward(model,observation));
		
	}

}
