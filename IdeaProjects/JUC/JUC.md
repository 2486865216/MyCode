# 1.什么是JUC

## 1.1.JUC简介

​	在java中，线程部分是一个重点，本篇文章说的UC也是关于线程的。JUC就是java.util.concurrent工具包的简称。这是一个处理线程的工具包，JDK1.5开始出现的

## 1.2.进程与线程

​	**进程(Process)**是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位，是操作系统结构的基础。在当代面向线程设计的计算机结构中，进程是线程的容器。程序是指令、数据及其组织形式的描述，进程是程序的实体。是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位，是操作系统结构的基础。程序是指令、数据及其组织形式的描述，进程是程序的实体。

​	**线程(thread)**是操作系统能够进行运算调度的最小单位。它被包含在进程之中，是进程中的实际运作单位。一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。

**总结来说：**

**进程：**指在系统中正在运行的一个应用程序；程序一旦运行就是进程；进程一一资源分配的最小单位。

**线程：**系统分配处理器时间资源的基本单元，或者说进程之内独立执行的一个单元执行流。线程———程序执行的最小单位。

## 1.3.线程的状态

### 1.3.1.线程状态枚举类

**Thread.State**

```java
public enum State{
    NEW(新建);
    RUNNABLE(准备就绪);
    BLOCKED(阻塞);
    VAITING(不见不散);
    TIMED_WAITING(过时不候);
    TERMINATED(终结);
}
```

### 1.3.2.wait/sleep

(1)sleep是Thread的静态方法，wait是Object的方法，任何对象实例都能调用。

(2)sleep不会释放锁，它也不需要占用锁。wait会释放锁，但调用它的前提是当前线程占有锁（即代码要在synchronized中）

(3)它们都可以被interrupted方法中断。

## 1.4.并发与并行

### 1.4.1.串行模式

​	串行表示所有任务都一一按先后顺序进行。串行意味着必须先装完一车柴才能运送这车柴，只有运送到了，才能卸下这车柴，并且只有完成了这整个三个步骤，才能进行下一个步骤。

**串行是一次只能取得一个任务，并执行这个任务**

### 1.4.2.并行模式

并行意味着可以同时取得多个任务，并同时去执行所取得的这些任务。并行模式相当于将长长的一条队列，划分成了多条短队列，所以并行缩短了任务队列的长度。并行的效率从代码层次上强依赖于多进程/多线程代码，从硬件角度上则依赖于多核CPU。

### 1.4.3.并发

​	**并发(concurrent)指的是多个程序可以同时运行的现象，更细化的是多进程可以同时运行或者多指令可以同时运行。**但这不是重点，在描述并发的时候也不会去扣这种字眼是否精确，==并发的重点在于它是一种现象==，==并发描述的是多进程同时运行的现象==。但实际上，对于单核心CPU来说，同一时刻只能运行一个线程。所以，这里的"同时运行"表示的不是真的同一时刻有多个线程运行的现象，这是并行的概念，而是提供一种功能让用户看来多个程序同时运行起来了，但实际上这些程序中的进程不是一直霸占CPU的，而是执行一会停一会。

​	**要解决大并发问题，通常是将大任务分解成多个小任务，**由于操作系统对进程的调度是随机的，所以切分成多个小任务后，可能会从任一小任务处执行。这可能会出现一些现象

- 可能出现一个小任务执行了多次，还没开始下个任务的情况。这时一般会采用队列或类似的数据结构来存放各个小任务的成果，
- 可能出现还没准备好第一步就执行第二步的可能。这时，一般采用多路复用或异步的方式，比如只有准备好产生了事件通知才执行某个任务。·
- 可以多进程/多线程的方式并行执行这些小任务。也可以单进程/单线程执行这些小任务，这时很可能要配合多路复用才能达到较高的效率

### 1.4.4.小结(重点)

并发：同一时刻多个线程在访问同一个资源，多个线程对一个点

​	例子：春运抢票电商秒杀

并行：多项工作一起执行，之后再汇总

​	例子：泡方便面，电水壶烧水，一边撕调料倒入桶中





![image-20220321131324752](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220321131324752.png)



![image-20220321131736058](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220321131736058.png)

# 2.Lock接口 

## 2.1.1 Synchronized关键字回顾

​	synchronized是Java中的关键字，是一种同步锁。它修饰的对象有以下几种：：

1. 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象；
2. 修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象；

