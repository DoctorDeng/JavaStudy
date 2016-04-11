package practice;
/*
 *Description:    冒泡排序
 * Author:        Doctor邓
 * Time:          2016-4-9
 */

public class BubblSort {
	
	//冒泡排序
	public static void bubblSort(int a[]){
		
		int temp;
	    for(int i=0;i<a.length;i++){              //需要比较n-1轮
	    	for(int j=0;j<a.length-i-1;j++){      //根据a.length-i-1,每轮需要比较的次数逐渐减少1次;
	    		if(a[j]>a[j+1]){                  //相邻数比较，符合条件进行替换
	    			temp=a[j];
	    			a[j]=a[j+1];
	    			a[j+1]=temp;
	    		}
	    	}
	    }
	    
	    //输出排序结果
	    for(int i=0;i<a.length;i++){
	    	System.out.print(a[i]+" ");
	    }
	}

	public static void main(String[] args) {
		
		//验证冒泡排序
		int[] a={3,39,58,29,59};
		BubblSort.bubblSort(a);
	}

}
