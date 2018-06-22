package com.zsw.jni.sharedmemory;

/**
 * 将jni中间量的指针地址保存在java端，需要使用时，java端将该地址传给底层即可
 */
public class Main {
    public static void main(String[] args) throws Exception {
        PointerShared ps = new PointerShared();
        long a = ps.getPointer();
        System.out.println("a:" + a);
        PointerShared ps2 = new PointerShared();
        ps2.restorePointer(a);
    }
}