虽然可以使用synchronized来定义方法，但synchronized并不属于方法定义的一部分，因此，synchronized关键字不能被继承。如果在父类中的某个方法使用了synchronized关键字，而在子类中覆盖了这个方法，在子类中的这个方法默认情况下并不是同步的，而必须显式地在子类的这个方法中加上

3. 修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象；
4. 修改一个类，其作用的范围是synchronized后面括号括起来的部分，作用主的对象是这个类的所有对象。

## Synchronized实现卖票例子

## 多线程编程步骤（上）

第一创建资源类，创建属性和操作方法

第二创建多线程调用资源类的方法

```java
package com.example.sync;

/**
 * author ye
 * createDate 2022/3/21  13:33
 *
 * //第一步创建资源类，定义属性和和燥作方法
 */
class Ticket{
    //票数
    private int number = 30;
    //买票
    public synchronized void sale(){
        //判断是否有票
        if (number > 0){
            System.out.println(Thread.currentThread().getName() + " : 卖出1, 剩下:" + number--);
        }
    }
}
public class SaleTicket {
    //第二步创建多个线程，调用资源类的燥作方法
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}
```

## 2.2.什么是Lock

​	Lock锁实现提供了比使用同步方法和语句可以获得的更广泛的锁操作。它们允许更灵活的结构，可能具有非常不同的属性，并且可能支持多个关联的条件对象。Lock提供了比synchronized更多的功能。

可重入锁指的是已经获得该锁了，但在代码块里还能接着获得该锁，只是后面也要释放两次该锁

**Lock与的Synchronized区别**

- Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问；
- Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。

- ```
	public class ReentrantLock
	extends Object
	implements Lock, Serializable
	```

## 2.3 ReentrantLock

一个可重入互斥**Lock**具有与使用**synchronized**方法和语句访问的隐式监视锁相同的基本行为和语义，但具有扩展功能。

一个**ReentrantLock**由线程拥有 ，最后成功锁定，但尚未解锁。  调用lock的线程将返回，成功获取锁，当锁不是由另一个线程拥有。  如果当前线程已经拥有该锁，该方法将立即返回。 这可以使用方法`isHeldByCurrentThread()`和`getHoldCount()`进行检查。 

该类的构造函数接受可选的公平参数。 当设置`true`  ，在争用下，锁有利于授予访问最长等待的线程。 否则，该锁不保证任何特定的访问顺序。  使用许多线程访问的公平锁的程序可能会比使用默认设置的整体吞吐量小（即，更慢，通常要慢得多），但是具有更小的差异来获得锁定并保证缺乏饥饿。  但是请注意，锁的公平性不能保证线程调度的公平性。  因此，使用公平锁的许多线程之一可以连续获得多次，而其他活动线程不进行而不是当前持有锁。 另请注意， 未定义的`tryLock()`方法不符合公平性设置。  如果锁可用，即使其他线程正在等待，它也会成功。 

建议的做法是始终立即跟随`lock`与`try`块的通话，最常见的是在之前/之后的建设，如： 

```java
   class X { 
       private final ReentrantLock lock = new ReentrantLock(); // ... 
       public void m() { 
           lock.lock(); // block until condition holds 
           try { 
               // ... method body 
           } 
           finally { 
               lock.unlock() 
           } 
       } 
   } 
```

除了实现[`Lock`](../../../../java/util/concurrent/locks/Lock.html)接口，这个类定义了许多`public`种`protected`方法用于检查锁的状态。  其中一些方法仅适用于仪器和监控。 

此类的序列化与内置锁的操作方式相同：反序列化锁处于未锁定状态，无论其序列化时的状态如何。 

此锁最多支持同一个线程的2147483647递归锁。 尝试超过此限制会导致`Error`从锁定方法中抛出。 

## 2.4 ReadWriteLock

`public interface ReadWriteLock`

A `ReadWriteLock`维护一对关联的`locks` ，一个用于只读操作，一个用于写入。`read  lock`可以由多个阅读器线程同时进行，只要没有作者。`write  lock`是独家的。

所有`ReadWriteLock`实现必须保证的存储器同步效应`writeLock`操作（如在指定`Lock`也保持相对于所述相关联的`readLock`  。 也就是说，**一个线程成功获取读锁定将会看到在之前发布的写锁定所做的所有更新。** 

读写锁允许访问共享数据时的并发性高于互斥锁所允许的并发性。 它利用了这样一个事实：一次只有一个线程（写入线程）可以修改共享数据，在许多情况下，任何数量的线程都可以同时读取数据（因此*读取器*线程）。  从理论上讲，通过使用读写锁允许的并发性增加将导致性能改进超过使用互斥锁。  实际上，并发性的增加只能在多处理器上完全实现，然后只有在共享数据的访问模式是合适的时才可以。 

