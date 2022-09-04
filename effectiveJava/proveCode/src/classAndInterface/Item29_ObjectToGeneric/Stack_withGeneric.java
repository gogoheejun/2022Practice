package classAndInterface.Item29_ObjectToGeneric;

import java.util.Arrays;
import java.util.EmptyStackException;

// 배열보다 리스트를 우선하라는 아이템28과 모순되지만,
// 제네릭 타입에서 리스트를 사용하는 것이 무조건 좋거나 가능한것도 아님.
public class Stack_withGeneric<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY=16;

    @SuppressWarnings("unchecked")
    public Stack_withGeneric(){
        //대놓고 우회하기! 경고 뜸
        /*
        컴파일러는 타입안전한지 모르지만, 우리는 알 수 있음.
        elements가 private필드에 저장되고, 클라이언트로 반환되거나 다른 메서드에 전달되지 않음.
        또한 push로 들어오는 것도 항상 E임.
        고로 @SuppressWarnings 가능.
         */
        elements = (E[])new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop(){
        if(size == 0) throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null;
        return result;
    }
    private void ensureCapacity() {
        if(elements.length == size){
            elements = Arrays.copyOf(elements,2*size+1);
        }
    }
}
