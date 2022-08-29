# 第1章Java NIO概述

Java NIO(New IO或Non Blocking IO)是从Java1.4版本开始引入的一个新的IO API,可以替代标准的Java IO API。NIO支持面向缓冲区的、基于通道的IO操作。NO将以更加高效的方式进行文件的读写操作。

## 1.1阻塞IO

通常在进行同步I/O操作时，如果读取数据，代码会阻塞直至有可供读取的数据。同样，写入调用将会阻塞直至数据能够写入。传统的Server/Client模式会基于TPR(Thread per Request),服务器会为每个客户端请求建立一个线程，由该线程单独负责处理一个客户请求。这种模式带来的一个问题就是线程数量的剧增，大量的线程会增大服务器的开销。大多数的实现为了避免这个问题，都采用了线程池模型，并设置线程池线程的最大数量，这由带来了新的问题，如果线程池中有100个线程，而有100个用户都在进行大文件下载，会导致第101个用户的请求无法及时处理，即便第101个用户只想请求一个几KB大小的页面。传统的Server/Client模式如下图所示：

![image-20220324122151605](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324122151605.png)

## 1.2非阻塞IO(NIO)

NIO中非阻塞I/O采用了基于Reactor模式的工作方式，I/O调用不会被阻塞，相反是注册感兴趣的特定I/O事件，如可读数据到达，新的套接字连接等等，在发生特定事件时，系统再通知我们。NIO中实现非阻塞I/O的核心对象就是Selector,Selector就是注册各种I/O事件地方，而且当我们感兴趣的事件发生时，就是这个对象告诉我们所发生的事件，如下图所示：

![image-20220324122326785](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324122326785.png)

从图中可以看出，当有读或写等任何注册的事件发生时，可以从Selector中获得相应的SelectionKey,同时从SelectionKey中可以找到发生的事件和该事件所发生的具体的SelectableChannel,以获得客户端发送过来的数据。

非阻塞指的是IO事件本身不阻塞，但是获取IO事件的select()方法是需要阻塞等待的.区别是阻塞的IO会阻塞在IO操作上，NIO阻塞在事件获取上，没有事件就没有IO,从高层次看IO就不阻塞了.也就是说只有IO已经发生那么我们才评估IO是否阻塞，但是select()阻塞的时候IO还没有发生，何谈IO的阻塞呢？NIO的本质是延迟IO操作到真正发生IO的时候，而不是以前的只要IO流打开了就一直等待IO操作。

![image-20220324122540004](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324122540004.png)

## 1.3NIO概述

Java NIO由以下几个核心部分组成：

Channels

Buffers

Selectors

虽然Java NIO中除此之外还有很多类和组件，但Channel,Buffer和Selector构成了核心的API。其它组件，如Pipe和FileLock,只不过是与三个核心组件共同使用的工具类。

### 1.3.1 Channel

首先说一下Channel,可以翻译成"通道”。Channel和IO中的Stream(流)是差不多一个等级的。只不过Stream是单向的，譬如：InputStream,OutputStream.而Channel是双向的，既可以用来进行读操作，，又可以用来进行写操作。

NlO中的Channel的主要实现有：FileChannel、DatagramChannel、SocketChannel和ServerSocketChannel,这里看名字就可以猜出个所以然来：分别可以对应文件IO.UDP和TCP(Server和Client)

### 1.3.2 Buffers

NIO中的关键Buffer实现有：ByteBuffer,CharBuffer,DoubleBuffer,FloatBuffer,IntBuffer,,LongBuffer,ShortBuffer,分别对应基本数据类型：byte,char,double,float,int,long,short

### 1.3.3 Selector

Selector运行单线程处理多个Channel,如果你的应用打开了多个通道，但每个连接的流量都很低，使用Selector就会很方便。例如在一个聊天服务器中。要使用Selector,,得向Selector注册Channel,然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新的连接进来、数据接收等。

# 第2章Java NIO(Channel)

## 2.1 Channel概述

Channel是一个通道，可以通过它读取和写入数据，它就像水管一样，网络数据通过Channel读取和写入。通道与流的不同之处在于通道是双向的，流只是在一个方向上移动（一个流必须是InputStream或者OutputStream的子类），而且通道可以用于读、写或者同时用于读写。因为Channel是全双工的，所以它J以比流更好地映射底层操作系统的API。

NIO中通过channel封装了对数据源的操作，通过channel我们可以操作数据源，但又不必关心数据源的具体物理结构。这个数据源可能是多种的。比如，可以是文件，也可以是网络socket。在大多数应用中，channel与文件描述符或者socket是一一对应的。Channel.用于在字节缓冲区和位于通道另一侧的实体（通常是一个文件或套接字)之间有效地传输数据。

**Channel源码**

```java
public interface Channel extends Closeable {

    /**
     * Tells whether or not this channel is open.
     *
     * @return <tt>true</tt> if, and only if, this channel is open
     */
    public boolean isOpen();

    /**
     * Closes this channel.
     *
     * <p> After a channel is closed, any further attempt to invoke I/O
     * operations upon it will cause a {@link ClosedChannelException} to be
     * thrown.
     *
     * <p> If this channel is already closed then invoking this method has no
     * effect.
     *
     * <p> This method may be invoked at any time.  If some other thread has
     * already invoked it, however, then another invocation will block until
     * the first invocation is complete, after which it will return without
     * effect. </p>
     *
     * @throws  IOException  If an I/O error occurs
     */
    public void close() throws IOException;

}
```

与缓冲区不同，通道API主要由接口指定。不同的操作系统上通道实现(Channel Implementation)会有根本性的差异，所以通道API仅仅描述了可以做什么。因此很自然地，通道实现经常使用操作系统的本地代码。通道接口允许您以一种受控且可移植的方式来访问底层的I/O服务。

Channel是一个对象，可以通过它读取和写入数据。拿NIO与原来的I/O做个比较，通道就像是流。所有数据都通过Buffer对象来处理。您永远不会将字节直接写入通道中，相反，您是将数据写入包含一个或者多个字节的缓冲区。同样，您不会直接从通道中读取字节，而是将数据从通道读入缓冲区，再从缓冲区获取这个字节。

Java NIO的通道类似流，但又有些不同：

- 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
- 通道可以异步地读写。
- 通道中的数据总是要先读到一个Buffer,或者总是要从一个Buffer中写入。

正如上面所说，从通道读取数据到缓冲区，从缓冲区写入数据到通道。如下图所示：

![image-20220324123746821](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324123746821.png)

## 2.2 Channel实现

下面是Java NIO中最重要的Channel的实现：

FileChannel	从文件中读写数据

DatagramChannel	能通过UDP读写网络中的数据，

SocketChannel	能通过TCP读写网络中的数据。

ServerSocketChannel	可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。

正如你所看到的，这些通道涵盖了UDP和TCP网络IO,以及文件IO

## 2.3 FileChannel介绍和示例

FileChannel类可以实现常用的read,write以及scatter/gather操作，同时它也提供了很多专用于文件的新方法。这些方法中的许多都是我们所熟悉的文件操作。

| 方法                                                  | 描述                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| <font color='red'>int read(ByteBuffer dst)></font>    | <font color='red'>从Channel中读取数据到ByteBuffer</font>     |
| <font color='red'>long read(ByteBuffer[]dsts)</font>  | <font color='red'>将Channel中的数据“分散”到ByteBuffer[]</font> |
| <font color='red'>int write(ByteBuffer src)</font>    | <font color='red'>将ByteBuffer中的数据写入到Channel</font>   |
| <font color='red'>long write(ByteBuffer[]srcs)</font> | <font color='red'>将ByteBuffer[]中的数据“聚集”到Channel</font> |
| long position()                                       | 返回此通道的文件位置                                         |
| FileChannel position(long p)                          | 设置此通道的文件位置                                         |
| long size()                                           | 返回此通道的文件的当前大小                                   |
| FileChannel truncate(long s)                          | 将此通道的文件截取为给定大小                                 |
| void force(boolean metaData)                          | 强制将所有对此通道的文件更新写入到存储设备中                 |

下面是一个使用FileChannel读取数据到Buffer中的示例：

