#include <jni.h>
#include <string>
#include <android/log.h>
#include <errno.h>
extern "C" JNIEXPORT jstring



JNICALL
Java_com_personal_tools_simplestvp_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    FILE* fp = fopen("/data/system/packages.xml","r");
    if (fp != NULL){
        __android_log_print(ANDROID_LOG_DEBUG,"virtualapp", "fopen success");
        fclose(fp);
    }
    else{
        __android_log_print(ANDROID_LOG_DEBUG,"virtualapp", "fopen fail:%s", strerror(errno));
    }
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jint JNI_OnLoad(JavaVM* vm,void*)
{
    __android_log_print(ANDROID_LOG_DEBUG,"virtualapp","JNI OnLoad");
    return JNI_VERSION_1_4;
}
