import org.junit.Test;

import java.util.ArrayList;

public class ConcurrentModify {
  @Test
  public void test() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      list.add(i);
    }
    for (int i = 0; i < 4; i++) {

      System.out.println(list.remove(i));
    }
  }

  @Test
  public void test1() {
    int i = 1;
    try {
      i = 2;
    }finally {
      i = 3;
      return;
    }
  }
}
