package jvmLearn;


import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class gc {

    public static void main(String[] args) {

        Object object = new Object(); //强引用  即使jvm 内存不足,也不会回收
        SoftReference<Object> softReference = new SoftReference<Object>(object);//软引用
        System.out.println(softReference.get().hashCode());
        object = null; //软引用  只有在jvm 内存不足时,才会回收    软引用可用来实现内存敏感的高速缓存。
        // 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
        System.gc();
//        System.out.println(softReference.get().hashCode());
        System.out.println("################");
        object = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(object);    //弱引用
        // gc线程 一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
        // 弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
        //当你想引用一个对象，但是这个对象有自己的生命周期，你不想介入这个对象的生命周期，这时候你就是用弱引用。
        //这个引用不会在对象的垃圾回收判断中产生任何附加的影响
//        System.out.println(weakReference.get().hashCode());
        object = null;
        System.gc();//调用 gc object 已被回收
        //System.out.println(weakReference.get().hashCode());


        //  “虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
        //虚引用主要用来跟踪对象被垃圾回收器回收的活动。虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。
        // 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之 关联的引用队列中。
    }
}