```java
package com.example.Channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author ye
 * createDate 2022/3/24  12:48
 */
public class FileChannelDemo01 {
    //FileChannel读取数据到buffer中
    public static void main(String[] args) throws Exception{
        //创建FiLeChannel
        RandomAccessFile file = new RandomAccessFile("Test/src/test.txt","rw");
        FileChannel channel = file.getChannel();

        //创Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //读取数据到buffer中
        int bufferRead = channel.read(byteBuffer);
        while (bufferRead != -1){
            System.out.println("读取了" + byteBuffer);
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }
            byteBuffer.clear();
            bufferRead = channel.read(byteBuffer);
        }

        file.close();
        System.out.println("结束了");
    }
}
```

**Buffer通常的操作**

将数据写入缓冲区

调用buffer.flip()反转读写模式

从缓冲区读取数据

调用buffer.clear()或buffer.compact()清除缓冲区内容

## 2.4 FileChannel操作详解

### 2.4.1打开FileChannel

在使用FileChannel之前，必须先打开它。但是，我们无法直接打开一个FileChannel,需要通过使用一个InputStream、
OutputStream或RandomAccessFile来获取一个FileChannel实例。下面是通过RandomAccessFile打开FileChannel的示例：

```java
		//创建FiLeChannel
        RandomAccessFile file = new RandomAccessFile("Test/src/test.txt","rw");
        FileChannel channel = file.getChannel();
```

### 2.4.2从FileChannel读取数据

调用多个read()方法之一从FileChannel中读取数据。如：

```java
		//创Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //读取数据到buffer中
        int bufferRead = channel.read(byteBuffer);
```

