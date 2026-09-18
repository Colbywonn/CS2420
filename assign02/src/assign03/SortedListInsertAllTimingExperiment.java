package assign03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import timing.TimingExperiment;

public class SortedListInsertAllTimingExperiment extends TimingExperiment{
    private static String problemSizeDescription = "list size";
    private static int problemSizeMin = 100000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 100000;
    private static int experimentIterationCount = 50;

    private final static Random rng = new Random();

    private SortedArrayList<Integer> sortedList;
    private ArrayList<Integer> targets;

    public static void main(String[] args) {
	TimingExperiment timingExperiment = new SortedArrayListContainsAllTimingExperiment();

	System.out.println("\n---Computing timing results---\n");
	timingExperiment.printResults();
    }

    public SortedListInsertAllTimingExperiment() {
	super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Fills the sorted list and targets with the given number of integers.
     * 
     * @param problemSize - the number of integers to fill the sorted list
     */
    @Override
    protected void setupExperiment(int problemSize) {
	// Populate targets list with problemSize integers.
	targets = new ArrayList<>();
	targets.add(rng.nextInt(10));
	for (int i = 1; i < problemSize; i++) {
	    targets.add(targets.get(i - 1) + rng.nextInt(1, 11));
	}

	// Populates sorted list with every element from targets list. Since
	// this method is not timed, inserts in sorted order for quicker setup.
	sortedList = new SortedArrayList<>();
	for (int i = 0; i < problemSize; i++) {
	    sortedList.insert(targets.get(i));
	}

	// Shuffle elements of the targets array for use in containsAll.
	Collections.shuffle(targets);
    }

    /**
     * Runs the containsAll method for the sorted list.
     */
    @Override
    protected void runComputation() {
	sortedList.insertAll(targets);
    }
}