读写锁是否会提高使用互斥锁的性能取决于数据被读取的频率与被修改的频率相比，读取和写入操作的持续时间以及数据的争用 -  即是，将尝试同时读取或写入数据的线程数。  例如，最初填充数据的集合，然后经常被修改的频繁搜索（例如某种目录）是使用读写锁的理想候选。  然而，如果更新变得频繁，那么数据的大部分时间将被专门锁定，并且并发性增加很少。  此外，如果读取操作太短，则读写锁定实现（其本身比互斥锁更复杂）的开销可以支配执行成本，特别是因为许多读写锁定实现仍将序列化所有线程通过小部分代码。  最终，只有剖析和测量将确定使用读写锁是否适合您的应用程序。 

虽然读写锁的基本操作是直接的，但是执行必须做出许多策略决策，这可能会影响给定应用程序中读写锁定的有效性。  这些政策的

例子包括： 

- 在写入器释放写入锁定时，确定在读取器和写入器都在等待时是否授予读取锁定或写入锁定。  作家偏好是常见的，因为写作预计会很短，很少见。  读者喜好不常见，因为如果读者经常和长期的预期，写作可能导致漫长的延迟。 公平的或“按顺序”的实现也是可能的。 
- 确定在读卡器处于活动状态并且写入器正在等待时请求读取锁定的读取器是否被授予读取锁定。  读者的偏好可以无限期地拖延作者，而对作者的偏好可以减少并发的潜力。 
- 确定锁是否可重入：一个具有写锁的线程是否可以重新获取？ 持有写锁可以获取读锁吗？  读锁本身是否可重入？ 
- 写入锁可以降级到读锁，而不允许插入写者？ 读锁可以升级到写锁，优先于其他等待读者或作者吗？ 

在评估应用程序的给定实现的适用性时，应考虑所有这些问题。

## 2.5小结重点)

Lock和synchronized有以下几点不同：

1. Lock是一个接口，而synchronized是java中的关键字，synchronized是内置的语言实现；
2. synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock():去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
3. Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
4. 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
5. Lock可以提高多个线程进行读操作的效率。

在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时LOck的性能要**远远优于**synchronized。

# 3.线程间通信

## 3.1.线程间通信

```java
package com.example.ThreadDemo;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/3/21  20:26
 */
class TestThread {
    private Integer number = 0;

    public synchronized void increment() throws InterruptedException {
        //wait()那里睡，那里醒，会直接执行之后的代码，导致虚假唤醒
        //用while解决
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();
    }
}

public class Demo01 {
    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testThread.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}

========================================================
package com.example.Lock;


import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/23  8:14
 */
class Share{
    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

}
public class ThreadDemo02 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"CC").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"DD").start();
    }
}
```



## 3.2.线程间定制化通信

```java
package com.example.Lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/23  8:30
 */
class ShareResource{
    //定义标志位
    private int flag = 1; //1 AA 2 BB 3 CC

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印一次
    public void printOne(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1){
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "::第" + loop + "轮");
            flag = 2;
            //通知BB线程
            c2.signal();
        }finally{
            lock.unlock();
        }
    }
    //打印两次
    public void printTwo(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "::第" + loop + "轮");
            }
            flag = 3;
            //通知CC线程
            c3.signal();
        }finally{
            lock.unlock();
        }
    }
    //打印三次
    public void printThree(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "::第" + loop + "轮");
            }
            flag = 1;
            //通知AA线程
            c1.signal();
        }finally{
            lock.unlock();
        }
    }
}
public class ThreadDemo03 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.printOne(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.printTwo(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.printThree(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}

```



# 4.集合的线程安全

## 4.1.ArrayList集合线程不安全演示

```java
package com.example.ListThread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * author ye
 * createDate 2022/3/23  9:00
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}

```

![image-20220323090842963](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323090842963.png)

### 4.1.1.解决方案-Vector

```java
List<String> list = new Vector<>();
```



### 4.1.2.解决方案-Collections

```java
List<String> list = Collections.synchronizedList(new ArrayList<>());
```



### 4.1.3.解决方案-CopyOnWriteArrayList

```java
List<String> list = new CopyOnWriteArrayList<>();
```

![image-20220323091434058](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20222323091434058.png)

## 4.2.HashSet线程不安全演示

```java
Set<String> set  = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
```

