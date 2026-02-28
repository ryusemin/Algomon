import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String [] str_arr = br.readLine().split(" ");
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++)
		{
			int temp = Integer.parseInt(str_arr[i]);
			list.add(temp);
		}
		
		int[] arr             = new int[list.size()];
		int[] arr_reverse     = new int[list.size()];
		int[] dp_up_forward   = new int[list.size()];
		int[] dp_down_forward = new int[list.size()];
		int[] dp_up_reverse   = new int[list.size()];
		int[] dp_down_reverse = new int[list.size()];
		int[] sum             = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
		Collections.reverse(list);
		
		for (int i = 0; i < list.size(); i++) arr_reverse[i] = list.get(i);
		
		for (int i = 0; i < arr.length; i++)
		{
			dp_up_forward[i] = 1;
			dp_down_forward[i] = 1;
			dp_up_reverse[i] = 1;
			dp_down_reverse[i] = 1;
			
			for (int j = 0; j < i; j++)
			{
				if (arr[j] < arr[i]) dp_up_forward[i] = Math.max(dp_up_forward[i], dp_up_forward[j] + 1);
				if (arr[j] > arr[i]) dp_down_forward[i] = Math.max(dp_down_forward[i], dp_down_forward[j] + 1);
				if (arr_reverse[j] < arr_reverse[i]) dp_up_reverse[i] = Math.max(dp_up_reverse[i], dp_up_reverse[j] + 1);
				if (arr_reverse[j] > arr_reverse[i]) dp_down_reverse[i] = Math.max(dp_down_reverse[i], dp_down_reverse[j] + 1);
			}
		}
		
		int max = 0;
		int index_N = dp_up_forward.length;
		
		for (int i = 0; i < index_N; i++)
		{
			int temp2 = dp_down_forward[i] + dp_down_reverse[index_N - 1 - i] - 1;
			if (temp2 > max) max = temp2;
		}
		
		System.out.println(max);
	}
}