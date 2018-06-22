#include "com_zsw_jni_sharedmemory1_PointerShared.h"
#include <stdio.h>

char* a;

JNIEXPORT jlong JNICALL Java_com_zsw_jni_sharedmemory1_PointerShared_getPointer
  (JNIEnv * env, jobject jobj)
{
	a="abcde";//这是一个局部变量，仍然能够
	return (long)a;
}

JNIEXPORT void JNICALL Java_com_zsw_jni_sharedmemory1_PointerShared_restorePointer
  (JNIEnv * env, jobject jobj, jlong pointerAddress)
{
  	char *a= (char *)pointerAddress;
	printf("a[0]:%c\n",a[0]);
	printf("a[1]:%c\n",a[1]);
	printf("a[2]:%c\n",a[2]);
	printf("a[3]:%c\n",a[3]);
	printf("a[4]:%c\n",a[4]);
}