![image-20220323092529015](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323092529015.png)

### 4.2.1.解决方案-CopyOnWriteArraySet

```java
Set<String> set  = new CopyOnWriteArraySet<>();
```

## 4.3.HashMap线程不安全演示

```java
Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
```

![image-20220323092958974](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323092958974.png)

### 4.3.1解决方案-ConcurrentHashMap

```java
Map<String, String> map = new ConcurrentHashMap<>();
```

# 5.多线程锁

## 5.1演示锁的八种情况

```java
package com.example.sync02;

/**
 * author ye
 * createDate 2022/3/23  12:02
 */
class Phone{
    public static synchronized void sendSMS() throws InterruptedException {
        //停留四秒
        Thread.sleep(4000);
        System.out.println("sendSMS");
    }
    public synchronized void sendEmail(){
        System.out.println("sendEmail");
    }
    public void getHello(){
        System.out.println("getHello");
    }
}

/**
 * 8锁
 *
 * 1 标准访问，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 2 停4秒在短后方法内，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 3 新增普通TheLLo.方法，是先打短信还是he llo
 *      getHello
 *      sendSMS
 * 4 现在有两部手机，先打印短信还是邮件
 *      sendEmail
 *      sendSMS
 * 5 两个静态同步方法，1部手机，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 6两个静态同步方法，2部手机，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 *      sendEmail
 *      sendSMS
 * 8 1个静态同步方法，1个普通同步方法，2部手机，先打印短后还是邮件
 *      sendEmail
 *      sendSMS
 */
public class Lock_8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            //phone.sendEmail();
            //phone.getHello();
            phone2.sendEmail();
        },"BB").start();
    }
}
```

synchronized实现同步的基础：Java中的每一个对象都可以作为锁

具体表现为以下3种形式。

对于普通同步方法，锁是当前实例对象。

对于静态同步方法，锁是当前类的class对象。

对于同步方法块，锁是Synchonized括号里配置的对象

## 5.2公平锁和非公平锁

```java	
Lock lock = new ReentrantLock(true);
```



![image-20220323122458143](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323122458143.png)

## 6.3可重入锁(递归锁)

synchronized(隐式)和Lock(显式)都是可重入锁

```java
package com.example.syncLockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/23  12:29
 * 可重入锁
 */
public class Test {
    public synchronized void add(){
        add();
    }
    public static void main(String[] args) {
        //synchronized
        //new Test().add();
        /*Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "::外层");

                synchronized (o){
                    System.out.println(Thread.currentThread().getName() + "::中层");

                    synchronized (o){
                        System.out.println(Thread.currentThread().getName() + "::内层");
                    }
                }
            }
        },"t1").start();*/

        //lock
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "::外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "::内层");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
```

## 5.4.死锁

```java
package com.example.DeadLock;

/**
 * author ye
 * createDate 2022/3/23  13:05
 */
public class Test {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + "  持有o1,试图获取o2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() + "试图获取o2");
                }
            }
        },"A").start();

        new Thread(() -> {
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "  持有o2,试图获取o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + "试图获取o1");
                }
            }
        },"B").start();
    }
}

```

## 死锁验证

![image-20220323131412344](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323131412344.png)

![image-20220323131514776](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323131514776.png)

![image-20220323131530724](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323131530724.png)

# 5.Callable&Future接口

## 5.1 Callable接口.

目前我们学习了有两种创建线程的方法-一种是通过创建Thread类，另一种是通过使用Runnable创建线程。但是，Runnable缺少的一项功能是，当线程终止时（即run()完成时），我们无法使线程返回结果。为了支持此功能，Java中提供了Callable接口。

**==现在我们学习的是创建线程的第三种方案--Callable接口==**

**Callable接口的特点如下（重点）**

- 为了实现Runnable,需要实现不返回任何内容的run()方法，而对于Callable,需要实现在完成时返回结果的call()方法。
- call()方法可以引发异常，而run()则不能。
- 为实现Callable而必须重写call方法
- 不能直接替换runnable,因为Thread类的构造方法根本没有Callable.



![image-20220323135309853](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220323135309853.png)

