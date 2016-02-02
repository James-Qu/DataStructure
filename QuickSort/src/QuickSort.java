
public class QuickSort {

	public static void main(String[] args) {
		//initial array
		int[] array={15,1,6,2,13,11,7,3,66,21,102,52,12,99,42};
		//initial left and right cursor
		int left=0,right=14;
		System.out.println("Initial array:");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
		quickSort(array,left,right);
		System.out.println("Array after quicksort:");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
			
			
		}
	}


	public static void quickSort(int[] a, int left, int right ){
		int pivot,leftArrow,rightArrow;
		leftArrow=left;
		rightArrow=right;
		pivot=a[(leftArrow+rightArrow)/2];
		do{
			while(a[rightArrow]>pivot){
				--rightArrow;
			}
			while(a[leftArrow]<pivot){
				++leftArrow;
			}
			if(leftArrow<=rightArrow){
				//swap
				int  temp=a[leftArrow];
				a[leftArrow]=a[rightArrow];
				a[rightArrow]=temp;
				++leftArrow;
				--rightArrow;
			}
		}while(rightArrow>leftArrow);
		if(left<rightArrow){
			quickSort(a,left,rightArrow);
		}
		if(leftArrow<right){
			quickSort(a,leftArrow,right);
		}
	}
}
