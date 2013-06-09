package com.smart.hmm;

/**
 * ����HMMģ�����������Ԫ�أ�
 */
public class HMMModel {
	
	private double[] pi; //��ʼ����pi
	private int NumberOfObservation; //�۲�״̬����
	private int NumberOfHidden; //����״̬����
	private double[][] transfer; //״̬ת�ƾ���
	private double[][] confusion; //�������󣬴�����״̬���۲�״̬�ķ������
	
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