```java
package com.example.Callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * author ye
 * createDate 2022/3/23  13:19
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunableTest RunTest = new RunableTest();
        CallableTest callTest = new CallableTest();

        //RunnabLe接口创建线程
        new Thread(RunTest, "AA").start();
        //Callable接口创建线程
        //new Thread(callTest, "BB").start(); //报错


        //FutureTask
        //FutureTask<Integer> futureTask = new FutureTask(new CallableTest());

        FutureTask<Integer> futureTask = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "come in callable");
            return 1024;
        });

        new Thread(futureTask, "call").start();

        while (!futureTask.isDone()){
            System.out.println("wait...");
        }

        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName() + "over");
    }
    //FutureTask,原理未来任务
/**
 *1、
 老师上课，口渴了，去买票不合适，讲课线程继续。
 单开启线程找班上班长帮我买水，把水买回来，需要时候直接t

 *2、4个同学，1同学1+2..5 ,2同学10+11+12..50, 3同学60+61+62, 4同学100+200
 第2个同学计算量比较大，
 FutureTask单开启线程给2同学计算，先汇总134，最后等2同学计算位完成，统一汇总

 *3、考试，做会做的题目，最后看不会做的题目
 */
}
```



# 6.JUC三大辅助类

## 6.1减少计数CountDownLatch.

CountDownLatch类可以设置一个计数器，然后通过countDown方法来进行减1的操作，使用await方法等待计数器不大于0，然后继续执行await方法之后的语句。

- CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
- 其它线程调用countDown方法会将计数器减1（调用countDown方法的线程不会阻塞)
- 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行：

**场景：6个同学陆续离开教室后值班同学才可以关门。**

```java
package com.example.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * author ye
 * createDate 2022/3/23  13:58
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象’设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号同学离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "班长锁门走人");
    }
}
```

## 6.2循环栅栏CyclicBarrier

CyclicBarrier看英文单词可以看出大概就是循环阻塞的意思，在使用中CyclicBarrier的构造方法第一个参数是目标障碍数，每次执行CyclicBarrier一次障碍数会加一，如果达到了目标障碍数，才会执行cyclicBarrier.await()之后的语句。可以将CyclicBarrier理解为减一操作

场景：集齐7颗龙珠就可以召唤神龙

```java
package com.example.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * author ye
 * createDate 2022/3/23  19:21
 */
public class CyclicBarrierDemo {
    private static final Integer NUMBER = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐七颗龙珠");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"星龙珠被收集");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
```

## 6.3信号灯Semaphore

Semaphore的构造方法中传入的第一个参数是最大信号量（可以看成最大线程池)，每个信号量初始化为一个最多只能分发一个许可证。使用acquire方法获得许可证，release方法释放许可，

场景：抢车位，6部汽车3个停车位：

```java
package com.example.JUC;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * author ye
 * createDate 2022/3/23  19:30
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + "抢到车位");

                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName() + "======离开了车位");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
```



# 7.读写锁ReentrantReadWriteLock

读写锁：一个资源可以被多个读线程访问，或者可以被一个写线程访问，但是不能同时存在读写线程，读写互斥，读读共享的

```java
package com.example.ReadWire;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author ye
 * createDate 2022/3/23  19:49
 */
class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

    //创建读写锁
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //放数据
    public void put(String key, Object value){
        //添加写锁
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写操作");
            Thread.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            lock.writeLock().unlock();
        }
    }

    //取数据
    public Object get(String key){

        //添加读锁
        lock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在读操作");
            Thread.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取完了" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
        return result;
    }
}
public class ReadWireDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }
}

```

## 锁降级：将写入锁降级为读锁

 ```java
 package com.example.ReadWire;
 
 import java.util.concurrent.locks.ReentrantReadWriteLock;
 
 /**
  * author ye
  * createDate 2022/3/23  20:10
  * 锁降级
  */
 public class ReadWireDemo02 {
     public static void main(String[] args) {
         //可重入读写锁对象
         ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
         ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
         ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
 
         //锁降级
         writeLock.lock();
         System.out.println("Hello World");
 
         readLock.lock();
         System.out.println("----read");
 
         writeLock.unlock();
         readLock.unlock();
     }
 }
 
 ```



# 8.阻塞队列BlockingQueue

## 8.1 BlockingQueue简介。

Concurrent包中，BlockingQueue很好的解决了多线程中，如何高效安全“传输”数据的问题。通过这些高效并且线程安全的队列类，为我们快速搭建高质量的多线程程序带来极大的便利。本文详细介绍了BlockingQueue家庭中的所有成员，包括他们各自的功能以及常见使用场景。

阻塞队列，顾名思义，首先它是一个队列，通过一个共享的队列，可以使得数据由队列的一端输入，从另外一端输出；

![image-20220324083416747](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-10220324083416747.png)

当队列是空的，从队列中获取元素的操作将会被阻塞，

