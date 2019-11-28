package cn.heyajun;

/**
 * @author Admin
 * @create 2019-09-25 17:17
 * @desc 测试
 **/





import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            String[] strs = new String[number];
            for (int i = 0; i < number; i++) {
                String str = scanner.next();
                strs[i] = str;
            }
            int sum=scanner.nextInt();

            int count=0;
            for (String hh : strs) {
                System.out.println(hh);
                count+=Integer.parseInt(hh);
            }
////           Map<Integer,Integer> map=new LinkedHashMap<>();
////            List<Integer> list=new ArrayList<>();
//           Integer[] ob=new Integer[strs.length];
//           for(int l=0;l<strs.length;l++){
//               String ss=strs[l];
//               char[] chs=ss.toCharArray();
//               if((chs.length-1)>=0&&chs[chs.length-1]=='M'){
//                   StringBuilder builder=new StringBuilder();
//                   for(int m=0;m<chs.length-1;m++){
//                       builder.append(String.valueOf(chs[m]));
//                   }
//                ob[l]=Integer.parseInt(builder.toString());
////                   map.put(l,Integer.parseInt(builder.toString()));
//               }else if((chs.length-1)>=0&&chs[chs.length-1]=='G'){
//                  StringBuilder builder=new StringBuilder();
//                  Integer num=0;
//                  for(int m=0;m<chs.length-1;m++){
//                      builder.append(String.valueOf(chs[m]));
//                  }
//                   num=Integer.parseInt(builder.toString())*1000;
//                   ob[l]=num;
//
//               }else if((chs.length-1)>=0&&chs[chs.length-1]=='T'){
//                   StringBuilder builder=new StringBuilder();
//                   Integer num=0;
//                   for(int m=0;m<chs.length-1;m++){
//                       builder.append(String.valueOf(chs[m]));
//                   }
//                   num=Integer.parseInt(builder.toString())*1000*1000;
//                   ob[l]=num;
//
//               }
//
//           }
//          for(int out=0;out<ob.length;out++){
//              for(int in=out+1;in<ob.length;in++){
//                  if(ob[in] < ob[out]) {
//                      Integer temp = ob[out];
//                      ob[out]=ob[in];
//                      ob[in]=temp;
//                  }
//              }
//          }
//          List<String> p=new ArrayList<>();
//          for(Integer ii:ob){
//              if(ii>0&&ii<1000){
//                 p.add(ii+"M");
//              }else if(ii>1000&&ii<1000*1000){
//                  p.add(ii/1000+"G");
//              }else if(ii>1000*1000){
//                  p.add(ii/(1000*1000)+"T");
//              }
//          }
//          for(String ff:p){
//              System.out.println(ff);
//          }
//
//        }
        }
        scanner.close();
    }

}