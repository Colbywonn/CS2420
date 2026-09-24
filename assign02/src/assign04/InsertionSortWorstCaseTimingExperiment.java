package assign04;

import java.util.Comparator;

import timing.ArrayGenerator;
import timing.TimingExperiment;

public class InsertionSortWorstCaseTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "arrayLength";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 10;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 10;

    protected Integer[] array;
    

    public static void main(String[] args) {
	TimingExperiment timingExperiment = new InsertionSortWorstCaseTimingExperiment();

	System.out.println("\n---Computing timing results---\n");
	timingExperiment.printResults();
    }

    public InsertionSortWorstCaseTimingExperiment() {
	super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }


    @Override
    protected void setupExperiment(int problemSize) {
	array = ArrayGenerator.generateDescendingArray(problemSize);
    }


    @Override
    protected void runComputation() {
	IntegerStringUtility.insertionSort(array, Comparator.naturalOrder());

    }

}