当队列是满的，从队列中添加元素的操作将会被阻塞

试图从空的队列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入新的元素

试图向已满的队列中添加新元素的线程将会被阻塞，直到其他线程从队列中移除一个或多个元素或者完全清空，使队列变得空闲起来并后续新增

常用的队列主要有以下两种：

- 先进先出（FIFO):先插入的队列的元素也最先出队列，类似于排队的功能。从某种程度上来说这种队列也体现了一种公平性
- 后进先出(LIFO):后插入队列的元素最先出队列，这种队列优先处理最近发生的事件（栈）

在多线程领域：所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤起

为什么需要Blocking Queue:

好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了在concurrent包发布以前，在多线程环境下，我们每个程序员都必须去自己控制这些细节，尤其还要兼顾效率和线程安全，而这会给我们的程序带来不小的复杂度。

多线程环境中，通过队列可以很容易实现数据共享，比如经典的“生产者”和“消费者”模型中，通过队列可以很便利地实现两者之间的数据共享。假设我们有若干生产者线程，另外又有若干个消费者线程。如果生产者线程需要把准备好的数据共享给消费者线程，利用队列的方式来传递数据，就可以很方便地共享数据

## 8.2常见的BlockingQueue.

### 8.2.1 ArrayBlockingQueue(常用)

基于数组的阻塞队列实现，在ArrayBlocking Queue内部，维护了一个定长数组，以便缓存队列中的数据对象，这是一个常用的阻塞队列，除了一个定长数组外，ArrayBlockingQueue内部还保存着两个整形变量，分别标识着队列的头部和尾部在数组中的位置。“

ArrayBlockingQueue在生产者放入数据和消费者获取数据，都是共用同一个锁对象，由此也意味着两者无法真正并行运行，这点尤其不同于LinkedBlockingQueue;按照实现原理来分析，ArrayBlockingQueue完全可以采用分离锁，从而实现生产者和消费者操作的完全并行运行。Doug Lea之所以没这样去做，也许是因为ArrayBlocking Queue的数据写入和获取操作已经足够轻巧，以至于引入独立的锁机制，除了给代码带来额外的复杂性外，其在性能上完全占不到任何便宜。ArrayBlockingQueue和LinkedBlockingQueue间还有一个明显的不同之处在于，前者在插入或删除元素时不会产生或销毁任何额外的对象实例，而后者则会生成一个额外的Node对象。这在长时间内需要高效并发地处理大批量数据的系统中，其对于GC的影响还是存在一定的区别。而在创建ArrayBlockingQueue时，我们还可以控制对象的内部锁是否采用公平锁，默认采用非公平锁。

**==一句话总结：由数组结构组成的有界阻塞队列。==**

### 8.2.2 LinkedBlockingQueue(常用)

基于链表的阻塞队列，同ArrayListBlockingQueue类似，其内部也维持着一个数据缓冲队列（该队列由一个链表构成），当生产者往队列中放入一个数据时，队列会从生产者手中获取数据，并缓存在队列内部，而生产者立即返回；只有当队列缓冲区达到最大值缓存容量时(LinkedBlockingQueue可以通过构造函数指定该值)，才会阻塞生产者队列，直到消费者从队列中消费掉一份数据，生产者线程会被唤醒，反之对于消费者这端的处理也基于同样的原理。而LinkedBlockingQueue之所以能够高效的处理并发数据，还因为其对于生
产者端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。

**ArrayBlockingQueue和LinkedBlockingQueue是两个最普通也是最常用的阻塞队列，一般情况下，在处理多线程间的生产者消费者问题，使用这两个类足以。**

**==一句话总结：由链表结构组成的有界（但大小默认值为integer.MAX_VALUE)阻塞队列。==**

### 8.2.3 DelayQueue.

DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue是一个没有大小限制的队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞。

**==一句话总结：使用优先级队列实现的延迟无界阻塞队列。==**

### 8.2.4 PriorityBlockingQueue

基于优先级的阻塞队列（优先级的判断通过构造函数传入的Compator对象来决定)，但需要注意的是PriorityBlockingQueue并**不会阻塞数据生产者，而只会在没有可消费的数据时，阻塞数据的消费者。**

因此使用的时候要特别注意，**生产者生产数据的速度绝对不能快于消费者消费数据的速度**，否则时间一长，会最终耗尽所有的可用堆内存空间。

在实现PriorityBlockingQueue时，内部控制线程同步的锁采用的是公平锁。

**==一句话总结：支持优先级排序的无界阻塞队列。==**

