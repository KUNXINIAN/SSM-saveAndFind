package cn.itcast.test;

import com.sun.corba.se.impl.orbutil.closure.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> f1=new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("这里是f1");
                int sum = 0;
                for(int i = 0;i<100;i++){
                    sum+=i;
                }
                return sum;
            }
        });

        FutureTask<Integer> f2=new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("这里是f2");
                int sum = 0;
                for(int i = 0;i<100;i++){
                    sum+=i;
                }
                return sum;
            }
        });

        new Thread(f1).start();
        new Thread(f2).start();

        System.out.println(f1.get()+"");
        System.out.println(f2.get()+"");
    }
}
