package mpd;
// Francisco Montanez

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {

    @Override
    public int minimumPairwiseDistance(int[] values) {
        // throw new UnsupportedOperationException();
        this.values = values;

        // instances for each class
        lowerLeft LL = new lowerLeft(values, globalMin);
        bottomRight BR = new bottomRight(values, globalMin);
        topRight TR = new topRight(values, globalMin);
        center C = new center(values, globalMin);

        // thread for each section
        Thread ll = new Thread(LL);
        ll.start();
        Thread br = new Thread(BR);
        br.start();
        Thread tr = new Thread(TR);
        tr.start();
        Thread c = new Thread(C);
        c.start();

        // Wait for threads to finish
        try{
            ll.join();
            br.join();
            tr.join();
            c.join();
        }
        catch (InterruptedException exception) {
            System.out.println(exception);
        }
        // updates the global minimum
        // The global minimum returned is the minimum from all 4 threads
        return globalMin;
    }

    private int[] values;

    private int globalMin = Integer.MAX_VALUE;

    public class lowerLeft implements Runnable {
        private int[] values;
        private int globalMin;

        public lowerLeft(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run(){
            int result = Integer.MAX_VALUE;

            for (int i = 0; i < (values.length / 2); i++) {
                for (int j = 0; j < i; j++) {
                    int relative = Math.abs(values[i] - values[j]);
                    if (relative < result) {
                        result = relative;
                    }
                }
            }
            updateGlobalResult(result);
        }
    }

    public class bottomRight implements Runnable {
        private int[] values;
        private int globalMin;

        public bottomRight(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run() {
            int result = Integer.MAX_VALUE;
            for (int i = (values.length / 2); i < (values.length); i++) {
                for (int j = 0; j < i - (values.length / 2); j++) {
                    int relative = Math.abs(values[i] - values[j]);
                    if (relative < result) {
                        result = relative;
                    }
                }
            }
            updateGlobalResult(result);
        }
    }

    public class topRight implements Runnable {

        private int[] values;
        private int globalMin;

        public topRight(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run() {
            int result = Integer.MAX_VALUE;
            for (int i = (values.length / 2); i < values.length; i++) {
                for (int j = (values.length / 2); j < i; j++) {
                    int relative = Math.abs(values[i] - values[j]);
                    if (relative < result) {
                        result = relative;
                    }
                }
            }
            updateGlobalResult(result);
        }
    }

    public class center implements Runnable {
        private int[] values;
        private int globalMin;

        public center(int[] values, int globalMin) {
            this.values = values;
            this.globalMin = globalMin;
        }

        public void run() {
            int result = Integer.MAX_VALUE;
            for (int i = values.length / 2; i < (values.length); i++) {
                for (int j = i - (values.length / 2); j < (values.length / 2); j++) {
                    int relative = Math.abs(values[i] - values[j]);
                    if (relative < result) {
                        result = relative;
                    }
                }
            }
            updateGlobalResult(result);
        }
    }

    public synchronized void updateGlobalResult(int localResult) {
        if (localResult < globalMin){
            globalMin = localResult;
        }
    }
}
