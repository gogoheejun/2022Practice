package classAndInterface;

import java.util.Collection;
import java.util.HashSet;

// ITEM 18-1: 잘못된 예- 상속을 잘못사용
// HashSet을 사용하는 프로그램을 만들었음. 이 때 원소가 몇개 추가됐는지 알고 싶은 기능임
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0 ; // 추가된 원소의 수

    public InstrumentedHashSet(){
    }

    public InstrumentedHashSet(int initCap, float loadFactor){
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