### 8.2.5 SynchronousQueue.

一种无缓冲的等待队列，类以于无中介的直接交易，有点像原始社会中的生产者和消费者，生产者拿着产品去集市销售给产品的最终消费者，而消费者必须亲自去集市找到所要商品的直接生产者，如果一方没有找到合适的目标，那么对不起，大家都在集市等待。相对于有缓冲的BlockingQueue来说，少了一个中间经销商的环节（缓冲区），如果有经销商，生产者直接把产品批发给经销商，而无需在意经销商最终会将这些产品卖给那些消费者，由于经销商可以库存一部分商品，因此相对于直接交易模式，总体来说采用中间经销商的模式会吞吐量高一些（可以批量买卖）；但另一方面，又因为经销商的引入，使得产品从生产者到消费者中间增加了额外的交易环节，单个产品的及时响应性能可能会降低

声明一个SynchronousQueue有两种不同的方式，它们之间有着不太一样的行为。

**公平模式和非公平模式的区别：**

- 公平模式：SynchronousQueue会采用公平锁，并配合一个FIFO队列来阻塞多余的生产者和消费者，从而体系整体的公平策略：
- 非公平模式(SynchronousQueue默认)：SynchronousQueue采用非公平锁，同时配合一个「O队列来管理多余的生产者和消费者，而后一种模式如果生产者和消费者的处理速度有差距，则很容易出现饥渴的情况，即可能有某些生产者或者是消费者的数据永远都得不到处理。

**==一句话总结：不存储元素的阻塞队列，也即单个元素的队列。==**

### 8.2.6 LinkedTransferQueue.

LinkedTransferQueue是一个由链表结构组成的无界阻塞TransferQueue队列。相对于其他阻塞队列，LinkedTransferQueue多了tryTransfer和transfer方法。

LinkedTransferQueue采用-种预占模式。意思就是消费者线程取元素时，如果队列不为空，则直接取走数据，若队列为空，那就生成一个节点（节点元素为null)入队，然后消费者线程被等待在这个节点上，后面生产者线程入队时发现有一个元素为null的节点，生产者线程就不入队了，直接就将元素填充到该节点，并唤醒该节点等待的线程，被唤醒的消费者线程取走元素，从调用的方法返回。

**==一句话总结：由链表组成的无界阻塞队列。==**

### 8.2.7 LinkedBlockingDeque.

LinkedBlockingDeque是一个由链表结构组成的双向阻塞队列，即可以从队列的两端插入和移除元素。

对于一些指定的操作，在插入或者获取队列元素时如果队列状态不允许该操作可能会阻塞住该线程直到队列状态变更为允许操作，这里的阻塞一般有两种情况

- 插入元素时：如果当前队列已满将会进入阻塞状态，一直等到队列有空的位置时再讲该元素插入，该操作可以通过设置超时参数，超时后返回false表示操作失败，也可以不设置超时参数一直阻塞，中断后抛出InterruptedException异常
- 读取元素时：如果当前队列为空会阻塞住直到队列不为空然后返回元素，同样可以通过设置超时参数

**==一句话总结：由链表组成的双向阻塞队列==**

![image-20220324090337592](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324090337592.png)

```java
package com.example.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * author ye
 * createDate 2022/3/24  8:56
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(3);
        /*System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));

        System.out.println(queue.element());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());*/

        //System.out.println(queue.add("d"));

        /*System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d"));

        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());*/

        /*queue.put("a");
        queue.put("b");
        queue.put("c");
        //queue.put("d");
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        //System.out.println(queue.take());*/

        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d", 3l, TimeUnit.SECONDS));


        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
```

## 8.3小结

1. **在多线程领域：所谓阻塞，在某些情况下会挂起线程（即阻塞）满足，被挂起的线程又会自动被唤起**
2. **为什么需要BlockingQueue?在concurrent包发布以前，在多线程环境下，我们每个程序员都必须去自己控制这些细节，尤其还要兼顾效率和线程安全而这会给我们的程序带来不小的复杂度。使用后我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了**

# 9.ThreadPool线程池

## 9.1线程池简介

线程池（英语：thread pool):一种线程使用模式。线程过多会带来调度开销，进而影响缓存局部性和整体性能。而线程池维护着多个线程，等待着监督管理者分配可并发执行的任务。这避免了在处理短时间任务时创建与销毁线程的代价。线程池不仅能够保证内核的充分利用，还能防止过分调度。

