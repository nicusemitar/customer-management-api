package multithreading;

import java.math.BigInteger;

public class ComplexCalculation {

    public static void main(String[] args) throws InterruptedException {

        ComplexCalculation c = new ComplexCalculation();
        c.calculateResult(BigInteger.TWO, BigInteger.TEN, BigInteger.TEN, BigInteger.TEN);
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;

        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(thread1.getResult());
        System.out.println(thread2.getResult());
//        /*
//            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
//            Where each calculation in (..) is calculated on a different thread
//        */
        result = thread1.getResult().add(thread2.getResult());

        return result;
    }


    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) < 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);

            }
        }

        public BigInteger getResult() {
            return result;
        }
    }
}