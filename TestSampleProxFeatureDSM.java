package uk.ac.gla.dcs.dsms;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.terrier.indexing.IndexTestUtils;
import org.terrier.structures.Index;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.IterablePosting;
import org.terrier.tests.ApplicationSetupBasedTest;
import org.terrier.utility.ApplicationSetup;

public class TestSampleProxFeatureDSM extends ApplicationSetupBasedTest
{
	@Test public void testOneDocTwoTerms() throws Exception {

		//make an index with a single sample document
		ApplicationSetup.setProperty("termpipelines", "");
		Index index = IndexTestUtils.makeIndexBlocks(
				new String[]{"doc1"}, 
				new String[]{"the quick jumps is 52 jumps brown fox 52 over jumps 52 lazy jumps dog fox is fox over jumps cheeky fox haha is the "});

		//get posting iterators for two terms 'fox' and 'jumps'
		IterablePosting[] ips = new IterablePosting[4];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("fox"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("jumps"));
		ips[2] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("the"));
		ips[3] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("52"));

		ips[0].next();
		ips[1].next();
		ips[2].next();
		ips[3].next();

		assertEquals(0, ips[0].getId());
		assertEquals(0, ips[1].getId());
		assertEquals(0, ips[2].getId());
		assertEquals(0, ips[3].getId());
		System.out.println("Positions of term 'fox'="+ Arrays.toString( ((BlockPosting)ips[0]).getPositions()));
		System.out.println("Positions of term 'jumps'="+ Arrays.toString( ((BlockPosting)ips[1]).getPositions()));
		System.out.println("Positions of term 'the'="+ Arrays.toString( ((BlockPosting)ips[2]).getPositions()));
		System.out.println("Positions of term 'the'="+ Arrays.toString( ((BlockPosting)ips[3]).getPositions()));

		SampleProxFeatureDSM sample = new SampleProxFeatureDSM();
		double score = sample.calculateDependence(
            ips, //posting lists
            new boolean[]{true,true, true, true},  
            new double[]{1d,1d}, false
		);
		System.out.println(score);
	}
}
