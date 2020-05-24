package uk.ac.gla.dcs.dsms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import org.terrier.matching.dsms.DependenceScoreModifier;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.Posting;

public class SampleProxFeatureDSM extends DependenceScoreModifier {


	@Override
	protected double calculateDependence(Posting[] ips, 
			boolean[] okToUse, 
			double[] phraseTermWeights, boolean SD 
	) {

		final int numberOfQueryTerms = okToUse.length;
		//Integer small = Integer.MAX_VALUE;
		Integer large = Integer.MIN_VALUE;
		//double final_score = 0.0d;
		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		int leng = 1;

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
		return 0;
	}

	@Override
	protected double scoreFDSD(int matchingNGrams, int docLength) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return "ProxFeatureDSM_MYFUNCTION";
	}

}
