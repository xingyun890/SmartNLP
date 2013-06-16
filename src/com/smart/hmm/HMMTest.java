package com.smart.hmm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

public class HMMTest
{

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	@Ignore
	public void testForForward()
	{
		double[] pi = { 0.63, 0.17, 0.20 };
		int numberOfObservation = 4;
		int numberOfHidden = 3;
		double[][] transfer = {{0.500, 0.375, 0.125}, {0.250, 0.125, 0.625},
				{0.250, 0.375, 0.375}};
		double[][] confusion = {{0.60, 0.20, 0.15, 0.05},
				{0.25, 0.25, 0.25, 0.25}, {0.05, 0.10, 0.35, 0.50}};

		HMMModel model = new HMMModel(pi, numberOfObservation, numberOfHidden,
				transfer, confusion);
		int[] observation = {0, 2, 3};
		HMMService service = new HMMService();
		System.out.println("the result:" + service.forward(model, observation));
	}
	
	@Test
	@Ignore
	public void testForViterbi()
	{
		double[] pi = { 0.333, 0.333, 0.333 };
		int numberOfObservation = 2;
		int numberOfHidden = 3;
		double[][] transfer = { { 0.333, 0.333, 0.333 },
				{ 0.333, 0.333, 0.333 }, { 0.333, 0.333, 0.333 } };
		double[][] confusion = { { 0.5, 0.5 }, { 0.75, 0.25 }, { 0.25, 0.75 } };

		HMMModel model = new HMMModel(pi, numberOfObservation, numberOfHidden,
				transfer, confusion);
		int[] observation = { 0, 0, 0, 0, 1, 0, 1, 1, 1, 1 };
		HMMService service = new HMMService();
		int[] sequence = service.viterbi(model, observation);
		System.out.print("The sequence is ");
		for (int i = 0; i < sequence.length; i++) {
			System.out.print(sequence[i] + " ");
		}
	}
	
	public double[][] loadFile(String filename) throws IOException
	{
		double[][] confusion;
		double value;
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String line = br.readLine();
		while(line!=null)
		{
			value=line.split();
		}
		return confusion;
	}

	@Test
	public void testForSeg() //×Ö±ê×¢²âÊÔ,×¢Òâ¸÷¸ö¾ØÕóÊ¼ÖÕ°´ÕÕB,M,E,SË³Ðò
	{
		int numberOfHidden = 4;
		/*
		 * ³õÊ¼¾ØÕó
		 * {'B': 0.6887918653263693, 'E': 0.0, 'M': 0.0, 'S': 0.31120813467363073}
		 */
		double[] pi={0.6887918653263693,0.0,0.0,0.31120813467363073}; 
		
		/*
		 * ×ªÒÆ¾ØÕó
		 * {'B': {'E': 0.8623367940544834, 'M': 0.13766320594551662},
 		 *	'E': {'B': 0.5544856664818801, 'S': 0.4455143335181199},
 		 *	'M': {'E': 0.7024280846522946, 'M': 0.2975719153477054},
 		 *	'S': {'B': 0.48617131037009215, 'S': 0.5138286896299078}}
		 */
		double[][] transfer = {{0, 0.13766320594551662, 0.8623367940544834, 0},
				{0, 0.2975719153477054, 0.7024280846522946, 0},
				{0.5544856664818801, 0, 0, 0.4455143335181199},
				{0.48617131037009215, 0, 0, 0.5138286896299078}};
		
		/*
		 * »ìÏý¾ØÕó
		 * 
		 * 
		 */
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
