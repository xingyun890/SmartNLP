package com.smart.hmm;

/**
 * 定义HMM模型所必须的五元素：
 */
public class HMMModel {
	
	private double[] pi; //初始向量pi
	private int NumberOfObservation; //观察状态总类
	private int NumberOfHidden; //隐藏状态总类
	private double[][] transfer; //状态转移矩阵
	private double[][] confusion; //混淆矩阵，从隐藏状态到观察状态的发射概率
	
	public HMMModel(double[] pi, int numberOfObservation, int numberOfHidden,
			double[][] transfer, double[][] confusion) {
		super();
		this.pi = pi;
		NumberOfObservation = numberOfObservation;
		NumberOfHidden = numberOfHidden;
		this.transfer = transfer;
		this.confusion = confusion;
	}
	
	public double[] getPi() {
		return pi;
	}
	public void setPi(double[] pi) {
		this.pi = pi;
	}
	public int getNumberOfObservation() {
		return NumberOfObservation;
	}
	public void setNumberOfObservation(int numberOfObservation) {
		NumberOfObservation = numberOfObservation;
	}
	public int getNumberOfHidden() {
		return NumberOfHidden;
	}
	public void setNumberOfHidden(int numberOfHidden) {
		NumberOfHidden = numberOfHidden;
	}
	public double[][] getTransfer() {
		return transfer;
	}
	public void setTransfer(double[][] transfer) {
		this.transfer = transfer;
	}
	public double[][] getConfusion() {
		return confusion;
	}
	public void setConfusion(double[][] confusion) {
		this.confusion = confusion;
	}
	
	
}
