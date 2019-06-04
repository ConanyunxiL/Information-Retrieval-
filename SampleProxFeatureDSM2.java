package uk.ac.gla.dcs.dsms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
public class SampleProxFeatureDSM2 extends DependenceScoreModifier {

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
	//	System.out.println("here"+((BlockPosting) ips[4]).getPositions().length);
		//System.out.println("here"+numberOfQueryTerms);
		
		Integer small = Integer.MAX_VALUE;
		Integer large = Integer.MIN_VALUE;
		// ***
		// TODO: in this part, write your code that inspects
		// the positions of query terms, to make a proximity function
		// NB: you can cast each Posting to org.terrier.structures.postings.BlockPosting
		// and use the getPositions() method to obtain the positions.
		// ***
		/*
		ArrayList<int[]> combine = new ArrayList<>();
		//List<Integer> indexList = new ArrayList<>();
		
		for (int z = 0; z < numberOfQueryTerms; z++) {
			if(okToUse[z]==true) {

			int[] all = ((BlockPosting) ips[z]).getPositions();

			combine.add(all);
			//System.out.println(all[1]);
			//indexList.add(z);

			}
		}*/
		
		//int[] haha = ((BlockPosting) ips[1]).getPositions();
		//System.out.println("never"+((BlockPosting) ips[0]).getPositions().length);

		
		ArrayList<Integer> arrlist = new ArrayList<Integer>();

		//int haha = Arrays.toString((BlockPosting)ips[1]).getPositions();
//###############################################################################
		//ArrayList<Integer> sumlength = new ArrayList<Integer>();

		double len = 1;
		double pls = 0;
		for (int z = 0; z < numberOfQueryTerms; z++) {
			if(okToUse[z]==true) {
				
				len *= ((BlockPosting) ips[z]).getPositions().length;
				//System.out.println("hi"+len);

			/*for (int i = 0; i < ((BlockPosting) ips[z]).getPositions().length; i++) {
				//int lengths = IntStream.of(combine.get(i)).sum();			
				///error
				int[] all = ((BlockPosting) ips[z]).getPositions();

				int pls1 = all[i];
				System.out.println("haha"+pls1);

				pls += pls1;
				
				//lengthsum.add(lengths);
				//query += lengths; 
				//System.out.println(query);
							
			}
			}*/
			
		}
		}

		for (int i = 0; i < numberOfQueryTerms-1; i++) {
			if(okToUse[i]==true) {
				//len *= ((BlockPosting) ips[i]).getPositions().length;
			for (int j =i+1; j<numberOfQueryTerms;j++) {
				
					if(okToUse[j]==true) {
						
				for (int x = 0; x < ((BlockPosting) ips[i]).getPositions().length; x++) {
					
					for (int y = 0; y < ((BlockPosting) ips[j]).getPositions().length; y++) {
						
						int[] out1 = ((BlockPosting) ips[i]).getPositions();
						int[] out2 = ((BlockPosting) ips[j]).getPositions();
						

						
						int out = Math.abs((out1[x] - out2[y]));
						pls = pls +out;
						//System.out.println("here"+out);
						//arrlist.add(out);

					}
					
	
				}
			}
			}

		}
			//final_score = Collections.min(arrlist);
			//System.out.println("here"+pls);

		}

		
		
		
		
	//	System.out.println("here"+pls);


		//return final_score;
		return pls/len;
		
		
		
		
		
		
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