首先，分配一个Buffer。.从FileChannel中读取的数据将被读到Buffer中。然后，调用FileChannel..read(方法。该方法将数据从FileChannel读取到Buffer中。read0方法返回的int值表示了有多少字节被读到了Buffer中。如果返回-1，表示到了文件末尾。

### 2.4.3向FileChannel写数据

使用FileChannel..write(方法向FileChannel写数据，该方法的参数是一个Buffer。

如：

```java
package com.example.Channel;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * author ye
 * createDate 2022/3/24  13:29
 */
public class FileChannelDemo02 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("Test/src/test02.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String value = "Hello World";

        buffer.clear();
        buffer.put(value.getBytes());

        buffer.flip();

        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

        channel.close();

    }
}
```

注意FileChannel..write()是在while循环中调用的。因为无法保证write()方法一次能向FileChannel写入多少字节，因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节。

### 2.4.4关闭FileChannels

用完FileChannel后必须将其关闭。如：

`inChannel.close()`

### 2.4.5 FileChannel的position方法

有时可能需要在FileChannel的某个特定位置进行数据的读/写操作。可以通过调用position(方法获取FileChannel的当前位置。也可以通过调用position(long pos))方法设置FileChannel的当前位置。：

这里有两个例子：

- <font color='red'>long pos = channel.position(;</font>
- <font color='red'>channel.position(pos +123);</font>

如果将位置设置在文件结束符之后，然后试图从文件通道中读取数据，读方法将返回-1(文件结束标志)。

如果将位置设置在文件结束符之后，然后向通道中写数据，文件将撑大到当前位置并写入数据。这可能导致“文件空洞”，磁盘上物理文件中写入的数据间有空隙。

### 2.4.6 FileChannel的size方法

FileChannel实例的size()方法将返回该实例所关联文件的大小。如：

<font color='red'>long fileSize = channel.size();</font>

### 2.4.7 FileChannel的truncate方法

可以使用FileChannel.truncate()方法截取一个文件。截取文件时，文件将中指定长度后面的部分将被删除。如：

<font color='red'>channel.truncate(1024);</font>

这个例子截取文件的前1024个字节。

### 2.4.8 FileChannel的force方法

FileChannel.force0方法将通道里尚未写入磁盘的数据强制写到磁盘上。出于性能方面的考虑，操作系统会将数据缓存在内存中，所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。要保证这一点，需要调用force(0方法。

force0方法有一个boolean类型的参数，指明是否同时将文件元数据（权限信息等）写到磁盘上。

### 2.4.9 FileChannel的transferTo和transferFrom方法

**通道之间的数据传输：**

如果两个通道中有一个是FileChannel,那你可以直接将数据从一个channel传输到另外一个channel。

<font color='red'>(1)transferFrom0方法</font>

FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中（译者注：这个方法在JDK文档中的解释为将字节从给定的可读取字节通道传输到此通道的文件中)。下面是一个FileChannel完成文件间的复制的例子：

```java
package com.example.Channel;

import sun.rmi.runtime.Log;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * author ye
 * createDate 2022/3/24  13:41
 */
public class FileChannelDemo03 {
    public static void main(String[] args) throws  Exception{

        RandomAccessFile file1 = new RandomAccessFile("Test/src/test.txt","rw");
        FileChannel fromChannel = file1.getChannel();

        RandomAccessFile file2 = new RandomAccessFile("Test/src/test02.txt","rw");
        FileChannel toChannel = file2.getChannel();

        //fromChannel传输到toChannel
        int position = 0;
        long size = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, size);

        file1.close();
        file2.close();
    }
}
```



方法的输入参数position表示从position处开始向目标文件写入数据，count表示最多传输的字节数。如果源通道的剩余空间小于count个字节，则所传输的字节数要小于请求的字节数。此外要注意，在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。因此，SocketChannel可能不会将请求的所有数据(count:个字节)全部传输到FileChannel中。

<font color='red'>(2)transferTo()方法</font>

transferTo()方法将数据从FileChannel传输到其他的channel中。

下面是一个transferTo0方法的例子：

```java
int position = 0;
long size = toChannel.size();
toChannel.transferTo(position, size, fromChannel);
```

## 2.5.Socket通道

​	(1)新的socket通道类可以运行非阻塞模式并且是可选择的，可以激活大程序（如网络服务器和中间件组件)巨大的可伸缩性和灵活性。本节中我们会看到，再也没有为每个socket连接使用一个线程的必要了，也避免了管理大量线程所需的上下文交换开销。借助新的O类，一个或几个线程就可以管理成百上干的活动socket连接了并且只有很少甚至可能没有性能损失。所有的socket通道类(DatagramChannel、SocketChannel和ServerSocketChannel)都继承了位于java.nio.channels..spi包中的AbstractSelectableChannel。这意味着我们可以用一个Selector对象来执行socket通道的就绪选择(readiness selection)。

​	(2)请注意DatagramChannel和SocketChannel实现定义读和写功能的接口而ServerSocketChannel不实现。ServerSocketChannel负责监听传入的连接和创建新的SocketChanne对象，它本身从不传输数据。

(3)在我们具体讨论每一种socket通道前，<font color='red'>您应该了解socket和socket通道之间的关系。</font>通道是一个连接I/O服务导管并提供与该服务交互的方法。<font color='red'>就某个socket而言，它不会再次实现与之对应的socket通道类中的socket协议APl,</font>而java.net中<font color='red'>已经存在的socket通道都可以被大多数协议操作重复使用。</font>

全部socket通道类<font color='red'>(DatagramChannel、SocketChannel和ServerSocketChannel)</font>在被实例化时都会创建一个对等socket对象。这些是我们所熟悉的来自java.net的类(Socket、.ServerSocket和DatagramSocket),它们已经被更新以识别通道。对等socket可以通过调用socket()方法从一个通道上获取。此外，这三个java.net类现在都有getChannel()方法。

​	(4)要把一个socket通道置于非阻塞模式，我们要依靠所有socket通道类的公有超级类：SelectableChannel。就绪选择(readiness selection)是一种可以用来查询通道的机制，该查询可以判断通道是否准备好执行一个目标操作，如读或写。非阻塞I/O和可选择性是紧密相连的，那也正是管理阻塞模式的AP!代码要在SelectableChannel超级类中定义的原因。

设置或重新设置一个通道的阻塞模式是很简单的，只要调用configureBlocking()方法即可，传递参数值为true则设为阻塞模式，参数值为false值设为非阻塞模式。可以通过调用isBlocking()方法来判断某个socket通道当前处于哪种模式。

​	非阻塞socket通常被认为是服务端使用的，因为它们使同时管理很多socket通道变得更容易。但是，在客户端使用一个或几个非阻塞模式的socket通道也是有益处的，例如，借助非阻塞socket通道，GUI程序可以专注于用户请求并且同时维护与一个或多个服务器的会话。在很多程序上，非阻塞模式都是有用的。

​	偶尔地，我们也会需要防止socket通道的阻塞模式被更改。API中有一个blockingLock()方法，该方法会返回一个非透明的对象引用。返回的对象是通道实现修改阻塞模式时内部使用的。只有拥有此对象的锁的线程才能更改通道的阻塞模式。

下面分别介绍这3个通道

### 2.5.1 ServerSocketChannel

ServerSocketChannel是一个基于通道的**socket监听器**。它同我们所熟悉的java.net.ServerSocket执行相同的任务，不过它增加了通道语义，因此能够在非阻塞模式下运行。

同java.net.ServerSocket一样，ServerSocketChannel也有accept()方法。一旦创建了一个ServerSocketChannel并用对等socket绑定了它，然后您就可以在其中一个上调用accept()。如果您选择在ServerSocket上调用accept()方法，那么它会同任何其他的ServerSocket表现一样的行为：总是阻塞并返回一个java.net.Socket对象。如果您选择在ServerSocketChannel上调用accept()方法则会返回SocketChannel类型的对象，返回的对象能够在非阻塞模式下运行。

换句话说：

ServerSocketChannel的accept0方法会返回SocketChannel类型对象，SocketChannel可以在非阻塞模式下运行。

其它Socket的accept0方法会阻塞返回一个Socket对象。如果ServerSocketChannel以非阻塞模式被调用，当没有传入连接在等待时，
ServerSocketChannel..accept()会立即返回null。正是这种检查连接而不阻塞的能力实现了可伸缩性并降低了复杂性。可选择性也因此得到实现。我们可以使用一个选择器实例来注册ServerSocketChannel对象以实现新连接到达时自动通知的功能。

**以下代码演示了如何使用一个非阻塞的accept()方法**

```java
package com.example.Channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * author ye
 * createDate 2022/3/24  14:02
 */
public class ServerSocketDemo01 {
    public static void main(String[] args) throws Exception{
        //端口号
        int port  = 8000;

        ByteBuffer buffer = ByteBuffer.wrap("Hello World".getBytes());

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.socket().bind(new InetSocketAddress(port));

        //设置非阻塞模式
        ssc.configureBlocking(false);

        //监听新连接传入
        while (true){
            System.out.println("Waiting for connections");
            SocketChannel sc = ssc.accept();
            if (sc == null){
                System.out.println("null");
                Thread.sleep(2000);
            }else {
                System.out.println("Incoming connection from:" + sc.socket().getRemoteSocketAddress());
                buffer.remaining(); //指针0
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
```



![image-20220324141232475](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220324141232475.png)

### 2.5.2 .SocketChannel

#### 1、SocketChannel介绍

Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道。

> A selectable channel for stream-oriented connecting sockets

以上是Java docs中对于SocketChannel的描述：SocketChannel是一种面向流连接sockets套接字的可选择通道。从这里可以看出：

- SocketChannel是用来连接Socket套接字
- SocketChannel主要用途用来处理网络I/O的通道
- SocketChannel是基于TCP连接传输
- SocketChannel实现了可选择通道，可以被多路复用的

#### 2、SocketChannel特征：

​	(1)对于已经存在的socket不能创建SocketChannel

​	(2)SocketChannel中提供的open接口创建的Channel并没有进行网络级联，需要使用connect接口连接到指定地址：

​	(3)未进行连接的SocketChannle执l/O操作时，会抛出NotYetConnectedException

​	(4)SocketChannel支持两种I/O模式：阻塞式和非阻塞式

​	(5)SocketChannel支持异步关闭。如果SocketChannel在一个线程上read阻塞，另一个线程对该SocketChannel调用shutdownInput则读阻塞的线程将返回-1表示没有读取任何数据；如果SocketChannel在一个线程上write阻塞，另一个线程对该SocketChannel调用shutdownWrite,则写阻塞的线程将抛出AsynchronousCloseException

- SO_SNDBUF		套接字发送缓冲区大小
- SO_RCVBUF 	    套接字接收缓冲区大小
- SO_KEEPALIVE    保活连接·
- O_REUSEADDR   复用地址
- SO_LINGER         有数据传输时延缓关闭Channel(只有在非阻塞模式下有用)
- TCP_NODELAY   禁用Nagle算法

#### 3、SocketChannel的使用

**(1)创建SocketChanne**

方法一

```java
SocketChannel socketChannel = SocketChannel
                .open(new InetSocketAddress("www.baidu.com", 80));
```

方法二

```java
SocketChannel socketChannel1 = SocketChannel.open();
socketChannel1.connect(new InetSocketAddress("www.baidu.com", 80));
```

直接使用有参open api或者使用无参open api,但是在无参open只是创建了一个SocketChannel对象，并没有进行实质的tcp连接。

**(2)连接校验**

```java
socketChannel.isOpen();//测试SocketChannel是否为open状东
socketChannel.isConnected();//测试SocketChannel是否已经被连接
socketChannel.isConnectionPending();//测试SocketChannel是否正在进行连接
socketChannel.finishConnect();//校验正在进行套接字连接的SocketChannel是否已经完成连接
```



**(3)读写模式**

前面提到SocketChannel支持阻塞和非阻塞两种模式：

```java
socketChannel.configureBlocking(false);
```

通过以上方法设置SocketChannel的读写模式。false表示非阻塞，true表示阻塞。

**(4)读写**

```java
SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("www.baidu.com",80));
ByteBuffer byteBuffer = ByteBuffer.allocate(16);
socketChannel.read(byteBuffer);
socketChannel.close();
System.out.println("read over");
```

以上为阻塞式读，当执行到read出，线程将阻塞，控制台将无法打印read over

```java
SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("www.baidu.com",80));
socketChannel.configureBlocking(false);
ByteBuffer byteBuffer = ByteBuffer.allocate(16);
socketChannel.read(byteBuffer);
socketChannel.close();
System.out.println("read over");
```



以上为非阻塞读，控制台将打印read overs

读写都是面向缓冲区，这个读写方式与前文中的FileChannel相同。



**(5)设置和获取参数**

```java
socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE,
                        Boolean.TRUE)
                .setOption(StandardSocketOptions.TCP_NODELAY, Boolean.TRUE);
```

通过setOptions方法可以设置socket套接字的相关参数

```java
socketChannel.getOption(StandardSocketOptions.SO_KEEPALIVE);
socketChannel.getOption(StandardSocketOptions.SO_RCVBUF);
```

可以通过getOption获取相关参数的值。如默认的接收缓冲区大小是8192byte。

SocketChannel还支持多路复用，但是多路复用在后续内容中会介绍到。

### 2.5.3 DatagramChannel

正如SocketChannel对应Socket,ServerSocketChannel对应ServerSocket,每一个DatagramChannel对象也有一个关联的DatagramSocket对象。正如SocketChannel模拟连接导向的流协议（如TCP/IP),DatagramChannel则模拟包导向的无连接协议（如UDP/IP)。DatagramChannel是无连接的，每个数据报(datagram)都是一个自包含的实体，拥有它自己的目的地址及不依赖其他数据报的
数据负载。与面向流的的socket不同，DatagramChannel可以发送单独的数据报给不同的目的地址。同样，DatagramChannel对象也可以接收来自任意地址的数据包。每个到达的数据报都含有关于它来自何处的信息（源地址）

**1、打开DatagramChannels**

```java
DatagramChannel datagramChannel = DatagramChannel.open();
InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 10086);
```

此例子是打开10086端口接收UDP数据包

**2、接收数据**

通过receive()接收UDP包

```java
//绑定
datagramChannel.bind(socketAddress);

ByteBuffer buffer = ByteBuffer.allocate(1024);

//接受
while (true){
      buffer.clear();
      SocketAddress socketAddress1 = datagramChannel.receive(buffer);
      buffer.flip();
      System.out.println(socketAddress1.toString());
      System.out.println(Charset.forName("UTF-8").decode(buffer));
}
```

SocketAddress可以获得发包的ip、端口等信息，用toString查看，格式如下

/127.0.0.1:57126

**3、发送数据**

