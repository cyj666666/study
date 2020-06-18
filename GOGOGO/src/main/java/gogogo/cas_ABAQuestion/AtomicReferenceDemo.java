package gogogo.cas_ABAQuestion;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: caoyunji
 * @Date: 2020/5/17 23:27
 */
@Data
@AllArgsConstructor
@Builder
class User {
    String userName;
    int age;
}


public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<User>();
//        User z3 = new User("z3", 20);
//        User l4 = new User("l4", 25);
//
//        atomicReference.set(z3);
//        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get());
//        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get());

    }
}
