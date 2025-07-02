import java.util.*;
class Item{
      int benefit;
      int weight;
      Item(int v,int w){
            benefit = v;
            weight = w;
      }
}
public class FractionalKnapsack{
      public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            Item[] items = new Item[n];
            for(int i = 0;i < n;i++){
                  int benefit = sc.nextInt();
                  int weight = sc.nextInt();
                  items[i] = new Item(benefit,weight);
            }
            Arrays.sort(items,(a,b)->Double.compare((double)b.benefit/b.weight,(double)a.benefit/a.weight));
            double bag = sc.nextInt();
            int totalprofit = 0;
            for(int i = 0;i < items.length;i++){
            if(bag == 0)
            break;
            if(items[i].weight <= bag){
                  totalprofit = totalprofit+items[i].benefit;
                  bag = bag - items[i].weight;
            }
            else{
                  totalprofit += ((double)items[i].benefit / items[i].weight) * bag;
                  break;
            }
            
      }
      System.out.println(totalprofit);
}
}