通过send(发送UDP包

```java
while (true){
        ByteBuffer buffer = ByteBuffer.wrap("发送UDP".getBytes("UTF-8"));
        datagramChannel.send(buffer, socketAddress);
        System.out.println("发送完毕");
        Thread.sleep(1000);
    }
```

**4、连接**

UDP不存在真正意义上的连接，这里的连接是向特定服务地址用read和write接收发送数据包。

```java
		DatagramChannel datagramChannel = DatagramChannel.open();
        //绑定
        datagramChannel.bind(new InetSocketAddress(9999));

        //连接
        datagramChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

        //write
        datagramChannel.write(ByteBuffer.wrap("发送".getBytes("UTF-8")));

        //read
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            buffer.clear();
            datagramChannel.read(buffer);
            buffer.flip();
            System.out.println(Charset.forName("utf-8").decode(buffer));
        }
```

read()和write()只有在connect().后才能使用，不然会抛NotYetConnectedException异常。用read()接收时，如果没有接收到包，会抛
PortUnreachableException异常。

## 2.6、Scatter/Gather

Java NIO开始支持scatter/gather,scatter/,gather用于描述从Channel中读取或者写入到Channel的操作。

**分散(scatter)**从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据"分散(scatter)”到多个Buffer中。

**聚集(gather)**写入Channel是指在写操作时将多个buffer的数据写入同一个Channel,因此，Channel将多个Buffer中的数据“聚集(gather)”后发送到Channel。

scatter/gather经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，这样你可以方便的处理消息头和消息体。·

### 2.6.1 Scattering Reads

Scattering Reads是指数据从一个channel读取到多个buffer中。如下图描述

![image-20220325091335332](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325091335332.png)

```java
ByteBuffer header = ByteBuffer.allocate(128);
ByteBuffer body = ByteBuffer.allocate(1024);

ByteBuffer[] bufferArray = { header,body }

channel.read(bufferArray);
```

注意buffer首先被插入到数组，然后再将数组作为channel..read()的输入参数。read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer,当一个buffer被写满后，channel紧接着向另一个buffer中写。

Scattering Reads在移动下一个buffer前，必须填满当前的buffer,这也意味着它不适用于动态消息（译者注：消息大小不固定）。换句话说，如果存在消息头和消息体，消息头必须完成填充（例如128byte),Scattering Reads才能正常工作。

### 2.6.2 Gathering Writes

Gathering Writes是指数据从多个buffer写入到同一个channel。.如下图描述：

![image-20220325091633562](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325091633562.png)

```java
ByteBuffer header = ByteBuffer.allocate(128);
ByteBuffer body = ByteBuffer.allocate(1024);

ByteBuffer[] bufferArray = { header,body }

channel.read(bufferArray);
```

buffers数组是write()方法的入参，write()方法会按照buffer在数组中的顺序，将数据写入到channel,注意只有position和limit之间的数据才会被写入。因此，如果一个buffer的容量为128byte,但是仅仅包含58byte的数据，那么这58byte的数据将被写入到channel中。因此与Scattering Reads相反，Gathering Writes能较好的处理动态消息。

# 第3章Java NIO(Buffer))

## 3.1Buffer简介

Java NIO中的Buffer用于和NIlO通道进行交互。数据是从通道读入缓冲区，从缓冲区写入到通道中的。

![image-20220325091932070](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325091932070.png)

缓冲区<font color='red'>本质上是一块可以写入数据，然后可以从中读取数据的内存。</font>这块内存被包装成NIO Buffer对象，并提供了一组方法，用来方便的访问该块内存。缓冲区实际上是一个容器对象，更直接的说，其实就是一个数组，<font color='red'>在NIO库中，所有数据都是用缓冲区处理的。</font>在读取数据时，它是直接读到缓冲区中的，在写入数据时，它也是写入到缓冲区中的；任何时候访问NIO中的数据，都是将它放到缓冲区中。而在面向流I/O系统中，所有数据都是直接写入或者直接将数据读取到Stream对象中。

在NIO中，所有的缓冲区类型都继承于抽象类Buffer,最常用的就是ByteBuffer,对于java中的基本类型，基本都有一个具体Buffer类型与之相对应，它们之间的继承关系如下图所示：

![image-20220325092718746](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325092718746.png)

## 3.2 Buffer的基本用法

**1、使用Buffer读写数据，一般遵循以下四个步骤：**

(1)写入数据到Buffer

(2)调用filp()方法

(3)从Buffer中读取数据

(4)调用clear()方法或者compact()方法

当向buffer写入数据时，buffer会记录下写了多少数据。一旦要读取数据，**需要通过flip()方法将Buffer从写模式切换到读模式**。在读模式下，可以读取之前写入到buffer的所有数据。一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有
两种方式能清空缓冲区：调用clear()或compact()方法。clear()方法会**清空整个缓冲区**。compact(）方法只会清除**已经读过的数据**。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。

**2、使用Buffer的例子**

```java
package com.example.Buffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author ye
 * createDate 2022/3/25  9:29
 */
public class Demo01 {
    @Test
    public void test() throws IOException {
        RandomAccessFile file = new RandomAccessFile("src/test.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读
        int bytesRead = channel.read(buffer);

        while (bytesRead != -1){
            //转换读模式
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }
            buffer.clear();
            bytesRead = channel.read(buffer);
        }

        file.close();
    }
}
```

**3、使用IntBuffer的例子**

```java
@Test
    public void test1() {
        IntBuffer buffer = IntBuffer.allocate(8);
        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2 * (i + 1);
            buffer.put(j);
        }

        buffer.flip();

        while (buffer.hasRemaining()){
            int val = buffer.get();
            System.out.println(val);
        }
    }
```

## 3.3 Buffer的capacity、position和limit

为了理解Buffer的工作原理，需要熟悉它的三个属性：

- Capacity
- Position
- limite

position和limit的含义取决于Buffer处在读模式还是写模式。不管Buffer处在什么模式，capacity的含义总是一样的。

![image-20220325094114274](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325094114274.png)

**(1)capacity**

作为一个内存块，Buffer有一个固定的大小值，也叫"capacity”.你只能往里写capacity个byte、long,char等类型。一旦Buffer满了，需要将其清空（通过读数据或者清除数据)才能继续写数据往里写数据。

**(2)positions**

1)**写数据到Buffer中时**，position表示写入数据的当前位置，position的初始值为0当一个byte、long等数据写到Buffer后，position会向下移动到下一个可插入数据的Buffer单元。position最大可为capacity-1(因为position的初始值为0)

2)**读数据到Buffer中时，**position表示读入数据的当前位置，如position=2时表示已开始读入了3个byte,或从第3个byte开始读取。通过ByteBuffer.flip()切换到读模式时position会被重置为0,当Buffer从position读入数据后，position会下移到下一个可读入的数据Buffer单元。

**(3)limit**

1)**写数据时**，limit表示可对Buffer最多写入多少个数据。写模式下，limit等于Buffer的capacity。

2)**读数据时**，imit表示Buffer里有多少可读数据(not null的数据)，因此能读到之前写入的所有数据(limit被设置成已写数据的数量，这个值在写模式下就是position)。

## 3.4Buffer的类型

![image-20220325092718746](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325092718746.png)

这些Buffer类型代表了不同的数据类型。换句话说，就是可以通过char,short,int,long,float或double类型来操作缓冲区中的字节。

## 3.5 Buffer分配和写数据

### 1、Buffer分配

要想获得一个Buffer对象首先要进行分配。每一个Buffer类都有一个allocate方法。

**下面是一个分配48字节capacity的ByteBuffer的例子。**

```java
ByteBuffer buf = ByteBuffer.allocate(48);
```

**这是分配一个可存储1024个字符的CharBuffer:**

```java
CharBuffer buf = CharBuffer.allocate(1024);
```

### 2、向Buffer中写数据

写数据到Buffer有两种方式：

1. 从Channel写到Buffer。
2. 通过Buffer的putO方法写到Buffer里。

**从Channel写到Buffer的例子**

```java
int bytesRead = inChannel.read(buf);//read into buffer.
```

**通过put方法写Buffer的例子：**

```java
buf.put(127)
```

### 3、flip()方法

flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0,并将imit设置成之前position的值。换句话说，position现在用于标记读的位置limit表示之前写进了多少个byte、char等（现在能读取多少个byte、char等）

## 3.6从Buffer中读取数据

**从Buffer中读取数据有两种方式：**

1. 从Buffer读取数据到Channel。
2. 使用get()方法从Buffer中读取数据，

**从Buffer读取数据到Channel的例子：**

```java
//read from buffer into channel.
int bytesWritten = inChannel.write(buf);
```

**使用get()方法从Buffer中读取数据的例子**

```java
byte aByte = buf.get();
```

get方法有很多版本，允许你以不同的方式从Buffer中读取数据。例如，从指定position读取，或者从Buffer中读取数据到字节数组。

