package com.zsw.jni.sharedmemory;

public class PointerShared {

    static {
        System.loadLibrary("PointerShared");
    }

    /**
     * 获取指针
     *
     * @return
     */
    public native long getPointer();

    /**
     * 还原指针
     *
     * @param pointerAddress
     */
    public native void restorePointer(long pointerAddress);

}
