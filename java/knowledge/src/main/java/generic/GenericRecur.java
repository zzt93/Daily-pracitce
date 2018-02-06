package generic;

/**
 * @author zzt
 */
public abstract class GenericRecur<T extends Comparable> {
//public abstract class Test<T extends Comparable<T>> {
  abstract T self();
}

class SubTest extends GenericRecur {

  @Override
  Comparable self() {
    return null;
  }
}

class CmpTest extends GenericRecur<Cmp> {

  @Override
  Cmp self() {
    return null;
  }
}
class Cmp implements Comparable {

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}

