package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucket = new int[M];
        for (Oomage oo: oomages) {
            int bucketNumber = (oo.hashCode() & 0x7FFFFFF) % M;
            ++bucket[bucketNumber];
        }

        double lowerBound = oomages.size() / 50;
        double upperBound = oomages.size() / 2.5;
        for (int bucketSize: bucket) {
            if (bucketSize < lowerBound || bucketSize > upperBound) {
                return false;
            }
        }
        return true;
    }
}
