package uk.ac.gla.dcs.dsms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import org.terrier.matching.dsms.DependenceScoreModifier;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.Posting;

/**
 * You should use this sample class to implement a proximity feature in Exercise
 * 2. TODO: Describe the function that your class implements
 * <p>
 * You can add your feature into a learned model by appending
 * DSM:uk.ac.gla.IRcourse.SampleProxFeatureDSM to the features.list file.
 * 
 * @author TODO
 */
public class SampleProxFeatureDSM extends DependenceScoreModifier {

	/**
	 * This class is passed the postings of the current document, and should return
	 * a score to represent that document.
	 */
	@Override
	protected double calculateDependence(Posting[] ips, // postings for this document (these are actually
														// IterablePosting[])
			boolean[] okToUse, // is each posting on the correct document?
			double[] phraseTermWeights, boolean SD // not needed
	) {

		final int numberOfQueryTerms = okToUse.length;
		//Integer small = Integer.MAX_VALUE;
		Integer large = Integer.MIN_VALUE;
		//double final_score = 0.0d;
		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		int leng = 1;

		// ***
		// TODO: in this part, write your code that inspects
		// the positions of query terms, to make a proximity function
		// NB: you can cast each Posting to org.terrier.structures.postings.BlockPosting
		// and use the getPositions() method to obtain the positions.
		// ***
		/*
		ArrayList<int[]> combine = new ArrayList<>();
		

		for (int z = 0; z < numberOfQueryTerms; z++) {
			
			if(okToUse[z]==true) {

			int[] all = ((BlockPosting) ips[z]).getPositions();

			combine.add(all);
			}
		}*/
		//System.out.println("here"+okToUse[0]);
		// System.out.println(combine.get(0)[1]-combine.get(1)[1]);
		//IntStream.of(combine.get(1)).sum(); 

	//	int[] ap = ((BlockPosting) ips[0]).getPositions();
		//int[] bp = ((BlockPosting) ips[1]).getPositions();

	//	Arrays.toString(((BlockPosting) ips[0]).getPositions());
	
//###############################################################################
	

		for (int i = 0; i < numberOfQueryTerms-1; i++) {
			if(okToUse[i]==true) {
				
			for (int j =i+1; j<numberOfQueryTerms;j++) {
				
					if(okToUse[j]==true) {
						
				for (int x = 0; x < ((BlockPosting) ips[i]).getPositions().length; x++) {
					
					for (int y = 0; y < ((BlockPosting) ips[j]).getPositions().length; y++) {
						
						int[] out1 = ((BlockPosting) ips[i]).getPositions();
						int[] out2 = ((BlockPosting) ips[j]).getPositions();
						

						
						int out = Math.abs((out1[x] - out2[y]));
						
						//System.out.println("here"+out);
						arrlist.add(out);

					}
					
	
				}
			}
			}
		}
			//final_score = Collections.min(arrlist);

		}
		

		//return final_score;

		return Collections.min(arrlist);
	}

	
	
	private int abs(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	/** You do NOT need to implement this method */
	@Override
	protected double scoreFDSD(int matchingNGrams, int docLength) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return "ProxFeatureDSM_MYFUNCTION";
	}

}
