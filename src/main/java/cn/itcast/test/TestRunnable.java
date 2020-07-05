package cn.itcast.test;

public class TestRunnable  {

    static final Object object = new Object();

    public static void main(String[] args) {




        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (object){
                    while(true){
                        System.out.println("这里是线程A");
                        object.notifyAll();
                        try{
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        try{
//                            Thread.sleep(2000);
//                        }catch (Exception e){}

                    }
                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(object){
                    while(true){
                        System.out.println("这里是线程B");
                        object.notifyAll();
                        try{
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        try{
//                            Thread.sleep(2000);
//                        }catch (Exception e){}
                    }
                }


            }
        }).start();
    }

}
