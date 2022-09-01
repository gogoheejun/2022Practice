package equals;

import java.util.Objects;

public class CaseInsensitiveString {//추이성 위배
    private final String s;

    public CaseInsensitiveString(String s){
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  CaseInsensitiveString){
            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
        }
        if(obj instanceof String){
            return s.equalsIgnoreCase((String) obj);
        }
        return false;
    }
}
