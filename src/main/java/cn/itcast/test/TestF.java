package cn.itcast.test;

public class TestF {
    private static int res;
    private static int N;
    private static int M;
    private static char[][] chars;
    public static void main(String[] args) {

        System.out.print(res);
    }

    public void getSteps(int starti,int startj,boolean[][] flags,int step,int usedTime){
        if(chars[starti][startj] == 'E'){
            res=Math.min(step,res);
            return;
        }

        flags[starti][startj] = true;
        //上
        if(starti>0&&(flags[starti-1][startj]==false)&&chars[starti-1][startj]!='#') getSteps(starti-1,startj,flags,step+1,usedTime);
        //下
        if(starti<(N-1)&&(flags[starti+1][startj]==false)&&chars[starti+1][startj]!='#') getSteps(starti+1,startj,flags,step+1,usedTime);
        //左
        if(startj>0&&(flags[starti][startj-1]==false)&&chars[starti][startj-1]!='#') getSteps(starti,startj-1,flags,step+1,usedTime);
        //右
        if(startj<M-1&&(flags[starti][startj+1]==false)&&chars[starti][startj+1]!='#') getSteps(starti,startj+1,flags,step+1,usedTime);
        //对称
        int newi=N-1-starti;
        int newj=M-1-startj;
        if(usedTime<5&&(flags[newi][newj]==false)&&newi>=0&&newi<=N-1&&newj>=0&&newj<=M-1&&chars[newi][newj]!='#') getSteps(newi,newj,flags,step+1,usedTime+1);

        flags[starti][startj] = false;
    }
}
