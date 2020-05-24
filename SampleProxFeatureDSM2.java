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

public class SampleProxFeatureDSM2 extends DependenceScoreModifier {

	@Override
	 protected double calculateDependence(Posting[] ips,
			boolean[] okToUse, 
			double[] phraseTermWeights, boolean SD 
	) {

		final int numberOfQueryTerms = okToUse.length;
	//	System.out.println("here"+((BlockPosting) ips[4]).getPositions().length);
		//System.out.println("here"+numberOfQueryTerms);
		Integer small = Integer.MAX_VALUE;
		Integer large = Integer.MIN_VALUE;
		ArrayList<Integer> arrlist = new ArrayList<Integer>();

		double len = 1;
		double pls = 0;
		for (int z = 0; z < numberOfQueryTerms; z++) {
			if(okToUse[z]==true) {
				
				len *= ((BlockPosting) ips[z]).getPositions().length;
			
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
		}

		
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
