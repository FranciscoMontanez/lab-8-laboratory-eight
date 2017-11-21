package mpd;
// Francisco Montanez

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {

    @Override
    public int minimumPairwiseDistance(int[] values) {
        // throw new UnsupportedOperationException();
        this.values = values;
        return 0;
    }

    // int array
    private int[] values;
    //
    private int globalMin = Integer.MAX_VALUE;

    // inner class
    public class lowerLeft implements Runnable {

        private int[] values;
        private int globalMin;

        public lowerLeft(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run() {

        }
    }

    // inner class
    public class bottomRight implements Runnable {

        private int[] values;
        private int globalMin;

        public bottomRight(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run() {

        }
    }

    // inner class
    public class topRight implements Runnable {

        private int[] values;
        private int globalMin;

        public topRight(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run() {

        }
    }

    // inner class
    public class center implements Runnable {

        private int[] values;
        private int globalMin;

        public center(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run() {

        }
    }

}
