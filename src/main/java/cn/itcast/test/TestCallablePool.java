package cn.itcast.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestCallablePool implements Callable<Integer> {
    private int num;

    public int getNum() {
        return num;
    }

    public TestCallablePool(int num){
        super();
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        Object object = new Object();
        int sum = 0;
//        System.out.println("任务：" + this.num);
//        System.out.println("当前名称是" + Thread.currentThread().getName());
        for(int i = 0;i<100;i++){
            sum+=i;
        }

        System.out.println("当前任务是" + this.num+"申请的object地址是 : "+object);
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        List<Future> list = new ArrayList<>();

        for(int i =0;i<10;i++){
            Future res = pool.submit(new TestCallablePool(i));
            list.add(res);
        }
        pool.shutdown();

        for(Future f:list){
//            System.out.println(f.get()+"");
        }
    }
}