例子：10年前单核CPU电脑，假的多线程，像马戏团小丑玩多个球，CPU需要来回切换。现在是多核电脑，多个线程各自跑在独立的CPU上，不用切换效率高。

**线程池的优势：**线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量，超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行。

**它的主要特点为：**

- 降低资源消耗：通过重复利用已创建的线程降低线程创建和销毁造成的销耗。
- 提高响应速度：当任务到达时，任务可以不需要等待线程创建就能立即执行。
- 提高线程的可管理性：线程是稀缺资源，如果无限制的创建，不仅会销耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。
- Java中的线程池是通过Executor框架实现的，该框架中用到了Executor,Executors,ExecutorService,ThreadPoolExecutor这几个类

## 9.2.线程池的架构

![image-20220324091132725](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324091132725.png)

## 9.3线程池使用方式

### Executors.newFixedThreadPool(int)

一池N线程

### Executors.newSingleThreadExecutor()

一个任务一个任务执行，一池一线程

### Executors.newCachedThreadPool())

线程池根据需求创建线程，可扩容，遇强则强

## 9.4参数说明



![image-20220324092811946](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324092811946.png)

## 9.5执行流程

![image-20220324092928881](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324092928881.png)

![image-20220324093400625](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324093400625.png)

## 9.6自定义线程池

```java
package com.example.ThreadPool;

import java.util.concurrent.*;

/**
 * author ye
 * createDate 2022/3/24  9:37
 * 自定义线程池
 */
public class Demo02 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < 20; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理义务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}

```



# 10.Fork/Join

## 10.1Fork/小oin框架简介

Fok小oin它可以将T个大的任务拆分成多个子任务进行并行处理，最后将子任务结果合并成最后的计算结果，并进行输出。Fok/小oin框架要完成两件事情：

Fork:把一个复杂任务进行分拆，大事化小

Join:把分拆任务的结果进行合并，

1. **任务分割：**首先Fok/小oin框架需要把大的任务分割成足够小的子任务，如果子任务比较大的话还要对子任务进行继续分割
2. **执行任务并合并结果：**分割的子任务分别放到双端队列里，然后几个启动线程分别从双端队列里获取任务执行。子任务执行完的结果都放在另外一个队列里，启动一个线程从队列里取数据，然后合并这些数据。

在Java的Fork/Join框架中，使用两个类完成上述操作

- ForkJoinTask:我们要使用Fork/月oin框架，首先需要创建一个ForkJoin任务。该类提供了在任务中执行fok和join的机制。通常情况下我们不需要直接集成ForkJoinTask类，只需要继承它的子类，Fork/Join框架提供了两个子类：
	- a.RecursiveAction:用于没有返回结果的任务
	- b.RecursiveTask:用于有返回结果的任务，
- ForkJoinPoo:ForkJoinTask需要通过ForkJoinPool来执行
- RecursiveTask:继承后可以实现递归（自己调自己）调用的任务

**Fork/Join框架的实现原理**

ForkJoinPool由ForkJoinTask数组和ForkJoinWorkerThread数组组成，

ForkJoinTask数组负责将存放以及将程序提交给ForkJoin Pool,而

ForkJoinWorkerThread负责执行这些任务。

## 10.2Fork方法

![image-20220324094724108](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324094724108.png)

![image-20220324094928449](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324094928449.png)



```java
package com.example.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * author ye
 * createDate 2022/3/24  9:50
 */
class MyTask extends RecursiveTask<Integer>{

    //拆分差值不能超过10
    private static final int VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并
    @Override
    protected Integer compute() {
        if ((end - begin) <= VALUE){
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            int middle = (begin + end) / 2;

            MyTask myTask = new MyTask(begin, middle);
            MyTask myTask1 = new MyTask(middle + 1, end);

            //拆分
            myTask.fork();
            myTask1.fork();

            //合并
            result = myTask.join() + myTask1.join();
        }
        return result;
    }
}
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        //创建分支合并池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        Integer integer = submit.get();
        System.out.println(integer);

        //关闭
        forkJoinPool.shutdown();
    }
}
```



# 11.CompletableFuture

```java
package com.example.Complaeable;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * author ye
 * createDate 2022/3/24  10:12
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用，没有返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture");
        });
        completableFuture.get();


        //异步调用，有返回值
        CompletableFuture<Integer> completableFuture02 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture02");
            return 1024;
        });
        completableFuture02.whenComplete((t, u) -> {
            System.out.println("t===" + t );//返回值
            System.out.println("u===" + u );//异常
        }).get();
    }
}

```