## 3.7 Buffer几个方法-

### 1、rewind()方法

Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素(byte、char等)。

### 2、clear()与compact()方法

一旦读完Buffer中的数据，需要让Buffer准备好再次被写入。可以通过clear()或compact()方法来完成。

如果调用的是clear()方法，position将被设回0，limit被设置成capacity的值。换句话说，Buffer被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。

如果Buffer中有一些未读的数据，调用clear()方法，数据将“被遗忘”，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。

如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。

compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。.现在Buffer准备好写数据了，但是不会覆盖未读的数据。

### 3、mark()与reset()方法

通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset(0方法恢复到这个position。例如：

```java
buffer.mark();
//call buffer.get() a couple of times,..during parsing.
buffer.reset();//set position back to mark.
```

## 3.8缓冲区操作

### 1、缓冲区分片

在NIO中，除了可以分配或者包装一个缓冲区对象外，还可以根据现有的缓冲区对象来创建一个子缓冲区，即在现有缓冲区切出一片来作为一个新的缓冲区，但现有的缓冲区与创建的子缓冲区在底层数组层面上是数据共享的，也就是说，子缓冲区相当于是现有缓冲区的一个视图窗口。调用slic()方法可以创建一个子缓冲区。

```java
		ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer buffer1 = buffer.slice();

        //改变子缓冲区内容
        for (int i = 0; i < buffer1.capacity(); i++) {
            buffer1.put(i, (byte) (buffer1.get(i) * 2));
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
```

### 2、只读缓冲区

只读缓冲区非常简单，可以读取它们，但是不能向它们写入数据。可以通过调用缓冲区的asReadOnlyBuffer()方法，将任何常规缓冲区转换为只读缓冲区，这个方法返回一个与原缓冲区完全相同的缓冲区，并与原缓冲区共享数据，只不过它是只读的。如果原缓冲区的内容发生了变化，只读缓冲区的内容也随之发生变化：

```java
		ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer buffer1 = buffer.asReadOnlyBuffer();

        buffer.put(3, (byte) 99);

        buffer1.flip();
        while (buffer1.hasRemaining()){
            System.out.println(buffer1.get());
        }
```

### 3、直接缓冲区

直接缓冲区是为加快I/O速度，使用一种特殊方式为其分配内存的缓冲区，JDK文档中的描述为：给定一个直接字节缓冲区，Java虚拟机将尽最大努力直接对它执行本机I/O操作。也就是说，它会在每一次调用底层操作系统的本机I/O操作之前（或之后），尝试避免将缓冲区的内容拷贝到一个中间缓冲区中或者从一个中间缓冲区中拷贝数据。要分配直接缓冲区，需要调用allocateDirect()方法，而不是allocate()方法，使用方式与普通缓冲区并无区别。

```java
		RandomAccessFile file = new RandomAccessFile("src/test.txt", "rw");
        RandomAccessFile file3 = new RandomAccessFile("src/test03.txt", "rw");

        FileChannel channel = file.getChannel();
        FileChannel channel3 = file3.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true){
            buffer.clear();
            int r = channel.read(buffer);
            if (r == -1){
                break;
            }
            buffer.flip();
            channel3.write(buffer);
        }
		file.close();
        file3.close();
```

### 4、内存映射文件I/O

内存映射文件I/O是一种读和写文件数据的方法，它可以比常规的基于流或者基于通道的I/O快的多。内存映射文件I/O是通过使文件中的数据出现为内存数组的内容来完成的，这其初听起来似乎不过就是将整个文件读到内存中，但是事实上并不是这样。一般来说，只有文件中实际读取或者写入的部分才会映射到内存中。

```java
		RandomAccessFile file = new RandomAccessFile("src/test.txt", "rw");
        FileChannel channel = file.getChannel();

        MappedByteBuffer mapped = channel.map(FileChannel.MapMode.READ_WRITE, start, size);

        mapped.put(0, (byte) 97);
        mapped.put(1023, (byte) 999);

        while (mapped.hasRemaining()){
            System.out.println((char)mapped.get());
        }

        file.close();
```

# 第4章Java NIO(Selector)

## 4.1 Selector简介

### 1、Selector和Channel关系

Selector一般称为选择器，也可以翻译为多路复用器。它是Java NIO核心组件中的一个，用于检查一个减多个NIO Channel(通道)的状态是否处于可读、可写。如此可以实现单线程管理多个channels,也就是可以管理多个网络链接。

![image-20220325121305944](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325121305944.png)

使用Selector的好处在于：使用更少的线程来就可以来处理通道了，相比使用多个线程，避免了线程上下文切换带来的开销。

### 2、可选择通道(SelectableChannel)

​	(1)不是所有的Channel都可以被Selector复用的。比方说，FileChannel就不能被选择器复用。判断一个Channel能被Selector复用，有一个前提：判断他是否继承了一个抽象类SelectableChannel。.如果继承了SelectableChannel,则可以被复用，否则不能。

​	(2)SelectableChannel类提供了实现通道的可选择性所需要的公共方法。它是所有支持就绪检查的通道类的父类。所有socket通道，都继承了SelectableChannel类都是可选择的，包括从管道(Pipe)对象的中获得的通道。而FileChannel类，没有继承SelectableChannel,
因此是不是可选通道。

​	(3)一个通道可以被注册到多个选择器上，但对每个选择器而言只能被注册一次。通道和选择器之间的关系，使用注册的方式完成。SelectableChannel可以被注册到Selector对象上，在注册的时候，需要指定通道的哪些操作，是Selector感兴趣的。

![image-20220325121641925](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325121641925.png)



### 3、Channel注册到Selector

(1)使用Channel..register(Selector sel,int ops)方法，将一个通道注册到一个选择器时。第一个参数，指定通道要注册的选择器。第二个参数指定选择器需要查询的通道操作。

(2)可以供选择器查询的通道操作，从类型来分，包括以下四种：

- 可读：SelectionKey.OP_READ
- 可写：SelectionKey.OP_WRITE
- 连接：SelectionKey.OP_CONNECT
- 接收：SelectionKey.OP_ACCEPT

如果Selector对通道的多操作类型感兴趣，可以用“位或”操作符来实现
比如：int key=SelectionKey.OP_READ| SelectionKey.OP_WRITE

(3)选择器查询的不是通道的操作，而是通道的某个操作的一种就绪状态。什么是操作的就绪状态？一旦通道具备完成某个操作的条件，表示该通道的某个操作已经就绪，就可以被Selector查询到，程序可以对通道进行对应的操作。比方说，某个SocketChannel通道可以连接到旷一个服务器，则处于“连接就绪”(OP_CONNECT)。再比方说，一个ServerSocketChannel服务器通道准备好接收新进入的连接，则处于"接收就绪”(OP_ACCEPT)状态。还比方说，一个有数据可读的通道，可以说是“读就绪”(OP_READ)。一个等待写数据的通道可以说是“写就绪”(OP_WRITE)。

### 4、选择键(SelectionKey)

​	(1)Channel注册到后，并且一旦通道处于某种就绪的状态，就可以被选择器查询到。这个工作，使用选择器Selector的select()方法完成。select方法的作用，对感兴趣的通道操作，进行就绪状态的查询。

​	(2)Seleator可以不断的查询Channel中发生的操作的就绪状态。并且挑选感兴趣的操作就绪状态。一旦通道有操作的就绪状态达成，并且是Selector感兴趣的操作，就会被Selector选中，放入选择键集合中。

​	(3)一个选择键，首先是包含了注册在Selector的通道操作的类型，比方说SelectionKey.OP_READ。也包含了特定的通道与特定的选择器之间的注册关系。

开发应用程序，选择键是编程的关键。NIO的编程，就是根据对应的选择键，进行不同的业务逻辑处理。

​	(4)选择键的概念，和事件的概念比较相似。一个选择键类似监听器模式里边的一个事件。由于Selector不是事件触发的模式，而是主动去查询的模式，所以不叫事件Event,而是叫SelectionKey选择键。

## 4.2 Selector的使用方法

### 1.Selector的创建

通过调用Selector.open0方法创建一个Selector对象，如下：

```java
//1、获取Selector.选择器
Selector selector = Selector.open();
```

### 2.注册Channel到Selector

要实现Selector管理Channel,需要将channel注册到相应的Selector上

```java
		//1、获取Selector选择器
        Selector selector = Selector.open();
        //2、获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3.设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //4、绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //5、将通道注册到选择器上，并制定监听事件为，：“接收”事件约
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
```

上面通过调用通道的register(O方法会将它注册到一个选择器上

首先需要注意的是：

(I)与Selector一起使用时，**Channel必须处于非阻塞模式**下，否则将抛出异常IllegalBlockingModeException。.这意味着，FileChannel不能与Selector一起使用，因为FileChannel不能切换到非阻塞模式，而套接字相关的所有的通道都可以。

(2)一个通道，并没有一定要支持所有的四种操作。比如服务器通道ServerSocketChannel支持Accept接受操作，而SocketChannel客户端通道则不支持。可以通过通道上的validOps()方法，来获取特定通道下所有支持的操作集合。

### 3.轮间查询就绪操作

(1)通过Selector的select()方法，可以查询出已经就绪的通道操作，这些就绪的状态集合，包存在一个元素是SelectionKey对象的Set集合中。

(2)下面是Selector几个重载的查询select()方法：

- select():阻塞到至少有一个通道在你注册的事件上就绪了。
- select(long timeout):和select()一样，但最长阻塞事件为timeout毫秒。
- selectNow():非阻塞，只要有通道就绪就立刻返回。

select()方法返回的int值，表示有多少通道已经就绪，更准确的说，是自前一次select方法以来到这一次select方法之间的时间段上，有多少通道变成就绪状态。

例如：首次调用select()方法，如果有一个通道变成就绪状态，返回了1，若再次调用select()方法，如果另一个通道就绪了，它会再次返回1。如果对第一个就绪的channel没有做任何操作，现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了。一旦调用select()方法，并且返回值不为0时，在Selector中有一个selectedKeys()方法，用来访问已选择键集合，迭代集合的每一个选择键元素，根据就绪操作的类型

```java
		Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isAcceptable()) {

            } else if (key.isConnectable()) {

            } else if (key.isReadable()) {

            } else if (key.isWritable()) {

            }
            iterator.remove();
        }
```

### 4.停止选择的方法

选择器执行选择的过程，系统底层会依次询问每个通道是否已经就绪，这个过程可能会造成调用线程进入阻塞状态，那么我们有以下二种方式可以唤醒在select()方法中阻塞的线程。

wakeup()方法：通过调用Selector对象的wakeup()方法让处在阻塞状态的select()方法立刻返回，

该方法使得选择器上的第一个还没有返回的选择操作立即返回。如果当前没有进行中的选择操作，那么下一次对select()方法的一次调用将立即返回。

close(方法：通过close()方法关闭Selector, 

该方法使得任何一个在选择操作中阻塞的线程都被唤醒（类似wakeup())，同时
使得注册到该Selector的所有Channel被注销，所有的键将被取消，但是Channel本身并不会关闭。

## 4.3示例代码

### 1、服务端代码

```java
//客户端代码
    @Test
    public void clientTest() throws IOException {
        //1.获取主机，绑定主机和端口号
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));

        //2.切换到非阻塞模式
        channel.configureBlocking(false);

        //3.创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4.写入Buffer数据
        buffer.put(new Date().toString().getBytes());

        //5.模式切换
        buffer.flip();

        //6.写入通道
        channel.write(buffer);

        //7,关闭
        channel.close();
    }
```



### 2、客户端代码

```java
//服务端
    @Test
    public void server() throws IOException {
        //1.获取服务端通道
        ServerSocketChannel channel = ServerSocketChannel.open();

        //2.切换非阻塞模式
        channel.configureBlocking(false);

        //3.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4.绑定端口
        channel.bind(new InetSocketAddress(8080));

        //5.获取selector选择器
        Selector selector = Selector.open();


        //6.通道注册到选择器
        channel.register(selector, SelectionKey.OP_ACCEPT);

        //7.选择器轮询，后续操作
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    //获取连接
                    SocketChannel accept = channel.accept();

                    //切换非阻塞模式
                    accept.configureBlocking(false);

                    //注册
                    accept.register(selector, SelectionKey.OP_READ);
                } else if (key.isConnectable()) {

                } else if (key.isReadable()) {
                    SocketChannel channel1 = (SocketChannel) key.channel();

                    //读取数据
                    int len = 0;
                    while ((len = channel1.read(buffer)) != -1){
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                } else if (key.isWritable()) {

                }
                iterator.remove();
            }
        }

    }
```



# 第5章Java NIO(Pipe和FileLock)

## 5.1 Pipe

Java NIO管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

![image-20220325131109226](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220325131109226.png)

### 1、创建管道

通过Pipe.open()方法打开管道。

```java
Pipe pipe = Pipe.open();
```

### 2、写入管道

要向管道写数据，需要访问sink通道。

```java
Pipe.SinkChannel sinkChannel = pipe.sink();
```

通过调用SinkChannel的write()方法，将数据写入SinkChannel:

```java
	//2. 获取sink通道
        Pipe.SinkChannel sink = pipe.sink();

        //3.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello World".getBytes());
        buffer.flip();

        //4.写入数据
        sink.write(buffer);
```

### 3、从管道读取数据

从读取管道的数据，需要访问source通道，像这样：

```java
//5。获取source通道
        Pipe.SourceChannel source = pipe.source();

        //6.读取数据
        buffer.flip();

        int len = source.read(buffer);

        System.out.println(new String(buffer.array(), 0, len));

        //7.关闭
        source.close();
        sink.close();
```

### 4、示例

```java
package com.example.Pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * author ye
 * createDate 2022/3/25  13:13
 */
public class Demo01 {
    public static void main(String[] args) throws IOException {
        //1. 获取管道
        Pipe pipe = Pipe.open();

        //2. 获取sink通道
        Pipe.SinkChannel sink = pipe.sink();

        //3.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello World".getBytes());
        buffer.flip();

        //4.写入数据
        sink.write(buffer);

        //5。获取source通道
        Pipe.SourceChannel source = pipe.source();

        //6.读取数据
        buffer.flip();

        int len = source.read(buffer);

        System.out.println(new String(buffer.array(), 0, len));

        //7.关闭
        source.close();
        sink.close();
    }
}
```



## 5.2 FileLock

### 1、FileLock简介

文件锁在OS中很常见，如果多个程序同时访问、修改同一个文件，很容易因为文件数据不同步而出现问题。给文件加一个锁，同一时间，只能有一个程序修改此文件，或者程序都只能读此文件，这就解决了同步问题。

文件锁是进程级别的，不是线程级别的。文件锁可以解决多个进程并发访问、修改同一个文件的问题，但不能解决多线程并发访问、修改同一文件的问题。

使用文件锁时，同一进程内的多个线程，可以同时访问、修改此文件。

文件锁是当前程序所属的VM实例持有的，一旦获取到文件锁（对文件加锁）要调用release(),或者关闭对应的FileChannel对象，或者当前JVM退出，才会释放这个锁。

一旦某个进程（比如说JVM实例）对某个文件加锁，则在释放这个锁之前，此进程不能再对此文件加锁，就是说VM实例在同一文件上的文件锁是不重叠的（进程级别不能重复在同一文件上获取锁)。

### 2、文件锁分类：

**排它锁**：又叫独占锁。对文件加排它锁后，该进程可以对此文件进行读写，该进程独占此文件，其他进程不能读写此文件，直到该进程释放文件锁。

**共享锁**：某个进程对文件加共享锁，其他进程也可以访问此文件，但这些进程都只能读此文件，不能写。线程是安全的。只要还有一个进程持有共享锁，此文件就只能读，不能写。

### 3、使用示例：

```java
//创建FileChannel对象，文件锁只能通过FileChannel对象来使用
FileChannel fileChannel = new FileOutputStream("./1.txt").getChannel();
//对文件加锁
FileLock lock=fileChannel.lock();
//对此文件进行一些读写操作
//...
//释放数
lock.release();
```

文件锁要通过FileChannel对象使用。

### 4、获取文件锁方法

**有4种获取文件锁的方法：**

lock()对整个文件加锁，默认为排它锁。

lock(long position,long size,booean shared)自定义加锁方式。

前2个参数指定要加锁的部分（可以只对此文件的部分内容加锁），第三个参数值指定是否是共享锁。



tryLock()对整个又件加锁，默认为排它锁。

tryLock(long position,long size,booean shared) //自定义加锁方式。

如果指定为共享锁，则其它进程可读此文件，所有进程均不能写此文件，如果某进程试图对此文件进行写操作，会抛出异常。

### 5、Iock与tryLock的区别：

lock是阻塞式的，如果未获取到文件锁，会一直阻塞当前线程，直到获取文件锁

tryLock和lock的作用相同，只不过tryLock是非阻塞式的，tryLock是尝试获取文件锁，获取成功就返回锁对象，否则返回null,不会阻塞当前线程。

### 6、FileLock两个方法：

boolean isShared() //此文件锁是否是共享锁

boolean isValid(0 //此文件锁是否还有效

在某些OS上，对某个文件加锁后，不能对此文件使用通道映射。

### 7、完整例子

```java
package com.example.FileLock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * author ye
 * createDate 2022/3/25  13:31
 */
public class FileLockDemo01 {
    public static void main(String[] args) throws Exception {
        String input = "Hello World";
        System.out.println("Input:" + input);

        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());

        String filePath = "Test/src/test.txt";
        Path path = Paths.get(filePath);

        FileChannel fileChannel = FileChannel.open(
                path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

        //枷锁
        FileLock lock = fileChannel.lock();
        System.out.println("是否是共享锁:" + lock.isShared());

        fileChannel.write(buffer);
        fileChannel.close();

        //读文件
        readFile(filePath);
    }
    public static void readFile(String filePath) throws Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tr = bufferedReader.readLine();
        System.out.println("读取出的内容 : ");
        while (tr != null){
            System.out.println(tr);
            tr = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
    }
}
```

# 第6章Java NIO(其他)

## 6.1 Path

### 1、Path简介

Java Path接口是Java NIO更新的一部分，同Java NIO一起已经包括在Java6和Java7中。Java Path接口是在Java7中添加到Java NIO的。Path接口位于java.nio.file包中，所以Path接口的完全限定名称为java.nio.file.Path

Java Path实例表示文件系统中的路径。一个路径可以指向一个文件或一个目录。路径可以是绝对路径，也可以是相对路径。绝对路径包含从文件系统的根目录到它指向的文件或目录的完整路径。相对路径包含相对于其他路径的文件或目录的路径。

在许多方面，java.nio.file.Path接口类似于java.io.File类，但是有一些差别。不过，在许多情况下，可以使用Path接口来替换File类的使用。

### 2、创建Path实例

使用java.nio.file.Path实例必须创建一个Path实例。可以使用Paths类java.nio.file.Paths)中的静态方法Paths.get()来创建路径实例。

```java
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo{
	public static void main(String[] args){
		Path path = Paths.get("d:atguigu\001.txt");
    }
}
```



### 3、创建绝对路径

(1)创建绝对路径，通过调用Paths.get(方法，给定绝对路径文件作为参数来完成。

```java
Path path = Paths.get("d:\\atguigu\\001.txt");
```

上述代码中，绝对路径是d:\atquigu小001.txt。在Java字符串中，\是一个转义字符，需要编写引，告诉Java编译器在字符串中写入一个\字符。

(2)如果在Linux、MacOS等操作字体上，上面的绝对路径可能如下

`Path path = Paths.get("/home/jakobjenkov/myfile.txt");`

绝对路径现在为/home/jakobjenkov/myfile.txt.

(3)如果在Windows机器上使用了从/开始的路径，那么路径将被解释为相对于当前驱动器。

### 4、创建相对路径

Java NIO Path类也可以用于处理相对路径。您可以使用Paths.get(basePath,relativePath)方法创建一个相对路径。

```java
		//代码
        Path projects = Paths.get("d:\\atguigu","projects");
        //代码2
        Path file = Paths.get("d:\\atguigu","projects\\002.txt");
```

代码1创建了一个Java Path的实例，指向路径（目录）：d:\atguigu\projects

代码2创建了一个Path的实例，指向路径（文件）：d:atguigu\projects\002.txt

### 5、Path.normalize()

Path接口的normalize()方法可以使路径标准化。标准化意味着它将移除所有在路径字符串的中间的和.代码，并解析路径字符串所引用的路径。

```java
		String originalPath  = "d:\\atguigu\\projects\\..\\yygh-project";
        Path path1 = Paths.get(originalPath);
        System.out.println("path1 = " + path1);
        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);
```

![image-20220326122413945](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220326122413945.png)

## 6.2 Files

Java NIO Files类(java.nio.file.Files)提供了几种操作文件系统中的文件的方法。以下内容介绍Java NIO Files最常用的一些方法。java.nio.file.Files类与java.nio.file.Path实例一起工作，因此在学习Files类之前，需要先了解Path类。

### 1、Files.createDirectory()

Files.createDirectory()方法，用于根据Path实例创建一个新目录

```java
		Path path = Paths.get("Test/src/fileDemo01");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

如果该目录已经存在，则是抛出一个java.nio.file.FileAlreadyExistsException。如果出现其他错误，可能会抛出IOException。例如，如果想要的新目录的父目录不存在，则可能会抛出IOException。

### 2、Files.copy()

**(1)Fies.copy()方法从一路径拷贝一个文件到另外一个目录**

```java
		Path path = Paths.get("Test/src/test.txt");
        Path path1 = Paths.get("Test/src/fileDemo01/test.txt");
        try {
            Files.copy(path, path1);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

首先，该示例创建两个Path实例。然后，这个例子调用Files.copy(),将两个Path实例作为参数传递。这可以让源路径引用的文件被复制到目标路径引用的文件中。

如果目标文件已经存在，则抛出一个java.nio.file.FileAlreadyExistsException异常。如果有其他错误，则会抛出一个IOException。例如，如果将该文件复制到不存在的目录，则会抛出IOException。

**(2)覆盖已存在的文件**

Files..copy0方法的第三个参数。如果目标文件已经存在，这个参数指示copy()方法覆盖现有的文件。

```java
Files.copy(path, path1, StandardCopyOption.REPLACE_EXISTING);
```

### 3、Files..move()

Files.move0用于将文件从一个路径移动到另一个路径。移动文件与重命名相同，但是移动文件既可以移动到不同的目录，也可以在相同的操作中更改它的名称。

```java
	Path path = Paths.get("Test/src/test.txt");
        Path path1 = Paths.get("Test/src/fileDemo01/test.txt");
        try {
            Files.move(path, path1, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

Files..move()的第三个参数。这个参数告诉Files..move()方法来覆盖目标路径上的任何现有文件。

### 4、Files.delete()

Files..delete0方法可以删除一个文件或者目录。

```java
		Path path = Paths.get("Test/src/fileDemo01/test.txt");
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

创建指向要删除的文件的Path。然后调用Files.delete()方法。如果Files.delete()不能删除文件（例如，文件或目录不存在），会抛出一个IOException。

### 5、Files.walkFileTree()

(1)Files.walkFileTree()方法包含递归遍历目录树功能，将Path实例和FileVisitor作为参数。Path实例指向要遍历的目录，FileVisitor在遍历期间被调用。

(2)FileVisitor是一个接口，必须自己实现FileVisitor接口，并将实现的实例传递给walkFileTree()方法。在目录遍历过程中，您的FileVisitor实现的每个方法都将被调用。如果不需要实现所有这些方法，那么可以扩展SimpleFileVisitor类，它包含FileVisitor接口中所有方法的默认实现。

(3)FileVisitor接口的方法中，每个都返回一个FileVisitResult枚举实例。FileVisitResult枚举包含以下四个选项：

- CONTINUE继续
- TERMINATE终I止
- SKIP SIBLING跳过同级
- SKIP SUBTREE跳过子级

(4)查找一个名为001.txt的文件示例：

```java
		Path path = Paths.get("Test/src");
        String s = File.separator + "001.txt";
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();

                    if (fileString.endsWith(s)){
                        System.out.println("file found in path " + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
```

## 6.3 AsynchronousFileChannel

在Java7中，Java NIO中添加了AsynchronousFileChannel,也就是是异步地将数据写入文件。

### 1、创建AsynchronousFileChannel

通过静态方法open()创建

```java
		Path path = Paths.get("Test/src/text.txt");
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

open()方法的第一个参数指向与AsynchronousFileChannel相关联文件的Path实例。

第二个参数是一个或多个打开选项，它告诉AsynchronousFileChannel在文件上执行什么操作。在本例中，我们使用了StandardOpenOption.READ选项，表示该文件将被打开阅读

### 2、通过Future读取数据

可以通过两种方式从AsynchronousFileChannel读取数据。第一种方式是调用返回Future的read()方法

```java
		//(1)创建了一个AsynchronousFileChannel,
        Path path = Paths.get("Test/src/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        //(2)创建一个ByteBuffer,它被传递给read()方法作为参数，以及一个0的位置。
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> read = channel.read(buffer, 0);

        //(3)在调用read()之后，循环，直到返回的isDone()方法返回true。
        while (!read.isDone());

        //(4)读取操作完成后，数据读取到ByteBuffer中，然后打印到System.out中。
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
        channel.close();
```

上述代码：

(1)创建了一个AsynchronousFileChannel,

(2)创建一个ByteBuffer,它被传递给read()方法作为参数，以及一个0的位置。

(3)在调用read()之后，循环，直到返回的isDone()方法返回true。

(4)读取操作完成后，数据读取到ByteBuffer中，然后打印到System.out中。

### 3、通过CompletionHandler读取数据

第二种方法是调用read()方法，该方法将一个CompletionHandler作为参数

```java
		//(1)创建了一个AsynchronousFileChannel,
        Path path = Paths.get("Test/src/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        //(2)创建一个ByteBuffer,它被传递给read()方法作为参数，以及一个0的位置。
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
        channel.close();
```

### 4、通过Future写数据

和读取一样，可以通过两种方式将数据写入一个AsynchronousFileChannels

```java
		Path path = Paths.get("Test/src/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("Test".getBytes());

        buffer.flip();

        Future<Integer> write = channel.write(buffer, 0);

        while (!write.isDone());
        buffer.clear();
        System.out.println("wired over");
```

注意，文件必须已经存在。如果该文件不存在，那么wite0方法将抛出一个java.nio.file.NoSuchFileException.

### 5、通过CompletionHandler写数据

```java
		Path path = Paths.get("Test/src/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("hello NIO".getBytes());
        buffer.flip();

        channel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
```

当写操作完成时，将会调用CompletionHandler的completed()方法。如果写失败，则会调用failed()方法。

## 6.4字符集(Charset)

java中使用Charset来表示字符集编码对象：

**Charset常用静态方法**

public static Charset forName(String charsetName) //通过编码类型获得Charset对象

public static SortedMap<String,Charset> availableCharsets() //获得系统支持的所有编码方式

public static Charset defaultCharset() //获得虚拟机默认的编码方式

public static boolean isSupported(String charsetName) //判断是否支持该编码类型

**Charset常用普通方法**

public final String name() //获得Charset对象的编码类型(String)

public abstract CharsetEncoder newEncoder() //获得编码器对象

public abstract CharsetEecoder newDecoder() //获得解码器对象

```java
package com.example.Charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.SortedMap;

/**
 * author ye
 * createDate 2022/3/26  13:42
 */
public class Demo01 {
    public static void main(String[] args) throws CharacterCodingException {
        //1获取charset对象
        Charset charset = Charset.forName("UTF-8");

        //2获得编码器对象
        CharsetEncoder charsetEncoder = charset.newEncoder();

        //3创建缓冲区
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("尚硅谷");
        buffer.flip();

        //4编码
        ByteBuffer encode = charsetEncoder.encode(buffer);
        System.out.println("编码之后的结果");
        for (int i = 0; i < encode.limit(); i++) {
            System.out.println(encode.get(i));
        }

        //5获取解码器对象
        CharsetDecoder charsetDecoder = charset.newDecoder();

        //6解码
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println("解码后的结果");
        String string = decode.toString();
        System.out.println(string);


        Charset charset1 = Charset.forName("GBK");
        encode.flip();
        CharBuffer decode1 = charset1.decode(encode);
        System.out.println("解码后的结果");
        String string1 = decode1.toString();
        System.out.println(string1);

        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        for (Map.Entry<String, Charset> stringCharsetEntry : stringCharsetSortedMap.entrySet()) {
            System.out.println(stringCharsetEntry.getKey() + " = " + stringCharsetEntry.getValue());
        }

    }
}
```

# 第7章Java NIO综合案例

使用Java NIO完成一个多人聊天室功能

```java
package com.example.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/26  14:05
 */
public class ChatServer {
    //服务器端启动
    public void startServer() throws IOException {
        //1.创建selector
        Selector selector = Selector.open();

        //2.创建ServerSocketChannel
        ServerSocketChannel channel = ServerSocketChannel.open();

        //3.绑定监听端口
        channel.bind(new InetSocketAddress(8080));
        channel.configureBlocking(false);

        //4循环，等待有新链接接入
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        //5循环，等待有新链接接入
        //while (true)
        for(;;){
            int count = selector.select();

            if (count == 0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                //6根据就绪状态，凋用对应方法实现具体业务操作
                //6.1如果accept状态
                if (next.isAcceptable()){
                    acceptOperation(channel, selector);
                }
                //6.2如果可读状态
                else if (next.isReadable()){
                    readOperation(selector, next);
                }
                iterator.remove();
            }
        }
    }

    //处理可读状态操作
    private void readOperation(Selector selector, SelectionKey key) throws IOException {
        //1从SelectionKey.获取到已经就绪的通道
        SocketChannel channel = (SocketChannel) key.channel();
        //2创buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3循环读取客户瑞消息
        int read = channel.read(buffer);
        String message = "";
        if (read > 0){
            buffer.flip();

            message += Charset.forName("UTF-8").decode(buffer);

        }
        //4将channel再次注册到选择器上，监听可读状
        channel.register(selector, SelectionKey.OP_READ);

        //把客户端发送消息，广播到其他客户端
        if (message.length() > 0){
            System.out.println(message);
            CastOtherClient(message, selector, channel);
        }
    }

    //广播到其它客户端
    private void CastOtherClient(String message, Selector selector, SocketChannel channel) throws IOException {
        //1获取所有已经接入channel
        Set<SelectionKey> keys = selector.keys();

        //2循环想所有channel广播消息
        for (SelectionKey key : keys) {
            Channel channel1 = key.channel();
            if (channel1 instanceof SocketChannel && channel1 != channel){
                ((SocketChannel) channel1).write(Charset.forName("UTF-8").encode(message));
            }
        }
    }

    //处理接入状态操作
    private void acceptOperation(ServerSocketChannel channel, Selector selector) throws IOException {
        //1接入状态，创建socketChannel
        SocketChannel accept = channel.accept();

        //2把socketChannel设置非阻塞模式
        accept.configureBlocking(false);

        //3把channel注册到seLector,选择器上，监听可读状态
        accept.register(selector,SelectionKey.OP_READ);

        //4客户端回复信息
        accept.write(Charset.forName("UTF-8").encode("欢迎进入聊天室"));
    }

    public static void main(String[] args) {
        try {
            new ChatServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

```java
package com.example.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * author ye
 * createDate 2022/3/26  14:06
 */
public class ChatClient {
    public void startClient(String name) throws IOException {
        //连接服务端
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1", 8080));


        //接收服务端响应数据
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        new Thread(new ClientThread(selector)).start();
        //向服务器端发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            if (s.length() > 0){
                socketChannel.write(Charset.forName("UTF-8").encode(name + ": " +s));
            }
        }
    }
    public static void main(String[] args) {

    }
}

```

```java
package com.example.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/26  14:37
 */
public class ClientThread implements Runnable{
    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        for(;;){
            int count = 0;
            try {
                count = selector.select();
                if (count == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isReadable()) {
                        readOperation(selector, next);
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readOperation(Selector selector, SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        //2创buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3循环读取客户瑞消息
        int read = channel.read(buffer);
        String message = "";
        if (read > 0){
            buffer.flip();
            message += Charset.forName("UTF-8").decode(buffer);

        }
        //4将channel再次注册到选择器上，监听可读
        channel.register(selector, SelectionKey.OP_READ);

        if (message.length() > 0){
            System.out.println(message);
        }
    }
}

```

```java
package com.example.client;

import java.io.IOException;

/**
 * author ye
 * createDate 2022/3/26  14:44
 */
public class AClient {
    public static void main(String[] args) throws IOException {
        new ChatClient().startClient("lucy");
    }
}

package com.example.client;

import java.io.IOException;

/**
 * author ye
 * createDate 2022/3/26  14:44
 */
public class BClient {
    public static void main(String[] args) throws IOException {
        new ChatClient().startClient("mary");
    }
}

```

